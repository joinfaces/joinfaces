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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
 * @param <T> Type of the actual {@link ServletContainerInitializer} implementation
 * @author Lars Grefer
 */
@RequiredArgsConstructor
@Getter
@Setter
@Slf4j
public class ServletContainerInitializerRegistrationBean<T extends ServletContainerInitializer> implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

	private final Class<T> servletContainerInitializerClass;
	private boolean usePreparedScanResult = true;

	@Override
	public void customize(ConfigurableServletWebServerFactory factory) {
		factory.addInitializers(servletContext -> {
			T servletContextInitializer = BeanUtils.instantiateClass(getServletContainerInitializerClass());
			servletContextInitializer.onStartup(getClasses(), servletContext);
		});
	}

	protected Set<Class<?>> getClasses() {
		return findPreparedScanResult().orElseGet(this::performClasspathScan);
	}

	protected Optional<Set<Class<?>>> findPreparedScanResult() {

		if (!isUsePreparedScanResult()) {
			return Optional.empty();
		}

		String resourceName = "META-INF/joinfaces/" + getServletContainerInitializerClass().getName() + ".classes";
		InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(resourceName);

		if (resourceAsStream == null) {
			log.debug("No prepared scan-result found for {}", getServletContainerInitializerClass());
			return Optional.empty();
		}

		StopWatch stopWatch = new StopWatch(getServletContainerInitializerClass().getName());
		stopWatch.start("load scan-result");

		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8))) {
			Set<Class<?>> collect = bufferedReader.lines()
					.map(className -> {
						try {
							return Class.forName(className);
						}
						catch (ClassNotFoundException e) {
							log.debug("Failed to load class from prepared result", e);
							return null;
						}
					})
					.filter(Objects::nonNull)
					.collect(Collectors.toSet());
			return Optional.of(collect);
		}
		catch (IOException e) {
			log.warn("Failed to read prepared scan-result {}", resourceName, e);
			return Optional.empty();
		}
		finally {
			stopWatch.stop();
			log.info("Load scan classes file for {} took {}s", getServletContainerInitializerClass().getName(), stopWatch.getTotalTimeSeconds());
			if (log.isDebugEnabled()) {
				log.debug(stopWatch.prettyPrint());
			}
		}
	}

	@Nullable
	protected Set<Class<?>> performClasspathScan() {
		HandlesTypes handlesTypes = AnnotationUtils.findAnnotation(getServletContainerInitializerClass(), HandlesTypes.class);

		if (handlesTypes == null) {
			return null;
		}

		Class<?>[] handledTypes = handlesTypes.value();
		if (handledTypes.length == 0) {
			return null;
		}

		StopWatch stopWatch = new StopWatch(getServletContainerInitializerClass().getName());
		stopWatch.start("prepare");

		ClassGraph classGraph = new ClassGraph()
				.enableClassInfo();

		// Only scan for Annotations if we have to
		if (Arrays.stream(handledTypes).anyMatch(Class::isAnnotation)) {
			classGraph = classGraph.enableAnnotationInfo();
		}

		classGraph = classGraph.enableExternalClasses()
				.enableSystemPackages() // Find classes in javax.faces
				.blacklistPackages("java", "jdk", "sun", "javafx", "oracle")
				.blacklistPackages("javax.xml", "javax.el", "javax.persistence")
				.blacklistModules("java.*", "jdk.*")
				.filterClasspathElements(path -> !JarUtils.getJreLibOrExtJars().contains(path))
				.filterClasspathElements(path -> {
					log.debug("Path {}", path);
					return true;
				});

		Set<Class<?>> classes = new HashSet<>();

		stopWatch.stop();
		stopWatch.start("classpath scan");

		try (ScanResult scanResult = classGraph.scan()) {

			stopWatch.stop();
			stopWatch.start("collect results");

			for (Class<?> handledType : handledTypes) {
				ClassInfoList classInfos;
				if (handledType.isAnnotation()) {
					classInfos = scanResult.getClassesWithAnnotation(handledType.getName());
				}
				else if (handledType.isInterface()) {
					classInfos = scanResult.getClassesImplementing(handledType.getName());
				}
				else {
					classInfos = scanResult.getSubclasses(handledType.getName());
				}
				classes.addAll(classInfos.loadClasses());
			}

			handleScanResult(scanResult);
		}
		finally {
			stopWatch.stop();
			log.info("Resolving classes for {} took {}s", getServletContainerInitializerClass().getName(), stopWatch.getTotalTimeSeconds());
			if (log.isDebugEnabled()) {
				log.debug(stopWatch.prettyPrint());
			}
		}

		return classes.isEmpty() ? null : classes;
	}

	protected void handleScanResult(ScanResult scanResult) {

	}
}
