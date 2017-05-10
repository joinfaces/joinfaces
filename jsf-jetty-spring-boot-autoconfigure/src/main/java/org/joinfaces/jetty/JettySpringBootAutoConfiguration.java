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

package org.joinfaces.jetty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot Auto Configuration of Jetty.
 * Taken from
 * https://github.com/ghillert/spring-boot-jsp-demo/blob/jetty/src/main/java/com/hillert/JspDemoApplication.java#L78
 * and from https://github.com/eclipse/jetty.project/issues/420 and from
 * https://github.com/spring-projects/spring-boot/pull/5290
 * @author Marcelo Fernandes
 */
@Configuration
@EnableConfigurationProperties({JettyProperties.class})
@ConditionalOnClass(name = "org.eclipse.jetty.server.Server")
public class JettySpringBootAutoConfiguration implements EmbeddedServletContainerCustomizer {

	@Autowired
	private JettyProperties jettyProperties;

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		if (container instanceof JettyEmbeddedServletContainerFactory) {
			((JettyEmbeddedServletContainerFactory) container).addServerCustomizers(
				new JsfJettyServerCustomizer(this.jettyProperties));
		}
	}
}
