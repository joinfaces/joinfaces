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

package org.joinfaces.mojarra;

import javax.servlet.ServletContext;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MojarraSpringBootAutoConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class MojarraServletContextConfigurerIT {

	@Autowired
	private MojarraProperties mojarraProperties;

	@Test
	public void testConfigure() {
		ServletContext servletContext = new MockServletContext();

		MojarraServletContextConfigurer mojarraServletContextConfigurer = MojarraServletContextConfigurer.builder()
			.mojarraProperties(this.mojarraProperties)
			.servletContext(servletContext)
			.build();

		mojarraServletContextConfigurer.configure();

		assertThat(servletContext.getInitParameter(MojarraServletContextConfigurer.PREFFIX + ".clientStateTimeout")).isEqualTo("10");
	}

}
