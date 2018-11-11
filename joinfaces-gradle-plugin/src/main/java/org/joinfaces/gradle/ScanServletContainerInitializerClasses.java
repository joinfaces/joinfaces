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

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ResourceList;
import io.github.classgraph.ScanResult;
import lombok.Getter;
import lombok.Setter;
import org.gradle.api.DefaultTask;
import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.tasks.Classpath;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.TaskAction;

@Getter
@Setter
public class ScanServletContainerInitializerClasses extends DefaultTask {

	@Classpath
	@InputFiles
	private final ConfigurableFileCollection classpath = getProject().files();

	@OutputFile
	private final RegularFileProperty resultFile = newOutputFile();

	@TaskAction
	public void scanClasses() {

		ClassGraph classGraph = new ClassGraph()
				.enableClassInfo()
				.enableAnnotationInfo()
				.overrideClasspath(classpath);

		try(ScanResult scanResult = classGraph.scan()) {
			ResourceList resourcesWithPath = scanResult.getResourcesWithPath("/META-INF/services/javax.servlet.ServletContainerInitializer");

			resourcesWithPath.forEach(resource -> {
				getLogger().warn(resource.getPath());
			});
		}
	}
}
