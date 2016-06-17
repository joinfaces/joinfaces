package com.github.persapiens.jsfboot.bootsfaces;

import javax.servlet.ServletContext;
import net.bootsfaces.C;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.testng.annotations.Test;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringApplicationConfiguration(classes = BootsfacesSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class BootsfacesServletContextConfigurerIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private BootsfacesProperties bootsfacesProperties;

    public void test() {
        ServletContext servletContext = new MockServletContext();
        
        BootsfacesServletContextConfigurer bootsfacesServletContextConfigurer = BootsfacesServletContextConfigurer.builder()
            .bootsfacesProperties(bootsfacesProperties)
            .servletContext(servletContext)
            .build();
        
        bootsfacesServletContextConfigurer.configure();
        
        assertThat(servletContext.getInitParameter(C.P_THEME)).isEqualTo("bootstrap");
	}

}
