package com.github.persapiens.jsfboot.butterfaces;

import com.github.persapiens.jsfboot.javaxfaces.JavaxFacesSpringBootAutoConfiguration;
import de.larmic.butterfaces.util.ReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ButterfacesProperties.class)
@ConditionalOnClass(ReflectionUtil.class)
@AutoConfigureBefore(JavaxFacesSpringBootAutoConfiguration.class)
@ConditionalOnWebApplication
public class ButterfacesSpringBootAutoConfiguration {

	@Autowired
	private ButterfacesProperties butterfacesProperties;
    
    @Bean
    public ServletContextInitializer butterfacesServletContextInitializer()
    {
        return new ButterfacesServletContextInitializer(butterfacesProperties);
    }
}
