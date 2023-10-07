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

package org.joinfaces.autoconfigure.mojarra;

import com.sun.faces.config.ConfigureListener;
import com.sun.faces.config.FacesInitializer;
import com.sun.faces.config.FacesInitializer2;
import org.joinfaces.autoconfigure.FacesImplementationAutoConfiguration;
import org.joinfaces.autoconfigure.faces.JakartaFaces3AutoConfiguration;
import org.joinfaces.servlet.ServletContainerInitializerRegistrationBean;
import org.joinfaces.servlet.TldListenerRegistrationBean;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportRuntimeHints;

/**
 * Spring Boot Auto Configuration for Mojarra.
 *
 * @author Marcelo Fernandes
 */
@AutoConfiguration(before = WebMvcAutoConfiguration.class, after = JakartaFaces3AutoConfiguration.class)
@EnableConfigurationProperties(MojarraProperties.class)
@ConditionalOnClass(FacesInitializer.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnMissingBean(FacesImplementationAutoConfiguration.class)
@ImportRuntimeHints(MojarraRuntimeHintsRegistrar.class)
public class MojarraAutoConfiguration implements FacesImplementationAutoConfiguration {

	@Bean
	public ServletContainerInitializerRegistrationBean<FacesInitializer> mojarraServletContainerInitializer() {
		return new MojarraInitializerRegistrationBean();
	}

	@Bean
	@ConditionalOnClass(FacesInitializer2.class)
	public ServletContainerInitializerRegistrationBean<FacesInitializer2> mojarraServletContainerInitializer2() {
		return new ServletContainerInitializerRegistrationBean<>(FacesInitializer2.class);
	}

	/**
	 * {@link TldListenerRegistrationBean} for the 'jsf_core.tld' of Mojarra 3.
	 *
	 * @return The {@link TldListenerRegistrationBean}.
	 * @deprecated Not needed for Mojarra 4+.
	 */
	@Bean
	@ConditionalOnResource(resources = "classpath:/META-INF/jsf_core.tld")
	public TldListenerRegistrationBean mojarraTldListenerRegistrationBean() {
		return TldListenerRegistrationBean.builder()
				.listener(ConfigureListener.class)
				.build();
	}
}
