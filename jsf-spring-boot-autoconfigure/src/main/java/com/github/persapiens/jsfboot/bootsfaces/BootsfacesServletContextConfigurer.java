package com.github.persapiens.jsfboot.bootsfaces;

import javax.servlet.ServletContext;
import lombok.Builder;
import com.github.persapiens.jsfboot.ServletContextConfigurer;
import net.bootsfaces.C;

public class BootsfacesServletContextConfigurer extends ServletContextConfigurer {

    private BootsfacesProperties bootsfacesProperties;

    public static final String PREFFIX = "net.bootsfaces.";

    @Builder
    public BootsfacesServletContextConfigurer(BootsfacesProperties bootsfacesProperties, ServletContext servletContext) {
        super(servletContext, "");
        this.bootsfacesProperties = bootsfacesProperties;
    }
    
    @Override
    public void configure()
    {        
        setInitParameter(C.P_THEME, bootsfacesProperties.getTheme());
        setInitParameter(C.P_USETHEME, bootsfacesProperties.getUsetheme());
        setInitParameter(C.P_VIEWPORT, bootsfacesProperties.getUseViewport());
        setInitParameter(PREFFIX + "defaults.renderLabel", bootsfacesProperties.getDefaults().getRenderLabel());
        setInitParameter(PREFFIX + "get_fontawesome_from_cdn", bootsfacesProperties.getGetFontawesomeFromCdn());
        setInitParameter(PREFFIX + "get_jquery_from_cdn", bootsfacesProperties.getGetJqueryFromCdn());
        setInitParameter(PREFFIX + "get_jqueryui_from_cdn", bootsfacesProperties.getGetJqueryuiFromCdn());
        setInitParameter(PREFFIX + "get_bootstrap_from_cdn", bootsfacesProperties.getGetBootstrapFromCdn());
        setInitParameter(PREFFIX + "blockUI", bootsfacesProperties.getBlockUI());
    }
}
