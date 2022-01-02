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

package org.joinfaces.autoconfigure.tomcat;

import java.io.File;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.catalina.Context;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.JarResourceSet;
import org.apache.catalina.webresources.JarWarResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@SuppressFBWarnings(value = "DMI_HARDCODED_ABSOLUTE_FILENAME", justification = "Tests")
public class JsfTomcatLifecycleListenerIT {

	private static final String METAINF_RESOURCES = "/META-INF/resources";
	private static final String TARGET = "build";
	private static final String TEST_CLASSES = "resources/test";
	private static final String INTERNAL_JAR = "internal.jar";
	private static final String TEST = "/test";

	private LifecycleEvent lifecycleEvent;

	@BeforeEach
	void init() {
		this.lifecycleEvent = new LifecycleEvent(mock(Lifecycle.class), Lifecycle.CONFIGURE_START_EVENT, null);
	}

	@Test
	public void customize() {
		Context standardContext = mock(Context.class);
		StandardRoot webResourceRoot = new StandardRoot(standardContext);
		Mockito.when(standardContext.getResources()).thenReturn(webResourceRoot);
		Mockito.when(standardContext.getAddWebinfClassesResources()).thenReturn(Boolean.FALSE);

		JsfTomcatLifecycleListener jsfTomcatLifecycleListener = new JsfTomcatLifecycleListener(standardContext);
		jsfTomcatLifecycleListener.lifecycleEvent(this.lifecycleEvent);
		assertThat(webResourceRoot.getPostResources())
			.hasSizeGreaterThanOrEqualTo(6);
	}

	@Test
	public void customizeTargetTestClasses() {
		Context standardContext = mock(Context.class);
		StandardRoot webResourceRoot = new StandardRoot(standardContext);
		Mockito.when(standardContext.getResources()).thenReturn(webResourceRoot);
		Mockito.when(standardContext.getAddWebinfClassesResources()).thenReturn(Boolean.FALSE);

		String absolutePath = new File("").getAbsolutePath();
		String internalPath = METAINF_RESOURCES;

		String targetTestClassesBase = absolutePath + "/" + "build/resources/test";
		File testClassesResources = new File(targetTestClassesBase + internalPath);
		if (!testClassesResources.mkdirs()) {
			throw new RuntimeException("Could not create dir: " + testClassesResources.toString());
		}

		JsfTomcatLifecycleListener jsfTomcatLifecycleListener = new JsfTomcatLifecycleListener(standardContext);

		jsfTomcatLifecycleListener.lifecycleEvent(this.lifecycleEvent);
		if (!testClassesResources.delete()) {
			throw new RuntimeException("Could not delete dir: " + testClassesResources.toString());
		}
		assertThat(webResourceRoot.getPostResources())
			.hasSizeGreaterThanOrEqualTo(7);
	}

	@Test
	public void resourcesNull() {
		Context standardContext = mock(Context.class);
		Mockito.when(standardContext.getResources()).thenReturn(null);
		Mockito.when(standardContext.getAddWebinfClassesResources()).thenReturn(Boolean.FALSE);

		JsfTomcatLifecycleListener jsfTomcatLifecycleListener = new JsfTomcatLifecycleListener(standardContext);
		jsfTomcatLifecycleListener.lifecycleEvent(this.lifecycleEvent);

		assertThat(jsfTomcatLifecycleListener)
			.isNotNull();
	}

	@Test
	public void jarResourcesNull() {
		Context standardContext = mock(Context.class);
		WebResourceRoot webResourceRoot = mock(WebResourceRoot.class);
		Mockito.when(standardContext.getResources()).thenReturn(webResourceRoot);
		Mockito.when(standardContext.getAddWebinfClassesResources()).thenReturn(Boolean.FALSE);
		Mockito.when(webResourceRoot.getJarResources()).thenReturn(null);

		JsfTomcatLifecycleListener jsfTomcatLifecycleListener = new JsfTomcatLifecycleListener(standardContext);
		jsfTomcatLifecycleListener.lifecycleEvent(this.lifecycleEvent);

		assertThat(jsfTomcatLifecycleListener)
			.isNotNull();
	}

	@Test
	public void testingResources() throws LifecycleException {
		ContextMock contextMock = new ContextMock();

		DirResourceSet dirResourceSet = new DirResourceSet(contextMock.getWebResourceRoot(),
			TEST, TEST, TEST);

		contextMock.init(dirResourceSet);

		callApplicationEvent(contextMock);

		assertThat(contextMock.getWebResourceRoot().getCreateWebResourceSetCalls())
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

		assertThat(contextMock.getWebResourceRoot().getCreateWebResourceSetCalls())
			.isEqualTo(2);
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

		assertThat(contextMock.getWebResourceRoot().getCreateWebResourceSetCalls())
			.isEqualTo(2);
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

		assertThat(contextMock.getWebResourceRoot().getCreateWebResourceSetCalls())
			.isEqualTo(2);
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

		assertThat(contextMock.getWebResourceRoot().getCreateWebResourceSetCalls())
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

		assertThat(contextMock.getWebResourceRoot().getCreateWebResourceSetCalls())
			.isEqualTo(0);
	}

	private void callApplicationEvent(ContextMock contextMock) {
		JsfTomcatLifecycleListener jsfTomcatLifecycleListener = new JsfTomcatLifecycleListener(contextMock.getStandardContext());
		jsfTomcatLifecycleListener.lifecycleEvent(this.lifecycleEvent);
	}
}
