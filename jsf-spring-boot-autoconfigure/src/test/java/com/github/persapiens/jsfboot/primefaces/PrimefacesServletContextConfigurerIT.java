package com.github.persapiens.jsfboot.primefaces;

import javax.servlet.ServletContext;
import static org.assertj.core.api.Assertions.assertThat;
import org.primefaces.component.captcha.Captcha;
import org.testng.annotations.Test;
import org.springframework.mock.web.MockServletContext;

@Test
public class PrimefacesServletContextConfigurerIT {

    public void test() {
        PrimefacesProperties primefacesProperties = new PrimefacesProperties();
        primefacesProperties.setPrivateCaptchaKey("myPrivateCaptchaKey");

        ServletContext servletContext = new MockServletContext();
        
        PrimefacesServletContextConfigurer primefacesServletContextConfigurer = PrimefacesServletContextConfigurer.builder()
            .primefacesProperties(primefacesProperties)
            .servletContext(servletContext)
            .build();
        
        primefacesServletContextConfigurer.configure();
        
        assertThat(servletContext.getInitParameter(Captcha.PRIVATE_KEY)).isEqualTo("myPrivateCaptchaKey");
	}

}
