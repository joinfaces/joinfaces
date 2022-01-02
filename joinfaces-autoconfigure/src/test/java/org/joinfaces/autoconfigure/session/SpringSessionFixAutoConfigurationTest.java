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

package org.joinfaces.autoconfigure.session;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.SessionRepositoryFilter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.mock;

class SpringSessionFixAutoConfigurationTest {

	private WebApplicationContextRunner contextRunner;

	@BeforeEach
	void setUp() {
		this.contextRunner = new WebApplicationContextRunner();
	}

	@Test
	void noSessionRepository() {
		this.contextRunner.withConfiguration(AutoConfigurations.of(SpringSessionFixAutoConfiguration.class))
			.run(context -> {
				assertThat(context).doesNotHaveBean("springSessionFixFilterRegistrationBean");
			});
	}

	@Test
	void withSessionRepository() {
		this.contextRunner.withConfiguration(AutoConfigurations.of(SpringSessionFixAutoConfiguration.class))
			.withUserConfiguration(Config.class)
			.run(context -> {
				assertThat(context).hasBean("springSessionFixFilterRegistrationBean");
			});
	}

	@Configuration
	public static class Config {

		@Bean
		public SessionRepositoryFilter<?> sessionRepositoryFilter() {
			return mock(SessionRepositoryFilter.class);
		}
	}
}
