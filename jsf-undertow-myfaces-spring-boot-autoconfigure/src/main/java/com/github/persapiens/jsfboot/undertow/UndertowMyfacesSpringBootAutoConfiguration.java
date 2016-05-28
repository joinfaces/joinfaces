package com.github.persapiens.jsfboot.undertow;

import io.undertow.Undertow;
import org.apache.myfaces.webapp.StartupServletContextListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Undertow.class)
public class UndertowMyfacesSpringBootAutoConfiguration {
    @Bean
    public StartupServletContextListener startupServletContextListener() {
        return new StartupServletContextListener();
    }

}
