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

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.el.ELResolver;
import javax.el.ExpressionFactory;
import javax.el.FunctionMapper;
import javax.el.VariableMapper;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;

import lombok.Getter;

/**
 * Facelet Context Mock.
 *
 * @author Marcelo Romulo Fernandes
 */
public class MockFaceletContext extends FaceletContext {

	private static final String NOT_SUPPORTED_YET = "Not supported yet.";

	private FacesContext facesContext;

	public MockFaceletContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	@Getter
	private Map<String, Object> attributes = new HashMap<String, Object>();

	@Override
	public FacesContext getFacesContext() {
		return this.facesContext;
	}

	@Override
	public String generateUniqueId(String base) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public ExpressionFactory getExpressionFactory() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public void setVariableMapper(VariableMapper varMapper) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public void setFunctionMapper(FunctionMapper fnMapper) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public void setAttribute(String name, Object value) {
		this.attributes.put(name, value);
	}

	@Override
	public Object getAttribute(String name) {
		return this.attributes.get(name);
	}

	@Override
	public void includeFacelet(UIComponent parent, String relativePath) throws IOException {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public void includeFacelet(UIComponent parent, URL absolutePath) throws IOException {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public ELResolver getELResolver() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public FunctionMapper getFunctionMapper() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public VariableMapper getVariableMapper() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}
}
