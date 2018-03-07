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

import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PreDestroyViewMapEvent;

import org.joinfaces.test.mock.JsfIT;
import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.doCallRealMethod;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.verify;
import static org.mockito.BDDMockito.when;

public class ViewScopeTest extends JsfIT {

	private static final String KEY = "key";

	private BeanFactory beanFactory;

	private ViewScope viewScope;

	@Before
	public void setUp() {
		this.beanFactory = mock(BeanFactory.class);
		this.viewScope = new ViewScope(this.beanFactory);
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
		SessionHelper sessionHelper = mock(SessionHelper.class);
		when(this.beanFactory.getBean(SessionHelper.class)).thenReturn(sessionHelper);

		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
		doCallRealMethod().when(viewRoot).subscribeToViewEvent(any(), any());
		doCallRealMethod().when(viewRoot).unsubscribeFromViewEvent(any(), any());
		when(viewRoot.getViewListenersForEventClass(any())).thenCallRealMethod();

		this.viewScope.registerDestructionCallback(KEY, () -> { });

		verify(viewRoot)
				.subscribeToViewEvent(eq(PreDestroyViewMapEvent.class), any());

		when(viewRoot.getListenersForEventClass(PreDestroyViewMapEvent.class))
				.thenReturn(Collections.singletonList(new DestructionCallbackWrapper(KEY, () -> { })));
		this.viewScope.remove(KEY);

		verify(viewRoot)
				.unsubscribeFromViewEvent(eq(PreDestroyViewMapEvent.class), any());
	}
}
