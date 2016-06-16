package com.github.persapiens.jsfboot.myfaces;

import javax.servlet.ServletContext;
import lombok.Builder;
import com.github.persapiens.jsfboot.ServletContextConfigurer;
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

public class MyfacesServletContextConfigurer extends ServletContextConfigurer {

    private MyfacesProperties myfacesProperties;

    private static final String PREFFIX = "org.apache.myfaces.";
    
    @Builder
    public MyfacesServletContextConfigurer(MyfacesProperties myfacesProperties, ServletContext servletContext) {
        super(servletContext, "");
        this.myfacesProperties = myfacesProperties;
    }
    
    private void configureExpressionLanguage()
    {        
        setInitParameter(PREFFIX + "STRICT_JSF_2_CC_EL_RESOLVER", myfacesProperties.getStrictJsf2CcElResolver());
        setInitParameter(PREFFIX + "SUPPORT_JSP_AND_FACES_EL", myfacesProperties.getSupportJspAndFacesEl());
        setInitParameter(PREFFIX + "SUPPORT_EL_3_IMPORT_HANDLER", myfacesProperties.getSupportEl3ImportHandler());
        setInitParameter(ResolverBuilderBase.EL_RESOLVER_COMPARATOR, myfacesProperties.getElResolverComparator());
        setInitParameter(ResolverBuilderBase.EL_RESOLVER_PREDICATE, myfacesProperties.getElResolverPredicate());        
        setInitParameter(FaceletCompositionContextImpl.INIT_PARAM_CACHE_EL_EXPRESSIONS, myfacesProperties.getCacheElExpressions());        
        setInitParameter(PREFFIX + "EXPRESSION_FACTORY", myfacesProperties.getExpressionFactory());
    }

    private void configureCDI() {
        setInitParameter(PREFFIX + "CDI_MANAGED_CONVERTERS_ENABLED", myfacesProperties.getCdiManagedConvertersEnabled());
        setInitParameter(PREFFIX + "CDI_MANAGED_VALIDATORS_ENABLED", myfacesProperties.getCdiManagedValidatorsEnabled());
    }

    private void configureRendering() {
        setInitParameter(PREFFIX + "PRETTY_HTML", myfacesProperties.getPrettyHtml());
        setInitParameter(PREFFIX + "ALLOW_JAVASCRIPT", myfacesProperties.getAllowJavascript());
        setInitParameter(PREFFIX + "STRICT_XHTML_LINKS", myfacesProperties.getStrictXhtmlLinks());
        setInitParameter(PREFFIX + "RENDER_CLEAR_JAVASCRIPT_FOR_BUTTON", myfacesProperties.getRenderClearJavascriptForButton());
        setInitParameter(PREFFIX + "RENDER_HIDDEN_FIELDS_FOR_LINK_PARAMS", myfacesProperties.getRenderHiddenFieldsForLinkParams());
        setInitParameter(PREFFIX + "SAVE_FORM_SUBMIT_LINK_IE", myfacesProperties.getSaveFormSubmitLinkIe());
        setInitParameter(PREFFIX + "WRAP_SCRIPT_CONTENT_WITH_XML_COMMENT_TAG", myfacesProperties.getWrapScriptContentWithXmlCommentTag());
        setInitParameter(PREFFIX + "RENDER_FORM_SUBMIT_SCRIPT_INLINE", myfacesProperties.getRenderFormSubmitScriptInline());
        setInitParameter(PREFFIX + "DEFAULT_RESPONSE_WRITER_CONTENT_TYPE_MODE", myfacesProperties.getDefaultResponseWriterContentTypeMode());
        setInitParameter(PREFFIX + "EARLY_FLUSH_ENABLED", myfacesProperties.getEarlyFlushEnabled());
        setInitParameter(PREFFIX + "RENDER_FORM_VIEW_STATE_AT_BEGIN", myfacesProperties.getRenderFormViewStateAtBegin());
        setInitParameter(InternalClassLoaderResourceLoader.USE_MULTIPLE_JS_FILES_FOR_JSF_UNCOMPRESSED_JS, myfacesProperties.getUseMultipleJsFilesForJsfUncompressedJs());
        setInitParameter(InternalClassLoaderResourceLoader.MYFACES_JSF_MODE, myfacesProperties.getJsfJsMode());
    }

