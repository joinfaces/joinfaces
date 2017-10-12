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

import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.view.Location;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;

/**
 * Tag Attribute Mock.
 *
 * @author Marcelo Romulo Fernandes
 */
public class MockTagAttribute extends TagAttribute {

	private static final String NOT_SUPPORTED_YET = "Not supported yet.";

	private String value;

	public MockTagAttribute() {
	}

	public MockTagAttribute(String value) {
		this.value = value;
	}

	@Override
	public boolean getBoolean(FaceletContext ctx) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public int getInt(FaceletContext ctx) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public String getLocalName() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public Location getLocation() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public MethodExpression getMethodExpression(FaceletContext ctx, Class type, Class[] paramTypes) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public String getNamespace() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public Object getObject(FaceletContext ctx) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public String getQName() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public String getValue() {
		return this.value;
	}

	@Override
	public String getValue(FaceletContext ctx) {
		return this.value;
	}

	@Override
	public Object getObject(FaceletContext ctx, Class type) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public ValueExpression getValueExpression(FaceletContext ctx, Class type) {
		return new MockValueExpression(this.value);
	}

	@Override
	public boolean isLiteral() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}
}
