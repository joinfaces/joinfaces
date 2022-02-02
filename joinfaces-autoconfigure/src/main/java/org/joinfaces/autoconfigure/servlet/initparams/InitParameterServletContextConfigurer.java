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

package org.joinfaces.autoconfigure.servlet.initparams;

import java.lang.reflect.Field;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.servlet.ServletContext;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.aop.support.AopUtils;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

/**
 * A ServletContextInitializer which looks for all {@link ServletContextInitParameter init parameters}
 * in an {@link ServletContextInitParameterProperties} object by reflection.
 *
 * @author Lars Grefer
 * @see ServletContextInitParameter
 */
@Slf4j
public class InitParameterServletContextConfigurer implements ServletContextInitializer, Ordered {

	private final List<ServletContextInitParameterProperties> initParameterProperties;
	private final Set<String> visitedInitParameters;
	private ServletContext servletContext;

	@Getter
	@Setter
	private int order;

	public InitParameterServletContextConfigurer(List<ServletContextInitParameterProperties> initParameterProperties) {
		this.initParameterProperties = new ArrayList<>(initParameterProperties);
		this.visitedInitParameters = new HashSet<>();
	}

	@Override
	@SuppressFBWarnings("EI_EXPOSE_REP2")
	public void onStartup(ServletContext servletContext) {
		this.servletContext = servletContext;
		for (ServletContextInitParameterProperties properties : this.initParameterProperties) {
			handlePropertiesObject(properties);
		}
	}

	private void handlePropertiesObject(final Object properties) {

		Class<?> type = AopUtils.getTargetClass(properties);

		ReflectionUtils.doWithFields(
				type,
				field -> handlePropertiesField(properties, field),
				field -> AnnotatedElementUtils.isAnnotated(field, ServletContextInitParameter.class) || AnnotatedElementUtils.isAnnotated(field, NestedConfigurationProperty.class));

	}

	private void handlePropertiesField(Object properties, Field field) {
		if (AnnotatedElementUtils.isAnnotated(field, NestedConfigurationProperty.class)) {

			ReflectionUtils.makeAccessible(field);
			Object nestedProperties = ReflectionUtils.getField(field, properties);

			if (nestedProperties != null) {
				handlePropertiesObject(nestedProperties);
			}
			else {
				log.debug("Not visiting nested property {} because its null", field);
			}
		}

		ServletContextInitParameter servletContextInitParameter = AnnotatedElementUtils.getMergedAnnotation(field, ServletContextInitParameter.class);
		if (servletContextInitParameter != null) {

			String paramName = servletContextInitParameter.value();

			if (this.visitedInitParameters.contains(paramName)) {
				log.debug("Not setting '{}' because it was already processed", paramName);
				return;
			}

			ReflectionUtils.makeAccessible(field);
			Object value = ReflectionUtils.getField(field, properties);

			if (value == null) {
				log.debug("Not setting '{}' because the value is null", paramName);
			}
			else {
				String paramValue = convertToString(field, value, servletContextInitParameter);

				log.debug("{} = {}", paramName, paramValue);
				this.servletContext.setInitParameter(paramName, paramValue);
			}
			this.visitedInitParameters.add(paramName);
		}
	}

	private String convertToString(Field field, Object value, ServletContextInitParameter servletContextInitParameter) {
		if (Collection.class.isAssignableFrom(field.getType())) {
			Collection<?> collection = (Collection<?>) value;

			if (collection.isEmpty()) {
				return "";
			}
			else {
				return collection.stream()
						.map(InitParameterServletContextConfigurer::convertToString)
						.collect(Collectors.joining(servletContextInitParameter.listSeparator()));
			}
		}
		else if (Duration.class.isAssignableFrom(field.getType())) {
			ChronoUnit chronoUnit = resolveChronoUnit(field);
			return convertToString((Duration) value, chronoUnit);
		}
		else if (DataSize.class.isAssignableFrom(field.getType())) {
			DataUnit dataUnit = resolveDataSizeUnit(field);
			return convertToString((DataSize) value, dataUnit);
		}
		else {
			return convertToString(value);
		}
	}

	static String convertToString(Duration duration, ChronoUnit chronoUnit) {
		return switch (chronoUnit) {
			case NANOS -> String.valueOf(duration.toNanos());
			case MILLIS -> String.valueOf(duration.toMillis());
			case SECONDS -> String.valueOf(duration.getSeconds());
			case MINUTES -> String.valueOf(duration.toMinutes());
			case HOURS -> String.valueOf(duration.toHours());
			default -> throw new IllegalStateException("Unsupported ChronoUnit: " + chronoUnit);
		};
	}

	static String convertToString(DataSize dataSize, DataUnit dataUnit) {
		return switch (dataUnit) {
			case BYTES -> String.valueOf(dataSize.toBytes());
			case KILOBYTES -> String.valueOf(dataSize.toKilobytes());
			case MEGABYTES -> String.valueOf(dataSize.toMegabytes());
			case GIGABYTES -> String.valueOf(dataSize.toGigabytes());
			case TERABYTES -> String.valueOf(dataSize.toTerabytes());
			default -> throw new IllegalStateException("Unsupported DataUnit: " + dataUnit);
		};
	}

	static String convertToString(Object value) {

		if (value instanceof String) {
			return (String) value;
		}

		if (value instanceof Enum) {
			return ((Enum<?>) value).name();
		}

		if (value instanceof Class) {
			return ((Class<?>) value).getName();
		}

		return value.toString();
	}

	static ChronoUnit resolveChronoUnit(Field field) {
		DurationUnit annotation = AnnotationUtils.findAnnotation(field, DurationUnit.class);
		if (annotation != null) {
			return annotation.value();
		}
		return ChronoUnit.MILLIS;
	}

	static DataUnit resolveDataSizeUnit(Field field) {
		DataSizeUnit annotation = AnnotationUtils.findAnnotation(field, DataSizeUnit.class);
		if (annotation != null) {
			return annotation.value();
		}
		return DataUnit.BYTES;
	}
}
