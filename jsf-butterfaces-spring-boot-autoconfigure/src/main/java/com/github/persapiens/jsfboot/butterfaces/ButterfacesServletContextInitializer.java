package com.github.persapiens.jsfboot.butterfaces;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.boot.context.embedded.ServletContextInitializer;

public class ButterfacesServletContextInitializer implements ServletContextInitializer {

	private final ButterfacesProperties butterfacesProperties;

    public ButterfacesServletContextInitializer(ButterfacesProperties butterfacesProperties) {
        this.butterfacesProperties = butterfacesProperties;
    }
    
    @Override
    public void onStartup(ServletContext sc) throws ServletException {                
        ButterfacesServletContextConfigurer.builder()
            .butterfacesProperties(butterfacesProperties)
            .servletContext(sc)
            .build()
            .configure();
    }
}
