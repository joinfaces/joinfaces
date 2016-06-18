package com.github.persapiens.jsfboot.omnifaces;

import com.github.persapiens.jsfboot.JsfAnnotatedClassFactory;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import static org.assertj.core.api.Assertions.assertThat;
import org.omnifaces.component.output.cache.CacheInitializerListener;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeSuite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringApplicationConfiguration(classes = OmnifacesSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class OmnifacesServletContextInitializerIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private OmnifacesProperties omnifacesProperties;

    private Set<Class<?>> classes;
    
    @BeforeSuite
    public void setupClasses() {
        OmnifacesServletContextInitializer configuration = new OmnifacesServletContextInitializer(null);
        
        classes = JsfAnnotatedClassFactory.builder()
            .jsfAnnotatedClassFactoryConfiguration(configuration)
            .build().find();
    }
    
	public void testEmpty() {
		assertThat(classes).isEmpty();
	}

    public void testOnStartup() throws ServletException
    {
        OmnifacesServletContextInitializer omnifacesServletContextInitializer 
            = new OmnifacesServletContextInitializer(omnifacesProperties);
        
        ServletContext servletContext = new MockServletContext();
        
        omnifacesServletContextInitializer.onStartup(servletContext);
        
        assertThat(servletContext.getInitParameter(CacheInitializerListener.CACHE_PROVIDER_INIT_PARAM_NAME))
            .isEqualTo("myCacheProvider");
	}

    public void testAnotherFacesConfig() throws ServletException
    {
        OmnifacesServletContextInitializer omnifacesServletContextInitializer 
            = new OmnifacesServletContextInitializer(omnifacesProperties);

        assertThat(omnifacesServletContextInitializer.getAnotherFacesConfig()).isNull();
	}

    public void testExcludeScopedAnnotations() throws ServletException
    {
        OmnifacesServletContextInitializer omnifacesServletContextInitializer 
            = new OmnifacesServletContextInitializer(omnifacesProperties);

        assertThat(omnifacesServletContextInitializer.isExcludeScopedAnnotations()).isTrue();
	}

}
