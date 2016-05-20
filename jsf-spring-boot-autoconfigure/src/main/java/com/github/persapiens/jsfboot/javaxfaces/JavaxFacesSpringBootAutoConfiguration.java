package com.github.persapiens.jsfboot.javaxfaces;


import com.github.persapiens.jsfboot.mojarra.MojarraSpringBootAutoConfiguration;
import com.github.persapiens.jsfboot.myfaces.MyfacesSpringBootAutoConfiguration;
import javax.faces.application.ProjectStage;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ JavaxFacesProperties.class })
@ConditionalOnClass(ProjectStage.class)
@AutoConfigureBefore({MojarraSpringBootAutoConfiguration.class, MyfacesSpringBootAutoConfiguration.class})
@ConditionalOnWebApplication
public class JavaxFacesSpringBootAutoConfiguration {

	@Autowired
	private JavaxFacesProperties javaxFacesProperties;
    
    @Bean
    public ServletContextInitializer javaxFacesServletContextInitializer()
    {
        return  new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext sc) throws ServletException {                
                JavaxFacesServletContextConfigurer.builder()
                    .javaxFacesProperties(javaxFacesProperties)
                    .servletContext(sc)
                    .build()
                    .configure();
            }            
        };
    }
    
    @Bean(autowire = Autowire.BY_NAME)
    public static CustomScopeConfigurer customScopeConfigurer() {
        CustomScopeConfigurer result = new CustomScopeConfigurer();
        
        result.addScope("view", new ViewScope());
        
        return result;
    }
}
