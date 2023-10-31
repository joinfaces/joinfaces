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

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Enumeration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Context;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.WebResourceSet;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.JarWarResourceSet;

/**
 * Jsf Tomcat lifecycle listener to add resources to jsf access resources at
 * unpackaged or embedded jar.
 *
 * @author Marcelo Fernandes
 * @see org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory.StaticResourceConfigurer
 */
@Slf4j
@RequiredArgsConstructor
public class JsfTomcatLifecycleListener implements LifecycleListener {

	private final Context context;

	private boolean isJarWarResourceSet(WebResourceSet resourceSet) {
		return (resourceSet != null) && (resourceSet instanceof JarWarResourceSet);
	}

	private boolean isNestedJarResourceSet(WebResourceSet resourceSet) {
		return (resourceSet != null) && (resourceSet.getClass().getSimpleName().equals("NestedJarResourceSet"));
	}

	private boolean isDirResourceSetWithoutBootInfFolder(WebResourceSet resourceSet) {
		return (resourceSet != null) && (resourceSet instanceof DirResourceSet)
			&& resourceSet.getBaseUrl() != null
			&& resourceSet.getBaseUrl().getFile() != null
			&& !resourceSet.getBaseUrl().getFile().contains("BOOT-INF");
	}

	private WebResourceSet findFirstWebResourceSet(WebResourceRoot resources, WebResourceSetCondition condition) {
		WebResourceSet result = null;
		for (WebResourceSet resourceSet : resources.getJarResources()) {
			if (condition.check(resourceSet)) {
				result = resourceSet;
				break;
			}
		}
		return result;
	}

	private WebResourceSet findFirstJarWarResourceSetOrNestedJarResourceSet(WebResourceRoot resources) {
		return findFirstWebResourceSet(resources, (r) -> {
			return isJarWarResourceSet(r) || isNestedJarResourceSet(r);
		});
	}

	private WebResourceSet findFirstDirResourceSetWithoutBootInfFolder(WebResourceRoot resources) {
		return findFirstWebResourceSet(resources, (r) -> {
			return isDirResourceSetWithoutBootInfFolder(r);
		});
	}

	private URL mainFile(WebResourceRoot resources) {
		URL result = null;
		WebResourceSet resourceSet = findFirstJarWarResourceSetOrNestedJarResourceSet(resources);
		if (resourceSet != null) {
			result = resourceSet.getBaseUrl();
		}
		return result;
	}

	private String base(URL url) throws URISyntaxException {
		String result;
		if (url.getProtocol().equals("nested")) {
			result = Paths.get(url.toURI()).toString();
			result = result.substring(0, result.indexOf("!BOOT-INF") - 1);
		}
		else if (url.getProtocol().equals("jar")) {
			result = url.getFile();
			result = result.substring("file:".length());
			result = result.substring(0, result.indexOf("!/"));
		}
		else {
			result = Paths.get(url.toURI()).toString();
		}

		return result;
	}

	private boolean isUberJar(WebResourceRoot resources) {
		WebResourceSet resourceSet = findFirstJarWarResourceSetOrNestedJarResourceSet(resources);
		return (resourceSet != null)
			&& resourceSet.getBaseUrl().getFile().endsWith(".jar");
	}

	private boolean isUberWar(WebResourceRoot resources) {
		WebResourceSet resourceSet = findFirstJarWarResourceSetOrNestedJarResourceSet(resources);
		return (resourceSet != null)
			&& resourceSet.getBaseUrl().getFile().endsWith(".war");
	}

	private boolean containsDirResourceSetWithoutBootInfFolder(WebResourceRoot resources) {
		return !isUberJar(resources) && !isUberWar(resources)
			&& findFirstDirResourceSetWithoutBootInfFolder(resources) != null;
	}

	/**
	 * Inform tomcat runtime setup. UNPACKAGED war not covered yet.
	 * @param resources of the tomcat
	 * @return tomcat runtime
	*/
	TomcatRuntime getTomcatRuntime(WebResourceRoot resources) {
		TomcatRuntime result = null;

		if (isUberJar(resources)) {
			result = TomcatRuntime.UBER_JAR;
		}
		else if (isUberWar(resources)) {
			result = TomcatRuntime.UBER_WAR;
		}
		else {
			result = TomcatRuntime.UNPACKAGED;
		}

		return result;
	}

	@Override
	public void lifecycleEvent(LifecycleEvent event) {
		if (event.getType().equals(Lifecycle.CONFIGURE_START_EVENT)) {
			WebResourceRoot resources = this.context.getResources();

			if (resources != null && resources.getJarResources() != null) {
				TomcatRuntime tomcatRuntime = getTomcatRuntime(resources);

				switch (tomcatRuntime) {
					// add main resource
					case UBER_JAR: try {
						addMainJarResourceSet(resources);
					}
					catch (URISyntaxException ex) {
						log.error(ex.getMessage());
					}
					break;
					// do nothing, already working with main resource and lib resources
					case UBER_WAR: break;
					// adding classpath resources
					// should not contains dir resource set without BOOT-INF folder
					case UNPACKAGED: if (!containsDirResourceSetWithoutBootInfFolder(resources)) {
						try {
							addClasspathResourceSets(resources);
						}
						catch (URISyntaxException | IOException ex) {
							log.error(ex.getMessage());
						}
					}
					break;
					// default do nothing
					default: break;
				}
			}
		}
	}

	private void addMainJarResourceSet(WebResourceRoot resources) throws URISyntaxException {
		String webAppMount = "/";
		String archivePath = null;
		String internalPath = "/META-INF/resources";
		String bootInfPath = "/BOOT-INF/classes";

		resources.createWebResourceSet(WebResourceRoot.ResourceSetType.POST,
			webAppMount, base(mainFile(resources)), archivePath, internalPath);
		resources.createWebResourceSet(WebResourceRoot.ResourceSetType.POST,
			webAppMount, base(mainFile(resources)), archivePath, bootInfPath + internalPath);
	}

	private void addClasspathResourceSets(WebResourceRoot resources) throws URISyntaxException, IOException {
		String webAppMount = "/";
		String archivePath = null;
		String internalPath = "/META-INF/resources";

		Enumeration<URL> urlEnumeration = this.getClass().getClassLoader().getResources("META-INF/resources/");

		while (urlEnumeration.hasMoreElements()) {
			URL url = urlEnumeration.nextElement();
			int index = url.toExternalForm().lastIndexOf("META-INF/resources/");
			if (index != -1) {
				// Add old url as contextUrl to support exotic url handlers
				url = new URL(url, url.toExternalForm().substring(0, index));
			}
			resources.createWebResourceSet(WebResourceRoot.ResourceSetType.POST,
				webAppMount, base(url), archivePath, internalPath);
		}
	}

	interface WebResourceSetCondition {
		boolean check(WebResourceSet resourceSet);
	}
}
