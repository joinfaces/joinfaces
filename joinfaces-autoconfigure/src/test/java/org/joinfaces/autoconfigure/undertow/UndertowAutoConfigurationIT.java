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

package org.joinfaces.autoconfigure.undertow;

import java.io.IOException;

import io.undertow.servlet.api.DeploymentInfo;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.embedded.undertow.UndertowDeploymentInfoCustomizer;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class UndertowAutoConfigurationIT {

	@Autowired
	@Qualifier("jsfUndertowFactoryCustomizer")
	private WebServerFactoryCustomizer<UndertowServletWebServerFactory> jsfUndertowFactoryCustomizer;

	@Test
	void customize() throws IOException {
		UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();

		this.jsfUndertowFactoryCustomizer.customize(factory);

		UndertowDeploymentInfoCustomizer undertowDeploymentInfoCustomizer
			= factory.getDeploymentInfoCustomizers().iterator().next();

		DeploymentInfo deploymentInfo = new DeploymentInfo();
		deploymentInfo.setClassLoader(this.getClass().getClassLoader());

		undertowDeploymentInfoCustomizer.customize(deploymentInfo);

		assertThat(deploymentInfo.getResourceManager().getResource("testUndertow.txt"))
			.isNotNull();
	}
}
