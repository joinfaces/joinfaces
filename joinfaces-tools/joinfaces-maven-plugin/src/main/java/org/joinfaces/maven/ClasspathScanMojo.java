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

package org.joinfaces.maven;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.joinfaces.tools.ClasspathScanner;

/**
 * {@link org.apache.maven.plugin.Mojo Mojo} which performs a classpath scan
 * using {@link ClasspathScanner}.
 *
 * @author Lars Grefer
 */
@Mojo(
		name = "classpath-scan",
		defaultPhase = LifecyclePhase.PROCESS_CLASSES,
		requiresDependencyCollection = ResolutionScope.COMPILE_PLUS_RUNTIME,
		threadSafe = true
)
public class ClasspathScanMojo extends AbstractMojo {

	@Parameter(defaultValue = "${project}", readonly = true, required = true)
	private MavenProject project;

	@Parameter(defaultValue = "${project.build.outputDirectory}")
	private File outputDirectory;

	@Parameter(property = "joinfaces.skip", defaultValue = "false")
	private boolean skip;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		if (this.skip) {
			skipMojo();
		}
		else {
			executeMojo();
		}
	}

	private void skipMojo() {
		getLog().info("Skipping JoinFaces execution because property joinfaces.skip is set.");
	}

	private void executeMojo() throws MojoExecutionException, MojoFailureException {
		Set<String> classpath = new HashSet<>();

		try {
			classpath.addAll(this.project.getRuntimeClasspathElements());
			classpath.addAll(this.project.getCompileClasspathElements());
		}
		catch (DependencyResolutionRequiredException e) {
			throw new MojoExecutionException("classpath not present", e);
		}

		try {
			ClasspathScanner.builder()
				.classpathRoot(this.outputDirectory)
				.classGraphConfigurer(classGraph -> classGraph.overrideClasspath(classpath))
				.build()
				.scanClasses();
		}
		catch (IOException e) {
				throw new MojoFailureException("Classpath scan failed", e);
		}
	}
}
