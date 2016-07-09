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

package com.github.persapiens.jsfboot.myfaces;

import javax.servlet.ServletContext;

import com.github.persapiens.jsfboot.ServletContextConfigurer;

import lombok.Builder;

import org.apache.myfaces.application.ResourceHandlerImpl;
import org.apache.myfaces.application.StateCache;
import org.apache.myfaces.config.FacesConfigValidator;
import org.apache.myfaces.config.annotation.CdiAnnotationProviderExtension;
import org.apache.myfaces.config.annotation.DefaultAnnotationProvider;
import org.apache.myfaces.el.unified.ResolverBuilderBase;
import org.apache.myfaces.lifecycle.ClientWindowFactoryImpl;
import org.apache.myfaces.renderkit.ErrorPageWriter;
import org.apache.myfaces.renderkit.html.HtmlResponseStateManager;
import org.apache.myfaces.resource.InternalClassLoaderResourceLoader;
import org.apache.myfaces.view.facelets.DefaultFaceletsStateManagementStrategy;
import org.apache.myfaces.view.facelets.FaceletViewDeclarationLanguage;
import org.apache.myfaces.view.facelets.el.ContextAwareUtils;
import org.apache.myfaces.view.facelets.impl.FaceletCompositionContextImpl;
import org.apache.myfaces.view.facelets.pool.ViewPool;
import org.apache.myfaces.webapp.AbstractFacesInitializer;

/**
 * Servlet context configurer of MyFaces.
 * @author Marcelo Fernandes
 */
public class MyfacesServletContextConfigurer extends ServletContextConfigurer {

	private MyfacesProperties myfacesProperties;

	/**
	 * Preffix of original MyFaces parameters.
	 */
	public static final String PREFFIX = "org.apache.myfaces.";

	@Builder
	public MyfacesServletContextConfigurer(MyfacesProperties myfacesProperties, ServletContext servletContext) {
		super(servletContext, "");
		this.myfacesProperties = myfacesProperties;
	}

	private void configureExpressionLanguage() {
		setInitParameter(PREFFIX + "STRICT_JSF_2_CC_EL_RESOLVER", this.myfacesProperties.getStrictJsf2CcElResolver());
		setInitParameter(PREFFIX + "SUPPORT_JSP_AND_FACES_EL", this.myfacesProperties.getSupportJspAndFacesEl());
		setInitParameter(PREFFIX + "SUPPORT_EL_3_IMPORT_HANDLER", this.myfacesProperties.getSupportEl3ImportHandler());
		setInitParameter(ResolverBuilderBase.EL_RESOLVER_COMPARATOR, this.myfacesProperties.getElResolverComparator());
		setInitParameter(ResolverBuilderBase.EL_RESOLVER_PREDICATE, this.myfacesProperties.getElResolverPredicate());
		setInitParameter(FaceletCompositionContextImpl.INIT_PARAM_CACHE_EL_EXPRESSIONS, this.myfacesProperties.getCacheElExpressions());
		setInitParameter(PREFFIX + "EXPRESSION_FACTORY", this.myfacesProperties.getExpressionFactory());
	}

	private void configureCDI() {
		setInitParameter(PREFFIX + "CDI_MANAGED_CONVERTERS_ENABLED", this.myfacesProperties.getCdiManagedConvertersEnabled());
		setInitParameter(PREFFIX + "CDI_MANAGED_VALIDATORS_ENABLED", this.myfacesProperties.getCdiManagedValidatorsEnabled());
	}

