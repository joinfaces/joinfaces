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
import lombok.Builder;
import com.github.persapiens.jsfboot.ServletContextConfigurer;

public class JavaxFacesServletContextConfigurer extends ServletContextConfigurer {

    private JavaxFacesProperties javaxFacesProperties;

    @Builder
    public JavaxFacesServletContextConfigurer(JavaxFacesProperties javaxFacesProperties, ServletContext servletContext) {
        super(servletContext, "");
        this.javaxFacesProperties = javaxFacesProperties;
    }
    
    @Override
    public void configure()
    {
        setInitParameter(ProjectStage.PROJECT_STAGE_PARAM_NAME, javaxFacesProperties.getProjectStage());
        
        setInitParameter(ResourceHandler.RESOURCE_EXCLUDES_PARAM_NAME, javaxFacesProperties.getResourceExcludes());
        setInitParameter(ResourceHandler.WEBAPP_CONTRACTS_DIRECTORY_PARAM_NAME, javaxFacesProperties.getWebappContractsDirectory());
        setInitParameter(ResourceHandler.WEBAPP_RESOURCES_DIRECTORY_PARAM_NAME, javaxFacesProperties.getWebappResourcesDirectory());
        
        setInitParameter(StateManager.FULL_STATE_SAVING_VIEW_IDS_PARAM_NAME, javaxFacesProperties.getFullStateSavingViewIds());
        setInitParameter(StateManager.PARTIAL_STATE_SAVING_PARAM_NAME, javaxFacesProperties.getPartialStateSaving());
        setInitParameter(StateManager.SERIALIZE_SERVER_STATE_PARAM_NAME, javaxFacesProperties.getSerializeServerState());
        setInitParameter(StateManager.STATE_SAVING_METHOD_PARAM_NAME, javaxFacesProperties.getStateSavingMethod());
        
        setInitParameter(ViewHandler.DEFAULT_SUFFIX_PARAM_NAME, javaxFacesProperties.getDefaultSuffix());
        setInitParameter(ViewHandler.DISABLE_FACELET_JSF_VIEWHANDLER_PARAM_NAME, javaxFacesProperties.getDisableFaceletJsfViewhandler());
        setInitParameter(ViewHandler.FACELETS_BUFFER_SIZE_PARAM_NAME, javaxFacesProperties.getFaceletsBufferSize());
        setInitParameter(ViewHandler.FACELETS_DECORATORS_PARAM_NAME, javaxFacesProperties.getFaceletsDecorators());
        setInitParameter(ViewHandler.FACELETS_LIBRARIES_PARAM_NAME, javaxFacesProperties.getFaceletsLibraries());
        setInitParameter(ViewHandler.FACELETS_REFRESH_PERIOD_PARAM_NAME, javaxFacesProperties.getFaceletsRefreshPeriod());
        setInitParameter(ViewHandler.FACELETS_SKIP_COMMENTS_PARAM_NAME, javaxFacesProperties.getFaceletsSkipComments());
        setInitParameter(ViewHandler.FACELETS_SUFFIX_PARAM_NAME, javaxFacesProperties.getFaceletsSuffix());
        setInitParameter(ViewHandler.FACELETS_VIEW_MAPPINGS_PARAM_NAME, javaxFacesProperties.getFaceletsViewMappings());
        
        setInitParameter(UIComponent.HONOR_CURRENT_COMPONENT_ATTRIBUTES_PARAM_NAME
            , javaxFacesProperties.getHonorCurrentComponentAttributes());
        
        setInitParameter(UIInput.VALIDATE_EMPTY_FIELDS_PARAM_NAME
            , javaxFacesProperties.getValidateEmptyFields());
        
        setInitParameter(UINamingContainer.SEPARATOR_CHAR_PARAM_NAME
            , javaxFacesProperties.getSeparatorChar());
                
        setInitParameter(PartialViewContext.PARTIAL_EXECUTE_PARAM_NAME
            , javaxFacesProperties.getPartial().getExecute());
        setInitParameter(PartialViewContext.PARTIAL_RENDER_PARAM_NAME
            , javaxFacesProperties.getPartial().getRender());
        setInitParameter(PartialViewContext.RESET_VALUES_PARAM_NAME
            , javaxFacesProperties.getPartial().getResetValues());        
        
        setInitParameter(Converter.DATETIMECONVERTER_DEFAULT_TIMEZONE_IS_SYSTEM_TIMEZONE_PARAM_NAME
            , javaxFacesProperties.getDatetimeconverterDefaultTimezoneIsSystemTimezone());
        
        setInitParameter(FlowHandler.NULL_FLOW
            , javaxFacesProperties.getFlow().getNullFlow());
        
        setInitParameter(BeanValidator.DISABLE_DEFAULT_BEAN_VALIDATOR_PARAM_NAME
            , javaxFacesProperties.getValidator().getDisableDefaultBeanValidator());
        
        setInitParameter(ResourceResolver.FACELETS_RESOURCE_RESOLVER_PARAM_NAME
            , javaxFacesProperties.getFaceletsResourceResolver());        
                
        setInitParameter(FacesServlet.CONFIG_FILES_ATTR
            , javaxFacesProperties.getConfigFiles());        
        setInitParameter(FacesServlet.LIFECYCLE_ID_ATTR
            , javaxFacesProperties.getLifecycleId());        
        
        setInitParameter(ClientWindow.CLIENT_WINDOW_MODE_PARAM_NAME
            , javaxFacesProperties.getClientWindowMode());        
        
        setInitParameter(JavaxFacesProperties.EMPTY_STRING_AS_NULL
            , javaxFacesProperties.getInterpretEmptyStringSubmittedValuesAsNull());        
        
    }
}
