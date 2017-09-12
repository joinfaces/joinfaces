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

package org.joinfaces.test.mock;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Lars Grefer
 */
public class MockFaceletHandlerTest {

	private MockFaceletHandler mockFaceletHandler;

	@Before
	public void setUp() {
		this.mockFaceletHandler = new MockFaceletHandler();
	}

	@Test
	public void testApply() throws IOException {
		assertThat(this.mockFaceletHandler.isApplied()).isFalse();

		this.mockFaceletHandler.apply(null, null);

		assertThat(this.mockFaceletHandler.isApplied()).isTrue();
	}

}
