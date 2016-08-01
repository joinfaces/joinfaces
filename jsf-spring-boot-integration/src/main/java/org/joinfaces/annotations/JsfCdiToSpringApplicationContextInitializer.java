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

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Jsf Cdi to Spring application context initializer.
 * @author Marcelo Fernandes
 */
public class JsfCdiToSpringApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	/**
	 * Add scope metadata resolver, bean name generator and bean factory post
	 * processor to enable jsf cdi annotations in spring.
	 */
	@Override
	public void initialize(ConfigurableApplicationContext c) {
		c.addBeanFactoryPostProcessor(new JsfCdiToSpringBeanFactoryPostProcessor());
	}

}
