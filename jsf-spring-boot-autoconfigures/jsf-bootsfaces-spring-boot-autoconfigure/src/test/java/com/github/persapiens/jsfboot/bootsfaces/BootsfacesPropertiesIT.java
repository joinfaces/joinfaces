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

package com.github.persapiens.jsfboot.bootsfaces;

import org.testng.annotations.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringApplicationConfiguration(classes = BootsfacesSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class BootsfacesPropertiesIT extends AbstractTestNGSpringContextTests {

	@Autowired
	private BootsfacesProperties bootsfacesProperties;

	public void testTheme() {
		assertThat(this.bootsfacesProperties.getTheme()).isEqualTo("bootstrap");
	}

	public void testUsetheme() {
		assertThat(this.bootsfacesProperties.getUsetheme()).isTrue();
	}

	public void testUseViewport() {
		assertThat(this.bootsfacesProperties.getUseViewport()).isTrue();
	}

	public void testDefaultsRenderLabel() {
		assertThat(this.bootsfacesProperties.getDefaults().getRenderLabel()).isEqualTo("myRenderLabel");
	}

	public void testGetFontawesomeFromCdn() {
		assertThat(this.bootsfacesProperties.getGetFontawesomeFromCdn()).isTrue();
	}

	public void testGetJqueryFromCdn() {
		assertThat(this.bootsfacesProperties.getGetJqueryFromCdn()).isTrue();
	}

	public void testGetJqueryuiFromCdn() {
		assertThat(this.bootsfacesProperties.getGetJqueryuiFromCdn()).isTrue();
	}

	public void testGetBootstrapFromCdn() {
		assertThat(this.bootsfacesProperties.getGetBootstrapFromCdn()).isTrue();
	}

	public void testBlockUI() {
		assertThat(this.bootsfacesProperties.getBlockUI()).isFalse();
	}

}
