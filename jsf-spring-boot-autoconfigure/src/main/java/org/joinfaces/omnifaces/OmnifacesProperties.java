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

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.joinfaces.configuration.ServletContextInitParameter;
import org.joinfaces.configuration.ServletContextInitParameterConfigurationProperties;
import org.omnifaces.component.output.cache.CacheInitializerListener;
import org.omnifaces.component.output.cache.CacheInstancePerScopeProvider;
import org.omnifaces.component.output.cache.CacheProvider;
import org.omnifaces.exceptionhandler.FullAjaxExceptionHandler;
import org.omnifaces.facesviews.FacesViews;
import org.omnifaces.renderkit.Html5RenderKit;
import org.omnifaces.resourcehandler.CDNResourceHandler;
import org.omnifaces.resourcehandler.CombinedResourceHandler;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties of OmniFaces.
 * Taken from
 * http://central.maven.org/maven2/org/omnifaces/omnifaces/1.13/omnifaces-1.13-javadoc.jar
 * @author Marcelo Fernandes
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "jsf.omnifaces")
public class OmnifacesProperties implements ServletContextInitParameterConfigurationProperties {

	@ServletContextInitParameter(CacheInitializerListener.CACHE_PROVIDER_INIT_PARAM_NAME)
	private Class<? extends CacheProvider> cacheProvider;

	@ServletContextInitParameter(CacheInstancePerScopeProvider.DEFAULT_CACHE_PARAM_NAME)
	private String defaultcache;

	@ServletContextInitParameter(value = FullAjaxExceptionHandler.PARAM_NAME_EXCEPTION_TYPES_TO_UNWRAP, listSeparator = ",")
	private List<Class<? extends Throwable>> exceptionTypesToUnwrap;

	@ServletContextInitParameter(FacesViews.FACES_VIEWS_DISPATCH_METHOD_PARAM_NAME)
	private String facesViewsDispatchMethod;

	@ServletContextInitParameter(FacesViews.FACES_VIEWS_ENABLED_PARAM_NAME)
	private Boolean facesViewsEnabled;

	@ServletContextInitParameter(FacesViews.FACES_VIEWS_EXTENSION_ACTION_PARAM_NAME)
	private String facesViewsExtensionAction;

	@ServletContextInitParameter(FacesViews.FACES_VIEWS_FILTER_AFTER_DECLARED_FILTERS_PARAM_NAME)
	private String facesViewsFilterAfterDeclaredFilters;

	@ServletContextInitParameter(FacesViews.FACES_VIEWS_PATH_ACTION_PARAM_NAME)
	private String facesViewsPathAction;

	/**
	 * List of paths that are to be scanned by faces views.
	 */
	@ServletContextInitParameter(value = FacesViews.FACES_VIEWS_SCAN_PATHS_PARAM_NAME, listSeparator = ",")
	private List<String> facesViewsScanPaths;

	@ServletContextInitParameter(FacesViews.FACES_VIEWS_SCANNED_VIEWS_EXTENSIONLESS_PARAM_NAME)
	private Boolean facesViewsScannedViewsAlwaysExtensionless;

	@ServletContextInitParameter(FacesViews.FACES_VIEWS_VIEW_HANDLER_MODE_PARAM_NAME)
	private String facesViewsViewHandlerMode;

	@ServletContextInitParameter(Html5RenderKit.PARAM_NAME_PASSTHROUGH_ATTRIBUTES)
	private String html5RenderKitPassthroughAttributes;

	@ServletContextInitParameter(CDNResourceHandler.PARAM_NAME_CDN_DISABLED)
	private Boolean cdnResourceHandlerDisabled;

	@ServletContextInitParameter(CDNResourceHandler.PARAM_NAME_CDN_RESOURCES)
	private String cdnResourceHandlerUrls;

	/**
	 * Set with a value greater than 0 to activate server-side caching of the combined resource files. The value is
	 * interpreted as cache TTL (time to live) in seconds and is only effective when the JSF project stage is
	 * <strong>not</strong> set to <code>Development</code>.
	 */
	@ServletContextInitParameter(CombinedResourceHandler.PARAM_NAME_CACHE_TTL)
	private Integer combinedResourceHandlerCacheTtl;

	@ServletContextInitParameter(CombinedResourceHandler.PARAM_NAME_DISABLED)
	private Boolean combinedResourceHandlerDisabled;

	/**
	 * List of resource identifiers of <code>&lt;h:head&gt;</code> resources which needs to be excluded
	 * from combining.
	 */
	@ServletContextInitParameter(value = CombinedResourceHandler.PARAM_NAME_EXCLUDED_RESOURCES, listSeparator = ",")
	private List<String> combinedResourceHandlerExcludedResources;

	/**
	 * Set to <code>true</code> if you want to render the combined CSS resources inline (embedded in HTML) instead of as a
	 * resource.
	 */
	@ServletContextInitParameter(CombinedResourceHandler.PARAM_NAME_INLINE_CSS)
	private Boolean combinedResourceHandlerInlineCss;

	/**
	 * Set to <code>true</code> if you want to render the combined JS resources inline (embedded in HTML) instead of as a
	 * resource.
	 */
	@ServletContextInitParameter(CombinedResourceHandler.PARAM_NAME_INLINE_JS)
	private Boolean combinedResourceHandlerInlineJs;

	/**
	 * List of resource identifiers of <code>&lt;h:head&gt;</code> resources which needs to be suppressed
	 * and removed.
	 */
	@ServletContextInitParameter(value = CombinedResourceHandler.PARAM_NAME_SUPPRESSED_RESOURCES, listSeparator = ",")
	private List<String> combinedResourceHandlerSuppressedResources;

	@ServletContextInitParameter(CacheInitializerListener.CACHE_PROVIDER_SETTING_INIT_PARAM_PREFIX + CacheInstancePerScopeProvider.APP_MAX_CAP_PARAM_NAME)
	private Integer cacheSettingApplicationMaxCapacity;

	@ServletContextInitParameter(CacheInitializerListener.CACHE_PROVIDER_SETTING_INIT_PARAM_PREFIX + CacheInstancePerScopeProvider.APP_TTL_PARAM_NAME)
	private Integer cacheSettingApplicationTtl;

	@ServletContextInitParameter(CacheInitializerListener.CACHE_PROVIDER_SETTING_INIT_PARAM_PREFIX + CacheInstancePerScopeProvider.SESSION_MAX_CAP_PARAM_NAME)
	private Integer cacheSettingSessionMaxCapacity;

	@ServletContextInitParameter(CacheInitializerListener.CACHE_PROVIDER_SETTING_INIT_PARAM_PREFIX + CacheInstancePerScopeProvider.SESSION_TTL_PARAM_NAME)
	private Integer cacheSettingSessionTtl;

}
