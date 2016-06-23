package com.github.persapiens.jsfboot;

import javax.servlet.ServletContainerInitializer;
import org.apache.myfaces.ee6.MyFacesContainerInitializer;

public class MyfacesServletContextInitializer implements JsfClassFactoryConfiguration {
	
    public static final String ANOTHER_FACES_CONFIG = "META-INF/standard-faces-config.xml";
    
    private ServletContainerInitializer servletContainerInitializer;
    
    @Override
    public ServletContainerInitializer getServletContainerInitializer()
    {
        if (servletContainerInitializer == null)
        {
            servletContainerInitializer = new MyFacesContainerInitializer();
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
