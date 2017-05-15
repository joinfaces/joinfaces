/*
 * Copyright 2016-2016 the original author or authors.
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

package org.joinfaces.security;

import java.io.IOException;

import javax.faces.view.facelets.FaceletContext;

import org.joinfaces.test.mock.JsfIT;
import org.joinfaces.test.mock.MockTagAttribute;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link AuthorizeFaceletsTagHandler}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecurityConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class AuthorizeFaceletsTagHandlerIT extends JsfIT {

	@Test
	public void testApplyFalse() throws IOException {
		new SpringSecurityMock().init(null);

		AuthorizeFaceletsTagHandler tag = new AuthorizeFaceletsTagHandler(
			getJsfMock().getMockTagConfig());

		tag.apply(null, null);

		assertThat(getJsfMock().getMockFaceletHandler().isApplied())
			.isFalse();
	}

	@Test
	public void testApplyAccessFalse() throws IOException {
		Authentication authentication = AuthenticationFactory.authentication(Roles.ROLE_A);
		new SpringSecurityMock().init(authentication);

		getJsfMock().getMockTagAttributes().getTagAttributes().put(
			"access", new MockTagAttribute("hasAnyRole('ROLE_B')"));

		AuthorizeFaceletsTagHandler tag = new AuthorizeFaceletsTagHandler(
			getJsfMock().getMockTagConfig());

		tag.apply(null, null);

		assertThat(getJsfMock().getMockFaceletHandler().isApplied())
			.isFalse();
	}

	@Test
	public void testApplyAccess() throws IOException {
		Authentication authentication = AuthenticationFactory.authentication(Roles.ROLE_A);
		new SpringSecurityMock().init(authentication);

		getJsfMock().getMockTagAttributes().getTagAttributes().put(
			"access", new MockTagAttribute("hasAnyRole('ROLE_A')"));

		AuthorizeFaceletsTagHandler tag = new AuthorizeFaceletsTagHandler(
			getJsfMock().getMockTagConfig());

		tag.apply(null, null);

		assertThat(getJsfMock().getMockFaceletHandler().isApplied())
			.isTrue();
	}

	@Test
	public void testApplyIfAllGranted() throws IOException {
		Authentication authentication = AuthenticationFactory.authentication(Roles.ROLE_A);
		new SpringSecurityMock().init(authentication);

		getJsfMock().getMockTagAttributes().getTagAttributes().put(
			"ifAllGranted", new MockTagAttribute(Roles.ROLE_A));

		AuthorizeFaceletsTagHandler tag = new AuthorizeFaceletsTagHandler(
			getJsfMock().getMockTagConfig());

		tag.apply(getJsfMock().getMockFaceletContext(), null);

		assertThat(getJsfMock().getMockFaceletHandler().isApplied())
			.isTrue();
	}

	@Test
	public void testApplyVar() throws IOException {
		Authentication authentication = AuthenticationFactory.authentication(Roles.ROLE_A);
		new SpringSecurityMock().init(authentication);

		MockTagAttribute myVariableTagAttribute = new MockTagAttribute("myVariable");
		getJsfMock().getMockTagAttributes().getTagAttributes().put(
			"var", myVariableTagAttribute);
		getJsfMock().getMockTagAttributes().getTagAttributes().put(
			"ifAllGranted", new MockTagAttribute(Roles.ROLE_A));

		FaceletContext mockFaceletContext = getJsfMock().getMockFaceletContext();

		AuthorizeFaceletsTagHandler tag = new AuthorizeFaceletsTagHandler(
			getJsfMock().getMockTagConfig());

		tag.apply(mockFaceletContext, null);

		assertThat(mockFaceletContext.getAttribute("myVariable"))
			.isEqualTo(Boolean.TRUE);
	}

}
