package com.github.persapiens.jsfboot.primefaces;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.primefaces.component.captcha.Captcha;
import org.testng.annotations.Test;
import org.springframework.mock.web.MockServletContext;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringApplicationConfiguration(classes = PrimefacesSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class PrimefacesServletContextInitializerIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private PrimefacesProperties primefacesProperties;
    
    public void testOnStartup() throws ServletException
    {
        PrimefacesServletContextInitializer primefacesServletContextInitializer =
            new PrimefacesServletContextInitializer(primefacesProperties);
        
        ServletContext servletContext = new MockServletContext();
        
        primefacesServletContextInitializer.onStartup(servletContext);
        
        assertThat(servletContext.getInitParameter(Captcha.PRIVATE_KEY)).isEqualTo("myPrivateCaptchaKey");
	}

}
