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
 * Unit tests for {@link AuthenticatedFaceletsTag}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecurityConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class AuthenticatedFaceletsTagIT extends JsfIT {

	@Test
	public void testAuthenticated() {
		AuthenticatedFaceletsTag tag = new AuthenticatedFaceletsTag();
		assertThat(tag.getAccess())
			.isEqualTo("isAuthenticated()");
	}

	@Test
	public void testNotAuthorize() throws IOException {
		new SpringSecurityMock().init(null);

		AuthenticatedFaceletsTag tag = new AuthenticatedFaceletsTag();
		assertThat(tag.authorize())
			.isFalse();
	}

	@Test
	public void testAuthorize() throws IOException {
		Authentication authentication = AuthenticationFactory.authentication(Roles.ROLE_A);
		new SpringSecurityMock().init(authentication);

		AuthenticatedFaceletsTag tag = new AuthenticatedFaceletsTag();
		assertThat(tag.authorize())
			.isTrue();
	}

}
