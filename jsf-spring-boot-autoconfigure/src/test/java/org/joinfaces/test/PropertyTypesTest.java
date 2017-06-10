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

package org.joinfaces.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Lars Grefer
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
		properties = "spring.profiles.active=propertyTest",
		webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
public class PropertyTypesTest {

	private static final String FOO = "foo";
	private static final String BAR = "bar";

	@Autowired
	private TestProperties testProperties;

	@Test
	public void testPropertiesListAProperties() {
		assertThat(this.testProperties.getListA()).containsExactly(FOO, BAR);
	}

	@Test
	public void testPropertiesListBProperties() {
		assertThat(this.testProperties.getListB()).containsExactly(FOO, BAR);
	}

	@Test
	public void testYamlListCProperties() {
		assertThat(this.testProperties.getListC()).containsExactly(FOO, BAR);
	}

	@Test
	public void testYamlListDProperties() {
		assertThat(this.testProperties.getListD()).containsExactly(FOO, BAR);
	}

	@Test
	public void textCharSequenceClassProperty() {
		assertThat(this.testProperties.getCharSequenceClass()).isNotNull();
		assertThat(this.testProperties.getCharSequenceClass()).isEqualTo(String.class);
	}

	@Test
	public void testClassProperty() {
		assertThat(this.testProperties.getRandomClass()).isNotNull();
		assertThat(this.testProperties.getRandomClass()).isEqualTo(Void.class);
	}

	@Test
	public void testClassListProperty() {
		assertThat(this.testProperties.getClassList()).containsExactly(String.class, Void.class, List.class);
	}

	@TestConfiguration
	@EnableConfigurationProperties(TestProperties.class)
	public static class TestPropertiesAutoConfiguration {
	}
}
