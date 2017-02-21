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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;

/**
 * Common base class for all auto-configuration classes which provide properties
 * as {@link javax.servlet.ServletContext} init parameters.
 *
 * @param <PC> Actual type of the properties class
 * @author Lars Grefer
 */
public abstract class PropertiesAutoConfiguration<PC> {

	@Autowired
	protected PC properties;

	@Autowired(required = false)
	protected List<PropertiesCustomizer<? super PC>> propertiesCustomizers;

	@Bean
	@ConditionalOnWebApplication
	public ServletContextInitializer propertiesServletContextInitializer() {

		if (this.propertiesCustomizers != null) {
			for (PropertiesCustomizer<? super PC> propertiesCustomizer : this.propertiesCustomizers) {
				propertiesCustomizer.process(this.properties);
			}
		}

		return new ReflectiveServletContextInitializer<PC>(this.properties);
	}
}
