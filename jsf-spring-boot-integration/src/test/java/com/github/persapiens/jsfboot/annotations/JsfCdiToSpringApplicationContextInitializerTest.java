package com.github.persapiens.jsfboot.annotations;

import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * Jsf Cdi to Spring application context initializer
 */
@Test
public class JsfCdiToSpringApplicationContextInitializerTest {

    public void testAnnotationConfigEmbeddedWebApplicationContext() {
        AnnotationConfigEmbeddedWebApplicationContext annotationConfigEmbeddedWebApplicationContext = new AnnotationConfigEmbeddedWebApplicationContext();
        JsfCdiToSpringApplicationContextInitializer initializer = new JsfCdiToSpringApplicationContextInitializer();
        
        initializer.initialize(annotationConfigEmbeddedWebApplicationContext);
        
        assertThat(annotationConfigEmbeddedWebApplicationContext.getBeanFactoryPostProcessors().isEmpty()).isFalse();
    }

    public void testAnnotationConfigWebApplicationContext() {
        AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
        JsfCdiToSpringApplicationContextInitializer initializer = new JsfCdiToSpringApplicationContextInitializer();
        
        initializer.initialize(annotationConfigWebApplicationContext);
        
        assertThat(annotationConfigWebApplicationContext.getBeanFactoryPostProcessors().isEmpty()).isFalse();
    }

    public void testAnnotationConfigApplicationContext() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        JsfCdiToSpringApplicationContextInitializer initializer = new JsfCdiToSpringApplicationContextInitializer();
        
        initializer.initialize(annotationConfigApplicationContext);
        
        assertThat(annotationConfigApplicationContext.getBeanFactoryPostProcessors().isEmpty()).isFalse();
    }

}
