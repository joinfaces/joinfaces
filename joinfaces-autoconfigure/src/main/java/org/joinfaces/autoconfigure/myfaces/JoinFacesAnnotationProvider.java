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

package org.joinfaces.autoconfigure.myfaces;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;

import jakarta.faces.context.ExternalContext;
import jakarta.servlet.ServletContext;

import lombok.extern.slf4j.Slf4j;
import org.apache.myfaces.spi.AnnotationProvider;
import org.apache.myfaces.spi.AnnotationProviderWrapper;
import org.joinfaces.FacesContextUtils;
import org.joinfaces.ServletContextUtils;
import org.joinfaces.autoconfigure.FacesAnnotationProviderUtil;

import org.springframework.web.context.support.WebApplicationContextUtils;

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

	public JoinFacesAnnotationProvider(AnnotationProvider delegate) {
		super(delegate);
	}

	@Override
	public Map<Class<? extends Annotation>, Set<Class<?>>> getAnnotatedClasses(ExternalContext ctx) {

		ServletContext servletContext = FacesContextUtils.getServletContext(ctx);

		ClassLoader classLoader = ServletContextUtils.getClassLoader(servletContext);
		var preparedScanResult = FacesAnnotationProviderUtil.findPreparedScanResult(AnnotationProvider.class, classLoader);

		if (preparedScanResult.isPresent()) {
			return preparedScanResult.get();
		}

		MyFacesInitializerRegistrationBean registrationBean = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletContext)
				.getBeanProvider(MyFacesInitializerRegistrationBean.class)
				.getIfAvailable();
		if (registrationBean != null && registrationBean.getAnnotatedClasses() != null) {
			return registrationBean.getAnnotatedClasses();
		}

		return super.getAnnotatedClasses(ctx);
	}
}
