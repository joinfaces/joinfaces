package com.github.persapiens.jsfboot.angularfaces;

import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringApplicationConfiguration(classes = AngularfacesSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class AngularfacesPropertiesIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private AngularfacesProperties angularfacesProperties;
    
	public void testAddLabels() {
		assertThat(angularfacesProperties.getAddLabels()).isTrue();
	}

	public void testAddMessages() {
		assertThat(angularfacesProperties.getAddMessages()).isTrue();
	}

	public void testTranslation() {
		assertThat(angularfacesProperties.getTranslation()).isTrue();
	}

	public void testIncludeAngularJS() {
		assertThat(angularfacesProperties.getIncludeAngularJS()).isTrue();
	}

	public void testIncludeJQuery() {
		assertThat(angularfacesProperties.getIncludeJQuery()).isTrue();
	}

	public void testIncludeJQueryUI() {
		assertThat(angularfacesProperties.getIncludeJQueryUI()).isTrue();
	}

	public void testIncludeAngularMessages() {
		assertThat(angularfacesProperties.getIncludeAngularMessages()).isTrue();
	}

	public void testClientSideMessages() {
		assertThat(angularfacesProperties.getClientSideMessages()).isTrue();
	}

	public void testIncludeMainJS() {
		assertThat(angularfacesProperties.getIncludeMainJS()).isTrue();
	}

}