	private void configureRendering() {
		setInitParameter(PREFFIX + "PRETTY_HTML", this.myfacesProperties.getPrettyHtml());
		setInitParameter(PREFFIX + "ALLOW_JAVASCRIPT", this.myfacesProperties.getAllowJavascript());
		setInitParameter(PREFFIX + "STRICT_XHTML_LINKS", this.myfacesProperties.getStrictXhtmlLinks());
		setInitParameter(PREFFIX + "RENDER_CLEAR_JAVASCRIPT_FOR_BUTTON", this.myfacesProperties.getRenderClearJavascriptForButton());
		setInitParameter(PREFFIX + "RENDER_HIDDEN_FIELDS_FOR_LINK_PARAMS", this.myfacesProperties.getRenderHiddenFieldsForLinkParams());
		setInitParameter(PREFFIX + "SAVE_FORM_SUBMIT_LINK_IE", this.myfacesProperties.getSaveFormSubmitLinkIe());
		setInitParameter(PREFFIX + "WRAP_SCRIPT_CONTENT_WITH_XML_COMMENT_TAG", this.myfacesProperties.getWrapScriptContentWithXmlCommentTag());
		setInitParameter(PREFFIX + "RENDER_FORM_SUBMIT_SCRIPT_INLINE", this.myfacesProperties.getRenderFormSubmitScriptInline());
		setInitParameter(PREFFIX + "DEFAULT_RESPONSE_WRITER_CONTENT_TYPE_MODE", this.myfacesProperties.getDefaultResponseWriterContentTypeMode());
		setInitParameter(PREFFIX + "EARLY_FLUSH_ENABLED", this.myfacesProperties.getEarlyFlushEnabled());
		setInitParameter(PREFFIX + "RENDER_FORM_VIEW_STATE_AT_BEGIN", this.myfacesProperties.getRenderFormViewStateAtBegin());
		setInitParameter(InternalClassLoaderResourceLoader.USE_MULTIPLE_JS_FILES_FOR_JSF_UNCOMPRESSED_JS, this.myfacesProperties.getUseMultipleJsFilesForJsfUncompressedJs());
		setInitParameter(InternalClassLoaderResourceLoader.MYFACES_JSF_MODE, this.myfacesProperties.getJsfJsMode());
	}

	private void configureResources() {
		setInitParameter(PREFFIX + "RESOURCE_MAX_TIME_EXPIRES", this.myfacesProperties.getResourceMaxTimeExpires());
		setInitParameter(PREFFIX + "RESOURCE_HANDLER_CACHE_SIZE", this.myfacesProperties.getResourceHandlerCacheSize());
		setInitParameter(PREFFIX + "RESOURCE_HANDLER_CACHE_ENABLED", this.myfacesProperties.getResourceHandlerCacheEnabled());
		setInitParameter(ResourceHandlerImpl.INIT_PARAM_STRICT_JSF_2_ALLOW_SLASH_LIBRARY_NAME, this.myfacesProperties.getStrictJsf2AllowSlashLibraryName());
		setInitParameter(ResourceHandlerImpl.INIT_PARAM_RESOURCE_BUFFER_SIZE, this.myfacesProperties.getResourceBufferSize());
	}

