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

package org.joinfaces.autoconfigure.omnifaces;

import org.joinfaces.autoconfigure.faces.JakartaFaces3AutoConfiguration;
import org.joinfaces.servlet.ServletContainerInitializerRegistrationBean;
import org.omnifaces.ApplicationInitializer;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration Auto Configuration} for Omnifaces.
 *
 * @author Marcelo Fernandes
 */
@AutoConfiguration(before = JakartaFaces3AutoConfiguration.class)
@ConditionalOnClass(ApplicationInitializer.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@EnableConfigurationProperties(OmnifacesProperties.class)
@ServletComponentScan("org.omnifaces")
public class OmnifacesAutoConfiguration {

	@Bean
	public ServletContainerInitializerRegistrationBean<ApplicationInitializer> omnifacesServletContainerInitializer() {
		return new ServletContainerInitializerRegistrationBean<>(ApplicationInitializer.class);
	}

}


