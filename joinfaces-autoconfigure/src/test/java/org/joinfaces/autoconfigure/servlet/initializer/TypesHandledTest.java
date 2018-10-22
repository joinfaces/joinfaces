/*
 * Copyright 2016-2018 the original author or authors.
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

package org.joinfaces.autoconfigure.servlet.initializer;


import javax.servlet.annotation.HandlesTypes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.when;

public class TypesHandledTest {

	private HandlesTypes handlesTypes;

	@BeforeEach
	public void setUp() {
		this.handlesTypes = mock(HandlesTypes.class);
	}

	@Test
	public void testNull() {
		JsfClassFactory.TypesHandled typesHandled = new JsfClassFactory.TypesHandled(null, null);

		assertThat(typesHandled.isEmpty()).isTrue();
	}

	@Test
	public void testEmpty() {
		when(this.handlesTypes.value()).thenReturn(new Class[0]);

		JsfClassFactory.TypesHandled typesHandled = new JsfClassFactory.TypesHandled(this.handlesTypes, null);

		assertThat(typesHandled.isEmpty()).isTrue();
	}
}
