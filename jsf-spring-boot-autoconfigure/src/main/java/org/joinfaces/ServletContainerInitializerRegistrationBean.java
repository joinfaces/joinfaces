/*
 * Copyright 2016-2018 the original author or authors.
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

package org.joinfaces;

import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import org.springframework.beans.BeanUtils;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.RegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * {@link RegistrationBean} for {@link ServletContainerInitializer}s.
 * <p>
 * This is implemented as {@link EmbeddedServletContainerCustomizer} so its only applied to embedded servlet-containers.
 * When deployed as war file, the external servlet-container will handle the {@link ServletContainerInitializer}.
 *
 * @param <T> The type of the {@link ServletContainerInitializer}
 * @author Lars Grefer
 */
@RequiredArgsConstructor
@Getter
@Setter
public class ServletContainerInitializerRegistrationBean<T extends ServletContainerInitializer> implements EmbeddedServletContainerCustomizer {

	private final Class<T> servletContainerInitializerClass;

	protected Set<Class<?>> getClasses(HandlesTypes handlesTypes) {
		throw new UnsupportedOperationException("Can't handle " + handlesTypes + " for class " + servletContainerInitializerClass);
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
		configurableEmbeddedServletContainer.addInitializers(new ServletContextInitializer() {
			@Override
			public void onStartup(ServletContext servletContext) throws ServletException {
				HandlesTypes handlesTypes = AnnotationUtils.findAnnotation(getServletContainerInitializerClass(), HandlesTypes.class);

				Set<Class<?>> classes = null;

				if (handlesTypes != null) {
					classes = getClasses(handlesTypes);
				}

				BeanUtils.instantiateClass(getServletContainerInitializerClass())
						.onStartup(classes, servletContext);
			}
		});
	}
}
