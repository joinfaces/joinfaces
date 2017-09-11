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

import javax.el.ValueExpression;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Lars Grefer
 */
public class MockTagAttributeTest {

	private static final String FOO = "foo";
	private MockTagAttribute mockTagAttribute;

	@Before
	public void setUp() {
		this.mockTagAttribute = new MockTagAttribute(FOO);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetBoolean() {
		this.mockTagAttribute.getBoolean(null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetInt() {
		this.mockTagAttribute.getInt(null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetLocalName() {
		this.mockTagAttribute.getLocalName();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetLocation() {
		this.mockTagAttribute.getLocation();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetMethodExpression() {
		this.mockTagAttribute.getMethodExpression(null, null, null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetNamespace() {
		this.mockTagAttribute.getNamespace();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetObject() {
		this.mockTagAttribute.getObject(null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetQName() {
		this.mockTagAttribute.getQName();
	}

	@Test
	public void testGetValue() {
		assertThat(this.mockTagAttribute.getValue()).isEqualTo(FOO);
	}

	@Test
	public void testGetValue1() {
		assertThat(this.mockTagAttribute.getValue(null)).isEqualTo(FOO);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetObject1() {
		this.mockTagAttribute.getObject(null, null);
	}

	@Test
	public void testGetValueExpression() {
		ValueExpression valueExpression = this.mockTagAttribute.getValueExpression(null, null);

		assertThat(valueExpression).isInstanceOf(MockValueExpression.class);
		assertThat(valueExpression.getValue(null)).isEqualTo(FOO);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testIsLiteral() {
		this.mockTagAttribute.isLiteral();
	}

}
