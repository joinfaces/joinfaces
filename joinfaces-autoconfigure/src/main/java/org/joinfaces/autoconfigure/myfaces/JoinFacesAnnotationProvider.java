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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.faces.context.ExternalContext;

import lombok.extern.slf4j.Slf4j;
import org.apache.myfaces.spi.AnnotationProvider;
import org.apache.myfaces.spi.AnnotationProviderWrapper;

import org.springframework.util.StringUtils;

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

	private void findPreparedScanResult() {
		String resourceName = "/META-INF/joinfaces/" + AnnotationProvider.class.getName() + ".classes";
		InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(resourceName);

		if (resourceAsStream == null) {
			return;
		}

		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8))) {
			setAnnotatedClasses(readAnnotatedClassesMap(bufferedReader));
		}
		catch (IOException e) {
			log.warn("Failed to load {}", resourceName, e);
		}
	}

	private Map<Class<? extends Annotation>, Set<Class<?>>> readAnnotatedClassesMap(BufferedReader bufferedReader) {
		Map<Class<? extends Annotation>, Set<Class<?>>> classes = new HashMap<>();

		bufferedReader.lines().forEach(line -> {
			String[] split = line.split("=", 2);
			String annotationName = split[0];
			String classNameList = split[1];

			Class<? extends Annotation> annotation;
			try {
				annotation = (Class<? extends Annotation>) Class.forName(annotationName);
			}
			catch (ClassNotFoundException | LinkageError e) {
				log.info("Failed to load annotation class {}", annotationName, e);
				return;
			}
			Set<Class<?>> classSet;

			if (StringUtils.hasText(annotationName)) {
				classSet = Arrays.stream(classNameList.split(","))
						.filter(StringUtils::hasText)
						.map(className -> {
							try {
								return Class.forName(className);
							}
							catch (ClassNotFoundException e) {
								log.debug("Failed to load class {}", className, e);
							}
							catch (LinkageError e) {
								log.info("Failed to load class {}", className, e);
							}
							return null;
						})
						.filter(Objects::nonNull)
						.collect(Collectors.toSet());
			}
			else {
				classSet = Collections.emptySet();
			}

			classes.put(annotation, classSet);
		});
		return classes;
	}
}
