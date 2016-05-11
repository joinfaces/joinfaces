//
// Copyright described at LICENSE.txt
//
package org.persapiens.jsfboot.javaxfaces;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * Implementation of view scope
 */
public class ViewScope implements Scope
{
    @Override
    public Object get( String name, ObjectFactory objectFactory )
    {
        Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();

        if ( viewMap.containsKey( name ) )
        {
            return viewMap.get( name );
        }
        else
        {
            Object object = objectFactory.getObject();
            viewMap.put( name, object );

            return object;
        }
    }

    @Override
    public Object remove( String name )
    {
        return FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove( name );
    }

    @Override
    public String getConversationId()
    {
        return null;
    }

    @Override
    public void registerDestructionCallback( String name, Runnable callback )
    {
        //Not supported
    }

    @Override
    public Object resolveContextualObject( String key )
    {
        return null;
    }
}
