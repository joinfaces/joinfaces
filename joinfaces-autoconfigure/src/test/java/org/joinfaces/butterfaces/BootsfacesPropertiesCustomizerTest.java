/*
 * Copyright 2016-2017 the original author or authors.
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

package org.joinfaces.butterfaces;

import org.joinfaces.bootsfaces.BootsfacesProperties;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Lars Grefer
 */
public class BootsfacesPropertiesCustomizerTest {

	private static final String TRUE = "true";

	private ButterfacesSpringBootAutoConfiguration.ButterfacesBootsfacesAutoConfiguration.BootsfacesPropertiesCustomizer bootsfacesPropertiesCustomizer;
	private BootsfacesProperties bootsfacesProperties;

	@Before
	public void init() {
		this.bootsfacesPropertiesCustomizer = new ButterfacesSpringBootAutoConfiguration.ButterfacesBootsfacesAutoConfiguration.BootsfacesPropertiesCustomizer();
		this.bootsfacesProperties = new BootsfacesProperties();
	}

	@Test
	public void testProcessNull() {
		this.bootsfacesProperties.setGetJqueryFromCdn(null);

		this.bootsfacesPropertiesCustomizer.postProcessAfterInitialization(this.bootsfacesProperties, null);

		assertThat(this.bootsfacesProperties.getGetJqueryFromCdn()).isEqualTo(TRUE);
	}

	@Test
	public void testProcessFalse() {
		this.bootsfacesProperties.setGetJqueryFromCdn("false");

		this.bootsfacesPropertiesCustomizer.postProcessAfterInitialization(this.bootsfacesProperties, null);

		assertThat(this.bootsfacesProperties.getGetJqueryFromCdn()).isEqualTo(TRUE);
	}

	@Test
	public void testProcessTrue() {
		this.bootsfacesProperties.setGetJqueryFromCdn(TRUE);

		this.bootsfacesPropertiesCustomizer.postProcessAfterInitialization(this.bootsfacesProperties, null);

		assertThat(this.bootsfacesProperties.getGetJqueryFromCdn()).isEqualTo(TRUE);
	}

	@Test
	public void testProcessEl() {
		this.bootsfacesProperties.setGetJqueryFromCdn("#{foo}");

		this.bootsfacesPropertiesCustomizer.postProcessAfterInitialization(this.bootsfacesProperties, null);

		assertThat(this.bootsfacesProperties.getGetJqueryFromCdn()).isEqualTo("#{foo}");
	}

}
