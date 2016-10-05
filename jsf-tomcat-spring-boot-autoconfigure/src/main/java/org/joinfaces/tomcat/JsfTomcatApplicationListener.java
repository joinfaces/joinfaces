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

import lombok.Builder;

import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

/**
 * Jsf Tomcat application listener to add resources to jsf access resources at
 * integration tests.
 *
 * @author Marcelo Fernandes
 */
@Builder
public class JsfTomcatApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

	private Context context;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		if (context != null) {
			WebResourceRoot resources = context.getResources();

			if (resources != null) {
				String absolutePath = new File("").getAbsolutePath();
				String webAppMount = "/";
				String targetClassesBase = absolutePath + "/" + "target/classes";
				String targetTestClassesBase = absolutePath + "/" + "target/test-classes";
				String archivePath = null;
				String internalPath = "/META-INF/resources";
				if (resources.getJarResources() != null && resources.getJarResources().length <= 1) {
					if (new File(targetClassesBase + internalPath).exists()) {
						resources.createWebResourceSet(WebResourceRoot.ResourceSetType.POST, webAppMount, targetClassesBase, archivePath, internalPath);
					}
					if (new File(targetTestClassesBase + internalPath).exists()) {
						resources.createWebResourceSet(WebResourceRoot.ResourceSetType.POST, webAppMount, targetTestClassesBase, archivePath, internalPath);
					}
				}
			}
		}
	}
}
