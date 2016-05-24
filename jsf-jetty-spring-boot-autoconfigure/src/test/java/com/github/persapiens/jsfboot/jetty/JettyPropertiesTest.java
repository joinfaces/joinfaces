package com.github.persapiens.jsfboot.jetty;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class JettyPropertiesTest {

	public void testForceLoadConfiguration() {
		JettyProperties jettyProperties = new JettyProperties();
        jettyProperties.setClassPathResource("public");

		assertThat(jettyProperties.getClassPathResource()).isEqualTo("public");
	}

}
