package com.github.persapiens.jsfboot.omnifaces;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Taken from http://central.maven.org/maven2/org/omnifaces/omnifaces/1.13/omnifaces-1.13-javadoc.jar
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "jsf.omnifaces.cache")
public class OmnifacesCacheProperties {
    private String applicationMaxCapacity;
            
    private String applicationTtl;;
            
    private String sessionMaxCapacity;
    
    private String sessionTtl;
}
