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

import org.omnifaces.component.output.cache.CacheProvider;
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
public class OmnifacesProperties {

	private Class<? extends CacheProvider> cacheProvider;

	private String defaultcache;

	private String exceptionTypesToUnwrap;

	private String facesViewsDispatchMethod;

	private Boolean facesViewsEnabled;

	private String facesViewsExtensionAction;

	private String facesViewsFilterAfterDeclaredFilters;

	private String facesViewsPathAction;

	/**
	 * List of paths that are to be scanned by faces views.
	 */
	private List<String> facesViewsScanPaths;

	private Boolean facesViewsScannedViewsAlwaysExtensionless;

	private String facesViewsViewHandlerMode;

	private String html5RenderKitPassthroughAttributes;

	private Boolean cdnResourceHandlerDisabled;

	private String cdnResourceHandlerUrls;

	/**
	 * Set with a value greater than 0 to activate server-side caching of the combined resource files. The value is
	 * interpreted as cache TTL (time to live) in seconds and is only effective when the JSF project stage is
	 * <strong>not</strong> set to <code>Development</code>.
	 */
	private Integer combinedResourceHandlerCacheTtl;

	private Boolean combinedResourceHandlerDisabled;

	/**
	 * List of resource identifiers of <code>&lt;h:head&gt;</code> resources which needs to be excluded
	 * from combining.
	 */
	private List<String> combinedResourceHandlerExcludedResources;

	/**
	 * Set to <code>true</code> if you want to render the combined CSS resources inline (embedded in HTML) instead of as a
	 * resource.
	 */
	private Boolean combinedResourceHandlerInlineCss;

	/**
	 * Set to <code>true</code> if you want to render the combined JS resources inline (embedded in HTML) instead of as a
	 * resource.
	 */
	private Boolean combinedResourceHandlerInlineJs;

	/**
	 * List of resource identifiers of <code>&lt;h:head&gt;</code> resources which needs to be suppressed
	 * and removed.
	 */
	private List<String> combinedResourceHandlerSuppressedResources;

	private Cache cache = new Cache();

	/**
	 * Cache class of applicationMaxCapacity, applicationTtl, sessionMaxCapacity
	 * and sessionTtl properties.
	 */
	@Getter
	@Setter
	public static class Cache {

		private Integer applicationMaxCapacity;

		private Integer applicationTtl;

		private Integer sessionMaxCapacity;

		private Integer sessionTtl;
	}
}
