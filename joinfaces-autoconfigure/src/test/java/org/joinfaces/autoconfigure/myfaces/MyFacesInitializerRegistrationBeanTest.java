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

package org.joinfaces.autoconfigure.myfaces;

import java.util.Set;

import javax.faces.component.html.HtmlPanelGroup;
import javax.servlet.annotation.HandlesTypes;

import net.bootsfaces.component.tree.TreeRenderer;
import org.apache.myfaces.ee6.MyFacesContainerInitializer;
import org.apache.myfaces.renderkit.html.HtmlGridRenderer;
import org.joinfaces.autoconfigure.JsfClassFactory;
import org.joinfaces.autoconfigure.JsfClassFactoryConfiguration;
import org.junit.BeforeClass;
import org.junit.Test;
import org.omnifaces.component.input.Form;
import org.omnifaces.converter.SelectItemsIndexConverter;
import org.omnifaces.validator.RequiredCheckboxValidator;

import org.springframework.core.annotation.AnnotationUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class MyFacesInitializerRegistrationBeanTest {

	private static Set<Class<?>> classes;

	@BeforeClass
	public static void setupClasses() {
		JsfClassFactoryConfiguration configuration = JsfClassFactoryConfiguration.builder()
				.handlesTypes(AnnotationUtils.findAnnotation(MyFacesContainerInitializer.class, HandlesTypes.class))
				.excludeScopedAnnotations(true)
				.anotherFacesConfig(MyFacesInitializerRegistrationBean.ANOTHER_FACES_CONFIG)
				.build();

		classes = new JsfClassFactory(configuration).getAllClasses();
	}

	@Test
	public void testOmnifacesSelectItemsIndexConverter() {
		assertThat(classes).contains(SelectItemsIndexConverter.class);
	}

	@Test
	public void testOmnifacesRequiredCheckboxValidator() {
		assertThat(classes).contains(RequiredCheckboxValidator.class);
	}

	@Test
	public void testOmnifacesFormComponent() {
		assertThat(classes).contains(Form.class);
	}

	@Test
	public void testJavaxFacesHtmlPanelGroup() {
		assertThat(classes).contains(HtmlPanelGroup.class);
	}

	@Test
	public void testMyfacesHtmlGridRenderer() {
		assertThat(classes).contains(HtmlGridRenderer.class);
	}

	@Test
	public void testBootsfacesTreeRenderer() {
		assertThat(classes).contains(TreeRenderer.class);
	}

}
