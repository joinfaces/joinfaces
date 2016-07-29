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

import org.testng.annotations.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = MyfacesSpringBootAutoConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Test
public class MyfacesPropertiesViewHandlingIT extends AbstractTestNGSpringContextTests {

	@Autowired
	private MyfacesProperties myfacesProperties;

	public void testCheckedViewidCacheSize() {
		assertThat(this.myfacesProperties.getCheckedViewidCacheSize()).isEqualTo(500);
	}

	public void testCheckedViewidCacheEnabled() {
		assertThat(this.myfacesProperties.getCheckedViewidCacheEnabled()).isTrue();
	}

	public void testViewUniqueIdsCacheEnabled() {
		assertThat(this.myfacesProperties.getViewUniqueIdsCacheEnabled()).isTrue();
	}

	public void testComponentUniqueIdsCacheSize() {
		assertThat(this.myfacesProperties.getComponentUniqueIdsCacheSize()).isEqualTo(100);
	}

	public void testStrictJsf2ViewNotFound() {
		assertThat(this.myfacesProperties.getStrictJsf2ViewNotFound()).isTrue();
	}

	public void testStrictJsf2FaceletsCompatibility() {
		assertThat(this.myfacesProperties.getStrictJsf2FaceletsCompatibility()).isTrue();
	}

}
