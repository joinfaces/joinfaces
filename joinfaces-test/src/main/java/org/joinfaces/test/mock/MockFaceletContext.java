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

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import jakarta.el.ELResolver;
import jakarta.el.ExpressionFactory;
import jakarta.el.FunctionMapper;
import jakarta.el.VariableMapper;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.facelets.FaceletContext;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Facelet Context Mock.
 *
 * @author Marcelo Romulo Fernandes
 */
@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
@NoArgsConstructor
@AllArgsConstructor
public class MockFaceletContext extends FaceletContext {

	private static final String NOT_SUPPORTED_YET = "Not supported yet.";

	private FacesContext facesContext;

	public MockFaceletContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	@Getter
	private Map<String, Object> attributes = new HashMap<>();

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
	public void includeFacelet(UIComponent parent, String relativePath) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public void includeFacelet(UIComponent parent, URL absolutePath) {
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
