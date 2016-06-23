package com.github.persapiens.jsfboot.javaxfaces;


import javax.faces.application.ProjectStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ JavaxFacesProperties.class })
@ConditionalOnClass(ProjectStage.class)
@ConditionalOnWebApplication
public class JavaxFacesSpringBootAutoConfiguration {

	@Autowired
	private JavaxFacesProperties javaxFacesProperties;
    
    @Bean
    public ServletContextInitializer javaxFacesServletContextInitializer()
    {
        return new JavaxFacesServletContextInitializer(javaxFacesProperties);
    }
}
