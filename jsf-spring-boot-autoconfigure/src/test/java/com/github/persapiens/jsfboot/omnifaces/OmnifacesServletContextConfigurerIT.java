package com.github.persapiens.jsfboot.omnifaces;

import javax.servlet.ServletContext;
import static org.assertj.core.api.Assertions.assertThat;
import org.omnifaces.component.output.cache.CacheInitializerListener;
import org.testng.annotations.Test;
import org.springframework.mock.web.MockServletContext;

@Test
public class OmnifacesServletContextConfigurerIT {

    public void test() {
        OmnifacesProperties omnifacesProperties = new OmnifacesProperties();
        omnifacesProperties.setCacheProvider("myCacheProvider");

        ServletContext servletContext = new MockServletContext();
        
        OmnifacesServletContextConfigurer omnifacesServletContextConfigurer = OmnifacesServletContextConfigurer.builder()
            .omnifacesProperties(omnifacesProperties)
            .servletContext(servletContext)
            .build();
        
        omnifacesServletContextConfigurer.configure();
        
        assertThat(servletContext.getInitParameter(CacheInitializerListener.CACHE_PROVIDER_INIT_PARAM_NAME)).isEqualTo("myCacheProvider");
	}

}
