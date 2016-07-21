/*
 * Copyright 2016-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.joinfaces.mojarra;

import org.testng.annotations.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringApplicationConfiguration(classes = MojarraSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class MojarraPropertiesIT extends AbstractTestNGSpringContextTests {

	@Autowired
	private MojarraProperties mojarraProperties;

	public void testClientStateTimeout() {
		assertThat(this.mojarraProperties.getClientStateTimeout()).isEqualTo(10);
	}

	public void testClientStateWriteBufferSize() {
		assertThat(this.mojarraProperties.getClientStateWriteBufferSize()).isEqualTo(11);
	}

	public void testCompressViewState() {
		assertThat(this.mojarraProperties.getCompressViewState()).isTrue();
	}

	public void testDisableClientStateEncryption() {
		assertThat(this.mojarraProperties.getDisableClientStateEncryption()).isTrue();
	}

	public void testEnableClientStateDebugging() {
		assertThat(this.mojarraProperties.getEnableClientStateDebugging()).isTrue();
	}

	public void testGenerateUniqueServerStateIds() {
		assertThat(this.mojarraProperties.getGenerateUniqueServerStateIds()).isTrue();
	}

	public void testNumberOfLogicalViews() {
		assertThat(this.mojarraProperties.getNumberOfLogicalViews()).isEqualTo(15);
	}

	public void testNumberOfViewsInSession() {
		assertThat(this.mojarraProperties.getNumberOfViewsInSession()).isEqualTo(16);
	}

	public void testSerializeServerState() {
		assertThat(this.mojarraProperties.getSerializeServerState()).isTrue();
	}

	public void testWriteStateAtFormEnd() {
		assertThat(this.mojarraProperties.getWriteStateAtFormEnd()).isTrue();
	}

	public void testAllowTextChildren() {
		assertThat(this.mojarraProperties.getAllowTextChildren()).isTrue();
	}

	public void testAutoCompleteOffOnViewState() {
		assertThat(this.mojarraProperties.getAutoCompleteOffOnViewState()).isTrue();
	}

	public void testCompressJavaScript() {
		assertThat(this.mojarraProperties.getCompressJavaScript()).isTrue();
	}

	public void testDisableUnicodeEscaping() {
		assertThat(this.mojarraProperties.getDisableUnicodeEscaping()).isTrue();
	}

	public void testDisableIdUniquenessCheck() {
		assertThat(this.mojarraProperties.getDisableIdUniquenessCheck()).isTrue();
	}

	public void testEnabledJSStyleHiding() {
		assertThat(this.mojarraProperties.getEnabledJSStyleHiding()).isTrue();
	}

	public void testEnableScriptsInAttributeValues() {
		assertThat(this.mojarraProperties.getEnableScriptsInAttributeValues()).isTrue();
	}

	public void testEnableViewStateIdRendering() {
		assertThat(this.mojarraProperties.getEnableViewStateIdRendering()).isTrue();
	}

	public void testPreferXHTML() {
		assertThat(this.mojarraProperties.getPreferXHTML()).isTrue();
	}

	public void testResponseBufferSize() {
		assertThat(this.mojarraProperties.getResponseBufferSize()).isEqualTo(20);
	}

	public void testCacheResourceModificationTimestamp() {
		assertThat(this.mojarraProperties.getCacheResourceModificationTimestamp()).isTrue();
	}

	public void testCompressableMimeTypes() {
		assertThat(this.mojarraProperties.getCompressableMimeTypes()).isEqualTo("txt");
	}

	public void testDefaultResourceMaxAge() {
		assertThat(this.mojarraProperties.getDefaultResourceMaxAge()).isEqualTo(30);
	}

	public void testEnableFaceletsResourceResolverCompositeComponents() {
		assertThat(this.mojarraProperties.getEnableFaceletsResourceResolverCompositeComponents()).isTrue();
	}

	public void testEnableMissingResourceLibraryDetection() {
		assertThat(this.mojarraProperties.getEnableMissingResourceLibraryDetection()).isTrue();
	}

	public void testResourceUpdateCheckPeriod() {
		assertThat(this.mojarraProperties.getResourceUpdateCheckPeriod()).isEqualTo(50);
	}

	public void testEnableAgressiveSessionDirtying() {
		assertThat(this.mojarraProperties.getEnableAgressiveSessionDirtying()).isTrue();
	}

	public void testEnableDistributable() {
		assertThat(this.mojarraProperties.getEnableDistributable()).isTrue();
	}

	public void testAnnotationScanPackages() {
		assertThat(this.mojarraProperties.getAnnotationScanPackages()).isEqualTo("mypackage");
	}

	public void testDisplayConfiguration() {
		assertThat(this.mojarraProperties.getDisplayConfiguration()).isTrue();
	}

	public void testEnableCoreTagLibValidator() {
		assertThat(this.mojarraProperties.getEnableCoreTagLibValidator()).isTrue();
	}

	public void testEnableHtmlTagLibValidator() {
		assertThat(this.mojarraProperties.getEnableHtmlTagLibValidator()).isTrue();
	}

	public void testEnableLazyBeanValidation() {
		assertThat(this.mojarraProperties.getEnableLazyBeanValidation()).isTrue();
	}

	public void testEnableThreading() {
		assertThat(this.mojarraProperties.getEnableThreading()).isTrue();
	}

	public void testForceLoadConfiguration() {
		assertThat(this.mojarraProperties.getForceLoadConfiguration()).isTrue();
	}

	public void testValidateXml() {
		assertThat(this.mojarraProperties.getValidateXml()).isTrue();
	}

	public void testVerifyObjects() {
		assertThat(this.mojarraProperties.getVerifyObjects()).isTrue();
	}

	public void testEnableTransitionTimeNoOpFlash() {
		assertThat(this.mojarraProperties.getEnableTransitionTimeNoOpFlash()).isTrue();
	}

	public void testExpressionFactory() {
		assertThat(this.mojarraProperties.getExpressionFactory()).isEqualTo("myExpressionFactory");
	}

	public void testForceAlwaysWriteFlashCookie() {
		assertThat(this.mojarraProperties.getForceAlwaysWriteFlashCookie()).isTrue();
	}

	public void testInjectionProvider() {
		assertThat(this.mojarraProperties.getInjectionProvider()).isEqualTo("myInjectionProvider");
	}

	public void testNamespaceParameters() {
		assertThat(this.mojarraProperties.getNamespaceParameters()).isTrue();
	}

	public void testRegisterConverterPropertyEditors() {
		assertThat(this.mojarraProperties.getRegisterConverterPropertyEditors()).isTrue();
	}

	public void testSendPoweredByHeader() {
		assertThat(this.mojarraProperties.getSendPoweredByHeader()).isTrue();
	}

	public void testSerializationProvider() {
		assertThat(this.mojarraProperties.getSerializationProvider()).isEqualTo("myProvider");
	}

	public void testFaceletFactory() {
		assertThat(this.mojarraProperties.getFaceletFactory()).isEqualTo("myFactory");
	}

}
