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

package com.github.persapiens.jsfboot.annotations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

/**
 * Spring Bean Name Generator to support cdi and jsf annotations.
 * @author Marcelo Fernandes
 */
public class JsfCdiToSpringBeanNameGenerator
	extends AnnotationBeanNameGenerator {

	private static final Logger logger = LoggerFactory
		.getLogger(JsfCdiToSpringBeanNameGenerator.class);

	@Override
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
		String result = super.generateBeanName(definition, registry);

		if (definition instanceof AnnotatedBeanDefinition) {
			AnnotatedBeanDefinition annDef = (AnnotatedBeanDefinition) definition;

			String scopeName = JsfCdiToSpring.scopeName(annDef.getMetadata().getAnnotationTypes());
			if (scopeName != null) {
				definition.setScope(scopeName);

				logger.debug(definition.getBeanClassName()
					+ " - Scope(" + definition.getScope().toUpperCase()
					+ ")");
			}
		}

		return result;
	}
}
