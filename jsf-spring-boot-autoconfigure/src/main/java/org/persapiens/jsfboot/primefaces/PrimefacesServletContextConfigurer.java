package org.persapiens.jsfboot.primefaces;

import javax.servlet.ServletContext;
import lombok.Builder;
import org.persapiens.jsfboot.ServletContextConfigurer;
import org.primefaces.component.captcha.Captcha;
import org.primefaces.util.Constants.ContextParams;

public class PrimefacesServletContextConfigurer extends ServletContextConfigurer {

    private PrimefacesProperties primefacesProperties;

    @Builder
    public PrimefacesServletContextConfigurer(PrimefacesProperties primefacesProperties, ServletContext servletContext) {
        super(servletContext, "");
        this.primefacesProperties = primefacesProperties;
    }
    
    @Override
    public void configure()
    {        
        setInitParameter(Captcha.PRIVATE_KEY, primefacesProperties.getPrivateCaptchaKey());
        setInitParameter(Captcha.PUBLIC_KEY, primefacesProperties.getPublicCaptchaKey());
        setInitParameter(ContextParams.THEME, primefacesProperties.getTheme());
        setInitParameter(ContextParams.MOBILE_THEME, primefacesProperties.getMobile().getTheme());                
        setInitParameter(ContextParams.PUSH_SERVER_URL, primefacesProperties.getPushServerUrl());
        setInitParameter(ContextParams.SUBMIT, primefacesProperties.getSubmit());
        setInitParameter(ContextParams.DIRECTION, primefacesProperties.getDir());
        setInitParameter(ContextParams.RESET_VALUES, primefacesProperties.getResetValues());
        setInitParameter(ContextParams.SECRET_KEY, primefacesProperties.getSecret());
        setInitParameter(ContextParams.PFV_KEY, primefacesProperties.getClientSideValidation());
        setInitParameter(ContextParams.UPLOADER, primefacesProperties.getUploader());
        setInitParameter(ContextParams.TRANSFORM_METADATA, primefacesProperties.getTransformMetadata());
        setInitParameter(ContextParams.LEGACY_WIDGET_NAMESPACE, primefacesProperties.getLegacyWidgetNamespace());
        setInitParameter(ContextParams.FONT_AWESOME, primefacesProperties.getFontAwesome());
        setInitParameter(ContextParams.CACHE_PROVIDER, primefacesProperties.getCacheProvider());
        setInitParameter(ContextParams.AUTO_UPDATE, primefacesProperties.getAutoUpdate());
        
    }
}
