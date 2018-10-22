/*
 * Copyright 2016-2018 the original author or authors.
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

package org.joinfaces.autoconfigure.jpa;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.FilteredClassLoader;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;

import static org.assertj.core.api.Assertions.assertThat;

public class JpaWebAutoConfigurationTest {

	private WebApplicationContextRunner webApplicationContextRunner;

	@BeforeEach
	public void setUp() {
		this.webApplicationContextRunner = new WebApplicationContextRunner()
				.withConfiguration(AutoConfigurations.of(JpaWebAutoConfiguration.class, HibernateJpaAutoConfiguration.class));
	}

	@Test
	public void testInterceptorReplacedByFilter() {
		this.webApplicationContextRunner.run(context -> {
			assertThat(context).doesNotHaveBean(OpenEntityManagerInViewInterceptor.class);
			assertThat(context).hasSingleBean(OpenEntityManagerInViewFilter.class);
		});
	}

	@Test
	public void testDisabledByProperty() {
		this.webApplicationContextRunner
				.withPropertyValues("spring.jpa.open-in-view=false")
				.run(context -> {
			assertThat(context).doesNotHaveBean(OpenEntityManagerInViewInterceptor.class);
			assertThat(context).doesNotHaveBean(OpenEntityManagerInViewFilter.class);
		});
	}

	@Test
	public void testDisabledByMissingEntityManagerClass() {
		this.webApplicationContextRunner
				.withClassLoader(new FilteredClassLoader(EntityManager.class))
				.run(context -> {
			assertThat(context).doesNotHaveBean(OpenEntityManagerInViewInterceptor.class);
			assertThat(context).doesNotHaveBean(OpenEntityManagerInViewFilter.class);
		});
	}
}
