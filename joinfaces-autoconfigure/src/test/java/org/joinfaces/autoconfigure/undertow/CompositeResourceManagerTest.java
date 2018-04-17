/*
 * Copyright 2016-2018 the original author or authors.
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

package org.joinfaces.autoconfigure.undertow;

import java.io.IOException;

import io.undertow.server.handlers.resource.ResourceManager;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.verify;

public class CompositeResourceManagerTest {

	@Test
	public void testClose() throws IOException {
		ResourceManager resourceManager1 = mock(ResourceManager.class);
		ResourceManager resourceManager2 = mock(ResourceManager.class);
		ResourceManager resourceManager3 = mock(ResourceManager.class);

		CompositeResourceManager compositeResourceManager = new CompositeResourceManager(
				resourceManager1, resourceManager2, resourceManager3
		);

		compositeResourceManager.close();

		verify(resourceManager1).close();
		verify(resourceManager2).close();
		verify(resourceManager3).close();
	}

	@Test
	public void testGetResource_empty() throws IOException {
		assertThat(new CompositeResourceManager().getResource(null)).isNull();
		assertThat(new CompositeResourceManager().getResource("")).isNull();
		assertThat(new CompositeResourceManager().getResource("/index.xhtml")).isNull();
	}

	@Test
	public void testIsResourceChangeListenerSupported() {
		CompositeResourceManager compositeResourceManager = new CompositeResourceManager();

		assertThat(compositeResourceManager.isResourceChangeListenerSupported()).isFalse();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRegisterResourceChangeListener() {
		new CompositeResourceManager().registerResourceChangeListener(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRemoveResourceChangeListener() {
		new CompositeResourceManager().removeResourceChangeListener(null);
	}

}
