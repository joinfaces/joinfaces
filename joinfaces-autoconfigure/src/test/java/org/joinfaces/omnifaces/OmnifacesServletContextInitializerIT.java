/*
 * Copyright 2016-2016 the original author or authors.
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

package org.joinfaces.omnifaces;

import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.joinfaces.JsfClassFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class OmnifacesServletContextInitializerIT {

	private static Set<Class<?>> classes;

	@BeforeClass
	public static void setupClasses() {
		OmnifacesServletContextInitializer configuration = new OmnifacesServletContextInitializer();

		classes = new JsfClassFactory(configuration).getAllClasses();
	}

	@Test
	public void testEmpty() {
		assertThat(this.classes).isEmpty();
	}

	@Test
	public void testOnStartup() throws ServletException {
		OmnifacesServletContextInitializer omnifacesServletContextInitializer
			= new OmnifacesServletContextInitializer();

		ServletContext servletContext = new MockServletContext();

		omnifacesServletContextInitializer.onStartup(servletContext);
	}

	@Test
	public void testAnotherFacesConfig() throws ServletException {
		OmnifacesServletContextInitializer omnifacesServletContextInitializer
			= new OmnifacesServletContextInitializer();

		assertThat(omnifacesServletContextInitializer.getAnotherFacesConfig()).isNull();
	}

	@Test
	public void testExcludeScopedAnnotations() throws ServletException {
		OmnifacesServletContextInitializer omnifacesServletContextInitializer
			= new OmnifacesServletContextInitializer();

		assertThat(omnifacesServletContextInitializer.isExcludeScopedAnnotations()).isTrue();
	}

}
