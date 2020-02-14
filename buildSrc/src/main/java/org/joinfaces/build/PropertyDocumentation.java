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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.codehaus.groovy.runtime.ResourceGroovyMethods;
import org.gradle.api.DefaultTask;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.tasks.InputFile;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.TaskAction;

import org.springframework.boot.configurationmetadata.ConfigurationMetadataGroup;
import org.springframework.boot.configurationmetadata.ConfigurationMetadataProperty;
import org.springframework.boot.configurationmetadata.ConfigurationMetadataRepository;
import org.springframework.boot.configurationmetadata.ConfigurationMetadataRepositoryJsonBuilder;
import org.springframework.boot.configurationmetadata.ConfigurationMetadataSource;

/**
 * @author Lars Grefer
 */
public class PropertyDocumentation extends DefaultTask {

	@InputFile
	private final RegularFileProperty inputFile = getProject().getObjects().fileProperty();

	@OutputFile
	private final RegularFileProperty outputFile = getProject().getObjects().fileProperty();

	@TaskAction
	public void generatePropertyDocumentation() throws IOException {
		ConfigurationMetadataRepository configurationMetadataRepository;

		configurationMetadataRepository = ConfigurationMetadataRepositoryJsonBuilder.create()
				.withJsonResource(new FileInputStream(getInputFile().getAsFile().get()))
				.build();

		try (PrintWriter writer = ResourceGroovyMethods.newPrintWriter(getOutputFile().getAsFile().get(), "UTF-8")) {

			writer.println("[source,properties,indent=0,subs=\"verbatim,attributes,macros\"]");
			writer.println("----");

			configurationMetadataRepository.getAllGroups().values().stream()
					.sorted(Comparator.comparing(ConfigurationMetadataGroup::getId))
					.forEach(group -> {
						writer.printf("## %s\n", group.getId());

						group.getSources().values()
								.stream()
								.map(ConfigurationMetadataSource::getShortDescription)
								.filter(s -> s != null && !s.isEmpty())
								.forEach(d -> writer.printf("# %s\n", d));

						group.getProperties().values().stream()
								.sorted(Comparator.comparing(ConfigurationMetadataProperty::getId))
								.forEach(property -> printProperty(writer, property));
						writer.println();
						writer.flush();
					});

			writer.println("----");
		}
	}

	private void printProperty(PrintWriter writer, ConfigurationMetadataProperty property) {
		writer.print(property.getId());
		writer.print('=');

		Object defaultValue = property.getDefaultValue();
		if (defaultValue != null) {
			if (defaultValue.getClass().isArray()) {
				defaultValue = Arrays.stream((Object[]) defaultValue)
						.map(String::valueOf)
						.collect(Collectors.joining(","));
			}

			writer.print(defaultValue);
		}

		if (property.getShortDescription() != null) {
			writer.print(" # ");
			writer.print(property.getShortDescription());
		}

		writer.println();
	}

	public RegularFileProperty getInputFile() {
		return this.inputFile;
	}

	public RegularFileProperty getOutputFile() {
		return this.outputFile;
	}
}
