package com.github.persapiens.jsfboot;

import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.faces.bean.NoneScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.servlet.annotation.HandlesTypes;
import lombok.Builder;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

/**
 * Factory of classes annotated with jsf types handled by servlet context initializer
 */
@Builder
public class JsfAnnotatedClassFactory {
    
    private static final Logger log = LoggerFactory
			.getLogger(JsfAnnotatedClassFactory.class);    

    @NonNull
    private JsfAnnotatedClassFactoryConfiguration jsfAnnotatedClassFactoryConfiguration;
    
    /**
     * Return list of annotations to exclude from handlesType.
     * Ignore ViewScoped, SessionScoped, RequestScoped and NoneScoped annotations 
     * because spring will take care of them.
     */
    private Set<Class<? extends Annotation>> annotationsToExclude() {
        Set<Class<? extends Annotation>> result = new HashSet<>();
        if (jsfAnnotatedClassFactoryConfiguration.isExcludeScopedAnnotations())
        {
            result.add(ViewScoped.class);
            result.add(SessionScoped.class);
            result.add(RequestScoped.class);
            result.add(NoneScoped.class);
        }
        return result;
    }
    
    /**
     * Return set of annotation classes to be handled by servlet container initializar
     */
    private Set<Class<? extends Annotation>> handlesTypes() {
        Set<Class<? extends Annotation>> result = null;
        
        HandlesTypes ht = jsfAnnotatedClassFactoryConfiguration.getServletContainerInitializer().getClass().getAnnotation(HandlesTypes.class);
        if (ht != null) {
            Class<?>[] types = ht.value();
        
            if (types != null) {
                Set<Class<? extends Annotation>> annotationsToExclude = annotationsToExclude();
                
                for (Class<?> type : types) {
                    if (type.isAnnotation())
                    {
                        Class<? extends Annotation> annotation = (Class<? extends Annotation>) type;
                        if (!annotationsToExclude.contains(annotation))
                        {
                            if (result == null)
                            {
                                result = new HashSet<>();
                            }
                            result.add(annotation);
                        }
                    }
                }
            }        
        }
        
        return result;
    }
    
    /**
     * Find classes annotated by types handled by servlet container initilizer.
     * Search libraries with anotherFacesConfig also.
     */
    public Set<Class<?>> find() {        
        Set<Class<?>> result = new HashSet<>();
        
        Set<Class<? extends Annotation>> handlesTypes = handlesTypes();
        // check if any annotation type is handled
        if (handlesTypes != null && !handlesTypes.isEmpty())
        {
            // get only urls of libraries that contains jsf types
            Collection<URL> urls = new ArrayList<>(ClasspathHelper.forResource("META-INF/faces-config.xml", this.getClass().getClassLoader()));
            // add jsf library with anotherFacesConfig
            String anotherFacesConfig = jsfAnnotatedClassFactoryConfiguration.getAnotherFacesConfig();
            if (anotherFacesConfig != null)
            {
                urls.addAll(ClasspathHelper.forResource(anotherFacesConfig, this.getClass().getClassLoader()));
            }

            // create reflections
            Reflections reflections = new Reflections(new ConfigurationBuilder().setUrls(urls));
            
            // add types annotated for each type to be handled
            for (Class<? extends Annotation> annotationType : handlesTypes)
            {
                result.addAll(reflections.getTypesAnnotatedWith(annotationType));
            }
        }
        
        return result;        
    }
}
