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

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link FullyAuthenticatedFaceletsTag}.
 */
@SpringApplicationConfiguration(classes = SecurityConfiguration.class)
@Test
@WebAppConfiguration
public class FullyAuthenticatedFaceletsTagIT extends JsfIT {

	public void testFullyAuthenticated() {
		FullyAuthenticatedFaceletsTag tag = new FullyAuthenticatedFaceletsTag();
		assertThat("isFullyAuthenticated()")
			.isEqualTo(tag.getAccess());
	}

	public void testNotAuthorize() throws IOException {
		new SpringSecurityMock().init(null);

		FullyAuthenticatedFaceletsTag tag = new FullyAuthenticatedFaceletsTag();
		assertThat(tag.authorize())
			.isFalse();
	}

	public void testAuthorize() throws IOException {
		Authentication authentication = AuthenticationFactory.authentication(Roles.ROLE_A);
		new SpringSecurityMock().init(authentication);

		FullyAuthenticatedFaceletsTag tag = new FullyAuthenticatedFaceletsTag();
		assertThat(tag.authorize())
			.isTrue();
	}

}
