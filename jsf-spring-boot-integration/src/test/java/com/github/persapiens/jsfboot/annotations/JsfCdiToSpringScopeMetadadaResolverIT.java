package com.github.persapiens.jsfboot.annotations;

import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.context.annotation.AnnotationScopeMetadataResolver;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class JsfCdiToSpringScopeMetadadaResolverIT {

    public void testViewScope() {
        AnnotationMetadata annotationMetadata = new StandardAnnotationMetadata(ViewScopedClass.class);
        AnnotatedBeanDefinition beanDefinition = new AnnotatedGenericBeanDefinition(annotationMetadata);
        AnnotationScopeMetadataResolver annotationScopeMetadataResolver = new JsfCdiToSpringScopeMetadataResolver();
        
        ScopeMetadata scopeMetadata = annotationScopeMetadataResolver.resolveScopeMetadata(
            beanDefinition);
        
        assertThat(scopeMetadata.getScopeName()).isEqualTo(JsfCdiToSpring.VIEW);
    }

    public void testSessionScope() {
        AnnotationMetadata annotationMetadata = new StandardAnnotationMetadata(SessionScopedClass.class);
        AnnotatedBeanDefinition beanDefinition = new AnnotatedGenericBeanDefinition(annotationMetadata);
        AnnotationScopeMetadataResolver annotationScopeMetadataResolver = new JsfCdiToSpringScopeMetadataResolver();
        
        ScopeMetadata scopeMetadata = annotationScopeMetadataResolver.resolveScopeMetadata(
            beanDefinition);
        
        assertThat(scopeMetadata.getScopeName()).isEqualTo(JsfCdiToSpring.SESSION);
    }

    public void testNoScope() {
        AnnotationMetadata annotationMetadata = new StandardAnnotationMetadata(NoScopedClass.class);
        AnnotatedBeanDefinition beanDefinition = new AnnotatedGenericBeanDefinition(annotationMetadata);
        AnnotationScopeMetadataResolver annotationScopeMetadataResolver = new JsfCdiToSpringScopeMetadataResolver();
        
        ScopeMetadata scopeMetadata = annotationScopeMetadataResolver.resolveScopeMetadata(
            beanDefinition);
        
        assertThat(scopeMetadata.getScopeName()).isEqualTo(JsfCdiToSpring.SINGLETON);
    }

}
