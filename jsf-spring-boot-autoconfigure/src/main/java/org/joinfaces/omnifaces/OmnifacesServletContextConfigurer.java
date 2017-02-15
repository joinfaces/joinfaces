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

package org.joinfaces.omnifaces;

import javax.servlet.ServletContext;

import lombok.Builder;
import org.joinfaces.ServletContextConfigurer;
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
		setInitParameterString(CacheInitializerListener.CACHE_PROVIDER_INIT_PARAM_NAME, this.omnifacesProperties.getCacheProvider());
		setInitParameterString(CacheInstancePerScopeProvider.DEFAULT_CACHE_PARAM_NAME, this.omnifacesProperties.getDefaultcache());
		setInitParameterString(FullAjaxExceptionHandler.PARAM_NAME_EXCEPTION_TYPES_TO_UNWRAP, this.omnifacesProperties.getExceptionTypesToUnwrap());
		setInitParameterString(FacesViews.FACES_VIEWS_DISPATCH_METHOD_PARAM_NAME, this.omnifacesProperties.getFacesViewsDispatchMethod());
		setInitParameterBoolean(FacesViews.FACES_VIEWS_ENABLED_PARAM_NAME, this.omnifacesProperties.getFacesViewsEnabled());
		setInitParameterString(FacesViews.FACES_VIEWS_EXTENSION_ACTION_PARAM_NAME, this.omnifacesProperties.getFacesViewsExtensionAction());
		setInitParameterString(FacesViews.FACES_VIEWS_FILTER_AFTER_DECLARED_FILTERS_PARAM_NAME, this.omnifacesProperties.getFacesViewsFilterAfterDeclaredFilters());
		setInitParameterString(FacesViews.FACES_VIEWS_PATH_ACTION_PARAM_NAME, this.omnifacesProperties.getFacesViewsPathAction());
		setInitParameterStringCollection(FacesViews.FACES_VIEWS_SCAN_PATHS_PARAM_NAME, this.omnifacesProperties.getFacesViewsScanPaths(), Separator.COMMA);
		setInitParameterBoolean(FacesViews.FACES_VIEWS_SCANNED_VIEWS_EXTENSIONLESS_PARAM_NAME, this.omnifacesProperties.getFacesViewsScannedViewsAlwaysExtensionless());
		setInitParameterString(FacesViews.FACES_VIEWS_VIEW_HANDLER_MODE_PARAM_NAME, this.omnifacesProperties.getFacesViewsViewHandlerMode());
		setInitParameterString(Html5RenderKit.PARAM_NAME_PASSTHROUGH_ATTRIBUTES, this.omnifacesProperties.getHtml5RenderKitPassthroughAttributes());
		setInitParameterBoolean(CDNResourceHandler.PARAM_NAME_CDN_DISABLED, this.omnifacesProperties.getCdnResourceHandlerDisabled());
		setInitParameterString(CDNResourceHandler.PARAM_NAME_CDN_RESOURCES, this.omnifacesProperties.getCdnResourceHandlerUrls());
		setInitParameterInteger(CombinedResourceHandler.PARAM_NAME_CACHE_TTL, this.omnifacesProperties.getCombinedResourceHandlerCacheTtl());
		setInitParameterBoolean(CombinedResourceHandler.PARAM_NAME_DISABLED, this.omnifacesProperties.getCombinedResourceHandlerDisabled());
		setInitParameterStringCollection(CombinedResourceHandler.PARAM_NAME_EXCLUDED_RESOURCES, this.omnifacesProperties.getCombinedResourceHandlerExcludedResources(), Separator.COMMA);
		setInitParameterBoolean(CombinedResourceHandler.PARAM_NAME_INLINE_CSS, this.omnifacesProperties.getCombinedResourceHandlerInlineCss());
		setInitParameterBoolean(CombinedResourceHandler.PARAM_NAME_INLINE_JS, this.omnifacesProperties.getCombinedResourceHandlerInlineJs());
		setInitParameterStringCollection(CombinedResourceHandler.PARAM_NAME_SUPPRESSED_RESOURCES, this.omnifacesProperties.getCombinedResourceHandlerSuppressedResources(), Separator.COMMA);

		setInitParameterInteger(CacheInstancePerScopeProvider.APP_MAX_CAP_PARAM_NAME, this.omnifacesProperties.getCache().getApplicationMaxCapacity());
		setInitParameterInteger(CacheInstancePerScopeProvider.APP_TTL_PARAM_NAME, this.omnifacesProperties.getCache().getApplicationTtl());
		setInitParameterInteger(CacheInstancePerScopeProvider.SESSION_MAX_CAP_PARAM_NAME, this.omnifacesProperties.getCache().getSessionMaxCapacity());
		setInitParameterInteger(CacheInstancePerScopeProvider.SESSION_TTL_PARAM_NAME, this.omnifacesProperties.getCache().getSessionTtl());
	}
}
