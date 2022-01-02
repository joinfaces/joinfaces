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

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.embedded.jetty.JettyWebServer;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = JettyAutoConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class JettyAutoConfigurationIT {

	@Autowired
	private JettyAutoConfiguration jettyAutoConfiguration;

	@Test
	public void customize() {
		JettyServletWebServerFactory factory = new JettyServletWebServerFactory();

		this.jettyAutoConfiguration.jsfJettyFactoryCustomizer().customize(factory);

		Server server = ((JettyWebServer) factory.getWebServer()).getServer();

		Handler[] childHandlersByClass = server.getChildHandlersByClass(WebAppContext.class);
		WebAppContext webAppContext = (WebAppContext) childHandlersByClass[0];

		assertThat(webAppContext.getBaseResource().getResource("testJetty.txt").exists())
			.isTrue();
	}
}
