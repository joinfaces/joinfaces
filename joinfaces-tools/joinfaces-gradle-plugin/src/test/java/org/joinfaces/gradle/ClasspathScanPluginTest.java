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

import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ClasspathScanPluginTest {

	private Project project;

	@BeforeEach
	void setUp() {
		this.project = ProjectBuilder.builder()
				.build();
	}

	@Test
	void apply_alone() {
		this.project.getPlugins().apply(ClasspathScanPlugin.class);
	}

	@Test
	void apply_beforeJava() {
		this.project.getPlugins().apply(ClasspathScanPlugin.class);
		this.project.getPlugins().apply(JavaPlugin.class);

		checkForGeneratedTasks();
	}

	@Test
	void apply_afterJava() {
		this.project.getPlugins().apply(JavaPlugin.class);
		this.project.getPlugins().apply(ClasspathScanPlugin.class);

		checkForGeneratedTasks();
	}

	private void checkForGeneratedTasks() {
		Task scanClasspath = this.project.getTasks().getByName("scanJoinfacesClasspath");
		Task scanTestClasspath = this.project.getTasks().getByName("scanJoinfacesTestClasspath");

		assertThat(scanClasspath).isNotNull();
		assertThat(scanClasspath).isInstanceOf(ClasspathScan.class);
		assertThat(scanTestClasspath).isNotNull();
		assertThat(scanTestClasspath).isInstanceOf(ClasspathScan.class);
	}
}
