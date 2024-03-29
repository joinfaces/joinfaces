/*
 * Copyright 2016-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.joinfaces.myfaces;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;

import org.apache.myfaces.config.DefaultFacesConfigResourceProvider;
import org.apache.myfaces.spi.FacesConfigResourceProvider;
import org.junit.jupiter.api.Test;

import org.springframework.util.StopWatch;

import static org.assertj.core.api.Assertions.assertThat;

class SpringFacesConfigResourceProviderTest extends MyFacesSpiTest {

	private FacesConfigResourceProvider springProvider = new SpringFacesConfigResourceProvider();
	private FacesConfigResourceProvider defaultProvider = new DefaultFacesConfigResourceProvider();

	@Test
	void test() throws IOException {
		Collection<URL> resources = this.springProvider.getMetaInfConfigurationResources(this.externalContext);

		assertThat(resources).isNotEmpty();
		assertThat(resources).anyMatch(u -> u.toString().endsWith("test.faces-config.xml"));
	}

	@Test
	void test_compare() throws IOException {

		Collection<URL> actual = this.springProvider.getMetaInfConfigurationResources(this.externalContext);
		Collection<URL> expected = this.defaultProvider.getMetaInfConfigurationResources(this.externalContext);

		assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);


		for (int i = 0; i < 50; i++) {
			this.springProvider.getMetaInfConfigurationResources(this.externalContext);
			this.defaultProvider.getMetaInfConfigurationResources(this.externalContext);
		}

		StopWatch stopWatch = new StopWatch();
		stopWatch.start("spring");
		for (int i = 0; i < 100; i++) {
			this.springProvider.getMetaInfConfigurationResources(this.externalContext);
		}
		stopWatch.stop();

		stopWatch.start("default");
		for (int i = 0; i < 100; i++) {
			this.defaultProvider.getMetaInfConfigurationResources(this.externalContext);
		}
		stopWatch.stop();

		System.out.println(stopWatch.prettyPrint());
	}

}
