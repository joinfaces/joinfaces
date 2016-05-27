//
// Copyright described at LICENSE.txt
//
package com.github.persapiens.jsfboot.annotations;

import java.util.Set;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;

/**
 * Convert jsf and cdi annotation types to spring scope
 */
public class JsfCdiToSpring
{
    public static String scopeName( AnnotatedBeanDefinition annDef )
    {
        String result = null;
        Set<String> annotationTypes = annDef.getMetadata().getAnnotationTypes();
        if (annotationTypes != null && !annotationTypes.isEmpty())
        {
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
        }
        return result;
    }
}
