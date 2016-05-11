package com.github.persapiens.jsfboot.omnifaces;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class OmnifacesCachePropertiesTest {

	public void testTheme() {
		OmnifacesCacheProperties omnifacesCacheProperties = new OmnifacesCacheProperties();
        omnifacesCacheProperties.setApplicationMaxCapacity("myCapacity");

		assertThat(omnifacesCacheProperties.getApplicationMaxCapacity()).isEqualTo("myCapacity");
	}

}
