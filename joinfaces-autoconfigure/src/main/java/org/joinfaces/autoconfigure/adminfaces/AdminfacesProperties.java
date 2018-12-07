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

package org.joinfaces.autoconfigure.adminfaces;

import java.io.Serializable;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties of AdminFaces.
 * <p>
 * Taken from
 * <ul>
 * <li>https://github.com/adminfaces/admin-template#configuration</li>
 * <li>https://github.com/adminfaces/admin-template/blob/master/src/main/java/com/github/adminfaces/template/config/AdminConfig.java</li>
 * </ul>
 *
 * @author Marcelo Fernandes
 */
@Data
@ConfigurationProperties(prefix = "joinfaces.adminfaces")
public class AdminfacesProperties implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * login page location (relative to webapp). It you only be used if you configure Admin Session.
	 */
	private String loginPage;

	/**
	 * index page location. User will be redirected to it when it access app root (contextPath/).
	 */
	private String indexPage;

	/**
	 * Date format used in error page (500.xhtml), by default it is JVM default format.
	 */
	private String dateFormat;

	/**
	 * Template path.
	 */
	private String templatePath;

	/**
	 * Number of breadcrumbs to queue before removing the older ones.
	 */
	private Integer breadcrumbSize;

	/**
	 * When false, p:messages defined in admin template will not be rendered.
	 */
	private Boolean renderMessages;

	/**
	 * When false ajaxStatus, which triggers the loading bar on every ajax request,
	 * will not be rendered.
	 */
	private Boolean renderAjaxStatus;

	/**
	 * Disables AdminFilter, responsible for redirecting user after session timeout,
	 * sending user to logon page when it is not logged in among other things.
	 */
	private Boolean disableFilter;

	/**
	 * When false, the breadCrumb component, declared in admin template, will not be rendered.
	 */
	private Boolean renderBreadCrumb;

	/**
	 * If true will make left menu touch enable (can be closed or opened via touch).
	 * Can be enable/disabled per page with <ui:param name="enableSlideMenu" value="false" />.
	 */
	private Boolean enableSlideMenu;

	/**
	 * When true it will create a wave/ripple effect on elements specified by rippleElements.
	 */
	private Boolean enableRipple;

	/**
	 * A list of comma separated list of (jquery) selector which elements will be
	 * affected by ripple effect.
	 */
	private String rippleElements;

	/**
	 * Default template skin.
	 */
	private String skin;

	/**
	 * Automatic shows navbar when users scrolls page up (on small screens).
	 * Can be enable/disabled per page with <ui:param name="autoShowNavbar" value="false" />.
	 */
	private Boolean autoShowNavbar;

	/**
	 * Comma separated resources (pages or urls) to be skiped by AdminFilter.
	 * Ex: /rest, /pages/car-list. Note that by default the filter skips pages
	 * under CONTEXT/public/ folder.
	 */
	private String ignoredResources;

	/**
	 * image used for the loading popup. It must be under webapp/resources/images folder.
	 */
	private String loadingImage;

	/**
	 * Removes extension suffix from breadCrumb links.
	 */
	private Boolean extensionLessUrls;

	/**
	 * When true it will activate control sidebar component.
	 */
	private Boolean renderControlSidebar;

	/**
	 * When true the ripple effect will be enabled only on mobile (small) screens.
	 */
	private Boolean rippleMobileOnly;

	/**
	 * Enables or disables menu search.
	 */
	private Boolean renderMenuSearch;

	/**
	 * If true PrimeFaces info messages will be hidden after a certain timeout.
	 */
	private Boolean autoHideMessages;

	/**
	 * Timeout to hide info messages. Note that the timeout is also composed by
	 * configured timeout + number of words in message.
	 */
	private String messagesHideTimeout;

	/**
	 * Enables material effect when icons (e.g modal close, calendar) are clicked.
	 */
	private Boolean iconsEffect;

	/**
	 * When true it will activate control sidebar component.
	 */
	private ControlSidebar controlSidebar = new ControlSidebar();

	/**
	 * ControlSidebar properties.
	 */
	@Data
	public static class ControlSidebar implements Serializable {

		private static final long serialVersionUID = 1L;

		/**
		 * When true control sidebar will be also rendered on mobile devices.
		 */
		private Boolean showOnMobile;

		/**
		 * Switches layout between left (default) and top menu.
		 */
		private Boolean leftMenuTemplate;

		/**
		 * Toggles fixed layout where navbar is fixed on the page.
		 */
		private Boolean fixedLayout;

		/**
		 * Boxed layout.
		 */
		private Boolean boxedLayout;

		/**
		 * When true left sidebar will be collapsed.
		 */
		private Boolean sidebarCollapsed;

		/**
		 * When true left sidebar will expand on mouse hover.
		 */
		private Boolean expandOnHover;

		/**
		 * When true control sidebar will be fixed on the page.
		 */
		private Boolean fixed;

		/**
		 * Changes control sidebar skin between dark and light.
		 */
		private Boolean darkSkin;
	}
}
