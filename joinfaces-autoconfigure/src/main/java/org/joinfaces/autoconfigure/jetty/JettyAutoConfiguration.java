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

package org.joinfaces.autoconfigure.jetty;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.resource.ResourceCollection;
import org.eclipse.jetty.webapp.WebAppContext;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.jetty.JettyServerCustomizer;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

/**
 * Spring Boot Auto Configuration of Jetty.
 * Taken from
 * https://github.com/ghillert/spring-boot-jsp-demo/blob/jetty/src/main/java/com/hillert/JspDemoApplication.java#L78
 * and from https://github.com/eclipse/jetty.project/issues/420 and from
 * https://github.com/spring-projects/spring-boot/pull/5290
 * @author Marcelo Fernandes
 */
@Slf4j
@AutoConfiguration
@RequiredArgsConstructor
@EnableConfigurationProperties(JettyProperties.class)
@ConditionalOnClass(Server.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class JettyAutoConfiguration {

	private final JettyProperties jettyProperties;

	@Bean
	public WebServerFactoryCustomizer<JettyServletWebServerFactory> jsfJettyFactoryCustomizer() {
		return factory -> factory.addServerCustomizers(new JettyServerCustomizer() {
			@Override
			@SneakyThrows(IOException.class)
			public void customize(Server server) {
				Handler[] childHandlersByClass = server.getChildHandlersByClass(WebAppContext.class);
				final WebAppContext webAppContext = (WebAppContext) childHandlersByClass[0];

				String classPathResourceString = JettyAutoConfiguration.this.jettyProperties.getClassPathResource();

				webAppContext.setBaseResource(new ResourceCollection(
					Resource.newResource(new ClassPathResource(classPathResourceString).getURI()),
					webAppContext.getBaseResource()));

				log.info("Setting Jetty classLoader to {} directory", classPathResourceString);
			}
		});
	}
}
