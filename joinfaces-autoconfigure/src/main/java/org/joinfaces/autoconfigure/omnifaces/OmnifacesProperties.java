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

package org.joinfaces.autoconfigure.omnifaces;

import java.util.List;

import lombok.Data;
import org.joinfaces.autoconfigure.servlet.initparams.ServletContextInitParameter;
import org.joinfaces.autoconfigure.servlet.initparams.ServletContextInitParameterProperties;
import org.omnifaces.component.output.cache.CacheInitializerListener;
import org.omnifaces.component.output.cache.CacheInstancePerScopeProvider;
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
 *
 * @author Marcelo Fernandes
 * @author Lars Grefer
 */
@Data
@ConfigurationProperties(prefix = "jsf.omnifaces")
public class OmnifacesProperties implements ServletContextInitParameterProperties {


	/**
	 * org.omnifaces.CACHE_PROVIDER.
	 *
	 * @see CacheInitializerListener#CACHE_PROVIDER_INIT_PARAM_NAME
	 */
	@ServletContextInitParameter("org.omnifaces.CACHE_PROVIDER")
	private Class<?> cacheProvider;

	/**
	 * org.omnifaces.defaultcache.
	 *
	 * @see CacheInstancePerScopeProvider#DEFAULT_CACHE_PARAM_NAME
	 */
	@ServletContextInitParameter("org.omnifaces.defaultcache")
	private String defaultcache;

	/**
	 * org.omnifaces.EXCEPTION_TYPES_TO_UNWRAP.
	 *
	 * @see FullAjaxExceptionHandler#PARAM_NAME_EXCEPTION_TYPES_TO_UNWRAP
	 */
	@ServletContextInitParameter("org.omnifaces.EXCEPTION_TYPES_TO_UNWRAP")
	private List<Class<? extends Throwable>> exceptionTypesToUnwrap;

	/**
	 * org.omnifaces.FACES_VIEWS_DISPATCH_METHOD.
	 *
	 * @see FacesViews#FACES_VIEWS_DISPATCH_METHOD_PARAM_NAME
	 */
	@ServletContextInitParameter("org.omnifaces.FACES_VIEWS_DISPATCH_METHOD")
	private String facesViewsDispatchMethod;

	/**
	 * org.omnifaces.FACES_VIEWS_ENABLED.
	 *
	 * @see FacesViews#FACES_VIEWS_ENABLED_PARAM_NAME
	 */
	@ServletContextInitParameter("org.omnifaces.FACES_VIEWS_ENABLED")
	private Boolean facesViewsEnabled;

	/**
	 * org.omnifaces.FACES_VIEWS_EXTENSION_ACTION.
	 *
	 * @see FacesViews#FACES_VIEWS_EXTENSION_ACTION_PARAM_NAME
	 */
	@ServletContextInitParameter("org.omnifaces.FACES_VIEWS_EXTENSION_ACTION")
	private String facesViewsExtensionAction;

	/**
	 * org.omnifaces.FACES_VIEWS_FILTER_AFTER_DECLARED_FILTERS.
	 *
	 * @see FacesViews#FACES_VIEWS_FILTER_AFTER_DECLARED_FILTERS_PARAM_NAME
	 */
	@ServletContextInitParameter("org.omnifaces.FACES_VIEWS_FILTER_AFTER_DECLARED_FILTERS")
	private String facesViewsFilterAfterDeclaredFilters;

	/**
	 * org.omnifaces.FACES_VIEWS_PATH_ACTION.
	 *
	 * @see FacesViews#FACES_VIEWS_PATH_ACTION_PARAM_NAME
	 */
	@ServletContextInitParameter("org.omnifaces.FACES_VIEWS_PATH_ACTION")
	private String facesViewsPathAction;

