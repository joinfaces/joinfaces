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

package com.github.persapiens.jsfboot.butterfaces;

import javax.servlet.ServletContext;

import com.github.persapiens.jsfboot.ServletContextConfigurer;

import de.larmic.butterfaces.resolver.WebXmlParameters;

import lombok.Builder;

/**
 * Servlet context configurer of ButterFaces.
 * @author Marcelo Fernandes
 */
public class ButterfacesServletContextConfigurer extends ServletContextConfigurer {

	private ButterfacesProperties butterfacesProperties;

	@Builder
	public ButterfacesServletContextConfigurer(ButterfacesProperties butterfacesProperties, ServletContext servletContext) {
		super(servletContext, "");
		this.butterfacesProperties = butterfacesProperties;
	}

	@Override
	public void configure() {
		setInitParameter(WebXmlParameters.CTX_PARAM_AJAX_DISABLE_RENDER_REGIONS_ON_REQUEST, this.butterfacesProperties.getAjaxDisableRenderRegionsOnRequest());
		setInitParameter(WebXmlParameters.CTX_PARAM_AJAX_PROCESSING_GLYPHICON, this.butterfacesProperties.getAjaxProcessingGlyphiconOnRequest());
		setInitParameter(WebXmlParameters.CTX_PARAM_AJAX_PROCESSING_TEXT, this.butterfacesProperties.getAjaxProcessingTextOnRequest());
		setInitParameter(WebXmlParameters.CTX_PARAM_AUTO_TRIM_INPUT_FIELDS, this.butterfacesProperties.getAutoTrimInputFields());
		setInitParameter(WebXmlParameters.CTX_PARAM_BOOTSTRAP, this.butterfacesProperties.getProvideBootstrap());
		setInitParameter(WebXmlParameters.CTX_PARAM_COLLAPSING_GLYPHICON, this.butterfacesProperties.getGlyphicon().getCollapsing());
		setInitParameter(WebXmlParameters.CTX_PARAM_EXPANSION_GLYPHICON, this.butterfacesProperties.getGlyphicon().getExpansion());
		setInitParameter(WebXmlParameters.CTX_PARAM_INTEGRATION_PRIMEFACES_DISABLEJQUERY, this.butterfacesProperties.getIntegration().getPrimefaces().getDisableJQuery());
		setInitParameter(WebXmlParameters.CTX_PARAM_JQUERY, this.butterfacesProperties.getProvideJQuery());
		setInitParameter(WebXmlParameters.CTX_PARAM_MAX_LENGTH_TEXT, this.butterfacesProperties.getMaxLengthText());
		setInitParameter(WebXmlParameters.CTX_PARAM_NO_ENTRIES_TEXT, this.butterfacesProperties.getNoEntriesText());
		setInitParameter(WebXmlParameters.CTX_PARAM_OPTIONS_GLYPHICON, this.butterfacesProperties.getGlyphicon().getOptions());
		setInitParameter(WebXmlParameters.CTX_PARAM_ORDER_LEFT_GLYPHICON, this.butterfacesProperties.getGlyphicon().getOrder().getLeft());
		setInitParameter(WebXmlParameters.CTX_PARAM_ORDER_RIGHT_GLYPHICON, this.butterfacesProperties.getGlyphicon().getOrder().getRight());
		setInitParameter(WebXmlParameters.CTX_PARAM_REFRESH_GLYPHICON, this.butterfacesProperties.getGlyphicon().getRefresh());
		setInitParameter(WebXmlParameters.CTX_PARAM_SORT_ASC_GLYPHICON, this.butterfacesProperties.getGlyphicon().getSort().getAscending());
		setInitParameter(WebXmlParameters.CTX_PARAM_SORT_DESC_GLYPHICON, this.butterfacesProperties.getGlyphicon().getSort().getDescending());
		setInitParameter(WebXmlParameters.CTX_PARAM_SORT_GLYPHICON, this.butterfacesProperties.getGlyphicon().getSort().getNone());
		setInitParameter(WebXmlParameters.CTX_PARAM_SPINNER_TEXT, this.butterfacesProperties.getSpinnerText());
		setInitParameter(WebXmlParameters.CTX_PARAM_USE_COMPRESSED_RESOURCES, this.butterfacesProperties.getUseCompressedResources());

		// this bootsfaces parameters should be set to work with butterfaces
		// https://github.com/ButterFaces/bootsfaces-integration
		setInitParameter("net.bootsfaces.get_jquery_from_cdn", "true");
	}
}
