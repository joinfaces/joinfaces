package com.github.persapiens.jsfboot.primefaces;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "org.primefaces.component.captcha")
public class PrimefacesCaptchaProperties {
    
    private String privateKey;
    
}
