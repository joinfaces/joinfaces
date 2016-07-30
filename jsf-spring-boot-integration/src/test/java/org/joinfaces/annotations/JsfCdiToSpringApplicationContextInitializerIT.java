/*
 * Copyright 2016-2016 the original author or authors.
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

package org.joinfaces.annotations;

import org.junit.Test;

import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Jsf Cdi to Spring application context initializer
 */
public class JsfCdiToSpringApplicationContextInitializerIT {

	@Test
	public void testAnnotationConfigEmbeddedWebApplicationContext() {
		AnnotationConfigEmbeddedWebApplicationContext annotationConfigEmbeddedWebApplicationContext = new AnnotationConfigEmbeddedWebApplicationContext();
		JsfCdiToSpringApplicationContextInitializer initializer = new JsfCdiToSpringApplicationContextInitializer();

		initializer.initialize(annotationConfigEmbeddedWebApplicationContext);

		assertThat(annotationConfigEmbeddedWebApplicationContext.getBeanFactoryPostProcessors().isEmpty()).isFalse();
	}

	@Test
	public void testAnnotationConfigWebApplicationContext() {
		AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
		JsfCdiToSpringApplicationContextInitializer initializer = new JsfCdiToSpringApplicationContextInitializer();

		initializer.initialize(annotationConfigWebApplicationContext);

		assertThat(annotationConfigWebApplicationContext.getBeanFactoryPostProcessors().isEmpty()).isFalse();
	}

	@Test
	public void testAnnotationConfigApplicationContext() {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		JsfCdiToSpringApplicationContextInitializer initializer = new JsfCdiToSpringApplicationContextInitializer();

		initializer.initialize(annotationConfigApplicationContext);

		assertThat(annotationConfigApplicationContext.getBeanFactoryPostProcessors().isEmpty()).isFalse();
	}

}
