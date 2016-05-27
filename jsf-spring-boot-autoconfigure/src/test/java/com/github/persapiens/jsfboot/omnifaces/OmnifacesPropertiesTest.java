package com.github.persapiens.jsfboot.omnifaces;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class OmnifacesPropertiesTest {

	public void testCacheProvider() {
		OmnifacesProperties omnifacesProperties = new OmnifacesProperties();
        omnifacesProperties.setCacheProvider("myCacheProvider");

		assertThat(omnifacesProperties.getCacheProvider()).isEqualTo("myCacheProvider");
	}

	public void testCacheApplicationMaxCapacity() {
		OmnifacesProperties omnifacesProperties = new OmnifacesProperties();
        omnifacesProperties.getCache().setApplicationMaxCapacity("myCapacity");

		assertThat(omnifacesProperties.getCache().getApplicationMaxCapacity()).isEqualTo("myCapacity");
	}

}