    private void configureResources() {
        setInitParameter(PREFFIX + "RESOURCE_MAX_TIME_EXPIRES", myfacesProperties.getResourceMaxTimeExpires());
        setInitParameter(PREFFIX + "RESOURCE_HANDLER_CACHE_SIZE", myfacesProperties.getResourceHandlerCacheSize());
        setInitParameter(PREFFIX + "RESOURCE_HANDLER_CACHE_ENABLED", myfacesProperties.getResourceHandlerCacheEnabled());
        setInitParameter(ResourceHandlerImpl.INIT_PARAM_STRICT_JSF_2_ALLOW_SLASH_LIBRARY_NAME, myfacesProperties.getStrictJsf2AllowSlashLibraryName());
        setInitParameter(ResourceHandlerImpl.INIT_PARAM_RESOURCE_BUFFER_SIZE, myfacesProperties.getResourceBufferSize());
    }

    private void configureStateSaving() {
        setInitParameter(PREFFIX + "RENDER_VIEWSTATE_ID", myfacesProperties.getRenderViewstateId());        
        setInitParameter(FaceletViewDeclarationLanguage.REFRESH_TRANSIENT_BUILD_ON_PSS, myfacesProperties.getRefreshTransientBuildOnPss());
        setInitParameter(PREFFIX + "REFRESH_TRANSIENT_BUILD_ON_PSS_PRESERVE_STATE", myfacesProperties.getRefreshTransientBuildOnPssPreserveState());        
        setInitParameter(PREFFIX + "NUMBER_OF_VIEWS_IN_SESSION", myfacesProperties.getNumberOfViewsInSession());        
        setInitParameter(PREFFIX + "NUMBER_OF_SEQUENTIAL_VIEWS_IN_SESSION", myfacesProperties.getNumberOfSequentialViewsInSession());        
        setInitParameter(PREFFIX + "NUMBER_OF_FLASH_TOKENS_IN_SESSION", myfacesProperties.getNumberOfFlashTokensInSession());        
        setInitParameter(PREFFIX + "FACES_FLOW_CLIENT_WINDOW_IDS_IN_SESSION", myfacesProperties.getFacesFlowClientWindowIdsInSession());        
        setInitParameter(PREFFIX + "USE_ENCRYPTION", myfacesProperties.getUseEncryption());
        setInitParameter(PREFFIX + "SECRET", myfacesProperties.getSecret());        
        setInitParameter(PREFFIX + "ALGORITHM", myfacesProperties.getAlgorithm());
        setInitParameter(PREFFIX + "SECRET.CACHE", myfacesProperties.getSecretCache());
        setInitParameter(PREFFIX + "ALGORITHM.IV", myfacesProperties.getAlgorithmIv());
        setInitParameter(PREFFIX + "ALGORITHM.PARAMETERS", myfacesProperties.getAlgorithmParameters());
        setInitParameter(PREFFIX + "SERIAL_FACTORY", myfacesProperties.getSerialFactory());
        setInitParameter(PREFFIX + "COMPRESS_STATE_IN_CLIENT", myfacesProperties.getCompressStateInClient());        
        setInitParameter(PREFFIX + "MAC_ALGORITHM", myfacesProperties.getMacAlgorithm());
        setInitParameter(PREFFIX + "MAC_SECRET", myfacesProperties.getMacSecret());
        setInitParameter(PREFFIX + "MAC_SECRET.CACHE", myfacesProperties.getMacSecretCache());
        setInitParameter(StateCache.RANDOM_KEY_IN_CSRF_SESSION_TOKEN_PARAM, myfacesProperties.getRandomKeyInCsrfSessionToken());
        setInitParameter(StateCache.RANDOM_KEY_IN_CSRF_SESSION_TOKEN_LENGTH_PARAM, myfacesProperties.getRandomKeyInCsrfSessionTokenLength());
        setInitParameter(StateCache.RANDOM_KEY_IN_CSRF_SESSION_TOKEN_SECURE_RANDOM_CLASS_PARAM, myfacesProperties.getRandomKeyInCsrfSessionTokenSecureRandomClass());
        setInitParameter(StateCache.RANDOM_KEY_IN_CSRF_SESSION_TOKEN_SECURE_RANDOM_PROVIDER_PARAM, myfacesProperties.getRandomKeyInCsrfSessionTokenSecureRandomProvider());
        setInitParameter(StateCache.RANDOM_KEY_IN_CSRF_SESSION_TOKEN_SECURE_RANDOM_ALGORITM_PARAM, myfacesProperties.getRandomKeyInCsrfSessionTokenSecureRandomAlgoritm());
        setInitParameter(PREFFIX + "CLIENT_VIEW_STATE_TIMEOUT", myfacesProperties.getClientViewStateTimeout());
        setInitParameter(PREFFIX + "COMPRESS_STATE_IN_SESSION", myfacesProperties.getCompressStateInSession());
        setInitParameter(PREFFIX + "USE_FLASH_SCOPE_PURGE_VIEWS_IN_SESSION", myfacesProperties.getUseFlashScopePurgeViewsInSession());
        setInitParameter(PREFFIX + "RANDOM_KEY_IN_VIEW_STATE_SESSION_TOKEN", myfacesProperties.getRandomKeyInViewStateSessionToken());
        setInitParameter(PREFFIX + "RANDOM_KEY_IN_VIEW_STATE_SESSION_TOKEN_LENGTH", myfacesProperties.getRandomKeyInViewStateSessionTokenLength());
        setInitParameter(PREFFIX + "RANDOM_KEY_IN_VIEW_STATE_SESSION_TOKEN_SECURE_RANDOM_CLASS", myfacesProperties.getRandomKeyInViewStateSessionTokenSecureRandomClass());
        setInitParameter(PREFFIX + "RANDOM_KEY_IN_VIEW_STATE_SESSION_TOKEN_SECURE_RANDOM_PROVIDER", myfacesProperties.getRandomKeyInViewStateSessionTokenSecureRandomProvider());
        setInitParameter(PREFFIX + "RANDOM_KEY_IN_VIEW_STATE_SESSION_TOKEN_SECURE_RANDOM_ALGORITM", myfacesProperties.getRandomKeyInViewStateSessionTokenSecureRandomAlgorithm());
        setInitParameter(HtmlResponseStateManager.INIT_PARAM_AUTOCOMPLETE_OFF_VIEW_STATE, myfacesProperties.getAutocompleteOffViewState());
        setInitParameter(DefaultFaceletsStateManagementStrategy.CHECK_ID_PRODUCTION_MODE, myfacesProperties.getCheckIdProductionMode());
    }

