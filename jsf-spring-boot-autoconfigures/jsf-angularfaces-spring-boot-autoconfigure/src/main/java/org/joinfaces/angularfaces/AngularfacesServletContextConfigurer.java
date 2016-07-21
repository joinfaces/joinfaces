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

import javax.faces.application.ViewHandler;
import javax.servlet.ServletContext;

import org.joinfaces.ServletContextConfigurer;

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
		setInitParameterBoolean(PREFFIX + "addLabels", this.angularfacesProperties.getAddLabels());
		setInitParameterBoolean(PREFFIX + "addMessages", this.angularfacesProperties.getAddMessages());
		setInitParameterBoolean(PREFFIX + "translation", this.angularfacesProperties.getTranslation());
		setInitParameterBoolean(PREFFIX + "includeAngularJS", this.angularfacesProperties.getIncludeAngularJS());
		setInitParameterBoolean(PREFFIX + "includeJQuery", this.angularfacesProperties.getIncludeJQuery());
		setInitParameterBoolean(PREFFIX + "includeJQueryUI", this.angularfacesProperties.getIncludeJQueryUI());
		setInitParameterBoolean(PREFFIX + "includeAngularMessages", this.angularfacesProperties.getIncludeAngularMessages());
		setInitParameterBoolean(PREFFIX + "clientSideMessages", this.angularfacesProperties.getClientSideMessages());
		setInitParameterBoolean(PREFFIX + "includeMainJS", this.angularfacesProperties.getIncludeMainJS());

		setInitParameterString(ViewHandler.FACELETS_DECORATORS_PARAM_NAME, AngularTagDecorator.class.getName());
	}
}
