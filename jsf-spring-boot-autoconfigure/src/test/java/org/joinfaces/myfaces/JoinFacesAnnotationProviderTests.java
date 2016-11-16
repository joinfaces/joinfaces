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

package org.joinfaces.myfaces;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.faces.convert.FacesConverter;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JoinFacesAnnotationProviderTests {

	@Test
	public void testBaseUrls() throws MalformedURLException, IOException {
		Set<URL> urls = new HashSet<URL>();
		URL myurl = new URL("http://localhost:8080");
		urls.add(myurl);
		JoinFacesAnnotationProvider.setUrls(urls);

		assertThat(new JoinFacesAnnotationProvider().getBaseUrls())
			.contains(myurl);
	}

	@Test
	public void testAnnotatedClasses() throws MalformedURLException, IOException {
		Map<Class<? extends Annotation>, Set<Class<?>>> annotatedClasses = new HashMap<Class<? extends Annotation>, Set<Class<?>>>();
		annotatedClasses.put(FacesConverter.class, new HashSet<Class<?>>());
		JoinFacesAnnotationProvider.setAnnotatedClasses(annotatedClasses);

		assertThat(new JoinFacesAnnotationProvider().getAnnotatedClasses(null).get(FacesConverter.class))
			.isNotNull();
	}

}
