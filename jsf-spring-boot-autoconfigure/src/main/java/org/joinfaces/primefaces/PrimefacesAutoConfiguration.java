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

package org.joinfaces.primefaces;

import org.joinfaces.configuration.ConditionalOnClassVersion;
import org.joinfaces.configuration.ReflectiveServletContextInitializer;
import org.joinfaces.javaxfaces.JavaxFacesSpringBootAutoConfiguration;
import org.primefaces.util.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot Auto Configuration for Primefaces.
 *
 * @author Marcelo Fernandes
 * @author Lars Grefer
 */
@Configuration
@ConditionalOnClass(Constants.class)
@ConditionalOnWebApplication
@AutoConfigureBefore(JavaxFacesSpringBootAutoConfiguration.class)
public class PrimefacesAutoConfiguration {

	@Configuration
	@EnableConfigurationProperties(Primefaces5_0Properties.class)
	@ConditionalOnClassVersion(value = Constants.ContextParams.class, version = "5\\.0.*")
	public static class Primefaces5_0AutoConfiguration {

		@Autowired
		private Primefaces5_0Properties primefacesProperties;

		@Bean
		public ServletContextInitializer primefacesServletContextInitializer() {
			return new ReflectiveServletContextInitializer<Primefaces5_0Properties>(
					this.primefacesProperties
			);
		}
	}

	@Configuration
	@EnableConfigurationProperties(Primefaces5_1Properties.class)
	@ConditionalOnClassVersion(value = Constants.ContextParams.class, version = "5\\.1.*")
	public static class Primefaces5_1AutoConfiguration {

		@Autowired
		private Primefaces5_1Properties primefacesProperties;

		@Bean
		public ServletContextInitializer primefacesServletContextInitializer() {
			return new ReflectiveServletContextInitializer<Primefaces5_1Properties>(
					this.primefacesProperties
			);
		}
	}

	@Configuration
	@EnableConfigurationProperties(Primefaces5_2Properties.class)
	@ConditionalOnClassVersion(value = Constants.ContextParams.class, version = "5\\.[2-9].*")
	public static class Primefaces5_2AutoConfiguration {

		@Autowired
		private Primefaces5_2Properties primefacesProperties;

		@Bean
		public ServletContextInitializer primefacesServletContextInitializer() {
			return new ReflectiveServletContextInitializer<Primefaces5_2Properties>(
					this.primefacesProperties
			);
		}
	}

	@Configuration
	@EnableConfigurationProperties(Primefaces6_0Properties.class)
	@ConditionalOnClassVersion(value = Constants.ContextParams.class, version = "[6-9].*")
	public static class Primefaces6_0AutoConfiguration {

		@Autowired
		private Primefaces6_0Properties primefacesProperties;

		@Bean
		public ServletContextInitializer primefacesServletContextInitializer() {
			return new ReflectiveServletContextInitializer<Primefaces6_0Properties>(
					this.primefacesProperties
			);
		}
	}
}
