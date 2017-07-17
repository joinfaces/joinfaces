/*
 * Copyright 2016-2017 the original author or authors.
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

package org.joinfaces.integration;

import org.junit.Test;

import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class JsfCdiToSpringApplicationBeanFactoryPostProcessorIT {

	@Test
	public void testViewScopedClass() {
		GenericApplicationContext acx = new GenericApplicationContext();
		AnnotationConfigUtils.registerAnnotationConfigProcessors(acx);

		acx.registerBeanDefinition("viewScopedClass", new AnnotatedGenericBeanDefinition(
			new StandardAnnotationMetadata(ViewScopedClass.class)));
		acx.registerBeanDefinition("scopedBeansConfiguration", new RootBeanDefinition(
			ScopedBeansConfiguration.class));
		acx.addBeanFactoryPostProcessor(JsfScopeAnnotationsAutoConfiguration.jsfScopeAnnotationsConfigurer(acx.getEnvironment()));
		acx.addBeanFactoryPostProcessor(CdiScopeAnnotationsAutoConfiguration.cdiScopeAnnotationsConfigurer(acx.getEnvironment()));
		acx.refresh();

		assertThat(acx.getBeanDefinition("viewScopedClass").getScope())
			.isEqualTo(ViewScope.SCOPE_VIEW);
		assertThat(acx.getBeanDefinition("viewScopedBean").getScope())
			.isEqualTo(ViewScope.SCOPE_VIEW);
	}

	@Test
	public void testSessionScopedClass() {
		GenericApplicationContext acx = new GenericApplicationContext();
		AnnotationConfigUtils.registerAnnotationConfigProcessors(acx);

		acx.registerBeanDefinition("sessionScopedClass", new AnnotatedGenericBeanDefinition(
			new StandardAnnotationMetadata(SessionScopedClass.class)));
		acx.registerBeanDefinition("scopedBeansConfiguration", new RootBeanDefinition(
			ScopedBeansConfiguration.class));
		acx.addBeanFactoryPostProcessor(JsfScopeAnnotationsAutoConfiguration.jsfScopeAnnotationsConfigurer(acx.getEnvironment()));
		acx.addBeanFactoryPostProcessor(CdiScopeAnnotationsAutoConfiguration.cdiScopeAnnotationsConfigurer(acx.getEnvironment()));
		acx.refresh();

		assertThat(acx.getBeanDefinition("sessionScopedClass").getScope())
			.isEqualTo(WebApplicationContext.SCOPE_SESSION);
		assertThat(acx.getBeanDefinition("sessionScopedBean").getScope())
			.isEqualTo(WebApplicationContext.SCOPE_SESSION);
	}

	@Test
	public void testNoScopedClass() {
		GenericApplicationContext acx = new GenericApplicationContext();
		AnnotationConfigUtils.registerAnnotationConfigProcessors(acx);

		acx.registerBeanDefinition("noScopedClass", new AnnotatedGenericBeanDefinition(
			new StandardAnnotationMetadata(NoScopedClass.class)));
		acx.registerBeanDefinition("scopedBeansConfiguration", new RootBeanDefinition(
			ScopedBeansConfiguration.class));
		acx.addBeanFactoryPostProcessor(JsfScopeAnnotationsAutoConfiguration.jsfScopeAnnotationsConfigurer(acx.getEnvironment()));
		acx.addBeanFactoryPostProcessor(CdiScopeAnnotationsAutoConfiguration.cdiScopeAnnotationsConfigurer(acx.getEnvironment()));
		acx.refresh();

		assertThat(acx.getBeanDefinition("noScopedClass").getScope())
			.isEqualTo("");
		assertThat(acx.getBeanDefinition("noScopedBean").getScope())
			.isEqualTo("");

	}

}
