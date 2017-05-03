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

import javax.faces.application.ProjectStage;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;

/**
 * Auto configuration of JSF ProjectStage.
 * @author Lars Grefer
 */
@Slf4j
@ConditionalOnWebApplication
@ConditionalOnClass(ProjectStage.class)
@AutoConfigureBefore(JavaxFacesSpringBootAutoConfiguration.class)
public class ProjectStageAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty("debug")
	public ProjectStageCustomizer developmentProjectStageCustomizer() {
		return new ProjectStageCustomizer(ProjectStage.Development);
	}

	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty(value = "debug", havingValue = "false", matchIfMissing = true)
	public ProjectStageCustomizer productionProjectStageCustomizer() {
		return new ProjectStageCustomizer(ProjectStage.Production);
	}

}
