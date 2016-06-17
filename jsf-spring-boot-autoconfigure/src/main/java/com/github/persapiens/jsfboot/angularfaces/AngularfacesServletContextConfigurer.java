package com.github.persapiens.jsfboot.angularfaces;

import javax.servlet.ServletContext;
import lombok.Builder;
import com.github.persapiens.jsfboot.ServletContextConfigurer;

public class AngularfacesServletContextConfigurer extends ServletContextConfigurer {

    private AngularfacesProperties angularfacesProperties;

    public static final String PREFFIX = "AngularFaces";

    @Builder
    public AngularfacesServletContextConfigurer(AngularfacesProperties angularfacesProperties, ServletContext servletContext) {
        super(servletContext, PREFFIX);
        this.angularfacesProperties = angularfacesProperties;
    }
    
    @Override
    public void configure()
    {        
        setInitParameter("addLabels", angularfacesProperties.getAddLabels());
        setInitParameter("addMessages", angularfacesProperties.getAddMessages());
        setInitParameter("translation", angularfacesProperties.getTranslation());
        setInitParameter("includeAngularJS", angularfacesProperties.getIncludeAngularJS());
        setInitParameter("includeJQuery", angularfacesProperties.getIncludeJQuery());
        setInitParameter("includeJQueryUI", angularfacesProperties.getIncludeJQueryUI());
        setInitParameter("includeAngularMessages", angularfacesProperties.getIncludeAngularMessages());
        setInitParameter("clientSideMessages", angularfacesProperties.getClientSideMessages());
        setInitParameter("includeMainJS", angularfacesProperties.getIncludeMainJS());
    }
}
