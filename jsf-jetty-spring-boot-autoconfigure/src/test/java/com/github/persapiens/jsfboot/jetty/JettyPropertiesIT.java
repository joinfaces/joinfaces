package com.github.persapiens.jsfboot.jetty;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringApplicationConfiguration(classes = JettySpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class JettyPropertiesIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private JettyProperties jettyProperties;

	public void testForceLoadConfiguration() {
		assertThat(jettyProperties.getClassPathResource()).isEqualTo("public");
	}

}
