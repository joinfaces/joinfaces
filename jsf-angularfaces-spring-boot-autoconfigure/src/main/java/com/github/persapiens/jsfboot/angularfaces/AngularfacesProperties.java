package com.github.persapiens.jsfboot.angularfaces;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Taken from https://github.com/stephanrauh/AngularFaces
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "jsf.angularfaces")
public class AngularfacesProperties {

    private Boolean addLabels;
    
    private Boolean addMessages;
    
    private Boolean translation;
    
    private Boolean includeAngularJS;
    
    private Boolean includeJQuery;
    
    private Boolean includeJQueryUI;
    
    private Boolean includeAngularMessages;
    
    private Boolean clientSideMessages;
    
    private Boolean includeMainJS;
}
