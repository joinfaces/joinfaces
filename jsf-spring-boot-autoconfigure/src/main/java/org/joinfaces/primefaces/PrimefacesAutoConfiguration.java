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

package org.joinfaces.primefaces;

import org.joinfaces.javaxfaces.JavaxFacesSpringBootAutoConfiguration;
import org.primefaces.cache.CacheProvider;
import org.primefaces.component.captcha.Captcha;
import org.primefaces.util.Constants;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot Auto Configuration for Primefaces.
 *
 * @author Marcelo Fernandes
 * @author Lars Grefer
 */
@Configuration
@ConditionalOnClass(Constants.class)
@ConditionalOnWebApplication
@AutoConfigureBefore(JavaxFacesSpringBootAutoConfiguration.class)
public class PrimefacesAutoConfiguration {

	/**
	 * Auto Configuration for Primefaces 4.0.
	 */
	@Configuration
	@EnableConfigurationProperties(Primefaces4_0Properties.class)
	@ConditionalOnClass(Captcha.class)
	public static class Primefaces4_0AutoConfiguration {
	}

	/**
	 * Auto Configuration for Primefaces 5.0.
	 */
	@Configuration
	@EnableConfigurationProperties(Primefaces5_0Properties.class)
	@ConditionalOnClass(CacheProvider.class)
	public static class Primefaces5_0AutoConfiguration {
	}

	/**
	 * Auto Configuration for Primefaces 5.1.
	 */
	@Configuration
	@EnableConfigurationProperties(Primefaces5_1Properties.class)
	public static class Primefaces5_1AutoConfiguration {
	}

	/**
	 * Auto Configuration for Primefaces 5.2+.
	 */
	@Configuration
	@EnableConfigurationProperties(Primefaces5_2Properties.class)
	public static class Primefaces5_2AutoConfiguration {
	}

	/**
	 * Auto Configuration for Primefaces 6+.
	 */
	@Configuration
	@EnableConfigurationProperties(Primefaces6_0Properties.class)
	public static class Primefaces6_0AutoConfiguration {
	}
}
