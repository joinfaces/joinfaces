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

package org.joinfaces.myfaces;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import jakarta.faces.context.ExternalContext;

import org.apache.myfaces.spi.FacesConfigResourceProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * @author Lars Grefer
 */
public class SpringFacesConfigResourceProvider extends FacesConfigResourceProvider {

	private final ResourcePatternResolver resourcePatternResolver;

	public SpringFacesConfigResourceProvider() {
		this(new PathMatchingResourcePatternResolver());
	}

	@Autowired
	public SpringFacesConfigResourceProvider(ResourcePatternResolver resourcePatternResolver) {
		this.resourcePatternResolver = resourcePatternResolver;
	}

	@Override
	public Collection<URL> getMetaInfConfigurationResources(ExternalContext context) throws IOException {

		Collection<URL> urls = new ArrayList<>();

		for (Resource resource : resourcePatternResolver.getResources("classpath*:META-INF/faces-config.xml")) {
			urls.add(resource.getURL());
		}

		for (Resource resource : resourcePatternResolver.getResources("classpath*:META-INF/*.faces-config.xml")) {
			urls.add(resource.getURL());
		}

		return urls;
	}
}
