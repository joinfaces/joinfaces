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
import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.annotation.MultipartConfig;

import org.joinfaces.javaxfaces.JavaxFacesSpringBootAutoConfiguration;
import org.primefaces.webapp.MultipartRequest;
import org.primefaces.webapp.filter.FileUploadFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.web.MultipartProperties;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Jetty does not resolve part parameters without associating a multipart config
 * to corresponding servlet. This configuration needed to manually add that
 * configuration and native file upload of JSF can work.
 *
 * This configuration is also possible with using jetty-annotations module.
 * Since {@link FacesServlet} is annotated with {@link MultipartConfig}.
 *
 * {@link FileUploadFilter} bean is needed for requests to be wrapped as a
 * {@link MultipartRequest}.
 *
 * Finally multipart configuration properties are borrowed and set up from
 * spring's {@link MultipartProperties}
 *
 * @author Nurettin Yilmaz
 * @see PrimefacesFileUploadServletContextInitializer
 */
@ConditionalOnClass(name = {"org.primefaces.webapp.MultipartRequest"})
@Configuration
@AutoConfigureAfter(JavaxFacesSpringBootAutoConfiguration.class)
public class PrimefacesFileUploadServletContextAutoConfiguration {

	@Autowired
	private MultipartConfigElement multipartConfigElement;

	/**
	 * PrimefacesFileUploadServletContextInitializer for native uploader,
	 * since {@link FileUploadFilter} suffices for commons file uploader.
	 * @return primefaces file upload servlet context initializer
	 */
	@ConditionalOnExpression("'${jsf.primefaces.uploader}' != 'commons'")
	@Bean
	public ServletContextInitializer primefacesFileUploadServletContextInitializer() {
		return new PrimefacesFileUploadServletContextInitializer(this.multipartConfigElement);
	}

	/**
	 * File upload filter is required only if commons fileupload is chosen.
	 * @return file upload filter
	 */
	@ConditionalOnExpression("'${jsf.primefaces.uploader}' == 'commons'")
	@Bean
	public Filter fileUploadFilter() {
		return new FileUploadFilter();
	}
}
