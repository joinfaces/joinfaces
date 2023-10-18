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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

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
		assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() ->
				this.mockTagAttribute.getBoolean(null)
		);
	}

	@Test
	public void testGetInt() {
		assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() ->
				this.mockTagAttribute.getInt(null)
		);
	}

	@Test
	public void testGetLocalName() {
		assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() ->
				this.mockTagAttribute.getLocalName()
		);
	}

	@Test
	public void testGetLocation() {
		assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() ->
				this.mockTagAttribute.getLocation()
		);
	}

	@Test
	public void testGetMethodExpression() {
		assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() ->
				this.mockTagAttribute.getMethodExpression(null, null, null)
		);
	}

	@Test
	public void testGetNamespace() {
		assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() ->
				this.mockTagAttribute.getNamespace()
		);
	}

	@Test
	public void testGetObject() {
		assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() ->
				this.mockTagAttribute.getObject(null)
		);
	}

	@Test
	public void testGetQName() {
		assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() ->
				this.mockTagAttribute.getQName()
		);
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
		assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() ->
				this.mockTagAttribute.getObject(null, null)
		);
	}

	@Test
	public void testGetValueExpression() {
		ValueExpression valueExpression = this.mockTagAttribute.getValueExpression(null, null);

		assertThat(valueExpression).isInstanceOf(MockValueExpression.class);
		assertThat((String) valueExpression.getValue(null)).isEqualTo(FOO);
	}

	@Test
	public void testIsLiteral() {
		assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() ->
				this.mockTagAttribute.isLiteral()
		);
	}

}
