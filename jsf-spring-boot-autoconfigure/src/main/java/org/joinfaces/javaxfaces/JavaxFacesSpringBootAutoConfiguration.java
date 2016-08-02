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

package org.joinfaces.javaxfaces;

import javax.faces.application.ProjectStage;
import javax.faces.application.ResourceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * Auto configuration for Standard Javax Faces Properties.
 *
 * @author Marcelo Fernandes
 * @author Nurettin Yilmaz
 */
@Configuration
@EnableConfigurationProperties({JavaxFacesProperties.class})
@ConditionalOnClass(ProjectStage.class)
@ConditionalOnWebApplication
public class JavaxFacesSpringBootAutoConfiguration {

	@Autowired
	private JavaxFacesProperties javaxFacesProperties;

	@Bean
	public ServletContextInitializer javaxFacesServletContextInitializer() {
		return new JavaxFacesServletContextInitializer(this.javaxFacesProperties);
	}

	/**
	 * Allows JSF standard resource path through web security (ie in the case of using spring-security is enabled.)
    */
	@Configuration
	@ConditionalOnClass({EnableWebSecurity.class, AuthenticationEntryPoint.class})
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public static class JavaxFacesResourceIgnoringPathsSecurityConfiguration implements WebSecurityConfigurer<WebSecurity> {

		@Override
		public void init(WebSecurity builder) throws Exception { }

		@Override
		public void configure(WebSecurity builder) throws Exception {
			builder
				.ignoring()
				.antMatchers(ResourceHandler.RESOURCE_IDENTIFIER + "/**");
		}

	}
}
