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
import javax.servlet.ServletException;

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
public class InitParameterServletContextConfigurerIT {

	static final String FOO = "foo";
	private ServletContext servletContext;

	@Before
	public void setUp() throws ServletException {
		this.servletContext = new MockServletContext();

		InitParameterServletContextConfigurer servletContextConfigurer = new InitParameterServletContextConfigurer(Collections.singletonList((ServletContextInitParameterConfigurationProperties) new TestProperties()));
		servletContextConfigurer.onStartup(this.servletContext);
	}

	@Test
	public void testString() {
		assertThat(this.servletContext.getInitParameter("test.STRING")).isEqualTo("fooBar");
	}

	@Test
	public void testSuperString() {
		assertThat(this.servletContext.getInitParameter("test.superString")).isEqualTo("barFoo");
	}

	@Test
	public void testEmptyList() {
		assertThat(this.servletContext.getInitParameter("test.EMPTY_LIST")).isEmpty();
	}

	@Test
	public void testStringList() {
		assertThat(this.servletContext.getInitParameter("test.LIST")).isEqualTo("foo...bar");
	}

	@Test
	public void testSingletonList() {
		assertThat(this.servletContext.getInitParameter("test.SINGLETON_LIST")).isEqualTo(FOO);
	}

	@Test
	public void testNull() {
		assertThat(this.servletContext.getInitParameter("test.NULL")).isNull();
	}

	@Test
	public void testNestedProperty() {
		assertThat(this.servletContext.getInitParameter("test.inner.STRING")).isEqualTo("bar");
	}

	@Test
	public void testClassList() {
		assertThat(this.servletContext.getInitParameter("test.CLASS_LIST")).isEqualTo("java.lang.String;java.lang.Void");
	}

	@Test
	public void testEnum() {
		assertThat(this.servletContext.getInitParameter("test.ENUM")).isEqualTo("SOURCE");
	}

	@Test
	public void testBoolean() {
		assertThat(this.servletContext.getInitParameter("test.BOOLEAN")).isEqualTo("false");
	}

	@Test
	public void testInt() {
		assertThat(this.servletContext.getInitParameter("test.INT")).isEqualTo("42");
	}

	@Getter
	@Setter
	@NoArgsConstructor
	public static class TestProperties extends SuperProperties {

		@ServletContextInitParameter("test.STRING")
		private String testString = "fooBar";

		@ServletContextInitParameter(value = "test.EMPTY_LIST", listSeparator = "...")
		private List<String> testEmptyList = Collections.emptyList();

		@ServletContextInitParameter(value = "test.LIST", listSeparator = "...")
		private List<String> testStringList = Arrays.asList(FOO, "bar");

		@ServletContextInitParameter(value = "test.SINGLETON_LIST", listSeparator = "...")
		private List<String> singletonList = Collections.singletonList(FOO);

		@ServletContextInitParameter("test.NULL")
		private Object nullObject = null;

		@NestedProperty
		private InnerProperties innerProperties = new InnerProperties();

		@NestedProperty
		private InnerProperties nullInnerProperties = null;

		@ServletContextInitParameter(value = "test.CLASS_LIST", listSeparator = ";")
		private List<Class<?>> classList = Arrays.asList(
				String.class,
				Void.class
		);

		private String notAnnotatedField;

		@ServletContextInitParameter("test.ENUM")
		private RetentionPolicy testEnum = RetentionPolicy.SOURCE;

		@ServletContextInitParameter("test.BOOLEAN")
		private boolean testBoolean = false;

		@ServletContextInitParameter("test.INT")
		private int testInt = 42;

		@Getter
		@Setter
		@NoArgsConstructor
		public static class InnerProperties extends SuperProperties.InnerProperties {

			@ServletContextInitParameter("test.inner.STRING")
			private String innerString = "bar";
		}
	}

	@Getter
	@Setter
	@NoArgsConstructor
	public static class SuperProperties implements ServletContextInitParameterConfigurationProperties {

		@ServletContextInitParameter("test.superString")
		private String superString = "barFoo";

		@NestedProperty
		private TestProperties.InnerProperties innerProperties = new TestProperties.InnerProperties();

		@Getter
		@Setter
		@NoArgsConstructor
		public static class InnerProperties {

			@ServletContextInitParameter("test.inner.STRING")
			private String innerString = FOO;
		}
	}
}
