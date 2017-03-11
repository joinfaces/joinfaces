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
import javax.faces.component.UIInput;
import javax.faces.component.UINamingContainer;
import javax.faces.context.PartialViewContext;
import javax.faces.convert.Converter;
import javax.faces.validator.BeanValidator;
import javax.faces.view.facelets.ResourceResolver;
import javax.faces.view.facelets.TagDecorator;
import javax.faces.webapp.FacesServlet;

import lombok.Getter;
import lombok.Setter;
import org.joinfaces.configuration.NestedProperty;
import org.joinfaces.configuration.ServletContextInitParameter;
import org.joinfaces.configuration.ServletContextInitParameterConfigurationProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * {@link ConfigurationProperties Configuration properties} for JSF 2.0.
 *
 * @author Marcelo Fernandes
 * @author Lars Grefer
 * @see <a href="https://www.jcp.org/en/jsr/detail?id=314">JSR-314</a>
 * @see <a href="http://stackoverflow.com/a/17341945/3574494">http://stackoverflow.com/a/17341945/3574494</a>
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "jsf")
public class JavaxFaces2_0Properties implements ServletContextInitParameterConfigurationProperties {

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
	 * Semicolon-separated list of view IDs that must save state using the JSF
	 * 1.2-style state saving.
	 */
	@ServletContextInitParameter(value = StateManager.FULL_STATE_SAVING_VIEW_IDS_PARAM_NAME, listSeparator = ";")
	private List<String> fullStateSavingViewIds;

	/**
	 * If true, use the JSF2 partial state saving for views.
	 */
	@ServletContextInitParameter(StateManager.PARTIAL_STATE_SAVING_PARAM_NAME)
	private Boolean partialStateSaving;

	/**
	 * "server" or "client".
	 */
	@ServletContextInitParameter(StateManager.STATE_SAVING_METHOD_PARAM_NAME)
	private String stateSavingMethod;

	/**
	 * Change the default suffix for JSP views.
	 */
	@ServletContextInitParameter(ViewHandler.DEFAULT_SUFFIX_PARAM_NAME)
	private String defaultSuffix;

	/**
	 * ViewHandler. Useful for applications that use legacy Facelets
	 * implementation.
	 */
	@ServletContextInitParameter(ViewHandler.DISABLE_FACELET_JSF_VIEWHANDLER_PARAM_NAME)
	private Boolean disableFaceletJsfViewhandler;

	/**
	 * The buffer size set on the response.
	 */
	@ServletContextInitParameter(ViewHandler.FACELETS_BUFFER_SIZE_PARAM_NAME)
	private Integer faceletsBufferSize;

	/**
	 * TagDecorator implementations. See javadoc for javax.faces.view.facelets.TagDecorator.
	 */
	@ServletContextInitParameter(value = ViewHandler.FACELETS_DECORATORS_PARAM_NAME, listSeparator = ";")
	private List<Class<? extends TagDecorator>> faceletsDecorators;

	/**
	 * Semicolon-separated list of paths to Facelet tag libraries.
	 */
	@ServletContextInitParameter(value = ViewHandler.FACELETS_LIBRARIES_PARAM_NAME, listSeparator = ";")
	private List<String> faceletsLibraries;

	/**
	 * Time in seconds that facelets should be checked for changes since last
	 * request. A value of -1 disables refresh checking.
	 */
	@ServletContextInitParameter(ViewHandler.FACELETS_REFRESH_PERIOD_PARAM_NAME)
	private Integer faceletsRefreshPeriod;

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
	 * If "true", validate null and empty values. If "auto" validate when
	 * JSR-303 Bean Validation is enabled (in AS6 it is enabled by default).
	 */
	@ServletContextInitParameter(UIInput.VALIDATE_EMPTY_FIELDS_PARAM_NAME)
	private String validateEmptyFields;

	/**
	 * <p class="changed_added_2_0">The context-param that allows the separator
	 * char for clientId strings to be set on a per-web application basis.</p>
	 *
	 * @since 2.0
	 */
	@ServletContextInitParameter(UINamingContainer.SEPARATOR_CHAR_PARAM_NAME)
	private String separatorChar;

	@NestedProperty
	private Partial partial = new Partial();

	/**
	 * Controls if DateTimeConverter instances use the system timezone (if true)
	 * or GMT (if false).
	 */
	@ServletContextInitParameter(Converter.DATETIMECONVERTER_DEFAULT_TIMEZONE_IS_SYSTEM_TIMEZONE_PARAM_NAME)
	private Boolean datetimeconverterDefaultTimezoneIsSystemTimezone;

	@NestedProperty
	private Validator validator = new Validator();

	/**
	 * An implementation of javax.faces.view.facelets.ResourceResolver. See
	 * javadoc for details.
	 */
	@ServletContextInitParameter(ResourceResolver.FACELETS_RESOURCE_RESOLVER_PARAM_NAME)
	private Class<? extends ResourceResolver> faceletsResourceResolver;

	/**
	 * Comma-delimited list of faces config files.
	 */
	@ServletContextInitParameter(value = FacesServlet.CONFIG_FILES_ATTR, listSeparator = ",")
	private List<String> configFiles;

	/**
	 * ID for alternate Lifecycle implementations.
	 */
	@ServletContextInitParameter(FacesServlet.LIFECYCLE_ID_ATTR)
	private String lifecycleId;

	/**
	 * If true, consider empty UIInput values to be null instead of empty
	 * string.
	 */
	@ServletContextInitParameter(EMPTY_STRING_AS_NULL)
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
		@ServletContextInitParameter(PartialViewContext.PARTIAL_EXECUTE_PARAM_NAME)
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
		@ServletContextInitParameter(PartialViewContext.PARTIAL_RENDER_PARAM_NAME)
		private Boolean render;
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
		@ServletContextInitParameter(BeanValidator.DISABLE_DEFAULT_BEAN_VALIDATOR_PARAM_NAME)
		private Boolean disableDefaultBeanValidator;
	}
}
