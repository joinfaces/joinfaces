package org.joinfaces.annotations;

import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurettin Yilmaz
 */
@Configuration
public class ScopedBeansConfiguration {

   @Bean
   @ViewScoped
   public ViewScopedBean viewScopedBean() {
      return new ViewScopedBean();
   }

   @Bean
   @SessionScoped
   public SessionScopedBean sessionScopedBean() {
      return new SessionScopedBean();
   }

   @Bean
   public NoScopedBean noScopedBean() {
      return new NoScopedBean();
   }

   static class ViewScopedBean {

   }

   static class SessionScopedBean {

   }

   static class NoScopedBean {

   }

}
