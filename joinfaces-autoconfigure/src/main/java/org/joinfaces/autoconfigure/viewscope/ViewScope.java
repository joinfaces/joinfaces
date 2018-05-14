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

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.lang.NonNull;
import org.springframework.web.context.request.FacesRequestAttributes;

/**
 * Implementation of view scope.
 *
 * @author Marcelo Fernandes
 * @author Lars Grefer
 */
public class ViewScope implements Scope {

	/**
	 * Scope identifier for view scope: "view".
	 */
	public static final String SCOPE_VIEW = "view";

	@Override
	public Object get(String name, ObjectFactory objectFactory) {
		return getViewRoot()
				.getViewMap()
				.computeIfAbsent(name, k -> objectFactory.getObject());
	}

	@Override
	public Object remove(String name) {

		UIViewRoot viewRoot = getViewRoot();
		Object bean = viewRoot.getViewMap().remove(name);

		viewRoot
				.getViewListenersForEventClass(PreDestroyViewMapEvent.class)
				.stream()
				.filter(DestructionCallbackWrapper.class::isInstance)
				.map(DestructionCallbackWrapper.class::cast)
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
		getFacesContext(); //maybe throws an Exception
		return null;
	}

	@Override
	public void registerDestructionCallback(String name, Runnable callback) {
		DestructionCallbackWrapper wrapper = new DestructionCallbackWrapper(name, callback);

		getViewRoot().subscribeToViewEvent(PreDestroyViewMapEvent.class, wrapper);
		getSessionHelper().register(wrapper);
	}

	@Override
	public Object resolveContextualObject(String key) {
		return new FacesRequestAttributes(getFacesContext()).resolveReference(key);
	}

	@NonNull
	private UIViewRoot getViewRoot() {
		UIViewRoot viewRoot = getFacesContext().getViewRoot();
		if (viewRoot == null) {
			throw new IllegalStateException("No ViewRoot found");
		}

		return viewRoot;
	}

	@NonNull
	private FacesContext getFacesContext() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext == null) {
			throw new IllegalStateException("No FacesContext found.");
		}
		return facesContext;
	}

	private SessionHelper getSessionHelper() {
		return (SessionHelper) getFacesContext()
				.getExternalContext()
				.getSessionMap()
				.computeIfAbsent(SessionHelper.class.getName(), k -> new SessionHelper());
	}

}
