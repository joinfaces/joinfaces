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

package org.joinfaces.myfaces;

import jakarta.faces.context.ExternalContext;
import jakarta.servlet.ServletContext;

import org.junit.jupiter.api.BeforeEach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public abstract class MyFacesSpiTest {

	@Autowired
	private ServletContext servletContext;

	protected ExternalContext externalContext;

	@BeforeEach
	void init() {
		this.externalContext = mock(ExternalContext.class);
		when(this.externalContext.getContext()).thenReturn(this.servletContext);
	}
}
