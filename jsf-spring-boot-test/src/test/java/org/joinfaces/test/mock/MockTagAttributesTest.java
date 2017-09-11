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

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Lars Grefer
 */
public class MockTagAttributesTest {

	private MockTagAttributes mockTagAttributes;

	@Before
	public void setUp() {
		this.mockTagAttributes = new MockTagAttributes();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetAll() {
		this.mockTagAttributes.getAll();
	}

	@Test
	public void testGet() {
		assertThat(this.mockTagAttributes.get("foo")).isNull();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGet1() {
		this.mockTagAttributes.get(null, null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetAll1() {
		this.mockTagAttributes.getAll(null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetNamespaces() {
		this.mockTagAttributes.getNamespaces();
	}

}
