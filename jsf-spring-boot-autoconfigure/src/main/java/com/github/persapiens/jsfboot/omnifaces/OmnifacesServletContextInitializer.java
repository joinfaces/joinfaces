package com.github.persapiens.jsfboot.omnifaces;


import com.github.persapiens.jsfboot.JsfAnnotatedClassFactory;
import com.github.persapiens.jsfboot.JsfAnnotatedClassFactoryConfiguration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;
import javax.servlet.ServletContainerInitializer;
import org.omnifaces.facesviews.FacesViewsInitializer;
import org.springframework.boot.context.embedded.ServletContextInitializer;

public class OmnifacesServletContextInitializer implements ServletContextInitializer, JsfAnnotatedClassFactoryConfiguration {

	private final OmnifacesProperties omnifacesProperties;

    public OmnifacesServletContextInitializer(OmnifacesProperties omnifacesProperties) {
        this.omnifacesProperties = omnifacesProperties;
    }
    
    private ServletContainerInitializer servletContainerInitializer;
    
    @Override
    public ServletContainerInitializer getServletContainerInitializer()
    {
        if (servletContainerInitializer == null)
        {
            servletContainerInitializer = new FacesViewsInitializer();
        }
        return servletContainerInitializer;
    }
    
    @Override
    public String getAnotherFacesConfig() {
        return null;
    }
    
    @Override
    public boolean isExcludeScopedAnnotations() {
        return true;
    }
    
    @Override
    public void onStartup(ServletContext sc) throws ServletException {                
        OmnifacesServletContextConfigurer.builder()
            .omnifacesProperties(omnifacesProperties)
            .servletContext(sc)
            .build()
            .configure();
        
        ServletContainerInitializer servletContainerInitializer = getServletContainerInitializer();
        Set<Class<?>> classes = JsfAnnotatedClassFactory.builder()
            .jsfAnnotatedClassFactoryConfiguration(this)
            .build().find();
        servletContainerInitializer.onStartup(classes, sc);
    }
}
