package com.github.persapiens.jsfboot;

import javax.servlet.ServletContainerInitializer;

/**
 * Configuration of Jsf Annotated Class Factory
 */
public interface JsfAnnotatedClassFactoryConfiguration {

    /**
     * Servlet container initializer that contains handleTypes
     */
    ServletContainerInitializer getServletContainerInitializer();

    /**
     * Another faces config resource to include in search
     */
    String getAnotherFacesConfig();
    
    /**
     * Inform if exclude scoped annotations in search
     */
    boolean isExcludeScopedAnnotations();
}
