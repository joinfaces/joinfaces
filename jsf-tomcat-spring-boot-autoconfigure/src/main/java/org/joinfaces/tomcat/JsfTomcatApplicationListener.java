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

import java.net.URL;

import lombok.Builder;
import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.WebResourceSet;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.JarWarResourceSet;
import org.reflections.util.ClasspathHelper;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

/**
 * Jsf Tomcat application listener to add resources to jsf access resources at
 * integration tests or embedded jar.
 *
 * @author Marcelo Fernandes
 */
@Builder
public class JsfTomcatApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

	private Context context;

	private JarWarResourceSet getFirstJarWarResourceSetAtJarResources(WebResourceRoot resources) {
		JarWarResourceSet result = null;
		for (WebResourceSet resourceSet :resources.getJarResources()) {
			if (resourceSet instanceof JarWarResourceSet) {
				result = (JarWarResourceSet) resourceSet;
				break;
			}
		}
		return result;
	}

	private DirResourceSet getFirstDirResourceSetAtJarResources(WebResourceRoot resources) {
		DirResourceSet result = null;
		for (WebResourceSet resourceSet :resources.getJarResources()) {
			if (resourceSet instanceof DirResourceSet) {
				result = (DirResourceSet) resourceSet;
				break;
			}
		}
		return result;
	}

	private URL mainFile(WebResourceRoot resources) {
		URL result = null;
		for (WebResourceSet resourceSet :resources.getJarResources()) {
			if (resourceSet instanceof JarWarResourceSet) {
				result = resourceSet.getBaseUrl();
				break;
			}
		}
		return result;
	}

	private String base(URL url) {
		String result;
		if (url.getProtocol().equals("jar")) {
			result = url.getFile();
			result = result.substring("file:".length());
			result = result.substring(0, result.indexOf("!/"));
		}
		else {
			result = url.getFile();
		}
		return result;
	}

	private boolean isUberJar(WebResourceRoot resources) {
		JarWarResourceSet jarWarResourceSet = getFirstJarWarResourceSetAtJarResources(resources);
		return jarWarResourceSet != null
				&& jarWarResourceSet.getBaseUrl().getFile().endsWith(".jar");
	}

	private boolean isUberWar(WebResourceRoot resources) {
		JarWarResourceSet jarWarResourceSet = getFirstJarWarResourceSetAtJarResources(resources);
		return jarWarResourceSet != null
				&& jarWarResourceSet.getBaseUrl().getFile().endsWith(".war");
	}

	private boolean isTesting(WebResourceRoot resources) {
		return !isUberJar(resources) && !isUberWar(resources)
				&& getFirstDirResourceSetAtJarResources(resources) == null;
	}

	private boolean isUnpackagedJar(WebResourceRoot resources) {
		return !isUberJar(resources)
				&& getFirstDirResourceSetAtJarResources(resources) != null;
	}

	/**
	 * Inform tomcat runtime setup. UNPACKAGED_WAR not covered yet.
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
		else if (isTesting(resources)) {
			result = TomcatRuntime.TEST;
		}
		else if (isUnpackagedJar(resources)) {
			result = TomcatRuntime.UNPACKAGED_JAR;
		}

		return result;
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		if (this.context != null) {
			WebResourceRoot resources = this.context.getResources();

			if (resources != null && resources.getJarResources() != null) {
				TomcatRuntime tomcatRuntime = getTomcatRuntime(resources);

				switch (tomcatRuntime) {
					// add main resource
					case UBER_JAR: addMainJarResourceSet(resources);
						break;
					// do nothing, already working with main resource and lib resources
					case UNPACKAGED_JAR: break;
					// do nothing, already working with main resource and lib resources
					case UBER_WAR: break;
					// test jar: adding main resource and lib resources; test war: add lib resources
					case TEST: addClasspathResourceSets(resources);
						break;
					// default do nothing
					default: break;
				}
			}
		}
	}

	private void addMainJarResourceSet(WebResourceRoot resources) {
		String webAppMount = "/";
		String archivePath = null;
		String internalPath = "/META-INF/resources";

		resources.createWebResourceSet(WebResourceRoot.ResourceSetType.POST,
			webAppMount, base(mainFile(resources)), archivePath, internalPath);
	}

	private void addClasspathResourceSets(WebResourceRoot resources) {
		String webAppMount = "/";
		String archivePath = null;
		String internalPath = "/META-INF/resources";

		for (URL url : ClasspathHelper.forResource("META-INF/resources/", this.getClass().getClassLoader())) {
			resources.createWebResourceSet(WebResourceRoot.ResourceSetType.POST,
				webAppMount, base(url), archivePath, internalPath);
		}
	}
}
