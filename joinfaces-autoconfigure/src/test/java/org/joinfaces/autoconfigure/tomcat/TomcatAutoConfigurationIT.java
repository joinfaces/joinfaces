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

package org.joinfaces.autoconfigure.tomcat;

import org.junit.Test;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class TomcatAutoConfigurationIT {

	@Test
	public void customize() {
		TomcatServletWebServerFactory tomcatFactory = new TomcatServletWebServerFactory();

		TomcatAutoConfiguration tomcatAutoConfiguration = new TomcatAutoConfiguration();

		tomcatAutoConfiguration.customize(tomcatFactory);

		assertThat(tomcatFactory.getTomcatContextCustomizers())
			.isNotEmpty();
	}

	@Test
	public void doNotCustomize() {
		TomcatServletWebServerFactory tomcatFactory = new TomcatServletWebServerFactory();

		TomcatAutoConfiguration tomcatAutoConfiguration = new TomcatAutoConfiguration();

		tomcatAutoConfiguration.customize(tomcatFactory);

		assertThat(tomcatFactory.getTomcatConnectorCustomizers())
			.isEmpty();
	}

	@Test
	public void listenerIsNotNull() {
		TomcatAutoConfiguration tomcatAutoConfiguration = new TomcatAutoConfiguration();

		JsfTomcatApplicationListener jsfTomcatApplicationListener = tomcatAutoConfiguration.jsfTomcatApplicationListener();

		assertThat(jsfTomcatApplicationListener)
			.isNotNull();
	}
}
