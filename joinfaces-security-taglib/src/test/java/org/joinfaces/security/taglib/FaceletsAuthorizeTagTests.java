/*
 * Copyright 2016-2019 the original author or authors.
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

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link FaceletsAuthorizeTag}.
 *
 * @author Rossen Stoyanchev
 * @author Lars Grefer
 * @see <a href="https://github.com/spring-projects/spring-webflow/blob/2.5.x/spring-faces/src/test/java/org/springframework/faces/security/FaceletsAuthorizeTagTests.java">https://github.com/spring-projects/spring-webflow/blob/2.5.x/spring-faces/src/test/java/org/springframework/faces/security/FaceletsAuthorizeTagTests.java</a>
 */
class FaceletsAuthorizeTagTests {

	@Test
	void testIfAllGrantedWithOneRole() {
		FaceletsAuthorizeTag tag = new FaceletsAuthorizeTag();
		tag.setIfAllGranted("ROLE_A");
		assertThat(tag.getAccess()).isEqualTo("hasRole('ROLE_A')");
	}

	@Test
	void testIfAllGrantedWithMultipleRoles() {
		FaceletsAuthorizeTag tag = new FaceletsAuthorizeTag();
		tag.setIfAllGranted("ROLE_A, ROLE_B, ROLE_C");
		assertThat(tag.getAccess()).isEqualTo("hasRole('ROLE_A') and hasRole('ROLE_B') and hasRole('ROLE_C')");
	}

	@Test
	void testIfAnyGrantedWithOneRole() {
		FaceletsAuthorizeTag tag = new FaceletsAuthorizeTag();
		tag.setIfAnyGranted("ROLE_A");
		assertThat(tag.getAccess()).isEqualTo("hasAnyRole('ROLE_A')");
	}

	@Test
	void testIfAnyGrantedWithMultipleRole() {
		FaceletsAuthorizeTag tag = new FaceletsAuthorizeTag();
		tag.setIfAnyGranted("ROLE_A, ROLE_B, ROLE_C");
		assertThat(tag.getAccess()).isEqualTo("hasAnyRole('ROLE_A','ROLE_B','ROLE_C')");
	}

	@Test
	void testIfNoneGrantedWithOneRole() {
		FaceletsAuthorizeTag tag = new FaceletsAuthorizeTag();
		tag.setIfNotGranted("ROLE_A");
		assertThat(tag.getAccess()).isEqualTo("!hasAnyRole('ROLE_A')");
	}

	@Test
	void testIfNoneGrantedWithMultipleRole() {
		FaceletsAuthorizeTag tag = new FaceletsAuthorizeTag();
		tag.setIfNotGranted("ROLE_A, ROLE_B, ROLE_C");
		assertThat(tag.getAccess()).isEqualTo("!hasAnyRole('ROLE_A','ROLE_B','ROLE_C')");
	}

	@Test
	void testIfAllAnyNotGranted() {
		FaceletsAuthorizeTag tag = new FaceletsAuthorizeTag();
		tag.setIfAllGranted("ROLE_A");
		tag.setIfAnyGranted("ROLE_B");
		tag.setIfNotGranted("ROLE_C");
		assertThat(tag.getAccess()).isEqualTo("hasRole('ROLE_A') and hasAnyRole('ROLE_B') and !hasAnyRole('ROLE_C')");
	}

	@Test
	void testIfAllGrantedEmpty() {
		FaceletsAuthorizeTag tag = new FaceletsAuthorizeTag();
		tag.setIfAllGranted("");
		assertThat(tag.getAccess())
			.isNull();
	}

	@Test
	void testIfAnyGrantedEmpty() {
		FaceletsAuthorizeTag tag = new FaceletsAuthorizeTag();
		tag.setIfAnyGranted("");
		assertThat(tag.getAccess())
			.isNull();
	}

	@Test
	void testIfNotGrantedEmpty() {
		FaceletsAuthorizeTag tag = new FaceletsAuthorizeTag();
		tag.setIfNotGranted("");
		assertThat(tag.getAccess())
			.isNull();
	}

	@Test
	void testIfAllGrantedTwoRoles() {
		FaceletsAuthorizeTag tag = new FaceletsAuthorizeTag();
		tag.setIfAllGranted(Roles.ROLE_A);
		tag.setIfAllGranted(Roles.ROLE_B);
		assertThat(tag.getAccess())
			.isEqualTo("hasRole('ROLE_A') and hasRole('ROLE_B')");
	}

}
