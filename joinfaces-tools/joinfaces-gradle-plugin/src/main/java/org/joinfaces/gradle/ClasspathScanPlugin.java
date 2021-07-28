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

import java.util.Collections;
import java.util.concurrent.Callable;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.file.FileCollection;
import org.gradle.api.plugins.GroovyPlugin;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.JavaPluginExtension;
import org.gradle.api.plugins.scala.ScalaBasePlugin;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.TaskProvider;

/**
 * {@link Plugin Gradle plugin} for classpath scanning at build-time.
 *
 * @author Lars Grefer
 */
public class ClasspathScanPlugin implements Plugin<Project> {

	private Project project;

	@Override
	@SuppressFBWarnings("EI_EXPOSE_REP2")
	public void apply(Project project) {
		this.project = project;

		project.getPlugins().withType(JavaPlugin.class, javaPlugin ->
			project.getExtensions()
				.getByType(JavaPluginExtension.class)
				.getSourceSets()
				.all(this::configureClasspathScan)
		);
	}

	private void configureClasspathScan(SourceSet sourceSet) {
		String taskName = sourceSet.getTaskName("scanJoinfaces", "Classpath");

		TaskProvider<ClasspathScan> scanTaskProvider = this.project.getTasks().register(taskName, ClasspathScan.class, scanTask -> {
			scanTask.getDestinationDir().set(this.project.getLayout().getBuildDirectory().dir("joinfaces/" + sourceSet.getName()));

			scanTask.getClasspath().from((Callable<FileCollection>) () -> this.project.getConfigurations().getByName(sourceSet.getRuntimeClasspathConfigurationName()));
			scanTask.getClasspath().from(this.project.getTasks().named(sourceSet.getCompileJavaTaskName()));

		});

		this.project.getPlugins().withType(GroovyPlugin.class, groovyPlugin ->
			scanTaskProvider.configure(scanTask ->
				scanTask.getClasspath().from(this.project.getTasks().named(sourceSet.getCompileTaskName("groovy")))
			)
		);
		this.project.getPlugins().withType(ScalaBasePlugin.class, scalaBasePlugin ->
			scanTaskProvider.configure(scanTask ->
				scanTask.getClasspath().from(this.project.getTasks().named(sourceSet.getCompileTaskName("scala")))
			)
		);
		this.project.getPlugins().withId("kotlin", kotlinPlugin ->
			scanTaskProvider.configure(scanTask ->
				scanTask.getClasspath().from(this.project.getTasks().named(sourceSet.getCompileTaskName("kotlin")))
			)
		);
		this.project.getPlugins().withId("org.jetbrains.kotlin.jvm", kotlinPlugin ->
			scanTaskProvider.configure(scanTask ->
				scanTask.getClasspath().from(this.project.getTasks().named(sourceSet.getCompileTaskName("kotlin")))
			)
		);

		sourceSet.getOutput().dir(Collections.singletonMap("builtBy", scanTaskProvider), scanTaskProvider.flatMap(ClasspathScan::getDestinationDir));
	}
}
