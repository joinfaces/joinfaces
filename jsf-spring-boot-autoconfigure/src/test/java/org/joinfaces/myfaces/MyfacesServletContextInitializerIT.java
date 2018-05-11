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

package org.joinfaces.myfaces;

import java.util.Set;

import javax.faces.component.html.HtmlPanelGroup;
import javax.servlet.annotation.HandlesTypes;

import net.bootsfaces.component.tree.TreeRenderer;
import org.apache.myfaces.ee6.MyFacesContainerInitializer;
import org.apache.myfaces.renderkit.html.HtmlGridRenderer;
import org.joinfaces.JsfClassFactory;
import org.joinfaces.JsfClassFactoryConfiguration;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omnifaces.component.input.Form;
import org.omnifaces.converter.SelectItemsIndexConverter;
import org.omnifaces.validator.RequiredCheckboxValidator;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class MyfacesServletContextInitializerIT {

	private static Set<Class<?>> classes;

	@BeforeClass
	public static void setupClasses() {
		JsfClassFactoryConfiguration configuration = JsfClassFactoryConfiguration.builder()
				.handlesTypes(AnnotationUtils.findAnnotation(MyFacesContainerInitializer.class, HandlesTypes.class))
				.excludeScopedAnnotations(true)
				.anotherConfig(MyfacesServletContextInitializer.ANOTHER_CONFIG)
				.build();

		classes = new JsfClassFactory(configuration).getAllClasses();
	}

	@Test
	public void testOmnifacesSelectItemsIndexConverter() {
		assertThat(this.classes).contains(SelectItemsIndexConverter.class);
	}

	@Test
	public void testOmnifacesRequiredCheckboxValidator() {
		assertThat(this.classes).contains(RequiredCheckboxValidator.class);
	}

	@Test
	public void testOmnifacesFormComponent() {
		assertThat(this.classes).contains(Form.class);
	}

	@Test
	public void testJavaxFacesHtmlPanelGroup() {
		assertThat(this.classes).contains(HtmlPanelGroup.class);
	}

	@Test
	public void testMyfacesHtmlGridRenderer() {
		assertThat(this.classes).contains(HtmlGridRenderer.class);
	}

	@Test
	public void testBootsfacesTreeRenderer() {
		assertThat(this.classes).contains(TreeRenderer.class);
	}

}
