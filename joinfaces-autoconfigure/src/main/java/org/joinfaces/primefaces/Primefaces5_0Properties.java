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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joinfaces.configuration.NestedProperty;
import org.joinfaces.configuration.ServletContextInitParameter;
import org.joinfaces.configuration.ServletContextInitParameterConfigurationProperties;
import org.primefaces.cache.CacheProvider;
import org.primefaces.util.Constants;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for Primefaces 5.0.
 *
 * Values taken from http://www.primefaces.org/docs/guide/primefaces_user_guide_5_0.pdf
 * pages 13, 42, 55.
 *
 * @author Lars Grefer
 */
@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties("jsf.primefaces")
public class Primefaces5_0Properties implements ServletContextInitParameterConfigurationProperties {

	@NestedProperty
	private Mobile mobile = new Mobile();

	@ServletContextInitParameter(Constants.ContextParams.AUTO_UPDATE)
	private String autoUpdate;

	/**
	 * Transforms bean validation metadata to html attributes.
	 */
	@ServletContextInitParameter(Constants.ContextParams.TRANSFORM_METADATA)
	private boolean transformMetadata = false;

	/**
	 * A cache store is required to use the cache component, two different providers are supported as cache implementation;
	 * EHCache and Hazelcast.
	 */
	@ServletContextInitParameter(Constants.ContextParams.CACHE_PROVIDER)
	private Class<? extends CacheProvider> cacheProvider;

	/**
	 * Nested property class for {@literal primefaces.mobile}.
	 *
	 * @author Lars Grefer
	 */
	@Getter
	@Setter
	@NoArgsConstructor
	public static class Mobile {
		/**
		 * Theme of the mobile application.
		 */
		@ServletContextInitParameter(Constants.ContextParams.MOBILE_THEME)
		private String theme;
	}
}
