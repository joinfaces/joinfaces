/*
 * Copyright 2016-2019 the original author or authors.
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
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Map;

import io.github.classgraph.ScanResult;

/**
 * Abstract base class for classpath scans done at build time.
 * <p>
 * The actual classpath scan is only done once by {@link ClasspathScanner}.
 * The {@link ScanResult} is then passed to implementations of this class,
 * which can look for classes of their interest and can produce corresponding
 * result files to be read at runtime.
 *
 * @author Lars Grefer
 * @see ClasspathScanner
 */
public abstract class ScanResultHandler {

	/**
	 * Handles the {@link ScanResult} produced by {@link ClasspathScanner}.
	 *
	 * @param scanResult       The result of the classpath scan.
	 * @param classpathRoot The base dir for result files. This is usually a classpath root directory.
	 * @throws IOException when the result file(s) could not be written.
	 */
	public abstract void handle(ScanResult scanResult, File classpathRoot) throws IOException;

	/**
	 * Helper method which writes a list of class names to the given file.
	 *
	 * @param file       The target file in which the class names should be written.
	 * @param classNames The class names which should be written in the target file.
	 * @throws IOException when the class names could not be written to the target file.
	 */
	protected void writeClassList(File file, Collection<String> classNames) throws IOException {
		File baseDir = file.getParentFile();

		if (baseDir.isDirectory() || baseDir.mkdirs()) {
			Files.write(file.toPath(), classNames, StandardCharsets.UTF_8);
		}
		else {
			throw new IOException(baseDir + " does not exist and could not be created");
		}
	}

	/**
	 * Helper method which writes a map of class names to the given file.
	 *
	 * @param file     The target file in which the class names should be written.
	 * @param classMap The class names which should be written in the target file.
	 * @throws IOException when the class names could not be written to the target file.
	 */
	protected void writeClassMap(File file, Map<String, ? extends Collection<String>> classMap) throws IOException {
		File baseDir = file.getParentFile();

		if (baseDir.isDirectory() || baseDir.mkdirs()) {
			//noinspection CharsetObjectCanBeUsed
			try (PrintWriter printWriter = new PrintWriter(file, "UTF-8")) {
				classMap.forEach((key, value) -> {
					printWriter.print(key);
					printWriter.print("=");
					printWriter.println(String.join(",", value));
				});
			}
		}
		else {
			throw new IOException(baseDir + " does not exist and could not be created");
		}

	}
}