    private void configureConversionAndValidation() {
        setInitParameter(PREFFIX + "ENUM_CONVERTER_ALLOW_STRING_PASSTROUGH", myfacesProperties.getEnumConverterAllowStringPasstrough());
        setInitParameter(PREFFIX + "validator.BEAN_BEFORE_JSF_VALIDATION", myfacesProperties.getValidator().getBeanBeforeJsfValidation());
    }

    private void configureViewHandling() {
        setInitParameter(PREFFIX + "CHECKED_VIEWID_CACHE_SIZE", myfacesProperties.getCheckedViewidCacheSize());
        setInitParameter(PREFFIX + "CHECKED_VIEWID_CACHE_ENABLED", myfacesProperties.getCheckedViewidCacheEnabled());
        setInitParameter(PREFFIX + "VIEW_UNIQUE_IDS_CACHE_ENABLED", myfacesProperties.getViewUniqueIdsCacheEnabled());
        setInitParameter(PREFFIX + "COMPONENT_UNIQUE_IDS_CACHE_SIZE", myfacesProperties.getComponentUniqueIdsCacheSize());
        setInitParameter(PREFFIX + "STRICT_JSF_2_VIEW_NOT_FOUND", myfacesProperties.getStrictJsf2ViewNotFound());
        setInitParameter(PREFFIX + "STRICT_JSF_2_FACELETS_COMPATIBILITY", myfacesProperties.getStrictJsf2FaceletsCompatibility());
    }

