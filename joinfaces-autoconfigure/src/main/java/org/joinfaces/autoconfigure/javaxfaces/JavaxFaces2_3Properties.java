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

package org.joinfaces.autoconfigure.javaxfaces;

import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.push.PushContext;
import javax.faces.validator.BeanValidator;
import javax.faces.webapp.FacesServlet;

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
 * and com.sun.faces.config.WebConfiguration class
 * @author Marcelo Fernandes
 * @see <a href="https://www.jcp.org/en/jsr/detail?id=344">JSR-344</a>
 */
@Data
@ConfigurationProperties(prefix = "jsf")
public class JavaxFaces2_3Properties implements ServletContextInitParameterProperties {

	/**
     * If this param is set, and calling toLowerCase().equals("true") on a
     * String representation of its value returns true, validation
     * must be performed, even when there is no corresponding value for this
     * component in the incoming request. See {@link #validate}.
     */
	@ServletContextInitParameter(UIInput.ALWAYS_PERFORM_VALIDATION_WHEN_REQUIRED_IS_TRUE)
	private Boolean alwaysPerformValidationWhenRequiredIsTrue;

	/**
	 * The boolean context parameter name to explicitly enable web socket endpoint during startup.
	 */
	@ServletContextInitParameter(PushContext.ENABLE_WEBSOCKET_ENDPOINT_PARAM_NAME)
	private Boolean enableWebsocketEndpoint;

	/**
	 * The integer context parameter name to specify the websocket endpoint port when it's different from HTTP port.
	 */
	@ServletContextInitParameter(PushContext.WEBSOCKET_ENDPOINT_PORT_PARAM_NAME)
	private Integer websocketEndpointPort;

	/**
     * The <code>ServletContext</code> init
     * parameter consulted by the runtime to tell if the automatic mapping
     * of the {@code FacesServlet} to the extension {@code *.xhtml}
     * should be disabled.  The implementation must disable this automatic
     * mapping if and only if the value of this parameter is equal, ignoring
     * case, to {@code true}.
     *
     * <p>If this parameter is not specified, this automatic mapping is enabled
     * as specified above.</p>
     */
	@ServletContextInitParameter(FacesServlet.DISABLE_FACESSERVLET_TO_XHTML_PARAM_NAME)
	private Boolean disableFacesservletToXhtml;

	/**
     * If this param is set, and calling toLowerCase().equals("true") on a
     * String representation of its value returns true, exceptions thrown
     * by {@link PhaseListener}s installed on the {@code UIViewRoot} are
     * queued to the {@link javax.faces.context.ExceptionHandler} instead of
     * being logged and swallowed.
     *
     * @since 2.3
     */
	@ServletContextInitParameter(UIViewRoot.VIEWROOT_PHASE_LISTENER_QUEUES_EXCEPTIONS_PARAM_NAME)
	private Boolean viewrootPhaseListenerQueuesExceptions;

	/**
     * If this param is set, and calling toLowerCase().equals("true") on a
     * String representation of its value returns {@code true} take
     * the additional actions relating to <code>&lt;validateWholeBean /&gt;</code>
     * specified in {@link #validate}.
     *
     * @since 2.3
     */
	@ServletContextInitParameter(BeanValidator.ENABLE_VALIDATE_WHOLE_BEAN_PARAM_NAME)
	private Boolean enableValidateWholeBean;
}
