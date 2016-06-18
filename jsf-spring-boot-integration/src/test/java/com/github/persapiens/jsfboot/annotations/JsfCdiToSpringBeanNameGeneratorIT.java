package com.github.persapiens.jsfboot.annotations;

import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

@Test
public class JsfCdiToSpringBeanNameGeneratorIT {

    public void testViewScope() {
        AnnotationMetadata annotationMetadata = new StandardAnnotationMetadata(ViewScopedClass.class);
        AnnotatedBeanDefinition beanDefinition = new AnnotatedGenericBeanDefinition(annotationMetadata);
        
        BeanDefinitionRegistry registry= new SimpleBeanDefinitionRegistry();
        
        AnnotationBeanNameGenerator annotationBeanNameGeneratorannotationScopeMetadataResolver = 
            new JsfCdiToSpringBeanNameGenerator();
        
        annotationBeanNameGeneratorannotationScopeMetadataResolver.generateBeanName(
            beanDefinition, registry);
        
        assertThat(beanDefinition.getScope()).isEqualTo(JsfCdiToSpring.VIEW);
    }

    public void testSessionScope() {
        AnnotationMetadata annotationMetadata = new StandardAnnotationMetadata(SessionScopedClass.class);
        AnnotatedBeanDefinition beanDefinition = new AnnotatedGenericBeanDefinition(annotationMetadata);
        
        BeanDefinitionRegistry registry= new SimpleBeanDefinitionRegistry();
        
        AnnotationBeanNameGenerator annotationBeanNameGeneratorannotationScopeMetadataResolver = 
            new JsfCdiToSpringBeanNameGenerator();
        
        annotationBeanNameGeneratorannotationScopeMetadataResolver.generateBeanName(
            beanDefinition, registry);
        
        assertThat(beanDefinition.getScope()).isEqualTo(JsfCdiToSpring.SESSION);
    }

    public void testNoScope() {
        AnnotationMetadata annotationMetadata = new StandardAnnotationMetadata(NoScopedClass.class);
        AnnotatedBeanDefinition beanDefinition = new AnnotatedGenericBeanDefinition(annotationMetadata);
        
        BeanDefinitionRegistry registry= new SimpleBeanDefinitionRegistry();
        
        AnnotationBeanNameGenerator annotationBeanNameGeneratorannotationScopeMetadataResolver = 
            new JsfCdiToSpringBeanNameGenerator();
        
        annotationBeanNameGeneratorannotationScopeMetadataResolver.generateBeanName(
            beanDefinition, registry);
        
        assertThat(beanDefinition.getScope()).isEmpty();
    }

}
