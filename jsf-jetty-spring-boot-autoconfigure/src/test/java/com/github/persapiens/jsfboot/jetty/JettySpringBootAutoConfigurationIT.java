package com.github.persapiens.jsfboot.jetty;

import java.net.MalformedURLException;
import static org.assertj.core.api.Assertions.assertThat;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainer;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

/**
 * Taken from https://github.com/ghillert/spring-boot-jsp-demo/blob/jetty/src/main/java/com/hillert/JspDemoApplication.java#L78
 * and from https://github.com/eclipse/jetty.project/issues/420
 * and from https://github.com/spring-projects/spring-boot/pull/5290
 */
@SpringApplicationConfiguration(classes = JettySpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class JettySpringBootAutoConfigurationIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private JettySpringBootAutoConfiguration jettySpringBootAutoConfiguration;
    
    @Test
	public void customize() throws MalformedURLException {
		JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory();
        
        jettySpringBootAutoConfiguration.customize(factory);
        
        Server server = ((JettyEmbeddedServletContainer) factory.getEmbeddedServletContainer()).getServer();
        
        Handler[] childHandlersByClass = server.getChildHandlersByClass(WebAppContext.class);
        WebAppContext webAppContext = (WebAppContext) childHandlersByClass[0];
        
        assertThat(webAppContext.getBaseResource().getResource("test.txt").exists()).isTrue();
	}
}
