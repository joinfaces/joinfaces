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

import jakarta.el.ValueExpression;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Lars Grefer
 */
public class MockTagAttributeTest {

	private static final String FOO = "foo";
	private MockTagAttribute mockTagAttribute;

	@BeforeEach
	public void setUp() {
		this.mockTagAttribute = new MockTagAttribute(FOO);
	}

	@Test
	public void testGetBoolean() {
		assertThat(Assertions.assertThrows(UnsupportedOperationException.class, () ->
				this.mockTagAttribute.getBoolean(null)
		)).isNotNull();
	}

	@Test
	public void testGetInt() {
		assertThat(Assertions.assertThrows(UnsupportedOperationException.class, () ->
				this.mockTagAttribute.getInt(null)
		)).isNotNull();
	}

	@Test
	public void testGetLocalName() {
		assertThat(Assertions.assertThrows(UnsupportedOperationException.class, () ->
				this.mockTagAttribute.getLocalName()
		)).isNotNull();
	}

	@Test
	public void testGetLocation() {
		assertThat(Assertions.assertThrows(UnsupportedOperationException.class, () ->
				this.mockTagAttribute.getLocation()
		)).isNotNull();
	}

	@Test
	public void testGetMethodExpression() {
		assertThat(Assertions.assertThrows(UnsupportedOperationException.class, () ->
				this.mockTagAttribute.getMethodExpression(null, null, null)
		)).isNotNull();
	}

	@Test
	public void testGetNamespace() {
		assertThat(Assertions.assertThrows(UnsupportedOperationException.class, () ->
				this.mockTagAttribute.getNamespace()
		)).isNotNull();
	}

	@Test
	public void testGetObject() {
		assertThat(Assertions.assertThrows(UnsupportedOperationException.class, () ->
				this.mockTagAttribute.getObject(null)
		)).isNotNull();
	}

	@Test
	public void testGetQName() {
		assertThat(Assertions.assertThrows(UnsupportedOperationException.class, () ->
				this.mockTagAttribute.getQName()
		)).isNotNull();
	}

	@Test
	public void testGetValue() {
		assertThat(this.mockTagAttribute.getValue()).isEqualTo(FOO);
	}

	@Test
	public void testGetValue1() {
		assertThat(this.mockTagAttribute.getValue(null)).isEqualTo(FOO);
	}

	@Test
	public void testGetObject1() {
		assertThat(Assertions.assertThrows(UnsupportedOperationException.class, () ->
				this.mockTagAttribute.getObject(null, null)
		)).isNotNull();
	}

	@Test
	public void testGetValueExpression() {
		ValueExpression valueExpression = this.mockTagAttribute.getValueExpression(null, null);

		assertThat(valueExpression).isInstanceOf(MockValueExpression.class);
		assertThat((String) valueExpression.getValue(null)).isEqualTo(FOO);
	}

	@Test
	public void testIsLiteral() {
		assertThat(Assertions.assertThrows(UnsupportedOperationException.class, () ->
				this.mockTagAttribute.isLiteral()
		)).isNotNull();
	}

}
