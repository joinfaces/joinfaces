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

import jakarta.el.FunctionMapper;
import jakarta.el.VariableMapper;
import jakarta.faces.context.FacesContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * @author Lars Grefer
 */
public class MockFaceletContextTest {

	private FacesContext facesContext;
	private MockFaceletContext mockFaceletContext;

	@BeforeEach
	public void setUp() {
		this.facesContext = mock(FacesContext.class);
		this.mockFaceletContext = new MockFaceletContext(this.facesContext);
	}

	@Test
	public void testGetFacesContext() {
		assertThat(this.mockFaceletContext.getFacesContext()).isSameAs(this.facesContext);
	}

	@Test
	public void testGenerateUniqueId() {
		assertThat(Assertions.assertThrows(UnsupportedOperationException.class, () ->
				this.mockFaceletContext.generateUniqueId(null)
		)).isNotNull();
	}

	@Test
	public void testGetExpressionFactory() {
		assertThat(Assertions.assertThrows(UnsupportedOperationException.class, () ->
				this.mockFaceletContext.getExpressionFactory()
		)).isNotNull();
	}

	@Test
	public void testSetVariableMapper() {
		assertThat(Assertions.assertThrows(UnsupportedOperationException.class, () ->
				this.mockFaceletContext.setVariableMapper(mock(VariableMapper.class))
		)).isNotNull();
	}

	@Test
	public void testSetFunctionMapper() {
		assertThat(Assertions.assertThrows(UnsupportedOperationException.class, () ->
				this.mockFaceletContext.setFunctionMapper(mock(FunctionMapper.class))
		)).isNotNull();
	}

	@Test
	public void testAttributes() {
		Object object = new Object();

		this.mockFaceletContext.setAttribute("foo", object);

		assertThat(this.mockFaceletContext.getAttribute("foo")).isSameAs(object);
	}

	@Test
	public void testIncludeFacelet_String() {
		assertThat(Assertions.assertThrows(UnsupportedOperationException.class, () ->
				this.mockFaceletContext.includeFacelet(null, (String) null)
		)).isNotNull();
	}

	@Test
	public void testIncludeFacelet_URL() {
		assertThat(Assertions.assertThrows(UnsupportedOperationException.class, () ->
				this.mockFaceletContext.includeFacelet(null, (URL) null)
		)).isNotNull();
	}

	@Test
	public void testGetELResolver() {
		assertThat(Assertions.assertThrows(UnsupportedOperationException.class, () ->
				this.mockFaceletContext.getELResolver()
		)).isNotNull();
	}

	@Test
	public void testGetFunctionMapper() {
		assertThat(Assertions.assertThrows(UnsupportedOperationException.class, () ->
				this.mockFaceletContext.getFunctionMapper()
		)).isNotNull();
	}

	@Test
	public void testGetVariableMapper() {
		assertThat(Assertions.assertThrows(UnsupportedOperationException.class, () ->
				this.mockFaceletContext.getVariableMapper()
		)).isNotNull();
	}

}
