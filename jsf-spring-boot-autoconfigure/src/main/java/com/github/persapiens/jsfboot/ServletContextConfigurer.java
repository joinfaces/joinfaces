package com.github.persapiens.jsfboot;

import javax.servlet.ServletContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class ServletContextConfigurer {
    
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
    
    protected void setInitParameter(String name, String value) {
        if (!isNullOrEmpty(value))
        {
            servletContext.setInitParameter(fullName(name), value);
        }
    }
    
    protected void setInitParameter(String name, Boolean value) {
        if (value != null)
        {
            servletContext.setInitParameter(fullName(name), Boolean.toString(value));
        }
    }
    
    protected void setInitParameter(String name, Long value) {
        if (value != null)
        {
            servletContext.setInitParameter(fullName(name), Long.toString(value));
        }
    }
    
    public abstract void configure();
    
}
