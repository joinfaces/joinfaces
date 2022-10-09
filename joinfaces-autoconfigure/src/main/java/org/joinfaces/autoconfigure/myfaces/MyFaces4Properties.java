/*
 * Copyright 2016-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.joinfaces.autoconfigure.myfaces;

import lombok.Data;
import org.apache.myfaces.config.MyfacesConfig;
import org.joinfaces.autoconfigure.servlet.initparams.ServletContextInitParameter;
import org.joinfaces.autoconfigure.servlet.initparams.ServletContextInitParameterProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * MyFaces Configuration Properites added in MyFaces 2.3-next and 4.0.
 *
 * @author Lars Grefer
 * @see <a href="https://myfaces.apache.org/#/core40?id=configuration">https://myfaces.apache.org/#/core40?id=configuration</a>
 */
@Data
@ConfigurationProperties("joinfaces.myfaces")
public class MyFaces4Properties implements ServletContextInitParameterProperties {

	/**
	 * Defines if Lambda expressions (via LambdaMetafactory) are used for getter/setter instead of Reflection.
	 */
	@ServletContextInitParameter("org.apache.myfaces.USE_LAMBDA_METAFACTORY")
	private Boolean useLambdaMetafactory;

	/**
	 * Defines if the last-modified should be cached of the resources when the ProjectStage is Production.
	 *
	 * @see MyfacesConfig#RESOURCE_CACHE_LAST_MODIFIED
	 */
	@ServletContextInitParameter(MyfacesConfig.RESOURCE_CACHE_LAST_MODIFIED)
	private Boolean resourceCacheLastModified;

	/**
	 * Defines if the clientbehavior scripts are passed as string or function to the jsf.
	 *
	 * @see MyfacesConfig#RENDER_CLIENTBEHAVIOR_SCRIPTS_AS_STRING
	 */
	@ServletContextInitParameter(MyfacesConfig.RENDER_CLIENTBEHAVIOR_SCRIPTS_AS_STRING)
	private Boolean renderClientbehaviorScriptsAsString;

	/**
	 * Defines if a session should be created (if one does not exist) before response rendering.
	 *
	 * @see MyfacesConfig#ALWAYS_FORCE_SESSION_CREATION
	 */
	@ServletContextInitParameter(MyfacesConfig.ALWAYS_FORCE_SESSION_CREATION)
	private Boolean alwaysForceSessionCreation;

	/**
	 * Defines the {@link java.util.ResourceBundle.Control} to use for all
	 * {@link java.util.ResourceBundle#getBundle(java.lang.String)} calls.
	 *
	 * @see MyfacesConfig#RESOURCE_BUNDLE_CONTROL
	 */
	@ServletContextInitParameter(MyfacesConfig.RESOURCE_BUNDLE_CONTROL)
	private String resourceBundleControl;

	/**
	 * Defines if ELResolvers should be traced on each request and logged.
	 * Only active on Development ProjectStage.
	 *
	 * @see MyfacesConfig#EL_RESOLVER_TRACING
	 */
	@ServletContextInitParameter(MyfacesConfig.EL_RESOLVER_TRACING)
	private Boolean elResolverTracing;

	/**
	 * Enable or disable the cache used to 'remember' if a view is protected or not.
	 *
	 * @see MyfacesConfig#VIEWID_PROTECTED_CACHE_ENABLED
	 */
	@ServletContextInitParameter(MyfacesConfig.VIEWID_PROTECTED_CACHE_ENABLED)
	private Boolean viewidProtectedCacheEnabled;

	/**
	 * Enable or disable the cache used to 'remember' the derived viewId from the rawViewId.
	 *
	 * @see MyfacesConfig#VIEWID_DERIVE_CACHE_ENABLED
	 */
	@ServletContextInitParameter(MyfacesConfig.VIEWID_DERIVE_CACHE_ENABLED)
	private Boolean viewidDeriveCacheEnabled;
}
