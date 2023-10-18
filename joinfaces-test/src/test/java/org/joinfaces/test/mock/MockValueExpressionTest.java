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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
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
		assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() ->
				this.mockValueExpression.setValue(null, null)
		);
	}

	@Test
	public void testIsReadOnly() {
		assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() ->
				this.mockValueExpression.isReadOnly(null)
		);
	}

	@Test
	public void testGetType() {
		assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() ->
				this.mockValueExpression.getType(null)
		);
	}

	@Test
	public void testGetExpectedType() {
		assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() ->
				this.mockValueExpression.getExpectedType()
		);
	}

	@Test
	public void testGetExpressionString() {
		assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() ->
				this.mockValueExpression.getExpressionString()
		);
	}

	@Test
	public void testIsLiteralText() {
		assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() ->
				this.mockValueExpression.isLiteralText()
		);
	}

}
