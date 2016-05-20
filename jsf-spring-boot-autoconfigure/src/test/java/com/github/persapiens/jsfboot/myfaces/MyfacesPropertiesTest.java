package com.github.persapiens.jsfboot.myfaces;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class MyfacesPropertiesTest {

	public void testForceLoadConfiguration() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setAllowJavascript("true");

		assertThat(myfacesProperties.getAllowJavascript()).isEqualTo("true");
	}

}
