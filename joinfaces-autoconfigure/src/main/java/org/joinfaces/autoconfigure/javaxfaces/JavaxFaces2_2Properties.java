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

import jakarta.faces.context.PartialViewContext;
import jakarta.faces.flow.FlowHandler;

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

	@NestedConfigurationProperty
	private final Partial partial = new Partial();

	@NestedConfigurationProperty
	private final Flow flow = new Flow();

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
