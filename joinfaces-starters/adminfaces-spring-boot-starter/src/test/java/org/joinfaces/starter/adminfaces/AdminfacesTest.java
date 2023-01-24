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

package org.joinfaces.starter.adminfaces;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdminfacesTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
		assertThat(this.webApplicationContext).isNotNull();
	}

	/**
	 * <a href="https://github.com/joinfaces/joinfaces/issues/1628#issuecomment-1386267975">https://github.com/joinfaces/joinfaces/issues/1628#issuecomment-1386267975</a>
	 */
	@Test
	void requestWorks() {
		ResponseEntity<String> response = restTemplate.getForEntity("/index.xhtml", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).contains("Hello Adminfaces");

		ResponseEntity<String> response2 = restTemplate.getForEntity("/foo.xhtml", String.class);
		assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}
}
