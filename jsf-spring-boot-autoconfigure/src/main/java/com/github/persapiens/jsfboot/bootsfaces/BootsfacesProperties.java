package com.github.persapiens.jsfboot.bootsfaces;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Taken from http://search.maven.org/remotecontent?filepath=net/bootsfaces/bootsfaces/0.8.6/bootsfaces-0.8.6-javadoc.jar
 * and from https://github.com/TheCoder4eu/BootsFaces-OSP
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "jsf.bootsfaces")
public class BootsfacesProperties {

    private Defaults defaults = new Defaults();
    
    @Getter @Setter
    public static class Defaults {
        private String renderLabel;
    }
    
    /**
     * deactivate FontAwesome support if the no-fa facet is found in the h:head tag
     */
    private String getFontawesomeFromCdn;
    
    private String blockUI;
    
    /**
     * BootsFaces_THEME - controls the Theme to use: the value "default" is plain 
     * Bootstrap, the other options are a Bootswach Theme name (lowercase) or 
     * "custom". If custom is chosen, you will have to provide your custom CSS 
     * in the "other" folder.
     */
    private String theme;
    
    /**
     * BootsFaces_USETHEME - as in previous versions controls if the current theme 
     * is to be rendered in the Flat variant (default) or in its Enhanced variant,
     * with shadows and decorations turned on.
     */
    private String usetheme;
    
    private String useViewport;
    
    private String getJqueryFromCdn;
    
    private String getJqueryuiFromCdn;
    
    private String getBootstrapFromCdn;
}
