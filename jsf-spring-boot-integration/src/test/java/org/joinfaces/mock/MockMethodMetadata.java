package org.joinfaces.mock;

import java.lang.annotation.Annotation;
import java.util.Map;
import org.springframework.core.type.MethodMetadata;
import org.springframework.util.MultiValueMap;

/**
 * @author Nurettin Yilmaz
 */
public class MockMethodMetadata implements MethodMetadata {

   private final Class<? extends Annotation> annotation;

   public MockMethodMetadata(Class<? extends Annotation> annotation) {
      this.annotation = annotation;
   }

   @Override
   public String getMethodName() {
      return null;
   }

   @Override
   public String getDeclaringClassName() {
      return null;
   }

   @Override
   public String getReturnTypeName() {
      return null;
   }

   @Override
   public boolean isAbstract() {
      return false;
   }

   @Override
   public boolean isStatic() {
      return false;
   }

   @Override
   public boolean isFinal() {
      return false;
   }

   @Override
   public boolean isOverridable() {
      return false;
   }

   @Override
   public boolean isAnnotated(String annotationName) {
      return annotation.getName().equals(annotationName);
   }

   @Override
   public Map<String, Object> getAnnotationAttributes(String annotationName) {
      return null;
   }

   @Override
   public Map<String, Object> getAnnotationAttributes(String annotationName, boolean classValuesAsString) {
      return null;
   }

   @Override
   public MultiValueMap<String, Object> getAllAnnotationAttributes(String annotationName) {
      return null;
   }

   @Override
   public MultiValueMap<String, Object> getAllAnnotationAttributes(String annotationName, boolean classValuesAsString) {
      return null;
   }
}
