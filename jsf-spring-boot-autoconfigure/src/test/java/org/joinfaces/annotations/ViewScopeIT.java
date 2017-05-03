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

package org.joinfaces.annotations;

import org.joinfaces.mock.JsfIT;
import org.junit.Test;

import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = AnnotationConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ViewScopeIT extends JsfIT {

	private static final String KEY = "key";

	@Test
	public void testViewScope() {
		ViewScope viewScope = new ViewScope();

		ViewScopedClassFactory viewScopedClassFactory = new ViewScopedClassFactory();

		Object viewScopedClass = viewScope.get(KEY, viewScopedClassFactory);
		viewScopedClass = viewScope.get(KEY, viewScopedClassFactory);

		assertThat(viewScope.remove(KEY)).isEqualTo(viewScopedClass);
	}

	@Test
	public void testConversationId() {
		ViewScope viewScope = new ViewScope();
		assertThat(viewScope.getConversationId()).isNull();
	}

	@Test
	public void testResolveContextualObject() {
		ViewScope viewScope = new ViewScope();
		assertThat(viewScope.resolveContextualObject(KEY)).isNull();
	}

	@Test
	public void testRegisterDestructionCallback() {
		ViewScope viewScope = new ViewScope();
		viewScope.registerDestructionCallback(KEY, null);
	}
}
