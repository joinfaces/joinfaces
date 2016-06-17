package com.github.persapiens.jsfboot.bootsfaces;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import com.github.persapiens.jsfboot.javaxfaces.JavaxFacesSpringBootAutoConfiguration;
import net.bootsfaces.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(BootsfacesProperties.class)
@ConditionalOnClass(C.class)
@AutoConfigureBefore(JavaxFacesSpringBootAutoConfiguration.class)
@ConditionalOnWebApplication
public class BootsfacesSpringBootAutoConfiguration {

	@Autowired
	private BootsfacesProperties bootsfacesProperties;
    
    @Bean
    public ServletContextInitializer bootsfacesServletContextInitializer()
    {
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext sc) throws ServletException {                
                BootsfacesServletContextConfigurer.builder()
                    .bootsfacesProperties(bootsfacesProperties)
                    .servletContext(sc)
                    .build()
                    .configure();
            }
        };
    }
}
