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

package org.joinfaces.butterfaces;

import de.larmic.butterfaces.resolver.WebXmlParameters;
import lombok.Getter;
import lombok.Setter;
import org.joinfaces.configuration.NestedProperty;
import org.joinfaces.configuration.ServletContextInitParameter;
import org.joinfaces.configuration.ServletContextInitParameterConfigurationProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties of ButterFaces.
 * Taken from
 * https://butterfaces.gitbooks.io/butterfaces/content/configuration.html and
 * de.larmic.butterfaces.resolver.WebXmlParameters.java
 * @author Marcelo Fernandes
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "jsf.butterfaces")
public class ButterfacesProperties implements ServletContextInitParameterConfigurationProperties {

	/**
	 * b:commandLink components comes with ajaxDisableRenderRegionsOnRequest to
	 * crossfade render regions while ajax request is running. To configure this
	 * for global usage add following parameter
	 */
	@ServletContextInitParameter(WebXmlParameters.CTX_PARAM_AJAX_DISABLE_RENDER_REGIONS_ON_REQUEST)
	private Boolean ajaxDisableRenderRegionsOnRequest;

	@ServletContextInitParameter(WebXmlParameters.CTX_PARAM_AJAX_PROCESSING_GLYPHICON)
	private String ajaxProcessingGlyphiconOnRequest;

	@ServletContextInitParameter(WebXmlParameters.CTX_PARAM_AJAX_PROCESSING_TEXT)
	private String ajaxProcessingTextOnRequest;

	/**
	 * By default all input fields will be trimed after submit.
	 */
	@ServletContextInitParameter(WebXmlParameters.CTX_PARAM_AUTO_TRIM_INPUT_FIELDS)
	private Boolean autoTrimInputFields;

	/**
	 * As default ButterFaces comes with an actual version of Bootstrap 3.x. To
	 * disable ButterFaces Boostrap version because of using your own version
	 * you have to change following parameter
	 */
	@ServletContextInitParameter(WebXmlParameters.CTX_PARAM_BOOTSTRAP)
	private Boolean provideBootstrap;

	@NestedProperty
	private Glyphicon glyphicon = new Glyphicon();

	@NestedProperty
	private Treebox treebox = new Treebox();

	/**
	 * As default ButterFaces comes with an actual version of jQuery 2.x. To
	 * disable ButterFaces jQuery version because of using your own version you
	 * have to change following parameter
	 */
	@ServletContextInitParameter(WebXmlParameters.CTX_PARAM_JQUERY)
	private Boolean provideJQuery;

	/**
	 * When using maxlength parameter counting text will be {0} of {1}
	 * characters.
	 */
	@ServletContextInitParameter(WebXmlParameters.CTX_PARAM_MAX_LENGTH_TEXT)
	private String maxLengthText;

	/**
	 * b:tree and b:treeBox are trivial components and can be configured by
	 * components attribute or by changeing following parameters.
	 */
	@ServletContextInitParameter(WebXmlParameters.CTX_PARAM_NO_ENTRIES_TEXT)
	private String noEntriesText;

	/**
	 * b:tree and b:treeBox are trivial components and can be configured by
	 * components attribute or by changeing following parameters.
	 */
	@ServletContextInitParameter(WebXmlParameters.CTX_PARAM_SPINNER_TEXT)
	private String spinnerText;

	/**
	 * Each components comes up with it's own javascript and css resources. If
	 * you want to use ButterFaces resources (i.e. Bootstrap or jQuery) without
	 * any ButterFaces component use b:activateLibraries to load all resources
	 * to html. This tags add javascript and css resources for all existing
	 * components. In addition to that you may activate compression by web.xml
	 * This only works if you are using PROJECT_STAGE = Production
	 */
	@ServletContextInitParameter(WebXmlParameters.CTX_PARAM_USE_COMPRESSED_RESOURCES)
	private Boolean useCompressedResources;

	@NestedProperty
	private Integration integration = new Integration();

	/**
	 * Treebox class of specific tree box properties.
	 */
	@Getter
	@Setter
	public static class Treebox {
		@ServletContextInitParameter(WebXmlParameters.CTX_PARAM_TREEBOX_SHOW_CLEAR_BUTTON)
		private Boolean showClearButton;
	}

	/**
	 * Glyphicon class of collapsing, expansion, options, refresh and order properties.
	 */
	@Getter
	@Setter
	public static class Glyphicon {

		@ServletContextInitParameter(WebXmlParameters.CTX_PARAM_COLLAPSING_GLYPHICON)
		private String collapsing;

		@ServletContextInitParameter(WebXmlParameters.CTX_PARAM_EXPANSION_GLYPHICON)
		private String expansion;

		@ServletContextInitParameter(WebXmlParameters.CTX_PARAM_OPTIONS_GLYPHICON)
		private String options;

		@ServletContextInitParameter(WebXmlParameters.CTX_PARAM_REFRESH_GLYPHICON)
		private String refresh;

		@NestedProperty
		private Order order = new Order();

		@NestedProperty
		private Sort sort = new Sort();

		/**
		 * Order class of left and right properties.
		 */
		@Getter
		@Setter
		public static class Order {

			@ServletContextInitParameter(WebXmlParameters.CTX_PARAM_ORDER_LEFT_GLYPHICON)
			private String left;
			@ServletContextInitParameter(WebXmlParameters.CTX_PARAM_ORDER_RIGHT_GLYPHICON)
			private String right;
		}

		/**
		 * Sort class of ascending, descending and none properties.
		 */
		@Getter
		@Setter
		public static class Sort {

			@ServletContextInitParameter(WebXmlParameters.CTX_PARAM_SORT_ASC_GLYPHICON)
			private String ascending;
			@ServletContextInitParameter(WebXmlParameters.CTX_PARAM_SORT_DESC_GLYPHICON)
			private String descending;
			@ServletContextInitParameter(WebXmlParameters.CTX_PARAM_SORT_GLYPHICON)
			private String none;
		}
	}

	/**
	 * Integration class of primefaces properties.
	 */
	@Getter
	public static class Integration {

		@NestedProperty
		private Primefaces primefaces = new Primefaces();

		/**
		 * Primefaces class of disableJQuery properties.
		 */
		@Getter
		@Setter
		public static class Primefaces {

			/**
			 * As well as ButterFaces PrimeFaces comes with jQuery. By default
			 * PrimeFaces JQuery will be removed from resources. You can change
			 * this behaviour by changeing following parameter
			 */
			@ServletContextInitParameter(WebXmlParameters.CTX_PARAM_INTEGRATION_PRIMEFACES_DISABLEJQUERY)
			private Boolean disableJQuery;
		}
	}
}
