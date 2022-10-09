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

package org.joinfaces.autoconfigure.myfaces;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.annotation.ManagedBean;
import jakarta.faces.component.FacesComponent;
import jakarta.faces.component.behavior.FacesBehavior;
import jakarta.faces.convert.FacesConverter;
import jakarta.faces.event.NamedEvent;
import jakarta.faces.render.FacesBehaviorRenderer;
import jakarta.faces.render.FacesRenderer;
import jakarta.faces.validator.FacesValidator;
import jakarta.servlet.ServletContainerInitializer;

import io.github.classgraph.ScanResult;
import org.joinfaces.servlet.ServletContainerInitializerRegistrationBean;

/**
 * Servlet context initializer of MyFaces.
 *
 * @author Marcelo Fernandes
 * @author Lars Grefer
 * @see org.apache.myfaces.spi.AnnotationProvider
 * @see org.apache.myfaces.config.annotation.AnnotationConfigurator
 * @see JoinFacesAnnotationProvider
 */
public class MyFacesInitializerRegistrationBean<T extends ServletContainerInitializer> extends ServletContainerInitializerRegistrationBean<T> {

	public MyFacesInitializerRegistrationBean(Class<T> servletContainerInitializerClass) {
		super(servletContainerInitializerClass);
	}

	@Override
	protected void handleScanResult(ScanResult scanResult) {
		super.handleScanResult(scanResult);

		Map<Class<? extends Annotation>, Set<Class<?>>> annotatedClasses = new HashMap<>();

		Arrays.asList(
				ManagedBean.class,
				FacesComponent.class,
				FacesBehavior.class,
				FacesConverter.class,
				NamedEvent.class,
				FacesRenderer.class,
				FacesBehaviorRenderer.class,
				FacesValidator.class
		).forEach(annotationClass -> {
			List<Class<?>> classes = scanResult.getClassesWithAnnotation(annotationClass.getName()).loadClasses();
			annotatedClasses.put(annotationClass, new HashSet<>(classes));
		});

		JoinFacesAnnotationProvider.setAnnotatedClasses(annotatedClasses);
	}
}
