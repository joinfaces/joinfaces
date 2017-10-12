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

package org.joinfaces.angularfaces;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(
		properties = "spring.profiles.active=propertyTest",
		webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
public class AngularfacesPropertiesIT {

	@Autowired
	private AngularfacesProperties angularfacesProperties;

	@Test
	public void testAddLabels() {
		assertThat(this.angularfacesProperties.getAddLabels()).isTrue();
	}

	@Test
	public void testAddMessages() {
		assertThat(this.angularfacesProperties.getAddMessages()).isTrue();
	}

	@Test
	public void testTranslation() {
		assertThat(this.angularfacesProperties.getTranslation()).isTrue();
	}

	@Test
	public void testIncludeAngularJS() {
		assertThat(this.angularfacesProperties.getIncludeAngularJs()).isTrue();
	}

	@Test
	public void testIncludeJQuery() {
		assertThat(this.angularfacesProperties.getIncludeJQuery()).isTrue();
	}

	@Test
	public void testIncludeJQueryUI() {
		assertThat(this.angularfacesProperties.getIncludeJQueryUi()).isTrue();
	}

	@Test
	public void testIncludeAngularMessages() {
		assertThat(this.angularfacesProperties.getIncludeAngularMessages()).isTrue();
	}

	@Test
	public void testClientSideMessages() {
		assertThat(this.angularfacesProperties.getClientSideMessages()).isTrue();
	}

	@Test
	public void testIncludeMainJS() {
		assertThat(this.angularfacesProperties.getIncludeMainJs()).isTrue();
	}

}
