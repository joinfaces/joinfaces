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

import java.util.EventListener;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ServletContextListenerUtilTest {

	static AtomicBoolean atomicBoolean = new AtomicBoolean();

	@Test
	void testTomcat() {
		ConfigurableServletWebServerFactory factory = new TomcatServletWebServerFactory();

		ServletContextListenerUtil.addListeners(factory, List.of(TestListener.class));

		test(factory);

	}

	@Test
	void testJetty() {
		ConfigurableServletWebServerFactory factory = new JettyServletWebServerFactory();

		ServletContextListenerUtil.addListeners(factory, List.of(TestListener.class));

		test(factory);

	}

	@Test
	void testUndertow() {
		ConfigurableServletWebServerFactory factory = new UndertowServletWebServerFactory();

		ServletContextListenerUtil.addListeners(factory, List.of(TestListener.class));

		test(factory);

	}

	@SneakyThrows
	void test(ConfigurableServletWebServerFactory factory) {
		atomicBoolean.set(false);

		factory.setPort(-1);

		WebServer webServer = factory.getWebServer(servletContext -> System.out.println("FOO"));

		assertThat(atomicBoolean).isTrue();

	}

	public static class TestListener implements ServletContextListener {

		@Override
		public void contextInitialized(ServletContextEvent sce) {
			System.out.println("BAR");
			sce.getServletContext().getClassLoader();
			atomicBoolean.set(true);
		}
	}

}
