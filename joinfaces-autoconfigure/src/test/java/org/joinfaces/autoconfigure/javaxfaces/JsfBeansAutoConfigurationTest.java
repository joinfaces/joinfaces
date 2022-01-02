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

package org.joinfaces.autoconfigure.javaxfaces;

import java.util.Collections;
import java.util.Map;

import jakarta.faces.application.Application;
import jakarta.faces.component.UIViewRoot;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.flow.FlowHandler;

import org.joinfaces.test.mock.FacesContextMocker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.verify;
import static org.mockito.BDDMockito.when;

public class JsfBeansAutoConfigurationTest {

	private JsfBeansAutoConfiguration jsfBeansAutoConfiguration;
	private FacesContext facesContext;

	@BeforeEach
	public void setUp() {
		this.jsfBeansAutoConfiguration = new JsfBeansAutoConfiguration();
		this.facesContext = FacesContextMocker.mockFacesContext();
	}

	@Test
	public void testFacesContext() {
		assertThat(this.jsfBeansAutoConfiguration.facesContext()).isEqualTo(FacesContext.getCurrentInstance());
	}

	@Test
	public void testExternalContext() {
		ExternalContext externalContext = mock(ExternalContext.class);
		when(this.facesContext.getExternalContext()).thenReturn(externalContext);
		Application application = mock(Application.class);
		when(this.facesContext.getApplication()).thenReturn(application);
		FlowHandler flowHandler = mock(FlowHandler.class);
		when(application.getFlowHandler()).thenReturn(flowHandler);

		assertThat(this.jsfBeansAutoConfiguration.externalContext()).isEqualTo(externalContext);

		this.jsfBeansAutoConfiguration.application();
		verify(externalContext).getContext();

		this.jsfBeansAutoConfiguration.applicationMap();
		verify(externalContext).getApplicationMap();

		this.jsfBeansAutoConfiguration.requestCookieMap();
		verify(externalContext).getRequestCookieMap();

		this.jsfBeansAutoConfiguration.flash();
		verify(externalContext).getFlash();

		this.jsfBeansAutoConfiguration.headerMap();
		verify(externalContext).getRequestHeaderMap();

		this.jsfBeansAutoConfiguration.headerValuesMap();
		verify(externalContext).getRequestHeaderValuesMap();

		this.jsfBeansAutoConfiguration.initParameterMap();
		verify(externalContext).getInitParameterMap();

		this.jsfBeansAutoConfiguration.requestParameterMap();
		verify(externalContext).getRequestParameterMap();

		this.jsfBeansAutoConfiguration.requestParameterValuesMap();
		verify(externalContext).getRequestParameterValuesMap();

		this.jsfBeansAutoConfiguration.request();
		verify(externalContext).getRequest();

		this.jsfBeansAutoConfiguration.requestMap();
		verify(externalContext).getRequestMap();

		this.jsfBeansAutoConfiguration.session();
		verify(externalContext).getSession(false);

		this.jsfBeansAutoConfiguration.sessionMap();
		verify(externalContext).getSessionMap();

		this.jsfBeansAutoConfiguration.resourceHandler();
		verify(application).getResourceHandler();

		this.jsfBeansAutoConfiguration.flowScope();
		verify(flowHandler).getCurrentFlowScope();
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
