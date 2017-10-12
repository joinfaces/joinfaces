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

import javax.faces.application.ResourceHandler;
import javax.faces.application.StateManager;
import javax.faces.context.PartialViewContext;
import javax.faces.flow.FlowHandler;
import javax.faces.lifecycle.ClientWindow;

import lombok.Getter;
import lombok.Setter;
import org.joinfaces.configuration.NestedProperty;
import org.joinfaces.configuration.ServletContextInitParameter;
import org.joinfaces.configuration.ServletContextInitParameterConfigurationProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * {@link ConfigurationProperties Configuration properties} for JSF 2.0.
 *
 * JavaxFaces standard properties.
 * Taken from
 * https://javaserverfaces.java.net/docs/2.2/javadocs/constant-values.html
 * @author Marcelo Fernandes
 * @see <a href="https://www.jcp.org/en/jsr/detail?id=344">JSR-344</a>
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "jsf")
public class JavaxFaces2_2Properties implements ServletContextInitParameterConfigurationProperties {

	/**
	 * <p class="changed_added_2_2">If a <code>&lt;context-param&gt;</code> with
	 * the param name equal to the value of
	 * {@link #WEBAPP_CONTRACTS_DIRECTORY_PARAM_NAME} exists, the runtime must
	 * interpret its value as a path, relative to the web app root, where
	 * resource library contracts are to be located. This param value must not
	 * start with a "/", though it may contain "/" characters. If no such
	 * <code>&lt;context-param&gt;</code> exists, or its value is invalid, the
	 * value "contracts", without the quotes, must be used by the runtime as the
	 * value.</p>
	 *
	 * @since 2.2
	 */
	@ServletContextInitParameter(ResourceHandler.WEBAPP_CONTRACTS_DIRECTORY_PARAM_NAME)
	private String webappContractsDirectory;

	/**
	 * <p class="changed_added_2_2">If a <code>&lt;context-param&gt;</code> with
	 * the param name equal to the value of
	 * {@link #WEBAPP_RESOURCES_DIRECTORY_PARAM_NAME} exists, the runtime must
	 * interpret its value as a path, relative to the web app root, where
	 * resources are to be located. This param value must not start with a "/",
	 * though it may contain "/" characters. If no such
	 * <code>&lt;context-param&gt;</code> exists, or its value is invalid, the
	 * value "resources", without the quotes, must be used by the runtime as the
	 * value.</p>
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

	@NestedProperty
	private Partial partial = new Partial();

	@NestedProperty
	private Flow flow = new Flow();

	/**
	 * <p class="changed_added_2_2">The context-param that controls the
	 * operation of the <code>ClientWindow</code> feature. The runtime must
	 * support the values "none" and "url", without the quotes, but other values
	 * are possible. If not specified, or the value is not understood by the
	 * implementation, "none" is assumed.</p>
	 *
	 * @since 2.2
	 */
	@ServletContextInitParameter(ClientWindow.CLIENT_WINDOW_MODE_PARAM_NAME)
	private String clientWindowMode;

	/**
	 * Partial class for execute, render and resetValues parameters.
	 */
	@Getter
	@Setter
	public static class Partial {

		/**
		 * <p class="changed_added_2_2">
		 * If the request parameter named by the value of this constant has a
		 * parameter value of <code>true</code>, the implementation must return
		 * <code>true</code> from {@link #isResetValues}.</p>
		 *
		 * @since 2.2
		 */
		@ServletContextInitParameter(PartialViewContext.RESET_VALUES_PARAM_NAME)
		private Boolean resetValues;
	}

	/**
	 * Flow class for nullFlow parameter.
	 */
	@Getter
	@Setter
	public static class Flow {

		/**
		 * <p class="changed_added_2_2">Components that are rendered by
		 * <code>Renderers</code> of component-family
		 * <code>javax.faces.OutcomeTarget</code> must use this constant as the
		 * value of the parameter named by
		 * {@link #TO_FLOW_DOCUMENT_ID_REQUEST_PARAM_NAME} when returning from a
		 * flow (without entering another flow) using such a component. </p>
		 *
		 * @since 2.2
		 */
		@ServletContextInitParameter(FlowHandler.NULL_FLOW)
		private Boolean nullFlow;
	}
}
