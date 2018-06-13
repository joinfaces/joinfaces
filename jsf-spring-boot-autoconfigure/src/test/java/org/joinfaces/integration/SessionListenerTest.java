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

package org.joinfaces.integration;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionListenerTest {

	private ViewScope.SessionListener sessionListener;

	@Before
	public void setUp() {
		this.sessionListener = new ViewScope.SessionListener();
	}

	@Test
	public void testRegisterUnregister() {
		ViewScope.DestructionCallbackWrapper mock = Mockito.mock(ViewScope.DestructionCallbackWrapper.class);
		this.sessionListener.register(mock);

		assertThat(this.sessionListener.getDestructionCallbackWrappers()).containsExactly(mock);

		this.sessionListener.unregister(mock);
		assertThat(this.sessionListener.getDestructionCallbackWrappers()).isEmpty();
	}

	@Test
	public void testDestroy() {
		ViewScope.DestructionCallbackWrapper mock1 = Mockito.mock(ViewScope.DestructionCallbackWrapper.class);
		ViewScope.DestructionCallbackWrapper mock2 = Mockito.mock(ViewScope.DestructionCallbackWrapper.class);

		this.sessionListener.register(mock1);
		this.sessionListener.register(mock2);

		assertThat(this.sessionListener.getDestructionCallbackWrappers()).containsExactly(mock1, mock2);

		this.sessionListener.valueUnbound(null);

		Mockito.verify(mock1).onSessionDestroy();
		Mockito.verify(mock2).onSessionDestroy();
	}

	@Test
	public void testCleanup() {
		ViewScope.DestructionCallbackWrapper mock1 = Mockito.mock(ViewScope.DestructionCallbackWrapper.class);
		ViewScope.DestructionCallbackWrapper mock2 = Mockito.mock(ViewScope.DestructionCallbackWrapper.class);

		this.sessionListener.register(mock1);
		this.sessionListener.register(mock2);

		assertThat(this.sessionListener.getDestructionCallbackWrappers()).containsExactly(mock1, mock2);

		Mockito.when(mock1.isCallbackCalled()).thenReturn(true);
		this.sessionListener.cleanup();
		assertThat(this.sessionListener.getDestructionCallbackWrappers()).containsExactly(mock2);
	}
}
