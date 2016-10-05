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

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.WebResourceSet;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import org.junit.Test;

import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

public class JsfTomcatApplicationListenerIT {

	@Test
	public void customize() throws LifecycleException {
		Context standardContext = Mockito.mock(Context.class);
		StandardRoot webResourceRoot = new StandardRoot(standardContext);
		Mockito.when(standardContext.getResources()).thenReturn(webResourceRoot);
		Mockito.when(standardContext.getAddWebinfClassesResources()).thenReturn(Boolean.FALSE);

		JsfTomcatContextCustomizer jsfTomcatContextCustomizer = new JsfTomcatContextCustomizer();
		jsfTomcatContextCustomizer.customize(standardContext);

		JsfTomcatApplicationListener jsfTomcatApplicationListener = JsfTomcatApplicationListener.builder().context(jsfTomcatContextCustomizer.getContext()).build();
		jsfTomcatApplicationListener.onApplicationEvent(null);
		assertThat(webResourceRoot.getPostResources().length)
			.isEqualTo(0);

		String absolutePath = new File("").getAbsolutePath();
		String internalPath = "/META-INF/resources";

		String targetClassesBase = absolutePath + "/" + "target/classes";
		File classesResources = new File(targetClassesBase + internalPath);
		if (!classesResources.mkdirs()) {
			throw new RuntimeException("Could not create dir: " + classesResources.toString());
		}
		jsfTomcatApplicationListener = JsfTomcatApplicationListener.builder().context(jsfTomcatContextCustomizer.getContext()).build();

		jsfTomcatApplicationListener.onApplicationEvent(null);
		if (!classesResources.delete()) {
			throw new RuntimeException("Could not delete dir: " + classesResources.toString());
		}
		assertThat(webResourceRoot.getPostResources().length)
			.isEqualTo(1);

		String targetTestClassesBase = absolutePath + "/" + "target/test-classes";
		File testClassesResources = new File(targetTestClassesBase + internalPath);
		if (!testClassesResources.mkdirs()) {
			throw new RuntimeException("Could not create dir: " + testClassesResources.toString());
		}
		jsfTomcatApplicationListener = JsfTomcatApplicationListener.builder().context(jsfTomcatContextCustomizer.getContext()).build();

		jsfTomcatApplicationListener.onApplicationEvent(null);
		if (!testClassesResources.delete()) {
			throw new RuntimeException("Could not delete dir: " + testClassesResources.toString());
		}
		assertThat(webResourceRoot.getPostResources().length)
			.isEqualTo(2);
	}

	@Test
	public void contextNull() {
		JsfTomcatApplicationListener jsfTomcatApplicationListener = JsfTomcatApplicationListener.builder().build();
		jsfTomcatApplicationListener.onApplicationEvent(null);

		assertThat(jsfTomcatApplicationListener)
			.isNotNull();
	}

	@Test
	public void resourcesNull() throws LifecycleException {
		Context standardContext = Mockito.mock(Context.class);
		Mockito.when(standardContext.getResources()).thenReturn(null);
		Mockito.when(standardContext.getAddWebinfClassesResources()).thenReturn(Boolean.FALSE);

		JsfTomcatContextCustomizer jsfTomcatContextCustomizer = new JsfTomcatContextCustomizer();
		jsfTomcatContextCustomizer.customize(standardContext);

		JsfTomcatApplicationListener jsfTomcatApplicationListener = JsfTomcatApplicationListener.builder().context(jsfTomcatContextCustomizer.getContext()).build();
		jsfTomcatApplicationListener.onApplicationEvent(null);

		assertThat(jsfTomcatApplicationListener)
			.isNotNull();
	}

	@Test
	public void jarResourcesNull() throws LifecycleException {
		Context standardContext = Mockito.mock(Context.class);
		WebResourceRoot webResourceRoot = Mockito.mock(WebResourceRoot.class);
		Mockito.when(standardContext.getResources()).thenReturn(webResourceRoot);
		Mockito.when(standardContext.getAddWebinfClassesResources()).thenReturn(Boolean.FALSE);
		Mockito.when(webResourceRoot.getJarResources()).thenReturn(null);

		JsfTomcatContextCustomizer jsfTomcatContextCustomizer = new JsfTomcatContextCustomizer();
		jsfTomcatContextCustomizer.customize(standardContext);

		JsfTomcatApplicationListener jsfTomcatApplicationListener = JsfTomcatApplicationListener.builder().context(jsfTomcatContextCustomizer.getContext()).build();
		jsfTomcatApplicationListener.onApplicationEvent(null);

		assertThat(jsfTomcatApplicationListener)
			.isNotNull();
	}

	@Test
	public void jarResourcesBiggerThan1() throws LifecycleException {
		Context standardContext = Mockito.mock(Context.class);
		WebResourceRoot webResourceRoot = Mockito.mock(WebResourceRoot.class);
		LifecycleState state = LifecycleState.NEW;
		Mockito.when(webResourceRoot.getContext()).thenReturn(standardContext);
		Mockito.when(webResourceRoot.getState()).thenReturn(state);
		Mockito.when(standardContext.getResources()).thenReturn(webResourceRoot);
		Mockito.when(standardContext.getAddWebinfClassesResources()).thenReturn(Boolean.FALSE);

		String TEST = "/test";
		WebResourceSet[] array = {new DirResourceSet(webResourceRoot, TEST, TEST, TEST), new DirResourceSet(webResourceRoot, TEST, TEST, TEST)};

		Mockito.when(webResourceRoot.getJarResources()).thenReturn(array);

		JsfTomcatContextCustomizer jsfTomcatContextCustomizer = new JsfTomcatContextCustomizer();
		jsfTomcatContextCustomizer.customize(standardContext);

		JsfTomcatApplicationListener jsfTomcatApplicationListener = JsfTomcatApplicationListener.builder().context(jsfTomcatContextCustomizer.getContext()).build();
		jsfTomcatApplicationListener.onApplicationEvent(null);

		assertThat(jsfTomcatApplicationListener)
			.isNotNull();
	}
}