	private void configureStateSaving() {
		setInitParameter(PREFFIX + "RENDER_VIEWSTATE_ID", this.myfacesProperties.getRenderViewstateId());
		setInitParameter(FaceletViewDeclarationLanguage.REFRESH_TRANSIENT_BUILD_ON_PSS, this.myfacesProperties.getRefreshTransientBuildOnPss());
		setInitParameter(PREFFIX + "REFRESH_TRANSIENT_BUILD_ON_PSS_PRESERVE_STATE", this.myfacesProperties.getRefreshTransientBuildOnPssPreserveState());
		setInitParameter(PREFFIX + "NUMBER_OF_VIEWS_IN_SESSION", this.myfacesProperties.getNumberOfViewsInSession());
		setInitParameter(PREFFIX + "NUMBER_OF_SEQUENTIAL_VIEWS_IN_SESSION", this.myfacesProperties.getNumberOfSequentialViewsInSession());
		setInitParameter(PREFFIX + "NUMBER_OF_FLASH_TOKENS_IN_SESSION", this.myfacesProperties.getNumberOfFlashTokensInSession());
		setInitParameter(PREFFIX + "FACES_FLOW_CLIENT_WINDOW_IDS_IN_SESSION", this.myfacesProperties.getFacesFlowClientWindowIdsInSession());
		setInitParameter(PREFFIX + "USE_ENCRYPTION", this.myfacesProperties.getUseEncryption());
		setInitParameter(PREFFIX + "SECRET", this.myfacesProperties.getSecret());
		setInitParameter(PREFFIX + "ALGORITHM", this.myfacesProperties.getAlgorithm());
		setInitParameter(PREFFIX + "SECRET.CACHE", this.myfacesProperties.getSecretCache());
		setInitParameter(PREFFIX + "ALGORITHM.IV", this.myfacesProperties.getAlgorithmIv());
		setInitParameter(PREFFIX + "ALGORITHM.PARAMETERS", this.myfacesProperties.getAlgorithmParameters());
		setInitParameter(PREFFIX + "SERIAL_FACTORY", this.myfacesProperties.getSerialFactory());
		setInitParameter(PREFFIX + "COMPRESS_STATE_IN_CLIENT", this.myfacesProperties.getCompressStateInClient());
		setInitParameter(PREFFIX + "MAC_ALGORITHM", this.myfacesProperties.getMacAlgorithm());
		setInitParameter(PREFFIX + "MAC_SECRET", this.myfacesProperties.getMacSecret());
		setInitParameter(PREFFIX + "MAC_SECRET.CACHE", this.myfacesProperties.getMacSecretCache());
		setInitParameter(StateCache.RANDOM_KEY_IN_CSRF_SESSION_TOKEN_PARAM, this.myfacesProperties.getRandomKeyInCsrfSessionToken());
		setInitParameter(StateCache.RANDOM_KEY_IN_CSRF_SESSION_TOKEN_LENGTH_PARAM, this.myfacesProperties.getRandomKeyInCsrfSessionTokenLength());
		setInitParameter(StateCache.RANDOM_KEY_IN_CSRF_SESSION_TOKEN_SECURE_RANDOM_CLASS_PARAM, this.myfacesProperties.getRandomKeyInCsrfSessionTokenSecureRandomClass());
		setInitParameter(StateCache.RANDOM_KEY_IN_CSRF_SESSION_TOKEN_SECURE_RANDOM_PROVIDER_PARAM, this.myfacesProperties.getRandomKeyInCsrfSessionTokenSecureRandomProvider());
		setInitParameter(StateCache.RANDOM_KEY_IN_CSRF_SESSION_TOKEN_SECURE_RANDOM_ALGORITM_PARAM, this.myfacesProperties.getRandomKeyInCsrfSessionTokenSecureRandomAlgoritm());
		setInitParameter(PREFFIX + "CLIENT_VIEW_STATE_TIMEOUT", this.myfacesProperties.getClientViewStateTimeout());
		setInitParameter(PREFFIX + "COMPRESS_STATE_IN_SESSION", this.myfacesProperties.getCompressStateInSession());
		setInitParameter(PREFFIX + "USE_FLASH_SCOPE_PURGE_VIEWS_IN_SESSION", this.myfacesProperties.getUseFlashScopePurgeViewsInSession());
		setInitParameter(PREFFIX + "RANDOM_KEY_IN_VIEW_STATE_SESSION_TOKEN", this.myfacesProperties.getRandomKeyInViewStateSessionToken());
		setInitParameter(PREFFIX + "RANDOM_KEY_IN_VIEW_STATE_SESSION_TOKEN_LENGTH", this.myfacesProperties.getRandomKeyInViewStateSessionTokenLength());
		setInitParameter(PREFFIX + "RANDOM_KEY_IN_VIEW_STATE_SESSION_TOKEN_SECURE_RANDOM_CLASS", this.myfacesProperties.getRandomKeyInViewStateSessionTokenSecureRandomClass());
		setInitParameter(PREFFIX + "RANDOM_KEY_IN_VIEW_STATE_SESSION_TOKEN_SECURE_RANDOM_PROVIDER", this.myfacesProperties.getRandomKeyInViewStateSessionTokenSecureRandomProvider());
		setInitParameter(PREFFIX + "RANDOM_KEY_IN_VIEW_STATE_SESSION_TOKEN_SECURE_RANDOM_ALGORITM", this.myfacesProperties.getRandomKeyInViewStateSessionTokenSecureRandomAlgorithm());
		setInitParameter(HtmlResponseStateManager.INIT_PARAM_AUTOCOMPLETE_OFF_VIEW_STATE, this.myfacesProperties.getAutocompleteOffViewState());
		setInitParameter(DefaultFaceletsStateManagementStrategy.CHECK_ID_PRODUCTION_MODE, this.myfacesProperties.getCheckIdProductionMode());
	}

