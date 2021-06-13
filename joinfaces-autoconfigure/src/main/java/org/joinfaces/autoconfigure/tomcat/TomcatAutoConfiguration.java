/*
 * Copyright 2016-2021 the original author or authors.
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

package org.joinfaces.autoconfigure.tomcat;

import org.apache.catalina.Context;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Creating tomcat auto configuration to enable jsf read facelets at integration
 * tests.
 *
 * @author Marcelo Fernandes
 * @author Lars Grefer
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(Context.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class TomcatAutoConfiguration {

	@Bean
	public WebServerFactoryCustomizer joinfacesWebServletFactoryCustomizer() {
		return new WebServerFactoryCustomizer() {
			@Override
			public void customize(WebServerFactory webServerFactory) {
				if (webServerFactory instanceof TomcatServletWebServerFactory) {
					TomcatServletWebServerFactory tomcatServletWebServerFactory = (TomcatServletWebServerFactory) webServerFactory;
					tomcatServletWebServerFactory.addContextCustomizers(new TomcatContextCustomizer() {
						@Override
						public void customize(Context context) {
							context.addLifecycleListener(new JsfTomcatLifecycleListener(context));
						}
					});
				}
			}
		};
	}
}
