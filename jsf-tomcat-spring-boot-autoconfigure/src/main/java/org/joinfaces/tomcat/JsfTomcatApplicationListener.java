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
import org.reflections.util.ClasspathHelper;

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

	private boolean isEmbeddedTesting(WebResourceRoot resources) {
		return resources != null
			&& resources.getJarResources() != null
			&& resources.getJarResources().length <= 1;
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

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		if (this.context != null) {
			WebResourceRoot resources = this.context.getResources();

			if (isEmbeddedTesting(resources)) {
				String webAppMount = "/";
				String archivePath = null;
				String internalPath = "/META-INF/resources";

				for (URL url : ClasspathHelper.forResource("META-INF/resources/", this.getClass().getClassLoader())) {
					resources.createWebResourceSet(WebResourceRoot.ResourceSetType.POST, webAppMount, base(url), archivePath, internalPath);
				}
			}
		}
	}
}
