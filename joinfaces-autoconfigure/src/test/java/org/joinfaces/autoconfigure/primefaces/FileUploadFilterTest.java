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

package org.joinfaces.autoconfigure.primefaces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

public class FileUploadFilterTest {

	private WebApplicationContextRunner webApplicationContextRunner;

	@BeforeEach
	public void setUp() {
		this.webApplicationContextRunner = new WebApplicationContextRunner()
				.withConfiguration(AutoConfigurations.of(PrimefacesFileUploadServletContextAutoConfiguration.class, MultipartAutoConfiguration.class));
	}

	@Test
	public void testAddedWhenCommons() {
		this.webApplicationContextRunner
				.withPropertyValues("joinfaces.primefaces.uploader=commons")
				.run(context -> {
					assertThat(context).hasBean("primefacesFileUploadFilterRegistrationBean");
				});
	}

	@Test
	public void testNotAdded() {
		this.webApplicationContextRunner
				.run(context -> {
					assertThat(context).doesNotHaveBean("primefacesFileUploadFilterRegistrationBean");
				});
	}
}
