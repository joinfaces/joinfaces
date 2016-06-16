package com.github.persapiens.jsfboot.myfaces;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class MyfacesPropertiesViewHandlingTest {
        
	public void testCheckedViewidCacheSize() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setCheckedViewidCacheSize(500);

		assertThat(myfacesProperties.getCheckedViewidCacheSize()).isEqualTo(500);
	}

	public void testCheckedViewidCacheEnabled() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setCheckedViewidCacheEnabled(true);

		assertThat(myfacesProperties.getCheckedViewidCacheEnabled()).isTrue();
	}

	public void testViewUniqueIdsCacheEnabled() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setViewUniqueIdsCacheEnabled(true);

		assertThat(myfacesProperties.getViewUniqueIdsCacheEnabled()).isTrue();
	}

	public void testComponentUniqueIdsCacheSize() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setComponentUniqueIdsCacheSize(100);

		assertThat(myfacesProperties.getComponentUniqueIdsCacheSize()).isEqualTo(100);
	}

	public void testStrictJsf2ViewNotFound() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setStrictJsf2ViewNotFound(true);

		assertThat(myfacesProperties.getStrictJsf2ViewNotFound()).isTrue();
	}

	public void testStrictJsf2FaceletsCompatibility() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setStrictJsf2FaceletsCompatibility(true);

		assertThat(myfacesProperties.getStrictJsf2FaceletsCompatibility()).isTrue();
	}

}
