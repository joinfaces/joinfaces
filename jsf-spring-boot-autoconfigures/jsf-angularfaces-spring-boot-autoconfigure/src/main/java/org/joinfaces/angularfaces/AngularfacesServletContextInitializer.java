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

package org.joinfaces.angularfaces;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.web.servlet.ServletContextInitializer;

/**
 * Servlet context initializer of AngularFaces.
 * @author Marcelo Fernandes
 */
public class AngularfacesServletContextInitializer implements ServletContextInitializer {

	private final AngularfacesProperties angularfacesProperties;

	public AngularfacesServletContextInitializer(AngularfacesProperties angularfacesProperties) {
		this.angularfacesProperties = angularfacesProperties;
	}

	@Override
	public void onStartup(ServletContext sc) throws ServletException {
		AngularfacesServletContextConfigurer.builder()
			.angularfacesProperties(this.angularfacesProperties)
			.servletContext(sc)
			.build()
			.configure();
	}
}
