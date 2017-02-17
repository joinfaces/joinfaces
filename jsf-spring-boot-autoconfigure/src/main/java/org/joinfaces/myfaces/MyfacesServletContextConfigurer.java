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

package org.joinfaces.myfaces;

import javax.servlet.ServletContext;

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
import org.joinfaces.ServletContextConfigurer;

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
		setInitParameterString(PREFFIX + "STRICT_JSF_2_CC_EL_RESOLVER", this.myfacesProperties.getStrictJsf2CcElResolver());
		setInitParameterBoolean(PREFFIX + "SUPPORT_JSP_AND_FACES_EL", this.myfacesProperties.getSupportJspAndFacesEl());
		setInitParameterBoolean(PREFFIX + "SUPPORT_EL_3_IMPORT_HANDLER", this.myfacesProperties.getSupportEl3ImportHandler());
		setInitParameterClass(ResolverBuilderBase.EL_RESOLVER_COMPARATOR, this.myfacesProperties.getElResolverComparator());
		setInitParameterClass(ResolverBuilderBase.EL_RESOLVER_PREDICATE, this.myfacesProperties.getElResolverPredicate());
		setInitParameterBoolean(FaceletCompositionContextImpl.INIT_PARAM_CACHE_EL_EXPRESSIONS, this.myfacesProperties.getCacheElExpressions());
		setInitParameterClass(PREFFIX + "EXPRESSION_FACTORY", this.myfacesProperties.getExpressionFactory());
	}

	private void configureCDI() {
		setInitParameterBoolean(PREFFIX + "CDI_MANAGED_CONVERTERS_ENABLED", this.myfacesProperties.getCdiManagedConvertersEnabled());
		setInitParameterBoolean(PREFFIX + "CDI_MANAGED_VALIDATORS_ENABLED", this.myfacesProperties.getCdiManagedValidatorsEnabled());
	}

	private void configureRendering() {
		setInitParameterBoolean(PREFFIX + "PRETTY_HTML", this.myfacesProperties.getPrettyHtml());
		setInitParameterBoolean(PREFFIX + "ALLOW_JAVASCRIPT", this.myfacesProperties.getAllowJavascript());
		setInitParameterBoolean(PREFFIX + "STRICT_XHTML_LINKS", this.myfacesProperties.getStrictXhtmlLinks());
		setInitParameterBoolean(PREFFIX + "RENDER_CLEAR_JAVASCRIPT_FOR_BUTTON", this.myfacesProperties.getRenderClearJavascriptForButton());
		setInitParameterBoolean(PREFFIX + "RENDER_HIDDEN_FIELDS_FOR_LINK_PARAMS", this.myfacesProperties.getRenderHiddenFieldsForLinkParams());
		setInitParameterBoolean(PREFFIX + "SAVE_FORM_SUBMIT_LINK_IE", this.myfacesProperties.getSaveFormSubmitLinkIe());
		setInitParameterBoolean(PREFFIX + "WRAP_SCRIPT_CONTENT_WITH_XML_COMMENT_TAG", this.myfacesProperties.getWrapScriptContentWithXmlCommentTag());
		setInitParameterBoolean(PREFFIX + "RENDER_FORM_SUBMIT_SCRIPT_INLINE", this.myfacesProperties.getRenderFormSubmitScriptInline());
		setInitParameterString(PREFFIX + "DEFAULT_RESPONSE_WRITER_CONTENT_TYPE_MODE", this.myfacesProperties.getDefaultResponseWriterContentTypeMode());
		setInitParameterBoolean(PREFFIX + "EARLY_FLUSH_ENABLED", this.myfacesProperties.getEarlyFlushEnabled());
		setInitParameterBoolean(PREFFIX + "RENDER_FORM_VIEW_STATE_AT_BEGIN", this.myfacesProperties.getRenderFormViewStateAtBegin());
		setInitParameterBoolean(InternalClassLoaderResourceLoader.USE_MULTIPLE_JS_FILES_FOR_JSF_UNCOMPRESSED_JS, this.myfacesProperties.getUseMultipleJsFilesForJsfUncompressedJs());
		setInitParameterString(InternalClassLoaderResourceLoader.MYFACES_JSF_MODE, this.myfacesProperties.getJsfJsMode());
	}

	private void configureResources() {
		setInitParameterInteger(PREFFIX + "RESOURCE_MAX_TIME_EXPIRES", this.myfacesProperties.getResourceMaxTimeExpires());
		setInitParameterInteger(PREFFIX + "RESOURCE_HANDLER_CACHE_SIZE", this.myfacesProperties.getResourceHandlerCacheSize());
		setInitParameterBoolean(PREFFIX + "RESOURCE_HANDLER_CACHE_ENABLED", this.myfacesProperties.getResourceHandlerCacheEnabled());
		setInitParameterBoolean(ResourceHandlerImpl.INIT_PARAM_STRICT_JSF_2_ALLOW_SLASH_LIBRARY_NAME, this.myfacesProperties.getStrictJsf2AllowSlashLibraryName());
		setInitParameterInteger(ResourceHandlerImpl.INIT_PARAM_RESOURCE_BUFFER_SIZE, this.myfacesProperties.getResourceBufferSize());
	}

	private void configureStateSaving() {
		setInitParameterBoolean(PREFFIX + "RENDER_VIEWSTATE_ID", this.myfacesProperties.getRenderViewstateId());
		setInitParameterString(FaceletViewDeclarationLanguage.REFRESH_TRANSIENT_BUILD_ON_PSS, this.myfacesProperties.getRefreshTransientBuildOnPss());
		setInitParameterBoolean(PREFFIX + "REFRESH_TRANSIENT_BUILD_ON_PSS_PRESERVE_STATE", this.myfacesProperties.getRefreshTransientBuildOnPssPreserveState());
		setInitParameterInteger(PREFFIX + "NUMBER_OF_VIEWS_IN_SESSION", this.myfacesProperties.getNumberOfViewsInSession());
		setInitParameterInteger(PREFFIX + "NUMBER_OF_SEQUENTIAL_VIEWS_IN_SESSION", this.myfacesProperties.getNumberOfSequentialViewsInSession());
		setInitParameterInteger(PREFFIX + "NUMBER_OF_FLASH_TOKENS_IN_SESSION", this.myfacesProperties.getNumberOfFlashTokensInSession());
		setInitParameterInteger(PREFFIX + "FACES_FLOW_CLIENT_WINDOW_IDS_IN_SESSION", this.myfacesProperties.getFacesFlowClientWindowIdsInSession());
		setInitParameterBoolean(PREFFIX + "USE_ENCRYPTION", this.myfacesProperties.getUseEncryption());
		setInitParameterString(PREFFIX + "SECRET", this.myfacesProperties.getSecret());
		setInitParameterString(PREFFIX + "ALGORITHM", this.myfacesProperties.getAlgorithm());
		setInitParameterBoolean(PREFFIX + "SECRET.CACHE", this.myfacesProperties.getSecretCache());
		setInitParameterString(PREFFIX + "ALGORITHM.IV", this.myfacesProperties.getAlgorithmIv());
		setInitParameterString(PREFFIX + "ALGORITHM.PARAMETERS", this.myfacesProperties.getAlgorithmParameters());
		setInitParameterClass(PREFFIX + "SERIAL_FACTORY", this.myfacesProperties.getSerialFactory());
		setInitParameterBoolean(PREFFIX + "COMPRESS_STATE_IN_CLIENT", this.myfacesProperties.getCompressStateInClient());
		setInitParameterString(PREFFIX + "MAC_ALGORITHM", this.myfacesProperties.getMacAlgorithm());
		setInitParameterString(PREFFIX + "MAC_SECRET", this.myfacesProperties.getMacSecret());
		setInitParameterBoolean(PREFFIX + "MAC_SECRET.CACHE", this.myfacesProperties.getMacSecretCache());
		setInitParameterString(StateCache.RANDOM_KEY_IN_CSRF_SESSION_TOKEN_PARAM, this.myfacesProperties.getRandomKeyInCsrfSessionToken());
		setInitParameterInteger(StateCache.RANDOM_KEY_IN_CSRF_SESSION_TOKEN_LENGTH_PARAM, this.myfacesProperties.getRandomKeyInCsrfSessionTokenLength());
		setInitParameterString(StateCache.RANDOM_KEY_IN_CSRF_SESSION_TOKEN_SECURE_RANDOM_CLASS_PARAM, this.myfacesProperties.getRandomKeyInCsrfSessionTokenSecureRandomClass());
		setInitParameterString(StateCache.RANDOM_KEY_IN_CSRF_SESSION_TOKEN_SECURE_RANDOM_PROVIDER_PARAM, this.myfacesProperties.getRandomKeyInCsrfSessionTokenSecureRandomProvider());
		setInitParameterString(StateCache.RANDOM_KEY_IN_CSRF_SESSION_TOKEN_SECURE_RANDOM_ALGORITM_PARAM, this.myfacesProperties.getRandomKeyInCsrfSessionTokenSecureRandomAlgoritm());
		setInitParameterInteger(PREFFIX + "CLIENT_VIEW_STATE_TIMEOUT", this.myfacesProperties.getClientViewStateTimeout());
		setInitParameterBoolean(PREFFIX + "COMPRESS_STATE_IN_SESSION", this.myfacesProperties.getCompressStateInSession());
		setInitParameterBoolean(PREFFIX + "USE_FLASH_SCOPE_PURGE_VIEWS_IN_SESSION", this.myfacesProperties.getUseFlashScopePurgeViewsInSession());
		setInitParameterString(PREFFIX + "RANDOM_KEY_IN_VIEW_STATE_SESSION_TOKEN", this.myfacesProperties.getRandomKeyInViewStateSessionToken());
		setInitParameterInteger(PREFFIX + "RANDOM_KEY_IN_VIEW_STATE_SESSION_TOKEN_LENGTH", this.myfacesProperties.getRandomKeyInViewStateSessionTokenLength());
		setInitParameterString(PREFFIX + "RANDOM_KEY_IN_VIEW_STATE_SESSION_TOKEN_SECURE_RANDOM_CLASS", this.myfacesProperties.getRandomKeyInViewStateSessionTokenSecureRandomClass());
		setInitParameterString(PREFFIX + "RANDOM_KEY_IN_VIEW_STATE_SESSION_TOKEN_SECURE_RANDOM_PROVIDER", this.myfacesProperties.getRandomKeyInViewStateSessionTokenSecureRandomProvider());
		setInitParameterString(PREFFIX + "RANDOM_KEY_IN_VIEW_STATE_SESSION_TOKEN_SECURE_RANDOM_ALGORITM", this.myfacesProperties.getRandomKeyInViewStateSessionTokenSecureRandomAlgorithm());
		setInitParameterBoolean(HtmlResponseStateManager.INIT_PARAM_AUTOCOMPLETE_OFF_VIEW_STATE, this.myfacesProperties.getAutocompleteOffViewState());
		setInitParameterString(DefaultFaceletsStateManagementStrategy.CHECK_ID_PRODUCTION_MODE, this.myfacesProperties.getCheckIdProductionMode());
	}

	private void configureConversionAndValidation() {
		setInitParameterBoolean(PREFFIX + "ENUM_CONVERTER_ALLOW_STRING_PASSTROUGH", this.myfacesProperties.getEnumConverterAllowStringPasstrough());
		setInitParameterBoolean(PREFFIX + "validator.BEAN_BEFORE_JSF_VALIDATION", this.myfacesProperties.getValidator().getBeanBeforeJsfValidation());
	}

	private void configureViewHandling() {
		setInitParameterInteger(PREFFIX + "CHECKED_VIEWID_CACHE_SIZE", this.myfacesProperties.getCheckedViewidCacheSize());
		setInitParameterBoolean(PREFFIX + "CHECKED_VIEWID_CACHE_ENABLED", this.myfacesProperties.getCheckedViewidCacheEnabled());
		setInitParameterBoolean(PREFFIX + "VIEW_UNIQUE_IDS_CACHE_ENABLED", this.myfacesProperties.getViewUniqueIdsCacheEnabled());
		setInitParameterInteger(PREFFIX + "COMPONENT_UNIQUE_IDS_CACHE_SIZE", this.myfacesProperties.getComponentUniqueIdsCacheSize());
		setInitParameterBoolean(PREFFIX + "STRICT_JSF_2_VIEW_NOT_FOUND", this.myfacesProperties.getStrictJsf2ViewNotFound());
		setInitParameterBoolean(PREFFIX + "STRICT_JSF_2_FACELETS_COMPATIBILITY", this.myfacesProperties.getStrictJsf2FaceletsCompatibility());
	}

	private void configureOther() {
		setInitParameterInteger(PREFFIX + "CONFIG_REFRESH_PERIOD", this.myfacesProperties.getConfigRefreshPeriod());
		setInitParameterClass(PREFFIX + "DELEGATE_FACES_SERVLET", this.myfacesProperties.getDelegateFacesServlet());
		setInitParameterBoolean(PREFFIX + "VALIDATE_XML", this.myfacesProperties.getValidateXml());
		setInitParameterBoolean(PREFFIX + "DEBUG_PHASE_LISTENER", this.myfacesProperties.getDebugPhaseListener());
		setInitParameterBoolean(PREFFIX + "STRICT_JSF_2_REFRESH_TARGET_AJAX", this.myfacesProperties.getStrictJsf2RefreshTargetAjax());
		setInitParameterString(PREFFIX + "GAE_JSF_JAR_FILES", this.myfacesProperties.getGaeJsfJarFiles());
		setInitParameterString(PREFFIX + "GAE_JSF_ANNOTATIONS_JAR_FILES", this.myfacesProperties.getGaeJsfAnnotationsJarFiles());
		setInitParameterBoolean(PREFFIX + "FLASH_SCOPE_DISABLED", this.myfacesProperties.getFlashScopeDisabled());
		setInitParameterBoolean(PREFFIX + "LAZY_LOAD_CONFIG_OBJECTS", this.myfacesProperties.getLazyLoadConfigObjects());
		setInitParameterBoolean(FacesConfigValidator.VALIDATE_CONTEXT_PARAM, this.myfacesProperties.getValidate());
		setInitParameterBoolean(PREFFIX + "INITIALIZE_SKIP_JAR_FACES_CONFIG_SCAN", this.myfacesProperties.getInitializeSkipJarFacesConfigScan());
		setInitParameterString(ClientWindowFactoryImpl.INIT_PARAM_DEFAULT_WINDOW_MODE, this.myfacesProperties.getDefaultWindowMode());
		setInitParameterString(PREFFIX + "ERROR_TEMPLATE_RESOURCE", this.myfacesProperties.getErrorTemplateResource());
		setInitParameterString(PREFFIX + "DEBUG_TEMPLATE_RESOURCE", this.myfacesProperties.getDebugTemplateResource());
		setInitParameterBoolean(ErrorPageWriter.ERROR_HANDLING_PARAMETER, this.myfacesProperties.getErrorHandling());
		setInitParameterBoolean(PREFFIX + "TEMPORAL_RESOURCEHANDLER_CACHE_ENABLED", this.myfacesProperties.getTemporalResourcehandlerCacheEnabled());
		setInitParameterClass(PREFFIX + "SERVICE_PROVIDER_FINDER", this.myfacesProperties.getServiceProviderFinder());
		setInitParameterString(PREFFIX + "spi.InjectionProvider", this.myfacesProperties.getSpi().getInjectionProvider());
		setInitParameterBoolean(PREFFIX + "MARK_INITIAL_STATE_WHEN_APPLY_BUILD_VIEW", this.myfacesProperties.getMarkInitialStateWhenApplyBuildView());
		setInitParameterBoolean(ContextAwareUtils.INIT_PARAM_WRAP_TAG_EXCEPTIONS_AS_CONTEXT_AWARE, this.myfacesProperties.getWrapTagExceptionsAsContextAware());
		setInitParameterInteger(ViewPool.INIT_PARAM_VIEW_POOL_MAX_POOL_SIZE, this.myfacesProperties.getViewPoolMaxPoolSize());
		setInitParameterInteger(ViewPool.INIT_PARAM_VIEW_POOL_MAX_DYNAMIC_PARTIAL_LIMIT, this.myfacesProperties.getViewPoolMaxDynamicPartialLimit());
		setInitParameterString(ViewPool.INIT_PARAM_VIEW_POOL_ENTRY_MODE, this.myfacesProperties.getViewPoolEntryMode());
		setInitParameterBoolean(ViewPool.INIT_PARAM_VIEW_POOL_DEFERRED_NAVIGATION, this.myfacesProperties.getViewPoolDeferredNavigation());
		setInitParameterString(AbstractFacesInitializer.INIT_PARAM_LOG_WEB_CONTEXT_PARAMS, this.myfacesProperties.getLogWebContextParams());
		setInitParameterClass(PREFFIX + "FACES_INITIALIZER", this.myfacesProperties.getFacesInitializer());
		setInitParameterClassCollection(PREFFIX + "FACES_INIT_PLUGINS", this.myfacesProperties.getFacesInitPlugins(), Separator.COMMA);
		setInitParameterBoolean(CdiAnnotationProviderExtension.USE_CDI_FOR_ANNOTATION_SCANNING, this.myfacesProperties.getAnnotation().getUseCdiForAnnotationScanning());
		setInitParameterString(DefaultAnnotationProvider.SCAN_PACKAGES, this.myfacesProperties.getAnnotation().getScanPackages());
		setInitParameterBoolean(PREFFIX + "INITIALIZE_ALWAYS_STANDALONE", this.myfacesProperties.getInitializeAlwaysStandalone());
	}

	private void configureDeprecated() {
		setInitParameterString(PREFFIX + "ERROR_HANDLER", this.myfacesProperties.getErrorHandler());
		setInitParameterBoolean(PREFFIX + "VIEWSTATE_JAVASCRIPT", this.myfacesProperties.getViewstateJavascript());
		setInitParameterBoolean(PREFFIX + "SERIALIZE_STATE_IN_SESSION", this.myfacesProperties.getSerializeStateInSession());
		setInitParameterBoolean(PREFFIX + "CACHE_OLD_VIEWS_IN_SESSION_MODE", this.myfacesProperties.getCacheOldViewsInSessionMode());
		setInitParameterBoolean(HtmlResponseStateManager.INIT_PARAM_HANDLE_STATE_CACHING_MECHANICS, this.myfacesProperties.getHandleStateCachingMechanics());
		setInitParameterBoolean(DefaultFaceletsStateManagementStrategy.SAVE_STATE_WITH_VISIT_TREE_ON_PSS, this.myfacesProperties.getSaveStateWithVisitTreeOnPass());
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
