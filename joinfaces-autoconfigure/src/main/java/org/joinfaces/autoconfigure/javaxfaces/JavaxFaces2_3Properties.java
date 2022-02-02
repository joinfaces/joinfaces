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

package org.joinfaces.autoconfigure.javaxfaces;

import jakarta.faces.push.PushContext;

import lombok.Data;
import org.joinfaces.autoconfigure.servlet.initparams.ServletContextInitParameter;
import org.joinfaces.autoconfigure.servlet.initparams.ServletContextInitParameterProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * {@link ConfigurationProperties Configuration properties} for JSF 2.3.
 *
 * JavaxFaces standard properties.
 * Taken from
 * https://javaee.github.io/javaee-spec/javadocs/constant-values.html#javax.faces
 * and {@link com.sun.faces.config.WebConfiguration} class
 * @author Marcelo Fernandes
 * @see <a href="https://www.jcp.org/en/jsr/detail?id=344">JSR-344</a>
 */
@Data
@ConfigurationProperties(prefix = "joinfaces.jsf")
public class JavaxFaces2_3Properties implements ServletContextInitParameterProperties {

	/**
	 * The integer context parameter name to specify the websocket endpoint port when it's different from HTTP port.
	 */
	@ServletContextInitParameter(PushContext.WEBSOCKET_ENDPOINT_PORT_PARAM_NAME)
	private Integer websocketEndpointPort;

}
