/*
 * Copyright 2016-2017 the original author or authors.
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

package org.joinfaces.configuration;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletContext;

import lombok.extern.slf4j.Slf4j;

import org.springframework.aop.support.AopUtils;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.ReflectionUtils;

/**
 * A ServletContextConfigurer which looks for all {@link InitParameter init parameters}
 * in an object by reflection.
 *
 * @param <PC> Type of the properties object
 * @author Lars Grefer
 * @see InitParameter
 * @see NestedProperty
 */
@Slf4j
public class ReflectiveServletContextConfigurer<PC> {

	private final PC properties;
	private final Set<String> visitedProperties;
	private final ServletContext servletContext;

	public ReflectiveServletContextConfigurer(ServletContext servletContext, PC properties) {
		this.servletContext = servletContext;
		this.properties = properties;
		this.visitedProperties = new HashSet<String>();
	}

	public void configure() {
		handlePropertiesObject(this.properties);
	}

	private void handlePropertiesObject(final Object properties) {

		Class<?> type = AopUtils.getTargetClass(properties);

		ReflectionUtils.doWithFields(
				type,
				new ReflectionUtils.FieldCallback() {
					@Override
					public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
						handlePropertiesField(properties, field);
					}
				},
				new ReflectionUtils.FieldFilter() {
					@Override
					public boolean matches(Field field) {
						return AnnotatedElementUtils.isAnnotated(field, InitParameter.class) || AnnotatedElementUtils.isAnnotated(field, NestedProperty.class);
					}
				});

	}

	private void handlePropertiesField(Object properties, Field field) {
		if (field.isAnnotationPresent(NestedProperty.class)) {

			ReflectionUtils.makeAccessible(field);
			Object nestedProperties = ReflectionUtils.getField(field, properties);

			if (nestedProperties != null) {
				handlePropertiesObject(nestedProperties);
			}
			else {
				log.debug("Not visiting nested property {} because its null", field);
			}
		}
		else if (field.isAnnotationPresent(InitParameter.class)) {
			InitParameter initParameter = field.getAnnotation(InitParameter.class);

			ReflectionUtils.makeAccessible(field);
			Object value = ReflectionUtils.getField(field, properties);

			String paramName = initParameter.value();
			if (this.visitedProperties.contains(paramName)) {
				log.debug("Not setting '{}' because it was already processed", paramName);
				return;
			}
			if (this.servletContext.getInitParameter(paramName) != null) {
				log.info("{} already set in the ServletContext", paramName);
				return;
			}

			if (value == null) {
				log.debug("Not setting '{}' because the value is null", paramName);
			}
			else {
				String paramValue = convertToString(field, value);

				log.debug("{} = {}", paramName, paramValue);
				this.servletContext.setInitParameter(paramName, paramValue);
			}
			this.visitedProperties.add(paramName);
		}
	}

	private String convertToString(Field field, Object value) {
		Class<?> targetType = field.getType();

		if (Collection.class.isAssignableFrom(field.getType())) {
			targetType = resolveCollectionItemType(field);

			InitParameter initParameter = field.getAnnotation(InitParameter.class);

			if (((Collection) value).isEmpty()) {
				return "";
			}
			else {
				Iterator iterator = ((Collection) value).iterator();
				String firstValue = convertToString(targetType, iterator.next());

				StringBuilder sb = new StringBuilder(firstValue);
				while (iterator.hasNext()) {
					String nextValue = convertToString(targetType, iterator.next());
					sb.append(initParameter.listSeparator()).append(nextValue);
				}
				return sb.toString();
			}
		}
		else {
			return convertToString(targetType, value);
		}
	}

	private String convertToString(Class<?> targetType, Object value) {

		if (String.class.isAssignableFrom(targetType)) {
			return (String) value;
		}

		if (targetType.isEnum()) {
			return ((Enum) value).name();
		}

		if (Class.class.isAssignableFrom(targetType)) {
			return ((Class) value).getName();
		}

		return value.toString();
	}

	static Class<?> resolveCollectionItemType(Field field) {

		Type genericFieldType = field.getGenericType();
		if (genericFieldType instanceof Class) {
			log.warn("Field {} uses a raw collection type. Assuming Object as item type", field);
			return Object.class;
		}

		Type actualType = ((ParameterizedType) genericFieldType).getActualTypeArguments()[0];
		if (actualType instanceof Class) {
			return (Class<?>) actualType;
		}

		return (Class<?>) ((ParameterizedType) actualType).getRawType();
	}
}
