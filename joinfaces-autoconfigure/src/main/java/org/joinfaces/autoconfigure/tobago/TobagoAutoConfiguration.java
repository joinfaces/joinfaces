/*
 * Copyright 2016-2019 the original author or authors.
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

package org.joinfaces.autoconfigure.tobago;

import org.apache.myfaces.tobago.webapp.SecretSessionListener;
import org.apache.myfaces.tobago.webapp.TobagoServletContextListener;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@link org.springframework.boot.autoconfigure.EnableAutoConfiguration Auto configuration}
 * for Apache MyFaces Tobago.
 *
 * @author Lars Grefer
 */
@Configuration
@ConditionalOnClass(TobagoServletContextListener.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class TobagoAutoConfiguration {

	/**
	 * This {@link WebServerFactoryCustomizer} adds a {@link ServletContextInitializer} to the embedded servlet-container
	 * which is equivalent to tobagos's own {@code META-INF/web-fragment.xml}.
	 *
	 * @return tobago web server factory customizer
	 */
	@Bean
	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> tobagoWebServerFactoryCustomizer() {
		return factory -> factory.addInitializers(servletContext -> {
			servletContext.addListener(new TobagoServletContextListener());
			servletContext.addListener(new SecretSessionListener());
		});
	}
}
