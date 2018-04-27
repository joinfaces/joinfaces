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

package org.joinfaces.autoconfigure.myfaces;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

import javax.faces.context.ExternalContext;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.myfaces.spi.AnnotationProvider;

import org.springframework.lang.Nullable;

/**
 * Servlet context configurer of MyFaces.
 * @author Marcelo Fernandes
 */
@SuppressFBWarnings("DMI_COLLECTION_OF_URLS")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JoinFacesAnnotationProvider extends AnnotationProvider {

	private static Map<Class<? extends Annotation>, Set<Class<?>>> annotatedClasses;

	private static Collection<URL> urls;

	public static void setAnnotatedClasses(Map<Class<? extends Annotation>, Set<Class<?>>> annotatedClasses) {
		JoinFacesAnnotationProvider.annotatedClasses = annotatedClasses;
	}

	public static void setUrls(Collection<URL> urls) {
		JoinFacesAnnotationProvider.urls = urls;
	}

	@Nullable
	private AnnotationProvider wrapped;

	@Override
	public Map<Class<? extends Annotation>, Set<Class<?>>> getAnnotatedClasses(ExternalContext ctx) {
		Map<Class<? extends Annotation>, Set<Class<?>>> result = new HashMap<>();

		BiConsumer<Class<? extends Annotation>, Set<Class<?>>> resultMerger = (key, value) ->
				result.computeIfAbsent(key, k -> new HashSet<>()).addAll(value);

		if (annotatedClasses != null) {
			annotatedClasses.forEach(resultMerger);
		}
		if (this.wrapped != null) {
			this.wrapped.getAnnotatedClasses(ctx).forEach(resultMerger);
		}

		return result;
	}

	@Override
	public Set<URL> getBaseUrls() throws IOException {
		Set<URL> result = new HashSet<>();

		if (urls != null) {
			result.addAll(urls);
		}
		if (this.wrapped != null) {
			result.addAll(this.wrapped.getBaseUrls());
		}

		return result;
	}
}
