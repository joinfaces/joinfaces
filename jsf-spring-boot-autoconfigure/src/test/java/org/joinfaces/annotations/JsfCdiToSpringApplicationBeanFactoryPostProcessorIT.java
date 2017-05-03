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

import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.type.StandardAnnotationMetadata;

import static org.assertj.core.api.Assertions.assertThat;

public class JsfCdiToSpringApplicationBeanFactoryPostProcessorIT {

	@Test
	public void testRegisteredScopeView() {
		ConfigurableListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		BeanFactoryPostProcessor beanFactoryPostProcessor = new JsfCdiToSpringBeanFactoryPostProcessor();

		beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

		assertThat(beanFactory.getRegisteredScope(JsfCdiToSpring.VIEW))
			.isInstanceOf(ViewScope.class);
	}

	@Test
	public void testViewScopedClass() {
		GenericApplicationContext acx = new GenericApplicationContext();
		AnnotationConfigUtils.registerAnnotationConfigProcessors(acx);

		acx.registerBeanDefinition("viewScopedClass", new AnnotatedGenericBeanDefinition(
			new StandardAnnotationMetadata(ViewScopedClass.class)));
		acx.registerBeanDefinition("scopedBeansConfiguration", new RootBeanDefinition(
			ScopedBeansConfiguration.class));
		acx.addBeanFactoryPostProcessor(new JsfCdiToSpringBeanFactoryPostProcessor());
		acx.refresh();

		assertThat(acx.getBeanDefinition("viewScopedClass").getScope())
			.isEqualTo(JsfCdiToSpring.VIEW);
		assertThat(acx.getBeanDefinition("viewScopedBean").getScope())
			.isEqualTo(JsfCdiToSpring.VIEW);
	}

	@Test
	public void testSessionScopedClass() {
		GenericApplicationContext acx = new GenericApplicationContext();
		AnnotationConfigUtils.registerAnnotationConfigProcessors(acx);

		acx.registerBeanDefinition("sessionScopedClass", new AnnotatedGenericBeanDefinition(
			new StandardAnnotationMetadata(SessionScopedClass.class)));
		acx.registerBeanDefinition("scopedBeansConfiguration", new RootBeanDefinition(
			ScopedBeansConfiguration.class));
		acx.addBeanFactoryPostProcessor(new JsfCdiToSpringBeanFactoryPostProcessor());
		acx.refresh();

		assertThat(acx.getBeanDefinition("sessionScopedClass").getScope())
			.isEqualTo(JsfCdiToSpring.SESSION);
		assertThat(acx.getBeanDefinition("sessionScopedBean").getScope())
			.isEqualTo(JsfCdiToSpring.SESSION);
	}

	@Test
	public void testNoScopedClass() {
		GenericApplicationContext acx = new GenericApplicationContext();
		AnnotationConfigUtils.registerAnnotationConfigProcessors(acx);

		acx.registerBeanDefinition("noScopedClass", new AnnotatedGenericBeanDefinition(
			new StandardAnnotationMetadata(NoScopedClass.class)));
		acx.registerBeanDefinition("scopedBeansConfiguration", new RootBeanDefinition(
			ScopedBeansConfiguration.class));
		acx.addBeanFactoryPostProcessor(new JsfCdiToSpringBeanFactoryPostProcessor());
		acx.refresh();

		assertThat(acx.getBeanDefinition("noScopedClass").getScope())
			.isEqualTo("");
		assertThat(acx.getBeanDefinition("noScopedBean").getScope())
			.isEqualTo("");

	}

}
