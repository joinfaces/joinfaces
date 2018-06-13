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
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

/**
 * This {@link BeanPostProcessor} injects JSF artifacts which use a custom qualifier annotation.
 *
 * @author Lars Grefer
 * @see javax.faces.annotation
 */
public class JsfBeansAnnotationPostProcessor implements BeanPostProcessor {

	private final Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>();
	private final Map<Class<? extends Annotation>, Supplier<?>> suppliers = new HashMap<>();

	public JsfBeansAnnotationPostProcessor(JsfBeansAutoConfiguration jsfBeansAutoConfiguration) {

		this.autowiredAnnotationTypes.add(Autowired.class);
		this.autowiredAnnotationTypes.add(Value.class);
		try {
			this.autowiredAnnotationTypes.add((Class<? extends Annotation>)
					ClassUtils.forName("javax.inject.Inject", JsfBeansAnnotationPostProcessor.class.getClassLoader()));
		}
		catch (ClassNotFoundException ex) {
			// JSR-330 API not available - simply skip.
		}

		this.suppliers.put(ApplicationMap.class, jsfBeansAutoConfiguration::applicationMap);
		this.suppliers.put(FlowMap.class, jsfBeansAutoConfiguration::flowScope);
		this.suppliers.put(HeaderMap.class, jsfBeansAutoConfiguration::headerMap);
		this.suppliers.put(HeaderValuesMap.class, jsfBeansAutoConfiguration::headerValuesMap);
		this.suppliers.put(InitParameterMap.class, jsfBeansAutoConfiguration::initParameterMap);
		this.suppliers.put(RequestCookieMap.class, jsfBeansAutoConfiguration::requestCookieMap);
		this.suppliers.put(RequestMap.class, jsfBeansAutoConfiguration::requestMap);
		this.suppliers.put(RequestParameterMap.class, jsfBeansAutoConfiguration::requestParameterMap);
		this.suppliers.put(RequestParameterValuesMap.class, jsfBeansAutoConfiguration::requestParameterValuesMap);
		this.suppliers.put(SessionMap.class, jsfBeansAutoConfiguration::sessionMap);
		this.suppliers.put(ViewMap.class, jsfBeansAutoConfiguration::viewMap);

	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		ReflectionUtils.doWithFields(bean.getClass(),
				field -> this.suppliers.forEach((annotation, supplier) -> {
					if (AnnotatedElementUtils.hasAnnotation(field, annotation)) {
						ReflectionUtils.makeAccessible(field);
						ReflectionUtils.setField(field, bean, supplier.get());
					}
				}),
				this::isAutowiredField);
		return bean;
	}

	boolean isAutowiredField(Field field) {
		for (Class<? extends Annotation> autowiredAnnotationType : this.autowiredAnnotationTypes) {
			if (AnnotatedElementUtils.hasAnnotation(field, autowiredAnnotationType)) {
				return true;
			}
		}
		return false;
	}

}
