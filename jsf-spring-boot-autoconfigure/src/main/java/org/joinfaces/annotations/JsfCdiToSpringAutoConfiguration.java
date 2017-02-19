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

package org.joinfaces.annotations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;

/**
 * Auto-configuration for JSF-CDI-Spring integration.
 * @author Diego Diez
 */
@Configuration
public class JsfCdiToSpringAutoConfiguration {

	/**
	 * Property key to customize the order for JsfCdiToSpringBeanFactoryPostProcessor.
	 */
	public static final String JSF_CDI_CONFIGURATION_ORDER = "jsf.cdi.configurationOrder";

	/**
	 * BeanFactoryPostProcessor to enable jsf cdi annotations in spring.
	 * @param environment environment to load de property to set the order
	 * @return beanFactoryPostProcessor to enable jsf-cdi annotations
	 */
	@Bean
	public static JsfCdiToSpringBeanFactoryPostProcessor jsfCdiToSpringBeanFactoryPostProcessor(Environment environment) {
		JsfCdiToSpringBeanFactoryPostProcessor bfpp = new JsfCdiToSpringBeanFactoryPostProcessor();
		bfpp.setOrder(environment.getProperty(JSF_CDI_CONFIGURATION_ORDER, Integer.class, Ordered.LOWEST_PRECEDENCE));
		return bfpp;
	}

}
