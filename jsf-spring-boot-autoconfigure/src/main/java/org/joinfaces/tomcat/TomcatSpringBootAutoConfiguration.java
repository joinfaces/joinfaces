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

package org.joinfaces.tomcat;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Creating tomcat auto configuration to enable jsf read facelets at integration
 * tests.
 *
 * @author Marcelo Fernandes
 */
@ConditionalOnClass(name = "org.apache.catalina.Context")
@Configuration
public class TomcatSpringBootAutoConfiguration implements EmbeddedServletContainerCustomizer {

	private JsfTomcatContextCustomizer customizer = new JsfTomcatContextCustomizer();

	@Bean
	public JsfTomcatApplicationListener jsfTomcatApplicationListener() {
		return JsfTomcatApplicationListener.builder().context(this.customizer.getContext()).build();
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		if (container instanceof TomcatEmbeddedServletContainerFactory) {
			TomcatEmbeddedServletContainerFactory tomcatFactory
				= (TomcatEmbeddedServletContainerFactory) container;

			tomcatFactory.addContextCustomizers(this.customizer);
		}
	}
}
