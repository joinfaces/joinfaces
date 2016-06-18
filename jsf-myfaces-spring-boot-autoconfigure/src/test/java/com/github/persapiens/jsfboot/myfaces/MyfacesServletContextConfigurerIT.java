package com.github.persapiens.jsfboot.myfaces;

import static com.github.persapiens.jsfboot.myfaces.MyfacesServletContextConfigurer.PREFFIX;
import javax.servlet.ServletContext;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.testng.annotations.Test;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringApplicationConfiguration(classes = MyfacesSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class MyfacesServletContextConfigurerIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private MyfacesProperties myfacesProperties;

    public void testConfigure() {
        ServletContext servletContext = new MockServletContext();
        
        MyfacesServletContextConfigurer myfacesServletContextConfigurer = MyfacesServletContextConfigurer.builder()
            .myfacesProperties(myfacesProperties)
            .servletContext(servletContext)
            .build();
        
        myfacesServletContextConfigurer.configure();
        
        assertThat(servletContext.getInitParameter(PREFFIX + "STRICT_JSF_2_CC_EL_RESOLVER")).isEqualTo("myElResolver");
	}

}
