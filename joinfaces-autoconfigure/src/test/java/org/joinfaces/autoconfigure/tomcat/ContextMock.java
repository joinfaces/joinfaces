/*
 * Copyright 2016-2016 the original author or authors.
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

package org.joinfaces.autoconfigure.tomcat;

import lombok.Getter;
import org.apache.catalina.Context;
import org.apache.catalina.WebResourceSet;
import org.mockito.Mockito;

@Getter
public class ContextMock {

	private Context standardContext;

	private MockWebResourceRoot webResourceRoot;

	public ContextMock()  {
		this.standardContext = Mockito.mock(Context.class);
		this.webResourceRoot = new MockWebResourceRoot();
		Mockito.when(this.standardContext.getResources())
			.thenReturn(this.webResourceRoot);
		Mockito.when(this.standardContext.getAddWebinfClassesResources())
			.thenReturn(Boolean.FALSE);
		this.webResourceRoot.setContext(this.standardContext);
	}

	public void init(WebResourceSet... webResourcesSet) {
		for (WebResourceSet webResourceSet : webResourcesSet) {
			this.webResourceRoot.addJarResources(webResourceSet);
		}
	}
}
