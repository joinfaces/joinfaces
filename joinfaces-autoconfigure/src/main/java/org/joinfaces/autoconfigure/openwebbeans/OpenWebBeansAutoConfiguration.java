/*
 * Copyright 2016-2023 the original author or authors.
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

package org.joinfaces.autoconfigure.openwebbeans;

import org.apache.webbeans.servlet.WebBeansConfigurationListener;
import org.joinfaces.autoconfigure.CdiImplementationAutoConfiguration;
import org.joinfaces.servlet.ServletContainerInitializerRegistrationBean;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * Spring Boot Auto Configuration for Apache OpenWebBeans.
 *
 * @author Lars Grefer
 */
@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnClass(WebBeansConfigurationListener.class)
@ConditionalOnMissingBean(CdiImplementationAutoConfiguration.class)
@ImportRuntimeHints(OpenWebBeansRuntimeHintsRegistrar.class)
public class OpenWebBeansAutoConfiguration implements CdiImplementationAutoConfiguration {

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public ServletContainerInitializerRegistrationBean<WebBeansConfigurationListener.Auto> openWebBeansServletContainerInitializer() {
		System.setProperty("openwebbeans.web.sci.active", "true");
		return new ServletContainerInitializerRegistrationBean<>(WebBeansConfigurationListener.Auto.class);
	}
}
