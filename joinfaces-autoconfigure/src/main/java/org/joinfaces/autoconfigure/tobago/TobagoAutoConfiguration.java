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

package org.joinfaces.autoconfigure.tobago;

import java.util.EventListener;

import org.apache.myfaces.tobago.webapp.TobagoServletContextListener;
import org.joinfaces.autoconfigure.servlet.WebFragmentRegistrationBean;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ClassUtils;

/**
 * {@link org.springframework.boot.autoconfigure.EnableAutoConfiguration Auto configuration}
 * for Apache MyFaces Tobago.
 *
 * @author Lars Grefer
 * @author Carsten Dimmek
 */
@AutoConfiguration
@ConditionalOnClass(TobagoServletContextListener.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class TobagoAutoConfiguration {


	private static final String SECRET_SESSION_LISTENER = "org.apache.myfaces.tobago.webapp.SecretSessionListener";

	/**
	 * This {@link WebFragmentRegistrationBean} is equivalent to the
	 * {@code META-INF/web-fragment.xml} of the {@code tobago-core.jar}.
	 *
	 * @return tobagoWebFragmentRegistrationBean
	 */
	@Bean
	public WebFragmentRegistrationBean tobagoWebFragmentRegistrationBean() throws ClassNotFoundException {
		WebFragmentRegistrationBean bean = new WebFragmentRegistrationBean();
		bean.getListeners().add(TobagoServletContextListener.class);
		ClassLoader classLoader = TobagoAutoConfiguration.class.getClassLoader();
		if (ClassUtils.isPresent(SECRET_SESSION_LISTENER, classLoader)) {
			bean.getListeners().add((Class<? extends EventListener>) ClassUtils.forName(SECRET_SESSION_LISTENER, classLoader));
		}
		return bean;
	}
}
