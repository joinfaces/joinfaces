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

import lombok.Getter;
import lombok.Setter;

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
public class ButterfacesProperties {

	/**
	 * b:commandLink components comes with ajaxDisableRenderRegionsOnRequest to
	 * crossfade render regions while ajax request is running. To configure this
	 * for global usage add following parameter
	 */
	private Boolean ajaxDisableRenderRegionsOnRequest;

	private String ajaxProcessingGlyphiconOnRequest;

	private String ajaxProcessingTextOnRequest;

	/**
	 * By default all input fields will be trimed after submit.
	 */
	private Boolean autoTrimInputFields;

	/**
	 * As default ButterFaces comes with an actual version of Bootstrap 3.x. To
	 * disable ButterFaces Boostrap version because of using your own version
	 * you have to change following parameter
	 */
	private Boolean provideBootstrap;

	private Glyphicon glyphicon = new Glyphicon();

	/**
	 * As default ButterFaces comes with an actual version of jQuery 2.x. To
	 * disable ButterFaces jQuery version because of using your own version you
	 * have to change following parameter
	 */
	private Boolean provideJQuery;

	/**
	 * When using maxlength parameter counting text will be {0} of {1}
	 * characters.
	 */
	private String maxLengthText;

	/**
	 * b:tree and b:treeBox are trivial components and can be configured by
	 * components attribute or by changeing following parameters.
	 */
	private String noEntriesText;

	/**
	 * b:tree and b:treeBox are trivial components and can be configured by
	 * components attribute or by changeing following parameters.
	 */
	private String spinnerText;

	/**
	 * Each components comes up with it's own javascript and css resources. If
	 * you want to use ButterFaces resources (i.e. Bootstrap or jQuery) without
	 * any ButterFaces component use b:activateLibraries to load all resources
	 * to html. This tags add javascript and css resources for all existing
	 * components. In addition to that you may activate compression by web.xml
	 * This only works if you are using PROJECT_STAGE = Production
	 */
	private Boolean useCompressedResources;

	private Integration integration = new Integration();

	/**
	 * Glyphicon class of collapsing, expansion, options, refresh and order properties.
	 */
	@Getter
	@Setter
	public static class Glyphicon {

		private String collapsing;

		private String expansion;

		private String options;

		private String refresh;

		private Order order = new Order();

		private Sort sort = new Sort();

		/**
		 * Order class of left and right properties.
		 */
		@Getter
		@Setter
		public static class Order {

			private String left;
			private String right;
		}

		/**
		 * Sort class of ascending, descending and none properties.
		 */
		@Getter
		@Setter
		public static class Sort {

			private String ascending;
			private String descending;
			private String none;
		}
	}

	/**
	 * Integration class of primefaces properties.
	 */
	@Getter
	public static class Integration {

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
			private Boolean disableJQuery;
		}
	}
}
