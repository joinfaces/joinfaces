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

package com.github.persapiens.jsfboot.butterfaces;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.context.embedded.ServletContextInitializer;

/**
 * Servlet Context Initializer of ButterFaces.
 * @author Marcelo Fernandes
 */
public class ButterfacesServletContextInitializer implements ServletContextInitializer {

	private final ButterfacesProperties butterfacesProperties;

	public ButterfacesServletContextInitializer(ButterfacesProperties butterfacesProperties) {
		this.butterfacesProperties = butterfacesProperties;
	}

	@Override
	public void onStartup(ServletContext sc) throws ServletException {
		ButterfacesServletContextConfigurer.builder()
			.butterfacesProperties(this.butterfacesProperties)
			.servletContext(sc)
			.build()
			.configure();
	}
}
