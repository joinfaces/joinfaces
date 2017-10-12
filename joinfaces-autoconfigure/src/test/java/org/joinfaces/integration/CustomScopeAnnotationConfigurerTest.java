/*
 * Copyright 2016-2017 the original author or authors.
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

package org.joinfaces.integration;


import org.junit.Test;

import org.springframework.core.Ordered;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by larsgrefer on 23.06.17.
 */
public class CustomScopeAnnotationConfigurerTest {

	@Test
	public void test_defaultOrder() {
		assertThat(new CustomScopeAnnotationConfigurer().getOrder()).isEqualTo(Ordered.LOWEST_PRECEDENCE);
	}

	@Test
	public void test_customOrder() {
		CustomScopeAnnotationConfigurer customScopeAnnotationConfigurer = new CustomScopeAnnotationConfigurer();

		customScopeAnnotationConfigurer.setOrder(42);

		assertThat(customScopeAnnotationConfigurer.getOrder()).isEqualTo(42);
	}

	@Test
	public void testDeduceScopeName_nulls() {
		CustomScopeAnnotationConfigurer customScopeAnnotationConfigurer = new CustomScopeAnnotationConfigurer();

		customScopeAnnotationConfigurer.setAnnotationToScopeMappings(null);

		assertThat(customScopeAnnotationConfigurer.deduceScopeName((AnnotationMetadata) null)).isNull();
		assertThat(customScopeAnnotationConfigurer.deduceScopeName((MethodMetadata) null)).isNull();
	}
}
