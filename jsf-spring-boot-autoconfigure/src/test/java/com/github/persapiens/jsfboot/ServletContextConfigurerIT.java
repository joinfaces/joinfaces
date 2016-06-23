package com.github.persapiens.jsfboot;

import javax.servlet.ServletContext;
import org.testng.annotations.Test;
import org.springframework.mock.web.MockServletContext;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class ServletContextConfigurerIT {

    public void testEmpty() 
    {
        ServletContext servletContext = new MockServletContext();
        
        ServletContextConfigurer servletContextConfigurer 
            = new JsfServletContextConfigurer(servletContext);
        
        servletContextConfigurer.configure();
        
        assertThat(servletContext.getInitParameter("jsf.empty")).isNull();
	}

    public void testKey() 
    {
        ServletContext servletContext = new MockServletContext();
        
        ServletContextConfigurer servletContextConfigurer 
            = new JsfServletContextConfigurer(servletContext);
        
        servletContextConfigurer.configure();
        
        assertThat(servletContext.getInitParameter("jsf.key")).isEqualTo("value");
	}

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNull() 
    {
        ServletContext servletContext = new MockServletContext();
        
        ServletContextConfigurer servletContextConfigurer 
            = new NullServletContextConfigurer(servletContext);
        
        servletContextConfigurer.configure();
        
        assertThat(servletContext.getInitParameter(null)).isNull();
	}

}
