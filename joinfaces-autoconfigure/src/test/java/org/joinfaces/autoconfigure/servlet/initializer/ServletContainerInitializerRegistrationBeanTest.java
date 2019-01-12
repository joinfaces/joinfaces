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
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.annotation.HandlesTypes;

import com.sun.faces.config.FacesInitializer;
import com.sun.faces.renderkit.html_basic.MessageRenderer;
import org.apache.myfaces.ee.MyFacesContainerInitializer;
import org.junit.jupiter.api.Test;
import org.primefaces.component.inputnumber.InputNumberRenderer;

import org.springframework.web.jsf.DelegatingPhaseListenerMulticaster;

import static org.assertj.core.api.Assertions.assertThat;

public class ServletContainerInitializerRegistrationBeanTest {

	@Test
	public void getClasses_null() {
		ServletContainerInitializerRegistrationBean<NoHandlesTypes> bean = new ServletContainerInitializerRegistrationBean<>(NoHandlesTypes.class);
		assertThat(bean.getClasses(getClass().getClassLoader())).isNull();
	}

	@Test
	public void getClasses_empty() {
		ServletContainerInitializerRegistrationBean<EmptyHandlesTypes> bean = new ServletContainerInitializerRegistrationBean<>(EmptyHandlesTypes.class);
		assertThat(bean.getClasses(getClass().getClassLoader())).isNull();
	}

	@Test
	public void testMojarra() {
		ServletContainerInitializerRegistrationBean<FacesInitializer> bean = new ServletContainerInitializerRegistrationBean<>(FacesInitializer.class);
		Set<Class<?>> classes = bean.getClasses(getClass().getClassLoader());

		assertThat(classes).isNotEmpty();

		assertThat(classes).contains(UIViewAction.class, MessageRenderer.class, InputNumberRenderer.class, DelegatingPhaseListenerMulticaster.class);
	}

	@Test
	public void testMyfaces() {
		ServletContainerInitializerRegistrationBean<MyFacesContainerInitializer> bean = new ServletContainerInitializerRegistrationBean<>(MyFacesContainerInitializer.class);
		Set<Class<?>> classes = bean.getClasses(getClass().getClassLoader());

		assertThat(classes).isNotEmpty();

		assertThat(classes).contains(UIViewAction.class, MessageRenderer.class, InputNumberRenderer.class);
	}

	public static class NoHandlesTypes implements ServletContainerInitializer {
		@Override
		public void onStartup(Set<Class<?>> c, ServletContext ctx) {

		}
	}

	@HandlesTypes({})
	public static class EmptyHandlesTypes implements ServletContainerInitializer {
		@Override
		public void onStartup(Set<Class<?>> c, ServletContext ctx) {

		}
	}
}
