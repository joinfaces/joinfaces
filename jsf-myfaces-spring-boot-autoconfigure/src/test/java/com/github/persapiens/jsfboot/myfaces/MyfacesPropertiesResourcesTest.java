package com.github.persapiens.jsfboot.myfaces;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class MyfacesPropertiesResourcesTest {

	public void testResourceMaxTimeExpires() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setResourceMaxTimeExpires(604800000);

		assertThat(myfacesProperties.getResourceMaxTimeExpires()).isEqualTo(604800000);
	}

	public void testResourceHandlerCacheSize() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setResourceHandlerCacheSize(500);

		assertThat(myfacesProperties.getResourceHandlerCacheSize()).isEqualTo(500);
	}

	public void testResourceHandlerCacheEnabled() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setResourceHandlerCacheEnabled(true);

		assertThat(myfacesProperties.getResourceHandlerCacheEnabled()).isTrue();
	}

	public void testStrictJsf2AllowSlashLibraryName() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setStrictJsf2AllowSlashLibraryName(true);

		assertThat(myfacesProperties.getStrictJsf2AllowSlashLibraryName()).isTrue();
	}

	public void testResourceBufferSize() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setResourceBufferSize(2048);

		assertThat(myfacesProperties.getResourceBufferSize()).isEqualTo(2048);
	}

}
