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

package org.joinfaces.tools;

import java.io.File;
import java.io.IOException;
import java.util.ServiceLoader;
import java.util.function.Function;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link ClassGraph}-based classpath scanner highly tailored for JoinFaces.
 *
 * @author Lars Grefer
 * @see ScanResultHandler
 */
@Slf4j
@Data
@Builder
public class ClasspathScanner {

	private final File classpathRoot;
	private final Function<ClassGraph, ClassGraph> classGraphConfigurer;

	@SuppressFBWarnings(value = "RCN_REDUNDANT_NULLCHECK_WOULD_HAVE_BEEN_A_NPE", justification = "https://github.com/spotbugs/spotbugs/issues/756")
	public void scanClasses() throws IOException {

		ClassGraph classGraph = new ClassGraph()
				.enableAllInfo()
				.enableExternalClasses();

		classGraph = getClassGraphConfigurer().apply(classGraph);

		try (ScanResult scanResult = classGraph.scan()) {
			for (ScanResultHandler scanResultHandler : ServiceLoader.load(ScanResultHandler.class)) {
				scanResultHandler.handle(scanResult, getClasspathRoot());
			}
		}
	}
}
