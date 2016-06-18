package com.github.persapiens.jsfboot.angularfaces;

import de.beyondjava.angularFaces.core.tagTransformer.AngularTagDecorator;
import javax.faces.application.ViewHandler;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.testng.annotations.Test;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringApplicationConfiguration(classes = AngularfacesSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class AngularfacesServletContextInitializerIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private AngularfacesProperties angularfacesProperties;

    public void testOnStartup() throws ServletException 
    {
        AngularfacesServletContextInitializer angularfacesServletContextInitializer
            = new AngularfacesServletContextInitializer(angularfacesProperties);
        
        ServletContext servletContext = new MockServletContext();
        
        angularfacesServletContextInitializer.onStartup(servletContext);
        
        assertThat(servletContext.getInitParameter(AngularfacesServletContextConfigurer.PREFFIX + "addLabels")).isEqualTo("true");
        assertThat(servletContext.getInitParameter(ViewHandler.FACELETS_DECORATORS_PARAM_NAME)).isEqualTo(AngularTagDecorator.class.getName());
	}

}
