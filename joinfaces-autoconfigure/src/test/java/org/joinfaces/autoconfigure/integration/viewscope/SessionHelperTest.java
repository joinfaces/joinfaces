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

package org.joinfaces.autoconfigure.integration.viewscope;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.verify;
import static org.mockito.BDDMockito.when;

public class SessionHelperTest {

	private SessionHelper sessionHelper;

	@Before
	public void setUp() {
		this.sessionHelper = new SessionHelper();
	}

	@Test
	public void testRegisterUnregister() {
		DestructionCallbackWrapper mock = mock(DestructionCallbackWrapper.class);
		this.sessionHelper.register(mock);

		assertThat(this.sessionHelper.getDestructionCallbackWrappers()).containsExactly(mock);

		this.sessionHelper.unregister(mock);
		assertThat(this.sessionHelper.getDestructionCallbackWrappers()).isEmpty();
	}

	@Test
	public void testDestroy() {
		DestructionCallbackWrapper mock1 = mock(DestructionCallbackWrapper.class);
		DestructionCallbackWrapper mock2 = mock(DestructionCallbackWrapper.class);

		this.sessionHelper.register(mock1);
		this.sessionHelper.register(mock2);

		assertThat(this.sessionHelper.getDestructionCallbackWrappers()).containsExactly(mock1, mock2);

		this.sessionHelper.destroy();

		verify(mock1).onSessionDestroy();
		verify(mock2).onSessionDestroy();
	}

	@Test
	public void testCleanup() {
		DestructionCallbackWrapper mock1 = mock(DestructionCallbackWrapper.class);
		DestructionCallbackWrapper mock2 = mock(DestructionCallbackWrapper.class);

		this.sessionHelper.register(mock1);
		this.sessionHelper.register(mock2);

		assertThat(this.sessionHelper.getDestructionCallbackWrappers()).containsExactly(mock1, mock2);

		when(mock1.isCallbackCalled()).thenReturn(true);
		this.sessionHelper.cleanup();
		assertThat(this.sessionHelper.getDestructionCallbackWrappers()).containsExactly(mock2);

	}
}
