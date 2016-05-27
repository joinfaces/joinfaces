//
// Copyright described at LICENSE.txt
//
package com.github.persapiens.jsfboot.annotations;

import java.util.Set;

/**
 * Convert jsf and cdi enterprise annotation types to spring scope
 */
public class JsfCdiToSpring
{
    public final static String REQUEST = "request";
    public final static String SESSION = "session";
    public final static String SINGLETON = "singleton";
    public final static String PROTOTYPE = "prototype";
    public final static String VIEW = "view";
    
    public static String scopeName( Set<String> annotationTypes )
    {
        String result = null;
        if (annotationTypes != null && !annotationTypes.isEmpty())
        {
            if ( annotationTypes.contains(
                javax.enterprise.context.RequestScoped.class.getName() )
                || annotationTypes.contains(
                javax.faces.bean.RequestScoped.class.getName() ) )
            {
                result = REQUEST;
            }
            else if ( annotationTypes.contains(
                javax.enterprise.context.SessionScoped.class.getName() )
                || annotationTypes.contains(
                javax.faces.bean.SessionScoped.class.getName() ) )
            {
                result = SESSION;
            }
            else if ( annotationTypes.contains(
                javax.enterprise.context.ApplicationScoped.class.getName() )
                || annotationTypes.contains(
                javax.faces.bean.ApplicationScoped.class.getName() ) )
            {
                result = SINGLETON;
            }
            else if ( annotationTypes.contains(
                javax.faces.bean.NoneScoped.class.getName() ) )
            {
                result = PROTOTYPE;
            }
            else if ( annotationTypes.contains(
                javax.faces.view.ViewScoped.class.getName() )
                || annotationTypes.contains(
                javax.faces.bean.ViewScoped.class.getName() ))
            {
                result = VIEW;
            }
            else if ( annotationTypes.contains(
                javax.enterprise.context.ConversationScoped.class.getName() ) )
            {
                result = SESSION;
            }
        }
        return result;
    }
}
