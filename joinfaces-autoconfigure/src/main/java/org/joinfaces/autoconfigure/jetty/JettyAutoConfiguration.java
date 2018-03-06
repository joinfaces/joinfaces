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

package org.joinfaces.autoconfigure.jetty;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.util.resource.ResourceCollection;
import org.eclipse.jetty.webapp.WebAppContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@Configuration
@EnableConfigurationProperties({JettyProperties.class})
@ConditionalOnClass(name = "org.eclipse.jetty.server.Server")
public class JettyAutoConfiguration {

	@Autowired
	private JettyProperties jettyProperties;

	@Bean
	public WebServerFactoryCustomizer<JettyServletWebServerFactory> jsfJettyFactoryCustomizer() {
		return factory -> factory.addServerCustomizers(server -> {
			Handler[] childHandlersByClass = server.getChildHandlersByClass(WebAppContext.class);
			final WebAppContext webAppContext = (WebAppContext) childHandlersByClass[0];

			try {
				ClassPathResource classPathResource = new ClassPathResource(this.jettyProperties.getClassPathResource());
				webAppContext.setBaseResource(new ResourceCollection(classPathResource.getURI().toString()));

				AccessController.doPrivileged(new PrivilegedAction<Void>() {
					@Override
					public Void run() {
						webAppContext.setClassLoader(new URLClassLoader(new URL[0], this.getClass().getClassLoader()));
						return null;
					}
				});

				log.info("Setting Jetty classLoader to {} directory", this.jettyProperties.getClassPathResource());
			}
			catch (IOException exception) {
				log.error("Unable to configure Jetty classLoader to {} directory {}", this.jettyProperties.getClassPathResource(), exception.getMessage());

				throw new RuntimeException(exception);
			}
		});
	}
}
