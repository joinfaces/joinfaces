/*
 * Copyright 2016-2017 the original author or authors.
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

package org.joinfaces.autoconfigure.rewrite;


import javax.inject.Inject;

import org.ocpsoft.rewrite.servlet.RewriteFilter;
import org.ocpsoft.rewrite.servlet.impl.RewriteServletContextListener;
import org.ocpsoft.rewrite.servlet.impl.RewriteServletRequestListener;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * Spring Boot Auto Configuration of Rewrite.
 *
 * @author Marcelo Fernandes
 */
@Configuration
@EnableConfigurationProperties({RewriteFilterProperties.class, RewriteProperties.class})
@ConditionalOnClass(RewriteFilter.class)
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class RewriteAutoConfiguration {

	@Inject
	private RewriteFilterProperties rewriteFilterProperties;

	@Bean
	public ServletListenerRegistrationBean<RewriteServletRequestListener> rewriteServletRequestListener() {
		ServletListenerRegistrationBean<RewriteServletRequestListener> result = new ServletListenerRegistrationBean<RewriteServletRequestListener>();
		result.setListener(new RewriteServletRequestListener());
		return result;
	}

	@Bean
	public ServletListenerRegistrationBean<RewriteServletContextListener> rewriteServletContextListener() {
		ServletListenerRegistrationBean<RewriteServletContextListener> result = new ServletListenerRegistrationBean<>();
		result.setListener(new RewriteServletContextListener());
		return result;
	}

	/**
	 * See https://www.ocpsoft.org/support/topic/integration-with-spring-boot-not-working/ .
	 * @return rewrite filter registration bean
	 */
	@DependsOn("applicationContextProvider")
	@Bean
	public FilterRegistrationBean<RewriteFilter> rewriteFilterRegistrationBean() {
		FilterRegistrationBean<RewriteFilter> registration = new FilterRegistrationBean<>();
		registration.setFilter(new RewriteFilter());
		registration.setDispatcherTypes(this.rewriteFilterProperties.getDispatcherTypes());
		registration.addUrlPatterns(this.rewriteFilterProperties.getUrlPatterns().toArray(new String[this.rewriteFilterProperties.getUrlPatterns().size()]));
		registration.setEnabled(this.rewriteFilterProperties.isEnabled());
		registration.setAsyncSupported(this.rewriteFilterProperties.isAsyncSupported());
		registration.setOrder(this.rewriteFilterProperties.getOrder());
		return registration;
	}

	@Bean
	public ApplicationContextProvider applicationContextProvider() {
		return new ApplicationContextProvider();
	}
}
