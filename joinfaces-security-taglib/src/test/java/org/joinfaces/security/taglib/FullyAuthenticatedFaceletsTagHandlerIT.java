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

package org.joinfaces.security.taglib;

import java.io.IOException;

import org.joinfaces.test.mock.JsfIT;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link FullyAuthenticatedFaceletsTagHandler}.
 */
@SpringBootTest(classes = SecurityTestConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class FullyAuthenticatedFaceletsTagHandlerIT extends JsfIT {

	@Test
	@WithAnonymousUser
	void testNotAuthorize() throws IOException {
		FullyAuthenticatedFaceletsTagHandler tag = new FullyAuthenticatedFaceletsTagHandler(
			getJsfMock().getMockTagConfig());

		tag.apply(null, null);

		assertThat(getJsfMock().getMockFaceletHandler().isApplied())
			.isFalse();
	}

	@Test
	@WithMockUser(roles = "A")
	void testAuthorize() throws IOException {
		FullyAuthenticatedFaceletsTagHandler tag = new FullyAuthenticatedFaceletsTagHandler(
			getJsfMock().getMockTagConfig());

		tag.apply(null, null);

		assertThat(getJsfMock().getMockFaceletHandler().isApplied())
			.isTrue();
	}

}
