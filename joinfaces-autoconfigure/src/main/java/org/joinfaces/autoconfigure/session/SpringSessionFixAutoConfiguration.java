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

package org.joinfaces.autoconfigure.session;

import java.util.EnumSet;
import java.util.stream.Collectors;

import jakarta.servlet.DispatcherType;

import org.joinfaces.session.SpringSessionFixFilter;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.boot.autoconfigure.session.SessionProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;
import org.springframework.session.SessionRepository;
import org.springframework.session.web.http.SessionRepositoryFilter;

/**
 * {@link org.springframework.boot.autoconfigure.EnableAutoConfiguration Auto configuration}
 * for Spring Session.
 *
 * @author Lars Grefer
 * @see org.springframework.boot.autoconfigure.session.SessionRepositoryFilterConfiguration
 */
@AutoConfiguration(after = SessionAutoConfiguration.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@EnableConfigurationProperties(SessionProperties.class)
@ConditionalOnClass(SessionRepository.class)
public class SpringSessionFixAutoConfiguration {

	@Bean
	@ConditionalOnBean(SessionRepositoryFilter.class)
	public FilterRegistrationBean<SpringSessionFixFilter> springSessionFixFilterRegistrationBean(
			SessionProperties sessionProperties
	) {
		FilterRegistrationBean<SpringSessionFixFilter> registrationBean = new FilterRegistrationBean<>(new SpringSessionFixFilter());
		registrationBean.setOrder(sessionProperties.getServlet().getFilterOrder() + 1);
		registrationBean.setDispatcherTypes(getDispatcherTypes(sessionProperties));
		return registrationBean;
	}

	@Nullable
	private EnumSet<DispatcherType> getDispatcherTypes(SessionProperties sessionProperties) {
		SessionProperties.Servlet servletProperties = sessionProperties.getServlet();
		if (servletProperties.getFilterDispatcherTypes() == null) {
			return null;
		}
		return servletProperties.getFilterDispatcherTypes().stream().map((type) -> DispatcherType.valueOf(type.name()))
				.collect(Collectors.collectingAndThen(Collectors.toSet(), EnumSet::copyOf));
	}
}
