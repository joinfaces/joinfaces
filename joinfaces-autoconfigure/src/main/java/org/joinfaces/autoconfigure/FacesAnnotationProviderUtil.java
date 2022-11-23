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

package org.joinfaces.autoconfigure;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import jakarta.annotation.ManagedBean;
import jakarta.faces.component.FacesComponent;
import jakarta.faces.component.behavior.FacesBehavior;
import jakarta.faces.convert.FacesConverter;
import jakarta.faces.event.NamedEvent;
import jakarta.faces.render.FacesBehaviorRenderer;
import jakarta.faces.render.FacesRenderer;
import jakarta.faces.validator.FacesValidator;

import io.github.classgraph.ScanResult;
import lombok.experimental.UtilityClass;
import org.joinfaces.ClasspathScanUtil;

/**
 * Helper methods for the implementations of the AnnotationProvider SPI's of Mojarra and MyFaces.
 *
 * @author Lars Grefer
 * @see org.joinfaces.autoconfigure.mojarra.JoinFacesAnnotationProvider
 * @see org.joinfaces.autoconfigure.myfaces.JoinFacesAnnotationProvider
 */
@UtilityClass
public class FacesAnnotationProviderUtil {

	public static final List<Class<? extends Annotation>> CLASSES = Arrays.asList(
			ManagedBean.class,
			FacesComponent.class,
			FacesBehavior.class,
			FacesConverter.class,
			NamedEvent.class,
			FacesRenderer.class,
			FacesBehaviorRenderer.class,
			FacesValidator.class
	);

	public static Map<Class<? extends Annotation>, Set<Class<?>>> findAnnotatedClasses(ScanResult scanResult) {

		Map<Class<? extends Annotation>, Set<Class<?>>> annotatedClasses = new HashMap<>();

		for (Class<? extends Annotation> annotationClass : CLASSES) {
			Set<Class<?>> classes = new LinkedHashSet<>();

			classes.addAll(scanResult.getClassesWithAnnotation(annotationClass).loadClasses());
			classes.addAll(scanResult.getClassesWithMethodAnnotation(annotationClass).loadClasses());
			classes.addAll(scanResult.getClassesWithFieldAnnotation(annotationClass).loadClasses());

			annotatedClasses.put(annotationClass, classes);
		}

		return annotatedClasses;
	}

	public static Optional<Map<Class<? extends Annotation>, Set<Class<?>>>> findPreparedScanResult(Class<?> spiClass, ClassLoader classLoader) {
		String resourceName = "META-INF/joinfaces/" + spiClass.getName() + ".classes";
		return ClasspathScanUtil.readClassMap(resourceName, classLoader);
	}


}
