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

package org.joinfaces.javaxfaces;

import javax.faces.application.ProjectStage;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JavaxFacesSpringBootAutoConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class JavaxFacesServletContextInitializerIT {

	@Autowired
	private JavaxFacesProperties javaxFacesProperties;

	@Test
	public void testOnStartup() throws ServletException {
		JavaxFacesServletContextInitializer javaxFacesServletContextInitializer
			= new JavaxFacesServletContextInitializer(this.javaxFacesProperties);

		ServletContext servletContext = new MockServletContext();

		javaxFacesServletContextInitializer.onStartup(servletContext);

		assertThat(servletContext.getInitParameter(ProjectStage.PROJECT_STAGE_PARAM_NAME)).isEqualTo("Development");
	}

}
