/*
 * Copyright 2016-2022 the original author or authors.
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

package org.joinfaces.tools;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class ClasspathScannerTest {

	private ClasspathScanner classpathScanner;
	private File file;

	@BeforeEach
	void setUp() throws IOException {
		this.file = Files.createTempDirectory(getClass().getName()).toFile();
		this.classpathScanner = ClasspathScanner.builder()
				.classGraphConfigurer(c -> c)
				.classpathRoot(this.file)
				.build();
	}

	@AfterEach
	void deleteTempDir() throws IOException {
		Files.walkFileTree(this.file.toPath(), new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				Files.delete(file);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				Files.delete(dir);
				return FileVisitResult.CONTINUE;
			}
		});
		Files.deleteIfExists(this.file.toPath());
	}

	@Test
	void scanClasses_noHandlers() throws IOException {
		this.classpathScanner.scanClasses();
	}

	@Test
	void scanClasses_myfaces_sci() throws IOException {
		this.classpathScanner.scanClasses();

		File classesFile = new File(this.file, "META-INF/joinfaces/org.apache.myfaces.ee.MyFacesContainerInitializer.classes");
		assertThat(classesFile).isFile();

		List<String> classNames = Files.lines(classesFile.toPath(), StandardCharsets.UTF_8)
				.peek(log::debug)
				.collect(Collectors.toList());

		assertThat(classNames).isNotEmpty();
	}

	@Test
	void scanClasses_mojarra_sci() throws IOException {
		this.classpathScanner.scanClasses();

		File classesFile = new File(this.file, "META-INF/joinfaces/com.sun.faces.config.FacesInitializer.classes");
		assertThat(classesFile).isFile();

		List<String> classNames = Files.lines(classesFile.toPath(), StandardCharsets.UTF_8)
				.peek(log::debug)
				.collect(Collectors.toList());

		assertThat(classNames).isNotEmpty();
	}

	@Test
	void scanClasses_myFacesAnnotations() throws IOException {
		this.classpathScanner.scanClasses();

		File classesFile = new File(this.file, "META-INF/joinfaces/org.apache.myfaces.spi.AnnotationProvider.classes");
		assertThat(classesFile).isFile();

		List<String> mapEntries = Files.lines(classesFile.toPath(), StandardCharsets.UTF_8)
				.peek(log::debug)
				.collect(Collectors.toList());

		assertThat(mapEntries).isNotEmpty();

		Pattern pattern = Pattern.compile(".+=.?");
		mapEntries.forEach(mapEntry -> assertThat(mapEntry).matches(pattern));
	}

	@Test
	void scanClasses_rewriteAnnotations() throws IOException {
		this.classpathScanner.scanClasses();

		File classesFile = new File(this.file, "META-INF/joinfaces/" + RewriteAnnotationProviderHandler.REWRITE_ANNOTATION_HANDLER + ".classes");

		assertThat(classesFile).isFile();

		List<String> mapEntries = Files.lines(classesFile.toPath(), StandardCharsets.UTF_8)
				.peek(log::debug)
				.collect(Collectors.toList());

		assertThat(mapEntries.size()).isEqualTo(1);
		assertThat(mapEntries.get(0)).isEqualTo(AnnotatedComponent.class.getName());
	}
}
