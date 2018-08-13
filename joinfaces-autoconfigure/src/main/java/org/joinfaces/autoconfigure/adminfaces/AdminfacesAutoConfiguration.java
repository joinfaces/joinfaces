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

import javax.faces.application.ViewExpiredException;
import javax.persistence.OptimisticLockException;

import com.github.adminfaces.template.config.AdminConfig;
import com.github.adminfaces.template.config.ControlSidebarConfig;
import com.github.adminfaces.template.exception.AccessDeniedException;
import com.github.adminfaces.template.session.AdminServletContextListener;
import com.github.adminfaces.template.session.AdminSession;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.joinfaces.autoconfigure.primefaces.Primefaces4_0Properties;
import org.joinfaces.autoconfigure.primefaces.Primefaces5_2Properties;
import org.joinfaces.autoconfigure.primefaces.PrimefacesAutoConfiguration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

/**
 * Spring Boot Auto Configuration of AdminFaces.
 *
 * @author Marcelo Fernandes
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(AdminfacesProperties.class)
@ComponentScan({"com.github.adminfaces.template.bean",
	"com.github.adminfaces.template.config",
	"com.github.adminfaces.template.security"})
@ServletComponentScan({"com.github.adminfaces.template.security",
	"com.github.adminfaces.template.session"})
@ConditionalOnClass(AdminSession.class)
@AutoConfigureBefore(PrimefacesAutoConfiguration.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class AdminfacesAutoConfiguration {

	@Autowired
	private AdminfacesProperties adminfacesProperties;

	@Bean
	public BeanPostProcessor adminfacesPrimeFacesPropertiesPostProcessor() {
		PrimeFacesPropertiesPostProcessor result = new PrimeFacesPropertiesPostProcessor();
		result.setAdminfacesProperties(this.adminfacesProperties);
		return result;
	}

	// AdminSession does not contain @Named.
	@Bean
	@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public AdminSession adminSession() {
		return new AdminSession();
	}

	/**
	 * This {@link WebServerFactoryCustomizer} adds a {@link ServletContextInitializer} to the embedded servlet-container
	 * which is equivalent to adminfaces's own {@code META-INF/web-fragment.xml}.
	 * @return adminfaces web server factory customizer
	 */
	@Bean
	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> adminfacesWebServerFactoryCustomizer() {
		return factory -> {
			factory.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/403.jsf"),
					new ErrorPage(AccessDeniedException.class, "/403.jsf"),
					new ErrorPage(HttpStatus.NOT_FOUND, "/404.jsf"),
					new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.jsf"),
					new ErrorPage(Throwable.class, "/500.jsf"),
					new ErrorPage(ViewExpiredException.class, "/expired.jsf"),
					new ErrorPage(OptimisticLockException.class, "/optimistic.jsf")
			);
			factory.addInitializers(servletContext -> {
				servletContext.addListener(new AdminServletContextListener());
			});
		};
	}

	/**
	 * Configures Primefaces to use admin theme.
	 * Apply adminfaces configuration from adminfaces properties.
	 *
	 * @author Marcelo Fernandes
	 */
	static class PrimeFacesPropertiesPostProcessor implements BeanPostProcessor {
		@Setter
		private AdminfacesProperties adminfacesProperties;

		@Override
		public Object postProcessBeforeInitialization(@Nullable Object bean, @Nullable String beanName) throws BeansException {
			if (bean instanceof Primefaces4_0Properties) {
				Primefaces4_0Properties properties = (Primefaces4_0Properties) bean;
				log.warn("Changing primefaces theme from '{}' to 'admin'.", properties.getTheme());
				properties.setTheme("admin");
			}
			if (bean instanceof Primefaces5_2Properties) {
				Primefaces5_2Properties properties = (Primefaces5_2Properties) bean;
				log.warn("Changing primefaces fontAwesome from 'false' to 'true'.");
				properties.setFontAwesome(true);
			}
			/* Waiting 1.0.0-RC19 release
			if (bean instanceof AdminConfig) {
				configureAdminConfig((AdminConfig) bean);
			}
			*/
			return bean;
		}

		private void configureAdminConfig(AdminConfig adminConfig) {
			if (this.adminfacesProperties.getLoginPage() != null) {
				adminConfig.setLoginPage(this.adminfacesProperties.getLoginPage());
			}
			if (this.adminfacesProperties.getIndexPage() != null) {
				adminConfig.setIndexPage(this.adminfacesProperties.getIndexPage());
			}
			if (this.adminfacesProperties.getDateFormat() != null) {
				adminConfig.setDateFormat(this.adminfacesProperties.getDateFormat());
			}
			if (this.adminfacesProperties.getTemplatePath() != null) {
				adminConfig.setTemplatePath(this.adminfacesProperties.getTemplatePath());
			}
			if (this.adminfacesProperties.getBreadcrumbSize() != null) {
				adminConfig.setBreadCrumbMaxSize(this.adminfacesProperties.getBreadcrumbSize());
			}
			if (this.adminfacesProperties.getRenderMessages() != null) {
				adminConfig.setRenderMessages(this.adminfacesProperties.getRenderMessages());
			}
			if (this.adminfacesProperties.getRenderAjaxStatus() != null) {
				adminConfig.setRenderAjaxStatus(this.adminfacesProperties.getRenderAjaxStatus());
			}
			if (this.adminfacesProperties.getDisableFilter() != null) {
				adminConfig.setDisableFilter(this.adminfacesProperties.getDisableFilter());
			}
			if (this.adminfacesProperties.getRenderBreadCrumb() != null) {
				adminConfig.setRenderBreadCrumb(this.adminfacesProperties.getRenderBreadCrumb());
			}
			if (this.adminfacesProperties.getEnableSlideMenu() != null) {
				adminConfig.setEnableSlideMenu(this.adminfacesProperties.getEnableSlideMenu());
			}
			if (this.adminfacesProperties.getEnableRipple() != null) {
				adminConfig.setEnableRipple(this.adminfacesProperties.getEnableRipple());
			}
			if (this.adminfacesProperties.getRippleElements() != null) {
				adminConfig.setRippleElements(this.adminfacesProperties.getRippleElements());
			}
			if (this.adminfacesProperties.getSkin() != null) {
				adminConfig.setSkin(this.adminfacesProperties.getSkin());
			}
			if (this.adminfacesProperties.getAutoShowNavbar() != null) {
				adminConfig.setAutoShowNavbar(this.adminfacesProperties.getAutoShowNavbar());
			}
			if (this.adminfacesProperties.getIgnoredResources() != null) {
				adminConfig.setIgnoredResources(this.adminfacesProperties.getIgnoredResources());
			}
			if (this.adminfacesProperties.getLoadingImage() != null) {
				adminConfig.setLoadingImage(this.adminfacesProperties.getLoadingImage());
			}
			if (this.adminfacesProperties.getExtensionLessUrls() != null) {
				adminConfig.setExtensionLessUrls(this.adminfacesProperties.getExtensionLessUrls());
			}
			if (this.adminfacesProperties.getRenderControlSidebar() != null) {
				adminConfig.setRenderControlSidebar(this.adminfacesProperties.getRenderControlSidebar());
			}

			Boolean showOnMobile = adminConfig.getControlSidebar().getShowOnMobile();
			if (this.adminfacesProperties.getControlSidebar().getShowOnMobile() != null) {
				showOnMobile = this.adminfacesProperties.getControlSidebar().getShowOnMobile();
			}
			Boolean fixedLayout = adminConfig.getControlSidebar().getFixedLayout();
			if (this.adminfacesProperties.getControlSidebar().getFixedLayout() != null) {
				fixedLayout = this.adminfacesProperties.getControlSidebar().getFixedLayout();
			}
			Boolean boxedLayout = adminConfig.getControlSidebar().getBoxedLayout();
			if (this.adminfacesProperties.getControlSidebar().getBoxedLayout() != null) {
				boxedLayout = this.adminfacesProperties.getControlSidebar().getBoxedLayout();
			}
			Boolean expandOnHover = adminConfig.getControlSidebar().getExpandOnHover();
			if (this.adminfacesProperties.getControlSidebar().getExpandOnHover() != null) {
				expandOnHover = this.adminfacesProperties.getControlSidebar().getExpandOnHover();
			}
			Boolean sidebarCollapsed = adminConfig.getControlSidebar().getSidebarCollapsed();
			if (this.adminfacesProperties.getControlSidebar().getSidebarCollapsed() != null) {
				sidebarCollapsed = this.adminfacesProperties.getControlSidebar().getSidebarCollapsed();
			}
			Boolean fixed = adminConfig.getControlSidebar().getFixed();
			if (this.adminfacesProperties.getControlSidebar().getFixed() != null) {
				fixed = this.adminfacesProperties.getControlSidebar().getFixed();
			}
			Boolean darkSkin = adminConfig.getControlSidebar().getDarkSkin();
			if (this.adminfacesProperties.getControlSidebar().getDarkSkin() != null) {
				darkSkin = this.adminfacesProperties.getControlSidebar().getDarkSkin();
			}
			adminConfig.setControlSidebar(new ControlSidebarConfig(showOnMobile, fixedLayout, boxedLayout, expandOnHover, sidebarCollapsed, fixed, darkSkin));

			if (this.adminfacesProperties.getControlSidebar().getLeftMenuTemplate() != null) {
				adminConfig.setLeftMenuTemplate(this.adminfacesProperties.getControlSidebar().getLeftMenuTemplate());
			}

			if (this.adminfacesProperties.getRippleMobileOnly() != null) {
				adminConfig.setRippleMobileOnly(this.adminfacesProperties.getRippleMobileOnly());
			}
			if (this.adminfacesProperties.getRenderMenuSearch() != null) {
				adminConfig.setRenderMenuSearch(this.adminfacesProperties.getRenderMenuSearch());
			}
			if (this.adminfacesProperties.getAutoHideMessages() != null) {
				adminConfig.setAutoHideMessages(this.adminfacesProperties.getAutoHideMessages());
			}
			if (this.adminfacesProperties.getMessagesHideTimeout() != null) {
				adminConfig.setMessagesHideTimeout(this.adminfacesProperties.getMessagesHideTimeout());
			}
		}
	}

}
