package com.github.persapiens.jsfboot.myfaces;

import com.github.persapiens.jsfboot.JsfClassFactory;
import com.github.persapiens.jsfboot.JsfClassFactoryConfiguration;
import java.util.Set;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.apache.myfaces.ee6.MyFacesContainerInitializer;
import org.springframework.boot.context.embedded.ServletContextInitializer;

public class MyfacesServletContextInitializer implements ServletContextInitializer, JsfClassFactoryConfiguration {
	
    public static final String ANOTHER_FACES_CONFIG = "META-INF/standard-faces-config.xml";
    
	private final MyfacesProperties myfacesProperties;

    public MyfacesServletContextInitializer(MyfacesProperties myfacesProperties) {
        this.myfacesProperties = myfacesProperties;
    }
    
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
    
    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        MyfacesServletContextConfigurer.builder()
            .myfacesProperties(myfacesProperties)
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
