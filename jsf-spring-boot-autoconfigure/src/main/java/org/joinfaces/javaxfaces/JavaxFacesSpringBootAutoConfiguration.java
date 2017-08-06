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

import javax.faces.context.FacesContext;
import javax.faces.flow.FlowHandler;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Auto configuration for Standard Javax Faces Properties.
 *
 * @author Marcelo Fernandes
 */
@Configuration
@ConditionalOnClass(FacesContext.class)
@ConditionalOnWebApplication
public class JavaxFacesSpringBootAutoConfiguration {

	/**
	 * Auto configuration for JSF 2.0.
	 */
	@Configuration
	@EnableConfigurationProperties(JavaxFaces2_0Properties.class)
	public static class JavaxFaces2_0AutoConfiguration {
	}

	/**
	 * Auto configuration for JSF 2.1.
	 */
	@Configuration
	@EnableConfigurationProperties(JavaxFaces2_1Properties.class)
	public static class JavaxFaces2_1AutoConfiguration {
	}

	/**
	 * Auto configuration for JSF 2.2.
	 */
	@Configuration
	@EnableConfigurationProperties(JavaxFaces2_2Properties.class)
	@ConditionalOnClass(FlowHandler.class)
	public static class JavaxFaces2_2AutoConfiguration {
	}

}
