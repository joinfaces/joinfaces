package com.github.persapiens.jsfboot.undertow;

import org.apache.myfaces.webapp.StartupServletContextListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@SpringApplicationConfiguration(classes = UndertowMyfacesSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class UndertowMyfacesSpringBootAutoConfigurationIT extends AbstractTestNGSpringContextTests {
    
    @Autowired
    private StartupServletContextListener startupServletContextListener;
    
    public void startupServletContextListenerNotNull() {
        assertThat(startupServletContextListener).isNotNull();
    }

}
