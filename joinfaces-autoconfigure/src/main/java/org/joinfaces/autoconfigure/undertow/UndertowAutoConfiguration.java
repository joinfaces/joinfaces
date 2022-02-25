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

package org.joinfaces.autoconfigure.undertow;

import java.security.AccessController;
import java.security.PrivilegedAction;

import io.undertow.Undertow;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import lombok.extern.slf4j.Slf4j;
import org.joinfaces.undertow.CompositeResourceManager;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * Spring Boot Auto Configuration of Undertow.
 * Configure undertow to load jsf resources from classpath.
 * @author Marcelo Fernandes
 */
@Slf4j
@AutoConfiguration
@EnableConfigurationProperties(UndertowProperties.class)
@ConditionalOnClass(Undertow.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class UndertowAutoConfiguration {

	@Bean
	public WebServerFactoryCustomizer<UndertowServletWebServerFactory> jsfUndertowFactoryCustomizer(UndertowProperties undertowProperties) {
		return factory -> factory.addDeploymentInfoCustomizers(deploymentInfo -> {
			AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
				deploymentInfo.setResourceManager(new CompositeResourceManager(
					new ClassPathResourceManager(deploymentInfo.getClassLoader(), undertowProperties.getClassPathResource()),
					deploymentInfo.getResourceManager()));

				return null;
			});

			log.info("Setting Undertow classLoader to {} directory", undertowProperties.getClassPathResource());
		});
	}
}
