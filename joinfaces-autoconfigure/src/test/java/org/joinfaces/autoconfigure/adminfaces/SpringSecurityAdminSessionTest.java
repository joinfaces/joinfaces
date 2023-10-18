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

package org.joinfaces.autoconfigure.adminfaces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@ExtendWith(SpringExtension.class)
class SpringSecurityAdminSessionTest {

	private SpringSecurityAdminSession adminSession;

	@BeforeEach
	void setUp() {
		this.adminSession = new SpringSecurityAdminSession();
	}

	@Test
	void isLoggedIn() {
		assertThat(this.adminSession.isLoggedIn()).isFalse();
	}

	@Test
	@WithAnonymousUser
	void isLoggedIn_anon() {
		assertThat(this.adminSession.isLoggedIn()).isFalse();
	}

	@Test
	@WithMockUser(username = "foo")
	void isLoggedIn_foo() {
		assertThat(this.adminSession.isLoggedIn()).isTrue();
	}

	@Test
	void setIsLoggedIn() {
		assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() -> this.adminSession.setIsLoggedIn(true));
		assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() -> this.adminSession.setIsLoggedIn(false));
	}
}
