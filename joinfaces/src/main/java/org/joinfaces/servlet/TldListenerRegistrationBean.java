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

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Singular;

import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.AbstractServletWebServerFactory;
import org.springframework.boot.web.servlet.server.Jsp;
import org.springframework.util.ClassUtils;

/**
 * This class registers listeners to the servlet context, which would normally registered in a tld file.
 * <p>
 * Some JSF libraries declare listeners in JSP taglib files. Undertow and Jetty (since 9.4.14.v20181114)
 * don't find there listeners, so this class can be used as spring bean to manually add the listeners to an
 * embedded undertow or jetty.
 * <p>
 * Also the embedded Tomcat does not find the listeners, if tomcat-embed-jasper is not deployed.
 *
 * @author Lars Grefer
 * @see ServletContextListenerUtil
 */
@Builder
@RequiredArgsConstructor
public class TldListenerRegistrationBean implements WebServerFactoryCustomizer<AbstractServletWebServerFactory> {

	@Singular
	private final Collection<Class<? extends EventListener>> listeners;

	@Override
	public void customize(AbstractServletWebServerFactory factory) {
		if (mustAddInitializer(factory)) {
			ServletContextListenerUtil.addListeners(factory, listeners);
		}
	}

	private boolean mustAddInitializer(AbstractServletWebServerFactory factory) {
		if (factory instanceof JettyServletWebServerFactory) {
			return true;
		}

		if (factory instanceof UndertowServletWebServerFactory) {
			return true;
		}

		if (factory instanceof TomcatServletWebServerFactory) {
			Jsp jsp = factory.getJsp();
			return !jsp.getRegistered() || !ClassUtils.isPresent(jsp.getClassName(), null);
		}

		return false;
	}
}
