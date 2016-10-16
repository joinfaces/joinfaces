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
import org.joinfaces.javaxfaces.JavaxFacesSpringBootAutoConfiguration;
import org.primefaces.webapp.filter.FileUploadFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Spring Boot Auto Configuration of PrimeFaces.
 * @author Marcelo Fernandes
 * @author Nurettin Yilmaz
 */
@Configuration
@EnableConfigurationProperties(PrimefacesProperties.class)
@ConditionalOnClass(name = "org.primefaces.application.DialogViewHandler")
@AutoConfigureBefore(JavaxFacesSpringBootAutoConfiguration.class)
@ConditionalOnWebApplication
public class PrimefacesSpringBootAutoConfiguration {

	@Autowired
	private PrimefacesProperties primefacesProperties;

	@Bean
	public ServletContextInitializer primefacesServletContextInitializer() {
		return new PrimefacesServletContextInitializer(this.primefacesProperties);
	}

	/**
	 * Additional config for embedded jetty in spring boot, since spring boot
	 * does not pick up {@link FacesServlet} as a multipart servlet
	 * as a result multipart requests are not processed without this filter.
	 *
	 * Spring's {@link DispatcherServlet} offers {@link StandardServletMultipartResolver}
	 * But the request never reaches to this resolver.
	 */
	@Bean
	public FileUploadFilter primefacesFileUploadFilter() {
		return new FileUploadFilter();
	}

}
