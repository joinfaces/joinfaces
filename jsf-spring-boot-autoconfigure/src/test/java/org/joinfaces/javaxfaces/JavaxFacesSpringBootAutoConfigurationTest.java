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

package org.joinfaces.javaxfaces;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author Lars Grefer
 */
public class JavaxFacesSpringBootAutoConfigurationTest {

	@Test
	public void test2_0() {
		JavaxFacesSpringBootAutoConfiguration.JavaxFaces2_0AutoConfiguration javaxFaces2_0AutoConfiguration = new JavaxFacesSpringBootAutoConfiguration.JavaxFaces2_0AutoConfiguration();
		assertThat(javaxFaces2_0AutoConfiguration).isNotNull();
	}

	@Test
	public void test2_1() {
		JavaxFacesSpringBootAutoConfiguration.JavaxFaces2_1AutoConfiguration javaxFaces2_1AutoConfiguration = new JavaxFacesSpringBootAutoConfiguration.JavaxFaces2_1AutoConfiguration();
		assertThat(javaxFaces2_1AutoConfiguration).isNotNull();
	}

	@Test
	public void test2_2() {
		JavaxFacesSpringBootAutoConfiguration.JavaxFaces2_2AutoConfiguration javaxFaces2_2AutoConfiguration = new JavaxFacesSpringBootAutoConfiguration.JavaxFaces2_2AutoConfiguration();
		assertThat(javaxFaces2_2AutoConfiguration).isNotNull();
	}
}
