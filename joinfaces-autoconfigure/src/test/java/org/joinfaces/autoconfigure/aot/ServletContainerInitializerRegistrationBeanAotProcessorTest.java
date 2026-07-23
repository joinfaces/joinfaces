/*
 * Copyright 2016-2024 the original author or authors.
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

package org.joinfaces.autoconfigure.aot;

import java.util.Set;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.HandlesTypes;

import org.joinfaces.servlet.ServletContainerInitializerRegistrationBean;
import org.junit.jupiter.api.Test;

import org.springframework.aot.generate.ClassNameGenerator;
import org.springframework.aot.generate.DefaultGenerationContext;
import org.springframework.aot.generate.GeneratedFiles;
import org.springframework.aot.generate.InMemoryGeneratedFiles;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.javapoet.ClassName;

import static org.assertj.core.api.Assertions.assertThat;

class ServletContainerInitializerRegistrationBeanAotProcessorTest {

	/**
	 * When a {@link ServletContainerInitializer}'s {@code @HandlesTypes} matches no classes
	 * on the build-time classpath, the AOT processor must still write an empty marker file.
	 * Without it, {@link ServletContainerInitializerRegistrationBean#findPreparedScanResult}
	 * cannot distinguish "scan yielded no classes" from "scan was never performed" and falls
	 * back to a full ClassGraph scan at runtime — causing OOM on memory-constrained deployments.
	 */
	@Test
	void writesEmptyMarkerWhenScanYieldsNoClasses() throws Exception {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		beanFactory.registerBeanDefinition("mockFacesInitializer2",
				new RootBeanDefinition(ServletContainerInitializerRegistrationBean.class,
						() -> new ServletContainerInitializerRegistrationBean<>(MockFacesInitializer2.class)));

		ServletContainerInitializerRegistrationBeanAotProcessor processor =
				new ServletContainerInitializerRegistrationBeanAotProcessor();

		var contribution = processor.processAheadOfTime(beanFactory);
		assertThat(contribution).isNotNull();

		InMemoryGeneratedFiles generatedFiles = new InMemoryGeneratedFiles();
		DefaultGenerationContext context = new DefaultGenerationContext(
				new ClassNameGenerator(ClassName.get(Object.class)), generatedFiles);

		contribution.applyTo(context, null);
		context.writeGeneratedContent();

		String markerPath = new ServletContainerInitializerRegistrationBean<>(MockFacesInitializer2.class)
				.getPreparedScanResultPath();

		assertThat(generatedFiles.getGeneratedFiles(GeneratedFiles.Kind.RESOURCE))
				.containsKey(markerPath);

		// The file must exist and be empty (no class names) — this is what distinguishes
		// "AOT scanned, found nothing" from "AOT never ran" at runtime.
		String content = generatedFiles.getGeneratedFileContent(GeneratedFiles.Kind.RESOURCE, markerPath);
		assertThat(content).as("marker file must exist (even if empty)").isNotNull();
		assertThat(content.strip()).as("marker file must contain no class names").isEmpty();
	}

	/** Mirrors {@code FacesInitializer2}: handles a single annotation unlikely to match any class. */
	@HandlesTypes(Resource.class)
	static class MockFacesInitializer2 implements ServletContainerInitializer {
		@Override
		public void onStartup(Set<Class<?>> c, ServletContext ctx) {
		}
	}
}
