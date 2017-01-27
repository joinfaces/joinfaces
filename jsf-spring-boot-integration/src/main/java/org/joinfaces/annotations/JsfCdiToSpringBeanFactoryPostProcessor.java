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

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;

/**
 * Add custom JSF CDI scope implementations. Picks up JSF and CDI annotations both on
 * types and method bean declarations.
 *
 * @author Marcelo Fernandes
 * @author Nurettin Yilmaz
 */
@Slf4j
public class JsfCdiToSpringBeanFactoryPostProcessor implements BeanFactoryPostProcessor, Ordered {

	private int order = Ordered.LOWEST_PRECEDENCE;

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory clbf) throws BeansException {
		clbf.registerScope("view", new ViewScope());

		for (String beanName : clbf.getBeanDefinitionNames()) {
			BeanDefinition definition = clbf.getBeanDefinition(beanName);
			registerJsfCdiToSpring(definition);
		}
	}

	/**
	 * Checks how is bean defined and deduces scope name from JSF CDI annotations.
	 *
	 * @param definition beanDefinition
	 */
	private void registerJsfCdiToSpring(BeanDefinition definition) {

		if (definition instanceof AnnotatedBeanDefinition) {
			AnnotatedBeanDefinition annDef = (AnnotatedBeanDefinition) definition;

			String scopeName = null;
			// firstly check whether bean is defined via configuration
			if (annDef.getFactoryMethodMetadata() != null) {
				scopeName = JsfCdiToSpring.deduceScopeName(annDef.getFactoryMethodMetadata());
			}
			else {
				// fallback to type
				scopeName = JsfCdiToSpring.deduceScopeName(annDef.getMetadata().getAnnotationTypes());
			}

			if (scopeName != null) {
				definition.setScope(scopeName);

				log.debug(definition.getBeanClassName()
					+ " - Scope(" + definition.getScope().toUpperCase()
					+ ")");
			}
		}
	}

	@Override
	public int getOrder() {
		return this.order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
}
