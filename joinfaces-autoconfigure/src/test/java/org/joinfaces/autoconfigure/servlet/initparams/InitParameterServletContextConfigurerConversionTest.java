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

package org.joinfaces.autoconfigure.servlet.initparams;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import jakarta.faces.application.ProjectStage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Lars Grefer
 */
public class InitParameterServletContextConfigurerConversionTest {

	@Test
	public void convertToString_String() {
		assertThat(InitParameterServletContextConfigurer.convertToString("hello"))
				.isEqualTo("hello");
	}

	@Test
	public void convertToString_Boolean() {
		assertThat(InitParameterServletContextConfigurer.convertToString(true))
				.isEqualTo("true");
	}

	@Test
	public void convertToString_Enum() {
		assertThat(InitParameterServletContextConfigurer.convertToString(ProjectStage.UnitTest))
				.isEqualTo("UnitTest");
	}

	@Test
	public void convertToString_Class() {
		assertThat(InitParameterServletContextConfigurer.convertToString(Void.class))
				.isEqualTo("java.lang.Void");
	}

	@Test
	public void convertToString_Duration() {

		Duration duration = Duration.ofHours(1);

		assertThat(InitParameterServletContextConfigurer.convertToString(duration, ChronoUnit.HOURS))
				.isEqualTo("1");
		assertThat(InitParameterServletContextConfigurer.convertToString(duration, ChronoUnit.MINUTES))
				.isEqualTo("60");
		assertThat(InitParameterServletContextConfigurer.convertToString(duration, ChronoUnit.SECONDS))
				.isEqualTo("3600");
		assertThat(InitParameterServletContextConfigurer.convertToString(duration, ChronoUnit.MILLIS))
				.isEqualTo("3600000");
		assertThat(InitParameterServletContextConfigurer.convertToString(duration, ChronoUnit.NANOS))
				.isEqualTo("3600000000000");
	}

	@Test
	public void convertToString_Duration_Invalid() {
		assertThat(Assertions.assertThrows(RuntimeException.class, () ->
				InitParameterServletContextConfigurer.convertToString(Duration.ZERO, ChronoUnit.YEARS))
		).isNotNull();
	}
}
