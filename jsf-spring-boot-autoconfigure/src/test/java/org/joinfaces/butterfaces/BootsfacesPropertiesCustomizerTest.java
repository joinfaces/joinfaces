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

	private ButterfacesSpringBootAutoConfiguration.ButterfacesBootsfacesAutoConfiguration.BootsfacesPropertiesCustomizer bootsfacesPropertiesCustomizer;
	private BootsfacesProperties bootsfacesProperties;

	@Before
	public void setUp() throws Exception {
		this.bootsfacesPropertiesCustomizer = new ButterfacesSpringBootAutoConfiguration.ButterfacesBootsfacesAutoConfiguration.BootsfacesPropertiesCustomizer();
		this.bootsfacesProperties = new BootsfacesProperties();
	}

	@Test
	public void processNull() throws Exception {
		this.bootsfacesProperties.setGetJqueryFromCdn(null);

		this.bootsfacesPropertiesCustomizer.process(this.bootsfacesProperties);

		assertThat(this.bootsfacesProperties.getGetJqueryFromCdn()).isEqualTo("true");
	}

	@Test
	public void processFalse() throws Exception {
		this.bootsfacesProperties.setGetJqueryFromCdn("false");

		this.bootsfacesPropertiesCustomizer.process(this.bootsfacesProperties);

		assertThat(this.bootsfacesProperties.getGetJqueryFromCdn()).isEqualTo("true");
	}

	@Test
	public void processTrue() throws Exception {
		this.bootsfacesProperties.setGetJqueryFromCdn("true");

		this.bootsfacesPropertiesCustomizer.process(this.bootsfacesProperties);

		assertThat(this.bootsfacesProperties.getGetJqueryFromCdn()).isEqualTo("true");
	}

	@Test
	public void processEl() throws Exception {
		this.bootsfacesProperties.setGetJqueryFromCdn("#{foo}");

		this.bootsfacesPropertiesCustomizer.process(this.bootsfacesProperties);

		assertThat(this.bootsfacesProperties.getGetJqueryFromCdn()).isEqualTo("#{foo}");
	}

}
