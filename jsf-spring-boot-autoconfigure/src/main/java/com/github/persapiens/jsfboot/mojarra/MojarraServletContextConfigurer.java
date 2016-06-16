package com.github.persapiens.jsfboot.mojarra;

import javax.servlet.ServletContext;
import lombok.Builder;
import com.github.persapiens.jsfboot.ServletContextConfigurer;

public class MojarraServletContextConfigurer extends ServletContextConfigurer {

    private MojarraProperties mojarraProperties;

    public static final String PREFFIX = "com.sun.faces";
    
    @Builder
    public MojarraServletContextConfigurer(MojarraProperties mojarraProperties, ServletContext servletContext) {
        super(servletContext, PREFFIX);
        this.mojarraProperties = mojarraProperties;
    }
    
    @Override
    public void configure()
    {
        setInitParameter("clientStateTimeout", mojarraProperties.getClientStateTimeout());
        setInitParameter("clientStateWriteBufferSize", mojarraProperties.getClientStateWriteBufferSize());
        setInitParameter("compressViewState", mojarraProperties.getCompressViewState());
        setInitParameter("disableClientStateEncryption", mojarraProperties.getDisableClientStateEncryption());
        setInitParameter("enableClientStateDebugging", mojarraProperties.getEnableClientStateDebugging());
        setInitParameter("generateUniqueServerStateIds", mojarraProperties.getGenerateUniqueServerStateIds());
        setInitParameter("numberOfLogicalViews", mojarraProperties.getNumberOfLogicalViews());
        setInitParameter("numberOfViewsInSession", mojarraProperties.getNumberOfViewsInSession());
        setInitParameter("serializeServerState", mojarraProperties.getSerializeServerState());
        setInitParameter("writeStateAtFormEnd", mojarraProperties.getWriteStateAtFormEnd());
        setInitParameter("allowTextChildren", mojarraProperties.getAllowTextChildren());
        setInitParameter("autoCompleteOffOnViewState", mojarraProperties.getAutoCompleteOffOnViewState());
        setInitParameter("compressJavaScript", mojarraProperties.getCompressJavaScript());
        setInitParameter("disableUnicodeEscaping", mojarraProperties.getDisableUnicodeEscaping());
        setInitParameter("disableIdUniquenessCheck", mojarraProperties.getDisableIdUniquenessCheck());
        setInitParameter("enabledJSStyleHiding", mojarraProperties.getEnabledJSStyleHiding());
        setInitParameter("enableScriptsInAttributeValues", mojarraProperties.getEnableScriptsInAttributeValues());
        setInitParameter("enableViewStateIdRendering", mojarraProperties.getEnableViewStateIdRendering());
        setInitParameter("preferXHTML", mojarraProperties.getPreferXHTML());
        setInitParameter("responseBufferSize", mojarraProperties.getResponseBufferSize());
        setInitParameter("cacheResourceModificationTimestamp", mojarraProperties.getCacheResourceModificationTimestamp());
        setInitParameter("compressableMimeTypes", mojarraProperties.getCompressableMimeTypes());
        setInitParameter("defaultResourceMaxAge", mojarraProperties.getDefaultResourceMaxAge());
        setInitParameter("enableFaceletsResourceResolverCompositeComponents", mojarraProperties.getEnableFaceletsResourceResolverCompositeComponents());
        setInitParameter("enableMissingResourceLibraryDetection", mojarraProperties.getEnableMissingResourceLibraryDetection());
        setInitParameter("resourceUpdateCheckPeriod", mojarraProperties.getResourceUpdateCheckPeriod());
        setInitParameter("enableAgressiveSessionDirtying", mojarraProperties.getEnableAgressiveSessionDirtying());
        setInitParameter("enableDistributable", mojarraProperties.getEnableDistributable());
        setInitParameter("annotationScanPackages", mojarraProperties.getAnnotationScanPackages());
        setInitParameter("displayConfiguration", mojarraProperties.getDisplayConfiguration());
        setInitParameter("enableCoreTagLibValidator", mojarraProperties.getEnableCoreTagLibValidator());
        setInitParameter("enableHtmlTagLibValidator", mojarraProperties.getEnableHtmlTagLibValidator());
        setInitParameter("enableLazyBeanValidation", mojarraProperties.getEnableLazyBeanValidation());
        setInitParameter("enableThreading", mojarraProperties.getEnableThreading());
        setInitParameter("forceLoadConfiguration", mojarraProperties.getForceLoadConfiguration());
        setInitParameter("validateXml", mojarraProperties.getValidateXml());
        setInitParameter("verifyObjects", mojarraProperties.getVerifyObjects());
        setInitParameter("enableTransitionTimeNoOpFlash", mojarraProperties.getEnableTransitionTimeNoOpFlash());
        setInitParameter("expressionFactory", mojarraProperties.getExpressionFactory());
        setInitParameter("forceAlwaysWriteFlashCookie", mojarraProperties.getForceAlwaysWriteFlashCookie());
        setInitParameter("injectionProvider", mojarraProperties.getInjectionProvider());
        setInitParameter("namespaceParameters", mojarraProperties.getNamespaceParameters());
        setInitParameter("registerConverterPropertyEditors", mojarraProperties.getRegisterConverterPropertyEditors());
        setInitParameter("sendPoweredByHeader", mojarraProperties.getSendPoweredByHeader());
        setInitParameter("serializationProvider", mojarraProperties.getSerializationProvider());
        setInitParameter("faceletFactory", mojarraProperties.getFaceletFactory());
    }
}
