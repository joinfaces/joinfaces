package com.github.persapiens.jsfboot.primefaces;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import com.github.persapiens.jsfboot.javaxfaces.JavaxFacesSpringBootAutoConfiguration;
import org.primefaces.application.DialogViewHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(PrimefacesProperties.class)
@ConditionalOnClass(DialogViewHandler.class)
@AutoConfigureBefore(JavaxFacesSpringBootAutoConfiguration.class)
@ConditionalOnWebApplication
public class PrimefacesSpringBootAutoConfiguration {

	@Autowired
	private PrimefacesProperties primefacesProperties;
    
    @Bean
    public ServletContextInitializer primefacesServletContextInitializer()
    {
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext sc) throws ServletException {                
                PrimefacesServletContextConfigurer.builder()
                    .primefacesProperties(primefacesProperties)
                    .servletContext(sc)
                    .build()
                    .configure();
            }
        };
    }
}
