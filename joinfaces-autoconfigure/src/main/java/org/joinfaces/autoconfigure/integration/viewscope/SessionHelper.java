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

package org.joinfaces.autoconfigure.integration.viewscope;

import java.util.LinkedList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;

import org.springframework.beans.factory.DisposableBean;


/**
 * This class acts as "session-destroyed" listener, which call the destruction callbacks
 * of all view scoped beans that have not been destructed yet.
 *
 * @author Lars Grefer
 */
@Getter(AccessLevel.PACKAGE)
class SessionHelper implements DisposableBean {

	private List<DestructionCallbackWrapper> destructionCallbackWrappers = new LinkedList<>();

	@Override
	public void destroy() {
		this.destructionCallbackWrappers.forEach(DestructionCallbackWrapper::onSessionDestroy);
	}

	public void register(DestructionCallbackWrapper destructionCallbackWrapper) {
		cleanup();
		this.destructionCallbackWrappers.add(destructionCallbackWrapper);
	}

	public void unregister(DestructionCallbackWrapper destructionCallbackWrapper) {
		cleanup();
		this.destructionCallbackWrappers.remove(destructionCallbackWrapper);
	}

	synchronized void cleanup() {
		this.destructionCallbackWrappers.removeIf(DestructionCallbackWrapper::isCallbackCalled);
	}
}
