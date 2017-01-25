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

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.faces.application.ProjectStage;

/**
 * JavaxFaces standard properties.
 * Taken from
 * https://javaserverfaces.java.net/docs/2.2/javadocs/constant-values.html
 * @author Marcelo Fernandes
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "jsf")
public class JavaxFacesProperties {

	/**
	 * Set the project stage to "Development", "UnitTest", "SystemTest", or
	 * "Production".
	 */
	private ProjectStage projectStage;

	/**
	 * A space separated list of resource extensions for types that shouldn't be
	 * served by the ResourceHandler implementation. See the specification for
	 * further details.
	 */
	private String resourceExcludes;

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
	private String webappResourcesDirectory;

	/**
	 * Semicolon-separated list of view IDs that must save state using the JSF
	 * 1.2-style state saving.
	 */
	private String fullStateSavingViewIds;

	/**
	 * If true, use the JSF2 partial state saving for views.
	 */
	private Boolean partialStateSaving;

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
	private Boolean serializeServerState;

	/**
	 * "server" or "client".
	 */
	private String stateSavingMethod;

	/**
	 * Change the default suffix for JSP views.
	 */
	private String defaultSuffix;

	/**
	 * ViewHandler. Useful for applications that use legacy Facelets
	 * implementation.
	 */
	private Boolean disableFaceletJsfViewhandler;

	/**
	 * The buffer size set on the response.
	 */
	private Integer faceletsBufferSize;

	/**
	 * TagDecorator implementations. See javadoc for javax.faces.view
	 * .facelets.TagDecorator.
	 */
	private String faceletsDecorators;

	/**
	 * Semicolon-separated list of paths to Facelet tag libraries.
	 */
	private String faceletsLibraries;

	/**
	 * Time in seconds that facelets should be checked for changes since last
	 * request. A value of -1 disables refresh checking.
	 */
	private Integer faceletsRefreshPeriod;

	/**
	 * If true, strip XML comments out of Facelets before delivering to the
	 * client.
	 */
	private Boolean faceletsSkipComments;

	/**
	 * Set the suffix for Facelet xhtml files.
	 */
	private String faceletsSuffix;

	/**
	 * Semicolon-separated list of Facelet files that don't use the default
	 * facelets suffix.
	 */
	private String faceletsViewMappings;

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
	private Boolean honorCurrentComponentAttributes;

	/**
	 * If "true", validate null and empty values. If "auto" validate when
	 * JSR-303 Bean Validation is enabled (in AS6 it is enabled by default).
	 */
	private String validateEmptyFields;

	/**
	 * <p class="changed_added_2_0">The context-param that allows the separator
	 * char for clientId strings to be set on a per-web application basis.</p>
	 *
	 * @since 2.0
	 */
	private String separatorChar;

	private Partial partial = new Partial();

	/**
	 * Controls if DateTimeConverter instances use the system timezone (if true)
	 * or GMT (if false).
	 */
	private Boolean datetimeconverterDefaultTimezoneIsSystemTimezone;

	private Flow flow = new Flow();

	private Validator validator = new Validator();

	/**
	 * An implementation of javax.faces .view.facelets .ResourceResolver. See
	 * javadoc for details.
	 */
	private String faceletsResourceResolver;

	/**
	 * Comma-delimited list of faces config files.
	 */
	private String configFiles;

	/**
	 * ID for alternate Lifecycle implementations.
	 */
	private String lifecycleId;

	/**
	 * <p class="changed_added_2_2">The context-param that controls the
	 * operation of the <code>ClientWindow</code> feature. The runtime must
	 * support the values "none" and "url", without the quotes, but other values
	 * are possible. If not specified, or the value is not understood by the
	 * implementation, "none" is assumed.</p>
	 *
	 * @since 2.2
	 */
	private String clientWindowMode;

	/**
	 * If true, consider empty UIInput values to be null instead of empty
	 * string.
	 */
	private Boolean interpretEmptyStringSubmittedValuesAsNull;

	/**
	 * Interpret empty string submitted values as null constant.
	 */
	public static final String EMPTY_STRING_AS_NULL
		= "javax.faces.INTERPRET_EMPTY_STRING_SUBMITTED_VALUES_AS_NULL";

	/**
	 * Partial class for execute, render and resetValues parameters.
	 */
	@Getter
	@Setter
	public static class Partial {

		/**
		 * <p class="changed_added_2_0">
		 * The request parameter name whose request parameter value is a
		 * <code>Collection</code> of client identifiers identifying the
		 * components that must be processed during the
		 * <em>Apply Request Values</em>, <em>Process Validations</em>, and
		 * <em>Update Model Values</em> phases of the request processing
		 * lifecycle.</p>
		 *
		 * @since 2.0
		 */
		private Boolean execute;

		/**
		 * <p class="changed_added_2_0">
		 * The request parameter name whose request parameter value is a
		 * <code>Collection</code> of client identifiers identifying the
		 * components that must be processed during the
		 * <em>Render Response</em> phase of the request processing
		 * lifecycle.</p>
		 *
		 * @since 2.0
		 */
		private Boolean render;

		/**
		 * <p class="changed_added_2_2">
		 * If the request parameter named by the value of this constant has a
		 * parameter value of <code>true</code>, the implementation must return
		 * <code>true</code> from {@link #isResetValues}.</p>
		 *
		 * @since 2.2
		 */
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
		private Boolean nullFlow;
	}

	/**
	 * Valitador class for disableDefaultBeanValidator parameter.
	 */
	@Getter
	@Setter
	public static class Validator {

		/**
		 * If "true", disable JSR-303 Bean Validation.
		 */
		private Boolean disableDefaultBeanValidator;
	}
}
