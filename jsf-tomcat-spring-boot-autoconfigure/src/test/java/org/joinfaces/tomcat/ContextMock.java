/*
 * Copyright 2016-2016 the original author or authors.
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

package org.joinfaces.tomcat;

import lombok.Getter;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.WebResourceSet;
import org.mockito.Matchers;
import org.mockito.Mockito;

@Getter
public class ContextMock {

	private Context standardContext;

	private WebResourceRoot webResourceRoot;

	private CalledAnswer calledAnswer;

	public ContextMock()  {
		this.standardContext = Mockito.mock(Context.class);
		this.webResourceRoot = Mockito.mock(WebResourceRoot.class);
		LifecycleState state = LifecycleState.NEW;
		Mockito.when(this.webResourceRoot.getContext())
			.thenReturn(this.standardContext);
		Mockito.when(this.webResourceRoot.getState())
			.thenReturn(state);
		Mockito.when(this.standardContext.getResources())
			.thenReturn(this.webResourceRoot);
		Mockito.when(this.standardContext.getAddWebinfClassesResources())
			.thenReturn(Boolean.FALSE);
	}

	public void init(WebResourceSet... array) throws LifecycleException {
		Mockito.when(this.webResourceRoot.getJarResources()).thenReturn(array);

		this.calledAnswer = new CalledAnswer();

		Mockito.doAnswer(this.calledAnswer)
			.when(this.webResourceRoot)
			.createWebResourceSet(Matchers.any(WebResourceRoot.ResourceSetType.class),
				Matchers.any(String.class), Matchers.any(String.class),
				Matchers.any(String.class), Matchers.any(String.class));
	}
}
