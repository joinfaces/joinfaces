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

package org.joinfaces.undertow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot Auto Configuration of Undertow.
 * Configure undertow to load jsf resources from classpath.
 * @author Marcelo Fernandes
 */
@Configuration
@EnableConfigurationProperties({UndertowProperties.class})
@ConditionalOnClass(name = "io.undertow.Undertow")
public class UndertowSpringBootAutoConfiguration implements EmbeddedServletContainerCustomizer {

	@Autowired
	private UndertowProperties undertowProperties;

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		if (container instanceof UndertowEmbeddedServletContainerFactory) {
			((UndertowEmbeddedServletContainerFactory) container).addDeploymentInfoCustomizers(
				new JsfUndertowDeploymentInfoCustomizer(this.undertowProperties));
		}
	}
}
