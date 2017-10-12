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
public class MyfacesPropertiesResourcesIT {

	@Autowired
	private MyfacesProperties myfacesProperties;

	@Test
	public void testResourceMaxTimeExpires() {
		assertThat(this.myfacesProperties.getResourceMaxTimeExpires()).isEqualTo(604800000);
	}

	@Test
	public void testResourceHandlerCacheSize() {
		assertThat(this.myfacesProperties.getResourceHandlerCacheSize()).isEqualTo(500);
	}

	@Test
	public void testResourceHandlerCacheEnabled() {
		assertThat(this.myfacesProperties.getResourceHandlerCacheEnabled()).isTrue();
	}

	@Test
	public void testStrictJsf2AllowSlashLibraryName() {
		assertThat(this.myfacesProperties.getStrictJsf2AllowSlashLibraryName()).isTrue();
	}

	@Test
	public void testResourceBufferSize() {
		assertThat(this.myfacesProperties.getResourceBufferSize()).isEqualTo(2048);
	}

}
