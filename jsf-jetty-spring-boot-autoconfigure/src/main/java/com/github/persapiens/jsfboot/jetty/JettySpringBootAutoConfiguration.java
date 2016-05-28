package com.github.persapiens.jsfboot.jetty;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.ResourceCollection;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyServerCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Taken from https://github.com/ghillert/spring-boot-jsp-demo/blob/jetty/src/main/java/com/hillert/JspDemoApplication.java#L78
 * and from https://github.com/eclipse/jetty.project/issues/420
 * and from https://github.com/spring-projects/spring-boot/pull/5290
 */
@Configuration
@EnableConfigurationProperties({ JettyProperties.class })
@ConditionalOnClass(Server.class)
public class JettySpringBootAutoConfiguration extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer {

    @Autowired
    private JettyProperties jettyProperties;
    
	private static final Logger LOGGER = LoggerFactory.getLogger(JettySpringBootAutoConfiguration.class);

	private JettyServerCustomizer jettyServerCustomizer() {
		return new JettyServerCustomizerImpl();
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		if (container instanceof JettyEmbeddedServletContainerFactory) {
            ((JettyEmbeddedServletContainerFactory) container).addServerCustomizers(jettyServerCustomizer());
		}
	}

    private class JettyServerCustomizerImpl implements JettyServerCustomizer {
        @Override
        public void customize(Server server) {
            Handler[] childHandlersByClass = server.getChildHandlersByClass(WebAppContext.class);
            WebAppContext webAppContext = (WebAppContext) childHandlersByClass[0];
            
            try {
                ClassPathResource classPathResource = new ClassPathResource(jettyProperties.getClassPathResource());
                webAppContext.setBaseResource(new ResourceCollection(classPathResource.getURI().toString()));
                
                AccessController.doPrivileged(new PrivilegedAction<Void>() {
                    @Override
                    public Void run() {
                        webAppContext.setClassLoader(new URLClassLoader(new URL[0], this.getClass().getClassLoader()));
                        return null;
                    }
                });
                
                LOGGER.info("Setting Jetty classLoader to META-INF/resources directory");
            }
            catch (IOException exception) {
                LOGGER.error("Unable to configure Jetty classLoader to META-INF/resources directory " + exception.getMessage());
            }
        }
    }
}
