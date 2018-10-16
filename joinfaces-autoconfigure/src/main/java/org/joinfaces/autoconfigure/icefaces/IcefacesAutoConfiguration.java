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

package org.joinfaces.autoconfigure.icefaces;

import io.undertow.Undertow;
import org.icefaces.impl.application.SessionExpiredListener;
import org.icefaces.impl.application.WindowScopeManager;
import org.icefaces.impl.push.servlet.ICEpushResourceHandlerLifecycle;
import org.icefaces.util.EnvUtils;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot auto configuration for ICEfaces.
 *
 * @author Lars Grefer
 */
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnClass({EnvUtils.class, WindowScopeManager.class})
@EnableConfigurationProperties(IcefacesProperties.class)
public class IcefacesAutoConfiguration {

	@Bean
	public static CustomScopeConfigurer windowScopeConfigurer() {
		CustomScopeConfigurer windowScopeConfigurer = new CustomScopeConfigurer();
		windowScopeConfigurer.addScope(WindowScopeManager.ScopeName, new WindowScope());
		return windowScopeConfigurer;
	}

	@Configuration
	@ConditionalOnClass(Undertow.class)
	public static class IcefacesUndertowAutoConfiguration {

		/**
		 * This {@link WebServerFactoryCustomizer} adds a {@link ServletContextInitializer} to the embedded undertow
		 * which is equivalent to ICEfaces' {@code META-INF/core.tld}.
		 *
		 * @return ICEfaces undertow factory customizer
		 */
		@Bean
		public WebServerFactoryCustomizer<UndertowServletWebServerFactory> icefacesUndertowFactoryCustomizer() {
			return factory -> factory.addInitializers(servletContext -> {
				servletContext.addListener(ICEpushResourceHandlerLifecycle.class);
				servletContext.addListener(SessionExpiredListener.class);
				servletContext.addListener(WindowScopeManager.SetupTimer.class);
			});
		}
	}
}
