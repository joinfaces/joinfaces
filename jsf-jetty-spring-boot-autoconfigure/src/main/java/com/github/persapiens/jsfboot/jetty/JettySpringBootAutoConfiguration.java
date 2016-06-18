package com.github.persapiens.jsfboot.jetty;

import org.eclipse.jetty.server.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

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

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		if (container instanceof JettyEmbeddedServletContainerFactory) {
            ((JettyEmbeddedServletContainerFactory) container).addServerCustomizers(
                new JsfJettyServerCustomizer(jettyProperties));
		}
	}
}
