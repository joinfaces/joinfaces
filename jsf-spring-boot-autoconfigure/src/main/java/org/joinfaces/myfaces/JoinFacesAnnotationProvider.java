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
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.faces.context.ExternalContext;

import org.apache.myfaces.spi.AnnotationProvider;

/**
 * Servlet context configurer of MyFaces.
 * @author Marcelo Fernandes
 */
public class JoinFacesAnnotationProvider extends AnnotationProvider {

	private static Map<Class<? extends Annotation>, Set<Class<?>>> annotatedClasses;

	private static Collection<URL> urls;

	public static void setAnnotatedClasses(Map<Class<? extends Annotation>, Set<Class<?>>> annotatedClasses) {
		JoinFacesAnnotationProvider.annotatedClasses = annotatedClasses;
	}

	public static void setUrls(Collection<URL> urls) {
		JoinFacesAnnotationProvider.urls = urls;
	}

	@Override
	public Map<Class<? extends Annotation>, Set<Class<?>>> getAnnotatedClasses(ExternalContext ctx) {
		return annotatedClasses;
	}

	@Override
	public Set<URL> getBaseUrls() throws IOException {
		return new HashSet<URL>(urls);
	}
}
