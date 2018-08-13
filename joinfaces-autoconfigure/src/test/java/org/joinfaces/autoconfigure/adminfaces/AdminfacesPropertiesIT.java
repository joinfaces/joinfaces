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

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(
		properties = "spring.profiles.active=propertyTest",
		webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
public class AdminfacesPropertiesIT {

	@Autowired
	private AdminfacesProperties adminfacesProperties;

	@Test
	public void testLoginPage() {
		assertThat(this.adminfacesProperties.getLoginPage())
			.isEqualTo("login");
	}

	@Test
	public void testIndexPage() {
		assertThat(this.adminfacesProperties.getIndexPage())
			.isEqualTo("index");
	}

	@Test
	public void testDateFormat() {
		assertThat(this.adminfacesProperties.getDateFormat())
			.isEqualTo("dd-mm-yyyy");
	}

	@Test
	public void testTemplatePath() {
		assertThat(this.adminfacesProperties.getTemplatePath())
			.isEqualTo("my-template-path");
	}

	@Test
	public void testBraedCrumbSize() {
		assertThat(this.adminfacesProperties.getBreadcrumbSize())
			.isEqualTo(50);
	}

	@Test
	public void testRenderMessages() {
		assertThat(this.adminfacesProperties.getRenderMessages())
			.isTrue();
	}

	@Test
	public void testRenderAjaxStatus() {
		assertThat(this.adminfacesProperties.getRenderAjaxStatus())
			.isTrue();
	}

	@Test
	public void testDisableFilter() {
		assertThat(this.adminfacesProperties.getDisableFilter())
			.isTrue();
	}

	@Test
	public void testRenderBreadCrumb() {
		assertThat(this.adminfacesProperties.getRenderBreadCrumb())
			.isTrue();
	}

	@Test
	public void testEnableSlideMenu() {
		assertThat(this.adminfacesProperties.getEnableSlideMenu())
			.isTrue();
	}

	@Test
	public void testEnableRipple() {
		assertThat(this.adminfacesProperties.getEnableRipple())
			.isTrue();
	}

	@Test
	public void testRippleElements() {
		assertThat(this.adminfacesProperties.getRippleElements())
			.isEqualTo(".ripplelink,button.ui-button");
	}

	@Test
	public void testSkin() {
		assertThat(this.adminfacesProperties.getSkin())
			.isEqualTo("skin-blue");
	}

	@Test
	public void testAutoShowNavbar() {
		assertThat(this.adminfacesProperties.getAutoShowNavbar())
			.isTrue();
	}

	@Test
	public void testIgnoredResources() {
		assertThat(this.adminfacesProperties.getIgnoredResources())
			.isEqualTo("/rest, /pages/car-list");
	}

	@Test
	public void testLoadingImage() {
		assertThat(this.adminfacesProperties.getLoadingImage())
			.isEqualTo("ajaxloadingbar.png");
	}

	@Test
	public void testExtensionLessUrls() {
		assertThat(this.adminfacesProperties.getExtensionLessUrls())
			.isTrue();
	}

	@Test
	public void testRenderControlSidebar() {
		assertThat(this.adminfacesProperties.getRenderControlSidebar())
			.isTrue();
	}

	@Test
	public void testRippleMobileOnly() {
		assertThat(this.adminfacesProperties.getRippleMobileOnly())
			.isTrue();
	}

	@Test
	public void testRenderMenuSearch() {
		assertThat(this.adminfacesProperties.getRenderMenuSearch())
			.isTrue();
	}

	@Test
	public void testAutoHideMessages() {
		assertThat(this.adminfacesProperties.getAutoHideMessages())
			.isTrue();
	}

	@Test
	public void testMessagesHideTimeout() {
		assertThat(this.adminfacesProperties.getMessagesHideTimeout())
			.isEqualTo("2200");
	}

	@Test
	public void testShowOnMobile() {
		assertThat(this.adminfacesProperties.getControlSidebar().getShowOnMobile())
			.isTrue();
	}

	@Test
	public void testLeftMenuTemplate() {
		assertThat(this.adminfacesProperties.getControlSidebar().getLeftMenuTemplate())
			.isTrue();
	}

	@Test
	public void testFixedLayout() {
		assertThat(this.adminfacesProperties.getControlSidebar().getFixedLayout())
			.isTrue();
	}

	@Test
	public void testBoxedLayout() {
		assertThat(this.adminfacesProperties.getControlSidebar().getBoxedLayout())
			.isTrue();
	}

	@Test
	public void testSidebarCollapsed() {
		assertThat(this.adminfacesProperties.getControlSidebar().getSidebarCollapsed())
			.isTrue();
	}

	@Test
	public void testExpandOnHover() {
		assertThat(this.adminfacesProperties.getControlSidebar().getExpandOnHover())
			.isTrue();
	}

	@Test
	public void testFixed() {
		assertThat(this.adminfacesProperties.getControlSidebar().getFixed())
			.isTrue();
	}

	@Test
	public void testDarkSkin() {
		assertThat(this.adminfacesProperties.getControlSidebar().getDarkSkin())
			.isTrue();
	}

}
