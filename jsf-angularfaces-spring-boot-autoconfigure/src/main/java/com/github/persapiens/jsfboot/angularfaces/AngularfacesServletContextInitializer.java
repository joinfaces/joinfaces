package com.github.persapiens.jsfboot.angularfaces;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.boot.context.embedded.ServletContextInitializer;

public class AngularfacesServletContextInitializer implements ServletContextInitializer {

	private final AngularfacesProperties angularfacesProperties;

    public AngularfacesServletContextInitializer(AngularfacesProperties angularfacesProperties) {
        this.angularfacesProperties = angularfacesProperties;
    }
    
    @Override
    public void onStartup(ServletContext sc) throws ServletException {                
        AngularfacesServletContextConfigurer.builder()
            .angularfacesProperties(angularfacesProperties)
            .servletContext(sc)
            .build()
            .configure();
    }
}
