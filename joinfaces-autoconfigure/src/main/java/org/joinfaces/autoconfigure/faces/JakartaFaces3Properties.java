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

import java.util.List;
import java.util.TimeZone;

import jakarta.faces.application.ProjectStage;
import jakarta.faces.application.ResourceHandler;
import jakarta.faces.application.StateManager;
import jakarta.faces.application.ViewHandler;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.component.UINamingContainer;
import jakarta.faces.component.UIViewRoot;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.event.PhaseListener;
import jakarta.faces.lifecycle.ClientWindow;
import jakarta.faces.push.PushContext;
import jakarta.faces.validator.BeanValidator;
import jakarta.faces.view.facelets.TagDecorator;
import jakarta.faces.webapp.FacesServlet;

import lombok.Data;
import org.joinfaces.autoconfigure.servlet.initparams.ServletContextInitParameter;
import org.joinfaces.autoconfigure.servlet.initparams.ServletContextInitParameterProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * {@link ConfigurationProperties Configuration properties} for JSF 3.0.
 *
 * @see <a href="https://jakarta.ee/specifications/faces/3.0/jakarta-faces-3.0.html#a6088">https://jakarta.ee/specifications/faces/3.0/jakarta-faces-3.0.html#a6088</a>
 */
@Data
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
@ConfigurationProperties("joinfaces.faces")
public class JakartaFaces3Properties implements ServletContextInitParameterProperties {

	/**
	 * If this param is set, and calling toLowerCase().equals("true") on a
	 * String representation of its value returns true, validation
	 * must be performed, even when there is no corresponding value for this
	 * component in the incoming request.
	 *
	 * @since 2.3
	 * @see UIInput#ALWAYS_PERFORM_VALIDATION_WHEN_REQUIRED_IS_TRUE
	 * @see BeanValidator#validate(FacesContext, UIComponent, Object)
	 */
	@ServletContextInitParameter(UIInput.ALWAYS_PERFORM_VALIDATION_WHEN_REQUIRED_IS_TRUE)
	private Boolean alwaysPerformValidationWhenRequiredIsTrue;

	/**
	 * The context-param that controls the operation of the {@code ClientWindow} feature.
	 * The runtime must support the values "none" and "url", without the quotes, but other values
	 * are possible. If not specified, or the value is not understood by the
	 * implementation, "none" is assumed.
	 *
	 * @since 2.2
	 * @see ClientWindow#CLIENT_WINDOW_MODE_PARAM_NAME
	 */
	@ServletContextInitParameter(ClientWindow.CLIENT_WINDOW_MODE_PARAM_NAME)
	private String clientWindowMode;

	/**
	 * Comma-delimited list of context-relative resource paths under which the Jakarta Faces implementation will look for application configuration resources (see Application Configuration Resource Format),
	 * before loading a configuration resource named “/WEB-INF/faces-config.xml” (if such a resource exists).
	 * If “/WEB-INF/faces-config.xml” is present in the list, it must be ignored.
	 *
	 * @see FacesServlet#CONFIG_FILES_ATTR
	 */
	@ServletContextInitParameter(value = FacesServlet.CONFIG_FILES_ATTR, listSeparator = ",")
	private List<String> configFiles;

	/**
	 * Controls if DateTimeConverter instances use the system timezone (if true) or GMT (if false).
	 * If this param is set, and true, Application.createConverter() must guarantee that the default for the timezone of all {@link jakarta.faces.convert.DateTimeConverter} instances must be equal to {@link TimeZone#getDefault()} instead of “GMT”.
	 */
	@ServletContextInitParameter(Converter.DATETIMECONVERTER_DEFAULT_TIMEZONE_IS_SYSTEM_TIMEZONE_PARAM_NAME)
	private Boolean datetimeconverterDefaultTimezoneIsSystemTimezone;

	/**
	 * Allow the web application to define an alternate suffix for Jakarta Server Pages pages containing Jakarta Faces content.
	 *
	 * @see ViewHandler#DEFAULT_SUFFIX_PARAM_NAME
	 */
	@Deprecated(forRemoval = true, since = "5.0.0")
	@ServletContextInitParameter(value = ViewHandler.DEFAULT_SUFFIX_PARAM_NAME, listSeparator = " ")
	private String defaultSuffix;

