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
import java.util.Iterator;

import javax.servlet.ServletContext;

import lombok.extern.slf4j.Slf4j;
import org.joinfaces.ServletContextConfigurer;

import org.springframework.aop.support.AopUtils;

/**
 * A {@link ServletContextConfigurer} which looks for all {@link InitParameter init parameters}
 * in an object by reflection.
 *
 * @param <PC> Type of the properties object
 * @author Lars Grefer
 * @see InitParameter
 * @see NestedProperty
 */
@Slf4j
public class ReflectiveServletContextConfigurer<PC> extends ServletContextConfigurer {

	private PC properties;

	public ReflectiveServletContextConfigurer(ServletContext servletContext, PC properties) {
		super(servletContext, null);
		this.properties = properties;
	}

	@Override
	public void configure() {
		handlePropertiesObject(this.properties);
	}

	private void handlePropertiesObject(Object properties) {

		Class<?> type = AopUtils.getTargetClass(properties);

		while (!type.equals(Object.class)) {

			for (Field field : type.getDeclaredFields()) {
				handlePropertiesField(properties, field);
			}

			type = type.getSuperclass();
		}

	}

	private void handlePropertiesField(Object properties, Field field) {
		if (field.isAnnotationPresent(NestedProperty.class)) {
			try {
				field.setAccessible(true);
				Object nestedProperties = field.get(properties);
				if (nestedProperties != null) {
					handlePropertiesObject(nestedProperties);
				}
				else {
					log.debug("Not visiting nested property {} because its null", field);
				}
			}
			catch (Exception e) {
				log.error("Cannot visit nested property {}", field, e);
			}
			return;
		}

		InitParameter initParameter = field.getAnnotation(InitParameter.class);

		if (initParameter == null) {
			log.info("Field {} not annotated with @InitParameter or @NestedProperty", field);
			return;
		}

		Object value;
		try {
			field.setAccessible(true);
			value = field.get(properties);
		}
		catch (Exception e) {
			log.error("Cannot read field {}", field, e);
			return;
		}

		if (value == null) {
			log.debug("Not setting '{}' because the value is null", initParameter.value());
			return;
		}

		String paramValue = convertToString(field, initParameter, value);

		setInitParameterRaw(initParameter.value(), paramValue);
	}

	private String convertToString(Field field, InitParameter annotation, Object value) {
		Class<?> targetType = field.getType();

		if (Collection.class.isAssignableFrom(field.getType())) {
			Type actualType = ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
			if (actualType instanceof Class) {
				targetType = (Class<?>) actualType;
			}
			else if (actualType instanceof ParameterizedType) {
				targetType = (Class<?>) ((ParameterizedType) actualType).getRawType();
			}

			if (((Collection) value).isEmpty()) {
				return "";
			}
			else {
				Iterator iterator = ((Collection) value).iterator();
				String firstValue = convertToString(targetType, iterator.next());
				StringBuilder sb = new StringBuilder(firstValue);
				while (iterator.hasNext()) {
					String nextValue = convertToString(targetType, iterator.next());
					sb.append(annotation.listSeparator()).append(nextValue);
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
}
