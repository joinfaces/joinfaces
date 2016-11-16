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

package org.joinfaces.myfaces;

import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.myfaces.ee6.MyFacesContainerInitializer;

import org.joinfaces.JsfClassFactory;
import org.joinfaces.JsfClassFactoryConfiguration;
import org.joinfaces.MapUtil;

import org.springframework.boot.web.servlet.ServletContextInitializer;

/**
 * Servlet context initializer of MyFaces.
 * @author Marcelo Fernandes
 */
public class MyfacesServletContextInitializer implements ServletContextInitializer, JsfClassFactoryConfiguration {

	/**
	 * Constant of another faces config name of MyFaces.
	 */
	public static final String ANOTHER_FACES_CONFIG = "META-INF/standard-faces-config.xml";

	private final MyfacesProperties myfacesProperties;

	public MyfacesServletContextInitializer(MyfacesProperties myfacesProperties) {
		this.myfacesProperties = myfacesProperties;
	}

	private ServletContainerInitializer servletContainerInitializer;

	@Override
	public ServletContainerInitializer getServletContainerInitializer() {
		if (this.servletContainerInitializer == null) {
			this.servletContainerInitializer = new MyFacesContainerInitializer();
		}
		return this.servletContainerInitializer;
	}

	@Override
	public String getAnotherFacesConfig() {
		return ANOTHER_FACES_CONFIG;
	}

	@Override
	public boolean isExcludeScopedAnnotations() {
		return true;
	}

	@Override
	public void onStartup(ServletContext sc) throws ServletException {
		MyfacesServletContextConfigurer.builder()
			.myfacesProperties(this.myfacesProperties)
			.servletContext(sc)
			.build()
			.configure();

		ServletContainerInitializer servletContainerInitializer = getServletContainerInitializer();
		Map<Class<?>, Set<Class<?>>> map = JsfClassFactory.builder()
			.jsfAnnotatedClassFactoryConfiguration(this)
			.build().find();
		servletContainerInitializer.onStartup(new MapUtil().collectValues(map), sc);
	}
}
