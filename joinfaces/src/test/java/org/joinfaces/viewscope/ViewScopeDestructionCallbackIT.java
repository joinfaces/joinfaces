/*
 * Copyright 2016-2021 the original author or authors.
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

package org.joinfaces.viewscope;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.annotation.PreDestroy;

import lombok.Getter;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.Nullable;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ViewScopeDestructionCallbackIT {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	@Qualifier("testScopeConfigurer")
	CustomScopeConfigurer testScopeConfigurer;

	@Test
	public void testExpectedSpringBehaviour() throws NoSuchFieldException, IllegalAccessException {
		Field scopesField = CustomScopeConfigurer.class.getDeclaredField("scopes");
		scopesField.setAccessible(true);
		Map<String, org.springframework.beans.factory.config.Scope> scopes = (Map<String, org.springframework.beans.factory.config.Scope>) scopesField.get(this.testScopeConfigurer);
		TestConfig.TestScope testScope = (TestConfig.TestScope) scopes.get("test");

		BeanWithTwoDestructionCallbacks bean = this.applicationContext.getBean(BeanWithTwoDestructionCallbacks.class);

		assertThat(bean.destroyCalled).isFalse();
		assertThat(bean.preDestroyCalled).isFalse();

		assertThat(testScope.getBeans()).hasSize(1);
		assertThat(testScope.getCallbacks()).hasSize(1);

		//Only one destruction callback per bean
		List<Runnable> destructionCallbacks = testScope.getCallbacks().get("beanWithTwoDestructionCallbacks");
		assertThat(destructionCallbacks).hasSize(1);

		//The one destruction callback calls all destroy-methods
		destructionCallbacks.get(0).run();
		assertThat(bean.preDestroyCalled).isTrue();
		assertThat(bean.destroyCalled).isTrue();
	}


	@TestConfiguration
	public static class TestConfig {

		@Bean
		public static CustomScopeConfigurer testScopeConfigurer() {
			CustomScopeConfigurer customScopeConfigurer = new CustomScopeConfigurer();

			customScopeConfigurer.addScope("test", new TestScope());

			return customScopeConfigurer;
		}

		@Bean
		@Scope("test")
		public BeanWithTwoDestructionCallbacks beanWithTwoDestructionCallbacks() {
			return new BeanWithTwoDestructionCallbacks();
		}


		@Getter
		public static class TestScope implements org.springframework.beans.factory.config.Scope {

			Map<String, Object> beans = new HashMap<>();
			Map<String, List<Runnable>> callbacks = new HashMap<>();

			@Override
			public Object get(String name, ObjectFactory<?> objectFactory) {
				return this.beans.computeIfAbsent(name, k -> objectFactory.getObject());
			}

			@Override
			public Object remove(String name) {
				return this.beans.remove(name);
			}

			@Override
			public void registerDestructionCallback(String name, Runnable callback) {
				this.callbacks.computeIfAbsent(name, k -> new ArrayList<>()).add(callback);
			}

			@Override
			@Nullable
			public Object resolveContextualObject(String key) {
				return null;
			}

			@Override
			@Nullable
			public String getConversationId() {
				return null;
			}
		}
	}

	public static class BeanWithTwoDestructionCallbacks implements DisposableBean {

		boolean destroyCalled;
		boolean preDestroyCalled;

		@Override
		public void destroy() {
			this.destroyCalled = true;
		}

		@PreDestroy
		public void preDestroy() {
			this.preDestroyCalled = true;
		}
	}
}