	/**
	 * If this param is set, and true, the default ViewHandler must behave as specified in the latest 1.2 version of this specification.
	 * Any behavior specified in Default ViewDeclarationLanguage Implementation and implemented in the default ViewHandler that pertains to handling requests for pages authored in the Jakarta Server Faces View Declaration Language must not be executed by the runtime.
	 * For backward compatibility with previous versions of Facelets, the value DISABLE_FACELET_JSF_VIEWHANDLER must be supported.
	 */
	@Deprecated(forRemoval = true, since = "5.0.0")
	@ServletContextInitParameter("jakarta.faces.DISABLE_FACELET_JSF_VIEWHANDLER")
	private Boolean disableFaceletJsfViewhandler;

	/**
	 * The {@code ServletContext} init
	 * parameter consulted by the runtime to tell if the automatic mapping
	 * of the {@code FacesServlet} to the extension {@code *.xhtml}
	 * should be disabled.  The implementation must disable this automatic
	 * mapping if and only if the value of this parameter is equal, ignoring
	 * case, to {@code true}.
	 *
	 * <p>If this parameter is not specified, this automatic mapping is enabled
	 * as specified above.</p>
	 *
	 * @since 2.3
	 */
	@ServletContextInitParameter(FacesServlet.DISABLE_FACESSERVLET_TO_XHTML_PARAM_NAME)
	private Boolean disableFacesservletToXhtml;

	/**
	 * Semicolon-separated list of paths to Facelet tag libraries.
	 */
	@ServletContextInitParameter(value = ViewHandler.FACELETS_LIBRARIES_PARAM_NAME, listSeparator = ";")
	private List<String> faceletsLibraries;

	/**
	 * The buffer size set on the response.
	 */
	@ServletContextInitParameter(ViewHandler.FACELETS_BUFFER_SIZE_PARAM_NAME)
	private Integer faceletsBufferSize;

	/**
	 * TagDecorator implementations. See javadoc for jakarta.faces.view.facelets.TagDecorator.
	 */
	@ServletContextInitParameter(value = ViewHandler.FACELETS_DECORATORS_PARAM_NAME, listSeparator = ";")
	private List<Class<? extends TagDecorator>> faceletsDecorators;

	/**
	 * Time in seconds that facelets should be checked for changes since last
	 * request. A value of -1 disables refresh checking.
	 */
	@ServletContextInitParameter(ViewHandler.FACELETS_REFRESH_PERIOD_PARAM_NAME)
	private Integer faceletsRefreshPeriod;

	/**
	 * An implementation of jakarta.faces.view.facelets.ResourceResolver.
	 * See javadoc for details.
	 */
	@Deprecated(forRemoval = true, since = "5.0.0")
	@ServletContextInitParameter("jakarta.faces.FACELETS_RESOURCE_RESOLVER")
	private Class<?> faceletsResourceResolver;

	/**
	 * If true, strip XML comments out of Facelets before delivering to the
	 * client.
	 */
	@ServletContextInitParameter(ViewHandler.FACELETS_SKIP_COMMENTS_PARAM_NAME)
	private Boolean faceletsSkipComments;

	/**
	 * Set the suffix for Facelet xhtml files.
	 */
	@ServletContextInitParameter(ViewHandler.FACELETS_SUFFIX_PARAM_NAME)
	private String faceletsSuffix;

	/**
	 * Semicolon-separated list of Facelet files that don't use the default
	 * facelets suffix.
	 */
	@ServletContextInitParameter(value = ViewHandler.FACELETS_VIEW_MAPPINGS_PARAM_NAME, listSeparator = ";")
	private List<String> faceletsViewMappings;

	/**
	 * Semicolon-separated list of view IDs that must save state using the JSF
	 * 1.2-style state saving.
	 */
	@ServletContextInitParameter(value = StateManager.FULL_STATE_SAVING_VIEW_IDS_PARAM_NAME, listSeparator = ";")
	private List<String> fullStateSavingViewIds;

	/**
	 * The {@code ServletContext} init
	 * parameter consulted by the {@code UIComponent} to tell whether or
	 * not the {@link UIComponent#CURRENT_COMPONENT} and
	 * {@link UIComponent#CURRENT_COMPOSITE_COMPONENT} attribute keys should be honored as
	 * specified.
	 * <p>
	 * If this parameter is not specified, or is set to false, the contract
	 * specified by the {@link UIComponent#CURRENT_COMPONENT} and
	 * {@link UIComponent#CURRENT_COMPOSITE_COMPONENT} method is not honored. If this
	 * parameter is set to true, the contract is honored.
	 */
	@Deprecated(forRemoval = true, since = "5.0.0")
	@ServletContextInitParameter("jakarta.faces.HONOR_CURRENT_COMPONENT_ATTRIBUTES")
	private Boolean honorCurrentComponentAttributes;

