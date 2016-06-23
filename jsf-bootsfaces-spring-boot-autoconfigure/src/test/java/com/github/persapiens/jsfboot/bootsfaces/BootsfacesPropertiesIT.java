package com.github.persapiens.jsfboot.bootsfaces;

import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringApplicationConfiguration(classes = BootsfacesSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class BootsfacesPropertiesIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private BootsfacesProperties bootsfacesProperties;
    
	public void testTheme() {
		assertThat(bootsfacesProperties.getTheme()).isEqualTo("bootstrap");
	}

	public void testUsetheme() {
		assertThat(bootsfacesProperties.getUsetheme()).isTrue();
	}

	public void testUseViewport() {
		assertThat(bootsfacesProperties.getUseViewport()).isTrue();
	}

	public void testDefaultsRenderLabel() {
		assertThat(bootsfacesProperties.getDefaults().getRenderLabel()).isEqualTo("myRenderLabel");
	}

	public void testGetFontawesomeFromCdn() {
		assertThat(bootsfacesProperties.getGetFontawesomeFromCdn()).isTrue();
	}

	public void testGetJqueryFromCdn() {
		assertThat(bootsfacesProperties.getGetJqueryFromCdn()).isTrue();
	}

	public void testGetJqueryuiFromCdn() {
		assertThat(bootsfacesProperties.getGetJqueryuiFromCdn()).isTrue();
	}

	public void testGetBootstrapFromCdn() {
		assertThat(bootsfacesProperties.getGetBootstrapFromCdn()).isTrue();
	}

	public void testBlockUI() {
		assertThat(bootsfacesProperties.getBlockUI()).isFalse();
	}

}
