package com.github.persapiens.jsfboot.undertow;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jsf.undertow")
@Getter @Setter
public class UndertowProperties  {

    /**
     * Classpath to find jsf resources
     * Default to META-INF/resources
     */
    private String classPathResource = "META-INF/resources";
}
