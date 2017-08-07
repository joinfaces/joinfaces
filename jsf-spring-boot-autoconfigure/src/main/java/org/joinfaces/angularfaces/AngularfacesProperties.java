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

package org.joinfaces.angularfaces;

import lombok.Getter;
import lombok.Setter;
import org.joinfaces.configuration.ServletContextInitParameter;
import org.joinfaces.configuration.ServletContextInitParameterConfigurationProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties of AngularFaces.
 * Taken from https://github.com/stephanrauh/AngularFaces
 * @author Marcelo Fernandes
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "jsf.angularfaces")
public class AngularfacesProperties implements ServletContextInitParameterConfigurationProperties {

	static final String PREFIX = "AngularFaces.";

	@ServletContextInitParameter(PREFIX + "addLabels")
	private Boolean addLabels;

	@ServletContextInitParameter(PREFIX + "addMessages")
	private Boolean addMessages;

	@ServletContextInitParameter(PREFIX + "translation")
	private Boolean translation;

	@ServletContextInitParameter(PREFIX + "includeAngularJS")
	private Boolean includeAngularJs;

	@ServletContextInitParameter(PREFIX + "includeJQuery")
	private Boolean includeJQuery;

	@ServletContextInitParameter(PREFIX + "includeJQueryUI")
	private Boolean includeJQueryUi;

	@ServletContextInitParameter(PREFIX + "includeAngularMessages")
	private Boolean includeAngularMessages;

	@ServletContextInitParameter(PREFIX + "clientSideMessages")
	private Boolean clientSideMessages;

	@ServletContextInitParameter(PREFIX + "includeMainJS")
	private Boolean includeMainJs;
}
