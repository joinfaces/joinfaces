package com.github.persapiens.jsfboot.omnifaces;


import javax.servlet.DispatcherType;
import org.omnifaces.filter.CharacterEncodingFilter;
import com.github.persapiens.jsfboot.javaxfaces.JavaxFacesSpringBootAutoConfiguration;
import javax.servlet.ServletContainerInitializer;
import org.omnifaces.facesviews.FacesViewsInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(OmnifacesProperties.class)
@ConditionalOnClass({CharacterEncodingFilter.class})
@AutoConfigureBefore(JavaxFacesSpringBootAutoConfiguration.class)
@ConditionalOnWebApplication
public class OmnifacesSpringBootAutoConfiguration {

	@Autowired
	private OmnifacesProperties omnifacesProperties;

    public ServletContainerInitializer getServletContainerInitializer()
    {
        return new FacesViewsInitializer();
    }
    
    public String getAnotherFacesConfig() {
        return null;
    }
    
    public boolean isExcludeScopedAnnotations() {
        return true;
    }
    
    @Bean
    public ServletContextInitializer omnifacesServletContextInitializer()
    {
        return new OmnifacesServletContextInitializer(omnifacesProperties);
    }
    
    /**
     * Register omnifaces filter to solve primefaces encoding problem
     * @return characterEncodingFilter
     */ 
    @Bean
    public FilterRegistrationBean characterEncodingFilter() {
        FilterRegistrationBean result = new FilterRegistrationBean();
        result.setName("characterEncodingFilter");
        result.setFilter(new CharacterEncodingFilter());
        result.setDispatcherTypes(DispatcherType.FORWARD, DispatcherType.REQUEST
            , DispatcherType.INCLUDE, DispatcherType.ASYNC
            , DispatcherType.ERROR);
        result.setMatchAfter(true);
        result.addUrlPatterns("/*");
        
        return result;
    }
}
