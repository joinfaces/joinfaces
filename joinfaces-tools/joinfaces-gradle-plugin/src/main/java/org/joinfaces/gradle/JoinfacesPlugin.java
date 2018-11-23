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
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.jar.Attributes;
import java.util.jar.JarFile;

import javax.annotation.Nullable;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * {@link Plugin Gradle plugin} for JoinFaces projects.
 *
 * @author Lars Grefer
 */
public class JoinfacesPlugin implements Plugin<Project> {

	private static final String JOINFACES_VERSION = determineJoinfacesVersion();

	/**
	 * The coordinates {@code (group:name:version)} of the {@code joinfaces-dependencies} bom.
	 */
	public static final String BOM_COORDINATES = "org.joinfaces:joinfaces-dependencies:" + JOINFACES_VERSION;

	@Override
	public void apply(Project project) {
		project.getPlugins().apply(ClasspathScanPlugin.class);

		project.getPlugins().withId("io.spring.dependency-management", plugin ->
				project.getPlugins().apply(BomPlugin.class)
		);
	}

	@Nullable
	private static String determineJoinfacesVersion() {
		String implementationVersion = BomPlugin.class.getPackage().getImplementationVersion();
		if (implementationVersion != null) {
			return implementationVersion;
		}
		URL codeSourceLocation = BomPlugin.class.getProtectionDomain().getCodeSource().getLocation();
		try {
			URLConnection connection = codeSourceLocation.openConnection();
			if (connection instanceof JarURLConnection) {
				return getImplementationVersion(((JarURLConnection) connection).getJarFile());
			}
			try (JarFile jarFile = new JarFile(new File(codeSourceLocation.toURI()))) {
				return getImplementationVersion(jarFile);
			}
		}
		catch (IOException | URISyntaxException ex) {
			return null;
		}
	}

	private static String getImplementationVersion(JarFile jarFile) throws IOException {
		return jarFile.getManifest().getMainAttributes().getValue(Attributes.Name.IMPLEMENTATION_VERSION);
	}
}
