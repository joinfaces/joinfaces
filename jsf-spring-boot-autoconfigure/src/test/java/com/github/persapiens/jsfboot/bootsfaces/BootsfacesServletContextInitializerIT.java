package com.github.persapiens.jsfboot.bootsfaces;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import net.bootsfaces.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.testng.annotations.Test;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.assertj.core.api.Assertions.assertThat;

@SpringApplicationConfiguration(classes = BootsfacesSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class BootsfacesServletContextInitializerIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private BootsfacesProperties bootsfacesProperties;

    public void testOnStartup() throws ServletException
    {
        BootsfacesServletContextInitializer bootsfacesServletContextInitializer 
            = new BootsfacesServletContextInitializer(bootsfacesProperties);
        
        ServletContext servletContext = new MockServletContext();
        
        bootsfacesServletContextInitializer.onStartup(servletContext);
        
        assertThat(servletContext.getInitParameter(C.P_THEME)).isEqualTo("bootstrap");
	}

}
