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

import org.joinfaces.mock.JsfIT;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Unit tests for {@link AuthorizeFaceletsTag}.
 */
@SpringApplicationConfiguration(classes = SecurityConfiguration.class)
@Test
@WebAppConfiguration
public class AuthorizeFaceletsTagIT extends JsfIT {

	public void testIfAllGrantedEmpty() {
		AuthorizeFaceletsTag tag = new AuthorizeFaceletsTag();
		tag.setIfAllGranted("");
		assertThat(tag.getAccess())
			.isNull();
	}

	public void testIfAnyGrantedEmpty() {
		AuthorizeFaceletsTag tag = new AuthorizeFaceletsTag();
		tag.setIfAnyGranted("");
		assertThat(tag.getAccess())
			.isNull();
	}

	public void testIfNotGrantedEmpty() {
		AuthorizeFaceletsTag tag = new AuthorizeFaceletsTag();
		tag.setIfNotGranted("");
		assertThat(tag.getAccess())
			.isNull();
	}

	public void testIfAllGrantedTwoRoles() {
		AuthorizeFaceletsTag tag = new AuthorizeFaceletsTag();
		tag.setIfAllGranted(Roles.ROLE_A);
		tag.setIfAllGranted(Roles.ROLE_B);
		assertThat(tag.getAccess())
			.isEqualTo("hasRole('ROLE_A') and hasRole('ROLE_B')");
	}

	public void testIfAllGrantedWithMultipleRoles() {
		AuthorizeFaceletsTag tag = new AuthorizeFaceletsTag();
		tag.setIfAllGranted("ROLE_A, ROLE_B, ROLE_C");
		assertThat(tag.getAccess())
			.isEqualTo("hasRole('ROLE_A') and hasRole('ROLE_B') and hasRole('ROLE_C')");
	}

	public void testIfAnyGrantedWithOneRole() {
		AuthorizeFaceletsTag tag = new AuthorizeFaceletsTag();
		tag.setIfAnyGranted(Roles.ROLE_A);
		assertThat(tag.getAccess())
			.isEqualTo("hasAnyRole('ROLE_A')");
	}

	public void testIfAnyGrantedWithMultipleRole() {
		AuthorizeFaceletsTag tag = new AuthorizeFaceletsTag();
		tag.setIfAnyGranted("ROLE_A, ROLE_B, ROLE_C");
		assertThat(tag.getAccess())
			.isEqualTo("hasAnyRole('ROLE_A','ROLE_B','ROLE_C')");
	}

	public void testIfNoneGrantedWithOneRole() {
		AuthorizeFaceletsTag tag = new AuthorizeFaceletsTag();
		tag.setIfNotGranted(Roles.ROLE_A);
		assertThat(tag.getAccess())
			.isEqualTo("!hasAnyRole('ROLE_A')");
	}

	public void testIfNoneGrantedWithMultipleRole() {
		AuthorizeFaceletsTag tag = new AuthorizeFaceletsTag();
		tag.setIfNotGranted("ROLE_A, ROLE_B, ROLE_C");
		assertThat(tag.getAccess())
			.isEqualTo("!hasAnyRole('ROLE_A','ROLE_B','ROLE_C')");
	}

	public void testIfAllAnyNotGranted() {
		AuthorizeFaceletsTag tag = new AuthorizeFaceletsTag();
		tag.setIfAllGranted(Roles.ROLE_A);
		tag.setIfAnyGranted(Roles.ROLE_B);
		tag.setIfNotGranted(Roles.ROLE_C);
		assertThat(tag.getAccess())
			.isEqualTo("hasRole('ROLE_A') and hasAnyRole('ROLE_B') and !hasAnyRole('ROLE_C')");
	}

}
