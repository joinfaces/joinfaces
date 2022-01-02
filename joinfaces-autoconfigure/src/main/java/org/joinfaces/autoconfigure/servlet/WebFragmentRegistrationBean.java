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

package org.joinfaces.autoconfigure.servlet;

import java.util.EventListener;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.ListenerInfo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Context;
import org.eclipse.jetty.webapp.AbstractConfiguration;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;

import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.undertow.UndertowDeploymentInfoCustomizer;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;

/**
 * {@link org.springframework.boot.web.servlet.RegistrationBean RegistrationBean} for {@code web-fragment.xml} files.
 * <p>
 * This is implemented as {@link WebServerFactoryCustomizer} so it only applies to embedded servlet-containers.
 * When deployed as war file, the external servlet-container will find the {@code web-fragment.xml} file.
 * <p>
 * The main feature of this class is the way it registers listeners.
 * They will be treated as if they were declared in a {@code web-fragment.xml} and therefore aren't affected by the
 * restrictions of Section 4.4 of the Servlet Specification.
 *
 * @author Lars Grefer
 */
@Slf4j
@Getter
@Setter
public class WebFragmentRegistrationBean implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

	private List<Class<? extends EventListener>> listeners = new LinkedList<>();

	private List<ErrorPage> errorPages = new LinkedList<>();

	private Map<String, String> contextParams = new LinkedHashMap<>();

	@Override
	public void customize(ConfigurableServletWebServerFactory factory) {

		if (factory instanceof TomcatServletWebServerFactory) {
			((TomcatServletWebServerFactory) factory).addContextCustomizers(new TomcatListenerAdder(this.listeners));
		}
		else if (factory instanceof JettyServletWebServerFactory) {
			((JettyServletWebServerFactory) factory).addConfigurations(new JettyListenerAdder(this.listeners));
		}
		else if (factory instanceof UndertowServletWebServerFactory) {
			((UndertowServletWebServerFactory) factory).addDeploymentInfoCustomizers(new UndertowListenerAdder(this.listeners));
		}
		else {
			log.warn("Unkown WebServerFactory implementation: {}", factory.getClass());
			factory.addInitializers(servletContext -> this.listeners.forEach(servletContext::addListener));
		}

		factory.addInitializers(servletContext -> this.contextParams.forEach(servletContext::setInitParameter));

		this.errorPages.forEach(factory::addErrorPages);
	}

	/**
	 * This {@link TomcatContextCustomizer} adds listeners to the servlet-context in a non-programmatic way,
	 * so they aren't affected by the restrictions for programmatically registered listeners of Section 4.4
	 * of the Servlet Specification.
	 */
	@RequiredArgsConstructor
	public static class TomcatListenerAdder implements TomcatContextCustomizer {

		private final List<Class<? extends EventListener>> listeners;

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

		private final List<Class<? extends EventListener>> listeners;

		@Override
		public void configure(WebAppContext context) throws Exception {
			for (Class<? extends EventListener> listener : this.listeners) {
				context.addEventListener(listener.getDeclaredConstructor().newInstance());
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

		private final List<Class<? extends EventListener>> listeners;

		@Override
		public void customize(DeploymentInfo deploymentInfo) {
			this.listeners.forEach(listener -> deploymentInfo.addListener(new ListenerInfo(listener, false)));
		}
	}

}
