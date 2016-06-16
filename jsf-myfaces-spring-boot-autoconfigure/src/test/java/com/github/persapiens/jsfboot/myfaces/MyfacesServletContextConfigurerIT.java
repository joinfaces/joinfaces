package com.github.persapiens.jsfboot.myfaces;

import static com.github.persapiens.jsfboot.myfaces.MyfacesServletContextConfigurer.PREFFIX;
import javax.servlet.ServletContext;
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.Test;
import org.springframework.mock.web.MockServletContext;

@Test
public class MyfacesServletContextConfigurerIT {

    public void test() {
        MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setStrictJsf2CcElResolver("myElResolver");

        ServletContext servletContext = new MockServletContext();
        
        MyfacesServletContextConfigurer myfacesServletContextConfigurer = MyfacesServletContextConfigurer.builder()
            .myfacesProperties(myfacesProperties)
            .servletContext(servletContext)
            .build();
        
        myfacesServletContextConfigurer.configure();
        
        assertThat(servletContext.getInitParameter(PREFFIX + "STRICT_JSF_2_CC_EL_RESOLVER")).isEqualTo("myElResolver");        
	}

}
