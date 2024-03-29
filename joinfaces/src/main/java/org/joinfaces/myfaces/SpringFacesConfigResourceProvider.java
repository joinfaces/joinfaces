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

import lombok.NoArgsConstructor;
import org.apache.myfaces.spi.FacesConfigResourceProvider;
import org.joinfaces.FacesContextUtils;
import org.joinfaces.SpiUtils;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.context.WebApplicationContext;

/**
 * MyFaces {@link FacesConfigResourceProvider} implementation which uses the current Spring Context
 * as {@link ResourcePatternResolver} in order to resolve the faces config files.
 *
 * @author Lars Grefer
 */
@NoArgsConstructor
public class SpringFacesConfigResourceProvider extends FacesConfigResourceProvider {

	@Override
	public Collection<URL> getMetaInfConfigurationResources(ExternalContext context) throws IOException {

		WebApplicationContext applicationContext = FacesContextUtils.getRequiredWebApplicationContext(context);

		Collection<URL> result = new ArrayList<>();
		for (Resource resource : SpiUtils.getFacesConfigs(applicationContext)) {
			result.add(resource.getURL());
		}
		return result;
	}
}
