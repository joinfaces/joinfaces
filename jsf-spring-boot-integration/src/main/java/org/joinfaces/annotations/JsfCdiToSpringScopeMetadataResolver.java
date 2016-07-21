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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationScopeMetadataResolver;
import org.springframework.context.annotation.ScopeMetadata;

/**
 * Spring Metadata Resolver to support cdi and jsf annotations.
 * @author Marcelo Fernandes
 */
public class JsfCdiToSpringScopeMetadataResolver
	extends AnnotationScopeMetadataResolver {

	private static final Logger logger = LoggerFactory
		.getLogger(JsfCdiToSpringScopeMetadataResolver.class);

	/**
	 * Discover spring scope using bean definition.
	 *
	 * @param definition bean definition
	 * @return scope metadata
	 */
	@Override
	public ScopeMetadata resolveScopeMetadata(BeanDefinition definition) {
		ScopeMetadata metadata = new ScopeMetadata();
		if (definition instanceof AnnotatedBeanDefinition) {
			AnnotatedBeanDefinition annDef = (AnnotatedBeanDefinition) definition;

			String scopeName = JsfCdiToSpring.scopeName(annDef.getMetadata().getAnnotationTypes());
			if (scopeName != null) {
				metadata.setScopeName(scopeName);

				logger.debug(definition.getBeanClassName()
					+ " - Scope(" + metadata.getScopeName().toUpperCase()
					+ ") - " + metadata.getScopedProxyMode().toString().toUpperCase());
			}
			else {
				// do the regular Spring stuff..
				metadata = super.resolveScopeMetadata(definition);
			}
		}

		return metadata;
	}
}
