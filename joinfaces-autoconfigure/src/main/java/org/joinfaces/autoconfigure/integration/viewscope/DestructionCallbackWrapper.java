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

import javax.faces.component.UIViewRoot;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.util.Assert;

/**
 * Wrapper around the {@link ViewScope#registerDestructionCallback(String, Runnable) destruction callback} of
 * view scoped beans which acts as listener for {@link javax.faces.event.PreDestroyViewMapEvent view-map destruction} and
 * session destruction.
 *
 * @author Lars Grefer
 * @see ViewScope#registerDestructionCallback(String, Runnable)
 */
@Slf4j
@Getter
class DestructionCallbackWrapper implements SystemEventListener {

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
		doRunCallback(Source.VIEW);
	}

	@Override
	public boolean isListenerForSource(Object source) {
		return source instanceof UIViewRoot;
	}

	void onSessionDestroy() {
		doRunCallback(Source.SESSION);
	}

	private synchronized void doRunCallback(Source source) {
		if (!isCallbackCalled()) {
			log.info(source.getLogPattern(), getBeanName());
			this.callback.run();
			this.callbackCalled = true;
			this.callback = null;
		}
	}

	@Getter
	@RequiredArgsConstructor
	private enum Source {
		VIEW("Calling destruction callbacks for bean {} because the view map is destroyed"),
		SESSION("Calling destruction callbacks for bean {} because the session is destroyed");

		private final String logPattern;
	}
}
