/*
 * Copyright 2016-2022 the original author or authors.
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

package org.joinfaces.autoconfigure.scopemapping;

import lombok.Data;
import lombok.Getter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.DeprecatedConfigurationProperty;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.core.Ordered;

@Data
@ConfigurationProperties("joinfaces.scope-configurer")
public class ScopeConfigurerProperties {

	/**
	 * Support for JSF Managed Bean @jakarta.faces.bean.xxxScoped annotations.
	 */
	@Deprecated
	@NestedConfigurationProperty
	@Getter(onMethod_ = @DeprecatedConfigurationProperty(reason = "Removed in Faces 4."))
	private final ScopeConfigurer jsf = new ScopeConfigurer();

	/**
	 * Support for JSF {@link jakarta.faces.view.ViewScoped} annotation.
	 */
	@NestedConfigurationProperty
	private final ScopeConfigurer faces = new ScopeConfigurer();

	/**
	 * Support for CDI @javax.enterprise.context.xxxScoped annotations.
	 */
	@NestedConfigurationProperty
	private final ScopeConfigurer cdi = new ScopeConfigurer();

	@Data
	public static class ScopeConfigurer {
		private boolean enabled = true;
		/**
		 * Order of the BeanFactoryPostProcessor.
		 */
		private int order = Ordered.LOWEST_PRECEDENCE;
	}
}
