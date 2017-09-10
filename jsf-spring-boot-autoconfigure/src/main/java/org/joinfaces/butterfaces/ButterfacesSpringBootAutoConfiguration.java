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

package org.joinfaces.butterfaces;

import lombok.extern.slf4j.Slf4j;
import net.bootsfaces.C;
import org.joinfaces.bootsfaces.BootsfacesProperties;
import org.joinfaces.bootsfaces.BootsfacesSpringBootAutoConfiguration;
import org.joinfaces.javaxfaces.JavaxFacesSpringBootAutoConfiguration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot Auto Configuration of ButterFaces.
 *
 * @author Marcelo Fernandes
 */
@Configuration
@EnableConfigurationProperties(ButterfacesProperties.class)
@ConditionalOnClass(name = "de.larmic.butterfaces.util.ReflectionUtil")
@AutoConfigureBefore(JavaxFacesSpringBootAutoConfiguration.class)
@ConditionalOnWebApplication
@Slf4j
public class ButterfacesSpringBootAutoConfiguration {

	/**
	 * Special auto configuration for butterfaces and bootsfaces in combination.
	 *
	 * @author Lars Grefer
	 * @see <a href="https://github.com/ButterFaces/bootsfaces-integration/blob/6e9d45978590fa72361cf3c98bec77d863f02aea/README.md">ButterFaces/bootsfaces-integration</a>
	 */
	@Configuration
	@ConditionalOnClass(C.class)
	@AutoConfigureBefore(BootsfacesSpringBootAutoConfiguration.class)
	public static class ButterfacesBootsfacesAutoConfiguration {

		@Bean
		public BeanPostProcessor butterfacesBootsfacesPropertiesPostProcessor() {
			return new BootsfacesPropertiesCustomizer();
		}

		/**
		 * Configures Bootsfaces to work with Butterfaces.
		 *
		 * @author Lars Grefer
		 * @see description at https://github.com/ButterFaces/bootsfaces-integration/blob/6e9d45978590fa72361cf3c98bec77d863f02aea/README.md
		 */
		static class BootsfacesPropertiesCustomizer implements BeanPostProcessor {

			@Override
			public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
				return bean;
			}

			@Override
			public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

				if (bean instanceof BootsfacesProperties) {
					BootsfacesProperties properties = (BootsfacesProperties) bean;
					String getJqueryFromCdn = properties.getGetJqueryFromCdn();
					if (getJqueryFromCdn == null || getJqueryFromCdn.equalsIgnoreCase("false")) {
						log.info("Setting 'net.bootsfaces.get_jquery_from_cdn' to 'true'");
						log.info("See: https://github.com/ButterFaces/bootsfaces-integration/blob/6e9d45978590fa72361cf3c98bec77d863f02aea/README.md");

						properties.setGetJqueryFromCdn("true");
					}
				}

				return bean;
			}
		}
	}
}
