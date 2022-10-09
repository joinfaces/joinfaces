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

package org.joinfaces.autoconfigure.myfaces;

import lombok.Data;
import org.joinfaces.autoconfigure.servlet.initparams.ServletContextInitParameter;
import org.joinfaces.autoconfigure.servlet.initparams.ServletContextInitParameterProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration Properties added with MyFaces 2.3.
 * <p>
 * Taken from: https://myfaces.apache.org/core23/myfaces-impl/webconfig.html
 *
 * @author Lars Grefer
 */
@Data
@ConfigurationProperties("joinfaces.myfaces")
public class MyFaces2_3Properties implements ServletContextInitParameterProperties {

	/**
	 * If set false, myfaces won't support ManagedBeans anymore.
	 * ManagedBeans are deprecated in JSF 2.3
	 * Default value is true.
	 *
	 * @deprecated Removed in MyFaces 4.
	 */
	@Deprecated
	@ServletContextInitParameter(MyFacesProperties.PREFFIX + "SUPPORT_MANAGED_BEANS")
	private Boolean supportManagedBeans = false;

	/**
	 * When CLEAR_INPUT_WHEN_SUBMITTED_VALUE_IS_NULL_OR_EMPTY is enabled, input fields will be cleared when null or empty values are submitted.
	 * When disabled, and INTERPRET_EMPTY_STRING_SUBMITTED_VALUES_AS_NULL is enabled, submitting null or empty values will cause the previous model value to be restored to the input field.
	 */
	@ServletContextInitParameter("org.apache.myfaces.CLEAR_INPUT_WHEN_SUBMITTED_VALUE_IS_NULL_OR_EMPTY")
	private Boolean clearInputWhenSubmittedValueIsNullOrEmpty;

	/**
	 * JSP Suffix.
	 *
	 * @deprecated Removed in MyFaces 4.
	 */
	@Deprecated
	@ServletContextInitParameter(MyFacesProperties.PREFFIX + "JSP_SUFFIX")
	private String jspSuffix;

	/**
	 * This parameter enables automatic extensionless mapping for all JSF views.
	 */
	@ServletContextInitParameter(MyFacesProperties.PREFFIX + "AUTOMATIC_EXTENSIONLESS_MAPPINg")
	private Boolean automaticExtensionlessMapping;
}
