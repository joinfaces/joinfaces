/*
 * Copyright 2016-2020 the original author or authors.
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

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.UncheckedIOException;
import org.gradle.api.artifacts.Configuration;

import org.springframework.boot.gradle.plugin.SpringBootPlugin;

public class SpringBootVersionsPlugin implements Plugin<Project> {

	@Override
	public void apply(Project project) {
		Configuration springBootBom = project.getConfigurations().create("springBootBom");

		project.getDependencies().add(springBootBom.getName(), SpringBootPlugin.BOM_COORDINATES + "@pom");

		try (FileReader fileReader = new FileReader(springBootBom.getSingleFile())) {
			MavenXpp3Reader reader = new MavenXpp3Reader();

			Model model = reader.read(fileReader);
			project.getExtensions().add("springBootBomProperties", model.getProperties());
		}
		catch (XmlPullParserException | IOException e) {
			throw new UncheckedIOException(e);
		}
	}
}
