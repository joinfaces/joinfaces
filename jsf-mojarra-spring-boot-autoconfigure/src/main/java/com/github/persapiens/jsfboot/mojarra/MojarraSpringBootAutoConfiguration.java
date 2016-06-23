package com.github.persapiens.jsfboot.mojarra;

import com.github.persapiens.jsfboot.javaxfaces.JavaxFacesSpringBootAutoConfiguration;
import com.sun.faces.config.FacesInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ MojarraProperties.class })
@ConditionalOnClass(FacesInitializer.class)
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@AutoConfigureAfter({JavaxFacesSpringBootAutoConfiguration.class})
@ConditionalOnWebApplication
public class MojarraSpringBootAutoConfiguration {

	@Autowired
	private MojarraProperties mojarraProperties;
    
    @Bean
    public ServletContextInitializer mojarraServletContextInitializer()
    {
        return new MojarraServletContextInitializer(mojarraProperties);
    }
}