	private void configureConversionAndValidation() {
		setInitParameter(PREFFIX + "ENUM_CONVERTER_ALLOW_STRING_PASSTROUGH", this.myfacesProperties.getEnumConverterAllowStringPasstrough());
		setInitParameter(PREFFIX + "validator.BEAN_BEFORE_JSF_VALIDATION", this.myfacesProperties.getValidator().getBeanBeforeJsfValidation());
	}

	private void configureViewHandling() {
		setInitParameter(PREFFIX + "CHECKED_VIEWID_CACHE_SIZE", this.myfacesProperties.getCheckedViewidCacheSize());
		setInitParameter(PREFFIX + "CHECKED_VIEWID_CACHE_ENABLED", this.myfacesProperties.getCheckedViewidCacheEnabled());
		setInitParameter(PREFFIX + "VIEW_UNIQUE_IDS_CACHE_ENABLED", this.myfacesProperties.getViewUniqueIdsCacheEnabled());
		setInitParameter(PREFFIX + "COMPONENT_UNIQUE_IDS_CACHE_SIZE", this.myfacesProperties.getComponentUniqueIdsCacheSize());
		setInitParameter(PREFFIX + "STRICT_JSF_2_VIEW_NOT_FOUND", this.myfacesProperties.getStrictJsf2ViewNotFound());
		setInitParameter(PREFFIX + "STRICT_JSF_2_FACELETS_COMPATIBILITY", this.myfacesProperties.getStrictJsf2FaceletsCompatibility());
	}

