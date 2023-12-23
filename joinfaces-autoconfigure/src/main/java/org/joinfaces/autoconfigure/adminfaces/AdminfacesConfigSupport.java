/*
 * Copyright 2016-2023 the original author or authors.
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

package org.joinfaces.autoconfigure.adminfaces;

import java.util.Objects;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

/**
 * This class maps {@link AdminfacesProperties} to {@link System#getProperties() system properites},
 * so they will be found by {@link com.github.adminfaces.template.config.AdminConfig}.
 *
 * @author Lars Grefer
 * @since 5.2.2
 */
@Slf4j
@UtilityClass
public class AdminfacesConfigSupport {

	public static void mapProperties(AdminfacesProperties adminfacesProperties) {
		mapProperty("admin.loginPage", adminfacesProperties.getLoginPage());
		mapProperty("admin.indexPage", adminfacesProperties.getIndexPage());
		mapProperty("admin.dateFormat", adminfacesProperties.getDateFormat());
		mapProperty("admin.breadcrumbSize", adminfacesProperties.getBreadcrumbSize());
		mapProperty("admin.renderMessages", adminfacesProperties.getRenderMessages());
		mapProperty("admin.skipMessageDetailIfEqualsSummary", adminfacesProperties.getSkipMessageDetailIfEqualsSummary());
		mapProperty("admin.renderAjaxStatus", adminfacesProperties.getRenderAjaxStatus());
		mapProperty("admin.disableFilter", adminfacesProperties.getDisableFilter());
		mapProperty("admin.renderBreadCrumb", adminfacesProperties.getRenderBreadCrumb());
		mapProperty("admin.extensionLessUrls", adminfacesProperties.getExtensionLessUrls());
		mapProperty("admin.enableSlideMenu", adminfacesProperties.getEnableSlideMenu());
		mapProperty("admin.enableRipple", adminfacesProperties.getEnableRipple());
		mapProperty("admin.rippleMobileOnly", adminfacesProperties.getRippleMobileOnly());
		mapProperty("admin.renderMenuSearch", adminfacesProperties.getRenderMenuSearch());
		mapProperty("admin.renderControlSidebar", adminfacesProperties.getRenderControlSidebar());
		mapProperty("admin.controlSidebar.showOnMobile", adminfacesProperties.getControlSidebar().getShowOnMobile());
		mapProperty("admin.controlSidebar.leftMenuTemplate", adminfacesProperties.getControlSidebar().getLeftMenuTemplate());
		mapProperty("admin.controlSidebar.fixedLayout", adminfacesProperties.getControlSidebar().getFixedLayout());
		mapProperty("admin.controlSidebar.boxedLayout", adminfacesProperties.getControlSidebar().getBoxedLayout());
		mapProperty("admin.controlSidebar.sidebarCollapsed", adminfacesProperties.getControlSidebar().getSidebarCollapsed());
		mapProperty("admin.controlSidebar.expandOnHover", adminfacesProperties.getControlSidebar().getExpandOnHover());
		mapProperty("admin.controlSidebar.fixed", adminfacesProperties.getControlSidebar().getFixed());
		mapProperty("admin.controlSidebar.darkSkin", adminfacesProperties.getControlSidebar().getDarkSkin());
		mapProperty("admin.autoHideMessages", adminfacesProperties.getAutoHideMessages());
		mapProperty("admin.renderFormAsterisks", adminfacesProperties.getRenderFormAsterisks());
		mapProperty("admin.enableMobileHeader", adminfacesProperties.getEnableMobileHeader());
		mapProperty("admin.closableLoading", adminfacesProperties.getClosableLoading());
		mapProperty("admin.messagesHideTimeout", adminfacesProperties.getMessagesHideTimeout());
		mapProperty("admin.skin", adminfacesProperties.getSkin());
		mapProperty("admin.autoShowNavbar", adminfacesProperties.getAutoShowNavbar());
		mapProperty("admin.loadingImage", adminfacesProperties.getLoadingImage());
		mapProperty("admin.iconsEffect", adminfacesProperties.getIconsEffect());
		mapProperty("admin.rippleElements", adminfacesProperties.getRippleElements());
	}

	protected void mapProperty(@NonNull String key, @Nullable Object value) {
		Assert.hasText(key, "key must not be null or empty");

		if (value == null) {
			return;
		}

		String newValue = String.valueOf(value);

		String existingValue = System.getProperty(key);
		if (existingValue != null) {

			if (!Objects.equals(existingValue, newValue)) {
				log.info("AdminFaces Property {} has already been set to {} instead of {}.", key, existingValue, value);
			}

			return;
		}

		log.debug("Setting AdminFaces Property {}={}", key, value);
		System.setProperty(key, String.valueOf(value));
	}
}
