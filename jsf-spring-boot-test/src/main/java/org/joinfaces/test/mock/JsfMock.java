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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.application.Application;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.Tag;
import javax.faces.view.facelets.TagConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.Getter;
import org.mockito.Mockito;

import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.WebApplicationContext;

/**
 * JSF Mock.
 *
 * Taken from
 * http://ovaraksin.blogspot.com.br/2014/03/set-up-jsf-environment-for-junit-tests.html
 *
 * @author Marcelo Romulo Fernandes
 */
@Getter
public class JsfMock {

	private FacesContext mockFacesContext;
	private UIViewRoot mockViewRoot;
	private Application mockApplication;
	private ExternalContext mockExternalContext;
	private HttpSession mockHttpSession;
	private HttpServletRequest mockHttpServletRequest;
	private HttpServletResponse mockHttpServletResponse;
	private Map<String, Object> mockViewMap;
	private MockServletContext mockServletContext;
	private TagConfig mockTagConfig;
	private MockFaceletHandler mockFaceletHandler;
	private Tag mockTag;
	private MockTagAttributes mockTagAttributes;
	private MockFaceletContext mockFaceletContext;

	public void release() {
		this.mockFacesContext.release();
	}

	public void init(ApplicationContext applicationContext) {
		this.mockFacesContext = FacesContextMocker.mockFacesContext();
		this.mockApplication = Mockito.mock(Application.class);
		this.mockViewRoot = Mockito.mock(UIViewRoot.class);
		this.mockExternalContext = Mockito.mock(ExternalContext.class);
		this.mockHttpServletRequest = Mockito.mock(HttpServletRequest.class);
		this.mockHttpServletResponse = Mockito.mock(HttpServletResponse.class);
		this.mockHttpSession = Mockito.mock(HttpSession.class);
		this.mockTagConfig = Mockito.mock(TagConfig.class);
		this.mockFaceletHandler = new MockFaceletHandler();
		this.mockTagAttributes = new MockTagAttributes();
		this.mockTag = new Tag(null, null, null, null, this.mockTagAttributes);
		this.mockFaceletContext = new MockFaceletContext(this.mockFacesContext);

		this.mockViewMap = new HashMap<String, Object>();
		this.mockServletContext = new MockServletContext();
		if (applicationContext != null) {
			this.mockServletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, applicationContext);
		}

		Mockito.when(this.mockTagConfig.getNextHandler()).thenReturn(this.mockFaceletHandler);
		Mockito.when(this.mockTagConfig.getTag()).thenReturn(this.mockTag);

		Mockito.when(this.mockFacesContext.getApplication()).thenReturn(this.mockApplication);
		Mockito.when(this.mockApplication.getSupportedLocales()).thenReturn(createLocales().iterator());

		Mockito.when(this.mockFacesContext.getViewRoot()).thenReturn(this.mockViewRoot);
		Mockito.when(this.mockViewRoot.getLocale()).thenReturn(new Locale("en"));
		Mockito.when(this.mockViewRoot.getViewMap()).thenReturn(this.mockViewMap);

		Mockito.when(this.mockFacesContext.getExternalContext()).thenReturn(this.mockExternalContext);
		Mockito.when(this.mockExternalContext.getRequest()).thenReturn(this.mockHttpServletRequest);
		Mockito.when(this.mockHttpServletRequest.getSession()).thenReturn(this.mockHttpSession);
		Mockito.when(this.mockExternalContext.getResponse()).thenReturn(this.mockHttpServletResponse);
		Mockito.when(this.mockExternalContext.getContext()).thenReturn(this.mockServletContext);

		Map<String, String> requestMap = new HashMap<String, String>();
		Mockito.when(this.mockExternalContext.getRequestParameterMap()).thenReturn(requestMap);
	}

	private List<Locale> createLocales() {
		ArrayList<Locale> locales = new ArrayList<Locale>();
		locales.add(new Locale("en"));
		locales.add(new Locale("de"));
		locales.add(new Locale("pt_BR"));
		return locales;
	}
}
