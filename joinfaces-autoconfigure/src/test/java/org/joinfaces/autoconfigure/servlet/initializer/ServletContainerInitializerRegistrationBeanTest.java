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

package org.joinfaces.autoconfigure.servlet.initializer;

import java.util.Set;

import javax.faces.component.UIViewAction;
import javax.servlet.annotation.HandlesTypes;

import com.sun.faces.config.FacesInitializer;
import com.sun.faces.renderkit.html_basic.MessageRenderer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.primefaces.component.inputnumber.InputNumberRenderer;

import org.springframework.core.annotation.AnnotationUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.when;

public class ServletContainerInitializerRegistrationBeanTest {

	private ServletContainerInitializerRegistrationBean<?> servletContainerInitializerRegistrationBean;

	@BeforeEach
	public void setUp() {
		this.servletContainerInitializerRegistrationBean = new ServletContainerInitializerRegistrationBean<>(FacesInitializer.class);
	}

	@Test
	public void getClasses() {
		HandlesTypes handlesTypes = AnnotationUtils.findAnnotation(FacesInitializer.class, HandlesTypes.class);
		Set<Class<?>> classes = this.servletContainerInitializerRegistrationBean.getClasses(handlesTypes);

		assertThat(classes).isNotEmpty();

		assertThat(classes).contains(UIViewAction.class, MessageRenderer.class, InputNumberRenderer.class);
	}

	@Test
	public void getClasses_null() {
		assertThat(this.servletContainerInitializerRegistrationBean.getClasses(null)).isNull();
	}

	@Test
	public void getClasses_empty() {
		HandlesTypes handlesTypes = mock(HandlesTypes.class);
		when(handlesTypes.value()).thenReturn(new Class[0]);
		assertThat(this.servletContainerInitializerRegistrationBean.getClasses(handlesTypes)).isNull();
	}

	@Test
	public void getClasses_noneFound() {
		HandlesTypes handlesTypes = mock(HandlesTypes.class);
		when(handlesTypes.value()).thenReturn(new Class[]{this.getClass()});
		assertThat(this.servletContainerInitializerRegistrationBean.getClasses(handlesTypes)).isNull();
	}
}
