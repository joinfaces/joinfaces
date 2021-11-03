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

import java.io.IOException;

import org.joinfaces.test.mock.JsfIT;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link AnonymousFaceletsTag}.
 */
@SpringBootTest(classes = SecurityConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class AnonymousFaceletsTagIT extends JsfIT {

	@Test
	void testAnonymous() {
		AnonymousFaceletsTag tag = new AnonymousFaceletsTag();
		assertThat(tag.getAccess())
			.isEqualTo("isAnonymous()");
	}

	@Test
	void testNotAuthorize() throws IOException {
		AnonymousFaceletsTag tag = new AnonymousFaceletsTag();
		assertThat(tag.authorize())
			.isFalse();
	}

	@Test
	@WithAnonymousUser
	void testAuthorize() throws IOException {
		AnonymousFaceletsTag tag = new AnonymousFaceletsTag();
		assertThat(tag.authorize())
			.isTrue();
	}

}
