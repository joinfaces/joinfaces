package com.github.persapiens.jsfboot.mojarra;

import com.github.persapiens.jsfboot.JsfClassFactory;
import com.sun.faces.config.FacesInitializer;
import java.util.Set;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import com.github.persapiens.jsfboot.JsfClassFactoryConfiguration;

public class MojarraServletContextInitializer implements ServletContextInitializer, JsfClassFactoryConfiguration {
	
    public static final String ANOTHER_FACES_CONFIG = "com/sun/faces/jsf-ri-runtime.xml";
    
	private final MojarraProperties mojarraProperties;

    public MojarraServletContextInitializer(MojarraProperties mojarraProperties) {
        this.mojarraProperties = mojarraProperties;
    }
    
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
    
    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        MojarraServletContextConfigurer.builder()
            .mojarraProperties(mojarraProperties)
            .servletContext(sc)
            .build()
            .configure();
        
        ServletContainerInitializer servletContainerInitializer = getServletContainerInitializer();
        Set<Class<?>> classes = JsfClassFactory.builder()
            .jsfAnnotatedClassFactoryConfiguration(this)
            .build().find();
        servletContainerInitializer.onStartup(classes, sc);
    }            
}
