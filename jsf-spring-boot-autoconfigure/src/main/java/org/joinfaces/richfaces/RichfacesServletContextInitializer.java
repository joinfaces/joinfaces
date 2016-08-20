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

package org.joinfaces.richfaces;

import org.springframework.boot.web.servlet.ServletContextInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Servlet Context Initializer of RichFaces.
 * @author Marcelo Fernandes
 * @author Jamillo Santos
 */
public class RichfacesServletContextInitializer implements ServletContextInitializer {

	private final RichfacesProperties richfacesProperties;

	public RichfacesServletContextInitializer(RichfacesProperties richfacesProperties) {
		this.richfacesProperties = richfacesProperties;
	}

	@Override
	public void onStartup(ServletContext sc) throws ServletException {
		RichfacesServletContextConfigurer.builder()
			.richfacesProperties(this.richfacesProperties)
			.servletContext(sc)
			.build()
			.configure();
	}
}
