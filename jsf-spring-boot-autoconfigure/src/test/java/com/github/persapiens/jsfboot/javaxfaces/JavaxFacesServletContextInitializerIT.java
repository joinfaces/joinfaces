package com.github.persapiens.jsfboot.javaxfaces;

import javax.faces.application.ProjectStage;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.testng.annotations.Test;
import org.springframework.mock.web.MockServletContext;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringApplicationConfiguration(classes = JavaxFacesSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class JavaxFacesServletContextInitializerIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private JavaxFacesProperties javaxFacesProperties;

    public void testOnStartup() throws ServletException
    {
        JavaxFacesServletContextInitializer javaxFacesServletContextInitializer 
            = new JavaxFacesServletContextInitializer(javaxFacesProperties);
        
        ServletContext servletContext = new MockServletContext();
        
        javaxFacesServletContextInitializer.onStartup(servletContext);
        
        assertThat(servletContext.getInitParameter(ProjectStage.PROJECT_STAGE_PARAM_NAME)).isEqualTo("Development");
	}

}
