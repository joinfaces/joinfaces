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

package com.github.persapiens.jsfboot.security;

import java.io.IOException;

import com.github.persapiens.jsfboot.mock.JsfIT;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Unit tests for {@link FaceletsTagUtils}.
 */
@SpringApplicationConfiguration(classes = SecurityConfiguration.class)
@Test
@WebAppConfiguration
public class FaceletsTagUtilsIT extends JsfIT {

	public void testConstructor() {
		assertThat(new FaceletsTagUtils())
			.isNotNull();
	}

	public void testAnonymous() throws IOException {
		Authentication authentication = AuthenticationFactory.authentication(Roles.ROLE_A);
		new SpringSecurityMock().init(authentication);

		assertThat(FaceletsTagUtils.isAnonymous())
			.isFalse();
	}

	public void testAuthenticated() throws IOException {
		Authentication authentication = AuthenticationFactory.authentication(Roles.ROLE_A);
		new SpringSecurityMock().init(authentication);

		assertThat(FaceletsTagUtils.isAuthenticated())
			.isTrue();
	}

	public void testFullyAuthenticated() throws IOException {
		Authentication authentication = AuthenticationFactory.authentication(Roles.ROLE_A);
		new SpringSecurityMock().init(authentication);

		assertThat(FaceletsTagUtils.isFullyAuthenticated())
			.isTrue();
	}

	public void testAreNotGranted() throws IOException {
		Authentication authentication = AuthenticationFactory.authentication(Roles.ROLE_A);
		new SpringSecurityMock().init(authentication);

		assertThat(FaceletsTagUtils.areNotGranted(Roles.ROLE_C))
			.isTrue();
	}

	public void testAreAllGranted() throws IOException {
		Authentication authentication = AuthenticationFactory.authentication(
			Roles.ROLE_A, Roles.ROLE_B);
		new SpringSecurityMock().init(authentication);

		assertThat(FaceletsTagUtils.areAllGranted(Roles.ROLE_A + "," + Roles.ROLE_C))
			.isFalse();
	}

	public void testAreAnyGranted() throws IOException {
		Authentication authentication = AuthenticationFactory.authentication(
			Roles.ROLE_A, Roles.ROLE_B);
		new SpringSecurityMock().init(authentication);

		assertThat(FaceletsTagUtils.areAnyGranted(Roles.ROLE_A + "," + Roles.ROLE_C))
			.isTrue();
	}

	public void testIsAllowed() throws IOException {
		Authentication authentication = AuthenticationFactory.authentication(
			Roles.ROLE_A, Roles.ROLE_B);
		new SpringSecurityMock().init(authentication);

		assertThat(FaceletsTagUtils.isAllowed("myurl", "mymethod"))
			.isTrue();
	}

	public void testIsAllowedFalse() throws IOException {
		new SpringSecurityMock().init(null);

		assertThat(FaceletsTagUtils.isAllowed("myurl", "mymethod"))
			.isFalse();
	}

}
