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

import java.util.LinkedList;
import java.util.List;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.PreDestroyViewMapEvent;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.web.context.request.FacesRequestAttributes;

/**
 * Implementation of view scope.
 *
 * @author Marcelo Fernandes
 * @author Lars Grefer
 */
@Slf4j
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
					getSessionListener().unregister(destructionCallbackWrapper);
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
		getSessionListener().register(wrapper);
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

	private SessionListener getSessionListener() {
		return (SessionListener) getFacesContext()
				.getExternalContext()
				.getSessionMap()
				.computeIfAbsent(SessionListener.class.getName(), k -> new SessionListener());
	}

	/**
	 * This class acts as "session-destroyed" listener, which call the destruction callbacks
	 * of all view scoped beans that have not been destructed yet.
	 *
	 * @author Lars Grefer
	 * @see org.springframework.web.context.request.DestructionCallbackBindingListener
	 */
	@Getter
	public static class SessionListener implements HttpSessionBindingListener {

		private List<DestructionCallbackWrapper> destructionCallbackWrappers = new LinkedList<>();

		void register(DestructionCallbackWrapper destructionCallbackWrapper) {
			cleanup();
			this.destructionCallbackWrappers.add(destructionCallbackWrapper);
		}

		void unregister(DestructionCallbackWrapper destructionCallbackWrapper) {
			cleanup();
			this.destructionCallbackWrappers.remove(destructionCallbackWrapper);
		}

		synchronized void cleanup() {
			this.destructionCallbackWrappers.removeIf(DestructionCallbackWrapper::isCallbackCalled);
		}

		@Override
		public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
		}

		@Override
		public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
			this.destructionCallbackWrappers.forEach(DestructionCallbackWrapper::onSessionDestroy);
		}
	}

	/**
	 * Wrapper around the {@link ViewScope#registerDestructionCallback(String, Runnable) destruction callback} of
	 * view scoped beans which acts as listener for {@link PreDestroyViewMapEvent view-map destruction} and
	 * session destruction.
	 *
	 * @author Lars Grefer
	 * @see #registerDestructionCallback(String, Runnable)
	 */
	@Getter
	public static class DestructionCallbackWrapper implements SystemEventListener {

		private final String beanName;

		private Runnable callback;
		private boolean callbackCalled;

		DestructionCallbackWrapper(String beanName, Runnable callback) {
			Assert.hasText(beanName, "beanName must not be null or empty");
			Assert.notNull(callback, "callback must not be null");
			this.beanName = beanName;
			this.callback = callback;
		}

		@Override
		public void processEvent(SystemEvent systemEvent) throws AbortProcessingException {
			doRunCallback(false);
		}

		@Override
		public boolean isListenerForSource(Object source) {
			return source instanceof UIViewRoot;
		}

		void onSessionDestroy() {
			doRunCallback(true);
		}

		private synchronized void doRunCallback(boolean session) {
			if (!isCallbackCalled()) {
				log.debug("Calling destruction callbacks for bean {} because the {} is destroyed", getBeanName(), session ? "session" : "view map");
				this.callback.run();
				this.callbackCalled = true;
				this.callback = null;
			}
		}
	}
}
