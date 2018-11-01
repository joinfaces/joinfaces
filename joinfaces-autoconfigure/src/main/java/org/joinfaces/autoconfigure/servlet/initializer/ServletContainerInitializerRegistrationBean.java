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

package org.joinfaces.autoconfigure.servlet.initializer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.annotation.HandlesTypes;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import io.github.classgraph.utils.JarUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.RegistrationBean;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.StopWatch;

/**
 * {@link RegistrationBean} for {@link ServletContainerInitializer}s.
 * <p>
 * This is implemented as {@link WebServerFactoryCustomizer} so its only applied to embedded servlet-containers.
 * When deployed as war file, the external servlet-container will handle the {@link ServletContainerInitializer}.
 *
 * @param <T> The type of the {@link ServletContainerInitializer}
 * @author Lars Grefer
 */
@RequiredArgsConstructor
@Getter
@Setter
@Slf4j
public class ServletContainerInitializerRegistrationBean<T extends ServletContainerInitializer> implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

	private final Class<T> servletContainerInitializerClass;

	@Override
	public void customize(ConfigurableServletWebServerFactory factory) {
		factory.addInitializers(servletContext -> {
			HandlesTypes handlesTypes = AnnotationUtils.findAnnotation(getServletContainerInitializerClass(), HandlesTypes.class);

			Set<Class<?>> classes = null;

			if (handlesTypes != null) {
				classes = getClasses(handlesTypes);
			}

			BeanUtils.instantiateClass(getServletContainerInitializerClass())
					.onStartup(classes, servletContext);
		});
	}

	@Nullable
	protected Set<Class<?>> getClasses(HandlesTypes handlesTypes) {
		if (handlesTypes == null || handlesTypes.value().length == 0) {
			return null;
		}

		StopWatch stopWatch = new StopWatch(getServletContainerInitializerClass().getSimpleName());
		stopWatch.start("classpath scan");

		Set<Class<?>> classes = new HashSet<>();

		try (ScanResult scanResult = new ClassGraph()
				.enableClassInfo()
				.enableAnnotationInfo()
				.enableExternalClasses()
				.enableSystemPackages() // Find classes in com.sun.faces and javax.faces
				.blacklistPackages("java", "jdk")
				.filterClasspathElements(path -> !JarUtils.getJreLibOrExtJars().contains(path))
				.scan()) {
			stopWatch.stop();
			stopWatch.start("collection of results");

			for (Class<?> clazz : handlesTypes.value()) {
				ClassInfoList classInfos;
				if (clazz.isAnnotation()) {
					classInfos = scanResult.getClassesWithAnnotation(clazz.getName());
				}
				else if (clazz.isInterface()) {
					classInfos = scanResult.getClassesImplementing(clazz.getName());
				}
				else {
					classInfos = scanResult.getSubclasses(clazz.getName());
				}
				classes.addAll(classInfos.loadClasses());
			}

			handleScanResult(scanResult);
		}

		stopWatch.stop();

		log.info("Classpath scan for {} took {}s", stopWatch.getId(), stopWatch.getTotalTimeSeconds());
		log.debug(stopWatch.prettyPrint());

		return classes.isEmpty() ? null : classes;
	}

	protected void handleScanResult(ScanResult scanResult) {

	}
}
