/*
 * Copyright 2016-2018 the original author or authors.
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

package org.joinfaces.autoconfigure.integration.viewscope;

import javax.faces.context.FacesContext;

import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.BeanFactory;

import static org.mockito.BDDMockito.mock;

public class ViewScopeErrorsTest {

	private BeanFactory beanFactory;

	private ViewScope viewScope;

	@Before
	public void setUp() {
		this.beanFactory = mock(BeanFactory.class);
		this.viewScope = new ViewScope(this.beanFactory);
	}

	@Test(expected = IllegalStateException.class)
	public void testNoFacesContext_get() {
		DummyFacesContext.setInstance(null);

		this.viewScope.get("bean", Object::new);
	}

	@Test(expected = IllegalStateException.class)
	public void testNoFacesContext_remove() {
		DummyFacesContext.setInstance(null);

		this.viewScope.remove("bean");
	}

	@Test(expected = IllegalStateException.class)
	public void testNoViewRoot_get() {
		DummyFacesContext.setInstance(mock(FacesContext.class));

		this.viewScope.get("bean", Object::new);
	}

	@Test(expected = IllegalStateException.class)
	public void testNoViewRoot_remove() {
		DummyFacesContext.setInstance(mock(FacesContext.class));

		this.viewScope.remove("bean");
	}

	private static abstract class DummyFacesContext extends FacesContext {
		public static void setInstance(FacesContext instance) {
			setCurrentInstance(instance);
		}
	}
}
