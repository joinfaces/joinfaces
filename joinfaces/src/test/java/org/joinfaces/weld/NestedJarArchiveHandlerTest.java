/*
 * Copyright 2016-2026 the original author or authors.
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

package org.joinfaces.weld;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

import org.jboss.weld.environment.deployment.discovery.BeanArchiveBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.assertj.core.api.Assertions.assertThat;

class NestedJarArchiveHandlerTest {

	private static final String MARKER_CLASS = "org.example.TestBean";

	@TempDir
	Path tempDir;

	@Test
	void handleNestedJarInPathWithSpaceAndPlus() throws IOException {
		Path archiveDirectory = Files.createDirectory(this.tempDir.resolve("Program Files+plus"));
		Path outerJar = archiveDirectory.resolve("app.jar");
		writeOuterJar(outerJar, createNestedJar());

		String beanArchiveReference = "jar:nested:%s/!BOOT-INF/lib/test.jar!/META-INF/beans.xml"
			.formatted(outerJar.toUri().getRawPath());

		BeanArchiveBuilder builder = new NestedJarArchiveHandler().handle(beanArchiveReference);

		assertThat(beanArchiveReference).contains("Program%20Files+plus");
		assertThat(builder).isNotNull();
		assertThat(builder.getClasses()).contains(MARKER_CLASS);
	}

	private static byte[] createNestedJar() throws IOException {
		try (ByteArrayOutputStream output = new ByteArrayOutputStream();
			 JarOutputStream jar = new JarOutputStream(output)) {

			jar.putNextEntry(new JarEntry("META-INF/beans.xml"));
			jar.write("<beans/>".getBytes(StandardCharsets.UTF_8));
			jar.closeEntry();

			jar.putNextEntry(new JarEntry("org/example/TestBean.class"));
			jar.write(new byte[0]);
			jar.closeEntry();
			return output.toByteArray();
		}
	}

	private static void writeOuterJar(Path outerJar, byte[] nestedJar) throws IOException {
		try (OutputStream output = Files.newOutputStream(outerJar); JarOutputStream jar = new JarOutputStream(output)) {
			jar.putNextEntry(new JarEntry("BOOT-INF/lib/test.jar"));
			jar.write(nestedJar);
			jar.closeEntry();
		}
	}

}
