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

package com.github.persapiens.jsfboot.angularfaces;

import javax.faces.application.ViewHandler;
import javax.servlet.ServletContext;

import com.github.persapiens.jsfboot.ServletContextConfigurer;

import de.beyondjava.angularFaces.core.tagTransformer.AngularTagDecorator;

import lombok.Builder;

/**
 * Servlet context configurer of AngularFaces.
 * @author Marcelo Fernandes
 */
public class AngularfacesServletContextConfigurer extends ServletContextConfigurer {

	private AngularfacesProperties angularfacesProperties;

	/**
	 * Preffix of original AngularFaces context parameters.
	 */
	public static final String PREFFIX = "AngularFaces.";

	@Builder
	public AngularfacesServletContextConfigurer(AngularfacesProperties angularfacesProperties, ServletContext servletContext) {
		super(servletContext, "");
		this.angularfacesProperties = angularfacesProperties;
	}

	@Override
	public void configure() {
		setInitParameter(PREFFIX + "addLabels", this.angularfacesProperties.getAddLabels());
		setInitParameter(PREFFIX + "addMessages", this.angularfacesProperties.getAddMessages());
		setInitParameter(PREFFIX + "translation", this.angularfacesProperties.getTranslation());
		setInitParameter(PREFFIX + "includeAngularJS", this.angularfacesProperties.getIncludeAngularJS());
		setInitParameter(PREFFIX + "includeJQuery", this.angularfacesProperties.getIncludeJQuery());
		setInitParameter(PREFFIX + "includeJQueryUI", this.angularfacesProperties.getIncludeJQueryUI());
		setInitParameter(PREFFIX + "includeAngularMessages", this.angularfacesProperties.getIncludeAngularMessages());
		setInitParameter(PREFFIX + "clientSideMessages", this.angularfacesProperties.getClientSideMessages());
		setInitParameter(PREFFIX + "includeMainJS", this.angularfacesProperties.getIncludeMainJS());

		setInitParameter(ViewHandler.FACELETS_DECORATORS_PARAM_NAME, AngularTagDecorator.class.getName());
	}
}
