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
package org.joinfaces.autoconfigure.rewrite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ocpsoft.rewrite.annotation.config.AnnotationConfigProvider;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;
import org.springframework.mock.web.MockServletContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SpringBootAnnotationConfigProviderTest {

	private WebApplicationContextRunner webApplicationContextRunner;

	@BeforeEach
	public void setup() {
		this.webApplicationContextRunner = new WebApplicationContextRunner().withConfiguration(
				AutoConfigurations.of(RewriteAutoConfiguration.class, FooConfiguration.class));
	}

	@Test
	void getConfiguration_classpathScanningEnabled() {
		this.webApplicationContextRunner.run(context -> {
			SpringBootAnnotationConfigProvider annotationConfigProvider = context.getBean(
					SpringBootAnnotationConfigProvider.class);
			assertThat(annotationConfigProvider).isNotNull();
			MockServletContext servletContext = new MockServletContext();
			servletContext.setInitParameter(SpringBootAnnotationConfigProvider.SCAN_CLASSPATH, "true");
			servletContext.setInitParameter(AnnotationConfigProvider.CONFIG_BASE_PACKAGES, getClass().getPackage().getName());
			Configuration configuration = annotationConfigProvider.getConfiguration(servletContext);
			assertThat(configuration.getRules().size()).isEqualTo(1);
		});
	}

	@Test
	void getConfiguration_classpathScanningDisabled() {
		this.webApplicationContextRunner.run(context -> {
			SpringBootAnnotationConfigProvider annotationConfigProvider = context.getBean(
					SpringBootAnnotationConfigProvider.class);
			assertThat(annotationConfigProvider).isNotNull();
			MockServletContext servletContext = new MockServletContext();
			Configuration configuration = annotationConfigProvider.getConfiguration(servletContext);
			assertThat(configuration).isNull();
		});
	}
}
