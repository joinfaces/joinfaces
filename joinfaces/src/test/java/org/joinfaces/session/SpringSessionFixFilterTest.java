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

package org.joinfaces.session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.joinfaces.session.SpringSessionFixFilter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.mock;
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

}
