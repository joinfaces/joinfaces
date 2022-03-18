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

package org.joinfaces.autoconfigure.session;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.times;
import static org.mockito.BDDMockito.verify;
import static org.mockito.BDDMockito.when;

class SpringSessionFixFilterTest {

	@Test
	void testRequestWrapper() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getSession()).thenReturn(mock(HttpSession.class));

		SpringSessionFixFilter.RequestWrapper requestWrapper = new SpringSessionFixFilter.RequestWrapper(request);

		assertThat(requestWrapper.getSession()).isInstanceOf(SpringSessionFixFilter.SessionWrapper.class);
	}

	@SuppressWarnings("deprecation")
	@Test
	void testSessionWrapper() {
		HttpSession session = mock(HttpSession.class);

		SpringSessionFixFilter.SessionWrapper sessionWrapper = new SpringSessionFixFilter.SessionWrapper(session);

		sessionWrapper.getAttribute("foo");
		sessionWrapper.getValue("bar");

		assertThat(sessionWrapper.getReadAttributeNames()).contains("foo", "bar");
	}

	@Test
	void testSetAttributeWithValidSession() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession(false)).thenReturn(session);
		when(request.getSession()).thenReturn(session);
		String fooName = "fooName";
		String fooValue = "fooValue";
		when(session.getAttributeNames()).thenReturn(Collections.enumeration(Arrays.asList(fooName)));
		when(session.getAttribute(fooName)).thenReturn(fooValue);

		HttpServletResponse response = mock(HttpServletResponse.class);

		FilterChain filterChain = (ServletRequest sr, ServletResponse sr1) -> {
			HttpSession session1 = ((HttpServletRequest) sr).getSession();
			session1.getAttribute("fooName");
		};

		SpringSessionFixFilter filter = new SpringSessionFixFilter();

		filter.doFilterInternal(request, response, filterChain);

		verify(session, times(1)).setAttribute(fooName, fooValue);
	}

	@Test
	void testSetAttributeWithInvalidatedSession() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession(false)).thenReturn(session);
		when(request.getSession()).thenReturn(session);
		String fooName = "fooName";
		String fooValue = "fooValue";

		HttpServletResponse response = mock(HttpServletResponse.class);

		FilterChain filterChain = (ServletRequest sr, ServletResponse sr1) -> {
			HttpSession session1 = ((HttpServletRequest) sr).getSession();
			session1.getAttribute(fooName);
			session1.invalidate();
		};

		SpringSessionFixFilter filter = new SpringSessionFixFilter();

		filter.doFilterInternal(request, response, filterChain);

		verify(session, times(0)).setAttribute(fooName, fooValue);
	}

}
