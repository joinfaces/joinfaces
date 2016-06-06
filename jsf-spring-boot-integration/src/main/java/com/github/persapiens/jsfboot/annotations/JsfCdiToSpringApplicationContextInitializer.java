package com.github.persapiens.jsfboot.annotations;

import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * Jsf Cdi to Spring application context initializer
 */
public class JsfCdiToSpringApplicationContextInitializer implements ApplicationContextInitializer <ConfigurableApplicationContext> {

    /**
     * Add scope metadata resolver, bean name generator and bean factory post processor
     * to enable jsf cdi annotations in spring
     */
    @Override
    public void initialize(ConfigurableApplicationContext c) {
        if (c instanceof AnnotationConfigEmbeddedWebApplicationContext)
        {
            AnnotationConfigEmbeddedWebApplicationContext cc = (AnnotationConfigEmbeddedWebApplicationContext) c;
            cc.setScopeMetadataResolver(new JsfCdiToSpringScopeMetadataResolver());
            cc.setBeanNameGenerator(new JsfCdiToSpringBeanNameGenerator());
        }
        else if (c instanceof AnnotationConfigWebApplicationContext)
        {
            AnnotationConfigWebApplicationContext cc = (AnnotationConfigWebApplicationContext) c;
            cc.setScopeMetadataResolver(new JsfCdiToSpringScopeMetadataResolver());
            cc.setBeanNameGenerator(new JsfCdiToSpringBeanNameGenerator());
        }        
        else if (c instanceof AnnotationConfigApplicationContext)
        {
            AnnotationConfigApplicationContext cc = (AnnotationConfigApplicationContext) c;
            cc.setScopeMetadataResolver(new JsfCdiToSpringScopeMetadataResolver());
            cc.setBeanNameGenerator(new JsfCdiToSpringBeanNameGenerator());            
        }
        
        c.addBeanFactoryPostProcessor(new JsfCdiToSpringBeanFactoryPostProcessor());
    }

}
