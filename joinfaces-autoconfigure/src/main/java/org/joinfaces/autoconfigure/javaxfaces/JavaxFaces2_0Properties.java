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
import jakarta.faces.context.PartialViewContext;

import lombok.Data;
import org.joinfaces.autoconfigure.servlet.initparams.ServletContextInitParameter;
import org.joinfaces.autoconfigure.servlet.initparams.ServletContextInitParameterProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

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

	@NestedConfigurationProperty
	private final Partial partial = new Partial();

	/**
	 * Partial class for execute, render and resetValues parameters.
	 */
	@Data
	public static class Partial {

		/**
		 * The request parameter name whose request parameter value is a
		 * {@code Collection} of client identifiers identifying the
		 * components that must be processed during the
		 * <em>Apply Request Values</em>, <em>Process Validations</em>, and
		 * <em>Update Model Values</em> phases of the request processing
		 * lifecycle.
		 *
		 * @since 2.0
		 */
		@ServletContextInitParameter(PartialViewContext.PARTIAL_EXECUTE_PARAM_NAME)
		private Boolean execute;

		/**
		 * The request parameter name whose request parameter value is a
		 * {@code Collection} of client identifiers identifying the
		 * components that must be processed during the
		 * <em>Render Response</em> phase of the request processing
		 * lifecycle.
		 *
		 * @since 2.0
		 */
		@ServletContextInitParameter(PartialViewContext.PARTIAL_RENDER_PARAM_NAME)
		private Boolean render;
	}

}
