package com.github.persapiens.jsfboot.mojarra;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class MojarraPropertiesTest {
        
	public void testClientStateTimeout() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setClientStateTimeout(10);

		assertThat(mojarraProperties.getClientStateTimeout()).isEqualTo(10);
	}

	public void testClientStateWriteBufferSize() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setClientStateWriteBufferSize(11);

		assertThat(mojarraProperties.getClientStateWriteBufferSize()).isEqualTo(11);
	}

	public void testCompressViewState() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setCompressViewState(true);

		assertThat(mojarraProperties.getCompressViewState()).isTrue();
	}

	public void testDisableClientStateEncryption() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setDisableClientStateEncryption(true);

		assertThat(mojarraProperties.getDisableClientStateEncryption()).isTrue();
	}

	public void testEnableClientStateDebugging() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setEnableClientStateDebugging(true);

		assertThat(mojarraProperties.getEnableClientStateDebugging()).isTrue();
	}

	public void testGenerateUniqueServerStateIds() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setGenerateUniqueServerStateIds(true);

		assertThat(mojarraProperties.getGenerateUniqueServerStateIds()).isTrue();
	}

	public void testNumberOfLogicalViews() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setNumberOfLogicalViews(15);

		assertThat(mojarraProperties.getNumberOfLogicalViews()).isEqualTo(15);
	}

	public void testNumberOfViewsInSession() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setNumberOfViewsInSession(16);

		assertThat(mojarraProperties.getNumberOfViewsInSession()).isEqualTo(16);
	}

	public void testSerializeServerState() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setSerializeServerState(true);

		assertThat(mojarraProperties.getSerializeServerState()).isTrue();
	}

	public void testWriteStateAtFormEnd() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setWriteStateAtFormEnd(true);

		assertThat(mojarraProperties.getWriteStateAtFormEnd()).isTrue();
	}

	public void testAllowTextChildren() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setAllowTextChildren(true);

		assertThat(mojarraProperties.getAllowTextChildren()).isTrue();
	}

	public void testAutoCompleteOffOnViewState() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setAutoCompleteOffOnViewState(true);

		assertThat(mojarraProperties.getAutoCompleteOffOnViewState()).isTrue();
	}

	public void testCompressJavaScript() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setCompressJavaScript(true);

		assertThat(mojarraProperties.getCompressJavaScript()).isTrue();
	}

	public void testDisableUnicodeEscaping() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setDisableUnicodeEscaping(true);

		assertThat(mojarraProperties.getDisableUnicodeEscaping()).isTrue();
	}

	public void testDisableIdUniquenessCheck() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setDisableIdUniquenessCheck(true);

		assertThat(mojarraProperties.getDisableIdUniquenessCheck()).isTrue();
	}

	public void testEnabledJSStyleHiding() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setEnabledJSStyleHiding(true);

		assertThat(mojarraProperties.getEnabledJSStyleHiding()).isTrue();
	}

	public void testEnableScriptsInAttributeValues() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setEnableScriptsInAttributeValues(true);

		assertThat(mojarraProperties.getEnableScriptsInAttributeValues()).isTrue();
	}
        
	public void testEnableViewStateIdRendering() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setEnableViewStateIdRendering(true);

		assertThat(mojarraProperties.getEnableViewStateIdRendering()).isTrue();
	}

	public void testPreferXHTML() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setPreferXHTML(true);

		assertThat(mojarraProperties.getPreferXHTML()).isTrue();
	}

	public void testResponseBufferSize() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setResponseBufferSize(20);

		assertThat(mojarraProperties.getResponseBufferSize()).isEqualTo(20);
	}

	public void testCacheResourceModificationTimestamp() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setCacheResourceModificationTimestamp(true);

		assertThat(mojarraProperties.getCacheResourceModificationTimestamp()).isTrue();
	}

	public void testCompressableMimeTypes() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setCompressableMimeTypes("txt");

		assertThat(mojarraProperties.getCompressableMimeTypes()).isEqualTo("txt");
	}

	public void testDefaultResourceMaxAge() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setDefaultResourceMaxAge(30);

		assertThat(mojarraProperties.getDefaultResourceMaxAge()).isEqualTo(30);
	}

	public void testEnableFaceletsResourceResolverCompositeComponents() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setEnableFaceletsResourceResolverCompositeComponents(true);

		assertThat(mojarraProperties.getEnableFaceletsResourceResolverCompositeComponents()).isTrue();
	}

	public void testEnableMissingResourceLibraryDetection() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setEnableMissingResourceLibraryDetection(true);

		assertThat(mojarraProperties.getEnableMissingResourceLibraryDetection()).isTrue();
	}

	public void testResourceUpdateCheckPeriod() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setResourceUpdateCheckPeriod(50);

		assertThat(mojarraProperties.getResourceUpdateCheckPeriod()).isEqualTo(50);
	}

	public void testEnableAgressiveSessionDirtying() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setEnableAgressiveSessionDirtying(true);

		assertThat(mojarraProperties.getEnableAgressiveSessionDirtying()).isTrue();
	}

	public void testEnableDistributable() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setEnableDistributable(true);

		assertThat(mojarraProperties.getEnableDistributable()).isTrue();
	}

	public void testAnnotationScanPackages() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setAnnotationScanPackages("mypackage");

		assertThat(mojarraProperties.getAnnotationScanPackages()).isEqualTo("mypackage");
	}

	public void testDisplayConfiguration() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setDisplayConfiguration(true);

		assertThat(mojarraProperties.getDisplayConfiguration()).isTrue();
	}
        
	public void testEnableCoreTagLibValidator() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setEnableCoreTagLibValidator(true);

		assertThat(mojarraProperties.getEnableCoreTagLibValidator()).isTrue();
	}

	public void testEnableHtmlTagLibValidator() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setEnableHtmlTagLibValidator(true);

		assertThat(mojarraProperties.getEnableHtmlTagLibValidator()).isTrue();
	}

	public void testEnableLazyBeanValidation() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setEnableLazyBeanValidation(true);

		assertThat(mojarraProperties.getEnableLazyBeanValidation()).isTrue();
	}

	public void testEnableThreading() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setEnableThreading(true);

		assertThat(mojarraProperties.getEnableThreading()).isTrue();
	}

	public void testForceLoadConfiguration() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setForceLoadConfiguration(true);

		assertThat(mojarraProperties.getForceLoadConfiguration()).isTrue();
	}
        
	public void testValidateXml() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setValidateXml(true);

		assertThat(mojarraProperties.getValidateXml()).isTrue();
	}

	public void testVerifyObjects() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setVerifyObjects(true);

		assertThat(mojarraProperties.getVerifyObjects()).isTrue();
	}

	public void testEnableTransitionTimeNoOpFlash() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setEnableTransitionTimeNoOpFlash(true);

		assertThat(mojarraProperties.getEnableTransitionTimeNoOpFlash()).isTrue();
	}

	public void testExpressionFactory() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setExpressionFactory("myExpressionFactory");

		assertThat(mojarraProperties.getExpressionFactory()).isEqualTo("myExpressionFactory");
	}

	public void testForceAlwaysWriteFlashCookie() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setForceAlwaysWriteFlashCookie(true);

		assertThat(mojarraProperties.getForceAlwaysWriteFlashCookie()).isTrue();
	}
        
	public void testInjectionProvider() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setInjectionProvider("myInjectionProvider");

		assertThat(mojarraProperties.getInjectionProvider()).isEqualTo("myInjectionProvider");
	}

	public void testNamespaceParameters() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setNamespaceParameters(true);

		assertThat(mojarraProperties.getNamespaceParameters()).isTrue();
	}

	public void testRegisterConverterPropertyEditors() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setRegisterConverterPropertyEditors(true);

		assertThat(mojarraProperties.getRegisterConverterPropertyEditors()).isTrue();
	}

	public void testSendPoweredByHeader() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setSendPoweredByHeader(true);

		assertThat(mojarraProperties.getSendPoweredByHeader()).isTrue();
	}

	public void testSerializationProvider() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setSerializationProvider("myProvider");

		assertThat(mojarraProperties.getSerializationProvider()).isEqualTo("myProvider");
	}

	public void testFaceletFactory() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setFaceletFactory("myFactory");

		assertThat(mojarraProperties.getFaceletFactory()).isEqualTo("myFactory");
	}

}
