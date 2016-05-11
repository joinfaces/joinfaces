package com.github.persapiens.jsfboot.omnifaces;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class OmnifacesPropertiesTest {

	public void testTheme() {
		OmnifacesProperties omnifacesProperties = new OmnifacesProperties();
        omnifacesProperties.setCacheProvider("myCacheProvider");

		assertThat(omnifacesProperties.getCacheProvider()).isEqualTo("myCacheProvider");
	}

}
