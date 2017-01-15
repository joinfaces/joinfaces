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

package org.joinfaces.primefaces;

import javax.faces.webapp.FacesServlet;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import com.sun.faces.config.FacesInitializer;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * To be processed after {@link FacesInitializer} runs, so we have
 * {@link FacesServlet} at hand.
 *
 * @author Nurettin Yilmaz
 */
@Order(Ordered.LOWEST_PRECEDENCE)
class PrimefacesFileUploadServletContextInitializer implements ServletContextInitializer {

	private static final String FACES_SERVLET_NAME = "FacesServlet";

	private final MultipartConfigElement multipartConfigElement;

	PrimefacesFileUploadServletContextInitializer(MultipartConfigElement multipartConfigElement) {
		this.multipartConfigElement = multipartConfigElement;
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		ServletRegistration servletRegistration = servletContext.getServletRegistration(FACES_SERVLET_NAME);
		if (servletRegistration instanceof ServletRegistration.Dynamic) {
			((ServletRegistration.Dynamic) servletRegistration).setMultipartConfig(this.multipartConfigElement);
		}
	}
}
