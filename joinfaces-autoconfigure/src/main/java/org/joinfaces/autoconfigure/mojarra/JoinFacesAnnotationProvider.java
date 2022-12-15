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

package org.joinfaces.autoconfigure.mojarra;

import java.lang.annotation.Annotation;
import java.net.URI;
import java.util.Map;
import java.util.Set;

import jakarta.servlet.ServletContext;

import com.sun.faces.spi.AnnotationProvider;
import lombok.NoArgsConstructor;
import org.joinfaces.ServletContextUtils;
import org.joinfaces.autoconfigure.FacesAnnotationProviderUtil;

import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Mojarra {@link AnnotationProvider} implementation which will use existing scan results, if available.
 *
 * @author Lars Grefer
 */
@NoArgsConstructor
public class JoinFacesAnnotationProvider extends AnnotationProvider {

	public JoinFacesAnnotationProvider(ServletContext servletContext) {
		super(servletContext);
	}

	public JoinFacesAnnotationProvider(ServletContext servletContext, AnnotationProvider parent) {
		super(servletContext);
		this.wrappedAnnotationProvider = parent;
	}

	@Override
	public Map<Class<? extends Annotation>, Set<Class<?>>> getAnnotatedClasses(Set<URI> urls) {

		ClassLoader classLoader = ServletContextUtils.getClassLoader(this.servletContext);
		var preparedScanResult = FacesAnnotationProviderUtil.findPreparedScanResult(AnnotationProvider.class, classLoader);

		if (preparedScanResult.isPresent()) {
			return preparedScanResult.get();
		}

		MojarraInitializerRegistrationBean registrationBean = WebApplicationContextUtils
				.getRequiredWebApplicationContext(this.servletContext)
				.getBeanProvider(MojarraInitializerRegistrationBean.class)
				.getIfAvailable();
		if (registrationBean != null && registrationBean.getAnnotatedClasses() != null) {
			return registrationBean.getAnnotatedClasses();
		}

		return this.wrappedAnnotationProvider.getAnnotatedClasses(urls);
	}

}
