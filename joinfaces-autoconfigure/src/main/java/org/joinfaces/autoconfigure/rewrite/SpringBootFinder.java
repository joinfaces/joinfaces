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
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.ocpsoft.rewrite.annotation.api.ClassVisitor;
import org.ocpsoft.rewrite.annotation.spi.ClassFinder;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * Finds classes that contain the given annotations and match the given package.
 * <p>
 * Scans the classpath using Spring's {@link ClassPathScanningCandidateComponentProvider}.
 *
 * @author Patrick Mentz
 */
@Slf4j
@Getter
@AllArgsConstructor
public class SpringBootFinder implements ClassFinder {

	private final String packageFilter;

	final Set<Class<? extends Annotation>> supportedAnnotations;

	@Override
	public void findClasses(final ClassVisitor visitor) {
		final ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
		for (final Class<? extends Annotation> supportedAnnotation : this.supportedAnnotations) {
			scanner.addIncludeFilter(new AnnotationTypeFilter(supportedAnnotation));
		}
		log.debug("Scanning in package '{}'", this.packageFilter);
		for (final BeanDefinition bd : scanner.findCandidateComponents(this.packageFilter)) {
			log.debug("Found class {}", bd.getBeanClassName());
			try {
				visitor.visit(Class.forName(bd.getBeanClassName()));
			}
			catch (final NoClassDefFoundError e) {
				// reference to another class unknown to the classloader
				log.warn("Could not load class '{}'", bd.getBeanClassName(), e);
			}
			catch (final ClassNotFoundException e) {
				// should no happen, because we found the class on the classpath
				throw new IllegalStateException("Unable to load class: " + bd.getBeanClassName(), e);
			}
		}
	}

	@Override
	public int priority() {
		return 100;
	}
}
