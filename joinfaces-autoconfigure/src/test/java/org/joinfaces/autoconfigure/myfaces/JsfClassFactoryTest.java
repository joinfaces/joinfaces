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

package org.joinfaces.autoconfigure.myfaces;

import java.util.Set;

import javax.faces.component.html.HtmlPanelGroup;
import javax.servlet.annotation.HandlesTypes;

import org.apache.myfaces.ee.MyFacesContainerInitializer;
import org.apache.myfaces.renderkit.html.HtmlGridRenderer;
import org.joinfaces.autoconfigure.servlet.initializer.JsfClassFactory;
import org.junit.jupiter.api.Test;

import org.springframework.core.annotation.AnnotationUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class JsfClassFactoryTest {

	@Test
	public void testJavaxFacesHtmlPanelGroupWithMyfaces() {
		JsfClassFactory.Configuration configuration = JsfClassFactory.Configuration.builder()
				.anotherConfig(MyFacesInitializerRegistrationBean.ANOTHER_CONFIG)
				.excludeScopedAnnotations(true)
				.handlesTypes(AnnotationUtils.findAnnotation(MyFacesContainerInitializer.class, HandlesTypes.class))
				.build();

		Set<Class<?>> classes = new JsfClassFactory(configuration).getOtherClasses();
		assertThat(classes).contains(HtmlPanelGroup.class);
	}

	@Test
	public void testMyfacesHtmlGridRendererWithMyfaces() {
		JsfClassFactory.Configuration configuration = JsfClassFactory.Configuration.builder()
				.anotherConfig(MyFacesInitializerRegistrationBean.ANOTHER_CONFIG)
				.excludeScopedAnnotations(true)
				.handlesTypes(AnnotationUtils.findAnnotation(MyFacesContainerInitializer.class, HandlesTypes.class))
				.build();

		Set<Class<?>> classes = new JsfClassFactory(configuration).getOtherClasses();
		assertThat(classes).contains(HtmlGridRenderer.class);
	}

	@Test
	public void testNullServletContextInitializer() {
		JsfClassFactory.Configuration configuration = JsfClassFactory.Configuration.builder()
				.excludeScopedAnnotations(true)
				.handlesTypes(null)
				.anotherConfig(null)
				.build();

		Set<Class<?>> classes = new JsfClassFactory(configuration).getAllClasses();
		assertThat(classes).doesNotContain(HtmlGridRenderer.class);
	}

	@Test
	public void testNullAnotherServletContextInitializer() {
		JsfClassFactory.Configuration configuration = JsfClassFactory.Configuration.builder()
				.anotherConfig(null)
				.excludeScopedAnnotations(true)
				.handlesTypes(AnnotationUtils.findAnnotation(MyFacesContainerInitializer.class, HandlesTypes.class))
				.build();

		Set<Class<?>> classes = new JsfClassFactory(configuration).getAllClasses();
		assertThat(classes).doesNotContain(HtmlGridRenderer.class);
	}

}
