/*
 * Copyright 2016-2016 the original author or authors.
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

package org.joinfaces;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletContext;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Abstract class to configure servlet context from auto configuration properties.
 * @author Marcelo Fernandes
 */
@AllArgsConstructor
@Slf4j
public abstract class ServletContextConfigurer {

	private ServletContext servletContext;

	private String prefix;

	private boolean isNullOrEmpty(String s) {
		return s == null || s.trim().isEmpty();
	}

	private String fullName(String name) {
		String result = name;
		if (!isNullOrEmpty(this.prefix)) {
			result = this.prefix + "." + result;
		}
		return result;
	}

	private void setInitParameterWithDebug(String name, String value) {
		if (!isNullOrEmpty(name)) {
			name = fullName(name);

			this.servletContext.setInitParameter(name, value);

			log.debug("{} = {}", name, value);
		}
		else {
			log.warn("name of init parameter is null! value = {}", value);
		}
	}

	protected void setInitParameterString(String name, String value) {
		if (!isNullOrEmpty(value)) {
			setInitParameterWithDebug(name, value);
		}
	}

	protected void setInitParameterBoolean(String name, Boolean value) {
		if (value != null) {
			setInitParameterWithDebug(name, Boolean.toString(value));
		}
	}

	protected void setInitParameterInteger(String name, Integer value) {
		if (value != null) {
			setInitParameterWithDebug(name, Integer.toString(value));
		}
	}

	protected void setInitParameterLong(String name, Long value) {
		if (value != null) {
			setInitParameterWithDebug(name, Long.toString(value));
		}
	}

	protected void setInitParameterEnum(String name, Enum<?> value) {
		if (value != null) {
			setInitParameterWithDebug(name, value.toString());
		}
	}

	protected void setInitParameterClass(String name, Class<?> value) {
		if (value != null) {
			setInitParameterWithDebug(name, value.getName());
		}
	}

	protected void setInitParameterStringCollection(String name, Collection<String> value, Separator separator) {
		if (value != null) {
			if (value.isEmpty()) {
				setInitParameterWithDebug(name, "");
			}
			else {
				Iterator<String> iterator = value.iterator();
				StringBuilder sb = new StringBuilder(iterator.next());
				while (iterator.hasNext()) {
					sb.append(separator.getString()).append(iterator.next());
				}
				setInitParameterWithDebug(name, sb.toString());
			}
		}
	}

	protected void setInitParameterClassCollection(String name, Collection<Class<?>> value, Separator separator) {
		if (value != null) {
			if (value.isEmpty()) {
				setInitParameterWithDebug(name, "");
			}
			else {
				Iterator<Class<?>> iterator = value.iterator();
				StringBuilder sb = new StringBuilder(iterator.next().getName());
				while (iterator.hasNext()) {
					sb.append(separator.getString()).append(iterator.next().getName());
				}
				setInitParameterWithDebug(name, sb.toString());
			}
		}
	}

	public abstract void configure();

	/**
	 * Possible separators for lists used as init-parameter.
	 *
	 * @author Lars Grefer
	 */
	@Getter
	@AllArgsConstructor
	protected enum Separator {
		SPACE(" "),
		COMMA(","),
		SEMICOLON(";");

		private String string;
	}

}
