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

package org.joinfaces.undertow;

import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;

import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.servlet.api.DeploymentInfo;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.context.embedded.undertow.UndertowDeploymentInfoCustomizer;

/**
 * Configure undertow to load jsf resources from classpath.
 * @author Marcelo Fernandes
 */
@Slf4j
public class JsfUndertowDeploymentInfoCustomizer implements UndertowDeploymentInfoCustomizer {

	private final UndertowProperties undertowProperties;

	public JsfUndertowDeploymentInfoCustomizer(UndertowProperties undertowProperties) {
		this.undertowProperties = undertowProperties;
	}

	@Override
	public void customize(final DeploymentInfo di) {
		AccessController.doPrivileged(new PrivilegedAction<Void>() {
			@Override
			public Void run() {
				ClassLoader jsfClassLoader = new URLClassLoader(new URL[0], this.getClass().getClassLoader());
				di.setClassLoader(jsfClassLoader);

				di.setResourceManager(new ClassPathResourceManager(
					jsfClassLoader, JsfUndertowDeploymentInfoCustomizer.this.undertowProperties.getClassPathResource()));

				return null;
			}
		});

		log.info("Setting Undertow classLoader to {} directory", this.undertowProperties.getClassPathResource());
	}
}
