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
public class MyfacesPropertiesResourcesIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private MyfacesProperties myfacesProperties;

	public void testResourceMaxTimeExpires() {
		assertThat(myfacesProperties.getResourceMaxTimeExpires()).isEqualTo(604800000);
	}

	public void testResourceHandlerCacheSize() {
		assertThat(myfacesProperties.getResourceHandlerCacheSize()).isEqualTo(500);
	}

	public void testResourceHandlerCacheEnabled() {
		assertThat(myfacesProperties.getResourceHandlerCacheEnabled()).isTrue();
	}

	public void testStrictJsf2AllowSlashLibraryName() {
		assertThat(myfacesProperties.getStrictJsf2AllowSlashLibraryName()).isTrue();
	}

	public void testResourceBufferSize() {
		assertThat(myfacesProperties.getResourceBufferSize()).isEqualTo(2048);
	}

}
