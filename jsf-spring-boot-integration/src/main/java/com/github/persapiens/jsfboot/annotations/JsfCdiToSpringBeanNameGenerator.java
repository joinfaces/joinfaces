//
// Copyright described at LICENSE.txt
//
package com.github.persapiens.jsfboot.annotations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

/**
 * Spring Bean Name Generator to support cdi and jsf annotations
 */
public class JsfCdiToSpringBeanNameGenerator
    extends AnnotationBeanNameGenerator
{
    private static final Logger logger = LoggerFactory
			.getLogger(JsfCdiToSpringBeanNameGenerator.class);
    
    @Override
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        String result = super.generateBeanName(definition, registry);
        
        if ( definition instanceof AnnotatedBeanDefinition )
        {
            AnnotatedBeanDefinition annDef = ( AnnotatedBeanDefinition ) definition;

            String scopeName = JsfCdiToSpring.scopeName(annDef);
            if ( scopeName != null )
            {
                definition.setScope( scopeName );

                logger.debug( definition.getBeanClassName()
                    + " - Scope(" + definition.getScope().toUpperCase() 
                    + ")");
            }
        }
        
        return result;
    }
}