	private void configureOther() {
		setInitParameter(PREFFIX + "CONFIG_REFRESH_PERIOD", this.myfacesProperties.getConfigRefreshPeriod());
		setInitParameter(PREFFIX + "DELEGATE_FACES_SERVLET", this.myfacesProperties.getDelegateFacesServlet());
		setInitParameter(PREFFIX + "VALIDATE_XML", this.myfacesProperties.getValidateXml());
		setInitParameter(PREFFIX + "DEBUG_PHASE_LISTENER", this.myfacesProperties.getDebugPhaseListener());
		setInitParameter(PREFFIX + "STRICT_JSF_2_REFRESH_TARGET_AJAX", this.myfacesProperties.getStrictJsf2RefreshTargetAjax());
		setInitParameter(PREFFIX + "GAE_JSF_JAR_FILES", this.myfacesProperties.getGaeJsfJarFiles());
		setInitParameter(PREFFIX + "GAE_JSF_ANNOTATIONS_JAR_FILES", this.myfacesProperties.getGaeJsfAnnotationsJarFiles());
		setInitParameter(PREFFIX + "FLASH_SCOPE_DISABLED", this.myfacesProperties.getFlashScopeDisabled());
		setInitParameter(PREFFIX + "LAZY_LOAD_CONFIG_OBJECTS", this.myfacesProperties.getLazyLoadConfigObjects());
		setInitParameter(FacesConfigValidator.VALIDATE_CONTEXT_PARAM, this.myfacesProperties.getValidate());
		setInitParameter(PREFFIX + "INITIALIZE_SKIP_JAR_FACES_CONFIG_SCAN", this.myfacesProperties.getInitializeSkipJarFacesConfigScan());
		setInitParameter(ClientWindowFactoryImpl.INIT_PARAM_DEFAULT_WINDOW_MODE, this.myfacesProperties.getDefaultWindowMode());
		setInitParameter(PREFFIX + "ERROR_TEMPLATE_RESOURCE", this.myfacesProperties.getErrorTemplateResource());
		setInitParameter(PREFFIX + "DEBUG_TEMPLATE_RESOURCE", this.myfacesProperties.getDebugTemplateResource());
		setInitParameter(ErrorPageWriter.ERROR_HANDLING_PARAMETER, this.myfacesProperties.getErrorHandling());
		setInitParameter(PREFFIX + "TEMPORAL_RESOURCEHANDLER_CACHE_ENABLED", this.myfacesProperties.getTemporalResourcehandlerCacheEnabled());
		setInitParameter(PREFFIX + "SERVICE_PROVIDER_FINDER", this.myfacesProperties.getServiceProviderFinder());
		setInitParameter(PREFFIX + "spi.InjectionProvider", this.myfacesProperties.getSpi().getInjectionProvider());
		setInitParameter(PREFFIX + "MARK_INITIAL_STATE_WHEN_APPLY_BUILD_VIEW", this.myfacesProperties.getMarkInitialStateWhenApplyBuildView());
		setInitParameter(ContextAwareUtils.INIT_PARAM_WRAP_TAG_EXCEPTIONS_AS_CONTEXT_AWARE, this.myfacesProperties.getWrapTagExceptionsAsContextAware());
		setInitParameter(ViewPool.INIT_PARAM_VIEW_POOL_MAX_POOL_SIZE, this.myfacesProperties.getViewPoolMaxPoolSize());
		setInitParameter(ViewPool.INIT_PARAM_VIEW_POOL_MAX_DYNAMIC_PARTIAL_LIMIT, this.myfacesProperties.getViewPoolMaxDynamicPartialLimit());
		setInitParameter(ViewPool.INIT_PARAM_VIEW_POOL_ENTRY_MODE, this.myfacesProperties.getViewPoolEntryMode());
		setInitParameter(ViewPool.INIT_PARAM_VIEW_POOL_DEFERRED_NAVIGATION, this.myfacesProperties.getViewPoolDeferredNavigation());
		setInitParameter(AbstractFacesInitializer.INIT_PARAM_LOG_WEB_CONTEXT_PARAMS, this.myfacesProperties.getLogWebContextParams());
		setInitParameter(PREFFIX + "FACES_INITIALIZER", this.myfacesProperties.getFacesInitializer());
		setInitParameter(PREFFIX + "FACES_INIT_PLUGINS", this.myfacesProperties.getFacesInitPlugins());
		setInitParameter(CdiAnnotationProviderExtension.USE_CDI_FOR_ANNOTATION_SCANNING, this.myfacesProperties.getAnnotation().getUseCdiForAnnotationScanning());
		setInitParameter(DefaultAnnotationProvider.SCAN_PACKAGES, this.myfacesProperties.getAnnotation().getScanPackages());
		setInitParameter(PREFFIX + "INITIALIZE_ALWAYS_STANDALONE", this.myfacesProperties.getInitializeAlwaysStandalone());
	}

	private void configureDeprecated() {
		setInitParameter(PREFFIX + "ERROR_HANDLER", this.myfacesProperties.getErrorHandler());
		setInitParameter(PREFFIX + "VIEWSTATE_JAVASCRIPT", this.myfacesProperties.getViewstateJavascript());
		setInitParameter(PREFFIX + "SERIALIZE_STATE_IN_SESSION", this.myfacesProperties.getSerializeStateInSession());
		setInitParameter(PREFFIX + "CACHE_OLD_VIEWS_IN_SESSION_MODE", this.myfacesProperties.getCacheOldViewsInSessionMode());
		setInitParameter(HtmlResponseStateManager.INIT_PARAM_HANDLE_STATE_CACHING_MECHANICS, this.myfacesProperties.getHandleStateCachingMechanics());
		setInitParameter(DefaultFaceletsStateManagementStrategy.SAVE_STATE_WITH_VISIT_TREE_ON_PSS, this.myfacesProperties.getSaveStateWithVisitTreeOnPass());
	}

	@Override
	public void configure() {
		configureExpressionLanguage();
		configureCDI();
		configureRendering();
		configureResources();
		configureStateSaving();
		configureConversionAndValidation();
		configureViewHandling();
		configureOther();
		configureDeprecated();
	}
}
