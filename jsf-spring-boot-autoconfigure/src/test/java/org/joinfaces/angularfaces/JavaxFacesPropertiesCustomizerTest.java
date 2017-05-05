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

import java.util.ArrayList;

import javax.faces.view.facelets.TagDecorator;

import de.beyondjava.angularFaces.core.tagTransformer.AngularTagDecorator;
import org.joinfaces.javaxfaces.JavaxFaces2_0Properties;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Lars Grefer
 */
public class JavaxFacesPropertiesCustomizerTest {

	private AngularfacesSpringBootAutoConfiguration.JavaxFacesPropertiesPostProcessor javaxFacesPropertiesCustomizer;

	@Before
	public void setUp() {
		this.javaxFacesPropertiesCustomizer = new AngularfacesSpringBootAutoConfiguration.JavaxFacesPropertiesPostProcessor();
	}

	@Test
	public void testProcessNull() {
		JavaxFaces2_0Properties properties = new JavaxFaces2_0Properties();
		properties.setFaceletsDecorators(null);

		this.javaxFacesPropertiesCustomizer.postProcessAfterInitialization(properties, null);

		assertThat(properties.getFaceletsDecorators()).containsExactly(AngularTagDecorator.class);
	}

	@Test
	public void testProcess() {
		JavaxFaces2_0Properties properties = new JavaxFaces2_0Properties();
		properties.setFaceletsDecorators(new ArrayList<Class<? extends TagDecorator>>());

		this.javaxFacesPropertiesCustomizer.postProcessAfterInitialization(properties, null);

		assertThat(properties.getFaceletsDecorators()).containsOnlyOnce(AngularTagDecorator.class);
	}

	@Test
	public void testProcessDouble() {
		JavaxFaces2_0Properties properties = new JavaxFaces2_0Properties();

		this.javaxFacesPropertiesCustomizer.postProcessAfterInitialization(properties, null);
		this.javaxFacesPropertiesCustomizer.postProcessAfterInitialization(properties, null);

		assertThat(properties.getFaceletsDecorators()).containsOnlyOnce(AngularTagDecorator.class);
	}

}
