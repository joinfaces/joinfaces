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

package com.github.persapiens.jsfboot.omnifaces;

import javax.servlet.ServletContext;

import com.github.persapiens.jsfboot.ServletContextConfigurer;

import lombok.Builder;

import org.omnifaces.component.output.cache.CacheInitializerListener;
import org.omnifaces.component.output.cache.CacheInstancePerScopeProvider;
import org.omnifaces.exceptionhandler.FullAjaxExceptionHandler;
import org.omnifaces.facesviews.FacesViews;
import org.omnifaces.renderkit.Html5RenderKit;
import org.omnifaces.resourcehandler.CDNResourceHandler;
import org.omnifaces.resourcehandler.CombinedResourceHandler;

/**
 * Servlet context configurer of OmniFaces.
 * @author Marcelo Fernandes
 */
public class OmnifacesServletContextConfigurer extends ServletContextConfigurer {

	private OmnifacesProperties omnifacesProperties;

	@Builder
	public OmnifacesServletContextConfigurer(OmnifacesProperties omnifacesProperties, ServletContext servletContext) {
		super(servletContext, "");
		this.omnifacesProperties = omnifacesProperties;
	}

	@Override
	public void configure() {
		setInitParameter(CacheInitializerListener.CACHE_PROVIDER_INIT_PARAM_NAME, this.omnifacesProperties.getCacheProvider());
		setInitParameter(CacheInstancePerScopeProvider.DEFAULT_CACHE_PARAM_NAME, this.omnifacesProperties.getDefaultcache());
		setInitParameter(FullAjaxExceptionHandler.PARAM_NAME_EXCEPTION_TYPES_TO_UNWRAP, this.omnifacesProperties.getExceptionTypesToUnwrap());
		setInitParameter(FacesViews.FACES_VIEWS_DISPATCH_METHOD_PARAM_NAME, this.omnifacesProperties.getFacesViewsDispatchMethod());
		setInitParameter(FacesViews.FACES_VIEWS_ENABLED_PARAM_NAME, this.omnifacesProperties.getFacesViewsEnabled());
		setInitParameter(FacesViews.FACES_VIEWS_EXTENSION_ACTION_PARAM_NAME, this.omnifacesProperties.getFacesViewsExtensionAction());
		setInitParameter(FacesViews.FACES_VIEWS_FILTER_AFTER_DECLARED_FILTERS_PARAM_NAME, this.omnifacesProperties.getFacesViewsFilterAfterDeclaredFilters());
		setInitParameter(FacesViews.FACES_VIEWS_PATH_ACTION_PARAM_NAME, this.omnifacesProperties.getFacesViewsPathAction());
		setInitParameter(FacesViews.FACES_VIEWS_SCAN_PATHS_PARAM_NAME, this.omnifacesProperties.getFacesViewsScanPaths());
		setInitParameter(FacesViews.FACES_VIEWS_SCANNED_VIEWS_EXTENSIONLESS_PARAM_NAME, this.omnifacesProperties.getFacesViewsScannedViewsAlwaysExtensionless());
		setInitParameter(FacesViews.FACES_VIEWS_VIEW_HANDLER_MODE_PARAM_NAME, this.omnifacesProperties.getFacesViewsViewHandlerMode());
		setInitParameter(Html5RenderKit.PARAM_NAME_PASSTHROUGH_ATTRIBUTES, this.omnifacesProperties.getHtml5RenderKitPassthroughAttributes());
		setInitParameter(CDNResourceHandler.PARAM_NAME_CDN_DISABLED, this.omnifacesProperties.getCdnResourceHandlerDisabled());
		setInitParameter(CDNResourceHandler.PARAM_NAME_CDN_RESOURCES, this.omnifacesProperties.getCdnResourceHandlerUrls());
		setInitParameter(CombinedResourceHandler.PARAM_NAME_CACHE_TTL, this.omnifacesProperties.getCombinedResourceHandlerCacheTtl());
		setInitParameter(CombinedResourceHandler.PARAM_NAME_DISABLED, this.omnifacesProperties.getCombinedResourceHandlerDisabled());
		setInitParameter(CombinedResourceHandler.PARAM_NAME_EXCLUDED_RESOURCES, this.omnifacesProperties.getCombinedResourceHandlerExcludedResources());
		setInitParameter(CombinedResourceHandler.PARAM_NAME_INLINE_CSS, this.omnifacesProperties.getCombinedResourceHandlerInlineCss());
		setInitParameter(CombinedResourceHandler.PARAM_NAME_INLINE_JS, this.omnifacesProperties.getCombinedResourceHandlerInlineJs());
		setInitParameter(CombinedResourceHandler.PARAM_NAME_SUPPRESSED_RESOURCES, this.omnifacesProperties.getCombinedResourceHandlerSuppressedResources());

		setInitParameter(CacheInstancePerScopeProvider.APP_MAX_CAP_PARAM_NAME, this.omnifacesProperties.getCache().getApplicationMaxCapacity());
		setInitParameter(CacheInstancePerScopeProvider.APP_TTL_PARAM_NAME, this.omnifacesProperties.getCache().getApplicationTtl());
		setInitParameter(CacheInstancePerScopeProvider.SESSION_MAX_CAP_PARAM_NAME, this.omnifacesProperties.getCache().getSessionMaxCapacity());
		setInitParameter(CacheInstancePerScopeProvider.SESSION_TTL_PARAM_NAME, this.omnifacesProperties.getCache().getSessionTtl());
	}
}