    private void configureOther() {        
        setInitParameter(PREFFIX + "CONFIG_REFRESH_PERIOD", myfacesProperties.getConfigRefreshPeriod());
        setInitParameter(PREFFIX + "DELEGATE_FACES_SERVLET", myfacesProperties.getDelegateFacesServlet());
        setInitParameter(PREFFIX + "VALIDATE_XML", myfacesProperties.getValidateXml());
        setInitParameter(PREFFIX + "DEBUG_PHASE_LISTENER", myfacesProperties.getDebugPhaseListener());
        setInitParameter(PREFFIX + "STRICT_JSF_2_REFRESH_TARGET_AJAX", myfacesProperties.getStrictJsf2RefreshTargetAjax());
        setInitParameter(PREFFIX + "GAE_JSF_JAR_FILES", myfacesProperties.getGaeJsfJarFiles());
        setInitParameter(PREFFIX + "GAE_JSF_ANNOTATIONS_JAR_FILES", myfacesProperties.getGaeJsfAnnotationsJarFiles());
        setInitParameter(PREFFIX + "FLASH_SCOPE_DISABLED", myfacesProperties.getFlashScopeDisabled());
        setInitParameter(PREFFIX + "LAZY_LOAD_CONFIG_OBJECTS", myfacesProperties.getLazyLoadConfigObjects());
        setInitParameter(FacesConfigValidator.VALIDATE_CONTEXT_PARAM, myfacesProperties.getValidate());        
        setInitParameter(PREFFIX + "INITIALIZE_SKIP_JAR_FACES_CONFIG_SCAN", myfacesProperties.getInitializeSkipJarFacesConfigScan());
        setInitParameter(ClientWindowFactoryImpl.INIT_PARAM_DEFAULT_WINDOW_MODE, myfacesProperties.getDefaultWindowMode());        
        setInitParameter(PREFFIX + "ERROR_TEMPLATE_RESOURCE", myfacesProperties.getErrorTemplateResource());
        setInitParameter(PREFFIX + "DEBUG_TEMPLATE_RESOURCE", myfacesProperties.getDebugTemplateResource());
        setInitParameter(ErrorPageWriter.ERROR_HANDLING_PARAMETER, myfacesProperties.getErrorHandling());        
        setInitParameter(PREFFIX + "TEMPORAL_RESOURCEHANDLER_CACHE_ENABLED", myfacesProperties.getTemporalResourcehandlerCacheEnabled());        
        setInitParameter(PREFFIX + "SERVICE_PROVIDER_FINDER", myfacesProperties.getServiceProviderFinder());
        setInitParameter(PREFFIX + "spi.InjectionProvider", myfacesProperties.getSpi().getInjectionProvider());        
        setInitParameter(PREFFIX + "MARK_INITIAL_STATE_WHEN_APPLY_BUILD_VIEW", myfacesProperties.getMarkInitialStateWhenApplyBuildView());
        setInitParameter(ContextAwareUtils.INIT_PARAM_WRAP_TAG_EXCEPTIONS_AS_CONTEXT_AWARE, myfacesProperties.getWrapTagExceptionsAsContextAware());
        setInitParameter(ViewPool.INIT_PARAM_VIEW_POOL_MAX_POOL_SIZE, myfacesProperties.getViewPoolMaxPoolSize());
        setInitParameter(ViewPool.INIT_PARAM_VIEW_POOL_MAX_DYNAMIC_PARTIAL_LIMIT, myfacesProperties.getViewPoolMaxDynamicPartialLimit());
        setInitParameter(ViewPool.INIT_PARAM_VIEW_POOL_ENTRY_MODE, myfacesProperties.getViewPoolEntryMode());
        setInitParameter(ViewPool.INIT_PARAM_VIEW_POOL_DEFERRED_NAVIGATION, myfacesProperties.getViewPoolDeferredNavigation());
        setInitParameter(AbstractFacesInitializer.INIT_PARAM_LOG_WEB_CONTEXT_PARAMS, myfacesProperties.getLogWebContextParams());
        setInitParameter(PREFFIX + "FACES_INITIALIZER", myfacesProperties.getFacesInitializer());
        setInitParameter(PREFFIX + "FACES_INIT_PLUGINS", myfacesProperties.getFacesInitPlugins());
        setInitParameter(CdiAnnotationProviderExtension.USE_CDI_FOR_ANNOTATION_SCANNING, myfacesProperties.getAnnotation().getUseCdiForAnnotationScanning());
        setInitParameter(DefaultAnnotationProvider.SCAN_PACKAGES, myfacesProperties.getAnnotation().getScanPackages());
        setInitParameter(PREFFIX + "INITIALIZE_ALWAYS_STANDALONE", myfacesProperties.getInitializeAlwaysStandalone());
    }

    private void configureDeprecated() {
        setInitParameter(PREFFIX + "ERROR_HANDLER", myfacesProperties.getErrorHandler());
        setInitParameter(PREFFIX + "VIEWSTATE_JAVASCRIPT", myfacesProperties.getViewstateJavascript());
        setInitParameter(PREFFIX + "SERIALIZE_STATE_IN_SESSION", myfacesProperties.getSerializeStateInSession());
        setInitParameter(PREFFIX + "CACHE_OLD_VIEWS_IN_SESSION_MODE", myfacesProperties.getCacheOldViewsInSessionMode());
        setInitParameter(HtmlResponseStateManager.INIT_PARAM_HANDLE_STATE_CACHING_MECHANICS, myfacesProperties.getHandleStateCachingMechanics());
        setInitParameter(DefaultFaceletsStateManagementStrategy.SAVE_STATE_WITH_VISIT_TREE_ON_PSS, myfacesProperties.getSaveStateWithVisitTreeOnPass());
    }
    
    @Override
    public void configure()
    {
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
