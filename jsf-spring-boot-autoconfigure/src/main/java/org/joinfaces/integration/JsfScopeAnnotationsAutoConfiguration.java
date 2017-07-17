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

package org.joinfaces.integration;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.NoneScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@Configuration
@ConditionalOnClass(RequestScoped.class)
public class JsfScopeAnnotationsAutoConfiguration {

	@Bean
	@ConditionalOnProperty(value = "jsf.scope-configurer.jsf.enabled", havingValue = "true", matchIfMissing = true)
	public static BeanFactoryPostProcessor jsfScopeAnnotationsConfigurer(Environment environment) {
		CustomScopeAnnotationConfigurer scopeAnnotationConfigurer = new CustomScopeAnnotationConfigurer();

		scopeAnnotationConfigurer.setOrder(environment.getProperty("jsf.scope-configurer.jsf.order", Integer.class, Ordered.LOWEST_PRECEDENCE));

		scopeAnnotationConfigurer.addMapping(NoneScoped.class, ConfigurableBeanFactory.SCOPE_PROTOTYPE);
		scopeAnnotationConfigurer.addMapping(RequestScoped.class, WebApplicationContext.SCOPE_REQUEST);
		scopeAnnotationConfigurer.addMapping(javax.faces.bean.ViewScoped.class, ViewScope.SCOPE_VIEW);
		scopeAnnotationConfigurer.addMapping(javax.faces.view.ViewScoped.class, ViewScope.SCOPE_VIEW);
		scopeAnnotationConfigurer.addMapping(SessionScoped.class, WebApplicationContext.SCOPE_SESSION);
		scopeAnnotationConfigurer.addMapping(ApplicationScoped.class, WebApplicationContext.SCOPE_APPLICATION);

		return scopeAnnotationConfigurer;
	}

}
