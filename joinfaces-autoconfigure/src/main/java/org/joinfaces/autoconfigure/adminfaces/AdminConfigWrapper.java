/*
 * Copyright 2016-2016 the original author or authors.
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

import jakarta.enterprise.inject.Specializes;

import com.github.adminfaces.template.config.AdminConfig;
import com.github.adminfaces.template.config.ControlSidebarConfig;
import lombok.Setter;

/**
 * AdminConfig Wrapper to use AdminFacesProperties.
 * This class is not simpler because AdminConfig.getProperty is private.
 *
 * @author Marcelo Fernandes
 */
@Specializes
public class AdminConfigWrapper extends AdminConfig {
	@Setter
	private AdminfacesProperties adminfacesProperties;

	@Override
	protected void loadDefaults() {
		super.loadDefaults();
		configureAdminFacesProperties();
	}

	private String getAdminFacesStringProperty(String current, String fromAdminFacesProperties) {
		String result = current;
		if (fromAdminFacesProperties != null) {
			result = fromAdminFacesProperties;
		}
		return result;
	}

	private Boolean getAdminFacesBooleanProperty(Boolean current, Boolean fromAdminFacesProperties) {
		Boolean result = current;
		if (fromAdminFacesProperties != null) {
			result = fromAdminFacesProperties;
		}
		return result;
	}

	private Integer getAdminFacesIntegerProperty(Integer current, Integer fromAdminFacesProperties) {
		Integer result = current;
		if (fromAdminFacesProperties != null) {
			result = fromAdminFacesProperties;
		}
		return result;
	}

	private void configureAdminFacesProperties() {
		setLoginPage(getAdminFacesStringProperty(getLoginPage(), this.adminfacesProperties.getLoginPage()));
		setIndexPage(getAdminFacesStringProperty(getIndexPage(), this.adminfacesProperties.getIndexPage()));
		setDateFormat(getAdminFacesStringProperty(getDateFormat(), this.adminfacesProperties.getDateFormat()));
		setTemplatePath(getAdminFacesStringProperty(getTemplatePath(), this.adminfacesProperties.getTemplatePath()));
		setBreadCrumbMaxSize(getAdminFacesIntegerProperty(getBreadCrumbMaxSize(), this.adminfacesProperties.getBreadcrumbSize()));
		setRenderMessages(getAdminFacesBooleanProperty(isRenderMessages(), this.adminfacesProperties.getRenderMessages()));
		setSkipMessageDetailIfEqualsSummary(getAdminFacesBooleanProperty(isSkipMessageDetailIfEqualsSummary(), this.adminfacesProperties.getSkipMessageDetailIfEqualsSummary()));
		setRenderAjaxStatus(getAdminFacesBooleanProperty(isRenderAjaxStatus(), this.adminfacesProperties.getRenderAjaxStatus()));
		setDisableFilter(getAdminFacesBooleanProperty(isDisableFilter(), this.adminfacesProperties.getDisableFilter()));
		setRenderBreadCrumb(getAdminFacesBooleanProperty(isRenderBreadCrumb(), this.adminfacesProperties.getRenderBreadCrumb()));
		setEnableSlideMenu(getAdminFacesBooleanProperty(isEnableSlideMenu(), this.adminfacesProperties.getEnableSlideMenu()));
		setEnableRipple(getAdminFacesBooleanProperty(isEnableRipple(), this.adminfacesProperties.getEnableRipple()));
		setRippleElements(getAdminFacesStringProperty(getRippleElements(), this.adminfacesProperties.getRippleElements()));
		setSkin(getAdminFacesStringProperty(getSkin(), this.adminfacesProperties.getSkin()));
		setAutoShowNavbar(getAdminFacesBooleanProperty(isAutoShowNavbar(), this.adminfacesProperties.getAutoShowNavbar()));
		setIgnoredResources(getAdminFacesStringProperty(getIgnoredResources(), this.adminfacesProperties.getIgnoredResources()));
		setLoadingImage(getAdminFacesStringProperty(getLoadingImage(), this.adminfacesProperties.getLoadingImage()));
		setExtensionLessUrls(getAdminFacesBooleanProperty(isExtensionLessUrls(), this.adminfacesProperties.getExtensionLessUrls()));
		setRenderControlSidebar(getAdminFacesBooleanProperty(isRenderControlSidebar(), this.adminfacesProperties.getRenderControlSidebar()));

		setRippleMobileOnly(getAdminFacesBooleanProperty(isRippleMobileOnly(), this.adminfacesProperties.getRippleMobileOnly()));
		setRenderMenuSearch(getAdminFacesBooleanProperty(isRenderMenuSearch(), this.adminfacesProperties.getRenderMenuSearch()));
		setAutoHideMessages(getAdminFacesBooleanProperty(isAutoHideMessages(), this.adminfacesProperties.getAutoHideMessages()));
		setMessagesHideTimeout(getAdminFacesStringProperty(getMessagesHideTimeout(), this.adminfacesProperties.getMessagesHideTimeout()));
		setIconsEffect(getAdminFacesBooleanProperty(isIconsEffect(), this.adminfacesProperties.getIconsEffect()));

		setRenderFormAsterisks(getAdminFacesBooleanProperty(isRenderFormAsterisks(), this.adminfacesProperties.getRenderFormAsterisks()));
		setClosableLoading(getAdminFacesBooleanProperty(isClosableLoading(), this.adminfacesProperties.getClosableLoading()));
		setEnableMobileHeader(getAdminFacesBooleanProperty(isEnableMobileHeader(), this.adminfacesProperties.getEnableMobileHeader()));

		configControlSideBar();
	}

	private void configControlSideBar() {
		setLeftMenuTemplate(getAdminFacesBooleanProperty(isLeftMenuTemplate(), this.adminfacesProperties.getControlSidebar().getLeftMenuTemplate()));

		Boolean showOnMobile = getAdminFacesBooleanProperty(getControlSidebar().getShowOnMobile(), this.adminfacesProperties.getControlSidebar().getShowOnMobile());
		Boolean fixedLayout = getAdminFacesBooleanProperty(getControlSidebar().getFixedLayout(), this.adminfacesProperties.getControlSidebar().getFixedLayout());
		Boolean boxedLayout = getAdminFacesBooleanProperty(getControlSidebar().getBoxedLayout(), this.adminfacesProperties.getControlSidebar().getBoxedLayout());
		Boolean expandOnHover = getAdminFacesBooleanProperty(getControlSidebar().getExpandOnHover(), this.adminfacesProperties.getControlSidebar().getExpandOnHover());
		Boolean sidebarCollapsed = getAdminFacesBooleanProperty(getControlSidebar().getSidebarCollapsed(), this.adminfacesProperties.getControlSidebar().getSidebarCollapsed());
		Boolean fixed = getAdminFacesBooleanProperty(getControlSidebar().getFixed(), this.adminfacesProperties.getControlSidebar().getFixed());
		Boolean darkSkin = getAdminFacesBooleanProperty(getControlSidebar().getDarkSkin(), this.adminfacesProperties.getControlSidebar().getDarkSkin());

		setControlSidebar(new ControlSidebarConfig(showOnMobile, fixedLayout, boxedLayout, expandOnHover, sidebarCollapsed, fixed, darkSkin));
	}
}
