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

package org.joinfaces.autoconfigure.primefaces;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.boot.web.servlet.ServletContextInitializer;

import static org.assertj.core.api.Assertions.assertThat;

public class PrimefacesFileUploadServletContextInitializerTest {

	private ServletContextInitializer servletContextInitializer;
	private MultipartConfigElement multipartConfigElement;

	@BeforeEach
	public void setUp() {
		this.multipartConfigElement = new MultipartConfigElement("myLocation");
		this.servletContextInitializer =
				new PrimefacesFileUploadServletContextAutoConfiguration()
				.primefacesFileUploadServletContextInitializer(this.multipartConfigElement);
	}

	@Test
	public void testOnStartup() throws ServletException {
		MockServletRegistrationDynamic servletRegistration = new MockServletRegistrationDynamic();
		ServletContext servletContext = Mockito.mock(ServletContext.class);
		Mockito.when(servletContext.getServletRegistration("FacesServlet")).thenReturn(servletRegistration);

		this.servletContextInitializer.onStartup(servletContext);

		assertThat(servletRegistration.getMultipartConfig())
			.isEqualTo(this.multipartConfigElement);
	}

	@Test
	public void testOnStartup2() throws ServletException {
		MockServletRegistration servletRegistration = new MockServletRegistration();
		ServletContext servletContext = Mockito.mock(ServletContext.class);
		Mockito.when(servletContext.getServletRegistration("FacesServlet")).thenReturn(servletRegistration);

		this.servletContextInitializer.onStartup(servletContext);

		assertThat(servletRegistration.getMultipartConfig())
			.isNotEqualTo(this.multipartConfigElement);
	}
}
