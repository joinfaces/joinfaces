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

import javax.el.FunctionMapper;
import javax.el.VariableMapper;
import javax.faces.context.FacesContext;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * @author Lars Grefer
 */
public class MockFaceletContextTest {

	private FacesContext facesContext;
	private MockFaceletContext mockFaceletContext;

	@Before
	public void setUp() throws Exception {
		this.facesContext = mock(FacesContext.class);
		this.mockFaceletContext = new MockFaceletContext(this.facesContext);
	}

	@Test
	public void testGetFacesContext() {
		assertThat(this.mockFaceletContext.getFacesContext()).isSameAs(this.facesContext);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGenerateUniqueId() {
		this.mockFaceletContext.generateUniqueId(null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetExpressionFactory() {
		this.mockFaceletContext.getExpressionFactory();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testSetVariableMapper() {
		this.mockFaceletContext.setVariableMapper(mock(VariableMapper.class));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testSetFunctionMapper() {
		this.mockFaceletContext.setFunctionMapper(mock(FunctionMapper.class));
	}

	@Test
	public void testAttributes() {
		Object object = new Object();

		this.mockFaceletContext.setAttribute("foo", object);

		assertThat(this.mockFaceletContext.getAttribute("foo")).isSameAs(object);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testIncludeFacelet_String() throws IOException {
		this.mockFaceletContext.includeFacelet(null, (String) null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testIncludeFacelet_URL() throws IOException {
		this.mockFaceletContext.includeFacelet(null, (URL) null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetELResolver() {
		this.mockFaceletContext.getELResolver();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetFunctionMapper() {
		this.mockFaceletContext.getFunctionMapper();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetVariableMapper() {
		this.mockFaceletContext.getVariableMapper();
	}

}
