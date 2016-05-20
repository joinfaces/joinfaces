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

    @Builder
    public MyfacesServletContextConfigurer(MyfacesProperties myfacesProperties, ServletContext servletContext) {
        super(servletContext, "");
        this.myfacesProperties = myfacesProperties;
    }
    
    @Override
    public void configure()
    {
        String prefixo = "org.apache.myfaces.";
        
        setInitParameter(AbstractFacesInitializer.INIT_PARAM_LOG_WEB_CONTEXT_PARAMS, myfacesProperties.getLogWebContextParams());
        setInitParameter(FaceletViewDeclarationLanguage.REFRESH_TRANSIENT_BUILD_ON_PSS, myfacesProperties.getRefreshTransientBuildOnPss());
        setInitParameter(ResourceHandlerImpl.INIT_PARAM_STRICT_JSF_2_ALLOW_SLASH_LIBRARY_NAME, myfacesProperties.getStrictJsf2AllowSlashLibraryName());
        setInitParameter(ResourceHandlerImpl.INIT_PARAM_RESOURCE_BUFFER_SIZE, myfacesProperties.getResourceBufferSize());
        setInitParameter(StateCache.RANDOM_KEY_IN_CSRF_SESSION_TOKEN_PARAM, myfacesProperties.getRandomKeyInCsrfSessionToken());
        setInitParameter(StateCache.RANDOM_KEY_IN_CSRF_SESSION_TOKEN_LENGTH_PARAM, myfacesProperties.getRandomKeyInCsrfSessionTokenLength());
        setInitParameter(StateCache.RANDOM_KEY_IN_CSRF_SESSION_TOKEN_SECURE_RANDOM_CLASS_PARAM, myfacesProperties.getRandomKeyInCsrfSessionTokenSecureRandomClass());
        setInitParameter(StateCache.RANDOM_KEY_IN_CSRF_SESSION_TOKEN_SECURE_RANDOM_PROVIDER_PARAM, myfacesProperties.getRandomKeyInCsrfSessionTokenSecureRandomProvider());
        setInitParameter(StateCache.RANDOM_KEY_IN_CSRF_SESSION_TOKEN_SECURE_RANDOM_ALGORITM_PARAM, myfacesProperties.getRandomKeyInCsrfSessionTokenSecureRandomAlgoritm());
        setInitParameter(FacesConfigValidator.VALIDATE_CONTEXT_PARAM, myfacesProperties.getValidate());
        setInitParameter(CdiAnnotationProviderExtension.USE_CDI_FOR_ANNOTATION_SCANNING, myfacesProperties.getAnnotation().getUseCdiForAnnotationScanning());
        setInitParameter(DefaultAnnotationProvider.SCAN_PACKAGES, myfacesProperties.getAnnotation().getScanPackages());
        setInitParameter(ResolverBuilderBase.EL_RESOLVER_COMPARATOR, myfacesProperties.getElResolverComparator());
        setInitParameter(ResolverBuilderBase.EL_RESOLVER_PREDICATE, myfacesProperties.getElResolverPredicate());        
        setInitParameter(ClientWindowFactoryImpl.INIT_PARAM_DEFAULT_WINDOW_MODE, myfacesProperties.getDefaultWindowMode());
        setInitParameter(ErrorPageWriter.ERROR_HANDLING_PARAMETER, myfacesProperties.getErrorHandling());
        setInitParameter(HtmlResponseStateManager.INIT_PARAM_HANDLE_STATE_CACHING_MECHANICS, myfacesProperties.getHandleStateCachingMechanics());
        setInitParameter(HtmlResponseStateManager.INIT_PARAM_AUTOCOMPLETE_OFF_VIEW_STATE, myfacesProperties.getAutocompleteOffViewState());
        setInitParameter(InternalClassLoaderResourceLoader.USE_MULTIPLE_JS_FILES_FOR_JSF_UNCOMPRESSED_JS, myfacesProperties.getUseMultipleJsFilesForJsfUncompressedJs());
        setInitParameter(InternalClassLoaderResourceLoader.MYFACES_JSF_MODE, myfacesProperties.getJsfJsMode());
        setInitParameter(DefaultFaceletsStateManagementStrategy.SAVE_STATE_WITH_VISIT_TREE_ON_PSS, myfacesProperties.getSaveStateWithVisitTreeOnPass());
        setInitParameter(DefaultFaceletsStateManagementStrategy.CHECK_ID_PRODUCTION_MODE, myfacesProperties.getCheckIdProductionMode());
        setInitParameter(ContextAwareUtils.INIT_PARAM_WRAP_TAG_EXCEPTIONS_AS_CONTEXT_AWARE, myfacesProperties.getWrapTagExceptionsAsContextAware());
        setInitParameter(FaceletCompositionContextImpl.INIT_PARAM_CACHE_EL_EXPRESSIONS, myfacesProperties.getCacheElExpressions());        
        setInitParameter(ViewPool.INIT_PARAM_VIEW_POOL_MAX_POOL_SIZE, myfacesProperties.getViewPoolMaxPoolSize());
        setInitParameter(ViewPool.INIT_PARAM_VIEW_POOL_MAX_DYNAMIC_PARTIAL_LIMIT, myfacesProperties.getViewPoolMaxDynamicPartialLimit());
        setInitParameter(ViewPool.INIT_PARAM_VIEW_POOL_ENTRY_MODE, myfacesProperties.getViewPoolEntryMode());
        setInitParameter(ViewPool.INIT_PARAM_VIEW_POOL_DEFERRED_NAVIGATION, myfacesProperties.getViewPoolDeferredNavigation());
        setInitParameter(AbstractFacesInitializer.INIT_PARAM_LOG_WEB_CONTEXT_PARAMS, myfacesProperties.getLogWebContextParams());
        setInitParameter(prefixo + "ENUM_CONVERTER_ALLOW_STRING_PASSTROUGH", myfacesProperties.getEnumConverterAllowStringPasstrough());
        setInitParameter(prefixo + "ERROR_HANDLER", myfacesProperties.getErrorHandler());
        setInitParameter(prefixo + "CHECKED_VIEWID_CACHE_SIZE", myfacesProperties.getCheckedViewidCacheSize());
        setInitParameter(prefixo + "CHECKED_VIEWID_CACHE_ENABLED", myfacesProperties.getCheckedViewidCacheEnabled());
        setInitParameter(prefixo + "PRETTY_HTML", myfacesProperties.getPrettyHtml());
        setInitParameter(prefixo + "ALLOW_JAVASCRIPT", myfacesProperties.getAllowJavascript());
        setInitParameter(prefixo + "CONFIG_REFRESH_PERIOD", myfacesProperties.getConfigRefreshPeriod());
        setInitParameter(prefixo + "VIEWSTATE_JAVASCRIPT", myfacesProperties.getViewstateJavascript());
        setInitParameter(prefixo + "RENDER_VIEWSTATE_ID", myfacesProperties.getRenderViewstateId());
        setInitParameter(prefixo + "STRICT_XHTML_LINKS", myfacesProperties.getStrictXhtmlLinks());
        setInitParameter(prefixo + "RENDER_CLEAR_JAVASCRIPT_FOR_BUTTON", myfacesProperties.getRenderClearJavascriptForButton());
        setInitParameter(prefixo + "RENDER_HIDDEN_FIELDS_FOR_LINK_PARAMS", myfacesProperties.getRenderHiddenFieldsForLinkParams());
        setInitParameter(prefixo + "SAVE_FORM_SUBMIT_LINK_IE", myfacesProperties.getSaveFormSubmitLinkIe());
        setInitParameter(prefixo + "DELEGATE_FACES_SERVLET", myfacesProperties.getDelegateFacesServlet());
        setInitParameter(prefixo + "REFRESH_TRANSIENT_BUILD_ON_PSS_PRESERVE_STATE", myfacesProperties.getRefreshTransientBuildOnPssPreserveState());
        setInitParameter(prefixo + "VALIDATE_XML", myfacesProperties.getValidateXml());
        setInitParameter(prefixo + "WRAP_SCRIPT_CONTENT_WITH_XML_COMMENT_TAG", myfacesProperties.getWrapScriptContentWithXmlCommentTag());
        setInitParameter(prefixo + "RENDER_FORM_SUBMIT_SCRIPT_INLINE", myfacesProperties.getRenderFormSubmitScriptInline());
        setInitParameter(prefixo + "DEBUG_PHASE_LISTENER", myfacesProperties.getDebugPhaseListener());
        setInitParameter(prefixo + "STRICT_JSF_2_REFRESH_TARGET_AJAX", myfacesProperties.getStrictJsf2RefreshTargetAjax());
        setInitParameter(prefixo + "STRICT_JSF_2_CC_EL_RESOLVER", myfacesProperties.getStrictJsf2CcElResolver());
        setInitParameter(prefixo + "DEFAULT_RESPONSE_WRITER_CONTENT_TYPE_MODE", myfacesProperties.getDefaultResponseWriterContentTypeMode());
        setInitParameter(prefixo + "VIEW_UNIQUE_IDS_CACHE_ENABLED", myfacesProperties.getViewUniqueIdsCacheEnabled());
        setInitParameter(prefixo + "COMPONENT_UNIQUE_IDS_CACHE_SIZE", myfacesProperties.getComponentUniqueIdsCacheSize());
        setInitParameter(prefixo + "SUPPORT_JSP_AND_FACES_EL", myfacesProperties.getSupportJspAndFacesEl());
        setInitParameter(prefixo + "GAE_JSF_JAR_FILES", myfacesProperties.getGaeJsfJarFiles());
        setInitParameter(prefixo + "GAE_JSF_ANNOTATIONS_JAR_FILES", myfacesProperties.getGaeJsfAnnotationsJarFiles());
        setInitParameter(prefixo + "STRICT_JSF_2_VIEW_NOT_FOUND", myfacesProperties.getStrictJsf2ViewNotFound());
        setInitParameter(prefixo + "EARLY_FLUSH_ENABLED", myfacesProperties.getEarlyFlushEnabled());
        setInitParameter(prefixo + "CDI_MANAGED_CONVERTERS_ENABLED", myfacesProperties.getCdiManagedConvertersEnabled());
        setInitParameter(prefixo + "CDI_MANAGED_VALIDATORS_ENABLED", myfacesProperties.getCdiManagedValidatorsEnabled());
        setInitParameter(prefixo + "STRICT_JSF_2_FACELETS_COMPATIBILITY", myfacesProperties.getStrictJsf2FaceletsCompatibility());
        setInitParameter(prefixo + "RENDER_FORM_VIEW_STATE_AT_BEGIN", myfacesProperties.getRenderFormViewStateAtBegin());
        setInitParameter(prefixo + "FLASH_SCOPE_DISABLED", myfacesProperties.getFlashScopeDisabled());
        setInitParameter(prefixo + "NUMBER_OF_VIEWS_IN_SESSION", myfacesProperties.getNumberOfViewsInSession());
        setInitParameter(prefixo + "NUMBER_OF_SEQUENTIAL_VIEWS_IN_SESSION", myfacesProperties.getNumberOfSequentialViewsInSession());
        setInitParameter(prefixo + "NUMBER_OF_FLASH_TOKENS_IN_SESSION", myfacesProperties.getNumberOfFlashTokensInSession());
        setInitParameter(prefixo + "FACES_FLOW_CLIENT_WINDOW_IDS_IN_SESSION", myfacesProperties.getFacesFlowClientWindowIdsInSession());
        setInitParameter(prefixo + "SUPPORT_EL_3_IMPORT_HANDLER", myfacesProperties.getSupportEl3ImportHandler());
        setInitParameter(prefixo + "RESOURCE_MAX_TIME_EXPIRES", myfacesProperties.getResourceMaxTimeExpires());
        setInitParameter(prefixo + "RESOURCE_HANDLER_CACHE_SIZE", myfacesProperties.getResourceHandlerCacheSize());
        setInitParameter(prefixo + "RESOURCE_HANDLER_CACHE_ENABLED", myfacesProperties.getResourceHandlerCacheEnabled());
        setInitParameter(prefixo + "USE_ENCRYPTION", myfacesProperties.getUseEncryption());
        setInitParameter(prefixo + "SECRET", myfacesProperties.getSecret());
        setInitParameter(prefixo + "SECRET.CACHE", myfacesProperties.getSecretCache());
        setInitParameter(prefixo + "ALGORITHM", myfacesProperties.getAlgorithm());
        setInitParameter(prefixo + "ALGORITHM.IV", myfacesProperties.getAlgorithmIv());
        setInitParameter(prefixo + "ALGORITHM.PARAMETERS", myfacesProperties.getAlgorithmParameters());
        setInitParameter(prefixo + "SERIAL_FACTORY", myfacesProperties.getSerialFactory());
        setInitParameter(prefixo + "COMPRESS_STATE_IN_CLIENT", myfacesProperties.getCompressStateInClient());
        setInitParameter(prefixo + "MAC_ALGORITHM", myfacesProperties.getMacAlgorithm());
        setInitParameter(prefixo + "MAC_SECRET", myfacesProperties.getMacSecret());
        setInitParameter(prefixo + "MAC_SECRET.CACHE", myfacesProperties.getMacSecretCache());
        setInitParameter(prefixo + "LAZY_LOAD_CONFIG_OBJECTS", myfacesProperties.getLazyLoadConfigObjects());
        setInitParameter(prefixo + "CLIENT_VIEW_STATE_TIMEOUT", myfacesProperties.getClientViewStateTimeout());
        setInitParameter(prefixo + "SERIALIZE_STATE_IN_SESSION", myfacesProperties.getSerializeStateInSession());
        setInitParameter(prefixo + "COMPRESS_STATE_IN_SESSION", myfacesProperties.getCompressStateInSession());
        setInitParameter(prefixo + "CACHE_OLD_VIEWS_IN_SESSION_MODE", myfacesProperties.getCacheOldViewsInSessionMode());
        setInitParameter(prefixo + "USE_FLASH_SCOPE_PURGE_VIEWS_IN_SESSION", myfacesProperties.getUseFlashScopePurgeViewsInSession());
        setInitParameter(prefixo + "RANDOM_KEY_IN_VIEW_STATE_SESSION_TOKEN", myfacesProperties.getRandomKeyInCsrfSessionToken());
        setInitParameter(prefixo + "RANDOM_KEY_IN_VIEW_STATE_SESSION_TOKEN_LENGTH", myfacesProperties.getRandomKeyInCsrfSessionTokenLength());
        setInitParameter(prefixo + "RANDOM_KEY_IN_VIEW_STATE_SESSION_TOKEN_SECURE_RANDOM_CLASS", myfacesProperties.getRandomKeyInCsrfSessionTokenSecureRandomClass());
        setInitParameter(prefixo + "RANDOM_KEY_IN_VIEW_STATE_SESSION_TOKEN_SECURE_RANDOM_PROVIDER", myfacesProperties.getRandomKeyInViewStateSessionTokenSecureRandomProvider());
        setInitParameter(prefixo + "RANDOM_KEY_IN_VIEW_STATE_SESSION_TOKEN_SECURE_RANDOM_ALGORITM", myfacesProperties.getRandomKeyInViewStateSessionTokenSecureRandomAlgorithm());
        setInitParameter(prefixo + "INITIALIZE_SKIP_JAR_FACES_CONFIG_SCAN", myfacesProperties.getInitializeSkipJarFacesConfigScan());
        setInitParameter(prefixo + "ERROR_TEMPLATE_RESOURCE", myfacesProperties.getErrorTemplateResource());
        setInitParameter(prefixo + "DEBUG_TEMPLATE_RESOURCE", myfacesProperties.getDebugTemplateResource());
        setInitParameter(prefixo + "TEMPORAL_RESOURCEHANDLER_CACHE_ENABLED", myfacesProperties.getTemporalResourcehandlerCacheEnabled());
        setInitParameter(prefixo + "SERVICE_PROVIDER_FINDER", myfacesProperties.getServiceProviderFinder());
        setInitParameter(prefixo + "MARK_INITIAL_STATE_WHEN_APPLY_BUILD_VIEW", myfacesProperties.getMarkInitialStateWhenApplyBuildView());
        setInitParameter(prefixo + "FACES_INITIALIZER", myfacesProperties.getFacesInitializer());
        setInitParameter(prefixo + "FACES_INIT_PLUGINS", myfacesProperties.getFacesInitPlugins());
        setInitParameter(prefixo + "spi.InjectionProvider", myfacesProperties.getSpi().getInjectionProvider());
        setInitParameter(prefixo + "validator.BEAN_BEFORE_JSF_VALIDATION", myfacesProperties.getValidator().getBeanBeforeJsfValidation());
    }
}
