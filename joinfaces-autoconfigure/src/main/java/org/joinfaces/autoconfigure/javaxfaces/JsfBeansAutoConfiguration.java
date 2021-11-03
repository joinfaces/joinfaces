/*
 * Copyright 2016-2018 the original author or authors.
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

package org.joinfaces.autoconfigure.javaxfaces;

import java.util.Map;

import javax.faces.annotation.ApplicationMap;
import javax.faces.application.ResourceHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * JSF 2.3-like Bean Definitions.
 *
 * These bean definitions are taken from the mojarra {@link com.sun.faces.cdi.CdiProducer}s.
 *
 * @author Lars Grefer
 * @see <a href="http://arjan-tijms.omnifaces.org/p/jsf-23.html#1316">http://arjan-tijms.omnifaces.org/p/jsf-23.html#1316</a>
 */
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnClass(FacesContext.class)
public class JsfBeansAutoConfiguration {

	@Bean("application")
	@ConditionalOnMissingBean(name = "application")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public Object application() {
		return FacesContext.getCurrentInstance().getExternalContext().getContext();
	}

	@Bean("applicationScope")
	@ConditionalOnMissingBean(name = "applicationScope")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
	public Map<String, Object> applicationMap() {
		return FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
	}

	@Bean("cookie")
	@ConditionalOnMissingBean(name = "cookie")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
	public Map<String, Object> requestCookieMap() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap();
	}

	/**
	 * Spring bean definition for the JSF {@link FacesContext}.
	 *
	 * @see com.sun.faces.cdi.FacesContextProducer
	 * @return The current {@link FacesContext#getCurrentInstance() FacesContext}.
	 */
	@Bean("facesContext")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
	@ConditionalOnMissingBean
	public FacesContext facesContext() {
		return FacesContext.getCurrentInstance();
	}

	@Bean("flash")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
	@ConditionalOnMissingBean
	public Flash flash() {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}

	@Bean("flowScope")
	@ConditionalOnMissingBean(name = "flowScope")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
	public Map<Object, Object> flowScope() {
		return FacesContext.getCurrentInstance().getApplication().getFlowHandler().getCurrentFlowScope();
	}

	@Bean("header")
	@ConditionalOnMissingBean(name = "header")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
	public Map<String, String> headerMap() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestHeaderMap();
	}

	@Bean("headerValues")
	@ConditionalOnMissingBean(name = "headerValues")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
	public Map<String, String[]> headerValuesMap() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestHeaderValuesMap();
	}

	@Bean("initParam")
	@ConditionalOnMissingBean(name = "initParam")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
	@SuppressWarnings("unchecked")
	public Map<String, String> initParameterMap() {
		return FacesContext.getCurrentInstance().getExternalContext().getInitParameterMap();
	}

	@Bean("param")
	@ConditionalOnMissingBean(name = "param")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
	public Map<String, String> requestParameterMap() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	}

	@Bean("paramValues")
	@ConditionalOnMissingBean(name = "paramValues")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
	public Map<String, String[]> requestParameterValuesMap() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap();
	}

	@Bean("request")
	@ConditionalOnMissingBean
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public Object request() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	@Bean("requestScope")
	@ConditionalOnMissingBean(name = "requestScope")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
	public Map<String, Object> requestMap() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
	}

	@Bean("resource")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
	@ConditionalOnMissingBean
	public ResourceHandler resourceHandler() {
		return FacesContext.getCurrentInstance().getApplication().getResourceHandler();
	}

	@Bean("session")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
	@ConditionalOnMissingBean
	public Object session() {
		return FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	@Bean("sessionScope")
	@ConditionalOnMissingBean(name = "sessionScope")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
	public Map<String, Object> sessionMap() {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	}

	@Bean("view")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
	@ConditionalOnMissingBean
	public UIViewRoot viewRoot() {
		return FacesContext.getCurrentInstance().getViewRoot();
	}

	@Bean("viewScope")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
	@ConditionalOnMissingBean(name = "viewScope")
	public Map<String, Object> viewMap() {
		return FacesContext.getCurrentInstance().getViewRoot().getViewMap();
	}

	@Bean("externalContext")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
	@ConditionalOnMissingBean
	public ExternalContext externalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	@Configuration(proxyBeanMethods = false)
	@ConditionalOnClass(ApplicationMap.class)
	public static class Jsf2_3AutoConfiguration {

		@Bean
		public static JsfBeansAnnotationPostProcessor jsfBeansAnnotationPostProcessor() {
			return new JsfBeansAnnotationPostProcessor();
		}
	}
}
