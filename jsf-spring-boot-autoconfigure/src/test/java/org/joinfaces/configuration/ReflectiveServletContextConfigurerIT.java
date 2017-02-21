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

package org.joinfaces.configuration;

import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.Before;
import org.junit.Test;

import org.springframework.mock.web.MockServletContext;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Lars Grefer
 */
public class ReflectiveServletContextConfigurerIT {

	static final String FOO = "foo";
	private ReflectiveServletContextConfigurer servletContextConfigurer;
	private ServletContext servletContext;

	@Before
	public void setUp() {
		this.servletContext = new MockServletContext();

		this.servletContextConfigurer = new ReflectiveServletContextConfigurer<TestProperties>(this.servletContext, new TestProperties());
	}

	@Test
	public void testString() {
		this.servletContextConfigurer.configure();

		assertThat(this.servletContext.getInitParameter("test.STRING")).isEqualTo("fooBar");
	}

	@Test
	public void testSuperString() {
		this.servletContextConfigurer.configure();

		assertThat(this.servletContext.getInitParameter("test.superString")).isEqualTo("barFoo");
	}

	@Test
	public void testEmptyList() {
		this.servletContextConfigurer.configure();

		assertThat(this.servletContext.getInitParameter("test.EMPTY_LIST")).isEmpty();
	}

	@Test
	public void testStringList() {
		this.servletContextConfigurer.configure();

		assertThat(this.servletContext.getInitParameter("test.LIST")).isEqualTo("foo...bar");
	}

	@Test
	public void testSingletonList() {
		this.servletContextConfigurer.configure();

		assertThat(this.servletContext.getInitParameter("test.SINGLETON_LIST")).isEqualTo(FOO);
	}

	@Test
	public void testNull() {
		this.servletContextConfigurer.configure();

		assertThat(this.servletContext.getInitParameter("test.NULL")).isNull();
	}

	@Test
	public void testNestedProperty() {
		this.servletContextConfigurer.configure();

		assertThat(this.servletContext.getInitParameter("test.inner.STRING")).isEqualTo("bar");
	}

	@Test
	public void testClassList() {
		this.servletContextConfigurer.configure();

		assertThat(this.servletContext.getInitParameter("test.CLASS_LIST")).isEqualTo("java.lang.String;java.lang.Void");
	}

	@Test
	public void testEnum() {
		this.servletContextConfigurer.configure();

		assertThat(this.servletContext.getInitParameter("test.ENUM")).isEqualTo("SOURCE");
	}

	@Test
	public void testBoolean() {
		this.servletContextConfigurer.configure();

		assertThat(this.servletContext.getInitParameter("test.BOOLEAN")).isEqualTo("false");
	}

	@Test
	public void testInt() {
		this.servletContextConfigurer.configure();

		assertThat(this.servletContext.getInitParameter("test.INT")).isEqualTo("42");
	}

	@Getter
	@Setter
	@NoArgsConstructor
	public static class TestProperties extends SuperProperties {

		@InitParameter("test.STRING")
		private String testString = "fooBar";

		@InitParameter(value = "test.EMPTY_LIST", listSeparator = "...")
		private List<String> testEmptyList = Collections.emptyList();

		@InitParameter(value = "test.LIST", listSeparator = "...")
		private List<String> testStringList = Arrays.asList(FOO, "bar");

		@InitParameter(value = "test.SINGLETON_LIST", listSeparator = "...")
		private List<String> singletonList = Collections.singletonList(FOO);

		@InitParameter("test.NULL")
		private Object nullObject = null;

		@NestedProperty
		private InnerProperties innerProperties = new InnerProperties();

		@NestedProperty
		private InnerProperties nullInnerProperties = null;

		@InitParameter(value = "test.CLASS_LIST", listSeparator = ";")
		private List<Class<?>> classList = Arrays.asList(
				String.class,
				Void.class
		);

		private String notAnnotatedField;

		@InitParameter("test.ENUM")
		private RetentionPolicy testEnum = RetentionPolicy.SOURCE;

		@InitParameter("test.BOOLEAN")
		private boolean testBoolean = false;

		@InitParameter("test.INT")
		private int testInt = 42;

		@Getter
		@Setter
		@NoArgsConstructor
		public static class InnerProperties extends SuperProperties.InnerProperties {

			@InitParameter("test.inner.STRING")
			private String innerString = "bar";
		}
	}

	@Getter
	@Setter
	@NoArgsConstructor
	public static class SuperProperties {

		@InitParameter("test.superString")
		private String superString = "barFoo";

		@NestedProperty
		private TestProperties.InnerProperties innerProperties = new TestProperties.InnerProperties();

		@Getter
		@Setter
		@NoArgsConstructor
		public static class InnerProperties {

			@InitParameter("test.inner.STRING")
			private String innerString = FOO;
		}
	}
}
