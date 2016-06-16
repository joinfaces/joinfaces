package com.github.persapiens.jsfboot.myfaces;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class MyfacesPropertiesCDITest {

	public void testCdiManagedConvertersEnabled() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setCdiManagedConvertersEnabled(true);

		assertThat(myfacesProperties.getCdiManagedConvertersEnabled()).isTrue();
	}

	public void testCdiManagedValidatorsEnabled() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setCdiManagedValidatorsEnabled(true);

		assertThat(myfacesProperties.getCdiManagedValidatorsEnabled()).isTrue();
	}

}
