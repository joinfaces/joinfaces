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

package org.joinfaces.autoconfigure.javaxfaces;

import java.util.Optional;

import javax.faces.context.FacesContext;
import javax.faces.flow.FlowHandler;
import javax.faces.push.PushContext;
import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Auto configuration for Standard Javax Faces Properties.
 *
 * @author Marcelo Fernandes
 */
@Configuration
@ConditionalOnClass(FacesContext.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class JavaxFacesAutoConfiguration {

	/**
	 * This bean registers the {@link FacesServlet}.
	 * <p>
	 * This {@link ServletRegistrationBean} also sets two
	 * {@link ServletContext#setAttribute(String, Object) servlet-context attributes} to inform Mojarra and MyFaces about
	 * the dynamically added Servlet.
	 *
	 * @param javaxFaces2_3Properties The optional {@link JavaxFaces2_3Properties} in order to honor the
	 *                                {@link JavaxFaces2_3Properties#disableFacesservletToXhtml javax.faces.DISABLE_FACESSERVLET_TO_XHTML}
	 *                                init-parameter.
	 * @return A custom {@link ServletRegistrationBean} which registers the {@link FacesServlet}.
	 */
	@Bean
	@ConfigurationProperties("jsf.faces-servlet")
	public ServletRegistrationBean<FacesServlet> facesServletServletRegistrationBean(
			@SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<JavaxFaces2_3Properties> javaxFaces2_3Properties
	) {
		ServletRegistrationBean<FacesServlet> facesServletServletRegistrationBean = new ServletRegistrationBean<FacesServlet>(new FacesServlet()) {
			@Override
			protected ServletRegistration.Dynamic addRegistration(String description, ServletContext servletContext) {
				ServletRegistration.Dynamic servletRegistration = super.addRegistration(description, servletContext);
				if (servletRegistration != null) {
					servletContext.setAttribute("org.apache.myfaces.DYNAMICALLY_ADDED_FACES_SERVLET", true);
					servletContext.setAttribute("com.sun.faces.facesInitializerMappingsAdded", true);
				}
				return servletRegistration;
			}
		};

		facesServletServletRegistrationBean.setName("FacesServlet");
		facesServletServletRegistrationBean.addUrlMappings("/faces/*", "*.jsf", "*.faces", "*.xhtml");

		javaxFaces2_3Properties.map(JavaxFaces2_3Properties::getDisableFacesservletToXhtml)
				.ifPresent(disableFacesservletToXhtml -> {
					if (disableFacesservletToXhtml) {
						facesServletServletRegistrationBean.getUrlMappings().remove("*.xhtml");
					}
				});

		return facesServletServletRegistrationBean;
	}

	/**
	 * Auto configuration for JSF 2.0.
	 */
	@Configuration
	@EnableConfigurationProperties(JavaxFaces2_0Properties.class)
	public static class JavaxFaces2_0AutoConfiguration {
	}

	/**
	 * Auto configuration for JSF 2.1.
	 */
	@Configuration
	@EnableConfigurationProperties(JavaxFaces2_1Properties.class)
	public static class JavaxFaces2_1AutoConfiguration {
	}

	/**
	 * Auto configuration for JSF 2.2.
	 */
	@Configuration
	@EnableConfigurationProperties(JavaxFaces2_2Properties.class)
	@ConditionalOnClass(FlowHandler.class)
	public static class JavaxFaces2_2AutoConfiguration {
	}

	/**
	 * Auto configuration for JSF 2.3.
	 */
	@Configuration
	@EnableConfigurationProperties(JavaxFaces2_3Properties.class)
	@ConditionalOnClass(PushContext.class)
	public static class JavaxFaces2_3AutoConfiguration {
	}

}
