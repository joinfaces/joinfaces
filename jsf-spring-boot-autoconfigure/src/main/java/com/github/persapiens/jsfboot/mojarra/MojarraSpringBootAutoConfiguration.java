package com.github.persapiens.jsfboot.mojarra;

import com.sun.faces.config.FacesInitializer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Resource;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.bean.ManagedBean;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.behavior.FacesBehavior;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ListenerFor;
import javax.faces.event.ListenersFor;
import javax.faces.event.NamedEvent;
import javax.faces.event.PhaseListener;
import javax.faces.render.FacesBehaviorRenderer;
import javax.faces.render.Renderer;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.view.facelets.FaceletsResourceResolver;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
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
@ConditionalOnWebApplication
public class MojarraSpringBootAutoConfiguration {

	@Autowired
	private MojarraProperties mojarraProperties;
    
    @Bean
    public ServletContextInitializer mojarraServletContextInitializer()
    {
        return  new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext sc) throws ServletException {                
                MojarraServletContextConfigurer.builder()
                    .mojarraProperties(mojarraProperties)
                    .servletContext(sc)
                    .build()
                    .configure();
                
                Set<Class<?>> set = new HashSet<>();
                Collections.addAll(set, 
                    ManagedBean.class,
                    FacesComponent.class,
                    FacesValidator.class,
                    FacesConverter.class,
                    FacesBehaviorRenderer.class,
                    ResourceDependency.class,
                    ResourceDependencies.class,
                    ListenerFor.class,
                    ListenersFor.class,
                    UIComponent.class,
                    Validator.class,
                    Converter.class,
                    Renderer.class,
                    FacesBehavior.class, 
                    PhaseListener.class,
                    FaceletsResourceResolver.class,
                    Resource.class,
                    NamedEvent.class);
                new FacesInitializer().onStartup(set, sc);
            }            
        };
    }
}
