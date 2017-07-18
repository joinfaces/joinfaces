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

package org.joinfaces.tomcat;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.JarResourceSet;
import org.apache.catalina.webresources.JarWarResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

public class JsfTomcatApplicationListenerIT {

	private static final String METAINF_RESOURCES = "/META-INF/resources";
	private static final String TARGET = "target";
	private static final String TEST_CLASSES = "test-classes";
	private static final String INTERNAL_JAR = "internal.jar";
	private static final String TEST = "/test";

	@Test
	public void customize() throws LifecycleException {
		Context standardContext = Mockito.mock(Context.class);
		StandardRoot webResourceRoot = new StandardRoot(standardContext);
		Mockito.when(standardContext.getResources()).thenReturn(webResourceRoot);
		Mockito.when(standardContext.getAddWebinfClassesResources()).thenReturn(Boolean.FALSE);

		JsfTomcatContextCustomizer jsfTomcatContextCustomizer = new JsfTomcatContextCustomizer();
		jsfTomcatContextCustomizer.customize(standardContext);

		JsfTomcatApplicationListener jsfTomcatApplicationListener = JsfTomcatApplicationListener
			.builder().context(jsfTomcatContextCustomizer.getContext()).build();
		jsfTomcatApplicationListener.onApplicationEvent(null);
		assertThat(webResourceRoot.getPostResources().length)
			.isEqualTo(9);
	}

	@Test
	public void customizeTargetTestClasses() throws LifecycleException {
		Context standardContext = Mockito.mock(Context.class);
		StandardRoot webResourceRoot = new StandardRoot(standardContext);
		Mockito.when(standardContext.getResources()).thenReturn(webResourceRoot);
		Mockito.when(standardContext.getAddWebinfClassesResources()).thenReturn(Boolean.FALSE);

		String absolutePath = new File("").getAbsolutePath();
		String internalPath = METAINF_RESOURCES;

		String targetTestClassesBase = absolutePath + "/" + "target/test-classes";
		File testClassesResources = new File(targetTestClassesBase + internalPath);
		if (!testClassesResources.mkdirs()) {
			throw new RuntimeException("Could not create dir: " + testClassesResources.toString());
		}

		JsfTomcatContextCustomizer jsfTomcatContextCustomizer = new JsfTomcatContextCustomizer();
		jsfTomcatContextCustomizer.customize(standardContext);

		JsfTomcatApplicationListener jsfTomcatApplicationListener = JsfTomcatApplicationListener
			.builder().context(jsfTomcatContextCustomizer.getContext()).build();

		jsfTomcatApplicationListener.onApplicationEvent(null);
		if (!testClassesResources.delete()) {
			throw new RuntimeException("Could not delete dir: " + testClassesResources.toString());
		}
		assertThat(webResourceRoot.getPostResources().length)
			.isEqualTo(10);
	}

	@Test
	public void contextNull() {
		JsfTomcatApplicationListener jsfTomcatApplicationListener = JsfTomcatApplicationListener
			.builder().build();
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

		JsfTomcatApplicationListener jsfTomcatApplicationListener = JsfTomcatApplicationListener
			.builder().context(jsfTomcatContextCustomizer.getContext()).build();
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

		JsfTomcatApplicationListener jsfTomcatApplicationListener = JsfTomcatApplicationListener
			.builder().context(jsfTomcatContextCustomizer.getContext()).build();
		jsfTomcatApplicationListener.onApplicationEvent(null);

		assertThat(jsfTomcatApplicationListener)
			.isNotNull();
	}

	@Test
	public void testingResources() throws LifecycleException {
		ContextMock contextMock = new ContextMock();

		DirResourceSet dirResourceSet = new DirResourceSet(contextMock.getWebResourceRoot(),
			TEST, TEST, TEST);

		contextMock.init(dirResourceSet);

		callApplicationEvent(contextMock);

		assertThat(contextMock.getCalledAnswer().getCalls())
			.isEqualTo(0);
	}

