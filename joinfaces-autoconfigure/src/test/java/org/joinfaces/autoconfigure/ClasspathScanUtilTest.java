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

package org.joinfaces.autoconfigure;

import java.io.BufferedReader;
import java.io.StringReader;
import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import jakarta.faces.context.FacesContext;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.assertj.core.api.Assertions.assertThat;

class ClasspathScanUtilTest {

	@Test
	void getClasses_empty() {
		Set<Class<?>> classes = ClasspathScanUtil.getClasses(Stream.empty(), getClass().getClassLoader());

		assertThat(classes).isEmpty();
	}

	@Test
	void getClasses_notFound() {
		Set<Class<?>> classes = ClasspathScanUtil.getClasses(Stream.of("foo.Bar"), getClass().getClassLoader());

		assertThat(classes).isEmpty();
	}

	@Test
	void getClasses_notLoadable() {
		Set<Class<?>> classes = ClasspathScanUtil.getClasses(Stream.of("org.springframework.boot.autoconfigure.mustache.MustacheEnvironmentCollector"), getClass().getClassLoader());

		assertThat(classes).isEmpty();
	}

	@Test
	void getClasses_one() {
		Set<Class<?>> classes = ClasspathScanUtil.getClasses(Stream.of(this.getClass().getName()), getClass().getClassLoader());

		assertThat(classes).contains(this.getClass());
	}

	@Test
	void readClassSet() {
		Optional<Set<Class<?>>> classes = ClasspathScanUtil.readClassSet("META-INF/joinfaces/test-list.classes", getClass().getClassLoader());

		assertThat(classes).isPresent();
		assertThat(classes.get()).contains(String.class, FacesContext.class);
	}

	@Test
	void readAnnotationClassSet() {

		String sb = Test.class.getName() + "\n"
				+ ClasspathScanUtil.class.getName() + "\n"
				+ ClasspathScanUtilTest.class.getName() + "\n";
		BufferedReader in = new BufferedReader(new StringReader(sb));

		Set<Class<?>> set = ClasspathScanUtil.readClassSet(in, this.getClass().getClassLoader());

		assertThat(set).contains(Test.class, ClasspathScanUtil.class, ClasspathScanUtilTest.class);
	}


	@Test
	void readClassMap() {
		Optional<Map<Class<? extends Annotation>, Set<Class<?>>>> classes = ClasspathScanUtil.readClassMap("META-INF/joinfaces/test-map.classes", getClass().getClassLoader());

		assertThat(classes).isPresent();
		assertThat(classes.get()).containsKeys(Autowired.class, Qualifier.class);
	}

	@Test
	void readAnnotationClassMap() {

		String sb = "org.junit.jupiter.api.Test=org.joinfaces.autoconfigure.ClasspathScanUtil,org.joinfaces.autoconfigure.ClasspathScanUtilTest\n";
		sb += "non.loadable.Annotation=org.joinfaces.autoconfigure.ClasspathScanUtil\n";
		sb += "org.junit.jupiter.api.BeforeEach=\n";
		sb += "org.junit.jupiter.api.BeforeAll=non.loadable.Class,org.joinfaces.autoconfigure.ClasspathScanUtil\n";

		BufferedReader in = new BufferedReader(new StringReader(sb));

		Map<Class<? extends Annotation>, Set<Class<?>>> map = ClasspathScanUtil.readClassMap(in, this.getClass().getClassLoader());

		assertThat(map).containsKey(Test.class);
		assertThat(map).containsKey(BeforeEach.class);
		assertThat(map).containsKey(BeforeAll.class);
		assertThat(map).hasSize(3);

		assertThat(map.get(Test.class)).contains(ClasspathScanUtil.class, ClasspathScanUtilTest.class);
		assertThat(map.get(BeforeEach.class)).isEmpty();
		assertThat(map.get(BeforeAll.class)).containsExactly(ClasspathScanUtil.class);
	}
}
