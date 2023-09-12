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

package org.joinfaces.autoconfigure.faces;

import jakarta.faces.lifecycle.ClientWindow;
import jakarta.faces.webapp.FacesServlet;

import lombok.Data;
import org.joinfaces.autoconfigure.servlet.initparams.ServletContextInitParameter;
import org.joinfaces.autoconfigure.servlet.initparams.ServletContextInitParameterProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration Properties for Jakarta Faces 4.
 *
 * @author Lars Grefer
 * @see <a href="https://jakarta.ee/specifications/faces/4.0/jakarta-faces-4.0.html#a6088">11.1.3. Application Configuration Parameters</a>
 * @see <a href="https://myfaces.apache.org/#/core40?id=configuration">MyFaces 4 Configuration</a>
 */
@Data
@ConfigurationProperties("joinfaces.faces")
public class JakartaFaces4Properties implements ServletContextInitParameterProperties {

	/**
	 * The ServletContext init parameter consulted by the runtime to tell if the automatic mapping of the FacesServlet to the extensionless variant (without *.xhtml) should be enabled.
	 * The implementation must enable this automatic mapping if and only if the value of this parameter is equal, ignoring case, to true.
	 *
	 * @see FacesServlet#AUTOMATIC_EXTENSIONLESS_MAPPING_PARAM_NAME
	 */
	@ServletContextInitParameter(FacesServlet.AUTOMATIC_EXTENSIONLESS_MAPPING_PARAM_NAME)
	private Boolean automaticExtensionlessMapping;

	/**
	 * Indicate the max number of ClientWindows, which is used by ClientWindowScoped.
	 * It is only active when jakarta.faces.CLIENT_WINDOW_MODE is enabled.
	 *
	 * @see ClientWindow#CLIENT_WINDOW_MODE_PARAM_NAME
	 */
	@ServletContextInitParameter(ClientWindow.NUMBER_OF_CLIENT_WINDOWS_PARAM_NAME)
	private Integer numberOfClientWindows;
}
