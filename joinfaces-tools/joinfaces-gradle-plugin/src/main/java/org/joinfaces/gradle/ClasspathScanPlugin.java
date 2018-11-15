/*
 * Copyright 2016-2018 the original author or authors.
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

package org.joinfaces.gradle;

import java.io.File;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.file.FileCollection;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.tasks.SourceSet;

/**
 * {@link Plugin Gradle plugin} for classpath scanning at build-time.
 *
 * @author Lars Grefer
 */
public class ClasspathScanPlugin implements Plugin<Project> {

	private Project project;

	@Override
	public void apply(Project project) {
		this.project = project;

		project.getPlugins().withType(JavaPlugin.class, javaPlugin ->
				project.getConvention()
						.getPlugin(JavaPluginConvention.class)
						.getSourceSets()
						.all(this::configureClasspathScan)
		);
	}

	private void configureClasspathScan(SourceSet sourceSet) {
		String taskName = sourceSet.getTaskName("scan", "Classpath");
		File baseDir = new File(this.project.getBuildDir(), "joinfaces/" + sourceSet.getName());

		ClasspathScan scanTask = this.project.getTasks().create(taskName, ClasspathScan.class);
		scanTask.getDestinationDir().set(baseDir);

		this.project.afterEvaluate(p -> {
			FileCollection runtimeClasspath = sourceSet.getRuntimeClasspath();
			scanTask.getClasspath().from(runtimeClasspath);
			sourceSet.setRuntimeClasspath(runtimeClasspath.plus(this.project.files(scanTask)));
		});
	}
}
