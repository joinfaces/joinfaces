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
import javax.faces.context.FacesContext;
import javax.faces.event.PreDestroyViewMapEvent;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * Implementation of view scope.
 *
 * @author Marcelo Fernandes
 * @author Lars Grefer
 */
@RequiredArgsConstructor
public class ViewScope implements Scope {

	/**
	 * Scope identifier for view scope: "view".
	 */
	public static final String SCOPE_VIEW = "view";

	private final BeanFactory beanFactory;

	@Override
	public Object get(String name, ObjectFactory objectFactory) {
		return getViewRoot()
				.getViewMap()
				.computeIfAbsent(name, k -> objectFactory.getObject());
	}

	@Override
	@Nullable
	public Object remove(String name) {

		UIViewRoot viewRoot = getViewRoot();
		Object bean = viewRoot.getViewMap().remove(name);

		viewRoot
				.getViewListenersForEventClass(PreDestroyViewMapEvent.class)
				.stream()
				.filter(systemEventListener -> systemEventListener instanceof DestructionCallbackWrapper)
				.map(systemEventListener -> (DestructionCallbackWrapper) systemEventListener)
				.filter(destructionCallbackWrapper -> destructionCallbackWrapper.getBeanName().equals(name))
				.findFirst()
				.ifPresent(destructionCallbackWrapper -> {
					viewRoot.unsubscribeFromViewEvent(PreDestroyViewMapEvent.class, destructionCallbackWrapper);
					getSessionHelper().unregister(destructionCallbackWrapper);
				});

		return bean;
	}

	@Override
	public String getConversationId() {
		return getViewRoot().getViewId();
	}

	@Override
	public void registerDestructionCallback(String name, Runnable callback) {
		DestructionCallbackWrapper wrapper = new DestructionCallbackWrapper(name, callback);

		getViewRoot().subscribeToViewEvent(PreDestroyViewMapEvent.class, wrapper);
		getSessionHelper().register(wrapper);
	}

	@Nullable
	@Override
	public Object resolveContextualObject(String key) {
		RequestAttributes attributes = RequestContextHolder.currentRequestAttributes();
		return attributes.resolveReference(key);
	}

	@NonNull
	private UIViewRoot getViewRoot() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext == null) {
			throw new IllegalStateException("No FacesContext found.");
		}

		UIViewRoot viewRoot = facesContext.getViewRoot();
		if (viewRoot == null) {
			throw new IllegalStateException("No ViewRoot found");
		}

		return viewRoot;
	}

	private SessionHelper getSessionHelper() {
		return this.beanFactory.getBean(SessionHelper.class);
	}

}
