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

package org.joinfaces.javaxfaces;

import javax.faces.component.UIComponent;

import lombok.Getter;
import lombok.Setter;
import org.joinfaces.configuration.ServletContextInitParameter;
import org.joinfaces.configuration.ServletContextInitParameterConfigurationProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * {@link ConfigurationProperties Configuration properties} for JSF 2.1.
 *
 * @author Marcelo Fernandes
 * @author Lars Grefer
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "jsf")
public class JavaxFaces2_1Properties implements ServletContextInitParameterConfigurationProperties {

	/**
	 * <p class="changed_added_2_1">The <code>ServletContext</code> init
	 * parameter consulted by the <code>UIComponent</code> to tell whether or
	 * not the {@link #CURRENT_COMPONENT} and
	 * {@link #CURRENT_COMPOSITE_COMPONENT} attribute keys should be honored as
	 * specified.</p>
	 *
	 * <p>
	 * If this parameter is not specified, or is set to false, the contract
	 * specified by the {@link #CURRENT_COMPONENT} and
	 * {@link #CURRENT_COMPOSITE_COMPONENT} method is not honored. If this
	 * parameter is set to true, the contract is honored.</p>
	 */
	@ServletContextInitParameter(UIComponent.HONOR_CURRENT_COMPONENT_ATTRIBUTES_PARAM_NAME)
	private Boolean honorCurrentComponentAttributes;
}
