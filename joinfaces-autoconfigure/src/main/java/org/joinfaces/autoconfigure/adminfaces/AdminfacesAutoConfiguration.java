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

import com.github.adminfaces.template.exception.AccessDeniedException;
import com.github.adminfaces.template.session.AdminServletContextListener;
import com.github.adminfaces.template.session.AdminSession;
import lombok.extern.slf4j.Slf4j;
import org.joinfaces.autoconfigure.primefaces.Primefaces4_0Properties;
import org.joinfaces.autoconfigure.primefaces.Primefaces5_2Properties;
import org.joinfaces.autoconfigure.primefaces.PrimefacesAutoConfiguration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
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

/**
 * Spring Boot Auto Configuration of AdminFaces.
 *
 * @author Marcelo Fernandes
 */
@Slf4j
@Configuration
// will adminfaces autoconfigure via application.yml ?
//@EnableConfigurationProperties(AdminfacesProperties.class)
@ComponentScan({"com.github.adminfaces.template.bean",
	"com.github.adminfaces.template.config",
	"com.github.adminfaces.template.security"})
@ServletComponentScan({"com.github.adminfaces.template.security",
	"com.github.adminfaces.template.session"})
@ConditionalOnClass(AdminSession.class)
@AutoConfigureBefore(PrimefacesAutoConfiguration.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class AdminfacesAutoConfiguration {

	@Bean
	public BeanPostProcessor adminfacesPrimeFacesPropertiesPostProcessor() {
		return new PrimeFacesPropertiesPostProcessor();
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
	 *
	 * @author Marcelo Fernandes
	 */
	static class PrimeFacesPropertiesPostProcessor implements BeanPostProcessor {
		@Override
		public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
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
			return bean;
		}
	}

}
