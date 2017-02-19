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

package org.joinfaces.configuration;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;

import org.springframework.mock.web.MockServletContext;

/**
 * @author Lars Grefer
 */
public class ReflectiveServletContextInitializerTest {

	private ReflectiveServletContextInitializer<Object> servletContextInitializer;

	@Before
	public void setUp() {
		this.servletContextInitializer = new ReflectiveServletContextInitializer<Object>(new Object());
	}

	@Test
	public void onStartup() throws ServletException {
		MockServletContext servletContext = new MockServletContext();
		this.servletContextInitializer.onStartup(servletContext);
	}

}
