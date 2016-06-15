package com.github.persapiens.jsfboot.omnifaces;

import com.github.persapiens.jsfboot.JsfAnnotatedClassFactory;
import java.util.Set;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeSuite;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class OmnifacesServletContextInitializerIT {

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

}
