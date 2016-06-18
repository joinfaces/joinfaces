package com.github.persapiens.jsfboot.angularfaces;

import javax.servlet.ServletContext;
import lombok.Builder;
import com.github.persapiens.jsfboot.ServletContextConfigurer;
import de.beyondjava.angularFaces.core.tagTransformer.AngularTagDecorator;
import javax.faces.application.ViewHandler;

public class AngularfacesServletContextConfigurer extends ServletContextConfigurer {

    private AngularfacesProperties angularfacesProperties;

    public static final String PREFFIX = "AngularFaces.";

    @Builder
    public AngularfacesServletContextConfigurer(AngularfacesProperties angularfacesProperties, ServletContext servletContext) {
        super(servletContext, "");
        this.angularfacesProperties = angularfacesProperties;
    }
    
    @Override
    public void configure()
    {        
        setInitParameter(PREFFIX + "addLabels", angularfacesProperties.getAddLabels());
        setInitParameter(PREFFIX + "addMessages", angularfacesProperties.getAddMessages());
        setInitParameter(PREFFIX + "translation", angularfacesProperties.getTranslation());
        setInitParameter(PREFFIX + "includeAngularJS", angularfacesProperties.getIncludeAngularJS());
        setInitParameter(PREFFIX + "includeJQuery", angularfacesProperties.getIncludeJQuery());
        setInitParameter(PREFFIX + "includeJQueryUI", angularfacesProperties.getIncludeJQueryUI());
        setInitParameter(PREFFIX + "includeAngularMessages", angularfacesProperties.getIncludeAngularMessages());
        setInitParameter(PREFFIX + "clientSideMessages", angularfacesProperties.getClientSideMessages());
        setInitParameter(PREFFIX + "includeMainJS", angularfacesProperties.getIncludeMainJS());

        setInitParameter(ViewHandler.FACELETS_DECORATORS_PARAM_NAME, AngularTagDecorator.class.getName());
    }
}
