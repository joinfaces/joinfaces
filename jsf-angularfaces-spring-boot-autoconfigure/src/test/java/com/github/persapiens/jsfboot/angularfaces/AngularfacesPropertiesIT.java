/*
 * Copyright 2016-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.persapiens.jsfboot.angularfaces;

import org.testng.annotations.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringApplicationConfiguration(classes = AngularfacesSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class AngularfacesPropertiesIT extends AbstractTestNGSpringContextTests {

	@Autowired
	private AngularfacesProperties angularfacesProperties;

	public void testAddLabels() {
		assertThat(this.angularfacesProperties.getAddLabels()).isTrue();
	}

	public void testAddMessages() {
		assertThat(this.angularfacesProperties.getAddMessages()).isTrue();
	}

	public void testTranslation() {
		assertThat(this.angularfacesProperties.getTranslation()).isTrue();
	}

	public void testIncludeAngularJS() {
		assertThat(this.angularfacesProperties.getIncludeAngularJS()).isTrue();
	}

	public void testIncludeJQuery() {
		assertThat(this.angularfacesProperties.getIncludeJQuery()).isTrue();
	}

	public void testIncludeJQueryUI() {
		assertThat(this.angularfacesProperties.getIncludeJQueryUI()).isTrue();
	}

	public void testIncludeAngularMessages() {
		assertThat(this.angularfacesProperties.getIncludeAngularMessages()).isTrue();
	}

	public void testClientSideMessages() {
		assertThat(this.angularfacesProperties.getClientSideMessages()).isTrue();
	}

	public void testIncludeMainJS() {
		assertThat(this.angularfacesProperties.getIncludeMainJS()).isTrue();
	}

}
