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

package org.joinfaces.autoconfigure.omnifaces;

import javax.servlet.ServletContainerInitializer;

import org.joinfaces.autoconfigure.javaxfaces.JavaxFacesAutoConfiguration;
import org.joinfaces.autoconfigure.servlet.initializer.ServletContainerInitializerRegistrationBean;
import org.omnifaces.facesviews.FacesViews;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@link EnableAutoConfiguration Auto Configuration} for Omnifaces.
 *
 * @author Marcelo Fernandes
 */
@Configuration
@ConditionalOnClass(FacesViews.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@EnableConfigurationProperties(OmnifacesProperties.class)
@AutoConfigureBefore(JavaxFacesAutoConfiguration.class)
@ServletComponentScan("org.omnifaces")
public class OmnifacesAutoConfiguration {

	/**
	 * {@link EnableAutoConfiguration Auto Configuration} for Omnifaces 1.x.
	 *
	 * @author Lars Grefer
	 */
	@Configuration
	@ConditionalOnClass(name = "org.omnifaces.facesviews.FacesViewsInitializer")
	public static class Omnifaces1AutoConfiguration {

		@Bean
		public ServletContainerInitializerRegistrationBean<?> omnifacesServletContainerInitializer() throws ClassNotFoundException {
			@SuppressWarnings("unchecked")
			Class<? extends ServletContainerInitializer> facesViewsInitializerClass = (Class<? extends ServletContainerInitializer>) Class.forName("org.omnifaces.facesviews.FacesViewsInitializer");

			return new ServletContainerInitializerRegistrationBean<>(facesViewsInitializerClass);
		}
	}

	/**
	 * {@link EnableAutoConfiguration Auto Configuration} for Omnifaces 2.x and 3.x.
	 *
	 * @author Lars Grefer
	 */
	@Configuration
	@ConditionalOnClass(name = "org.omnifaces.ApplicationInitializer")
	public static class Omnifaces2AutoConfiguration {

		@Bean
		public ServletContainerInitializerRegistrationBean<?> omnifacesServletContainerInitializer() throws ClassNotFoundException {
			@SuppressWarnings("unchecked")
			Class<? extends ServletContainerInitializer> applicationInitializerClass = (Class<? extends ServletContainerInitializer>) Class.forName("org.omnifaces.ApplicationInitializer");

			return new ServletContainerInitializerRegistrationBean<>(applicationInitializerClass);
		}
	}
}


