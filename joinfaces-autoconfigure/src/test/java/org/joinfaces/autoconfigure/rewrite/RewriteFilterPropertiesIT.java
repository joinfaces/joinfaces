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

package org.joinfaces.autoconfigure.rewrite;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(
		properties = "spring.profiles.active=propertyTest",
		webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
public class RewriteFilterPropertiesIT {

	@Autowired
	private RewriteFilterProperties rewriteFilterProperties;

	@Test
	public void testOrder() {
		assertThat(this.rewriteFilterProperties.getOrder())
			.isEqualTo(1);
	}

	@Test
	public void testDispatcherTypes() {
		assertThat(this.rewriteFilterProperties.getDispatcherTypes())
			.isEqualTo(EnumSet.allOf(DispatcherType.class));
	}

	@Test
	public void testUrlPatterns() {
		assertThat(this.rewriteFilterProperties.getUrlPatterns())
			.containsExactly("/foo", "/base");
	}

	@Test
	public void testAsyncSupported() {
		assertThat(this.rewriteFilterProperties.isAsyncSupported())
			.isTrue();
	}

	@Test
	public void testEnabled() {
		assertThat(this.rewriteFilterProperties.isEnabled())
			.isTrue();
	}
}
