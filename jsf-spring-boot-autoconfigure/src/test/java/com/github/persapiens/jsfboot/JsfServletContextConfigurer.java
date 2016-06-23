package com.github.persapiens.jsfboot;

import javax.servlet.ServletContext;
import lombok.Builder;

public class JsfServletContextConfigurer extends ServletContextConfigurer {

    @Builder
    public JsfServletContextConfigurer(ServletContext servletContext) {
        super(servletContext, "jsf");
    }
    
    @Override
    public void configure()
    {
        setInitParameter("emptyString", "");
        setInitParameter("key", "value");
    }
}
