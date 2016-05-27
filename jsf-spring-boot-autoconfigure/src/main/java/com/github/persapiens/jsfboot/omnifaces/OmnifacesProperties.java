package com.github.persapiens.jsfboot.omnifaces;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Taken from http://central.maven.org/maven2/org/omnifaces/omnifaces/1.13/omnifaces-1.13-javadoc.jar
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "jsf.omnifaces")
public class OmnifacesProperties {
    private String cacheProvider;
            
    private String defaultcache;
            
    private String exceptionTypesToUnwrap;
    
    private String facesViewsDispatchMethod;
    
    private String facesViewsEnabled;
                    
    private String facesViewsExtensionAction;
    
    private String facesViewsFilterAfterDeclaredFilters;
    
    private String facesViewsPathAction;
                        
    private String facesViewsScanPaths;
                            
    private String facesViewsScannedViewsAlwaysExtensionless;
                            
    private String facesViewsViewHandlerMode;
                                
    private String html5RenderKitPassthroughAttributes;
    
    private String cdnResourceHandlerDisabled;
    
    private String cdnResourceHandlerUrls;
                                    
    private String combinedResourceHandlerCacheTtl;
                                        
    private String combinedResourceHandlerDisabled;
                                        
    private String combinedResourceHandlerExcludedResources;
                                            
    private String combinedResourceHandlerInlineCss;
    
    private String combinedResourceHandlerInlineJs;
    
    private String combinedResourceHandlerSuppressedResources;
    
    private Cache cache = new Cache();
    
    @Getter @Setter
    public static class Cache {
        private String applicationMaxCapacity;

        private String applicationTtl;;

        private String sessionMaxCapacity;

        private String sessionTtl;
    }
}
