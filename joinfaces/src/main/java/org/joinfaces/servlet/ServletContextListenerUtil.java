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

package org.joinfaces.servlet;

import java.util.Collection;
import java.util.EventListener;

import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.ListenerInfo;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Context;
import org.eclipse.jetty.webapp.AbstractConfiguration;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;

import org.springframework.beans.BeanUtils;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.undertow.UndertowDeploymentInfoCustomizer;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;

/**
 * Helper class for Servlet Context Listeners.
 * <p>
 * The main feature of this class is the way it registers listeners.
 * They will be treated as if they were declared in a non-programatic way and therefore aren't affected by the
 * restrictions of Section 4.4 of the Servlet Specification.
 *
 * @author Lars Grefer
 */
@Slf4j
@UtilityClass
public class ServletContextListenerUtil {

	/**
	 * Adds the given listener classes to the given Web server.
	 *
	 * @param factory   The factory for the WebServer to add the listeners to.
	 * @param listeners The listeners to add.
	 */
	public static void addListeners(ConfigurableServletWebServerFactory factory, Collection<Class<? extends EventListener>> listeners) {
		if (factory instanceof TomcatServletWebServerFactory tomcatFactory) {
			tomcatFactory.addContextCustomizers(new TomcatListenerAdder(listeners));
		}
		else if (factory instanceof JettyServletWebServerFactory jettyFactory) {
			jettyFactory.addConfigurations(new JettyListenerAdder(listeners));
		}
		else if (factory instanceof UndertowServletWebServerFactory undertowFactory) {
			undertowFactory.addDeploymentInfoCustomizers(new UndertowListenerAdder(listeners));
		}
		else {
			log.warn("Unkown WebServerFactory implementation: {}", factory.getClass());
			factory.addInitializers(servletContext -> listeners.forEach(servletContext::addListener));
		}
	}

	/**
	 * This {@link TomcatContextCustomizer} adds listeners to the servlet-context in a non-programmatic way,
	 * so they aren't affected by the restrictions for programmatically registered listeners of Section 4.4
	 * of the Servlet Specification.
	 */
	@RequiredArgsConstructor
	public static class TomcatListenerAdder implements TomcatContextCustomizer {

		private final Collection<Class<? extends EventListener>> listeners;

		@Override
		public void customize(Context context) {
			this.listeners.forEach(listener ->
					context.addApplicationListener(listener.getName())
			);
		}
	}

	/**
	 * This {@link Configuration} adds listeners to the servlet-context in a non-programmatic way,
	 * so they aren't affected by the restrictions for programmatically registered listeners of Section 4.4
	 * of the Servlet Specification.
	 */
	@RequiredArgsConstructor
	public static class JettyListenerAdder extends AbstractConfiguration {

		private final Collection<Class<? extends EventListener>> listeners;

		@Override
		public void configure(WebAppContext context) {
			for (Class<? extends EventListener> listener : this.listeners) {
				context.addEventListener(BeanUtils.instantiateClass(listener));
			}
		}
	}

	/**
	 * This {@link UndertowDeploymentInfoCustomizer} adds listeners to the servlet-context in a non-programmatic way,
	 * so they aren't affected by the restrictions for programmatically registered listeners of Section 4.4
	 * of the Servlet Specification.
	 */
	@RequiredArgsConstructor
	public static class UndertowListenerAdder implements UndertowDeploymentInfoCustomizer {

		private final Collection<Class<? extends EventListener>> listeners;

		@Override
		public void customize(DeploymentInfo deploymentInfo) {
			this.listeners.forEach(listener -> deploymentInfo.addListener(new ListenerInfo(listener, false)));
		}
	}
}
