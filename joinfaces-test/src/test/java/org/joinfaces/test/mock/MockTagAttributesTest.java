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

package org.joinfaces.test.mock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Lars Grefer
 */
public class MockTagAttributesTest {

	private MockTagAttributes mockTagAttributes;

	@BeforeEach
	public void setUp() {
		this.mockTagAttributes = new MockTagAttributes();
	}

	@Test
	public void testGetAll() {
		assertThat(Assertions.assertThrows(UnsupportedOperationException.class, () ->
				this.mockTagAttributes.getAll()
		)).isNotNull();
	}

	@Test
	public void testGet() {
		assertThat(this.mockTagAttributes.get("foo")).isNull();
	}

	@Test
	public void testGet1() {
		assertThat(Assertions.assertThrows(UnsupportedOperationException.class, () ->
				this.mockTagAttributes.get(null, null)
		)).isNotNull();
	}

	@Test
	public void testGetAll1() {
		assertThat(Assertions.assertThrows(UnsupportedOperationException.class, () ->
				this.mockTagAttributes.getAll(null)
		)).isNotNull();
	}

	@Test
	public void testGetNamespaces() {
		assertThat(Assertions.assertThrows(UnsupportedOperationException.class, () ->
				this.mockTagAttributes.getNamespaces()
		)).isNotNull();
	}

}
