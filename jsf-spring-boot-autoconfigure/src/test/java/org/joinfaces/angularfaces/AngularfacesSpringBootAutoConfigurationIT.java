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

package org.joinfaces.angularfaces;

import javax.faces.application.ViewHandler;

import de.beyondjava.angularFaces.core.tagTransformer.AngularTagDecorator;
import org.joinfaces.javaxfaces.JavaxFaces2_0Properties;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author Lars Grefer
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class AngularfacesSpringBootAutoConfigurationIT {

	@Autowired
	JavaxFaces2_0Properties javaxFaces2_0Properties;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Test
	public void testProperties() {
		assertThat(this.javaxFaces2_0Properties.getFaceletsDecorators()).contains(AngularTagDecorator.class);
	}

	@Test
	public void testServletContext() {
		assertThat(this.webApplicationContext.getServletContext().getInitParameter(ViewHandler.FACELETS_DECORATORS_PARAM_NAME)).contains(AngularTagDecorator.class.getName());
	}

}
