package com.github.persapiens.jsfboot.javaxfaces;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.boot.context.embedded.ServletContextInitializer;

public class JavaxFacesServletContextInitializer implements ServletContextInitializer {

	private final JavaxFacesProperties javaxFacesProperties;

    public JavaxFacesServletContextInitializer(JavaxFacesProperties javaxFacesProperties) {
        this.javaxFacesProperties = javaxFacesProperties;
    }
    
    @Override
    public void onStartup(ServletContext sc) throws ServletException {                
        JavaxFacesServletContextConfigurer.builder()
            .javaxFacesProperties(javaxFacesProperties)
            .servletContext(sc)
            .build()
            .configure();
    }            
}
