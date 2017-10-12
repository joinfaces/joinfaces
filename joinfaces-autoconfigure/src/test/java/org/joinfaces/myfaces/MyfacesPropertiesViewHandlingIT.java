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

package org.joinfaces.myfaces;

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
public class MyfacesPropertiesViewHandlingIT {

	@Autowired
	private MyfacesProperties myfacesProperties;

	@Test
	public void testCheckedViewidCacheSize() {
		assertThat(this.myfacesProperties.getCheckedViewidCacheSize()).isEqualTo(500);
	}

	@Test
	public void testCheckedViewidCacheEnabled() {
		assertThat(this.myfacesProperties.getCheckedViewidCacheEnabled()).isTrue();
	}

	@Test
	public void testViewUniqueIdsCacheEnabled() {
		assertThat(this.myfacesProperties.getViewUniqueIdsCacheEnabled()).isTrue();
	}

	@Test
	public void testComponentUniqueIdsCacheSize() {
		assertThat(this.myfacesProperties.getComponentUniqueIdsCacheSize()).isEqualTo(100);
	}

	@Test
	public void testStrictJsf2ViewNotFound() {
		assertThat(this.myfacesProperties.getStrictJsf2ViewNotFound()).isTrue();
	}

	@Test
	public void testStrictJsf2FaceletsCompatibility() {
		assertThat(this.myfacesProperties.getStrictJsf2FaceletsCompatibility()).isTrue();
	}

}
