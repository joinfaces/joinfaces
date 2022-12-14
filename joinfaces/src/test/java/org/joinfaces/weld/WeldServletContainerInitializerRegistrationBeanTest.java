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

package org.joinfaces.weld;

import org.jboss.weld.environment.jetty.AbstractJettyContainer;
import org.jboss.weld.environment.tomcat.TomcatContainer;
import org.jboss.weld.environment.undertow.UndertowContainer;
import org.joinfaces.weld.WeldServletContainerInitializerRegistrationBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.servlet.server.AbstractServletWebServerFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class WeldServletContainerInitializerRegistrationBeanTest {

	private WeldServletContainerInitializerRegistrationBean bean;

	@BeforeEach
	void setUp() {
		this.bean = new WeldServletContainerInitializerRegistrationBean();
	}

	@Test
	void setContainerClass() {
		this.bean.setContainerClass(new TomcatServletWebServerFactory());
		this.bean.setContainerClass(new JettyServletWebServerFactory());
		this.bean.setContainerClass(new UndertowServletWebServerFactory());
	}

}
