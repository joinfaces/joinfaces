package com.github.persapiens.jsfboot.jetty;

import org.apache.myfaces.webapp.StartupServletContextListener;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = JettyMyfacesSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class JettyMyfacesSpringBootAutoConfigurationIT extends AbstractTestNGSpringContextTests {
    
    @Autowired
    private StartupServletContextListener startupServletContextListener;
    
    public void startupServletContextListenerNotNull() {
        assertThat(startupServletContextListener).isNotNull();
    }

}
