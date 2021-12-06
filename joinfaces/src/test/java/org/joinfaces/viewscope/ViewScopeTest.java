/*
 * Copyright 2016-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.joinfaces.viewscope;

import java.util.concurrent.atomic.AtomicInteger;

import jakarta.faces.component.UIViewRoot;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PreDestroyViewMapEvent;

import org.joinfaces.test.mock.JsfIT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.ObjectFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.doCallRealMethod;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.when;

public class ViewScopeTest extends JsfIT {

	private static final String KEY = "key";

	private ViewScope viewScope;

	@BeforeEach
	public void setUp() {
		this.viewScope = new ViewScope();
	}

	@Test
	public void testGet() {

		AtomicInteger count = new AtomicInteger(0);
		ObjectFactory<?> objectFactory = (ObjectFactory<Object>) () -> {
			count.incrementAndGet();
			return new Object();
		};

		Object bean = this.viewScope.get(KEY, objectFactory);
		assertThat(count).hasValue(1);

		bean = this.viewScope.get(KEY, objectFactory);
		assertThat(count).hasValue(1);

		assertThat(this.viewScope.remove(KEY)).isEqualTo(bean);
	}

	@Test
	public void testConversationId() {
		assertThat(this.viewScope.getConversationId()).isNull();
	}

	@Test
	public void testResolveContextualObject() {
		assertThat(this.viewScope.resolveContextualObject(KEY)).isNull();
	}


	@Test
	public void testRegisterDestructionCallback() {
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
		doCallRealMethod().when(viewRoot).subscribeToViewEvent(any(), any());
		doCallRealMethod().when(viewRoot).unsubscribeFromViewEvent(any(), any());
		when(viewRoot.getViewListenersForEventClass(any())).thenCallRealMethod();

		this.viewScope.registerDestructionCallback(KEY, () -> {
		});

		this.viewScope.remove(KEY);
	}

	@Test
	public void processEvent() {
		PreDestroyViewMapEvent event = mock(PreDestroyViewMapEvent.class);
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
		when(event.getSource()).thenReturn(viewRoot);
		this.viewScope.getPreDestroyViewMapListener().processEvent(event);
	}

	@Test
	public void isListenerForSource() {
		assertThat(this.viewScope.getPreDestroyViewMapListener().isListenerForSource(mock(UIViewRoot.class))).isTrue();
		assertThat(this.viewScope.getPreDestroyViewMapListener().isListenerForSource(new Object())).isFalse();
	}
}
