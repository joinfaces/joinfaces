package com.github.persapiens.jsfboot.omnifaces;


import javax.servlet.ServletContext;
import lombok.Builder;
import org.omnifaces.component.output.cache.CacheInstancePerScopeProvider;
import com.github.persapiens.jsfboot.ServletContextConfigurer;

public class OmnifacesCacheServletContextConfigurer extends ServletContextConfigurer {

    private OmnifacesCacheProperties omnifacesCacheProperties;

    @Builder
    public OmnifacesCacheServletContextConfigurer(OmnifacesCacheProperties omnifacesCacheProperties, ServletContext servletContext) {
        super(servletContext, "");
        this.omnifacesCacheProperties = omnifacesCacheProperties;
    }
    
    @Override
    public void configure()
    {        
        setInitParameter(CacheInstancePerScopeProvider.APP_MAX_CAP_PARAM_NAME, omnifacesCacheProperties.getApplicationMaxCapacity());
        setInitParameter(CacheInstancePerScopeProvider.APP_TTL_PARAM_NAME, omnifacesCacheProperties.getApplicationTtl());
        setInitParameter(CacheInstancePerScopeProvider.SESSION_MAX_CAP_PARAM_NAME, omnifacesCacheProperties.getSessionMaxCapacity());
        setInitParameter(CacheInstancePerScopeProvider.SESSION_TTL_PARAM_NAME, omnifacesCacheProperties.getSessionTtl());
        
    }
}
