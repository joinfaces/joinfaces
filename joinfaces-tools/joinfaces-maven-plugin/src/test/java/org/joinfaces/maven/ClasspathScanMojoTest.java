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

package org.joinfaces.maven;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ClasspathScanMojoTest {

	private void setSkip(ClasspathScanMojo mojo, boolean skip) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Field mojoField = mojo.getClass().getDeclaredField("skip");
		mojoField.setAccessible(true);
		mojoField.setBoolean(mojo, skip);
	}

	private File setOutputDirectory(ClasspathScanMojo mojo) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, IOException {
		File outputDirectory = Files.createTempDirectory("classpathScan").toFile();
		outputDirectory.deleteOnExit();
		Field outputDirectoryField = mojo.getClass().getDeclaredField("outputDirectory");
		outputDirectoryField.setAccessible(true);
		outputDirectoryField.set(mojo, outputDirectory);

		return outputDirectory;
	}

	@Test
	void apply_skipTrue() throws IllegalAccessException, NoSuchFieldException, IOException, MojoExecutionException, MojoFailureException {
		ClasspathScanMojo mojo = new ClasspathScanMojo();

		setSkip(mojo, true);
		File outputDirectory = setOutputDirectory(mojo);

		mojo.execute();

		File metaInfFolder = new File(outputDirectory, "META-INF");

		assertThat(metaInfFolder.exists())
			.isEqualTo(Boolean.FALSE);
	}
}
