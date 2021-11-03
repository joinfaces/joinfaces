/*
 * Copyright 2016-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.joinfaces.autoconfigure.javaxfaces;

import java.util.HashMap;
import java.util.Map;

import javax.faces.annotation.ApplicationMap;
import javax.faces.annotation.FlowMap;
import javax.faces.annotation.HeaderMap;
import javax.faces.annotation.HeaderValuesMap;
import javax.faces.annotation.InitParameterMap;
import javax.faces.annotation.RequestCookieMap;
import javax.faces.annotation.RequestMap;
import javax.faces.annotation.RequestParameterMap;
import javax.faces.annotation.RequestParameterValuesMap;
import javax.faces.annotation.SessionMap;
import javax.faces.annotation.ViewMap;
import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ReflectionUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.verify;
import static org.mockito.BDDMockito.when;

public class JsfBeansAnnotationPostProcessorTest {

	private JsfBeansAnnotationPostProcessor jsfBeansAnnotationPostProcessor;
	private JsfBeansAutoConfiguration jsfBeansAutoConfiguration;

	@BeforeEach
	public void setUp() {
		this.jsfBeansAutoConfiguration = mock(JsfBeansAutoConfiguration.class);

		BeanFactory beanFactory = mock(BeanFactory.class);
		when(beanFactory.getBean(JsfBeansAutoConfiguration.class)).thenReturn(this.jsfBeansAutoConfiguration);

		this.jsfBeansAnnotationPostProcessor = new JsfBeansAnnotationPostProcessor();
		this.jsfBeansAnnotationPostProcessor.setBeanFactory(beanFactory);
	}

	@Test
	public void testIsAutowiredField() {
		class TestFields {
			@Autowired
			private Object a;
			@Value("b")
			private Object b;
			@Inject
			private Object c;

			private Object d;
		}

		assertThat(this.jsfBeansAnnotationPostProcessor.isAutowiredField(ReflectionUtils.findField(TestFields.class, "a"))).isTrue();
		assertThat(this.jsfBeansAnnotationPostProcessor.isAutowiredField(ReflectionUtils.findField(TestFields.class, "b"))).isTrue();
		assertThat(this.jsfBeansAnnotationPostProcessor.isAutowiredField(ReflectionUtils.findField(TestFields.class, "c"))).isTrue();
		assertThat(this.jsfBeansAnnotationPostProcessor.isAutowiredField(ReflectionUtils.findField(TestFields.class, "d"))).isFalse();
	}

	@Test
	public void testApplicationMap() {
		HashMap<String, Object> map = new HashMap<>();
		when(this.jsfBeansAutoConfiguration.applicationMap()).thenReturn(map);

		Object bean = new Object() {
			@Autowired
			@ApplicationMap
			private Map<String, Object> map;
		};

		this.jsfBeansAnnotationPostProcessor.postProcessBeforeInitialization(bean, "");

		verify(this.jsfBeansAutoConfiguration).applicationMap();
		assertThat(bean).hasFieldOrPropertyWithValue("map", map);
	}

	@Test
	public void testFlowMap() {
		HashMap<Object, Object> map = new HashMap<>();
		when(this.jsfBeansAutoConfiguration.flowScope()).thenReturn(map);

		Object bean = new Object() {
			@Autowired
			@FlowMap
			private Map<String, Object> map;
		};

		this.jsfBeansAnnotationPostProcessor.postProcessBeforeInitialization(bean, "");

		verify(this.jsfBeansAutoConfiguration).flowScope();
		assertThat(bean).hasFieldOrPropertyWithValue("map", map);
	}

	@Test
	public void testHeaderMap() {
		HashMap<String, String> map = new HashMap<>();
		when(this.jsfBeansAutoConfiguration.headerMap()).thenReturn(map);

		Object bean = new Object() {
			@Autowired
			@HeaderMap
			private Map<String, Object> map;
		};

		this.jsfBeansAnnotationPostProcessor.postProcessBeforeInitialization(bean, "");

		verify(this.jsfBeansAutoConfiguration).headerMap();
		assertThat(bean).hasFieldOrPropertyWithValue("map", map);
	}

	@Test
	public void testHeaderValuesMap() {
		HashMap<String, String[]> map = new HashMap<>();
		when(this.jsfBeansAutoConfiguration.headerValuesMap()).thenReturn(map);

		Object bean = new Object() {
			@Autowired
			@HeaderValuesMap
			private Map<String, Object> map;
		};

		this.jsfBeansAnnotationPostProcessor.postProcessBeforeInitialization(bean, "");

		verify(this.jsfBeansAutoConfiguration).headerValuesMap();
		assertThat(bean).hasFieldOrPropertyWithValue("map", map);
	}

	@Test
	public void testInitParameterMap() {
		HashMap<String, String> map = new HashMap<>();
		when(this.jsfBeansAutoConfiguration.initParameterMap()).thenReturn(map);

		Object bean = new Object() {
			@Autowired
			@InitParameterMap
			private Map<String, Object> map;
		};

		this.jsfBeansAnnotationPostProcessor.postProcessBeforeInitialization(bean, "");

		verify(this.jsfBeansAutoConfiguration).initParameterMap();
		assertThat(bean).hasFieldOrPropertyWithValue("map", map);
	}

	@Test
	public void testRequestCookieMap() {
		HashMap<String, Object> map = new HashMap<>();
		when(this.jsfBeansAutoConfiguration.requestCookieMap()).thenReturn(map);

		Object bean = new Object() {
			@Autowired
			@RequestCookieMap
			private Map<String, Object> map;
		};

		this.jsfBeansAnnotationPostProcessor.postProcessBeforeInitialization(bean, "");

		verify(this.jsfBeansAutoConfiguration).requestCookieMap();
		assertThat(bean).hasFieldOrPropertyWithValue("map", map);
	}

	@Test
	public void testRequestMap() {
		HashMap<String, Object> map = new HashMap<>();
		when(this.jsfBeansAutoConfiguration.requestMap()).thenReturn(map);

		Object bean = new Object() {
			@Autowired
			@RequestMap
			private Map<String, Object> map;
		};

		this.jsfBeansAnnotationPostProcessor.postProcessBeforeInitialization(bean, "");

		verify(this.jsfBeansAutoConfiguration).requestMap();
		assertThat(bean).hasFieldOrPropertyWithValue("map", map);
	}

	@Test
	public void testRequestParameterMap() {
		HashMap<String, String> map = new HashMap<>();
		when(this.jsfBeansAutoConfiguration.requestParameterMap()).thenReturn(map);

		Object bean = new Object() {
			@Autowired
			@RequestParameterMap
			private Map<String, Object> map;
		};

		this.jsfBeansAnnotationPostProcessor.postProcessBeforeInitialization(bean, "");

		verify(this.jsfBeansAutoConfiguration).requestParameterMap();
		assertThat(bean).hasFieldOrPropertyWithValue("map", map);
	}

	@Test
	public void testRequestParameterValuesMap() {
		HashMap<String, String[]> map = new HashMap<>();
		when(this.jsfBeansAutoConfiguration.requestParameterValuesMap()).thenReturn(map);

		Object bean = new Object() {
			@Autowired
			@RequestParameterValuesMap
			private Map<String, Object> map;
		};

		this.jsfBeansAnnotationPostProcessor.postProcessBeforeInitialization(bean, "");

		verify(this.jsfBeansAutoConfiguration).requestParameterValuesMap();
		assertThat(bean).hasFieldOrPropertyWithValue("map", map);
	}

	@Test
	public void testSessionMap() {
		HashMap<String, Object> map = new HashMap<>();
		when(this.jsfBeansAutoConfiguration.sessionMap()).thenReturn(map);

		Object bean = new Object() {
			@Autowired
			@SessionMap
			private Map<String, Object> sessionMap;
		};

		this.jsfBeansAnnotationPostProcessor.postProcessBeforeInitialization(bean, "");

		verify(this.jsfBeansAutoConfiguration).sessionMap();
		assertThat(bean).hasFieldOrPropertyWithValue("sessionMap", map);
	}

	@Test
	public void testViewMap() {
		HashMap<String, Object> map = new HashMap<>();
		when(this.jsfBeansAutoConfiguration.sessionMap()).thenReturn(map);

		Object bean = new Object() {
			@Inject
			@ViewMap
			private Map<String, Object> viewMap;
		};

		this.jsfBeansAnnotationPostProcessor.postProcessBeforeInitialization(bean, "");

		verify(this.jsfBeansAutoConfiguration).viewMap();
		assertThat(bean).hasFieldOrPropertyWithValue("viewMap", map);
	}
}
