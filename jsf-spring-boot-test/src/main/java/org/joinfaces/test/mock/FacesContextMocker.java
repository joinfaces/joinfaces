/*
 * Copyright 2016-2017 the original author or authors.
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

package org.joinfaces.test.mock;

import javax.faces.context.FacesContext;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * Faces Context Mocker.
 *
 * Taken from
 * http://ovaraksin.blogspot.com.br/2014/03/set-up-jsf-environment-for-junit-tests.html
 *
 * @author Marcelo Romulo Fernandes
 */
public abstract class FacesContextMocker extends FacesContext {

	private FacesContextMocker() {
	}

	private static final Release RELEASE = new Release();

	public static FacesContext mockFacesContext() {
		FacesContext context = Mockito.mock(FacesContext.class);
		setCurrentInstance(context);
		Mockito.doAnswer(RELEASE).when(context).release();
		return context;
	}

	private static class Release implements Answer<Void> {

		@Override
		public Void answer(InvocationOnMock invocation) throws Throwable {
			setCurrentInstance(null);
			return null;
		}
	}
}
