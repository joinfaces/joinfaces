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

package org.joinfaces.autoconfigure.angularfaces;

import java.util.List;

import javax.faces.view.facelets.TagDecorator;

import de.beyondjava.angularFaces.core.tagTransformer.AngularTagDecorator;
import org.joinfaces.autoconfigure.javaxfaces.JavaxFaces2_0Properties;
import org.joinfaces.autoconfigure.javaxfaces.JavaxFacesAutoConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

public class AngularfacesAutoConfigurationTest {

	private WebApplicationContextRunner webApplicationContextRunner;

	@BeforeEach
	public void setUp() {
		this.webApplicationContextRunner = new WebApplicationContextRunner()
				.withConfiguration(AutoConfigurations.of(AngularfacesAutoConfiguration.class, JavaxFacesAutoConfiguration.class));
	}

	@Test
	public void testTagDecoratorAdded() {
		this.webApplicationContextRunner
				.run(context -> {
					List<Class<? extends TagDecorator>> faceletsDecorators = context.getBean(JavaxFaces2_0Properties.class).getFaceletsDecorators();
					assertThat(faceletsDecorators).contains(AngularTagDecorator.class);
				});
	}

	@Test
	public void testTagDecoratorAlreadyPresent() {
		this.webApplicationContextRunner
				.withPropertyValues("joinfaces.jsf.facelets-decorators=de.beyondjava.angularFaces.core.tagTransformer.AngularTagDecorator")
				.run(context -> {
					List<Class<? extends TagDecorator>> faceletsDecorators = context.getBean(JavaxFaces2_0Properties.class).getFaceletsDecorators();
					assertThat(faceletsDecorators).containsOnlyOnce(AngularTagDecorator.class);
				});
	}
}
