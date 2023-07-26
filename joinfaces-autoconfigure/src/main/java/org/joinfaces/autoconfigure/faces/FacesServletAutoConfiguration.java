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

package org.joinfaces.autoconfigure.faces;

import jakarta.faces.webapp.FacesServlet;
import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration(after = JakartaFaces3AutoConfiguration.class)
@ConditionalOnClass(FacesServlet.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@EnableConfigurationProperties(FacesServletProperties.class)
public class FacesServletAutoConfiguration {

	/**
	 * This bean registers the {@link FacesServlet}.
	 * <p>
	 * This {@link ServletRegistrationBean} also sets two
	 * {@link ServletContext#setAttribute(String, Object) servlet-context attributes} to inform Mojarra and MyFaces about
	 * the dynamically added Servlet.
	 *
	 * @param facesServletProperties The properties for the {@link FacesServlet}-registration.
	 * @param multipartConfig The {@link MultipartConfigElement} for the FacesServlet, if available.
	 *
	 * @return A custom {@link ServletRegistrationBean} which registers the {@link FacesServlet}.
	 */
	@Bean
	public ServletRegistrationBean<FacesServlet> facesServletRegistrationBean(
			FacesServletProperties facesServletProperties,
			ObjectProvider<MultipartConfigElement> multipartConfig
	) {
		ServletRegistrationBean<FacesServlet> facesServletServletRegistrationBean = new ServletRegistrationBean<>(new FacesServlet()) {
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

		facesServletServletRegistrationBean.setName(facesServletProperties.getName());
		facesServletServletRegistrationBean.setUrlMappings(facesServletProperties.getUrlMappings());
		facesServletServletRegistrationBean.setLoadOnStartup(facesServletProperties.getLoadOnStartup());
		facesServletServletRegistrationBean.setEnabled(facesServletProperties.isEnabled());
		facesServletServletRegistrationBean.setAsyncSupported(facesServletProperties.isAsyncSupported());
		facesServletServletRegistrationBean.setOrder(facesServletProperties.getOrder());
		facesServletServletRegistrationBean.setIgnoreRegistrationFailure(facesServletProperties.isIgnoreRegistrationFailure());

		multipartConfig.ifAvailable(facesServletServletRegistrationBean::setMultipartConfig);

		return facesServletServletRegistrationBean;
	}
}
