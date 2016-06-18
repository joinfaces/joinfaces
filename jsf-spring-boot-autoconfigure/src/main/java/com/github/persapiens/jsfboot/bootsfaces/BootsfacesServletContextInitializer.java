package com.github.persapiens.jsfboot.bootsfaces;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.boot.context.embedded.ServletContextInitializer;

public class BootsfacesServletContextInitializer implements ServletContextInitializer {

	private final BootsfacesProperties bootsfacesProperties;

    public BootsfacesServletContextInitializer(BootsfacesProperties bootsfacesProperties) {
        this.bootsfacesProperties = bootsfacesProperties;
    }
    
    @Override
    public void onStartup(ServletContext sc) throws ServletException {                
        BootsfacesServletContextConfigurer.builder()
            .bootsfacesProperties(bootsfacesProperties)
            .servletContext(sc)
            .build()
            .configure();
    }
}
