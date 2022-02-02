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

package org.joinfaces.autoconfigure.javaxfaces;

import java.util.List;

import jakarta.faces.application.ResourceHandler;

import lombok.Data;
import org.joinfaces.autoconfigure.servlet.initparams.ServletContextInitParameter;
import org.joinfaces.autoconfigure.servlet.initparams.ServletContextInitParameterProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * {@link ConfigurationProperties Configuration properties} for JSF 2.0.
 *
 * @author Marcelo Fernandes
 * @author Lars Grefer
 * @see <a href="https://www.jcp.org/en/jsr/detail?id=314">JSR-314</a>
 * @see <a href="https://stackoverflow.com/a/17341945/3574494">https://stackoverflow.com/a/17341945/3574494</a>
 */
@Data
@ConfigurationProperties(prefix = "joinfaces.jsf")
public class JavaxFaces2_0Properties implements ServletContextInitParameterProperties {

	/**
	 * A space separated list of resource extensions for types that shouldn't be
	 * served by the ResourceHandler implementation. See the specification for
	 * further details.
	 */
	@ServletContextInitParameter(value = ResourceHandler.RESOURCE_EXCLUDES_PARAM_NAME, listSeparator = ",")
	private List<String> resourceExcludes;

}
