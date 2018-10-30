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

package org.joinfaces.autoconfigure.viewscope;

import javax.faces.context.FacesContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.lang.Nullable;

import static org.mockito.BDDMockito.mock;

public class ViewScopeErrorsTest {

	public static final String BEAN_NAME = "bean";

	private ViewScope viewScope;

	@BeforeEach
	public void setUp() {
		this.viewScope = new ViewScope();
	}

	@Test
	public void testNoFacesContext_get() {
		DummyFacesContext.setInstance(null);

		Assertions.assertThrows(IllegalStateException.class, () ->
				this.viewScope.get(BEAN_NAME, Object::new)
		);
	}

	@Test
	public void testNoFacesContext_remove() {
		DummyFacesContext.setInstance(null);

		Assertions.assertThrows(IllegalStateException.class, () ->
				this.viewScope.remove(BEAN_NAME)
		);
	}

	@Test
	public void testNoFacesContext_getConversationId() {
		DummyFacesContext.setInstance(null);

		Assertions.assertThrows(IllegalStateException.class, () ->
				this.viewScope.getConversationId()
		);
	}

	@Test
	public void testNoViewRoot_get() {
		DummyFacesContext.setInstance(mock(FacesContext.class));

		Assertions.assertThrows(IllegalStateException.class, () ->
				this.viewScope.get(BEAN_NAME, Object::new)
		);
	}

	@Test
	public void testNoViewRoot_remove() {
		DummyFacesContext.setInstance(mock(FacesContext.class));

		Assertions.assertThrows(IllegalStateException.class, () ->
				this.viewScope.remove(BEAN_NAME)
		);
	}

	private static abstract class DummyFacesContext extends FacesContext {
		public static void setInstance(@Nullable FacesContext instance) {
			setCurrentInstance(instance);
		}
	}
}
