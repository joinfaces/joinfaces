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

package org.joinfaces.autoconfigure.mojarra;

import java.util.Set;

import javax.faces.component.html.HtmlPanelGroup;
import javax.servlet.annotation.HandlesTypes;

import com.sun.faces.config.FacesInitializer;
import com.sun.faces.facelets.compiler.UIText;
import net.bootsfaces.component.tree.TreeRenderer;
import org.joinfaces.autoconfigure.servlet.initializer.JsfClassFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.omnifaces.component.input.Form;
import org.omnifaces.converter.SelectItemsIndexConverter;
import org.omnifaces.validator.RequiredCheckboxValidator;

import org.springframework.core.annotation.AnnotationUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class MojarraInitializerRegistrationBeanTest {

	private static Set<Class<?>> classes;

	@BeforeClass
	public static void setupClasses() {
		JsfClassFactory.Configuration configuration = JsfClassFactory.Configuration.builder()
				.excludeScopedAnnotations(true)
				.handlesTypes(AnnotationUtils.findAnnotation(FacesInitializer.class, HandlesTypes.class))
				.anotherFacesConfig(MojarraInitializerRegistrationBean.ANOTHER_FACES_CONFIG)
				.build();

		classes = new JsfClassFactory(configuration).getAllClasses();
	}

	@Test
	public void testJavaxFacesHtmlPanelGroup() {
		assertThat(classes).contains(HtmlPanelGroup.class);
	}

	@Test
	public void testMojarraUIText() {
		assertThat(classes).contains(UIText.class);
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
	public void testBootsfacesTreeRenderer() {
		assertThat(classes).contains(TreeRenderer.class);
	}

}
