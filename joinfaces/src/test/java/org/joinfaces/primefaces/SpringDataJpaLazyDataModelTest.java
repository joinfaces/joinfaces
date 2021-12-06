/*
 * Copyright 2016-2021 the original author or authors.
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

package org.joinfaces.primefaces;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SpringDataJpaLazyDataModelTest {

	@Autowired
	private PersonRepository personRepository;

	@BeforeAll
	void initData() {
		personRepository.saveAndFlush(new Person());
	}

	private SpringDataJpaLazyDataModel<Person, UUID, PersonRepository> dataModel;

	@BeforeEach
	void init() {
		dataModel = new SpringDataJpaLazyDataModel<>(personRepository);
		//dataModel.setPrimaryKeyExtractor(Person::getPk);
		dataModel.setPrimaryKeyDeserializer(UUID::fromString);
	}

	@Test
	void testData() {

		List<Person> load = dataModel.load(0, 10, Map.of(), Map.of());

		assertThat(load).isNotEmpty();
	}
	@Test
	void testRowKeys() {

		List<Person> load = dataModel.load(0, 10, Map.of(), Map.of());

		assertThat(load).isNotEmpty();

		for (Person person : load) {
			String rowKey = dataModel.getRowKey(person);

			Person rowData = dataModel.getRowData(rowKey);
			assertThat(rowData).isNotNull();
		}

		assertThat(dataModel.getIdClass()).isEqualTo(UUID.class);
	}

	@AfterAll
	void clearData() {
		personRepository.deleteAll();
	}

}
