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

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Lars Grefer
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ListPropertiesAutoConfiguration.class)
public class ListPropertiesTest {

	@Autowired
	private ListProperties listProperties;

	@Test
	public void testPropertiesListAProperties() {
		assertThat(listProperties.getListA()).containsExactly("foo", "bar");
	}

	@Test
	public void testPropertiesListBProperties() {
		assertThat(listProperties.getListB()).containsExactly("foo", "bar");
	}

	@Test
	public void testYamlListCProperties() {
		assertThat(listProperties.getListC()).containsExactly("foo", "bar");
	}

	@Test
	public void testYamlListDProperties() {
		assertThat(listProperties.getListD()).containsExactly("foo", "bar");
	}
}
