package com.github.persapiens.jsfboot.primefaces;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Taken from http://www.primefaces.org/docs/api/6.0/constant-values.html
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "jsf.primefaces")
public class PrimefacesProperties {

    private String privateCaptchaKey;

    private String publicCaptchaKey;

    /**
     * Theme of the application.
     */
    private String theme;

    private Mobile mobile = new Mobile();

    @Getter
    @Setter
    public static class Mobile {

        /**
         * Theme of the mobile application.
         */
        private String theme;
    }

    /**
     * Custom server url for PrimeFaces Push.
     */
    private String pushServerUrl;

    /**
     * Defines ajax submit mode, full or partial.
     */
    private String submit;

    /**
     * Defines orientation, ltr or rtl.
     */
    private String dir;

    /**
     * When enabled, ajax updated inputs are reset.
     */
    private Boolean resetValues;

    /**
     * Secret key to encrypt-decrypt value expressions exposed in rendering
     * StreamedContents.
     */
    private String secret;

    /**
     * Controls client side validation.
     */
    private Boolean clientSideValidation;

    /**
     * Defines uploader mode; auto, native or commons.
     */
    private String uploader;

    /**
     * Transforms bean validation metadata to html attributes.
     */
    private Boolean transformMetadata;

    /**
     * Enables window scope so that widgets can be accessed using
     * widgetVar.method() in addition to default PF namespace approach like
     * PF('widgetVar').method().
     */
    private Boolean legacyWidgetNamespace;

    /**
     * Enabled font-awesome icons.
     */
    private Boolean fontAwesome;

    /**
     * A cache store is required to use the cache component, two different
     * providers are supported as cache implementation; EHCache and Hazelcast.
     */
    private String cacheProvider;

    private String autoUpdate;

    private Captcha captcha = new Captcha();

    @Getter @Setter
    public static class Captcha {

        private String privateKey;

    }
    
    private Boolean earlyPostParamEvaluation;
    
    private Boolean beanValidationDisabled;
    
    private Boolean interpolateClientSideValidationMessages;
}
