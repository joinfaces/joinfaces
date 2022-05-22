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

package org.joinfaces.autoconfigure.weld;

import lombok.extern.slf4j.Slf4j;
import org.jboss.weld.environment.jetty.JettyLegacyContainer;
import org.jboss.weld.environment.servlet.Container;
import org.jboss.weld.environment.servlet.EnhancedListener;
import org.jboss.weld.environment.tomcat.TomcatContainer;
import org.jboss.weld.environment.undertow.UndertowContainer;
import org.joinfaces.autoconfigure.servlet.initializer.ServletContainerInitializerRegistrationBean;

import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

/**
 * {@link ServletContainerInitializerRegistrationBean} for WELD's {@link EnhancedListener}.
 * This also sets the {@value Container#CONTEXT_PARAM_CONTAINER_CLASS} based on the type of the embedded
 * server.
 *
 * @author Lars Grefer
 */
@Slf4j
public class WeldServletContainerInitializerRegistrationBean extends ServletContainerInitializerRegistrationBean<EnhancedListener> {
	public WeldServletContainerInitializerRegistrationBean() {
		super(EnhancedListener.class);
	}

	@Override
	public void customize(ConfigurableServletWebServerFactory factory) {
		setContainerClass(factory);

		super.customize(factory);
	}

	void setContainerClass(ConfigurableServletWebServerFactory factory) {
		Class<? extends Container> containerClass = findContainerClass(factory);

		if (containerClass != null) {
			String containerClassName = containerClass.getName();
			factory.addInitializers(servletContext -> {
						String currentContainerClass = servletContext.getInitParameter(Container.CONTEXT_PARAM_CONTAINER_CLASS);
						if (StringUtils.hasText(currentContainerClass)) {
							log.info("{} has already been set to {}", Container.CONTEXT_PARAM_CONTAINER_CLASS, currentContainerClass);
						}
						else {
							log.debug("Setting {} to {}", Container.CONTEXT_PARAM_CONTAINER_CLASS, containerClassName);
							servletContext.setInitParameter(Container.CONTEXT_PARAM_CONTAINER_CLASS, containerClassName);
						}
					}
			);
		}
	}

	@Nullable
	Class<? extends Container> findContainerClass(ConfigurableServletWebServerFactory factory) {
		if (factory instanceof TomcatServletWebServerFactory) {
			return TomcatContainer.class;
		}
		else if (factory instanceof JettyServletWebServerFactory) {
			return JettyLegacyContainer.class;
		}
		else if (factory instanceof UndertowServletWebServerFactory) {
			return UndertowContainer.class;
		}
		return null;
	}
}
