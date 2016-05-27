//
// Copyright described at LICENSE.txt
//
package com.github.persapiens.jsfboot.annotations;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * Add custom jsf cdi scope implementations
 */
public class JsfCdiToSpringBeanFactoryPostProcessor implements BeanFactoryPostProcessor
{
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory clbf) throws BeansException {
        clbf.registerScope("view", new ViewScope());
    }
}
