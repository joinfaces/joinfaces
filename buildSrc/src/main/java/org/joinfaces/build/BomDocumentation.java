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

package org.joinfaces.build;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.codehaus.groovy.runtime.ResourceGroovyMethods;
import org.gradle.api.DefaultTask;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.tasks.InputFile;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.TaskAction;

public class BomDocumentation extends DefaultTask {

	@OutputFile
	private final RegularFileProperty outputFile = getProject().getObjects().fileProperty();

	@InputFile
	private final RegularFileProperty inputFile = getProject().getObjects().fileProperty();

	@TaskAction
	public void generateBomDocumentation() throws IOException, XmlPullParserException {
		MavenXpp3Reader reader = new MavenXpp3Reader();
		Model model = reader.read(new FileReader(inputFile.getAsFile().get()));



		try (PrintWriter writer = ResourceGroovyMethods.newPrintWriter(getOutputFile().getAsFile().get(), "UTF-8")) {

			writer.println("|===");
			writer.println("|Group ID |Artifact ID |Version");

			model.getDependencyManagement()
					.getDependencies()
					.stream()
					.sorted(Comparator.comparing(Dependency::getGroupId).thenComparing(Dependency::getArtifactId))
					.forEach(dependency -> {

						writer.println();
						writer.printf("|`%s`\n", dependency.getGroupId());
						writer.printf("|`%s`\n", dependency.getArtifactId());
						writer.printf("|%s\n", dependency.getVersion());
					});

			writer.println("|===");
		}

	}

	public RegularFileProperty getOutputFile() {
		return this.outputFile;
	}

	public RegularFileProperty getInputFile() {
		return this.inputFile;
	}
}
