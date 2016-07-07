package com.github.persapiens.jsfboot.butterfaces;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Taken from https://butterfaces.gitbooks.io/butterfaces/content/configuration.html
 * and de.larmic.butterfaces.resolver.WebXmlParameters.java
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "jsf.butterfaces")
public class ButterfacesProperties {
    
    /**
     * b:commandLink components comes with ajaxDisableRenderRegionsOnRequest to 
     * crossfade render regions while ajax request is running. To configure this
     * for global usage add following parameter
     */
    private Boolean ajaxDisableRenderRegionsOnRequest;

    private String ajaxProcessingGlyphiconOnRequest;
    
    private String ajaxProcessingTextOnRequest;
    
    /**
     * By default all input fields will be trimed after submit.
     */
    private Boolean autoTrimInputFields;
    
    /**
     * As default ButterFaces comes with an actual version of Bootstrap 3.x. 
     * To disable ButterFaces Boostrap version because of using your own version
     * you have to change following parameter
     */
    private Boolean provideBootstrap;

    private Glyphicon glyphicon = new Glyphicon();
    
    @Getter @Setter
    public static class Glyphicon {    
        private String collapsing;
        
        private String expansion;
        
        private String options;

        private String refresh;
                        
        private Order order = new Order();
        
        @Getter
        @Setter
        public static class Order {
            private String left;
            private String right;
        }
                        
        private Sort sort = new Sort();
        
        @Getter
        @Setter
        public static class Sort {
            private String ascending;
            private String descending;
            private String none;
        }
    }
        
    /**
     * As default ButterFaces comes with an actual version of jQuery 2.x. 
     * To disable ButterFaces jQuery version because of using your own version 
     * you have to change following parameter
     */
    private Boolean provideJQuery;
    
    /**
     * When using maxlength parameter counting text will be {0} of {1} characters.
     */
    private String maxLengthText;
    
    /**
     * b:tree and b:treeBox are trivial components and can be configured by 
     * components attribute or by changeing following parameters
     */
    private String noEntriesText;
    
    /**
     * b:tree and b:treeBox are trivial components and can be configured by 
     * components attribute or by changeing following parameters
     */
    private String spinnerText;
    
    /**
     * Each components comes up with it's own javascript and css resources. 
     * If you want to use ButterFaces resources (i.e. Bootstrap or jQuery) without
     * any ButterFaces component use b:activateLibraries to load all resources to html.
     * This tags add javascript and css resources for all existing components. 
     * In addition to that you may activate compression by web.xml
     * This only works if you are using PROJECT_STAGE = Production
     */
    private Boolean useCompressedResources;
    
    private Integration integration = new Integration();
        
    @Getter
    public static class Integration {
        private Primefaces primefaces = new Primefaces();
        
        @Getter
        @Setter
        public static class Primefaces {
            /**
             * As well as ButterFaces PrimeFaces comes with jQuery. By default 
             * PrimeFaces JQuery will be removed from resources. You can change 
             * this behaviour by changeing following parameter             
             */
            private Boolean disableJQuery;
        }
    }
}
