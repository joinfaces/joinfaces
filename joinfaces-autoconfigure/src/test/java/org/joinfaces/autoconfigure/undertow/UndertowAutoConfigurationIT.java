/*
 * Copyright 2016-2017 the original author or authors.
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

package org.joinfaces.autoconfigure.undertow;

import java.io.IOException;

import io.undertow.servlet.api.DeploymentInfo;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.embedded.undertow.UndertowDeploymentInfoCustomizer;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UndertowAutoConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UndertowAutoConfigurationIT {

	@Autowired
	private UndertowAutoConfiguration undertowAutoConfiguration;

	@Test
	public void customize() throws IOException {
		UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();

		this.undertowAutoConfiguration.jsfUndertowFactoryCustomizer().customize(factory);

		UndertowDeploymentInfoCustomizer undertowDeploymentInfoCustomizer
			= factory.getDeploymentInfoCustomizers().iterator().next();

		DeploymentInfo deploymentInfo = new DeploymentInfo();
		deploymentInfo.setClassLoader(this.getClass().getClassLoader());

		undertowDeploymentInfoCustomizer.customize(deploymentInfo);

		assertThat(deploymentInfo.getResourceManager().getResource("testUndertow.txt"))
			.isNotNull();
	}
}
