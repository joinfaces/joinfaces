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

package org.joinfaces.bootsfaces;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootsfacesSpringBootAutoConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class BootsfacesPropertiesIT {

	@Autowired
	private BootsfacesProperties bootsfacesProperties;

	@Test
	public void testTheme() {
		assertThat(this.bootsfacesProperties.getTheme()).isEqualTo("bootstrap");
	}

	@Test
	public void testUsetheme() {
		assertThat(this.bootsfacesProperties.getUsetheme()).isTrue();
	}

	@Test
	public void testUseViewport() {
		assertThat(this.bootsfacesProperties.getUseViewport()).isTrue();
	}

	@Test
	public void testDefaultsRenderLabel() {
		assertThat(this.bootsfacesProperties.getDefaults().getRenderLabel()).isEqualTo("myRenderLabel");
	}

	@Test
	public void testGetFontawesomeFromCdn() {
		assertThat(this.bootsfacesProperties.getGetFontawesomeFromCdn()).isTrue();
	}

	@Test
	public void testGetJqueryFromCdn() {
		assertThat(this.bootsfacesProperties.getGetJqueryFromCdn()).isTrue();
	}

	@Test
	public void testGetJqueryuiFromCdn() {
		assertThat(this.bootsfacesProperties.getGetJqueryuiFromCdn()).isTrue();
	}

	@Test
	public void testGetBootstrapFromCdn() {
		assertThat(this.bootsfacesProperties.getGetBootstrapFromCdn()).isTrue();
	}

	@Test
	public void testBlockUI() {
		assertThat(this.bootsfacesProperties.getBlockUI()).isFalse();
	}

}
