package com.github.persapiens.jsfboot;

import com.sun.faces.config.FacesInitializer;
import javax.servlet.ServletContainerInitializer;

public class MojarraServletContextInitializer implements JsfClassFactoryConfiguration {
	
    public static final String ANOTHER_FACES_CONFIG = "com/sun/faces/jsf-ri-runtime.xml";
    
    private ServletContainerInitializer servletContainerInitializer;
    
    @Override
    public ServletContainerInitializer getServletContainerInitializer()
    {
        if (servletContainerInitializer == null)
        {
            servletContainerInitializer = new FacesInitializer();
        }
        return servletContainerInitializer;
    }
    
    @Override
    public String getAnotherFacesConfig() {
        return ANOTHER_FACES_CONFIG;
    }
    
    @Override
    public boolean isExcludeScopedAnnotations() {
        return true;
    }
}