	/**
	 * If true, consider empty UIInput values to be null instead of empty string.
	 */
	@ServletContextInitParameter("jakarta.faces.INTERPRET_EMPTY_STRING_SUBMITTED_VALUES_AS_NULL")
	private Boolean interpretEmptyStringSubmittedValuesAsNull;

	/**
	 * ID for alternate Lifecycle implementations.
	 */
	@ServletContextInitParameter(FacesServlet.LIFECYCLE_ID_ATTR)
	private String lifecycleId;

	/**
	 * If true, use the JSF2 partial state saving for views.
	 */
	@ServletContextInitParameter(StateManager.PARTIAL_STATE_SAVING_PARAM_NAME)
	private Boolean partialStateSaving;

	/**
	 * Set the project stage to "Development", "UnitTest", "SystemTest", or
	 * "Production".
	 */
	@ServletContextInitParameter(ProjectStage.PROJECT_STAGE_PARAM_NAME)
	private ProjectStage projectStage;

	/**
	 * A space separated list of resource extensions for types that shouldn't be
	 * served by the ResourceHandler implementation. See the specification for
	 * further details.
	 */
	@ServletContextInitParameter(value = ResourceHandler.RESOURCE_EXCLUDES_PARAM_NAME, listSeparator = ",")
	private List<String> resourceExcludes;

	/**
	 * The context-param that allows the separator char for clientId strings to be set on a per-web application basis.
	 *
	 * @since 2.0
	 */
	@ServletContextInitParameter(UINamingContainer.SEPARATOR_CHAR_PARAM_NAME)
	private String separatorChar;

	/**
	 * If this param is set, and calling toLowerCase().equals("true") on a
	 * String representation of its value returns true, and the
	 * jakarta.faces.STATE_SAVING_METHOD is set to "server" (as indicated below),
	 * the server state must be guaranteed to be Serializable such that the
	 * aggregate state implements java.io.Serializable. The intent of this
	 * parameter is to ensure that the act of writing out the state to an
	 * ObjectOutputStream would not throw a NotSerializableException, but the
	 * runtime is not required verify this before saving the state.
	 */
	@ServletContextInitParameter(StateManager.SERIALIZE_SERVER_STATE_PARAM_NAME)
	private Boolean serializeServerState;

	/**
	 * "server" or "client".
	 */
	@ServletContextInitParameter(StateManager.STATE_SAVING_METHOD_PARAM_NAME)
	private String stateSavingMethod;

	/**
	 * If "true", validate null and empty values. If "auto" validate when
	 * JSR-303 Bean Validation is enabled (in AS6 it is enabled by default).
	 */
	@ServletContextInitParameter(UIInput.VALIDATE_EMPTY_FIELDS_PARAM_NAME)
	private String validateEmptyFields;

	@NestedConfigurationProperty
	private final ValidatorProperties validator = new ValidatorProperties();

	/**
	 * If this param is set, and calling toLowerCase().equals("true") on a
	 * String representation of its value returns true, exceptions thrown
	 * by {@link PhaseListener}s installed on the {@code UIViewRoot} are
	 * queued to the {@link jakarta.faces.context.ExceptionHandler} instead of
	 * being logged and swallowed.
	 *
	 * @since 2.3
	 */
	@ServletContextInitParameter(UIViewRoot.VIEWROOT_PHASE_LISTENER_QUEUES_EXCEPTIONS_PARAM_NAME)
	private Boolean viewrootPhaseListenerQueuesExceptions;

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
	 * Valitador class for disableDefaultBeanValidator parameter.
	 */
	@Data
	public static class ValidatorProperties {

		/**
		 * If "true", disable JSR-303 Bean Validation.
		 */
		@ServletContextInitParameter(BeanValidator.DISABLE_DEFAULT_BEAN_VALIDATOR_PARAM_NAME)
		private Boolean disableDefaultBeanValidator;

		/**
		 * If this param is set, and calling toLowerCase().equals("true") on a String representation of its value returns true, the <f:validateWholeBean /> tag is enabled.
		 * If not set or set to false, this tag is a no-op.
		 */
		@ServletContextInitParameter(BeanValidator.ENABLE_VALIDATE_WHOLE_BEAN_PARAM_NAME)
		private Boolean enableValidateWholeBean;
	}
}
