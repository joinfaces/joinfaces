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

import java.util.ArrayList;

import javax.faces.view.facelets.TagDecorator;

import de.beyondjava.angularFaces.core.tagTransformer.AngularTagDecorator;
import org.joinfaces.configuration.PropertiesAutoConfiguration;
import org.joinfaces.configuration.PropertiesCustomizer;
import org.joinfaces.javaxfaces.JavaxFaces2_0Properties;
import org.joinfaces.javaxfaces.JavaxFacesSpringBootAutoConfiguration;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot Auto Configuration of AngularFaces.
 *
 * @author Marcelo Fernandes
 */
@Configuration
@EnableConfigurationProperties(AngularfacesProperties.class)
@ConditionalOnClass(name = "de.beyondjava.angularFaces.core.ELTools")
@AutoConfigureBefore(JavaxFacesSpringBootAutoConfiguration.class)
@ConditionalOnWebApplication
public class AngularfacesSpringBootAutoConfiguration extends PropertiesAutoConfiguration<AngularfacesProperties> {

	@Bean
	public PropertiesCustomizer<JavaxFaces2_0Properties> propertiesCustomizer() {
		return new PropertiesCustomizer<JavaxFaces2_0Properties>() {
			@Override
			public void process(JavaxFaces2_0Properties properties) {
				if (properties.getFaceletsDecorators() == null) {
					properties.setFaceletsDecorators(new ArrayList<Class<? extends TagDecorator>>());
				}

				properties.getFaceletsDecorators().add(AngularTagDecorator.class);
			}
		};
	}
}
