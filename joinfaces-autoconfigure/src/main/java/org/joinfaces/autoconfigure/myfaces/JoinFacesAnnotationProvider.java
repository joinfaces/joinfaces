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
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;

import javax.faces.context.ExternalContext;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.extern.slf4j.Slf4j;
import org.apache.myfaces.spi.AnnotationProvider;
import org.apache.myfaces.spi.AnnotationProviderWrapper;
import org.joinfaces.autoconfigure.ClasspathScanUtil;

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

	public JoinFacesAnnotationProvider() {
		findPreparedScanResult();
	}

	public JoinFacesAnnotationProvider(AnnotationProvider delegate) {
		super(delegate);
		findPreparedScanResult();
	}

	@Override
	public Map<Class<? extends Annotation>, Set<Class<?>>> getAnnotatedClasses(ExternalContext ctx) {
		if (annotatedClasses != null) {
			return annotatedClasses;
		}
		else {
			return super.getAnnotatedClasses(ctx);
		}
	}

	@SuppressFBWarnings(value = "RCN_REDUNDANT_NULLCHECK_OF_NONNULL_VALUE", justification = "https://github.com/spotbugs/spotbugs/issues/259")
	private void findPreparedScanResult() {
		String resourceName = "META-INF/joinfaces/" + AnnotationProvider.class.getName() + ".classes";
		InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(resourceName);

		if (resourceAsStream == null) {
			log.debug("No prepared scan result found.");
			return;
		}

		long start = System.nanoTime();
		try (InputStream inputStream = resourceAsStream) {
			Map<Class<? extends Annotation>, Set<Class<?>>> annotationClassMap = ClasspathScanUtil.readAnnotationClassMap(inputStream, getClass().getClassLoader());
			setAnnotatedClasses(annotationClassMap);
			double ms = (System.nanoTime() - start) / 1_000_000d;
			log.info("Loading prepared scan result took {}ms", ms);
		}
		catch (IOException e) {
			log.warn("Failed to load {}", resourceName, e);
		}
	}
}
