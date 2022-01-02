/*
 * Copyright 2016-2022 the original author or authors.
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

package org.joinfaces.autoconfigure.icefaces;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.faces.context.FacesContext;

import lombok.extern.slf4j.Slf4j;
import org.icefaces.impl.application.WindowScopeManager;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.lang.NonNull;

/**
 * {@link Scope Spring scope} implementation which delegates to ICEfaces' {@link WindowScopeManager}.
 *
 * @author Lars Grefer
 */
@Slf4j
@ParametersAreNonnullByDefault
public class WindowScope implements Scope {

	@SuppressWarnings("unchecked")
	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {

		WindowScopeManager.ScopeMap scopeMap = getScopeMap();

		Object bean = scopeMap.get(name);

		if (bean == null) {
			bean = objectFactory.getObject();
			scopeMap.put(name, bean);
		}

		return bean;
	}

	@Override
	public Object remove(String name) {
		return getScopeMap().remove(name);
	}

	@Override
	public void registerDestructionCallback(String name, Runnable runnable) {
		log.warn("Destruction callback for bean {} might not be executed", name);
	}

	@Override
	public Object resolveContextualObject(String name) {
		getScopeMap(); //Maybe throws an exception
		return null;
	}

	@Override
	public String getConversationId() {
		return getScopeMap().getId();
	}

	@NonNull
	WindowScopeManager.ScopeMap getScopeMap() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext == null) {
			throw new IllegalStateException("No FacesContext found");
		}

		WindowScopeManager.ScopeMap scopeMap = WindowScopeManager.lookupWindowScope(facesContext);
		if (scopeMap == null) {
			throw new IllegalStateException("No ScopeMap found");
		}
		return scopeMap;
	}

}
