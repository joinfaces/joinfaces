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

import javax.el.ELContext;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * @author Lars Grefer
 */
public class MockValueExpressionTest {

	private static final String FOO = "foo";
	private MockValueExpression mockValueExpression;

	@Before
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

	@Test(expected = UnsupportedOperationException.class)
	public void testSetValue() {
		this.mockValueExpression.setValue(null, null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testIsReadOnly() {
		this.mockValueExpression.isReadOnly(null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetType() {
		this.mockValueExpression.getType(null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetExpectedType() {
		this.mockValueExpression.getExpectedType();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetExpressionString() {
		this.mockValueExpression.getExpressionString();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testIsLiteralText() {
		this.mockValueExpression.isLiteralText();
	}

	@Test
	@SuppressWarnings("ObjectEqualsNull")
	public void testEquals_null() {
		assertThat(this.mockValueExpression.equals(null)).isFalse();
	}

	@Test
	@SuppressWarnings("EqualsWithItself")
	public void testEquals_self() {
		assertThat(this.mockValueExpression.equals(this.mockValueExpression)).isTrue();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testHashCode() {
		assertThat(this.mockValueExpression.hashCode()).isNotZero();
	}

}
