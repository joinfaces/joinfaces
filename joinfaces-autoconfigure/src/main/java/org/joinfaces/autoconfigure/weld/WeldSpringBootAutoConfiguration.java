package org.joinfaces.autoconfigure.weld;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass(name = "org.jboss.weld.environment.servlet.EnhancedListener")
public class WeldSpringBootAutoConfiguration {

    @Bean
    public WeldServletContextInitializer weldServletContextInitializer(){
        return new WeldServletContextInitializer();
    }
    
}
