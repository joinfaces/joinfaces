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

package org.joinfaces.autoconfigure.javaxfaces;

import javax.faces.application.ProjectStage;

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
				.withConfiguration(AutoConfigurations.of(JavaxFacesAutoConfiguration.class, ProjectStageAutoConfiguration.class));
	}

	@Test
	public void testProductionIsDefault() {
		this.webApplicationContextRunner
				.run(context ->
						assertThat(context.getBean(JavaxFaces2_0Properties.class).getProjectStage()).isEqualTo(ProjectStage.Production)
				);
	}

	@Test
	public void testDevelopmentWhenDebug() {
		this.webApplicationContextRunner
				.withPropertyValues("debug")
				.run(context ->
						assertThat(context.getBean(JavaxFaces2_0Properties.class).getProjectStage()).isEqualTo(ProjectStage.Development)
				);
	}

	@Test
	public void testExplicitMappingTakesPrecedence() {
		this.webApplicationContextRunner
				.withPropertyValues("jsf.project-stage=UnitTest")
				.run(context ->
						assertThat(context.getBean(JavaxFaces2_0Properties.class).getProjectStage()).isEqualTo(ProjectStage.UnitTest)
				);
		this.webApplicationContextRunner
				.withPropertyValues("debug", "jsf.project-stage=UnitTest")
				.run(context ->
						assertThat(context.getBean(JavaxFaces2_0Properties.class).getProjectStage()).isEqualTo(ProjectStage.UnitTest)
				);
	}

}
