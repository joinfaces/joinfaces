package com.github.persapiens.jsfboot.myfaces;

import com.github.persapiens.jsfboot.javaxfaces.JavaxFacesSpringBootAutoConfiguration;
import org.apache.myfaces.ee6.MyFacesContainerInitializer;
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
@EnableConfigurationProperties({ MyfacesProperties.class })
@ConditionalOnClass(MyFacesContainerInitializer.class)
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@AutoConfigureAfter({JavaxFacesSpringBootAutoConfiguration.class})
@ConditionalOnWebApplication
public class MyfacesSpringBootAutoConfiguration {

	@Autowired
	private MyfacesProperties myfacesProperties;
    
    @Bean
    public ServletContextInitializer myfacesServletContextInitializer()
    {
        return new MyfacesServletContextInitializer(myfacesProperties);
    }
}
