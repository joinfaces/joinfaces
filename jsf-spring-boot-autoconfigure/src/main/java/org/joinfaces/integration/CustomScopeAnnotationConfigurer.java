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

import java.lang.annotation.Annotation;
import java.util.LinkedList;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;

/**
 * Add custom JSF CDI scope implementations. Picks up JSF and CDI annotations both on
 * types and method bean declarations.
 *
 * @author Marcelo Fernandes
 * @author Nurettin Yilmaz
 */
@Slf4j
@Getter
@Setter
@RequiredArgsConstructor
public class CustomScopeAnnotationConfigurer implements BeanFactoryPostProcessor, Ordered {

	@Getter
	@Setter
	private int order = Ordered.LOWEST_PRECEDENCE;

	private List<AnnotationToScopeMapping> annotationToScopeMappings;

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory clbf) throws BeansException {
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
				scopeName = deduceScopeName(annDef.getFactoryMethodMetadata());
			}
			else {
				// fallback to type
				scopeName = deduceScopeName(annDef.getMetadata());
			}

			if (scopeName != null) {
				definition.setScope(scopeName);

				log.debug("{} - Scope({})", definition.getBeanClassName(), definition.getScope().toUpperCase());
			}
		}
	}

	protected String deduceScopeName(MethodMetadata factoryMethodMetadata) {
		if (getAnnotationToScopeMappings() == null) {
			return null;
		}

		for (AnnotationToScopeMapping annotationToScopeMapping : getAnnotationToScopeMappings()) {
			if (factoryMethodMetadata.isAnnotated(annotationToScopeMapping.getAnnotation().getName())) {
				return annotationToScopeMapping.getScope();
			}
		}

		return null;
	}

	protected String deduceScopeName(AnnotationMetadata classMetadata) {
		if (classMetadata == null || getAnnotationToScopeMappings() == null) {
			return null;
		}

		for (AnnotationToScopeMapping annotationToScopeMapping : getAnnotationToScopeMappings()) {
			if (classMetadata.hasAnnotation(annotationToScopeMapping.getAnnotation().getName())) {
				return annotationToScopeMapping.getScope();
			}
		}

		return null;
	}

	public void addMapping(Class<? extends Annotation> annotation, String scopeName) {
		addMapping(new AnnotationToScopeMapping(annotation, scopeName));
	}

	public void addMapping(AnnotationToScopeMapping annotationToScopeMapping) {
		if (this.annotationToScopeMappings == null) {
			this.annotationToScopeMappings = new LinkedList<AnnotationToScopeMapping>();
		}

		this.annotationToScopeMappings.add(annotationToScopeMapping);
	}
}
