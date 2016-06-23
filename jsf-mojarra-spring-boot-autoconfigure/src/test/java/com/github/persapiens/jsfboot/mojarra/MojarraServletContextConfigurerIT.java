package com.github.persapiens.jsfboot.mojarra;

import javax.servlet.ServletContext;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.testng.annotations.Test;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringApplicationConfiguration(classes = MojarraSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class MojarraServletContextConfigurerIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private MojarraProperties mojarraProperties;

    public void testConfigure() {
        ServletContext servletContext = new MockServletContext();
        
        MojarraServletContextConfigurer mojarraServletContextConfigurer = MojarraServletContextConfigurer.builder()
            .mojarraProperties(mojarraProperties)
            .servletContext(servletContext)
            .build();
        
        mojarraServletContextConfigurer.configure();
        
        assertThat(servletContext.getInitParameter(MojarraServletContextConfigurer.PREFFIX + ".clientStateTimeout")).isEqualTo("10");
	}

}
