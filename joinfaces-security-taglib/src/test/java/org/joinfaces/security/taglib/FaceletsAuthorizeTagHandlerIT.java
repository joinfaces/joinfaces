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

package org.joinfaces.security.taglib;

import java.io.IOException;

import javax.faces.view.facelets.FaceletContext;

import org.joinfaces.test.mock.JsfIT;
import org.joinfaces.test.mock.MockTagAttribute;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link FaceletsAuthorizeTagHandler}.
 */
@SpringBootTest(classes = SecurityConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class FaceletsAuthorizeTagHandlerIT extends JsfIT {

	@Test
	void testApplyFalse() throws IOException {
		FaceletsAuthorizeTagHandler tag = new FaceletsAuthorizeTagHandler(
			getJsfMock().getMockTagConfig());

		tag.apply(null, null);

		assertThat(getJsfMock().getMockFaceletHandler().isApplied())
			.isFalse();
	}

	@Test
	@WithMockUser(roles = "A")
	void testApplyAccessFalse() throws IOException {
		getJsfMock().getMockTagAttributes().getTagAttributes().put(
			"access", new MockTagAttribute("hasAnyRole('ROLE_B')"));

		FaceletsAuthorizeTagHandler tag = new FaceletsAuthorizeTagHandler(
			getJsfMock().getMockTagConfig());

		tag.apply(null, null);

		assertThat(getJsfMock().getMockFaceletHandler().isApplied())
			.isFalse();
	}

	@Test
	@WithMockUser(roles = "A")
	void testApplyAccess() throws IOException {
		getJsfMock().getMockTagAttributes().getTagAttributes().put(
			"access", new MockTagAttribute("hasAnyRole('ROLE_A')"));

		FaceletsAuthorizeTagHandler tag = new FaceletsAuthorizeTagHandler(
			getJsfMock().getMockTagConfig());

		tag.apply(null, null);

		assertThat(getJsfMock().getMockFaceletHandler().isApplied())
			.isTrue();
	}

	@Test
	@WithMockUser(roles = "A")
	void testApplyIfAllGranted() throws IOException {
		getJsfMock().getMockTagAttributes().getTagAttributes().put(
			"ifAllGranted", new MockTagAttribute(Roles.ROLE_A));

		FaceletsAuthorizeTagHandler tag = new FaceletsAuthorizeTagHandler(
			getJsfMock().getMockTagConfig());

		tag.apply(getJsfMock().getMockFaceletContext(), null);

		assertThat(getJsfMock().getMockFaceletHandler().isApplied())
			.isTrue();
	}

	@Test
	@WithMockUser(roles = "A")
	void testApplyVar() throws IOException {
		MockTagAttribute myVariableTagAttribute = new MockTagAttribute("myVariable");
		getJsfMock().getMockTagAttributes().getTagAttributes().put(
			"var", myVariableTagAttribute);
		getJsfMock().getMockTagAttributes().getTagAttributes().put(
			"ifAllGranted", new MockTagAttribute(Roles.ROLE_A));

		FaceletContext mockFaceletContext = getJsfMock().getMockFaceletContext();

		FaceletsAuthorizeTagHandler tag = new FaceletsAuthorizeTagHandler(
			getJsfMock().getMockTagConfig());

		tag.apply(mockFaceletContext, null);

		assertThat(mockFaceletContext.getAttribute("myVariable"))
			.isEqualTo(Boolean.TRUE);
	}

}
