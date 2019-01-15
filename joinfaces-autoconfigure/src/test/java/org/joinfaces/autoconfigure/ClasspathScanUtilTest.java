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

package org.joinfaces.autoconfigure;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	void getClasses_one() {
		Set<Class<?>> classes = ClasspathScanUtil.getClasses(Stream.of(this.getClass().getName()), getClass().getClassLoader());

		assertThat(classes).contains(this.getClass());
	}

	@Test
	void readAnnotationClassSet() throws IOException {

		String sb = Test.class.getName() + "\n"
				+ ClasspathScanUtil.class.getName() + "\n"
				+ ClasspathScanUtilTest.class.getName() + "\n";
		ByteArrayInputStream in = new ByteArrayInputStream(sb.getBytes(StandardCharsets.UTF_8));

		Set<Class<?>> set = ClasspathScanUtil.readAnnotationClassSet(in, this.getClass().getClassLoader());

		assertThat(set).contains(Test.class, ClasspathScanUtil.class, ClasspathScanUtilTest.class);
	}

	@Test
	void readAnnotationClassMap() throws IOException {

		String sb = "org.junit.jupiter.api.Test=org.joinfaces.autoconfigure.ClasspathScanUtil,org.joinfaces.autoconfigure.ClasspathScanUtilTest\n";
		sb += "non.loadable.Annotation=org.joinfaces.autoconfigure.ClasspathScanUtil\n";
		sb += "org.junit.jupiter.api.BeforeEach=\n";
		sb += "org.junit.jupiter.api.BeforeAll=non.loadable.Class,org.joinfaces.autoconfigure.ClasspathScanUtil\n";

		ByteArrayInputStream in = new ByteArrayInputStream(sb.getBytes(StandardCharsets.UTF_8));

		Map<Class<? extends Annotation>, Set<Class<?>>> map = ClasspathScanUtil.readAnnotationClassMap(in, this.getClass().getClassLoader());

		assertThat(map).containsKey(Test.class);
		assertThat(map).containsKey(BeforeEach.class);
		assertThat(map).containsKey(BeforeAll.class);
		assertThat(map).hasSize(3);

		assertThat(map.get(Test.class)).contains(ClasspathScanUtil.class, ClasspathScanUtilTest.class);
		assertThat(map.get(BeforeEach.class)).isEmpty();
		assertThat(map.get(BeforeAll.class)).containsExactly(ClasspathScanUtil.class);
	}
}
