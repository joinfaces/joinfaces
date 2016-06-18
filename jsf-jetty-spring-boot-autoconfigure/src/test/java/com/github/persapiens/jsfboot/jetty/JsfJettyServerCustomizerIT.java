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
public class JsfJettyServerCustomizerIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private JettyProperties jettyProperties;
    
	public void customize() throws MalformedURLException {
		JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory();
        
        JsfJettyServerCustomizer customizer = new JsfJettyServerCustomizer(jettyProperties);
        
        Server server = ((JettyEmbeddedServletContainer) factory.getEmbeddedServletContainer()).getServer();
        
        customizer.customize(server);
        
        Handler[] childHandlersByClass = server.getChildHandlersByClass(WebAppContext.class);
        WebAppContext webAppContext = (WebAppContext) childHandlersByClass[0];
        
        assertThat(webAppContext.getBaseResource().getResource("test.txt").exists()).isTrue();
	}
    
    @Test(expectedExceptions = RuntimeException.class)
	public void invalidClassPathResource() throws MalformedURLException {
		JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory();

        JettyProperties jp = new JettyProperties();
        jp.setClassPathResource("/~ã``'[ªº*-+.@#$%{&*ç|°;.<>");
        
        JsfJettyServerCustomizer customizer = new JsfJettyServerCustomizer(jp);
        
        Server server = ((JettyEmbeddedServletContainer) factory.getEmbeddedServletContainer()).getServer();
        
        customizer.customize(server);
        
        Handler[] childHandlersByClass = server.getChildHandlersByClass(WebAppContext.class);
        WebAppContext webAppContext = (WebAppContext) childHandlersByClass[0];
        
        assertThat(webAppContext.getBaseResource().getResource("test.txt").exists()).isTrue();
	}
}
