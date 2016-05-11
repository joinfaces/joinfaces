package com.github.persapiens.jsfboot.primefaces;

import javax.servlet.ServletContext;
import lombok.Builder;
import com.github.persapiens.jsfboot.ServletContextConfigurer;

public class PrimefacesCaptchaServletContextConfigurer extends ServletContextConfigurer {

    private PrimefacesCaptchaProperties captchaPrimefacesProperties;

    @Builder
    public PrimefacesCaptchaServletContextConfigurer(PrimefacesCaptchaProperties captchaPrimefacesProperties, ServletContext servletContext) {
        super(servletContext, "org.primefaces.component.captcha");
        this.captchaPrimefacesProperties = captchaPrimefacesProperties;
    }
    
    @Override
    public void configure()
    {        
        setInitParameter("PRIVATE_KEY", captchaPrimefacesProperties.getPrivateKey());
    }
}
