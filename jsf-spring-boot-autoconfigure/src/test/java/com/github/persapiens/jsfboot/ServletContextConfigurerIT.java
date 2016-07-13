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

package com.github.persapiens.jsfboot;

import javax.servlet.ServletContext;

import org.testng.annotations.Test;

import org.springframework.mock.web.MockServletContext;

import static org.assertj.core.api.Assertions.assertThat;

@Test
public class ServletContextConfigurerIT {

	public void testEmpty() {
		ServletContext servletContext = new MockServletContext();

		ServletContextConfigurer servletContextConfigurer
			= new JsfServletContextConfigurer(servletContext);

		servletContextConfigurer.configure();

		assertThat(servletContext.getInitParameter("jsf.empty")).isNull();
	}

	public void testKey() {
		ServletContext servletContext = new MockServletContext();

		ServletContextConfigurer servletContextConfigurer
			= new JsfServletContextConfigurer(servletContext);

		servletContextConfigurer.configure();

		assertThat(servletContext.getInitParameter("jsf.key")).isEqualTo("value");
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testNull() {
		ServletContext servletContext = new MockServletContext();

		ServletContextConfigurer servletContextConfigurer
			= new NullServletContextConfigurer(servletContext);

		servletContextConfigurer.configure();

		assertThat(servletContext.getInitParameter(null)).isNull();
		assertThat(servletContext.getInitParameter("string")).isNull();
		assertThat(servletContext.getInitParameter("boolean")).isNull();
		assertThat(servletContext.getInitParameter("integer")).isNull();
	}

}
