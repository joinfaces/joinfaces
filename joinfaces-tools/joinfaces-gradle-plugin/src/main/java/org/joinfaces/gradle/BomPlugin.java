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

package org.joinfaces.gradle;

import io.spring.gradle.dependencymanagement.DependencyManagementPlugin;
import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * {@link Plugin Gradle plugin} which applies the {@link DependencyManagementPlugin} and
 * {@link io.spring.gradle.dependencymanagement.dsl.ImportsHandler#mavenBom(String) imports}
 * the {@code joinfaces-dependencies} bom.
 *
 * @author Lars Grefer
 * @see org.springframework.boot.gradle.plugin.DependencyManagementPluginAction
 */
public class BomPlugin implements Plugin<Project> {
	@Override
	public void apply(Project project) {

		project.getPlugins().apply(DependencyManagementPlugin.class);

		DependencyManagementExtension dependencyManagement = project.getExtensions().getByType(DependencyManagementExtension.class);

		dependencyManagement.imports(importsHandler -> importsHandler.mavenBom(
				JoinfacesPlugin.BOM_COORDINATES
		));
	}
}
