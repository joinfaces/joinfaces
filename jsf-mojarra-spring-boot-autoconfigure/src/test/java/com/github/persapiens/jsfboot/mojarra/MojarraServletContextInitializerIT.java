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

package com.github.persapiens.jsfboot.mojarra;

import java.util.Set;

import javax.faces.component.html.HtmlPanelGroup;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import com.github.persapiens.jsfboot.JsfClassFactory;
import com.sun.faces.facelets.compiler.UIText;

import net.bootsfaces.component.tree.TreeRenderer;

import org.omnifaces.component.input.Form;
import org.omnifaces.converter.SelectItemsIndexConverter;
import org.omnifaces.validator.RequiredCheckboxValidator;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringApplicationConfiguration(classes = MojarraSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class MojarraServletContextInitializerIT extends AbstractTestNGSpringContextTests {

	@Autowired
	private MojarraProperties mojarraProperties;

	private Set<Class<?>> classes;

	@BeforeSuite
	public void setupClasses() {
		MojarraServletContextInitializer configuration = new MojarraServletContextInitializer(null);

		this.classes = JsfClassFactory.builder()
			.jsfAnnotatedClassFactoryConfiguration(configuration)
			.build().find();
	}

	public void testJavaxFacesHtmlPanelGroup() {
		assertThat(this.classes).contains(HtmlPanelGroup.class);
	}

	public void testMojarraUIText() {
		assertThat(this.classes).contains(UIText.class);
	}

	public void testOmnifacesSelectItemsIndexConverter() {
		assertThat(this.classes).contains(SelectItemsIndexConverter.class);
	}

	public void testOmnifacesRequiredCheckboxValidator() {
		assertThat(this.classes).contains(RequiredCheckboxValidator.class);
	}

	public void testOmnifacesFormComponent() {
		assertThat(this.classes).contains(Form.class);
	}

	public void testBootsfacesTreeRenderer() {
		assertThat(this.classes).contains(TreeRenderer.class);
	}

	public void testAnotherFacesConfig() throws ServletException {
		MojarraServletContextInitializer mojarraServletContextInitializer
			= new MojarraServletContextInitializer(this.mojarraProperties);

		assertThat(mojarraServletContextInitializer.getAnotherFacesConfig())
			.isEqualTo(MojarraServletContextInitializer.ANOTHER_FACES_CONFIG);
	}

	public void testExcludeScopedAnnotations() throws ServletException {
		MojarraServletContextInitializer mojarraServletContextInitializer
			= new MojarraServletContextInitializer(this.mojarraProperties);

		assertThat(mojarraServletContextInitializer.isExcludeScopedAnnotations()).isTrue();
	}

	public void testOnStartup() throws ServletException {
		MojarraServletContextInitializer mojarraServletContextInitializer
			= new MojarraServletContextInitializer(this.mojarraProperties);

		ServletContext servletContext = new MojarraMockServletContext();

		mojarraServletContextInitializer.onStartup(servletContext);

		assertThat(servletContext.getInitParameter(MojarraServletContextConfigurer.PREFFIX + ".clientStateTimeout"))
			.isEqualTo("10");
	}

}
