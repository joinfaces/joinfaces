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

package org.joinfaces.test.mock;

import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Lars Grefer
 */
public class MockMethodMetadataTest {

	private MockMethodMetadata mockMethodMetadata;

	@Before
	public void setUp() {
		this.mockMethodMetadata = new MockMethodMetadata(Autowired.class);
	}

	@Test
	public void testGetMethodName() {
		assertThat(this.mockMethodMetadata.getMethodName()).isNull();
	}

	@Test
	public void testGetDeclaringClassName() {
		assertThat(this.mockMethodMetadata.getDeclaringClassName()).isNull();
	}

	@Test
	public void testGetReturnTypeName() {
		assertThat(this.mockMethodMetadata.getReturnTypeName()).isNull();
	}

	@Test
	public void testIsAbstract() {
		assertThat(this.mockMethodMetadata.isAbstract()).isFalse();
	}

	@Test
	public void testIsStatic() {
		assertThat(this.mockMethodMetadata.isStatic()).isFalse();
	}

	@Test
	public void testIsFinal() {
		assertThat(this.mockMethodMetadata.isFinal()).isFalse();
	}

	@Test
	public void testIsOverridable() {
		assertThat(this.mockMethodMetadata.isOverridable()).isFalse();
	}

	@Test
	public void testIsAnnotated() {
		assertThat(this.mockMethodMetadata.isAnnotated(null)).isFalse();
		assertThat(this.mockMethodMetadata.isAnnotated(Autowired.class.getName())).isTrue();
		assertThat(this.mockMethodMetadata.isAnnotated("foo")).isFalse();
	}

	@Test
	public void testGetAnnotationAttributes_1() {
		assertThat(this.mockMethodMetadata.getAnnotationAttributes(null)).isNull();
	}

	@Test
	public void testGetAnnotationAttributes_2() {
		assertThat(this.mockMethodMetadata.getAnnotationAttributes(null, false)).isNull();
	}

	@Test
	public void testGetAllAnnotationAttributes_1() {
		assertThat(this.mockMethodMetadata.getAllAnnotationAttributes(null)).isNull();
	}

	@Test
	public void testGetAllAnnotationAttributes_2() {
		assertThat(this.mockMethodMetadata.getAllAnnotationAttributes(null, false)).isNull();
	}

}
