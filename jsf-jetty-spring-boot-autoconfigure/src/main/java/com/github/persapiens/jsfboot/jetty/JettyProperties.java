package com.github.persapiens.jsfboot.jetty;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jsf.jetty")
@Getter @Setter
public class JettyProperties  {

    /**
     * Classpath to find jsf resources
     * Default to META-INF/resources
     */
    private String classPathResource = "META-INF/resources";
}
