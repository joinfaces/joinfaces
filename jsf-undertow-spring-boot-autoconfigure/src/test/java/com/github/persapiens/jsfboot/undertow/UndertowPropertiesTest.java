package com.github.persapiens.jsfboot.undertow;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class UndertowPropertiesTest {

	public void testForceLoadConfiguration() {
		UndertowProperties undertowProperties = new UndertowProperties();
        undertowProperties.setClassPathResource("public");

		assertThat(undertowProperties.getClassPathResource()).isEqualTo("public");
	}

}
