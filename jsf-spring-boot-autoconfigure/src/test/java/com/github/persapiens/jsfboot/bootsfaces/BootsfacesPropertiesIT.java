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
		assertThat(bootsfacesProperties.getUsetheme()).isEqualTo(Boolean.TRUE.toString());
	}

	public void testUseViewport() {
		assertThat(bootsfacesProperties.getUseViewport()).isEqualTo(Boolean.TRUE.toString());
	}

	public void testDefaultsRenderLabel() {
		assertThat(bootsfacesProperties.getDefaults().getRenderLabel()).isEqualTo("myRenderLabel");
	}

	public void testGetFontawesomeFromCdn() {
		assertThat(bootsfacesProperties.getGetFontawesomeFromCdn()).isEqualTo(Boolean.TRUE.toString());
	}

	public void testGetJqueryFromCdn() {
		assertThat(bootsfacesProperties.getGetJqueryFromCdn()).isEqualTo(Boolean.TRUE.toString());
	}

	public void testGetJqueryuiFromCdn() {
		assertThat(bootsfacesProperties.getGetJqueryuiFromCdn()).isEqualTo(Boolean.TRUE.toString());
	}

	public void testGetBootstrapFromCdn() {
		assertThat(bootsfacesProperties.getGetBootstrapFromCdn()).isEqualTo(Boolean.TRUE.toString());
	}

	public void testBlockUI() {
		assertThat(bootsfacesProperties.getBlockUI()).isEqualTo("false");
	}

}
