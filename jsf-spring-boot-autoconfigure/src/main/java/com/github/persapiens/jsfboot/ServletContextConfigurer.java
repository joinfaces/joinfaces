package com.github.persapiens.jsfboot;

import javax.servlet.ServletContext;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
public abstract class ServletContextConfigurer {
    
    private static final Logger logger = LoggerFactory
			.getLogger(ServletContextConfigurer.class);
        
    private ServletContext servletContext;
    
    private String preffix;
    
    private boolean isNullOrEmpty(String s)
    {
        return s == null || s.trim().isEmpty();
    }
    
    private String fullName(String name)
    {
        String result = name;
        if (!isNullOrEmpty(preffix))
        {
            result = preffix + "." + result;
        }
        return result;
    }
    
    private void setInitParameterWithDebug(String name, String value) {
        name = fullName(name);
        
        servletContext.setInitParameter(name, value);

        logger.debug(name + " = " + value);
    }
    
    protected void setInitParameter(String name, String value) {
        if (!isNullOrEmpty(value))
        {
            setInitParameterWithDebug(name, value);
        }
    }
    
    protected void setInitParameter(String name, Boolean value) {
        if (value != null)
        {
            setInitParameterWithDebug(name, Boolean.toString(value));
        }
    }
    
    protected void setInitParameter(String name, Integer value) {
        if (value != null)
        {
            setInitParameterWithDebug(name, Integer.toString(value));
        }
    }
    
    public abstract void configure();
    
}
