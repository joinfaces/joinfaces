package com.github.persapiens.jsfboot.javaxfaces;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class JavaxFacesPropertiesTest {

	public void testProjectState() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.setProjectStage("UnitTest");

		assertThat(javaxFacesProperties.getProjectStage()).isEqualTo("UnitTest");
	}

	public void testFaceletsRefreshPeriod() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.setFaceletsRefreshPeriod(10l);

		assertThat(javaxFacesProperties.getFaceletsRefreshPeriod()).isEqualTo(10);
	}

}
