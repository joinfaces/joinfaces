/*
 * Copyright 2016-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.joinfaces.autoconfigure.rewrite;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.ServletContext;

import lombok.extern.slf4j.Slf4j;
import org.ocpsoft.common.services.ServiceLoader;
import org.ocpsoft.rewrite.annotation.ClassVisitorImpl;
import org.ocpsoft.rewrite.annotation.api.ClassVisitor;
import org.ocpsoft.rewrite.annotation.config.AnnotationConfigProvider;
import org.ocpsoft.rewrite.annotation.spi.AnnotationHandler;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * An {@link HttpConfigurationProvider} that scans classes in the classpath for
 * <a href="https://github.com/ocpsoft/rewrite/blob/master/documentation/src/main/asciidoc/configuration/annotations/index.asciidoc">Rewrite Annotastions</a>.
 * <p>
 * Inspired by the {@code AnnotationConfigProvider}: Finds class' names and handles the rule evaluation via
 * {@link ClassVisitorImpl}.
 * <p>
 * Note: This scanner is disabled by default to stay compliant with the original behaviour and because the classpath
 * scanning is very time costly if the packages are not narrowed down.
 *
 * @author Patrick Mentz
 * @see AnnotationConfigProvider
 * @see org.ocpsoft.rewrite.annotation.scan.WebClassesFinder
 */
@Slf4j
public class SpringBootAnnotationConfigProvider extends HttpConfigurationProvider {

	public static final String SCAN_CLASSPATH = "org.joinfaces.rewrite.annotation.SCAN_CLASSPATH";

	@Override
	public Configuration getConfiguration(final ServletContext servletContext) {

		if (!Boolean.valueOf(servletContext.getInitParameter(SCAN_CLASSPATH))) {
			log.debug("Annotation scanning in classpath is disabled!");
			return null;
		}

		final String packageFilters = Optional.ofNullable(
				servletContext.getInitParameter(AnnotationConfigProvider.CONFIG_BASE_PACKAGES)).orElse("").trim();
		// rewrite allows disable annotation scanning globally
		if (packageFilters.equalsIgnoreCase("none")) {
			log.debug("Annotation scanning is globally disabled!");
			return null;
		}
		else if (packageFilters.isEmpty()) {
			log.warn("No base package defined, annotation scanning may be extremely slow");
		}

		// Generate a list of all relevant annotations
		final Set<Class<? extends Annotation>> ruleAnnotations = new LinkedHashSet<>();
		final List<AnnotationHandler<Annotation>> annotationHandlers = new ArrayList<>();
		@SuppressWarnings("unchecked")
		final Iterator<AnnotationHandler<Annotation>> handlerIterator = ServiceLoader.load(
				AnnotationHandler.class).iterator();
		while (handlerIterator.hasNext()) {
			final AnnotationHandler<Annotation> handler = handlerIterator.next();
			annotationHandlers.add(handler);
			ruleAnnotations.add(handler.handles());
		}

		final ClassVisitorImpl ruleBuilderVisitor = new ClassVisitorImpl(annotationHandlers, servletContext);

		scanClasses(packageFilters.split("\\s*,\\s*"), ruleAnnotations, ruleBuilderVisitor);
		return ruleBuilderVisitor;
	}

	@Override
	public int priority() {
		return 100;
	}

	/**
	 * Scans classes in the given package for one of the annotations given and calls the given visitor on them.
	 *
	 * @param basePackages the packages to scan
	 * @param supportedAnnotations the annotations to search for
	 * @param visitor the visitor to trigger with matching classes
	 */
	private void scanClasses(final String[] basePackages,
			final Set<Class<? extends Annotation>> supportedAnnotations,
			final ClassVisitor visitor) {
		final ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
		for (final Class<? extends Annotation> supportedAnnotation : supportedAnnotations) {
			scanner.addIncludeFilter(new AnnotationTypeFilter(supportedAnnotation));
		}

		for (String basePackage : basePackages) {
			log.debug("Scanning package '{}'", basePackage);
			for (final BeanDefinition bd : scanner.findCandidateComponents(basePackage)) {
				visitClass(bd.getBeanClassName(), visitor);
			}
		}
	}

	/**
	 * Retrieves the given class by name and calls visitor on it.
	 *
	 * @param className the name of the class to visit
	 * @param visitor the visitor instance to use
	 */
	private void visitClass(String className, ClassVisitor visitor) {
		try {
			log.debug("Found class {}", className);
			visitor.visit(Class.forName(className));
		}
		catch (final ClassNotFoundException e) {
			// should not happen, because we found the class on the classpath
			throw new IllegalStateException("Unable to load class: " + className, e);
		}
	}
}
