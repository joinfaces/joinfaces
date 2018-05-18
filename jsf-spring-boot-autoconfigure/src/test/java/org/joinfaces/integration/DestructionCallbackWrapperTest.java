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

public class DestructionCallbackWrapperTest {

	private ViewScope.DestructionCallbackWrapper destructionCallbackWrapper;
	private Runnable callback;

	@Before
	public void setUp() {
		this.callback = Mockito.mock(Runnable.class);
		this.destructionCallbackWrapper = new ViewScope.DestructionCallbackWrapper("bean", this.callback);
	}

	@Test
	public void onSessionDestroy() {
		assertThat(this.destructionCallbackWrapper.isCallbackCalled()).isFalse();
		this.destructionCallbackWrapper.onSessionDestroy();
		Mockito.verify(this.callback).run();
		assertThat(this.destructionCallbackWrapper.isCallbackCalled()).isTrue();
	}

	@Test
	public void testRunCalledOnlyOnce() {
		this.destructionCallbackWrapper.onViewDestroy();
		this.destructionCallbackWrapper.onSessionDestroy();
		Mockito.verify(this.callback).run();
	}
}
