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

package com.github.persapiens.jsfboot;

import javax.servlet.ServletContext;

import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract class to configure servlet context from auto configuration properties.
 * @author Marcelo Fernandes
 */
@AllArgsConstructor
public abstract class ServletContextConfigurer {

	private static final Logger logger = LoggerFactory
		.getLogger(ServletContextConfigurer.class);

	private ServletContext servletContext;

	private String preffix;

	private boolean isNullOrEmpty(String s) {
		return s == null || s.trim().isEmpty();
	}

	private String fullName(String name) {
		String result = name;
		if (!isNullOrEmpty(this.preffix)) {
			result = this.preffix + "." + result;
		}
		return result;
	}

	private void setInitParameterWithDebug(String name, String value) {
		name = fullName(name);

		this.servletContext.setInitParameter(name, value);

		logger.debug(name + " = " + value);
	}

	protected void setInitParameter(String name, String value) {
		if (!isNullOrEmpty(value)) {
			setInitParameterWithDebug(name, value);
		}
	}

	protected void setInitParameter(String name, Boolean value) {
		if (value != null) {
			setInitParameterWithDebug(name, Boolean.toString(value));
		}
	}

	protected void setInitParameter(String name, Integer value) {
		if (value != null) {
			setInitParameterWithDebug(name, Integer.toString(value));
		}
	}

	public abstract void configure();

}
