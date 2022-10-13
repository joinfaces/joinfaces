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

package org.joinfaces.autoconfigure.faces;

import jakarta.faces.application.ProjectStage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectStageAutoConfigurationTest {

	private WebApplicationContextRunner webApplicationContextRunner;

	@BeforeEach
	public void setUp() {
		this.webApplicationContextRunner = new WebApplicationContextRunner()
				.withConfiguration(AutoConfigurations.of(JakartaFaces3AutoConfiguration.class, ProjectStageAutoConfiguration.class));
	}

	@Test
	public void testProductionIsDefault() {
		this.webApplicationContextRunner
				.run(context ->
						assertThat(context.getBean(JakartaFaces3Properties.class).getProjectStage()).isEqualTo(ProjectStage.Production)
				);
	}

	@Test
	public void testDevelopmentWhenDebug() {
		this.webApplicationContextRunner
				.withPropertyValues("debug")
				.run(context ->
						assertThat(context.getBean(JakartaFaces3Properties.class).getProjectStage()).isEqualTo(ProjectStage.Development)
				);
	}

	@Test
	public void testExplicitMappingTakesPrecedence() {
		this.webApplicationContextRunner
				.withPropertyValues("joinfaces.faces.project-stage=UnitTest")
				.run(context ->
						assertThat(context.getBean(JakartaFaces3Properties.class).getProjectStage()).isEqualTo(ProjectStage.UnitTest)
				);
		this.webApplicationContextRunner
				.withPropertyValues("debug", "joinfaces.faces.project-stage=UnitTest")
				.run(context ->
						assertThat(context.getBean(JakartaFaces3Properties.class).getProjectStage()).isEqualTo(ProjectStage.UnitTest)
				);
	}

}
