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

package org.joinfaces.jetty;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.ResourceCollection;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.context.embedded.jetty.JettyServerCustomizer;
import org.springframework.core.io.ClassPathResource;

/**
 * Jetty Server Customizer of JSF.
 *
 * @author Marcelo Fernandes
 */
public class JsfJettyServerCustomizer implements JettyServerCustomizer {

	private final JettyProperties jettyProperties;

	private static final Logger LOGGER = LoggerFactory.getLogger(JsfJettyServerCustomizer.class);

	public JsfJettyServerCustomizer(JettyProperties jettyProperties) {
		this.jettyProperties = jettyProperties;
	}

	@Override
	public void customize(Server server) {
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

			LOGGER.info("Setting Jetty classLoader to " + this.jettyProperties.getClassPathResource() + " directory");
		}
		catch (IOException exception) {
			LOGGER.error("Unable to configure Jetty classLoader to " + this.jettyProperties.getClassPathResource() + " directory " + exception.getMessage());

			throw new RuntimeException(exception);
		}
	}
}
