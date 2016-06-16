package com.github.persapiens.jsfboot.myfaces;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class MyfacesPropertiesConversionAndValidationTest {

	public void testEnumConverterAllowStringPasstrough() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setEnumConverterAllowStringPasstrough(true);

		assertThat(myfacesProperties.getEnumConverterAllowStringPasstrough()).isTrue();
	}

	public void testValidatorBeanBeforeJsfValidation() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.getValidator().setBeanBeforeJsfValidation(true);

		assertThat(myfacesProperties.getValidator().getBeanBeforeJsfValidation()).isTrue();
	}

}
