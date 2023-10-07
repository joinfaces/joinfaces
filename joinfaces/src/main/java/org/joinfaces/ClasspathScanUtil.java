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

package org.joinfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import org.springframework.aot.generate.GenerationContext;
import org.springframework.aot.hint.TypeReference;
import org.springframework.util.StringUtils;

/**
 * Utility class for handling classpath scan results.
 *
 * @author Lars Grefer
 */
@Slf4j
@UtilityClass
public class ClasspathScanUtil {

	public static Optional<Set<Class<?>>> readClassSet(String resourceName, ClassLoader classLoader) {
		return readClasses(
			resourceName, classLoader,
			ClasspathScanUtil::readClassSet
		);
	}

	public static void writeClassSet(GenerationContext generationContext, String resourceFilePath, Collection<Class<?>> classes) {

		generationContext.getRuntimeHints().resources().registerPattern(resourceFilePath);

		List<String> sortedClassNames = classes.stream()
			.map(Class::getName)
			.sorted(String::compareTo)
			.toList();

		generationContext.getGeneratedFiles().addResourceFile(resourceFilePath, appendable -> {
			for (String className : sortedClassNames) {
				appendable.append(className);
				appendable.append("\n");
			}
		});

		for (String className : sortedClassNames) {
			generationContext.getRuntimeHints().reflection().registerType(TypeReference.of(className));
		}
	}

	public static Optional<Map<Class<? extends Annotation>, Set<Class<?>>>> readClassMap(String resourceName, ClassLoader classLoader) {
		return readClasses(
			resourceName, classLoader,
			ClasspathScanUtil::readClassMap
		);
	}

	public static void writeClassMap(GenerationContext generationContext, String resourceFilePath, Map<Class<? extends Annotation>, Set<Class<?>>> classMap) {

		generationContext.getRuntimeHints().resources().registerPattern(resourceFilePath);

		List<String> sortedClassNames = classMap.values().stream()
			.flatMap(Collection::stream)
			.map(Class::getName)
			.sorted(String::compareTo)
			.distinct()
			.toList();

		generationContext.getGeneratedFiles().addResourceFile(resourceFilePath, appendable -> {

			for (Map.Entry<Class<? extends Annotation>, Set<Class<?>>> classSetEntry : classMap.entrySet()) {
				appendable.append(classSetEntry.getKey().getName());
				appendable.append("=");

				appendable.append(classSetEntry.getValue().stream().map(Class::getName).sorted().collect(Collectors.joining(",")));
				appendable.append("\n");
			}

		});

		for (String className : sortedClassNames) {
			generationContext.getRuntimeHints().reflection().registerType(TypeReference.of(className));
		}
	}

	private static <T> Optional<T> readClasses(String resourceName, ClassLoader classLoader, BiFunction<BufferedReader, ClassLoader, T> function) {
		InputStream resourceAsStream = classLoader.getResourceAsStream(resourceName);

		if (resourceAsStream == null) {
			log.debug("No prepared scan result {} found.", resourceName);
			return Optional.empty();
		}

		long start = System.nanoTime();
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8))) {
			T result = function.apply(bufferedReader, classLoader);
			double ms = (System.nanoTime() - start) / 1_000_000d;
			log.info("Loading prepared scan result took {}ms", ms);
			return Optional.ofNullable(result);
		}
		catch (IOException e) {
			log.warn("Failed to read prepared scan-result {}", resourceName, e);
			return Optional.empty();
		}
	}

	static Set<Class<?>> readClassSet(BufferedReader bufferedReader, ClassLoader classLoader) {
		return getClasses(bufferedReader.lines(), classLoader);
	}

	static Map<Class<? extends Annotation>, Set<Class<?>>> readClassMap(BufferedReader bufferedReader, ClassLoader classLoader) {
		Map<Class<? extends Annotation>, Set<Class<?>>> classes = new HashMap<>();

		bufferedReader.lines().forEach(line -> {
			String[] split = line.split("=", 2);
			String annotationName = split[0];
			String classNameList = split[1];

			Class<? extends Annotation> annotation;
			try {
				annotation = (Class<? extends Annotation>) classLoader.loadClass(annotationName);
			}
			catch (ClassNotFoundException | LinkageError e) {
				log.warn("Failed to load annotation class {}", annotationName, e);
				return;
			}
			Set<Class<?>> classSet;

			if (StringUtils.hasText(classNameList)) {
				classSet = getClasses(Arrays.stream(classNameList.split(",")), classLoader);
			}
			else {
				classSet = Collections.emptySet();
			}

			classes.put(annotation, classSet);
		});
		return classes;

	}

	static Set<Class<?>> getClasses(Stream<String> classNames, ClassLoader classLoader) {
		AtomicInteger missingClasses = new AtomicInteger();
		AtomicInteger missingDependentClasses = new AtomicInteger();

		Set<Class<?>> collect = classNames
			.map(className -> {
				try {
					return classLoader.loadClass(className);
				}
				catch (ClassNotFoundException e) {
					missingClasses.incrementAndGet();
					log.debug("Failed to load class {} although it's listed in the prepared scan result.", className);
					log.trace("Stacktrace", e);
				}
				catch (NoClassDefFoundError e) {
					missingDependentClasses.incrementAndGet();
					log.debug("Failed to load class {} because it's dependency {} is missing.", className, e.getMessage());
					log.trace("Stacktrace", e);
				}
				catch (LinkageError e) {
					log.warn("Failed to load class {} from prepared scan result", className, e);
				}
				return null;
			})
			.filter(Objects::nonNull)
			.collect(Collectors.toSet());

		if (missingClasses.get() > 0) {
			log.warn("{} classes listed in the prepared scan result could not be found. Set the log-level to debug for more information.", missingClasses.get());
		}
		if (missingDependentClasses.get() > 0) {
			log.info("{} classes failed to load, because some of their dependencies are missing. Set the log-level to debug for more information.", missingDependentClasses.get());
		}
		return collect;
	}
}
