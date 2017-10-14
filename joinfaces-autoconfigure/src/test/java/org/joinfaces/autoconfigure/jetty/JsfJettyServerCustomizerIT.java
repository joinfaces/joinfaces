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

import java.net.MalformedURLException;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.embedded.jetty.JettyWebServer;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JettySpringBootAutoConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class JsfJettyServerCustomizerIT {

	@Autowired
	private JettyProperties jettyProperties;

	@Test
	public void customize() throws MalformedURLException {
		JettyServletWebServerFactory factory = new JettyServletWebServerFactory();

		JsfJettyServerCustomizer customizer = new JsfJettyServerCustomizer(this.jettyProperties);

		Server server = ((JettyWebServer) factory.getWebServer()).getServer();

		customizer.customize(server);

		Handler[] childHandlersByClass = server.getChildHandlersByClass(WebAppContext.class);
		WebAppContext webAppContext = (WebAppContext) childHandlersByClass[0];

		assertThat(webAppContext.getBaseResource().getResource("testJetty.txt").exists())
			.isTrue();
	}

	@Test(expected = RuntimeException.class)
	public void invalidClassPathResource() throws MalformedURLException {
		JettyServletWebServerFactory factory = new JettyServletWebServerFactory();

		JettyProperties jp = new JettyProperties();
		jp.setClassPathResource("/~ã``'[ªº*-+.@#$%{&*ç|°;.<>");

		JsfJettyServerCustomizer customizer = new JsfJettyServerCustomizer(jp);

		Server server = ((JettyWebServer) factory.getWebServer()).getServer();

		customizer.customize(server);

		Handler[] childHandlersByClass = server.getChildHandlersByClass(WebAppContext.class);
		WebAppContext webAppContext = (WebAppContext) childHandlersByClass[0];

		assertThat(webAppContext.getBaseResource().getResource("test.txt").exists()).isTrue();
	}
}
