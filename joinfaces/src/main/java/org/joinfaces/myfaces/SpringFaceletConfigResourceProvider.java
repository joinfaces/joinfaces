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
import java.util.List;

import jakarta.faces.context.ExternalContext;

import lombok.NoArgsConstructor;
import org.apache.myfaces.spi.FaceletConfigResourceProvider;
import org.joinfaces.FacesContextUtils;

import org.springframework.core.io.Resource;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Lars Grefer
 */
@NoArgsConstructor
public class SpringFaceletConfigResourceProvider extends FaceletConfigResourceProvider {

	@Override
	public Collection<URL> getFaceletTagLibConfigurationResources(ExternalContext context) throws IOException {

		WebApplicationContext applicationContext = FacesContextUtils.getRequiredWebApplicationContext(context);

		Resource[] resources = applicationContext.getResources("classpath*:/META-INF/*.taglib.xml");

		List<URL> urls = new ArrayList<>(resources.length);
		for (Resource resource : resources) {
			urls.add(resource.getURL());
		}

		return urls;

	}

}
