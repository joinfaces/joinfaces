package com.github.persapiens.jsfboot.mojarra;

import javax.servlet.ServletContext;
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.Test;
import org.springframework.mock.web.MockServletContext;

@Test
public class MojarraServletContextConfigurerIT {

    public void test() {
        MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setClientStateTimeout(10);

        ServletContext servletContext = new MockServletContext();
        
        MojarraServletContextConfigurer mojarraServletContextConfigurer = MojarraServletContextConfigurer.builder()
            .mojarraProperties(mojarraProperties)
            .servletContext(servletContext)
            .build();
        
        mojarraServletContextConfigurer.configure();
        
        assertThat(servletContext.getInitParameter(MojarraServletContextConfigurer.PREFFIX + ".clientStateTimeout")).isEqualTo("10");
	}

}
