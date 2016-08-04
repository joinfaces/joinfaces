package org.joinfaces.javaxfaces;

import javax.faces.application.ResourceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * Additive Auto configuration of spring-security for JSF
 *
 * @author Nurettin Yilmaz
 */
@Configuration
@EnableConfigurationProperties({JavaxFacesProperties.class})
@ConditionalOnClass({EnableWebSecurity.class, AuthenticationEntryPoint.class})
@Order(Ordered.HIGHEST_PRECEDENCE)
public class JavaxFacesSpringSecurityAutoConfiguration implements WebSecurityConfigurer<WebSecurity> {

   @Autowired
   private JavaxFacesProperties javaxFacesProperties;

   @Override
   public void init(WebSecurity builder) throws Exception { }

   @Override
   public void configure(WebSecurity builder) throws Exception {

      Boolean ignoreResources = javaxFacesProperties.getSecurity().getIgnoreResources();
      if(ignoreResources) {
         builder
             .ignoring()
             .antMatchers(ResourceHandler.RESOURCE_IDENTIFIER + "/**");
      }
   }
}
