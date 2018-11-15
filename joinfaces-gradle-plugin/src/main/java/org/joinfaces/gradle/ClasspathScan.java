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

package org.joinfaces.gradle;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import io.github.classgraph.AnnotationClassRef;
import io.github.classgraph.AnnotationInfo;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.groovy.runtime.ResourceGroovyMethods;
import org.gradle.api.DefaultTask;
import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.tasks.Classpath;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.Internal;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.TaskAction;

/**
 * @author Lars Grefer
 */
@Getter
@Setter
public class ClasspathScan extends DefaultTask {

	public static final String SERVLET_CONTAINER_INITIALIZER = "javax.servlet.ServletContainerInitializer";
	public static final String HANDLES_TYPES = "javax.servlet.annotation.HandlesTypes";
	public static final String MYFACES_ANNOTATION_PROVIDER = "org.apache.myfaces.spi.AnnotationProvider";
	public static final List<String> MYFACES_ANNOTATIONS = Collections.unmodifiableList(Arrays.asList(
			"javax.faces.bean.ManagedBean",
			"javax.faces.component.FacesComponent",
			"javax.faces.component.behavior.FacesBehavior",
			"javax.faces.convert.FacesConverter",
			"javax.faces.event.NamedEvent",
			"javax.faces.render.FacesRenderer",
			"javax.faces.render.FacesBehaviorRenderer",
			"javax.faces.validator.FacesValidator"
	));

	@Classpath
	@InputFiles
	private final ConfigurableFileCollection classpath = getProject().files();

	@OutputDirectory
	private final DirectoryProperty destinationDir = newOutputDirectory();

	@Internal
	private File getBaseDir() {
		return destinationDir.dir("META-INF/joinfaces").get().getAsFile();
	}

	@TaskAction
	public void scanClasses() throws IOException {

		ClassGraph classGraph = new ClassGraph()
				.enableAllInfo()
				.enableSystemPackages()
				.overrideClasspath(classpath);

		try (ScanResult scanResult = classGraph.scan()) {
			processHandlesTypes(scanResult);

			processMyfacesAnnotationProvider(scanResult);
		}
	}

	private void processMyfacesAnnotationProvider(ScanResult scanResult) throws IOException {
		if (scanResult.getClassInfo(MYFACES_ANNOTATION_PROVIDER) == null) {
			return;
		}

		File resultFile = new File(getBaseDir(), MYFACES_ANNOTATION_PROVIDER + ".classes");

		try (PrintWriter printWriter = ResourceGroovyMethods.newPrintWriter(resultFile, "UTF-8")) {
			for (String myfacesAnnotation : MYFACES_ANNOTATIONS) {

				printWriter.print(myfacesAnnotation);
				printWriter.print("=");

				ClassInfoList classesWithAnnotation = scanResult.getClassesWithAnnotation(myfacesAnnotation);
				printWriter.println(String.join(",", classesWithAnnotation.getNames()));
			}
		}
	}

	private void processHandlesTypes(ScanResult scanResult) throws IOException {
		ClassInfoList scis = scanResult.getClassesImplementing(SERVLET_CONTAINER_INITIALIZER)
				.filter(classInfo -> classInfo.hasAnnotation(HANDLES_TYPES));

		for (ClassInfo sciClassInfo : scis) {
			List<ClassInfo> handledTypes = resolveHandledTypes(sciClassInfo);

			SortedSet<String> classes = findHandledClasses(scanResult, handledTypes);

			File resultFile = new File(getBaseDir(), sciClassInfo.getName() + ".classes");

			Files.write(resultFile.toPath(), classes, StandardCharsets.UTF_8);
		}
	}

	/**
	 * Resolves the {@link Class}[] of the {@code HandlesTypes}-annotations present on the given
	 * {@code ServletContainerInitializer} implementation
	 *
	 * @param servletContainerInitializerClassInfo The {@link ClassInfo} object representing the {@code ServletContainerInitializer} implementation
	 */
	private List<ClassInfo> resolveHandledTypes(ClassInfo servletContainerInitializerClassInfo) {
		AnnotationInfo handlesTypes = servletContainerInitializerClassInfo.getAnnotationInfo(HANDLES_TYPES);

		Object[] value = (Object[]) handlesTypes.getParameterValues().get("value");

		return Arrays.stream(value)
				.map(AnnotationClassRef.class::cast)
				.map(AnnotationClassRef::getClassInfo)
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
