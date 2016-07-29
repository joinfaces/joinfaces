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

package org.joinfaces.myfaces;

import javax.servlet.ServletContext;

import org.testng.annotations.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = MyfacesSpringBootAutoConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Test
public class MyfacesServletContextConfigurerIT extends AbstractTestNGSpringContextTests {

	@Autowired
	private MyfacesProperties myfacesProperties;

	public void testConfigure() {
		ServletContext servletContext = new MockServletContext();

		MyfacesServletContextConfigurer myfacesServletContextConfigurer = MyfacesServletContextConfigurer.builder()
			.myfacesProperties(this.myfacesProperties)
			.servletContext(servletContext)
			.build();

		myfacesServletContextConfigurer.configure();

		assertThat(servletContext.getInitParameter(MyfacesServletContextConfigurer.PREFFIX + "STRICT_JSF_2_CC_EL_RESOLVER"))
			.isEqualTo("myElResolver");
	}

}
