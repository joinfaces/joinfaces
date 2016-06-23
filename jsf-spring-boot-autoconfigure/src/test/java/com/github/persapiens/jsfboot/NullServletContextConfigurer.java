package com.github.persapiens.jsfboot;

import javax.servlet.ServletContext;
import lombok.Builder;

public class NullServletContextConfigurer extends ServletContextConfigurer {

    @Builder
    public NullServletContextConfigurer(ServletContext servletContext) {
        super(servletContext, null);
    }
    
    @Override
    public void configure()
    {
        setInitParameter(null, "");        
    }
}
