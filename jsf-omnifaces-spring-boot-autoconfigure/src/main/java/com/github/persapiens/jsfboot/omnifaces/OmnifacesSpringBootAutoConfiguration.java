package com.github.persapiens.jsfboot.omnifaces;


import com.github.persapiens.jsfboot.javaxfaces.JavaxFacesSpringBootAutoConfiguration;
import org.omnifaces.facesviews.FacesViewsInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(OmnifacesProperties.class)
@ConditionalOnClass({FacesViewsInitializer.class})
@AutoConfigureBefore(JavaxFacesSpringBootAutoConfiguration.class)
@ConditionalOnWebApplication
public class OmnifacesSpringBootAutoConfiguration {

	@Autowired
	private OmnifacesProperties omnifacesProperties;
    
    @Bean
    public ServletContextInitializer omnifacesServletContextInitializer()
    {
        return new OmnifacesServletContextInitializer(omnifacesProperties);
    }
}
