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

package org.joinfaces.autoconfigure.viewscope;

import javax.faces.component.UIViewRoot;
import javax.faces.event.PreDestroyViewMapEvent;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.verify;

public class DestructionCallbackWrapperTest {

	private DestructionCallbackWrapper destructionCallbackWrapper;
	private Runnable callback;

	@Before
	public void setUp() {
		this.callback = mock(Runnable.class);
		this.destructionCallbackWrapper = new DestructionCallbackWrapper("bean", this.callback);
	}

	@Test
	public void processEvent() {
		this.destructionCallbackWrapper.processEvent(mock(PreDestroyViewMapEvent.class));

		verify(this.callback).run();
		assertThat(this.destructionCallbackWrapper.isCallbackCalled()).isTrue();
		assertThat(this.destructionCallbackWrapper.getCallback()).isNull();
	}

	@Test
	public void isListenerForSource() {
		assertThat(this.destructionCallbackWrapper.isListenerForSource(mock(UIViewRoot.class))).isTrue();
		assertThat(this.destructionCallbackWrapper.isListenerForSource(new Object())).isFalse();
	}

	@Test
	public void onSessionDestroy() {
		this.destructionCallbackWrapper.onSessionDestroy();

		verify(this.callback).run();
		assertThat(this.destructionCallbackWrapper.isCallbackCalled()).isTrue();
		assertThat(this.destructionCallbackWrapper.getCallback()).isNull();
	}

	@Test
	public void testRunCalledOnlyOnce() {
		this.destructionCallbackWrapper.processEvent(mock(PreDestroyViewMapEvent.class));
		this.destructionCallbackWrapper.onSessionDestroy();
		verify(this.callback).run();

	}
}