	/**
	 * List of paths that are to be scanned by faces views.
	 *
	 * @see FacesViews#FACES_VIEWS_SCAN_PATHS_PARAM_NAME
	 */
	@ServletContextInitParameter("org.omnifaces.FACES_VIEWS_SCAN_PATHS")
	private List<String> facesViewsScanPaths;

	/**
	 * org.omnifaces.FACES_VIEWS_SCANNED_VIEWS_ALWAYS_EXTENSIONLESS.
	 *
	 * @see FacesViews#FACES_VIEWS_SCANNED_VIEWS_EXTENSIONLESS_PARAM_NAME
	 */
	@ServletContextInitParameter("org.omnifaces.FACES_VIEWS_SCANNED_VIEWS_ALWAYS_EXTENSIONLESS")
	private Boolean facesViewsScannedViewsAlwaysExtensionless;

	/**
	 * org.omnifaces.FACES_VIEWS_VIEW_HANDLER_MODE.
	 *
	 * @see FacesViews#FACES_VIEWS_VIEW_HANDLER_MODE_PARAM_NAME
	 */
	@ServletContextInitParameter("org.omnifaces.FACES_VIEWS_VIEW_HANDLER_MODE")
	private String facesViewsViewHandlerMode;

	/**
	 * org.omnifaces.HTML5_RENDER_KIT_PASSTHROUGH_ATTRIBUTES.
	 *
	 * @see Html5RenderKit#PARAM_NAME_PASSTHROUGH_ATTRIBUTES
	 */
	@ServletContextInitParameter("org.omnifaces.HTML5_RENDER_KIT_PASSTHROUGH_ATTRIBUTES")
	private String html5RenderKitPassthroughAttributes;

	/**
	 * org.omnifaces.CDN_RESOURCE_HANDLER_DISABLED.
	 *
	 * @see CDNResourceHandler#PARAM_NAME_CDN_DISABLED
	 */
	@ServletContextInitParameter("org.omnifaces.CDN_RESOURCE_HANDLER_DISABLED")
	private Boolean cdnResourceHandlerDisabled;

	/**
	 * org.omnifaces.CDN_RESOURCE_HANDLER_URLS.
	 *
	 * @see CDNResourceHandler#PARAM_NAME_CDN_RESOURCES
	 */
	@ServletContextInitParameter("org.omnifaces.CDN_RESOURCE_HANDLER_URLS")
	private String cdnResourceHandlerUrls;

	/**
	 * Set with a value greater than 0 to activate server-side caching of the combined resource files. The value is
	 * interpreted as cache TTL (time to live) in seconds and is only effective when the JSF project stage is
	 * <strong>not</strong> set to {@code Development}.
	 *
	 * @see CombinedResourceHandler#PARAM_NAME_CACHE_TTL
	 */
	@ServletContextInitParameter("org.omnifaces.COMBINED_RESOURCE_HANDLER_CACHE_TTL")
	private Integer combinedResourceHandlerCacheTtl;

	/**
	 * org.omnifaces.COMBINED_RESOURCE_HANDLER_DISABLED.
	 *
	 * @see CombinedResourceHandler#PARAM_NAME_DISABLED
	 */
	@ServletContextInitParameter("org.omnifaces.COMBINED_RESOURCE_HANDLER_DISABLED")
	private Boolean combinedResourceHandlerDisabled;

	/**
	 * List of resource identifiers of {@code <h:head>} resources which needs to be excluded
	 * from combining.
	 *
	 * @see CombinedResourceHandler#PARAM_NAME_EXCLUDED_RESOURCES
	 */
	@ServletContextInitParameter("org.omnifaces.COMBINED_RESOURCE_HANDLER_EXCLUDED_RESOURCES")
	private List<String> combinedResourceHandlerExcludedResources;

	/**
	 * Set to {@code true} if you want to render the combined CSS resources inline (embedded in HTML) instead of as a
	 * resource.
	 *
	 * @see CombinedResourceHandler#PARAM_NAME_INLINE_CSS
	 */
	@ServletContextInitParameter("org.omnifaces.COMBINED_RESOURCE_HANDLER_INLINE_CSS")
	private Boolean combinedResourceHandlerInlineCss;

