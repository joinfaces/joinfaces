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

package org.joinfaces.angularfaces;

import javax.faces.application.ViewHandler;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import de.beyondjava.angularFaces.core.tagTransformer.AngularTagDecorator;

import org.testng.annotations.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringApplicationConfiguration(classes = AngularfacesSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class AngularfacesServletContextInitializerIT extends AbstractTestNGSpringContextTests {

	@Autowired
	private AngularfacesProperties angularfacesProperties;

	public void testOnStartup() throws ServletException {
		AngularfacesServletContextInitializer angularfacesServletContextInitializer
			= new AngularfacesServletContextInitializer(this.angularfacesProperties);

		ServletContext servletContext = new MockServletContext();

		angularfacesServletContextInitializer.onStartup(servletContext);

		assertThat(servletContext.getInitParameter(AngularfacesServletContextConfigurer.PREFFIX + "addLabels"))
			.isEqualTo("true");
		assertThat(servletContext.getInitParameter(ViewHandler.FACELETS_DECORATORS_PARAM_NAME))
			.isEqualTo(AngularTagDecorator.class.getName());
	}

}
