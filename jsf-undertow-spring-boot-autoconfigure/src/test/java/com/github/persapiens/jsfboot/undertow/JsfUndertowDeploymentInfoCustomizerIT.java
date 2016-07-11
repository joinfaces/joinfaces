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

package com.github.persapiens.jsfboot.undertow;

import java.io.IOException;

import io.undertow.servlet.api.DeploymentInfo;

import org.testng.annotations.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.undertow.UndertowDeploymentInfoCustomizer;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringApplicationConfiguration(classes = UndertowSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class JsfUndertowDeploymentInfoCustomizerIT extends AbstractTestNGSpringContextTests {

	@Autowired
	private UndertowProperties undertowProperties;

	public void testGetResource() throws IOException {
		UndertowDeploymentInfoCustomizer undertowDeploymentInfoCustomizer
			= new JsfUndertowDeploymentInfoCustomizer(this.undertowProperties);

		DeploymentInfo deploymentInfo = new DeploymentInfo();

		undertowDeploymentInfoCustomizer.customize(deploymentInfo);

		assertThat(deploymentInfo.getResourceManager().getResource("test.txt"))
			.isNotNull();
	}

}
