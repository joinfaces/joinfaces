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

package org.joinfaces;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;

import org.junit.Before;
import org.junit.Test;

import org.springframework.mock.web.MockServletContext;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author Lars Grefer
 */
public class ServletContextConfigurerTest {

	private static final String FOO = "foo";
	private static final String BAR = "bar";

	private ServletContextConfigurer servletContextConfigurer;
	private ServletContext servletContext;

	@Before
	public void setUp() {
		servletContext = new MockServletContext();

		servletContextConfigurer = new ServletContextConfigurer(servletContext, "") {
			@Override
			public void configure() {
			}
		};
	}

	@Test
	public void setInitParameterString() {
		servletContextConfigurer.setInitParameterString(FOO, BAR);

		assertThat(servletContext.getInitParameter(FOO)).isEqualTo(BAR);
	}

	@Test
	public void setInitParameterBoolean() {
		servletContextConfigurer.setInitParameterBoolean(FOO, true);
		servletContextConfigurer.setInitParameterBoolean(BAR, false);

		assertThat(servletContext.getInitParameter(FOO)).isEqualTo("true");
		assertThat(servletContext.getInitParameter(BAR)).isEqualTo("false");
	}

	@Test
	public void setInitParameterCollection() {
		servletContextConfigurer.setInitParameterStringCollection(FOO, Arrays.asList(FOO, BAR), ServletContextConfigurer.Separator.COMMA);

		assertThat(servletContext.getInitParameter(FOO)).isEqualTo("foo,bar");
	}

	@Test
	public void setInitParameterCollectionNull() {
		servletContextConfigurer.setInitParameterStringCollection(FOO, null, ServletContextConfigurer.Separator.COMMA);

		assertThat(servletContext.getInitParameter(FOO)).isNull();
	}

	@Test
	public void setInitParameterCollectionEmpty() {
		servletContextConfigurer.setInitParameterStringCollection(FOO, Collections.<String>emptyList(), ServletContextConfigurer.Separator.COMMA);

		assertThat(servletContext.getInitParameter(FOO)).isEmpty();
	}

	@Test
	public void setInitParameterCollectionSingle() {
		servletContextConfigurer.setInitParameterStringCollection(FOO, Collections.singletonList(BAR), ServletContextConfigurer.Separator.COMMA);

		assertThat(servletContext.getInitParameter(FOO)).isEqualTo(BAR);
	}

	@Test
	public void testInitParameterClassCollection() {
		List<Class<?>> list = Arrays.asList(Void.class, String.class);
		servletContextConfigurer.setInitParameterClassCollection(FOO, list, ServletContextConfigurer.Separator.COMMA);
		servletContextConfigurer.setInitParameterClassCollection(BAR, list, ServletContextConfigurer.Separator.SEMICOLON);

		assertThat(servletContext.getInitParameter(FOO)).isEqualTo("java.lang.Void,java.lang.String");
		assertThat(servletContext.getInitParameter(BAR)).isEqualTo("java.lang.Void;java.lang.String");
	}

	@Test
	public void testInitParameterClassCollectionNull() {
		servletContextConfigurer.setInitParameterClassCollection(FOO, null, ServletContextConfigurer.Separator.COMMA);

		assertThat(servletContext.getInitParameter(FOO)).isNull();
	}

	@Test
	public void testInitParameterClassCollectionEmpty() {
		servletContextConfigurer.setInitParameterClassCollection(FOO, Collections.<Class<?>>emptyList(), ServletContextConfigurer.Separator.COMMA);

		assertThat(servletContext.getInitParameter(FOO)).isEmpty();
	}

	@Test
	public void testInitParameterClassCollectionSingle() {
		servletContextConfigurer.setInitParameterClassCollection(FOO, Collections.<Class<? extends Void>>singletonList(Void.class), ServletContextConfigurer.Separator.COMMA);

		assertThat(servletContext.getInitParameter(FOO)).isEqualTo("java.lang.Void");
	}

}
