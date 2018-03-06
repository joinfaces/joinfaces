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

package org.joinfaces.autoconfigure.omnifaces;

import java.util.Set;

import javax.servlet.annotation.HandlesTypes;

import org.joinfaces.autoconfigure.JsfClassFactory;
import org.joinfaces.autoconfigure.JsfClassFactoryConfiguration;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omnifaces.facesviews.FacesViewsInitializer;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class OmnifacesServletContextInitializerIT {

	private static Set<Class<?>> classes;

	@BeforeClass
	public static void setupClasses() {
		JsfClassFactoryConfiguration configuration = JsfClassFactoryConfiguration.builder()
				.excludeScopedAnnotations(true)
				.anotherFacesConfig(null)
				.handlesTypes(AnnotationUtils.findAnnotation(FacesViewsInitializer.class, HandlesTypes.class))
				.build();

		classes = new JsfClassFactory(configuration).getAllClasses();
	}

	@Test
	public void testEmpty() {
		assertThat(this.classes).isEmpty();
	}

}
