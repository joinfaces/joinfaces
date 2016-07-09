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

package com.github.persapiens.jsfboot.javaxfaces;

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
import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;

import com.github.persapiens.jsfboot.ServletContextConfigurer;

import lombok.Builder;

/**
 * Servlet Context Configurer of Javax Faces properties.
 * @author Marcelo Fernandes
 */
public class JavaxFacesServletContextConfigurer extends ServletContextConfigurer {

	private JavaxFacesProperties javaxFacesProperties;

	@Builder
	public JavaxFacesServletContextConfigurer(JavaxFacesProperties javaxFacesProperties, ServletContext servletContext) {
		super(servletContext, "");
		this.javaxFacesProperties = javaxFacesProperties;
	}

	@Override
	public void configure() {
		setInitParameter(ProjectStage.PROJECT_STAGE_PARAM_NAME, this.javaxFacesProperties.getProjectStage());

		setInitParameter(ResourceHandler.RESOURCE_EXCLUDES_PARAM_NAME, this.javaxFacesProperties.getResourceExcludes());
		setInitParameter(ResourceHandler.WEBAPP_CONTRACTS_DIRECTORY_PARAM_NAME, this.javaxFacesProperties.getWebappContractsDirectory());
		setInitParameter(ResourceHandler.WEBAPP_RESOURCES_DIRECTORY_PARAM_NAME, this.javaxFacesProperties.getWebappResourcesDirectory());

		setInitParameter(StateManager.FULL_STATE_SAVING_VIEW_IDS_PARAM_NAME, this.javaxFacesProperties.getFullStateSavingViewIds());
		setInitParameter(StateManager.PARTIAL_STATE_SAVING_PARAM_NAME, this.javaxFacesProperties.getPartialStateSaving());
		setInitParameter(StateManager.SERIALIZE_SERVER_STATE_PARAM_NAME, this.javaxFacesProperties.getSerializeServerState());
		setInitParameter(StateManager.STATE_SAVING_METHOD_PARAM_NAME, this.javaxFacesProperties.getStateSavingMethod());

		setInitParameter(ViewHandler.DEFAULT_SUFFIX_PARAM_NAME, this.javaxFacesProperties.getDefaultSuffix());
		setInitParameter(ViewHandler.DISABLE_FACELET_JSF_VIEWHANDLER_PARAM_NAME, this.javaxFacesProperties.getDisableFaceletJsfViewhandler());
		setInitParameter(ViewHandler.FACELETS_BUFFER_SIZE_PARAM_NAME, this.javaxFacesProperties.getFaceletsBufferSize());
		setInitParameter(ViewHandler.FACELETS_DECORATORS_PARAM_NAME, this.javaxFacesProperties.getFaceletsDecorators());
		setInitParameter(ViewHandler.FACELETS_LIBRARIES_PARAM_NAME, this.javaxFacesProperties.getFaceletsLibraries());
		setInitParameter(ViewHandler.FACELETS_REFRESH_PERIOD_PARAM_NAME, this.javaxFacesProperties.getFaceletsRefreshPeriod());
		setInitParameter(ViewHandler.FACELETS_SKIP_COMMENTS_PARAM_NAME, this.javaxFacesProperties.getFaceletsSkipComments());
		setInitParameter(ViewHandler.FACELETS_SUFFIX_PARAM_NAME, this.javaxFacesProperties.getFaceletsSuffix());
		setInitParameter(ViewHandler.FACELETS_VIEW_MAPPINGS_PARAM_NAME, this.javaxFacesProperties.getFaceletsViewMappings());

		setInitParameter(UIComponent.HONOR_CURRENT_COMPONENT_ATTRIBUTES_PARAM_NAME, this.javaxFacesProperties.getHonorCurrentComponentAttributes());

		setInitParameter(UIInput.VALIDATE_EMPTY_FIELDS_PARAM_NAME, this.javaxFacesProperties.getValidateEmptyFields());

		setInitParameter(UINamingContainer.SEPARATOR_CHAR_PARAM_NAME, this.javaxFacesProperties.getSeparatorChar());

		setInitParameter(PartialViewContext.PARTIAL_EXECUTE_PARAM_NAME, this.javaxFacesProperties.getPartial().getExecute());
		setInitParameter(PartialViewContext.PARTIAL_RENDER_PARAM_NAME, this.javaxFacesProperties.getPartial().getRender());
		setInitParameter(PartialViewContext.RESET_VALUES_PARAM_NAME, this.javaxFacesProperties.getPartial().getResetValues());

		setInitParameter(Converter.DATETIMECONVERTER_DEFAULT_TIMEZONE_IS_SYSTEM_TIMEZONE_PARAM_NAME, this.javaxFacesProperties.getDatetimeconverterDefaultTimezoneIsSystemTimezone());

		setInitParameter(FlowHandler.NULL_FLOW, this.javaxFacesProperties.getFlow().getNullFlow());

		setInitParameter(BeanValidator.DISABLE_DEFAULT_BEAN_VALIDATOR_PARAM_NAME, this.javaxFacesProperties.getValidator().getDisableDefaultBeanValidator());

		setInitParameter(ResourceResolver.FACELETS_RESOURCE_RESOLVER_PARAM_NAME, this.javaxFacesProperties.getFaceletsResourceResolver());

		setInitParameter(FacesServlet.CONFIG_FILES_ATTR, this.javaxFacesProperties.getConfigFiles());
		setInitParameter(FacesServlet.LIFECYCLE_ID_ATTR, this.javaxFacesProperties.getLifecycleId());

		setInitParameter(ClientWindow.CLIENT_WINDOW_MODE_PARAM_NAME, this.javaxFacesProperties.getClientWindowMode());

		setInitParameter(JavaxFacesProperties.EMPTY_STRING_AS_NULL, this.javaxFacesProperties.getInterpretEmptyStringSubmittedValuesAsNull());

	}
}
