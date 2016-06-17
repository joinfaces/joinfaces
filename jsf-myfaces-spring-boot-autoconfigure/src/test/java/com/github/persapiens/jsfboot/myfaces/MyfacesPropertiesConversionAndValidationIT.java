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
public class MyfacesPropertiesConversionAndValidationIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private MyfacesProperties myfacesProperties;

	public void testEnumConverterAllowStringPasstrough() {
		assertThat(myfacesProperties.getEnumConverterAllowStringPasstrough()).isTrue();
	}

	public void testValidatorBeanBeforeJsfValidation() {
		assertThat(myfacesProperties.getValidator().getBeanBeforeJsfValidation()).isTrue();
	}

}
