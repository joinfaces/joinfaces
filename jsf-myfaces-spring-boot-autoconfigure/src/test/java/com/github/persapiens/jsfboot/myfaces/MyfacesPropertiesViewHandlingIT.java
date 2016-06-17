package com.github.persapiens.jsfboot.myfaces;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringApplicationConfiguration(classes = MyfacesSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class MyfacesPropertiesViewHandlingIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private MyfacesProperties myfacesProperties;
        
	public void testCheckedViewidCacheSize() {
		assertThat(myfacesProperties.getCheckedViewidCacheSize()).isEqualTo(500);
	}

	public void testCheckedViewidCacheEnabled() {
		assertThat(myfacesProperties.getCheckedViewidCacheEnabled()).isTrue();
	}

	public void testViewUniqueIdsCacheEnabled() {
		assertThat(myfacesProperties.getViewUniqueIdsCacheEnabled()).isTrue();
	}

	public void testComponentUniqueIdsCacheSize() {
		assertThat(myfacesProperties.getComponentUniqueIdsCacheSize()).isEqualTo(100);
	}

	public void testStrictJsf2ViewNotFound() {
		assertThat(myfacesProperties.getStrictJsf2ViewNotFound()).isTrue();
	}

	public void testStrictJsf2FaceletsCompatibility() {
		assertThat(myfacesProperties.getStrictJsf2FaceletsCompatibility()).isTrue();
	}

}
