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

import javax.faces.event.PreDestroyViewMapEvent;

import org.joinfaces.test.mock.JsfIT;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.verify;
import static org.mockito.BDDMockito.when;

public class PreDestroyViewMapListenerTest extends JsfIT {

	private ViewScope.PreDestroyViewMapListener preDestroyViewMapListener;

	@Before
	public void setUp() {
		ViewScope viewScope = new ViewScope();

		this.preDestroyViewMapListener = viewScope.getPreDestroyViewMapListener();
	}

	@Test
	public void testProcessEvent() {
		PreDestroyViewMapEvent event = mock(PreDestroyViewMapEvent.class);

		when(event.getSource()).thenReturn(getJsfMock().getMockViewRoot());
		when(getJsfMock().getMockViewRoot().getViewMap(false))
				.thenReturn(getJsfMock().getMockViewMap());

		Runnable runnable = mock(Runnable.class);
		ViewScope.DestructionCallbackWrapper destructionCallbackWrapper = new ViewScope.DestructionCallbackWrapper(
				"foo", runnable
		);

		getJsfMock().getMockViewMap()
				.put(ViewScope.DESTRUCTION_CALLBACK_NAME_PREFIX + "foo", destructionCallbackWrapper);

		this.preDestroyViewMapListener.processEvent(event);

		verify(runnable).run();
	}
}
