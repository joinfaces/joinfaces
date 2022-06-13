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

import jakarta.faces.context.ExternalContext;

import org.apache.myfaces.config.DefaultFacesConfigResourceProvider;
import org.apache.myfaces.spi.FacesConfigResourceProvider;
import org.junit.jupiter.api.Test;

import org.springframework.mock.web.MockServletContext;
import org.springframework.util.StopWatch;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SpringFacesConfigResourceProviderTest {

	FacesConfigResourceProvider provider = new SpringFacesConfigResourceProvider();
	FacesConfigResourceProvider defaultProvider = new DefaultFacesConfigResourceProvider();

	@Test
	void test() throws IOException {
		Collection<URL> resources = provider.getMetaInfConfigurationResources(null);

		assertThat(resources).isNotEmpty();
		assertThat(resources).anyMatch(u -> u.toString().endsWith("test.faces-config.xml"));
	}

	@Test
	void test_compare() throws IOException {
		ExternalContext externalContext = mock(ExternalContext.class);
		when(externalContext.getContext()).thenReturn(new MockServletContext());

		Collection<URL> actual = provider.getMetaInfConfigurationResources(externalContext);
		Collection<URL> expected = defaultProvider.getMetaInfConfigurationResources(externalContext);

		assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);


		for (int i = 0; i < 50; i++) {
			provider.getMetaInfConfigurationResources(externalContext);
			defaultProvider.getMetaInfConfigurationResources(externalContext);
		}

		StopWatch stopWatch = new StopWatch();
		stopWatch.start("classgraph");
		for (int i = 0; i < 100; i++) {
			provider.getMetaInfConfigurationResources(externalContext);
		}
		stopWatch.stop();

		stopWatch.start("default");
		for (int i = 0; i < 100; i++) {
			defaultProvider.getMetaInfConfigurationResources(externalContext);
		}
		stopWatch.stop();

		System.out.println(stopWatch.prettyPrint());
	}

}
