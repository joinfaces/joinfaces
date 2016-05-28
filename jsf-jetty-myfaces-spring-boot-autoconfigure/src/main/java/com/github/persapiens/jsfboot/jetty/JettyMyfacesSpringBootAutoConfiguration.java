package com.github.persapiens.jsfboot.jetty;

import org.apache.myfaces.webapp.StartupServletContextListener;
import org.eclipse.jetty.server.Server;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Server.class)
public class JettyMyfacesSpringBootAutoConfiguration {
    @Bean
    public StartupServletContextListener startupServletContextListener() {
        return new StartupServletContextListener();
    }

}
