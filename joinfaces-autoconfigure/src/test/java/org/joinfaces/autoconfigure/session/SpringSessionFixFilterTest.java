/*
 * Copyright 2016-2019 the original author or authors.
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

package org.joinfaces.autoconfigure.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.when;

class SpringSessionFixFilterTest {

	@Test
	void testRequestWrapper() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getSession()).thenReturn(mock(HttpSession.class));
		when(request.getSession(true)).thenReturn(mock(HttpSession.class));
		when(request.getSession(false)).thenReturn(null);

		SpringSessionFixFilter.RequestWrapper requestWrapper = new SpringSessionFixFilter.RequestWrapper(request);

		assertThat(requestWrapper.getSession(false)).isNull();
		assertThat(requestWrapper.getSession(true)).isInstanceOf(SpringSessionFixFilter.RequestWrapper.SessionWrapper.class);
		assertThat(requestWrapper.getSession()).isInstanceOf(SpringSessionFixFilter.RequestWrapper.SessionWrapper.class);
	}

	@SuppressWarnings("deprecation")
	@Test
	void testSessionWrapper() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession()).thenReturn(session);

		SpringSessionFixFilter.RequestWrapper requestWrapper = new SpringSessionFixFilter.RequestWrapper(request);

		HttpSession sessionWrapper = requestWrapper.getSession();

		sessionWrapper.getAttribute("foo");
		sessionWrapper.getValue("bar");

		assertThat(requestWrapper.getReadAttributeNames()).contains("foo", "bar");

		sessionWrapper.invalidate();

		assertThat(requestWrapper.getReadAttributeNames()).isEmpty();
	}

}
