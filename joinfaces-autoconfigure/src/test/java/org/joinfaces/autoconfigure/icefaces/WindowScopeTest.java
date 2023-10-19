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

package org.joinfaces.autoconfigure.icefaces;

import java.net.MalformedURLException;

import javax.faces.application.Application;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.icefaces.impl.application.WindowScopeManager;
import org.joinfaces.test.mock.FacesContextMocker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.lang.NonNull;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.when;


/**
 * @author Lars Grefer
 */
public class WindowScopeTest {

	private WindowScope windowScope;

	private WindowScopeManager.ScopeMap scopeMap;

	@BeforeEach
	public void setUp() throws MalformedURLException {
		FacesContext facesContext = FacesContextMocker.mockFacesContext();
		ExternalContext externalContext = mock(ExternalContext.class);
		when(facesContext.getExternalContext()).thenReturn(externalContext);
		when(externalContext.getResource("/WEB-INF/portlet.xml")).thenReturn(null);
		Application application = mock(Application.class);
		when(facesContext.getApplication()).thenReturn(application);

		this.scopeMap = new WindowScopeManager.ScopeMap(FacesContext.getCurrentInstance());

		this.windowScope = new WindowScope() {
			@Override
			@NonNull
			WindowScopeManager.ScopeMap getScopeMap() {
				return WindowScopeTest.this.scopeMap;
			}
		};
	}

	@SuppressWarnings("unchecked")
	@Test
	public void get() {
		assertThat(this.scopeMap).doesNotContainKeys("foo");

		Object bean = new Object();

		assertThat(this.windowScope.get("foo", () -> bean))
				.isSameAs(bean);

		assertThat(this.scopeMap).containsKey("foo");

		assertThat(this.windowScope.get("foo", () -> {
			fail("The bean should already exist");
			return null;
		}))
				.isSameAs(bean);
	}

	@Test
	public void remove() {
		assertThat(this.windowScope.remove("bar")).isNull();
	}

	@Test
	public void registerDestructionCallback() {
		this.windowScope.registerDestructionCallback("foo", () -> {
		});
	}

	@Test
	public void resolveContextualObject() {
		assertThat(this.windowScope.resolveContextualObject("bar"))
				.isNull();
	}

	@Test
	public void getConversationId() {
		assertThat(this.windowScope.getConversationId())
				.isEqualTo(this.scopeMap.getId());
	}

	@Test
	public void testGetScopeMap_noScopeMap() {
		assertThatExceptionOfType(IllegalStateException.class).isThrownBy(() ->
				new WindowScope().getScopeMap()
		);
	}

	@Test
	public void testGetScopeMap_noFacesContext() {
		FacesContext.getCurrentInstance().release();
		assertThatExceptionOfType(IllegalStateException.class).isThrownBy(() ->
				new WindowScope().getScopeMap()
		);
	}
}
