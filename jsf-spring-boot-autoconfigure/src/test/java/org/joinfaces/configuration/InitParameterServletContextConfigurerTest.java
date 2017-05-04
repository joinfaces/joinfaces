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

import java.lang.reflect.Field;
import java.util.List;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Lars Grefer
 */
public class InitParameterServletContextConfigurerTest {

	@Test
	public void testResolveCollectionTypeRaw() throws NoSuchFieldException {
		Field listRawClass = InitParameterServletContextConfigurerTest.class.getDeclaredField("listRawClass");

		Class<?> type = InitParameterServletContextConfigurer.resolveCollectionItemType(listRawClass);

		assertThat(type).isEqualTo(Class.class);
	}

	@Test
	public void testResolveCollectionTypeGeneric() throws NoSuchFieldException {
		Field listGenericClass = InitParameterServletContextConfigurerTest.class.getDeclaredField("listGenericClass");

		Class<?> type = InitParameterServletContextConfigurer.resolveCollectionItemType(listGenericClass);

		assertThat(type).isEqualTo(Class.class);
	}

	@Test
	public void testRawList() throws NoSuchFieldException {
		Field listRaw = InitParameterServletContextConfigurerTest.class.getDeclaredField("listRaw");

		Class<?> type = InitParameterServletContextConfigurer.resolveCollectionItemType(listRaw);

		assertThat(type).isEqualTo(Object.class);
	}

	@SuppressWarnings("unused")
	private List listRaw;
	@SuppressWarnings("unused")
	private List<Class> listRawClass;
	@SuppressWarnings("unused")
	private List<Class<?>> listGenericClass;

}
