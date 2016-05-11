//
// Copyright described at LICENSE.txt
//
package org.persapiens.jsfboot;

import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationScopeMetadataResolver;
import org.springframework.context.annotation.ScopeMetadata;

/**
 * Spring Metadata Resolver to support cdi and jsf annotations
 */
public class CdiScopeResolver
    extends AnnotationScopeMetadataResolver
{
    private static final Logger logger = LoggerFactory
			.getLogger(CdiScopeResolver.class);
    
    public String scopeName( Set<String> annotationTypes )
    {
        String result = null;
        if ( annotationTypes.contains(
            javax.enterprise.context.RequestScoped.class.getName() )
            || annotationTypes.contains(
            javax.faces.bean.RequestScoped.class.getName() ) )
        {
            result = "request";
        }
        else if ( annotationTypes.contains(
            javax.enterprise.context.SessionScoped.class.getName() )
            || annotationTypes.contains(
            javax.faces.bean.SessionScoped.class.getName() ) )
        {
            result = "session";
        }
        else if ( annotationTypes.contains(
            javax.enterprise.context.ApplicationScoped.class.getName() )
            || annotationTypes.contains(
            javax.faces.bean.ApplicationScoped.class.getName() ) )
        {
            result = "singleton";
        }
        else if ( annotationTypes.contains(
            javax.faces.bean.NoneScoped.class.getName() ) )
        {
            result = "prototype";
        }
        else if ( annotationTypes.contains(
            javax.faces.view.ViewScoped.class.getName() )
            || annotationTypes.contains(
            javax.faces.bean.ViewScoped.class.getName() ))
        {
            result = "view";
        }
        else if ( annotationTypes.contains(
            javax.enterprise.context.ConversationScoped.class.getName() ) )
        {
            result = "session";
        }
        return result;
    }
    
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

            String scopeName = scopeName(annDef.getMetadata().getAnnotationTypes());
            if ( scopeName != null )
            {
                metadata.setScopeName( scopeName );
            }
            else
            {
                // do the regular Spring stuff..
                metadata = super.resolveScopeMetadata( definition );
            }
        }

        logger.debug( definition.getBeanClassName()
            + " - " + metadata.getScopeName().toUpperCase() 
            + " - " + metadata.getScopedProxyMode().toString().toUpperCase());
        
        return metadata;
    }
}
