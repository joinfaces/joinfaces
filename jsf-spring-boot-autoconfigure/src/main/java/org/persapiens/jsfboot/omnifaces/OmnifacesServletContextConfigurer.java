package org.persapiens.jsfboot.omnifaces;


import javax.servlet.ServletContext;
import lombok.Builder;
import org.omnifaces.component.output.cache.CacheInitializerListener;
import org.omnifaces.component.output.cache.CacheInstancePerScopeProvider;
import org.omnifaces.exceptionhandler.FullAjaxExceptionHandler;
import org.omnifaces.facesviews.FacesViews;
import org.omnifaces.renderkit.Html5RenderKit;
import org.omnifaces.resourcehandler.CDNResourceHandler;
import org.omnifaces.resourcehandler.CombinedResourceHandler;
import org.persapiens.jsfboot.ServletContextConfigurer;

public class OmnifacesServletContextConfigurer extends ServletContextConfigurer {

    private OmnifacesProperties omnifacesProperties;

    @Builder
    public OmnifacesServletContextConfigurer(OmnifacesProperties omnifacesProperties, ServletContext servletContext) {
        super(servletContext, "");
        this.omnifacesProperties = omnifacesProperties;
    }
    
    @Override
    public void configure()
    {        
        setInitParameter(CacheInitializerListener.CACHE_PROVIDER_INIT_PARAM_NAME, omnifacesProperties.getCacheProvider());
        setInitParameter(CacheInstancePerScopeProvider.DEFAULT_CACHE_PARAM_NAME, omnifacesProperties.getDefaultcache());
        setInitParameter(FullAjaxExceptionHandler.PARAM_NAME_EXCEPTION_TYPES_TO_UNWRAP, omnifacesProperties.getExceptionTypesToUnwrap());
        setInitParameter(FacesViews.FACES_VIEWS_DISPATCH_METHOD_PARAM_NAME, omnifacesProperties.getFacesViewsDispatchMethod());
        setInitParameter(FacesViews.FACES_VIEWS_ENABLED_PARAM_NAME, omnifacesProperties.getFacesViewsEnabled());
        setInitParameter(FacesViews.FACES_VIEWS_EXTENSION_ACTION_PARAM_NAME, omnifacesProperties.getFacesViewsExtensionAction());
        setInitParameter(FacesViews.FACES_VIEWS_FILTER_AFTER_DECLARED_FILTERS_PARAM_NAME, omnifacesProperties.getFacesViewsFilterAfterDeclaredFilters());
        setInitParameter(FacesViews.FACES_VIEWS_PATH_ACTION_PARAM_NAME, omnifacesProperties.getFacesViewsPathAction());
        setInitParameter(FacesViews.FACES_VIEWS_SCAN_PATHS_PARAM_NAME, omnifacesProperties.getFacesViewsScanPaths());
        setInitParameter(FacesViews.FACES_VIEWS_SCANNED_VIEWS_EXTENSIONLESS_PARAM_NAME, omnifacesProperties.getFacesViewsScannedViewsAlwaysExtensionless());
        setInitParameter(FacesViews.FACES_VIEWS_VIEW_HANDLER_MODE_PARAM_NAME, omnifacesProperties.getFacesViewsViewHandlerMode());
        setInitParameter(Html5RenderKit.PARAM_NAME_PASSTHROUGH_ATTRIBUTES, omnifacesProperties.getHtml5RenderKitPassthroughAttributes());
        setInitParameter(CDNResourceHandler.PARAM_NAME_CDN_DISABLED, omnifacesProperties.getCdnResourceHandlerDisabled());
        setInitParameter(CDNResourceHandler.PARAM_NAME_CDN_RESOURCES, omnifacesProperties.getCdnResourceHandlerUrls());
        setInitParameter(CombinedResourceHandler.PARAM_NAME_CACHE_TTL, omnifacesProperties.getCombinedResourceHandlerCacheTtl());
        setInitParameter(CombinedResourceHandler.PARAM_NAME_DISABLED, omnifacesProperties.getCombinedResourceHandlerDisabled());
        setInitParameter(CombinedResourceHandler.PARAM_NAME_EXCLUDED_RESOURCES, omnifacesProperties.getCombinedResourceHandlerExcludedResources());
        setInitParameter(CombinedResourceHandler.PARAM_NAME_INLINE_CSS, omnifacesProperties.getCombinedResourceHandlerInlineCss());
        setInitParameter(CombinedResourceHandler.PARAM_NAME_INLINE_JS, omnifacesProperties.getCombinedResourceHandlerInlineJs());
        setInitParameter(CombinedResourceHandler.PARAM_NAME_SUPPRESSED_RESOURCES, omnifacesProperties.getCombinedResourceHandlerSuppressedResources());
        
    }
}
