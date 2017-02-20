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

import java.util.List;

import javax.faces.application.ProjectStage;
import javax.faces.application.ResourceHandler;
import javax.faces.application.StateManager;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UINamingContainer;
import javax.faces.context.PartialViewContext;
import javax.faces.convert.Converter;
import javax.faces.flow.FlowHandler;
import javax.faces.lifecycle.ClientWindow;
import javax.faces.validator.BeanValidator;
import javax.faces.view.facelets.ResourceResolver;
import javax.faces.view.facelets.TagDecorator;
import javax.faces.webapp.FacesServlet;

import lombok.Getter;
import lombok.Setter;
import org.joinfaces.configuration.InitParameter;
import org.joinfaces.configuration.NestedProperty;

import org.springframework.boot.context.properties.ConfigurationProperties;

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
	@InitParameter(ProjectStage.PROJECT_STAGE_PARAM_NAME)
	private ProjectStage projectStage;

	/**
	 * A space separated list of resource extensions for types that shouldn't be
	 * served by the ResourceHandler implementation. See the specification for
	 * further details.
	 */
	@InitParameter(value = ResourceHandler.RESOURCE_EXCLUDES_PARAM_NAME, listSeparator = ",")
	private List<String> resourceExcludes;

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
	@InitParameter(ResourceHandler.WEBAPP_CONTRACTS_DIRECTORY_PARAM_NAME)
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
	@InitParameter(ResourceHandler.WEBAPP_RESOURCES_DIRECTORY_PARAM_NAME)
	private String webappResourcesDirectory;

	/**
	 * Semicolon-separated list of view IDs that must save state using the JSF
	 * 1.2-style state saving.
	 */
	@InitParameter(value = StateManager.FULL_STATE_SAVING_VIEW_IDS_PARAM_NAME, listSeparator = ";")
	private List<String> fullStateSavingViewIds;

	/**
	 * If true, use the JSF2 partial state saving for views.
	 */
	@InitParameter(StateManager.PARTIAL_STATE_SAVING_PARAM_NAME)
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
	@InitParameter(StateManager.SERIALIZE_SERVER_STATE_PARAM_NAME)
	private Boolean serializeServerState;

	/**
	 * "server" or "client".
	 */
	@InitParameter(StateManager.STATE_SAVING_METHOD_PARAM_NAME)
	private String stateSavingMethod;

	/**
	 * Change the default suffix for JSP views.
	 */
	@InitParameter(ViewHandler.DEFAULT_SUFFIX_PARAM_NAME)
	private String defaultSuffix;

	/**
	 * ViewHandler. Useful for applications that use legacy Facelets
	 * implementation.
	 */
	@InitParameter(ViewHandler.DISABLE_FACELET_JSF_VIEWHANDLER_PARAM_NAME)
	private Boolean disableFaceletJsfViewhandler;

	/**
	 * The buffer size set on the response.
	 */
	@InitParameter(ViewHandler.FACELETS_BUFFER_SIZE_PARAM_NAME)
	private Integer faceletsBufferSize;

	/**
	 * TagDecorator implementations. See javadoc for javax.faces.view.facelets.TagDecorator.
	 */
	@InitParameter(value = ViewHandler.FACELETS_DECORATORS_PARAM_NAME, listSeparator = ";")
	private List<Class<? extends TagDecorator>> faceletsDecorators;

	/**
	 * Semicolon-separated list of paths to Facelet tag libraries.
	 */
	@InitParameter(value = ViewHandler.FACELETS_LIBRARIES_PARAM_NAME, listSeparator = ";")
	private List<String> faceletsLibraries;

	/**
	 * Time in seconds that facelets should be checked for changes since last
	 * request. A value of -1 disables refresh checking.
	 */
	@InitParameter(ViewHandler.FACELETS_REFRESH_PERIOD_PARAM_NAME)
	private Integer faceletsRefreshPeriod;

	/**
	 * If true, strip XML comments out of Facelets before delivering to the
	 * client.
	 */
	@InitParameter(ViewHandler.FACELETS_SKIP_COMMENTS_PARAM_NAME)
	private Boolean faceletsSkipComments;

	/**
	 * Set the suffix for Facelet xhtml files.
	 */
	@InitParameter(ViewHandler.FACELETS_SUFFIX_PARAM_NAME)
	private String faceletsSuffix;

	/**
	 * Semicolon-separated list of Facelet files that don't use the default
	 * facelets suffix.
	 */
	@InitParameter(value = ViewHandler.FACELETS_VIEW_MAPPINGS_PARAM_NAME, listSeparator = ";")
	private List<String> faceletsViewMappings;

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
	@InitParameter(UIComponent.HONOR_CURRENT_COMPONENT_ATTRIBUTES_PARAM_NAME)
	private Boolean honorCurrentComponentAttributes;

	/**
	 * If "true", validate null and empty values. If "auto" validate when
	 * JSR-303 Bean Validation is enabled (in AS6 it is enabled by default).
	 */
	@InitParameter(UIInput.VALIDATE_EMPTY_FIELDS_PARAM_NAME)
	private String validateEmptyFields;

	/**
	 * <p class="changed_added_2_0">The context-param that allows the separator
	 * char for clientId strings to be set on a per-web application basis.</p>
	 *
	 * @since 2.0
	 */
	@InitParameter(UINamingContainer.SEPARATOR_CHAR_PARAM_NAME)
	private String separatorChar;

	@NestedProperty
	private Partial partial = new Partial();

	/**
	 * Controls if DateTimeConverter instances use the system timezone (if true)
	 * or GMT (if false).
	 */
	@InitParameter(Converter.DATETIMECONVERTER_DEFAULT_TIMEZONE_IS_SYSTEM_TIMEZONE_PARAM_NAME)
	private Boolean datetimeconverterDefaultTimezoneIsSystemTimezone;

	@NestedProperty
	private Flow flow = new Flow();

	@NestedProperty
	private Validator validator = new Validator();

	/**
	 * An implementation of javax.faces.view.facelets.ResourceResolver. See
	 * javadoc for details.
	 */
	@InitParameter(ResourceResolver.FACELETS_RESOURCE_RESOLVER_PARAM_NAME)
	private Class<? extends ResourceResolver> faceletsResourceResolver;

	/**
	 * Comma-delimited list of faces config files.
	 */
	@InitParameter(value = FacesServlet.CONFIG_FILES_ATTR, listSeparator = ",")
	private List<String> configFiles;

	/**
	 * ID for alternate Lifecycle implementations.
	 */
	@InitParameter(FacesServlet.LIFECYCLE_ID_ATTR)
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
	@InitParameter(ClientWindow.CLIENT_WINDOW_MODE_PARAM_NAME)
	private String clientWindowMode;

	/**
	 * If true, consider empty UIInput values to be null instead of empty
	 * string.
	 */
	@InitParameter(JavaxFacesProperties.EMPTY_STRING_AS_NULL)
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
		@InitParameter(PartialViewContext.PARTIAL_EXECUTE_PARAM_NAME)
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
		@InitParameter(PartialViewContext.PARTIAL_RENDER_PARAM_NAME)
		private Boolean render;

		/**
		 * <p class="changed_added_2_2">
		 * If the request parameter named by the value of this constant has a
		 * parameter value of <code>true</code>, the implementation must return
		 * <code>true</code> from {@link #isResetValues}.</p>
		 *
		 * @since 2.2
		 */
		@InitParameter(PartialViewContext.RESET_VALUES_PARAM_NAME)
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
		@InitParameter(FlowHandler.NULL_FLOW)
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
		@InitParameter(BeanValidator.DISABLE_DEFAULT_BEAN_VALIDATOR_PARAM_NAME)
		private Boolean disableDefaultBeanValidator;
	}
}
