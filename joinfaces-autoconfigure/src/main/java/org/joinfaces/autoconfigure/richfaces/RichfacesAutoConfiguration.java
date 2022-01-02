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

package org.joinfaces.autoconfigure.richfaces;

import org.joinfaces.autoconfigure.javaxfaces.JavaxFacesAutoConfiguration;
import org.joinfaces.autoconfigure.servlet.initializer.ServletContainerInitializerRegistrationBean;
import org.richfaces.application.CoreConfiguration;
import org.richfaces.webapp.ServletsInitializer;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot Auto Configuration of RichFaces.
 * @author Marcelo Fernandes
 * @author Jamillo Santos
 * @author Renato Soares
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(RichfacesProperties.class)
@ConditionalOnClass(CoreConfiguration.class)
@AutoConfigureBefore(JavaxFacesAutoConfiguration.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class RichfacesAutoConfiguration {

	@Bean
	public ServletContainerInitializerRegistrationBean<ServletsInitializer> richfacesServletsInitializer() {
		return new ServletContainerInitializerRegistrationBean<>(ServletsInitializer.class);
	}
}
