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

import javax.faces.application.ResourceHandler;
import javax.faces.application.StateManager;
import javax.faces.context.PartialViewContext;
import javax.faces.flow.FlowHandler;
import javax.faces.lifecycle.ClientWindow;

import lombok.Data;
import org.joinfaces.autoconfigure.servlet.initparams.ServletContextInitParameter;
import org.joinfaces.autoconfigure.servlet.initparams.ServletContextInitParameterProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * {@link ConfigurationProperties Configuration properties} for JSF 2.0.
 *
 * JavaxFaces standard properties.
 * Taken from
 * https://javaserverfaces.java.net/docs/2.2/javadocs/constant-values.html
 * @author Marcelo Fernandes
 * @see <a href="https://www.jcp.org/en/jsr/detail?id=344">JSR-344</a>
 */
@Data
@ConfigurationProperties(prefix = "joinfaces.jsf")
public class JavaxFaces2_2Properties implements ServletContextInitParameterProperties {

	/**
	 * If a {@code <context-param>} with the param name equal to the value of
	 * {@link ResourceHandler#WEBAPP_CONTRACTS_DIRECTORY_PARAM_NAME} exists, the runtime must
	 * interpret its value as a path, relative to the web app root, where
	 * resource library contracts are to be located. This param value must not
	 * start with a "/", though it may contain "/" characters. If no such
	 * {@code <context-param>} exists, or its value is invalid, the
	 * value "contracts", without the quotes, must be used by the runtime as the
	 * value.
	 *
	 * @since 2.2
	 */
	@ServletContextInitParameter(ResourceHandler.WEBAPP_CONTRACTS_DIRECTORY_PARAM_NAME)
	private String webappContractsDirectory;

	/**
	 * If a {@code <context-param>} with the param name equal to the value of
	 * {@link ResourceHandler#WEBAPP_RESOURCES_DIRECTORY_PARAM_NAME} exists, the runtime must
	 * interpret its value as a path, relative to the web app root, where
	 * resources are to be located. This param value must not start with a "/",
	 * though it may contain "/" characters. If no such
	 * {@code <context-param>} exists, or its value is invalid, the
	 * value "resources", without the quotes, must be used by the runtime as the
	 * value.
	 *
	 * @since 2.2
	 */
	@ServletContextInitParameter(ResourceHandler.WEBAPP_RESOURCES_DIRECTORY_PARAM_NAME)
	private String webappResourcesDirectory;

	/**
	 * If this param is set, and calling toLowerCase().equals("true") on a
	 * String representation of its value returns true, and the
	 * javax.faces.STATE_SAVING_METHOD is set to "server" (as indicated below),
	 * the server state must be guaranteed to be Serializable such that the
	 * aggregate state implements java.io.Serializable. The intent of this
	 * parameter is to ensure that the act of writing out the state to an
	 * ObjectOutputStream would not throw a NotSerializableException, but the
	 * runtime is not required verify this before saving the state.
	 */
	@ServletContextInitParameter(StateManager.SERIALIZE_SERVER_STATE_PARAM_NAME)
	private Boolean serializeServerState;

	@NestedConfigurationProperty
	private final Partial partial = new Partial();

	@NestedConfigurationProperty
	private final Flow flow = new Flow();

	/**
	 * The context-param that controls the operation of the {@code ClientWindow} feature.
	 * The runtime must support the values "none" and "url", without the quotes, but other values
	 * are possible. If not specified, or the value is not understood by the
	 * implementation, "none" is assumed.
	 *
	 * @since 2.2
	 */
	@ServletContextInitParameter(ClientWindow.CLIENT_WINDOW_MODE_PARAM_NAME)
	private String clientWindowMode;

	/**
	 * Partial class for execute, render and resetValues parameters.
	 */
	@Data
	public static class Partial {

		/**
		 * If the request parameter named by the value of this constant has a
		 * parameter value of {@code true}, the implementation must return
		 * {@code true} from {@link PartialViewContext#isResetValues()}.
		 *
		 * @since 2.2
		 */
		@ServletContextInitParameter(PartialViewContext.RESET_VALUES_PARAM_NAME)
		private Boolean resetValues;
	}

	/**
	 * Flow class for nullFlow parameter.
	 */
	@Data
	public static class Flow {

		/**
		 * Components that are rendered by {@code Renderers} of component-family
		 * {@code javax.faces.OutcomeTarget} must use this constant as the
		 * value of the parameter named by
		 * {@link FlowHandler#TO_FLOW_DOCUMENT_ID_REQUEST_PARAM_NAME} when returning from a
		 * flow (without entering another flow) using such a component.
		 *
		 * @since 2.2
		 */
		@ServletContextInitParameter(FlowHandler.NULL_FLOW)
		private Boolean nullFlow;
	}
}
