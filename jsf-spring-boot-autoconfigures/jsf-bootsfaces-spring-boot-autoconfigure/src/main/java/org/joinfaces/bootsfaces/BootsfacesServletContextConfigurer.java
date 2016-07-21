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

import javax.servlet.ServletContext;

import org.joinfaces.ServletContextConfigurer;

import lombok.Builder;

import net.bootsfaces.C;

/**
 * Servlet context configurer of BootsFaces.
 * @author Marcelo Fernandes
 */
public class BootsfacesServletContextConfigurer extends ServletContextConfigurer {

	private BootsfacesProperties bootsfacesProperties;

	/**
	 * Preffix of original BootsFaces configuration.
	 */
	public static final String PREFFIX = "net.bootsfaces.";

	@Builder
	public BootsfacesServletContextConfigurer(BootsfacesProperties bootsfacesProperties, ServletContext servletContext) {
		super(servletContext, "");
		this.bootsfacesProperties = bootsfacesProperties;
	}

	@Override
	public void configure() {
		setInitParameterString(C.P_THEME, this.bootsfacesProperties.getTheme());
		setInitParameterBoolean(C.P_USETHEME, this.bootsfacesProperties.getUsetheme());
		setInitParameterBoolean(C.P_VIEWPORT, this.bootsfacesProperties.getUseViewport());
		setInitParameterString(PREFFIX + "defaults.renderLabel", this.bootsfacesProperties.getDefaults().getRenderLabel());
		setInitParameterBoolean(PREFFIX + "get_fontawesome_from_cdn", this.bootsfacesProperties.getGetFontawesomeFromCdn());
		setInitParameterBoolean(PREFFIX + "get_jquery_from_cdn", this.bootsfacesProperties.getGetJqueryFromCdn());
		setInitParameterBoolean(PREFFIX + "get_jqueryui_from_cdn", this.bootsfacesProperties.getGetJqueryuiFromCdn());
		setInitParameterBoolean(PREFFIX + "get_bootstrap_from_cdn", this.bootsfacesProperties.getGetBootstrapFromCdn());
		setInitParameterBoolean(PREFFIX + "blockUI", this.bootsfacesProperties.getBlockUI());
	}
}
