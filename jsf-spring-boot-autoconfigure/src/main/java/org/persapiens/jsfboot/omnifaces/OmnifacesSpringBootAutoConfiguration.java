package org.persapiens.jsfboot.omnifaces;


import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.omnifaces.filter.CharacterEncodingFilter;
import org.persapiens.jsfboot.javaxfaces.JavaxFacesSpringBootAutoConfiguration;
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
@EnableConfigurationProperties({ OmnifacesProperties.class, OmnifacesCacheProperties.class })
@ConditionalOnClass({CharacterEncodingFilter.class})
@AutoConfigureBefore(JavaxFacesSpringBootAutoConfiguration.class)
@ConditionalOnWebApplication
public class OmnifacesSpringBootAutoConfiguration {

	@Autowired
	private OmnifacesProperties omnifacesProperties;

	@Autowired
	private OmnifacesCacheProperties omnifacesCacheProperties;
    
    @Bean
    public ServletContextInitializer omnifacesServletContextInitializer()
    {
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext sc) throws ServletException {                
                OmnifacesServletContextConfigurer.builder()
                    .omnifacesProperties(omnifacesProperties)
                    .servletContext(sc)
                    .build()
                    .configure();
                
                OmnifacesCacheServletContextConfigurer.builder()
                    .omnifacesCacheProperties(omnifacesCacheProperties)
                    .servletContext(sc)
                    .build()
                    .configure();
            }
        };
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
