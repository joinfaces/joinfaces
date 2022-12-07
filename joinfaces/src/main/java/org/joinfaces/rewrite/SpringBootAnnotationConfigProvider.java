/*
 * Copyright 2016-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.joinfaces.rewrite;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import jakarta.servlet.ServletContext;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.joinfaces.ClasspathScanUtil;
import org.ocpsoft.common.services.ServiceLoader;
import org.ocpsoft.rewrite.annotation.ClassVisitorImpl;
import org.ocpsoft.rewrite.annotation.config.AnnotationConfigProvider;
import org.ocpsoft.rewrite.annotation.spi.AnnotationHandler;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;

import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

/**
 * An {@link HttpConfigurationProvider} that scans classes in the classpath for
 * <a href="https://github.com/ocpsoft/rewrite/blob/master/documentation/src/main/asciidoc/configuration/annotations/index.asciidoc">Rewrite Annotations</a>.
 * <p>
 * Inspired by the {@link AnnotationConfigProvider}: Finds class' names and handles the rule evaluation via
 * {@link ClassVisitorImpl}.
 *
 * @author Patrick Mentz
 * @author Lars Grefer
 * @see AnnotationConfigProvider
 * @see org.ocpsoft.rewrite.annotation.scan.WebClassesFinder
 */
@Slf4j
@RequiredArgsConstructor
public class SpringBootAnnotationConfigProvider extends HttpConfigurationProvider {

	@Getter
	@Setter
	private boolean enabled = true;

	@Getter
	@Setter
	private List<String> basePackages;

	@Override
	@Nullable
	public Configuration getConfiguration(final ServletContext servletContext) {

		if (!this.isEnabled()) {
			return null;
		}

		// Generate a list of all relevant annotations
		final Set<Class<? extends Annotation>> ruleAnnotations = new LinkedHashSet<>();
		final List<AnnotationHandler<Annotation>> annotationHandlers = new ArrayList<>();
		for (AnnotationHandler<Annotation> handler : (Iterable<AnnotationHandler<Annotation>>) ServiceLoader.load(AnnotationHandler.class)) {
			annotationHandlers.add(handler);
			ruleAnnotations.add(handler.handles());
		}

		final ClassVisitorImpl ruleBuilderVisitor = new ClassVisitorImpl(annotationHandlers, servletContext);

		Set<Class<?>> scanResult = findPreparedScanResult(servletContext.getClassLoader())
				.orElseGet(() -> scanClasses(ruleAnnotations));

		scanResult.forEach(ruleBuilderVisitor::visit);

		return ruleBuilderVisitor;
	}

	@Override
	public int priority() {
		return 100;
	}

	private Set<Class<?>> scanClasses(final Set<Class<? extends Annotation>> supportedAnnotations) {
		Set<Class<?>> result = new LinkedHashSet<>();

		ClassGraph classGraph = new ClassGraph()
				.enableAllInfo()
				.enableExternalClasses();
		if (!CollectionUtils.isEmpty(this.basePackages)) {
			classGraph = classGraph.whitelistPackages(this.basePackages.toArray(new String[0]));
		}

		try (ScanResult scanResult = classGraph.scan()) {
			for (final Class<? extends Annotation> supportedAnnotation : supportedAnnotations) {
				result.addAll(scanResult.getClassesWithAnnotation(supportedAnnotation.getName()).loadClasses(true));
				result.addAll(scanResult.getClassesWithMethodAnnotation(supportedAnnotation.getName()).loadClasses(true));
				result.addAll(scanResult.getClassesWithFieldAnnotation(supportedAnnotation.getName()).loadClasses(true));
			}
		}

		return result;
	}

	private Optional<Set<Class<?>>> findPreparedScanResult(ClassLoader classLoader) {
		String resourceName = "META-INF/joinfaces/" + AnnotationHandler.class.getName() + ".classes";
		return ClasspathScanUtil.readClassSet(resourceName, classLoader);
	}
}