	/**
	 * Set to {@code true} if you want to render the combined JS resources inline (embedded in HTML) instead of as a
	 * resource.
	 *
	 * @see CombinedResourceHandler#PARAM_NAME_INLINE_JS
	 */
	@ServletContextInitParameter("org.omnifaces.COMBINED_RESOURCE_HANDLER_INLINE_JS")
	private Boolean combinedResourceHandlerInlineJs;

	/**
	 * List of resource identifiers of {@code <h:head>} resources which needs to be suppressed
	 * and removed.
	 *
	 * @see CombinedResourceHandler#PARAM_NAME_SUPPRESSED_RESOURCES
	 */
	@ServletContextInitParameter("org.omnifaces.COMBINED_RESOURCE_HANDLER_SUPPRESSED_RESOURCES")
	private List<String> combinedResourceHandlerSuppressedResources;

	/**
	 * Sets the maximum number of elements that will be stored per web module (application scope).
	 * Default: no limit
	 *
	 * @see CacheInitializerListener#CACHE_PROVIDER_SETTING_INIT_PARAM_PREFIX
	 * @see CacheInstancePerScopeProvider#APP_MAX_CAP_PARAM_NAME
	 */
	@ServletContextInitParameter("org.omnifaces.CACHE_SETTING_APPLICATION_MAX_CAPACITY")
	private Integer cacheSettingApplicationMaxCapacity;

	/**
	 * Sets the maximum amount of time in seconds that cached content is valid for the application scope.
	 * Can be overriden by individal cache components.
	 * Default: no limit.
	 *
	 * @see CacheInitializerListener#CACHE_PROVIDER_SETTING_INIT_PARAM_PREFIX
	 * @see CacheInstancePerScopeProvider#APP_TTL_PARAM_NAME
	 */
	@ServletContextInitParameter("org.omnifaces.CACHE_SETTING_APPLICATION_TTL")
	private Integer cacheSettingApplicationTtl;

	/**
	 * Sets the maximum number of elements that will be stored per session.
	 * Default: no limit.
	 *
	 * @see CacheInitializerListener#CACHE_PROVIDER_SETTING_INIT_PARAM_PREFIX
	 * @see CacheInstancePerScopeProvider#SESSION_MAX_CAP_PARAM_NAME
	 */
	@ServletContextInitParameter("org.omnifaces.CACHE_SETTING_SESSION_MAX_CAPACITY")
	private Integer cacheSettingSessionMaxCapacity;

	/**
	 * Sets the maximum amount of time in seconds that cached content is valid for the session scope.
	 * Can be overriden by individal cache components.
	 * Default: no limit.
	 *
	 * @see CacheInitializerListener#CACHE_PROVIDER_SETTING_INIT_PARAM_PREFIX
	 * @see CacheInstancePerScopeProvider#SESSION_TTL_PARAM_NAME
	 */
	@ServletContextInitParameter("org.omnifaces.CACHE_SETTING_SESSION_TTL")
	private Integer cacheSettingSessionTtl;

	/**
	 * Boolean that when true installs a Servlet Filter (Servlet 3.0+ only) that works in conjunction with the useBuffer attribute of the Cache component to enable an alternative way to grab the content that needs to be cached.
	 * This is a convenience setting that is a short-cut for installing the {@link org.omnifaces.servlet.BufferedHttpServletResponse} filter manually.
	 * If more finegrained control is needed regarding which place in the filter chain the filter appears and which resources it exactly filters, this setting should not be used and the mentioned filter should be manually configured.
	 * Default: false.
	 */
	@ServletContextInitParameter("org.omnifaces.CACHE_INSTALL_BUFFER_FILTER")
	private Boolean cacheInstallBufferFilter;

}
