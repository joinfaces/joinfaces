package com.github.persapiens.jsfboot.primefaces;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class PrimefacesPropertiesTest {

	public void testTheme() {
		PrimefacesProperties primefacesProperties = new PrimefacesProperties();
        primefacesProperties.setTheme("cupertino");

		assertThat(primefacesProperties.getTheme()).isEqualTo("cupertino");
	}

}
