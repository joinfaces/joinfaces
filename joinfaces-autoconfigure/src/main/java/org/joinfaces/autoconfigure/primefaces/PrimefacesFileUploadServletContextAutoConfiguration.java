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

import jakarta.faces.webapp.FacesServlet;

import org.joinfaces.autoconfigure.faces.JakartaFaces3AutoConfiguration;
import org.primefaces.webapp.MultipartRequest;
import org.primefaces.webapp.filter.FileUploadFilter;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.RegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@link FileUploadFilter} bean is needed for requests to be wrapped as a
 * {@link MultipartRequest}.
 *
 * @author Nurettin Yilmaz
 * @author Lars Grefer
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(FileUploadFilter.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@AutoConfigureAfter(JakartaFaces3AutoConfiguration.class)
@EnableConfigurationProperties(PrimefacesFileUploadFilterProperties.class)
public class PrimefacesFileUploadServletContextAutoConfiguration {

	/**
	 * File upload filter is required only if commons fileupload is chosen.
	 *
	 * @param uploadFilterProperties       {@link ConfigurationProperties} object for the {@link FileUploadFilter}.
	 * @param facesServletRegistrationBean {@link RegistrationBean} for the {@link FacesServlet}, if available.
	 * @return file upload filter
	 */
	@Bean
	@ConditionalOnProperty(value = "joinfaces.primefaces.uploader", havingValue = "commons")
	@ConditionalOnClass(name = "org.apache.commons.fileupload.servlet.ServletFileUpload")
	public FilterRegistrationBean<FileUploadFilter> primefacesFileUploadFilterRegistrationBean(
			PrimefacesFileUploadFilterProperties uploadFilterProperties,
			ObjectProvider<ServletRegistrationBean<FacesServlet>> facesServletRegistrationBean
	) {
		FilterRegistrationBean<FileUploadFilter> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(new FileUploadFilter());
		registrationBean.setName(uploadFilterProperties.getName());
		registrationBean.setOrder(uploadFilterProperties.getOrder());

		facesServletRegistrationBean.ifAvailable(registrationBean::addServletRegistrationBeans);

		if (uploadFilterProperties.getThresholdSize() != null) {
			registrationBean.addInitParameter("thresholdSize", String.valueOf(uploadFilterProperties.getThresholdSize().toBytes()));
		}

		if (uploadFilterProperties.getUploadDirectory() != null) {
			registrationBean.addInitParameter("uploadDirectory", uploadFilterProperties.getUploadDirectory());
		}

		return registrationBean;
	}
}
