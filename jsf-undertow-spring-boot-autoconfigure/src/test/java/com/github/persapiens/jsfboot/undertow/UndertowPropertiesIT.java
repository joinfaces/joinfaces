package com.github.persapiens.jsfboot.undertow;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringApplicationConfiguration(classes = UndertowSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class UndertowPropertiesIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private UndertowProperties undertowProperties;

	public void testForceLoadConfiguration() {
		assertThat(undertowProperties.getClassPathResource()).isEqualTo("public");
	}

}