	@Test
	public void embeddedJarWithoutAppResources() throws LifecycleException {
		ContextMock contextMock = new ContextMock();

		File file = new File(TARGET + File.separator + TEST_CLASSES + File.separator + "test.jar");
		JarWarResourceSet jarWarResourceSet = new JarWarResourceSet(contextMock.getWebResourceRoot(),
			"/", file.getAbsolutePath(), INTERNAL_JAR, METAINF_RESOURCES);
		jarWarResourceSet.init();

		DirResourceSet dirResourceSet = new DirResourceSet(contextMock.getWebResourceRoot(),
			TEST, TEST, TEST);

		contextMock.init(jarWarResourceSet, dirResourceSet);

		callApplicationEvent(contextMock);

		assertThat(contextMock.getCalledAnswer().getCalls())
			.isEqualTo(1);
	}

	@Test
	public void embeddedJarWithoutAppResources2() throws LifecycleException {
		ContextMock contextMock = new ContextMock();

		File file = new File(TARGET + File.separator + TEST_CLASSES + File.separator + "test.jar");
		JarWarResourceSet jarWarResourceSet = new JarWarResourceSet(contextMock.getWebResourceRoot(),
			"/", file.getAbsolutePath(), INTERNAL_JAR, METAINF_RESOURCES);
		jarWarResourceSet.init();

		DirResourceSet dirResourceSet = new DirResourceSet(contextMock.getWebResourceRoot(),
			TEST, TEST, TEST);

		contextMock.init(dirResourceSet, jarWarResourceSet);

		callApplicationEvent(contextMock);

		assertThat(contextMock.getCalledAnswer().getCalls())
			.isEqualTo(1);
	}

	@Test
	public void embeddedJarWithAppResources() throws LifecycleException {
		ContextMock contextMock = new ContextMock();

		File file = new File(TARGET + File.separator + TEST_CLASSES + File.separator + "test.jar");
		JarWarResourceSet jarWarResourceSet = new JarWarResourceSet(contextMock.getWebResourceRoot(),
			"/", file.getAbsolutePath(), INTERNAL_JAR, METAINF_RESOURCES);
		jarWarResourceSet.init();

		JarResourceSet jarResourceSet = new JarResourceSet(contextMock.getWebResourceRoot(),
			"/", file.getAbsolutePath(), METAINF_RESOURCES);

		contextMock.init(jarWarResourceSet, jarResourceSet);

		callApplicationEvent(contextMock);

		assertThat(contextMock.getCalledAnswer().getCalls())
			.isEqualTo(1);
	}

	@Test
	public void embeddedWarWithAppResources() throws LifecycleException {
		ContextMock contextMock = new ContextMock();

		File file = new File(TARGET + File.separator + TEST_CLASSES + File.separator + "test.war");
		JarWarResourceSet jarWarResourceSet = new JarWarResourceSet(contextMock.getWebResourceRoot(),
			"/", file.getAbsolutePath(), INTERNAL_JAR, METAINF_RESOURCES);
		jarWarResourceSet.init();

		JarResourceSet jarResourceSet = new JarResourceSet(contextMock.getWebResourceRoot(),
			"/", file.getAbsolutePath(), METAINF_RESOURCES);

		contextMock.init(jarWarResourceSet, jarResourceSet);

		callApplicationEvent(contextMock);

		assertThat(contextMock.getCalledAnswer().getCalls())
			.isEqualTo(0);
	}

	@Test
	public void embeddedWarWithoutAppResources() throws LifecycleException {
		ContextMock contextMock = new ContextMock();

		File file = new File(TARGET + File.separator + TEST_CLASSES + File.separator + "test.war");
		JarWarResourceSet jarWarResourceSet = new JarWarResourceSet(contextMock.getWebResourceRoot(),
			"/", file.getAbsolutePath(), INTERNAL_JAR, METAINF_RESOURCES);
		jarWarResourceSet.init();

		DirResourceSet dirResourceSet = new DirResourceSet(contextMock.getWebResourceRoot(),
			TEST, TEST, TEST);

		contextMock.init(jarWarResourceSet, dirResourceSet);

		callApplicationEvent(contextMock);

		assertThat(contextMock.getCalledAnswer().getCalls())
			.isEqualTo(0);
	}

	private void callApplicationEvent(ContextMock contextMock) {
		JsfTomcatContextCustomizer jsfTomcatContextCustomizer = new JsfTomcatContextCustomizer();
		jsfTomcatContextCustomizer.customize(contextMock.getStandardContext());

		JsfTomcatApplicationListener jsfTomcatApplicationListener = JsfTomcatApplicationListener
			.builder().context(jsfTomcatContextCustomizer.getContext()).build();
		jsfTomcatApplicationListener.onApplicationEvent(null);
	}
}
