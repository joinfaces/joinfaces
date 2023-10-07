/*
 * Copyright 2016-2023 the original author or authors.
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

package org.joinfaces.autoconfigure;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import org.joinfaces.ClasspathScanUtil;

import org.springframework.aot.generate.GenerationContext;
import org.springframework.beans.factory.aot.BeanFactoryInitializationAotContribution;
import org.springframework.beans.factory.aot.BeanFactoryInitializationAotProcessor;
import org.springframework.beans.factory.aot.BeanFactoryInitializationCode;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.ClassUtils;

public abstract class FacesAnnotationProviderAotProcessor implements BeanFactoryInitializationAotProcessor {

	@Override
	public BeanFactoryInitializationAotContribution processAheadOfTime(ConfigurableListableBeanFactory beanFactory) {
		if (ClassUtils.isPresent(getSpiClassName(), FacesAnnotationProviderAotProcessor.class.getClassLoader())) {
			return new FacesAnnotationProviderAotContrib();
		}

		return null;
	}

	protected abstract String getSpiClassName();

	class FacesAnnotationProviderAotContrib implements BeanFactoryInitializationAotContribution {

		@Override
		public void applyTo(GenerationContext generationContext, BeanFactoryInitializationCode beanFactoryInitializationCode) {

			String resourceName = "META-INF/joinfaces/" + getSpiClassName() + ".classes";

			try (ScanResult scanResult = new ClassGraph()
				.enableAllInfo()
				.enableExternalClasses()
				.scan()) {
				Map<Class<? extends Annotation>, Set<Class<?>>> annotatedClasses = FacesAnnotationProviderUtil.findAnnotatedClasses(scanResult);
				ClasspathScanUtil.writeClassMap(generationContext, resourceName, annotatedClasses);
			}

		}
	}
}
