package com.github.persapiens.jsfboot.javaxfaces;

import javax.faces.application.ProjectStage;
import javax.servlet.ServletContext;
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.Test;
import org.springframework.mock.web.MockServletContext;

@Test
public class JavaxFacesServletContextConfigurerIT {

    public void test() {
        JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.setProjectStage("Development");

        ServletContext servletContext = new MockServletContext();
        
        JavaxFacesServletContextConfigurer javaxFacesServletContextConfigurer = JavaxFacesServletContextConfigurer.builder()
            .javaxFacesProperties(javaxFacesProperties)
            .servletContext(servletContext)
            .build();
        
        javaxFacesServletContextConfigurer.configure();
        
        assertThat(servletContext.getInitParameter(ProjectStage.PROJECT_STAGE_PARAM_NAME)).isEqualTo("Development");
	}

}
