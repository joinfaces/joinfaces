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

package org.joinfaces.autoconfigure.rewrite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;

import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

public class RewriteAutoConfigurationTest {

	private WebApplicationContextRunner webApplicationContextRunner;

	@BeforeEach
	public void setUp() {
		this.webApplicationContextRunner = new WebApplicationContextRunner()
				.withConfiguration(AutoConfigurations.of(RewriteAutoConfiguration.class, FooConfiguration.class));
	}

	@Test
	public void testConfigurationProvider() {
		this.webApplicationContextRunner
				.run(context -> {
					Foo1ConfigurationProvider fooConfigurationProvider = (Foo1ConfigurationProvider) context.getBean("foo1ConfigurationProvider");

					assertThat(fooConfigurationProvider)
						.isNotNull();

					SpringBootBeanNameResolver beanNameResolver = new SpringBootBeanNameResolver();
					assertThat(beanNameResolver.getBeanName(HttpConfigurationProvider.class))
						.isNull();
					assertThat(beanNameResolver.getBeanName(Foo1ConfigurationProvider.class))
						.isEqualTo("foo1ConfigurationProvider");
					assertThat(beanNameResolver.getBeanName(Foo2ConfigurationProvider.class))
						.isEqualTo("foo2ConfigurationProvider");
					assertThat(beanNameResolver.getBeanName(Foo3ConfigurationProvider.class))
						.isNull();

					SpringBootServiceLocator serviceLocator = new SpringBootServiceLocator();
					assertThat(serviceLocator.locate(HttpConfigurationProvider.class))
						.hasSize(2);
					assertThat(serviceLocator.locate(Foo1ConfigurationProvider.class))
						.hasSize(1);
					assertThat(serviceLocator.locate(Foo2ConfigurationProvider.class))
						.hasSize(1);
					assertThat(serviceLocator.locate(Foo3ConfigurationProvider.class))
						.isEmpty();
				});
	}
}
