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

package org.joinfaces.bootsfaces;

import lombok.Getter;
import lombok.Setter;
import net.bootsfaces.C;
import org.joinfaces.configuration.NestedProperty;
import org.joinfaces.configuration.ServletContextInitParameter;
import org.joinfaces.configuration.ServletContextInitParameterConfigurationProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties of BootsFaces.
 * Taken from
 * http://search.maven.org/remotecontent?filepath=net/bootsfaces/bootsfaces/0.8.6/bootsfaces-0.8.6-javadoc.jar
 * and from https://github.com/TheCoder4eu/BootsFaces-OSP
 * @author Marcelo Fernandes
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "jsf.bootsfaces")
public class BootsfacesProperties implements ServletContextInitParameterConfigurationProperties {

	private static final String PREFFIX = "net.bootsfaces.";

	@NestedProperty
	private Defaults defaults = new Defaults();

	/**
	 * deactivate FontAwesome support if the no-fa facet is found in the h:head
	 * tag.
	 */
	@ServletContextInitParameter(PREFFIX + "get_fontawesome_from_cdn")
	private Boolean getFontawesomeFromCdn;

	@ServletContextInitParameter(PREFFIX + "blockUI")
	private Boolean blockUI;

	/**
	 * BootsFaces_THEME - controls the Theme to use: the value "default" is
	 * plain Bootstrap, the other options are a Bootswach Theme name (lowercase)
	 * or "custom". If custom is chosen, you will have to provide your custom
	 * CSS in the "other" folder.
	 */
	@ServletContextInitParameter(C.P_THEME)
	private String theme;

	/**
	 * BootsFaces_USETHEME - as in previous versions controls if the current
	 * theme is to be rendered in the Flat variant (default) or in its Enhanced
	 * variant, with shadows and decorations turned on.
	 */
	@ServletContextInitParameter(C.P_USETHEME)
	private Boolean usetheme;

	@ServletContextInitParameter(C.P_VIEWPORT)
	private Boolean useViewport;

	@ServletContextInitParameter(PREFFIX + "get_jquery_from_cdn")
	private Boolean getJqueryFromCdn;

	@ServletContextInitParameter(PREFFIX + "get_jqueryui_from_cdn")
	private Boolean getJqueryuiFromCdn;

	@ServletContextInitParameter(PREFFIX + "get_bootstrap_from_cdn")
	private Boolean getBootstrapFromCdn;

	/**
	 * Defaults class of renderLabel property.
	 */
	@Getter
	@Setter
	public static class Defaults {

		@ServletContextInitParameter(PREFFIX + "defaults.renderLabel")
		private String renderLabel;

		@ServletContextInitParameter(PREFFIX + "defaults.decorator")
		private Boolean decorator;
	}
}
