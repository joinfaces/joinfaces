package com.github.persapiens.jsfboot.butterfaces;

import javax.servlet.ServletContext;
import lombok.Builder;
import com.github.persapiens.jsfboot.ServletContextConfigurer;
import de.larmic.butterfaces.resolver.WebXmlParameters;

public class ButterfacesServletContextConfigurer extends ServletContextConfigurer {

    private ButterfacesProperties butterfacesProperties;

    @Builder
    public ButterfacesServletContextConfigurer(ButterfacesProperties butterfacesProperties, ServletContext servletContext) {
        super(servletContext, "");
        this.butterfacesProperties = butterfacesProperties;
    }
    
    @Override
    public void configure()
    {        
        setInitParameter(WebXmlParameters.CTX_PARAM_AJAX_DISABLE_RENDER_REGIONS_ON_REQUEST, butterfacesProperties.getAjaxDisableRenderRegionsOnRequest());
        setInitParameter(WebXmlParameters.CTX_PARAM_AJAX_PROCESSING_GLYPHICON, butterfacesProperties.getAjaxProcessingGlyphiconOnRequest());
        setInitParameter(WebXmlParameters.CTX_PARAM_AJAX_PROCESSING_TEXT, butterfacesProperties.getAjaxProcessingTextOnRequest());
        setInitParameter(WebXmlParameters.CTX_PARAM_AUTO_TRIM_INPUT_FIELDS, butterfacesProperties.getAutoTrimInputFields());
        setInitParameter(WebXmlParameters.CTX_PARAM_BOOTSTRAP, butterfacesProperties.getProvideBootstrap());
        setInitParameter(WebXmlParameters.CTX_PARAM_COLLAPSING_GLYPHICON, butterfacesProperties.getGlyphicon().getCollapsing());
        setInitParameter(WebXmlParameters.CTX_PARAM_EXPANSION_GLYPHICON, butterfacesProperties.getGlyphicon().getExpansion());
        setInitParameter(WebXmlParameters.CTX_PARAM_INTEGRATION_PRIMEFACES_DISABLEJQUERY, butterfacesProperties.getIntegration().getPrimefaces().getDisableJQuery());
        setInitParameter(WebXmlParameters.CTX_PARAM_JQUERY, butterfacesProperties.getProvideJQuery());
        setInitParameter(WebXmlParameters.CTX_PARAM_MAX_LENGTH_TEXT, butterfacesProperties.getMaxLengthText());
        setInitParameter(WebXmlParameters.CTX_PARAM_NO_ENTRIES_TEXT, butterfacesProperties.getNoEntriesText());
        setInitParameter(WebXmlParameters.CTX_PARAM_OPTIONS_GLYPHICON, butterfacesProperties.getGlyphicon().getOptions());
        setInitParameter(WebXmlParameters.CTX_PARAM_ORDER_LEFT_GLYPHICON, butterfacesProperties.getGlyphicon().getOrder().getLeft());
        setInitParameter(WebXmlParameters.CTX_PARAM_ORDER_RIGHT_GLYPHICON, butterfacesProperties.getGlyphicon().getOrder().getRight());
        setInitParameter(WebXmlParameters.CTX_PARAM_REFRESH_GLYPHICON, butterfacesProperties.getGlyphicon().getRefresh());
        setInitParameter(WebXmlParameters.CTX_PARAM_SORT_ASC_GLYPHICON, butterfacesProperties.getGlyphicon().getSort().getAscending());
        setInitParameter(WebXmlParameters.CTX_PARAM_SORT_DESC_GLYPHICON, butterfacesProperties.getGlyphicon().getSort().getDescending());
        setInitParameter(WebXmlParameters.CTX_PARAM_SORT_GLYPHICON, butterfacesProperties.getGlyphicon().getSort().getNone());
        setInitParameter(WebXmlParameters.CTX_PARAM_SPINNER_TEXT, butterfacesProperties.getSpinnerText());
        setInitParameter(WebXmlParameters.CTX_PARAM_USE_COMPRESSED_RESOURCES, butterfacesProperties.getUseCompressedResources());
        
        // this bootsfaces parameters should be set to work with butterfaces
        // https://github.com/ButterFaces/bootsfaces-integration
        setInitParameter("net.bootsfaces.get_jquery_from_cdn", "true");
    }
}
