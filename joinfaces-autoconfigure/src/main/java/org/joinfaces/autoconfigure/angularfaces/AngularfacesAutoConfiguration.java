/*
 * Copyright 2016-2016 the original author or authors.
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

package org.joinfaces.autoconfigure.angularfaces;

import java.util.ArrayList;

import de.beyondjava.angularFaces.core.ELTools;
import de.beyondjava.angularFaces.core.tagTransformer.AngularTagDecorator;
import org.joinfaces.autoconfigure.javaxfaces.JavaxFaces2_0Properties;
import org.joinfaces.autoconfigure.javaxfaces.JavaxFacesAutoConfiguration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

/**
 * Spring Boot Auto Configuration of AngularFaces.
 *
 * @author Marcelo Fernandes
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(AngularfacesProperties.class)
@ConditionalOnClass(ELTools.class)
@AutoConfigureBefore(JavaxFacesAutoConfiguration.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class AngularfacesAutoConfiguration {

	@Bean
	public BeanPostProcessor angularfacesJavaxFacesPropertiesPostProcessor() {
		return new JavaxFacesPropertiesPostProcessor();
	}

	/**
	 * Adds the {@link AngularTagDecorator} to {@link JavaxFaces2_0Properties#faceletsDecorators}.
	 *
	 * @author Lars Grefer
	 */
	static class JavaxFacesPropertiesPostProcessor implements BeanPostProcessor {

		@Override
		public Object postProcessBeforeInitialization(@Nullable Object bean, @Nullable String beanName) throws BeansException {
			if (bean instanceof JavaxFaces2_0Properties) {
				JavaxFaces2_0Properties properties = (JavaxFaces2_0Properties) bean;
				if (properties.getFaceletsDecorators() == null) {
					properties.setFaceletsDecorators(new ArrayList<>(1));
				}

				if (!properties.getFaceletsDecorators().contains(AngularTagDecorator.class)) {
					properties.getFaceletsDecorators().add(AngularTagDecorator.class);
				}
			}
			return bean;
		}
	}
}
