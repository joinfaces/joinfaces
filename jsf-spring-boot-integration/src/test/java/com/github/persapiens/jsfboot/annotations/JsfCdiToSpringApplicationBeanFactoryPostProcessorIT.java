package com.github.persapiens.jsfboot.annotations;

import org.testng.annotations.Test;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class JsfCdiToSpringApplicationBeanFactoryPostProcessorIT {

    public void testRegisteredScopeView() {
        ConfigurableListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanFactoryPostProcessor beanFactoryPostProcessor = new JsfCdiToSpringBeanFactoryPostProcessor();
        
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        
        assertThat(beanFactory.getRegisteredScope("view")).isInstanceOf(ViewScope.class);
    }

}
