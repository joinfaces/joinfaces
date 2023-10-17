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

import jakarta.el.ELContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * @author Lars Grefer
 */
public class MockValueExpressionTest {

	private static final String FOO = "foo";
	private MockValueExpression mockValueExpression;

	@BeforeEach
	public void setUp() {
		this.mockValueExpression = new MockValueExpression(FOO);
	}

	@Test
	public void testGetValue_null() {
		assertThat(this.mockValueExpression.getValue(null)).isEqualTo(FOO);
	}

	@Test
	public void testGetValue_mock() {
		assertThat(this.mockValueExpression.getValue(mock(ELContext.class))).isEqualTo(FOO);
	}

	@Test
	public void testSetValue() {
		assertThat(Assertions.assertThrows(UnsupportedOperationException.class, () ->
				this.mockValueExpression.setValue(null, null)
		)).isNotNull();
	}

	@Test
	public void testIsReadOnly() {
		assertThat(Assertions.assertThrows(UnsupportedOperationException.class, () ->
				this.mockValueExpression.isReadOnly(null)
		)).isNotNull();
	}

	@Test
	public void testGetType() {
		assertThat(Assertions.assertThrows(UnsupportedOperationException.class, () ->
				this.mockValueExpression.getType(null)
		)).isNotNull();
	}

	@Test
	public void testGetExpectedType() {
		assertThat(Assertions.assertThrows(UnsupportedOperationException.class, () ->
				this.mockValueExpression.getExpectedType()
		)).isNotNull();
	}

	@Test
	public void testGetExpressionString() {
		assertThat(Assertions.assertThrows(UnsupportedOperationException.class, () ->
				this.mockValueExpression.getExpressionString()
		)).isNotNull();
	}

	@Test
	public void testIsLiteralText() {
		assertThat(Assertions.assertThrows(UnsupportedOperationException.class, () ->
				this.mockValueExpression.isLiteralText()
		)).isNotNull();
	}

}
