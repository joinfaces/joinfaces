package com.github.persapiens.jsfboot.mojarra;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringApplicationConfiguration(classes = MojarraSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class MojarraPropertiesIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private MojarraProperties mojarraProperties;
        
	public void testClientStateTimeout() {
		assertThat(mojarraProperties.getClientStateTimeout()).isEqualTo(10);
	}

	public void testClientStateWriteBufferSize() {
		assertThat(mojarraProperties.getClientStateWriteBufferSize()).isEqualTo(11);
	}

	public void testCompressViewState() {
		assertThat(mojarraProperties.getCompressViewState()).isTrue();
	}

	public void testDisableClientStateEncryption() {
		assertThat(mojarraProperties.getDisableClientStateEncryption()).isTrue();
	}

	public void testEnableClientStateDebugging() {
		assertThat(mojarraProperties.getEnableClientStateDebugging()).isTrue();
	}

	public void testGenerateUniqueServerStateIds() {
		assertThat(mojarraProperties.getGenerateUniqueServerStateIds()).isTrue();
	}

	public void testNumberOfLogicalViews() {
		assertThat(mojarraProperties.getNumberOfLogicalViews()).isEqualTo(15);
	}

	public void testNumberOfViewsInSession() {
		assertThat(mojarraProperties.getNumberOfViewsInSession()).isEqualTo(16);
	}

	public void testSerializeServerState() {
		assertThat(mojarraProperties.getSerializeServerState()).isTrue();
	}

	public void testWriteStateAtFormEnd() {
		assertThat(mojarraProperties.getWriteStateAtFormEnd()).isTrue();
	}

	public void testAllowTextChildren() {
		assertThat(mojarraProperties.getAllowTextChildren()).isTrue();
	}

	public void testAutoCompleteOffOnViewState() {
		assertThat(mojarraProperties.getAutoCompleteOffOnViewState()).isTrue();
	}

	public void testCompressJavaScript() {
		assertThat(mojarraProperties.getCompressJavaScript()).isTrue();
	}

	public void testDisableUnicodeEscaping() {
		assertThat(mojarraProperties.getDisableUnicodeEscaping()).isTrue();
	}

	public void testDisableIdUniquenessCheck() {
		assertThat(mojarraProperties.getDisableIdUniquenessCheck()).isTrue();
	}

	public void testEnabledJSStyleHiding() {
		assertThat(mojarraProperties.getEnabledJSStyleHiding()).isTrue();
	}

	public void testEnableScriptsInAttributeValues() {
		assertThat(mojarraProperties.getEnableScriptsInAttributeValues()).isTrue();
	}
        
	public void testEnableViewStateIdRendering() {
		assertThat(mojarraProperties.getEnableViewStateIdRendering()).isTrue();
	}

	public void testPreferXHTML() {
		assertThat(mojarraProperties.getPreferXHTML()).isTrue();
	}

	public void testResponseBufferSize() {
		assertThat(mojarraProperties.getResponseBufferSize()).isEqualTo(20);
	}

	public void testCacheResourceModificationTimestamp() {
		assertThat(mojarraProperties.getCacheResourceModificationTimestamp()).isTrue();
	}

	public void testCompressableMimeTypes() {
		assertThat(mojarraProperties.getCompressableMimeTypes()).isEqualTo("txt");
	}

	public void testDefaultResourceMaxAge() {
		assertThat(mojarraProperties.getDefaultResourceMaxAge()).isEqualTo(30);
	}

	public void testEnableFaceletsResourceResolverCompositeComponents() {
		assertThat(mojarraProperties.getEnableFaceletsResourceResolverCompositeComponents()).isTrue();
	}

	public void testEnableMissingResourceLibraryDetection() {
		assertThat(mojarraProperties.getEnableMissingResourceLibraryDetection()).isTrue();
	}

	public void testResourceUpdateCheckPeriod() {
		assertThat(mojarraProperties.getResourceUpdateCheckPeriod()).isEqualTo(50);
	}

	public void testEnableAgressiveSessionDirtying() {
		assertThat(mojarraProperties.getEnableAgressiveSessionDirtying()).isTrue();
	}

	public void testEnableDistributable() {
		assertThat(mojarraProperties.getEnableDistributable()).isTrue();
	}

	public void testAnnotationScanPackages() {
		assertThat(mojarraProperties.getAnnotationScanPackages()).isEqualTo("mypackage");
	}

	public void testDisplayConfiguration() {
		assertThat(mojarraProperties.getDisplayConfiguration()).isTrue();
	}
        
	public void testEnableCoreTagLibValidator() {
		assertThat(mojarraProperties.getEnableCoreTagLibValidator()).isTrue();
	}

	public void testEnableHtmlTagLibValidator() {
		assertThat(mojarraProperties.getEnableHtmlTagLibValidator()).isTrue();
	}

	public void testEnableLazyBeanValidation() {
		assertThat(mojarraProperties.getEnableLazyBeanValidation()).isTrue();
	}

	public void testEnableThreading() {
		assertThat(mojarraProperties.getEnableThreading()).isTrue();
	}

	public void testForceLoadConfiguration() {
		assertThat(mojarraProperties.getForceLoadConfiguration()).isTrue();
	}
        
	public void testValidateXml() {
		assertThat(mojarraProperties.getValidateXml()).isTrue();
	}

	public void testVerifyObjects() {
		assertThat(mojarraProperties.getVerifyObjects()).isTrue();
	}

	public void testEnableTransitionTimeNoOpFlash() {
		assertThat(mojarraProperties.getEnableTransitionTimeNoOpFlash()).isTrue();
	}

	public void testExpressionFactory() {
		assertThat(mojarraProperties.getExpressionFactory()).isEqualTo("myExpressionFactory");
	}

	public void testForceAlwaysWriteFlashCookie() {
		assertThat(mojarraProperties.getForceAlwaysWriteFlashCookie()).isTrue();
	}
        
	public void testInjectionProvider() {
		assertThat(mojarraProperties.getInjectionProvider()).isEqualTo("myInjectionProvider");
	}

	public void testNamespaceParameters() {
		assertThat(mojarraProperties.getNamespaceParameters()).isTrue();
	}

	public void testRegisterConverterPropertyEditors() {
		assertThat(mojarraProperties.getRegisterConverterPropertyEditors()).isTrue();
	}

	public void testSendPoweredByHeader() {
		assertThat(mojarraProperties.getSendPoweredByHeader()).isTrue();
	}

	public void testSerializationProvider() {
		assertThat(mojarraProperties.getSerializationProvider()).isEqualTo("myProvider");
	}

	public void testFaceletFactory() {
		assertThat(mojarraProperties.getFaceletFactory()).isEqualTo("myFactory");
	}

}
