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

package org.joinfaces.autoconfigure.javaxfaces;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.faces.annotation.ApplicationMap;
import javax.faces.annotation.FlowMap;
import javax.faces.annotation.HeaderMap;
import javax.faces.annotation.HeaderValuesMap;
import javax.faces.annotation.InitParameterMap;
import javax.faces.annotation.RequestCookieMap;
import javax.faces.annotation.RequestMap;
import javax.faces.annotation.RequestParameterMap;
import javax.faces.annotation.RequestParameterValuesMap;
import javax.faces.annotation.SessionMap;
import javax.faces.annotation.ViewMap;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

/**
 * @author Lars Grefer
 */
public class JsfBeansAnnotationPostProcessor implements BeanPostProcessor {

	private final JsfBeansAutoConfiguration jsfBeansAutoConfiguration;

	private final Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>();


	public JsfBeansAnnotationPostProcessor(JsfBeansAutoConfiguration jsfBeansAutoConfiguration) {
		this.jsfBeansAutoConfiguration = jsfBeansAutoConfiguration;

		this.autowiredAnnotationTypes.add(Autowired.class);
		this.autowiredAnnotationTypes.add(Value.class);
		try {
			this.autowiredAnnotationTypes.add((Class<? extends Annotation>)
					ClassUtils.forName("javax.inject.Inject", AutowiredAnnotationBeanPostProcessor.class.getClassLoader()));
		}
		catch (ClassNotFoundException ex) {
			// JSR-330 API not available - simply skip.
		}
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		ReflectionUtils.doWithFields(bean.getClass(),
				field -> checkField(field, bean),
				field -> {
					for (Class<? extends Annotation> autowiredAnnotationType : this.autowiredAnnotationTypes) {
						if (AnnotatedElementUtils.hasAnnotation(field, autowiredAnnotationType)) {
							return true;
						}
					}
					return false;
				});
		return bean;
	}

	private void checkField(Field field, Object bean) {
		if (AnnotatedElementUtils.hasAnnotation(field, ApplicationMap.class)) {
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, bean, this.jsfBeansAutoConfiguration.applicationMap());
		}

		if (AnnotatedElementUtils.hasAnnotation(field, FlowMap.class)) {
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, bean, this.jsfBeansAutoConfiguration.flowScope());
		}

		if (AnnotatedElementUtils.hasAnnotation(field, HeaderMap.class)) {
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, bean, this.jsfBeansAutoConfiguration.headerMap());
		}

		if (AnnotatedElementUtils.hasAnnotation(field, HeaderValuesMap.class)) {
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, bean, this.jsfBeansAutoConfiguration.headerValuesMap());
		}

		if (AnnotatedElementUtils.hasAnnotation(field, InitParameterMap.class)) {
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, bean, this.jsfBeansAutoConfiguration.initParameterMap());
		}

		if (AnnotatedElementUtils.hasAnnotation(field, RequestCookieMap.class)) {
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, bean, this.jsfBeansAutoConfiguration.requestCookieMap());
		}

		if (AnnotatedElementUtils.hasAnnotation(field, RequestMap.class)) {
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, bean, this.jsfBeansAutoConfiguration.requestMap());
		}

		if (AnnotatedElementUtils.hasAnnotation(field, RequestParameterMap.class)) {
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, bean, this.jsfBeansAutoConfiguration.requestParameterMap());
		}

		if (AnnotatedElementUtils.hasAnnotation(field, RequestParameterValuesMap.class)) {
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, bean, this.jsfBeansAutoConfiguration.requestParameterValuesMap());
		}

		if (AnnotatedElementUtils.hasAnnotation(field, SessionMap.class)) {
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, bean, this.jsfBeansAutoConfiguration.sessionMap());
		}

		if (AnnotatedElementUtils.hasAnnotation(field, ViewMap.class)) {
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, bean, this.jsfBeansAutoConfiguration.viewMap());
		}
	}
}
