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

import java.lang.annotation.Annotation;
import java.util.Map;

import org.springframework.core.type.MethodMetadata;
import org.springframework.util.MultiValueMap;

/**
 * {@link MethodMetadata}-Mock.
 *
 * @author Nurettin Yilmaz
 */
public class MockMethodMetadata implements MethodMetadata {

	private final Class<? extends Annotation> annotation;

	public MockMethodMetadata(Class<? extends Annotation> annotation) {
		this.annotation = annotation;
	}

	@Override
	public String getMethodName() {
		return null;
	}

	@Override
	public String getDeclaringClassName() {
		return null;
	}

	@Override
	public String getReturnTypeName() {
		return null;
	}

	@Override
	public boolean isAbstract() {
		return false;
	}

	@Override
	public boolean isStatic() {
		return false;
	}

	@Override
	public boolean isFinal() {
		return false;
	}

	@Override
	public boolean isOverridable() {
		return false;
	}

	@Override
	public boolean isAnnotated(String annotationName) {
		return this.annotation.getName().equals(annotationName);
	}

	@Override
	public Map<String, Object> getAnnotationAttributes(String annotationName) {
		return null;
	}

	@Override
	public Map<String, Object> getAnnotationAttributes(String annotationName, boolean classValuesAsString) {
		return null;
	}

	@Override
	public MultiValueMap<String, Object> getAllAnnotationAttributes(String annotationName) {
		return null;
	}

	@Override
	public MultiValueMap<String, Object> getAllAnnotationAttributes(String annotationName, boolean classValuesAsString) {
		return null;
	}
}
