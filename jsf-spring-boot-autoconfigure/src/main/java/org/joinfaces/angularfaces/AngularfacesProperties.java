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
import org.joinfaces.configuration.InitParameter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties of AngularFaces.
 * Taken from https://github.com/stephanrauh/AngularFaces
 * @author Marcelo Fernandes
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "jsf.angularfaces")
public class AngularfacesProperties {

	static final String PREFIX = "AngularFaces.";

	@InitParameter(PREFIX + "addLabels")
	private Boolean addLabels;

	@InitParameter(PREFIX + "addMessages")
	private Boolean addMessages;

	@InitParameter(PREFIX + "translation")
	private Boolean translation;

	@InitParameter(PREFIX + "includeAngularJS")
	private Boolean includeAngularJS;

	@InitParameter(PREFIX + "includeJQuery")
	private Boolean includeJQuery;

	@InitParameter(PREFIX + "includeJQueryUI")
	private Boolean includeJQueryUI;

	@InitParameter(PREFIX + "includeAngularMessages")
	private Boolean includeAngularMessages;

	@InitParameter(PREFIX + "clientSideMessages")
	private Boolean clientSideMessages;

	@InitParameter(PREFIX + "includeMainJS")
	private Boolean includeMainJS;
}
