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

import org.joinfaces.test.mock.JsfIT;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link FaceletsTagUtils}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecurityConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class FaceletsTagUtilsIT extends JsfIT {

	@Test
	public void testConstructor() {
		assertThat(new FaceletsTagUtils())
			.isNotNull();
	}

	@Test
	public void testAnonymous() throws IOException {
		Authentication authentication = AuthenticationFactory.authentication(Roles.ROLE_A);
		new SpringSecurityMock().init(authentication);

		assertThat(FaceletsTagUtils.isAnonymous())
			.isFalse();
	}

	@Test
	public void testAuthenticated() throws IOException {
		Authentication authentication = AuthenticationFactory.authentication(Roles.ROLE_A);
		new SpringSecurityMock().init(authentication);

		assertThat(FaceletsTagUtils.isAuthenticated())
			.isTrue();
	}

	@Test
	public void testFullyAuthenticated() throws IOException {
		Authentication authentication = AuthenticationFactory.authentication(Roles.ROLE_A);
		new SpringSecurityMock().init(authentication);

		assertThat(FaceletsTagUtils.isFullyAuthenticated())
			.isTrue();
	}

	@Test
	public void testAreNotGranted() throws IOException {
		Authentication authentication = AuthenticationFactory.authentication(Roles.ROLE_A);
		new SpringSecurityMock().init(authentication);

		assertThat(FaceletsTagUtils.areNotGranted(Roles.ROLE_C))
			.isTrue();
	}

	@Test
	public void testAreAllGranted() throws IOException {
		Authentication authentication = AuthenticationFactory.authentication(
			Roles.ROLE_A, Roles.ROLE_B);
		new SpringSecurityMock().init(authentication);

		assertThat(FaceletsTagUtils.areAllGranted(Roles.ROLE_A + "," + Roles.ROLE_C))
			.isFalse();
	}

	@Test
	public void testAreAnyGranted() throws IOException {
		Authentication authentication = AuthenticationFactory.authentication(
			Roles.ROLE_A, Roles.ROLE_B);
		new SpringSecurityMock().init(authentication);

		assertThat(FaceletsTagUtils.areAnyGranted(Roles.ROLE_A + "," + Roles.ROLE_C))
			.isTrue();
	}

	@Test
	public void testIsAllowed() throws IOException {
		Authentication authentication = AuthenticationFactory.authentication(
			Roles.ROLE_A, Roles.ROLE_B);
		new SpringSecurityMock().init(authentication);

		assertThat(FaceletsTagUtils.isAllowed("myurl", "mymethod"))
			.isTrue();
	}

	@Test
	public void testIsAllowedFalse() throws IOException {
		new SpringSecurityMock().init(null);

		assertThat(FaceletsTagUtils.isAllowed("myurl", "mymethod"))
			.isFalse();
	}

}
