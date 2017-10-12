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
import javax.el.ValueExpression;

/**
 * Value Expression Mock.
 *
 * @author Marcelo Romulo Fernandes
 */
public class MockValueExpression extends ValueExpression {

	private static final String NOT_SUPPORTED_YET = "Not supported yet.";

	public MockValueExpression(String value) {
		this.value = value;
	}

	private String value;

	@Override
	public Object getValue(ELContext elc) {
		return this.value;
	}

	@Override
	public void setValue(ELContext elc, Object o) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public boolean isReadOnly(ELContext elc) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public Class<?> getType(ELContext elc) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public Class<?> getExpectedType() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public String getExpressionString() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public boolean equals(Object o) {
		boolean result = false;
		if (o instanceof MockValueExpression) {
			result = this.value.equals(((MockValueExpression) o).value);
		}
		return result;
	}

	@Override
	public int hashCode() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public boolean isLiteralText() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}
}
