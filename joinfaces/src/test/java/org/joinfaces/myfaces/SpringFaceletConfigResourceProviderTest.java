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

import org.apache.myfaces.spi.FaceletConfigResourceProvider;
import org.apache.myfaces.view.facelets.compiler.DefaultFaceletConfigResourceProvider;
import org.junit.jupiter.api.Test;

import org.springframework.util.StopWatch;

import static org.assertj.core.api.Assertions.assertThat;

class SpringFaceletConfigResourceProviderTest extends MyFacesSpiTest {

	private FaceletConfigResourceProvider springProvider = new SpringFaceletConfigResourceProvider();
	private FaceletConfigResourceProvider defaultProvider = new DefaultFaceletConfigResourceProvider();

	@Test
	void getFaceletTagLibConfigurationResources() throws IOException {

		Collection<URL> resources = this.springProvider.getFaceletTagLibConfigurationResources(this.externalContext);

		assertThat(resources).isNotEmpty();
	}

	@Test
	void test_compare() throws IOException {

		Collection<URL> actual = this.springProvider.getFaceletTagLibConfigurationResources(this.externalContext);
		Collection<URL> expected = this.defaultProvider.getFaceletTagLibConfigurationResources(this.externalContext);

		assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);


		for (int i = 0; i < 50; i++) {
			this.springProvider.getFaceletTagLibConfigurationResources(this.externalContext);
			this.defaultProvider.getFaceletTagLibConfigurationResources(this.externalContext);
		}

		StopWatch stopWatch = new StopWatch();
		stopWatch.start("spring");
		for (int i = 0; i < 100; i++) {
			this.springProvider.getFaceletTagLibConfigurationResources(this.externalContext);
		}
		stopWatch.stop();

		stopWatch.start("default");
		for (int i = 0; i < 100; i++) {
			this.defaultProvider.getFaceletTagLibConfigurationResources(this.externalContext);
		}
		stopWatch.stop();

		System.out.println(stopWatch.prettyPrint());
	}
}
