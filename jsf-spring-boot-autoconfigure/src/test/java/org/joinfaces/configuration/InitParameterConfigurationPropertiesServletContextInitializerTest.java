/*
 * Copyright 2016-2017 the original author or authors.
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

package org.joinfaces.configuration;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;

import org.springframework.core.Ordered;
import org.springframework.mock.web.MockServletContext;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Lars Grefer
 */
public class InitParameterConfigurationPropertiesServletContextInitializerTest {

	private InitParameterConfigurationPropertiesServletContextInitializer<? extends ServletContextInitParameterConfigurationProperties> servletContextInitializer;

	@Before
	public void setUp() {
		this.servletContextInitializer = new InitParameterConfigurationPropertiesServletContextInitializer<ServletContextInitParameterConfigurationProperties>(
				new ServletContextInitParameterConfigurationProperties() {
				}
		);
	}

	@Test
	public void onStartup() throws ServletException {
		MockServletContext servletContext = new MockServletContext();
		this.servletContextInitializer.onStartup(servletContext);
	}

	@Test
	public void testDefaultOrder() {
		assertThat(this.servletContextInitializer.getOrder()).isEqualTo(Ordered.LOWEST_PRECEDENCE);
	}

	@Test
	public void testExplicitOrder() {
		this.servletContextInitializer = new InitParameterConfigurationPropertiesServletContextInitializer<ServletContextInitParameterConfigurationProperties>(
				new ServletContextInitParameterConfigurationProperties() {
				}, 42
		);

		assertThat(this.servletContextInitializer.getOrder()).isEqualTo(42);
	}

}
