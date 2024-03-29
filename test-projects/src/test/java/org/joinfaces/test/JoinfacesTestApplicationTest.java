/*
 * Copyright 2016-2022 the original author or authors.
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

package org.joinfaces.test;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Lars Grefer
 */
@SpringBootTest(
		classes = JoinfacesTestApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class JoinfacesTestApplicationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testHelloFromSpring() {
		ResponseEntity<String> entity = this.restTemplate.getForEntity("/index.xhtml", String.class);

		assertThat(entity.getStatusCode().is2xxSuccessful()).isTrue();

		assertThat(entity.getBody()).contains("Hello from Spring:");
	}
}
