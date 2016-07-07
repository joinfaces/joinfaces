package com.github.persapiens.jsfboot.butterfaces;

import de.larmic.butterfaces.resolver.WebXmlParameters;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.testng.annotations.Test;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.assertj.core.api.Assertions.assertThat;

@SpringApplicationConfiguration(classes = ButterfacesSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class ButterfacesServletContextInitializerIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private ButterfacesProperties butterfacesProperties;

    public void testOnStartup() throws ServletException
    {
        ButterfacesServletContextInitializer butterfacesServletContextInitializer 
            = new ButterfacesServletContextInitializer(butterfacesProperties);
        
        ServletContext servletContext = new MockServletContext();
        
        butterfacesServletContextInitializer.onStartup(servletContext);
        
        assertThat(servletContext.getInitParameter(WebXmlParameters.CTX_PARAM_AJAX_DISABLE_RENDER_REGIONS_ON_REQUEST))
            .isEqualTo("true");
	}

}
