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

package org.joinfaces.autoconfigure.javaxfaces;

import java.util.Collections;
import java.util.Map;

import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.joinfaces.test.mock.FacesContextMocker;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JsfBeansAutoConfigurationTest {

	private JsfBeansAutoConfiguration jsfBeansAutoConfiguration;
	private FacesContext facesContext;
	private ExternalContext externalContext;

	@Before
	public void setUp() {
		this.jsfBeansAutoConfiguration = new JsfBeansAutoConfiguration();
		this.facesContext = FacesContextMocker.mockFacesContext();
		this.externalContext = mock(ExternalContext.class);
		when(facesContext.getExternalContext()).thenReturn(this.externalContext);
	}

	@Test
	public void testApplication() {
		Object application = "foo";
		when(this.externalContext.getContext()).thenReturn(application);
		assertThat(this.jsfBeansAutoConfiguration.application()).isSameAs(application);
	}

	@Test
	public void testApplicationMap() {
		Map<String, Object> map = Collections.emptyMap();
		when(this.externalContext.getApplicationMap()).thenReturn(map);
		assertThat(this.jsfBeansAutoConfiguration.applicationMap()).isSameAs(map);
	}

	@Test
	public void testViewMap() {
		UIViewRoot viewRoot = mock(UIViewRoot.class);
		when(this.facesContext.getViewRoot()).thenReturn(viewRoot);

		assertThat(this.jsfBeansAutoConfiguration.viewRoot()).isEqualTo(viewRoot);

		Map<String, Object> map = Collections.emptyMap();
		when(viewRoot.getViewMap()).thenReturn(map);
		assertThat(this.jsfBeansAutoConfiguration.viewMap()).isSameAs(map);
	}
}
