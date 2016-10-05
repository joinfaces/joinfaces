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

import java.net.MalformedURLException;

import org.junit.Test;

import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class TomcatSpringBootAutoConfigurationIT {

	@Test
	public void customize() throws MalformedURLException {
		TomcatEmbeddedServletContainerFactory tomcatFactory = new TomcatEmbeddedServletContainerFactory();

		TomcatSpringBootAutoConfiguration tomcatSpringBootAutoConfiguration = new TomcatSpringBootAutoConfiguration();

		tomcatSpringBootAutoConfiguration.customize(tomcatFactory);

		assertThat(tomcatFactory.getTomcatContextCustomizers())
			.isNotEmpty();
	}

	@Test
	public void doNotCustomize() throws MalformedURLException {
		JettyEmbeddedServletContainerFactory tomcatFactory = new JettyEmbeddedServletContainerFactory();

		TomcatSpringBootAutoConfiguration tomcatSpringBootAutoConfiguration = new TomcatSpringBootAutoConfiguration();

		tomcatSpringBootAutoConfiguration.customize(tomcatFactory);

		assertThat(tomcatFactory.getServerCustomizers())
			.isEmpty();
	}

	@Test
	public void listenerIsNotNull() {
		TomcatSpringBootAutoConfiguration tomcatSpringBootAutoConfiguration = new TomcatSpringBootAutoConfiguration();

		JsfTomcatApplicationListener jsfTomcatApplicationListener = tomcatSpringBootAutoConfiguration.jsfTomcatApplicationListener();

		assertThat(jsfTomcatApplicationListener)
			.isNotNull();
	}
}
