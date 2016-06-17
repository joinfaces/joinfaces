package com.github.persapiens.jsfboot.angularfaces;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import com.github.persapiens.jsfboot.javaxfaces.JavaxFacesSpringBootAutoConfiguration;
import de.beyondjava.angularFaces.core.ELTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AngularfacesProperties.class)
@ConditionalOnClass(ELTools.class)
@AutoConfigureBefore(JavaxFacesSpringBootAutoConfiguration.class)
@ConditionalOnWebApplication
public class AngularfacesSpringBootAutoConfiguration {

	@Autowired
	private AngularfacesProperties angularfacesProperties;
    
    @Bean
    public ServletContextInitializer angularfacesServletContextInitializer()
    {
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext sc) throws ServletException {                
                AngularfacesServletContextConfigurer.builder()
                    .angularfacesProperties(angularfacesProperties)
                    .servletContext(sc)
                    .build()
                    .configure();
            }
        };
    }
}
