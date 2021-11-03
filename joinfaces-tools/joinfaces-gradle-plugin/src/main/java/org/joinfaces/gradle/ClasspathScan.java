/*
 * Copyright 2016-2018 the original author or authors.
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

import java.io.IOException;

import lombok.Getter;
import lombok.Setter;
import org.gradle.api.DefaultTask;
import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.tasks.CacheableTask;
import org.gradle.api.tasks.Classpath;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.SkipWhenEmpty;
import org.gradle.api.tasks.TaskAction;
import org.joinfaces.tools.ClasspathScanner;

/**
 * {@link org.gradle.api.Task Gradle task} which performs a classpath scan
 * using {@link ClasspathScanner}.
 *
 * @author Lars Grefer
 */
@Getter
@Setter
@CacheableTask
public class ClasspathScan extends DefaultTask {

	@Classpath
	@InputFiles
	@SkipWhenEmpty
	private final ConfigurableFileCollection classpath = getProject().files();

	@OutputDirectory
	private final DirectoryProperty destinationDir = getProject().getObjects().directoryProperty();

	@TaskAction
	public void scanClasses() throws IOException {
		ClasspathScanner.builder()
				.classpathRoot(getDestinationDir().getAsFile().get())
				.classGraphConfigurer(classGraph -> classGraph.overrideClasspath(getClasspath()))
				.build()
				.scanClasses();
	}

}
