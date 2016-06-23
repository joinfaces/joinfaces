package com.github.persapiens.jsfboot.primefaces;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.boot.context.embedded.ServletContextInitializer;

public class PrimefacesServletContextInitializer implements ServletContextInitializer {

	private final PrimefacesProperties primefacesProperties;

    public PrimefacesServletContextInitializer(PrimefacesProperties primefacesProperties) {
        this.primefacesProperties = primefacesProperties;
    }
    
    @Override
    public void onStartup(ServletContext sc) throws ServletException {                
        PrimefacesServletContextConfigurer.builder()
            .primefacesProperties(primefacesProperties)
            .servletContext(sc)
            .build()
            .configure();
    }
}
