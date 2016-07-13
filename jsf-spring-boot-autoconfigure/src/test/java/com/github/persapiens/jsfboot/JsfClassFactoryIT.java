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

package com.github.persapiens.jsfboot;

import java.util.Set;

import javax.faces.component.html.HtmlPanelGroup;

import com.sun.faces.facelets.compiler.UIText;

import org.apache.myfaces.renderkit.html.HtmlGridRenderer;

import org.testng.annotations.Test;

import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@WebAppConfiguration
@Test
public class JsfClassFactoryIT {

	public void testJavaxFacesHtmlPanelGroupWithMojarra() {
		MojarraJsfClassFactoryConfiguration configuration = new MojarraJsfClassFactoryConfiguration();

		Set<Class<?>> classes = JsfClassFactory.builder()
			.jsfAnnotatedClassFactoryConfiguration(configuration)
			.build().find();
		assertThat(classes).contains(HtmlPanelGroup.class);
	}

	public void testMojarraUITextWithMojarra() {
		MojarraJsfClassFactoryConfiguration configuration = new MojarraJsfClassFactoryConfiguration();

		Set<Class<?>> classes = JsfClassFactory.builder()
			.jsfAnnotatedClassFactoryConfiguration(configuration)
			.build().find();
		assertThat(classes).contains(UIText.class);
	}

	public void testJavaxFacesHtmlPanelGroupWithMyfaces() {
		MyfacesJsfClassFactoryConfiguration configuration = new MyfacesJsfClassFactoryConfiguration();

		Set<Class<?>> classes = JsfClassFactory.builder()
			.jsfAnnotatedClassFactoryConfiguration(configuration)
			.build().find();
		assertThat(classes).contains(HtmlPanelGroup.class);
	}

	public void testMyfacesHtmlGridRendererWithMyfaces() {
		MyfacesJsfClassFactoryConfiguration configuration = new MyfacesJsfClassFactoryConfiguration();

		Set<Class<?>> classes = JsfClassFactory.builder()
			.jsfAnnotatedClassFactoryConfiguration(configuration)
			.build().find();
		assertThat(classes).contains(HtmlGridRenderer.class);
	}

	public void testNullServletContextInitializer() {
		NullJsfClassFactoryConfiguration configuration = new NullJsfClassFactoryConfiguration();

		Set<Class<?>> classes = JsfClassFactory.builder()
			.jsfAnnotatedClassFactoryConfiguration(configuration)
			.build().find();
		assertThat(classes).doesNotContain(HtmlGridRenderer.class);
	}

	public void testNullAnotherServletContextInitializer() {
		NullAnotherJsfClassFactoryConfiguration configuration = new NullAnotherJsfClassFactoryConfiguration();

		Set<Class<?>> classes = JsfClassFactory.builder()
			.jsfAnnotatedClassFactoryConfiguration(configuration)
			.build().find();
		assertThat(classes).doesNotContain(HtmlGridRenderer.class);
	}

}
