/*
 * Copyright 2016-2016 the original author or authors.
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

package org.joinfaces.autoconfigure.myfaces;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import jakarta.faces.context.ExternalContext;

import lombok.extern.slf4j.Slf4j;
import org.apache.myfaces.spi.AnnotationProvider;
import org.apache.myfaces.spi.AnnotationProviderWrapper;
import org.joinfaces.ClasspathScanUtil;

/**
 * Servlet context configurer of MyFaces.
 *
 * @author Marcelo Fernandes
 * @author Lars Grefer
 * @see org.apache.myfaces.spi.AnnotationProvider
 * @see MyFacesInitializerRegistrationBean
 */
@Slf4j
public class JoinFacesAnnotationProvider extends AnnotationProviderWrapper {

	private static Map<Class<? extends Annotation>, Set<Class<?>>> annotatedClasses;

	static void setAnnotatedClasses(Map<Class<? extends Annotation>, Set<Class<?>>> annotatedClasses) {
		JoinFacesAnnotationProvider.annotatedClasses = annotatedClasses;
	}

	public JoinFacesAnnotationProvider(AnnotationProvider delegate) {
		super(delegate);
		findPreparedScanResult();
	}

	@Override
	public Map<Class<? extends Annotation>, Set<Class<?>>> getAnnotatedClasses(ExternalContext ctx) {
		if (annotatedClasses != null) {
			return Collections.unmodifiableMap(annotatedClasses);
		}
		else {
			return super.getAnnotatedClasses(ctx);
		}
	}

	private void findPreparedScanResult() {
		String resourceName = "META-INF/joinfaces/" + AnnotationProvider.class.getName() + ".classes";
		ClasspathScanUtil.readClassMap(resourceName, getClass().getClassLoader())
				.ifPresent(JoinFacesAnnotationProvider::setAnnotatedClasses);
	}
}
