/*
 * Copyright 2016-2019 the original author or authors.
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

package org.joinfaces.tools;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import io.github.classgraph.AnnotationClassRef;
import io.github.classgraph.AnnotationInfo;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link ScanResultHandler} for the {@link javax.servlet.annotation.HandlesTypes} annotation
 * of {@link javax.servlet.ServletContainerInitializer}s.
 *
 * @author Lars Grefer
 */
@Slf4j
public class ServletContainerInitializerHandler extends ScanResultHandler {

	private static final String SERVLET_CONTAINER_INITIALIZER = "javax.servlet.ServletContainerInitializer";
	private static final String HANDLES_TYPES = "javax.servlet.annotation.HandlesTypes";

	@Override
	public void handle(ScanResult scanResult, File classpathRoot) throws IOException {
		ClassInfoList scis = scanResult.getClassesImplementing(SERVLET_CONTAINER_INITIALIZER)
				.filter(classInfo -> classInfo.hasAnnotation(HANDLES_TYPES));

		for (ClassInfo sciClassInfo : scis) {
			List<ClassInfo> handledTypes = resolveHandledTypes(sciClassInfo);

			SortedSet<String> classes = findHandledClasses(scanResult, handledTypes);

			File resultFile = new File(classpathRoot, "META-INF/joinfaces/" + sciClassInfo.getName() + ".classes");

			writeClassList(resultFile, classes);
		}
	}

	/**
	 * Resolves the {@link Class}[] of the {@code HandlesTypes}-annotations present on the given
	 * {@code ServletContainerInitializer} implementation.
	 *
	 * @param servletContainerInitializerClassInfo The {@link ClassInfo} object representing the {@code ServletContainerInitializer} implementation
	 * @return The classes listed in the {@code HandlesTypes} annotation of the given class.
	 */
	private List<ClassInfo> resolveHandledTypes(ClassInfo servletContainerInitializerClassInfo) {
		AnnotationInfo handlesTypes = servletContainerInitializerClassInfo.getAnnotationInfo(HANDLES_TYPES);

		Object[] value = (Object[]) handlesTypes.getParameterValues().get("value").getValue();

		return Arrays.stream(value)
				.map(AnnotationClassRef.class::cast)
				.map(annotationClassRef -> {
					ClassInfo classInfo = annotationClassRef.getClassInfo();
					if (classInfo == null) {
						log.warn("{} not found in the scan result, but declared in the @HandlesTypes annotation of {}", annotationClassRef.getName(), servletContainerInitializerClassInfo.getName());
					}
					return classInfo;
				})
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
	}

	/**
	 * Find the classes which match the given {@code HandlesTypes} spec.
	 *
	 * @param scanResult   The {@link ScanResult} in which the classes are searched.
	 * @param handledTypes The filter spec as found in the {@code HandlesTypes} annotation.
	 * @return The {@link SortedSet set} of classes which implement, extend or are annotated with the given classes.
	 */
	private SortedSet<String> findHandledClasses(ScanResult scanResult, List<ClassInfo> handledTypes) {

		SortedSet<String> classes = new TreeSet<>(String::compareTo);

		handledTypes.forEach(handledType -> {
			String name = handledType.getName();

			if (handledType.isAnnotation()) {
				classes.addAll(scanResult.getClassesWithAnnotation(name).getNames());
				classes.addAll(scanResult.getClassesWithMethodAnnotation(name).getNames());
				classes.addAll(scanResult.getClassesWithFieldAnnotation(name).getNames());
			}
			else if (handledType.isInterface()) {
				classes.addAll(scanResult.getClassesImplementing(name).getNames());
			}
			else {
				classes.addAll(scanResult.getSubclasses(name).getNames());
			}

		});

		return classes;
	}
}
