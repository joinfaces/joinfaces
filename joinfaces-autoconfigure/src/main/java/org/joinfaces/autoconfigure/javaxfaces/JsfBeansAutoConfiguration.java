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
 * @author Lars Grefer
 * @see <a href="http://arjan-tijms.omnifaces.org/p/jsf-23.html#1316">http://arjan-tijms.omnifaces.org/p/jsf-23.html#1316</a>
 */
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnClass(FacesContext.class)
public class JsfBeansAutoConfiguration {

	/**
	 * @see com.sun.faces.cdi.ApplicationProducer
	 */
	@Bean("application")
	@ConditionalOnMissingBean(name = "application")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public Object application() {
		return FacesContext.getCurrentInstance().getExternalContext().getContext();
	}

	/**
	 * @see com.sun.faces.cdi.ApplicationMapProducer
	 */
	@Bean("applicationScope")
	@ConditionalOnMissingBean(name = "applicationScope")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
	public Map<String, Object> applicationMap() {
		return FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
	}

	/**
	 * @see com.sun.faces.cdi.RequestCookieMapProducer
	 */
	@Bean("cookie")
	@ConditionalOnMissingBean(name = "cookie")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
	public Map<String, Object> requestCookieMap() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap();
	}

	/**
	 * @see com.sun.faces.cdi.FacesContextProducer
	 */
	@Bean("facesContext")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
	@ConditionalOnMissingBean
	public FacesContext facesContext() {
		return FacesContext.getCurrentInstance();
	}

	/**
	 * @see com.sun.faces.cdi.FlashProducer
	 */
	@Bean("flash")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
	@ConditionalOnMissingBean
	public Flash flash() {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}

	/**
	 * @see com.sun.faces.cdi.FlowMapProducer
	 */
	@Bean("flowScope")
	@ConditionalOnMissingBean(name = "flowScope")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
	public Map<Object, Object> flowScope() {
		return FacesContext.getCurrentInstance().getApplication().getFlowHandler().getCurrentFlowScope();
	}

	/**
	 * @see com.sun.faces.cdi.HeaderMapProducer
	 */
	@Bean("header")
	@ConditionalOnMissingBean(name = "header")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
	public Map<String, String> headerMap() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestHeaderMap();
	}

	/**
	 * @see com.sun.faces.cdi.HeaderValuesMapProducer
	 */
	@Bean("headerValues")
	@ConditionalOnMissingBean(name = "headerValues")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
	public Map<String, String[]> headerValuesMap() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestHeaderValuesMap();
	}

	/**
	 * @see com.sun.faces.cdi.InitParameterMapProducer
	 */
	@Bean("initParam")
	@ConditionalOnMissingBean(name = "initParam")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
	@SuppressWarnings("unchecked")
	public Map<String, String> initParameterMap() {
		return FacesContext.getCurrentInstance().getExternalContext().getInitParameterMap();
	}

	/**
	 * @see com.sun.faces.cdi.RequestParameterMapProducer
	 */
	@Bean("param")
	@ConditionalOnMissingBean(name = "param")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
	public Map<String, String> requestParameterMap() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	}

	/**
	 * @see com.sun.faces.cdi.RequestParameterValuesMapProducer
	 */
	@Bean("paramValues")
	@ConditionalOnMissingBean(name = "paramValues")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
	public Map<String, String[]> requestParameterValuesMap() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap();
	}

	/**
	 * @see com.sun.faces.cdi.RequestProducer
	 */
	@Bean("request")
	@ConditionalOnMissingBean
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public Object request() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	/**
	 * @see com.sun.faces.cdi.RequestMapProducer
	 */
	@Bean("requestScope")
	@ConditionalOnMissingBean(name = "requestScope")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
	public Map<String, Object> requestMap() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
	}

	/**
	 * @see com.sun.faces.cdi.ResourceHandlerProducer
	 */
	@Bean("resource")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
	@ConditionalOnMissingBean
	public ResourceHandler resourceHandler() {
		return FacesContext.getCurrentInstance().getApplication().getResourceHandler();
	}

	/**
	 * @see com.sun.faces.cdi.SessionProducer
	 */
	@Bean("session")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
	@ConditionalOnMissingBean
	public Object session() {
		return FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	/**
	 * @see com.sun.faces.cdi.SessionMapProducer
	 */
	@Bean("sessionScope")
	@ConditionalOnMissingBean(name = "sessionScope")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
	public Map<String, Object> sessionMap() {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	}

	/**
	 * @see com.sun.faces.cdi.ViewProducer
	 */
	@Bean("view")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
	@ConditionalOnMissingBean
	public UIViewRoot viewRoot() {
		return FacesContext.getCurrentInstance().getViewRoot();
	}

	/**
	 * @see com.sun.faces.cdi.ViewMapProducer
	 */
	@Bean("viewScope")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
	@ConditionalOnMissingBean(name = "viewScope")
	public Map<String, Object> viewMap() {
		return FacesContext.getCurrentInstance().getViewRoot().getViewMap();
	}

	/**
	 * @see com.sun.faces.cdi.ExternalContextProducer
	 */
	@Bean("externalContext")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
	@ConditionalOnMissingBean
	public ExternalContext externalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}
}
