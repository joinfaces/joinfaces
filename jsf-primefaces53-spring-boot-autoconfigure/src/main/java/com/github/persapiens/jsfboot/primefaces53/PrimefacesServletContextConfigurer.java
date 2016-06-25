package com.github.persapiens.jsfboot.primefaces53;

import javax.servlet.ServletContext;
import lombok.Builder;
import com.github.persapiens.jsfboot.ServletContextConfigurer;
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
        setInitParameter(ContextParams.AUTO_UPDATE, primefacesProperties.getAutoUpdate());
        setInitParameter(ContextParams.BEAN_VALIDATION_DISABLED, primefacesProperties.getBeanValidationDisabled());        
        setInitParameter(ContextParams.CACHE_PROVIDER, primefacesProperties.getCacheProvider());
        setInitParameter(ContextParams.DIRECTION, primefacesProperties.getDir());
        setInitParameter(ContextParams.FONT_AWESOME, primefacesProperties.getFontAwesome());
        setInitParameter(ContextParams.LEGACY_WIDGET_NAMESPACE, primefacesProperties.getLegacyWidgetNamespace());
        setInitParameter(ContextParams.MOBILE_THEME, primefacesProperties.getMobile().getTheme());                
        setInitParameter(ContextParams.PFV_KEY, primefacesProperties.getClientSideValidation());
        setInitParameter(ContextParams.PUSH_SERVER_URL, primefacesProperties.getPushServerUrl());
        setInitParameter(ContextParams.RESET_VALUES, primefacesProperties.getResetValues());
        setInitParameter(ContextParams.SECRET_KEY, primefacesProperties.getSecret());
        setInitParameter(ContextParams.SUBMIT, primefacesProperties.getSubmit());
        setInitParameter(ContextParams.THEME, primefacesProperties.getTheme());
        setInitParameter(ContextParams.TRANSFORM_METADATA, primefacesProperties.getTransformMetadata());
        setInitParameter(ContextParams.UPLOADER, primefacesProperties.getUploader());
        
        setInitParameter("org.primefaces.component.captcha.PRIVATE_KEY", primefacesProperties.getCaptcha().getPrivateKey());
    }
}
