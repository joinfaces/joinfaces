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

package org.joinfaces.mojarra;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

import jakarta.servlet.ServletContext;

import com.sun.faces.spi.ConfigurationResourceProvider;
import com.sun.faces.spi.FaceletConfigResourceProvider;
import lombok.SneakyThrows;
import org.joinfaces.SpiUtils;

import org.springframework.core.io.Resource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author Lars Grefer
 */
public class SpringFaceletConfigResourceProvider implements FaceletConfigResourceProvider, ConfigurationResourceProvider {

	@SneakyThrows
	@Override
	public Collection<URI> getResources(ServletContext context) {

		WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(context);

		Collection<URI> result = new ArrayList<>();
		for (Resource resource : SpiUtils.getFaceletConfigs(applicationContext)) {
			result.add(resource.getURI());
		}
		return result;
	}
}
