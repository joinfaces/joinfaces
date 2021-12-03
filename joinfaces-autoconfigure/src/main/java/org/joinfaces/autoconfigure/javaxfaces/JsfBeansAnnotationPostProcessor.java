/*
 * Copyright 2016-2018 the original author or authors.
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

package org.joinfaces.autoconfigure.javaxfaces;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import jakarta.faces.annotation.ApplicationMap;
import jakarta.faces.annotation.FlowMap;
import jakarta.faces.annotation.HeaderMap;
import jakarta.faces.annotation.HeaderValuesMap;
import jakarta.faces.annotation.InitParameterMap;
import jakarta.faces.annotation.RequestCookieMap;
import jakarta.faces.annotation.RequestMap;
import jakarta.faces.annotation.RequestParameterMap;
import jakarta.faces.annotation.RequestParameterValuesMap;
import jakarta.faces.annotation.SessionMap;
import jakarta.faces.annotation.ViewMap;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
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
	private final Map<Class<? extends Annotation>, Function<JsfBeansAutoConfiguration, ?>> mappers = new HashMap<>();

	private final BeanFactory beanFactory;

	public JsfBeansAnnotationPostProcessor(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;

		this.autowiredAnnotationTypes.add(Autowired.class);
		this.autowiredAnnotationTypes.add(Value.class);
		try {
			this.autowiredAnnotationTypes.add((Class<? extends Annotation>)
					ClassUtils.forName("jakarta.inject.Inject", JsfBeansAnnotationPostProcessor.class.getClassLoader()));
		}
		catch (ClassNotFoundException ex) {
			// JSR-330 API not available - simply skip.
		}

		this.mappers.put(ApplicationMap.class, JsfBeansAutoConfiguration::applicationMap);
		this.mappers.put(FlowMap.class, JsfBeansAutoConfiguration::flowScope);
		this.mappers.put(HeaderMap.class, JsfBeansAutoConfiguration::headerMap);
		this.mappers.put(HeaderValuesMap.class, JsfBeansAutoConfiguration::headerValuesMap);
		this.mappers.put(InitParameterMap.class, JsfBeansAutoConfiguration::initParameterMap);
		this.mappers.put(RequestCookieMap.class, JsfBeansAutoConfiguration::requestCookieMap);
		this.mappers.put(RequestMap.class, JsfBeansAutoConfiguration::requestMap);
		this.mappers.put(RequestParameterMap.class, JsfBeansAutoConfiguration::requestParameterMap);
		this.mappers.put(RequestParameterValuesMap.class, JsfBeansAutoConfiguration::requestParameterValuesMap);
		this.mappers.put(SessionMap.class, JsfBeansAutoConfiguration::sessionMap);
		this.mappers.put(ViewMap.class, JsfBeansAutoConfiguration::viewMap);

	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		ReflectionUtils.doWithFields(bean.getClass(),
				field -> this.mappers.forEach((annotation, mapper) -> {
					if (AnnotatedElementUtils.hasAnnotation(field, annotation)) {
						ReflectionUtils.makeAccessible(field);
						JsfBeansAutoConfiguration jsfBeanConfiguration = this.beanFactory.getBean(JsfBeansAutoConfiguration.class);
						ReflectionUtils.setField(field, bean, mapper.apply(jsfBeanConfiguration));
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
