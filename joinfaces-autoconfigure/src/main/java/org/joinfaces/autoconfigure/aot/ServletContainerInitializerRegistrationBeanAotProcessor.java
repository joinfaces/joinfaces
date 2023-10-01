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

package org.joinfaces.autoconfigure.aot;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joinfaces.servlet.ServletContainerInitializerRegistrationBean;

import org.springframework.aot.generate.GenerationContext;
import org.springframework.aot.hint.TypeReference;
import org.springframework.beans.factory.aot.BeanFactoryInitializationAotContribution;
import org.springframework.beans.factory.aot.BeanFactoryInitializationAotProcessor;
import org.springframework.beans.factory.aot.BeanFactoryInitializationCode;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.CollectionUtils;

/**
 * AOT Processor which performs the ClasspathScan for {@link ServletContainerInitializerRegistrationBean}.
 *
 * @author Lars Grefer
 */
@Slf4j
public class ServletContainerInitializerRegistrationBeanAotProcessor implements BeanFactoryInitializationAotProcessor {

	@Override
	public BeanFactoryInitializationAotContribution processAheadOfTime(ConfigurableListableBeanFactory beanFactory) {
		Collection<ServletContainerInitializerRegistrationBean> values = beanFactory.getBeansOfType(ServletContainerInitializerRegistrationBean.class).values();

		return new Contrib(values);
	}

	@RequiredArgsConstructor
	public static class Contrib implements BeanFactoryInitializationAotContribution {

		private final Collection<ServletContainerInitializerRegistrationBean> beans;

		@Override
		public void applyTo(GenerationContext generationContext, BeanFactoryInitializationCode beanFactoryInitializationCode) {

			for (ServletContainerInitializerRegistrationBean<?> bean : beans) {
				Set<Class<?>> classes = bean.performClasspathScan();

				if (CollectionUtils.isEmpty(classes)) {
					continue;
				}

				String resourceFilePath = "META-INF/joinfaces/" + bean.getServletContainerInitializerClass().getName() + ".classes";

				generationContext.getRuntimeHints().resources().registerPattern(resourceFilePath);

				List<String> sortedClassNames = classes.stream()
					.map(Class::getName)
					.sorted(String::compareTo)
					.toList();

				generationContext.getGeneratedFiles().addResourceFile(resourceFilePath, appendable -> {
					for (String className : sortedClassNames) {
						appendable.append(className);
						appendable.append("\n");
					}
				});

				for (String className : sortedClassNames) {
					generationContext.getRuntimeHints().reflection().registerType(TypeReference.of(className));
				}
			}

		}
	}
}
