//
// Copyright described at LICENSE.txt
//
package com.github.persapiens.jsfboot.annotations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationScopeMetadataResolver;
import org.springframework.context.annotation.ScopeMetadata;

/**
 * Spring Metadata Resolver to support cdi and jsf annotations
 */
public class JsfCdiToSpringScopeMetadataResolver
    extends AnnotationScopeMetadataResolver
{
    private static final Logger logger = LoggerFactory
			.getLogger(JsfCdiToSpringScopeMetadataResolver.class);
    
    /**
     * Discover spring scope using bean definition
     * @param definition bean definition
     * @return scope metadata
     */
    @Override
    public ScopeMetadata resolveScopeMetadata( BeanDefinition definition )
    {
        ScopeMetadata metadata = new ScopeMetadata();
        if ( definition instanceof AnnotatedBeanDefinition )
        {
            AnnotatedBeanDefinition annDef = ( AnnotatedBeanDefinition ) definition;

            String scopeName = JsfCdiToSpring.scopeName(annDef);
            if ( scopeName != null )
            {
                metadata.setScopeName( scopeName );

                logger.debug( definition.getBeanClassName()
                    + " - Scope(" + metadata.getScopeName().toUpperCase() 
                    + ") - " + metadata.getScopedProxyMode().toString().toUpperCase());
            }
            else
            {
                // do the regular Spring stuff..
                metadata = super.resolveScopeMetadata( definition );
            }
        }
        
        return metadata;
    }
}
