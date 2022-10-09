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

package org.joinfaces.autoconfigure.scopemapping;

import org.joinfaces.viewscope.ViewScope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.web.context.WebApplicationContext;

/**
 * {@link org.springframework.boot.autoconfigure.EnableAutoConfiguration Auto configuration}
 * for {@code javax.faces.bean.*Scoped} annotations support.
 *
 * @author Diego Diez
 * @author Lars Grefer
 */
@Deprecated
@AutoConfiguration
@ConditionalOnClass(name = "jakarta.faces.bean.RequestScoped")
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class Jsf3ScopeAnnotationsAutoConfiguration {

	@Bean
	@ConditionalOnProperty(value = "joinfaces.scope-configurer.jsf.enabled", havingValue = "true", matchIfMissing = true)
	public static CustomScopeAnnotationConfigurer jsf3ScopeAnnotationsConfigurer(Environment environment) throws ClassNotFoundException {
		CustomScopeAnnotationConfigurer scopeAnnotationConfigurer = new CustomScopeAnnotationConfigurer();

		scopeAnnotationConfigurer.setOrder(environment.getProperty("joinfaces.scope-configurer.jsf.order", Integer.class, Ordered.LOWEST_PRECEDENCE));

		scopeAnnotationConfigurer.addMapping("jakarta.faces.bean.NoneScoped", ConfigurableBeanFactory.SCOPE_PROTOTYPE);
		scopeAnnotationConfigurer.addMapping("jakarta.faces.bean.RequestScoped", WebApplicationContext.SCOPE_REQUEST);
		scopeAnnotationConfigurer.addMapping("jakarta.faces.bean.ViewScoped", ViewScope.SCOPE_VIEW);
		scopeAnnotationConfigurer.addMapping("jakarta.faces.bean.SessionScoped", WebApplicationContext.SCOPE_SESSION);
		scopeAnnotationConfigurer.addMapping("jakarta.faces.bean.ApplicationScoped", WebApplicationContext.SCOPE_APPLICATION);

		return scopeAnnotationConfigurer;
	}

}
