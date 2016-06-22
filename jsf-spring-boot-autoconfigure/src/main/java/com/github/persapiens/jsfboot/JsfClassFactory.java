package com.github.persapiens.jsfboot;

import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.faces.bean.NoneScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.servlet.annotation.HandlesTypes;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

/**
 * Factory of classes with jsf types handled by servlet context initializer
 */
@Builder
public class JsfClassFactory {
    
    private static final Logger log = LoggerFactory
			.getLogger(JsfClassFactory.class);    

    @NonNull
    private JsfClassFactoryConfiguration jsfAnnotatedClassFactoryConfiguration;
    
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

    @Getter
    private static class TypesToBeHandled {
        private Set<Class<? extends Annotation>> annotationTypes = new HashSet<>();
        private Set<Class> otherTypes = new HashSet<>();
    }
    
    /**
     * Return handlesTypes of
     * - set of annotation classes to be handled by servlet container initializer
     * - set of other classes to be handled by servlet container initializar
     */
    private TypesToBeHandled handlesTypes() {
        TypesToBeHandled result = new TypesToBeHandled();
        
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
                            result.getAnnotationTypes().add(annotation);
                        }
                    }
                    else {
                        result.getOtherTypes().add(type);
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
        
        TypesToBeHandled handlesTypes = handlesTypes();
        // check if any annotation type is handled
        if (!(handlesTypes.getAnnotationTypes().isEmpty() && handlesTypes.getOtherTypes().isEmpty()))
        {
            // get only urls of libraries that contains jsf types
            Collection<URL> urls = new HashSet<>(ClasspathHelper.forResource("META-INF/faces-config.xml", this.getClass().getClassLoader()));
            // add jsf library with anotherFacesConfig
            String anotherFacesConfig = jsfAnnotatedClassFactoryConfiguration.getAnotherFacesConfig();
            if (anotherFacesConfig != null)
            {
                urls.addAll(ClasspathHelper.forResource(anotherFacesConfig, this.getClass().getClassLoader()));
            }

            // create reflections
            Reflections reflections = new Reflections(new ConfigurationBuilder().setUrls(urls));
            
            // add types annotated for each type to be handled
            for (Class<? extends Annotation> annotationType : handlesTypes.getAnnotationTypes())
            {
                result.addAll(reflections.getTypesAnnotatedWith(annotationType));
            }
            // add subtype of other types to be handled
            for (Class<?> otherType : handlesTypes.getOtherTypes())
            {
                result.addAll(reflections.getSubTypesOf(otherType));
            }
        }
        
        return result;        
    }
}
