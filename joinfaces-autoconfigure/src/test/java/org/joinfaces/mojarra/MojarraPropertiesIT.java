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

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(
		properties = "spring.profiles.active=propertyTest",
		webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
public class MojarraPropertiesIT {

	@Autowired
	private MojarraProperties mojarraProperties;

	@Test
	public void testClientStateTimeout() {
		assertThat(this.mojarraProperties.getClientStateTimeout())
			.isEqualTo(10);
	}

	@Test
	public void testClientStateWriteBufferSize() {
		assertThat(this.mojarraProperties.getClientStateWriteBufferSize())
			.isEqualTo(11);
	}

	@Test
	public void testCompressViewState() {
		assertThat(this.mojarraProperties.getCompressViewState())
			.isTrue();
	}

	@Test
	public void testDisableClientStateEncryption() {
		assertThat(this.mojarraProperties.getDisableClientStateEncryption())
			.isTrue();
	}

	@Test
	public void testEnableClientStateDebugging() {
		assertThat(this.mojarraProperties.getEnableClientStateDebugging())
			.isTrue();
	}

	@Test
	public void testGenerateUniqueServerStateIds() {
		assertThat(this.mojarraProperties.getGenerateUniqueServerStateIds())
			.isTrue();
	}

	@Test
	public void testNumberOfLogicalViews() {
		assertThat(this.mojarraProperties.getNumberOfLogicalViews())
			.isEqualTo(15);
	}

	@Test
	public void testNumberOfViewsInSession() {
		assertThat(this.mojarraProperties.getNumberOfViewsInSession())
			.isEqualTo(16);
	}

	@Test
	public void testSerializeServerState() {
		assertThat(this.mojarraProperties.getSerializeServerState())
			.isTrue();
	}

	@Test
	public void testWriteStateAtFormEnd() {
		assertThat(this.mojarraProperties.getWriteStateAtFormEnd())
			.isTrue();
	}

	@Test
	public void testAllowTextChildren() {
		assertThat(this.mojarraProperties.getAllowTextChildren())
			.isTrue();
	}

	@Test
	public void testAutoCompleteOffOnViewState() {
		assertThat(this.mojarraProperties.getAutoCompleteOffOnViewState())
			.isTrue();
	}

	@Test
	public void testCompressJavaScript() {
		assertThat(this.mojarraProperties.getCompressJavaScript())
			.isTrue();
	}

	@Test
	public void testDisableUnicodeEscaping() {
		assertThat(this.mojarraProperties.getDisableUnicodeEscaping())
			.isEqualTo("true");
	}

	@Test
	public void testDisableIdUniquenessCheck() {
		assertThat(this.mojarraProperties.getDisableIdUniquenessCheck())
			.isTrue();
	}

	@Test
	public void testEnabledJSStyleHiding() {
		assertThat(this.mojarraProperties.getEnabledJsStyleHiding())
			.isTrue();
	}

	@Test
	public void testEnableScriptsInAttributeValues() {
		assertThat(this.mojarraProperties.getEnableScriptsInAttributeValues())
			.isTrue();
	}

	@Test
	public void testEnableViewStateIdRendering() {
		assertThat(this.mojarraProperties.getEnableViewStateIdRendering())
			.isTrue();
	}

	@Test
	public void testPreferXHTML() {
		assertThat(this.mojarraProperties.getPreferXhtml())
			.isTrue();
	}

	@Test
	public void testResponseBufferSize() {
		assertThat(this.mojarraProperties.getResponseBufferSize())
			.isEqualTo(20);
	}

	@Test
	public void testCacheResourceModificationTimestamp() {
		assertThat(this.mojarraProperties.getCacheResourceModificationTimestamp())
			.isTrue();
	}

	@Test
	public void testCompressableMimeTypes() {
		assertThat(this.mojarraProperties.getCompressableMimeTypes())
			.isEqualTo("txt");
	}

	@Test
	public void testDefaultResourceMaxAge() {
		assertThat(this.mojarraProperties.getDefaultResourceMaxAge())
			.isEqualTo(30);
	}

	@Test
	public void testEnableFaceletsResourceResolverCompositeComponents() {
		assertThat(this.mojarraProperties.getEnableFaceletsResourceResolverCompositeComponents())
			.isTrue();
	}

	@Test
	public void testEnableMissingResourceLibraryDetection() {
		assertThat(this.mojarraProperties.getEnableMissingResourceLibraryDetection())
			.isTrue();
	}

	@Test
	public void testResourceUpdateCheckPeriod() {
		assertThat(this.mojarraProperties.getResourceUpdateCheckPeriod())
			.isEqualTo(50);
	}

	@Test
	public void testEnableAgressiveSessionDirtying() {
		assertThat(this.mojarraProperties.getEnableAgressiveSessionDirtying())
			.isTrue();
	}

	@Test
	public void testEnableDistributable() {
		assertThat(this.mojarraProperties.getEnableDistributable())
			.isTrue();
	}

	@Test
	public void testAnnotationScanPackages() {
		assertThat(this.mojarraProperties.getAnnotationScanPackages())
			.isEqualTo("mypackage");
	}

	@Test
	public void testDisplayConfiguration() {
		assertThat(this.mojarraProperties.getDisplayConfiguration())
			.isTrue();
	}

	@Test
	public void testEnableCoreTagLibValidator() {
		assertThat(this.mojarraProperties.getEnableCoreTagLibValidator())
			.isTrue();
	}

	@Test
	public void testEnableHtmlTagLibValidator() {
		assertThat(this.mojarraProperties.getEnableHtmlTagLibValidator())
			.isTrue();
	}

	@Test
	public void testEnableLazyBeanValidation() {
		assertThat(this.mojarraProperties.getEnableLazyBeanValidation())
			.isTrue();
	}

	@Test
	public void testEnableThreading() {
		assertThat(this.mojarraProperties.getEnableThreading())
			.isTrue();
	}

	@Test
	public void testForceLoadConfiguration() {
		assertThat(this.mojarraProperties.getForceLoadConfiguration())
			.isTrue();
	}

	@Test
	public void testValidateXml() {
		assertThat(this.mojarraProperties.getValidateXml())
			.isTrue();
	}

	@Test
	public void testVerifyObjects() {
		assertThat(this.mojarraProperties.getVerifyObjects())
			.isTrue();
	}

	@Test
	public void testEnableTransitionTimeNoOpFlash() {
		assertThat(this.mojarraProperties.getEnableTransitionTimeNoOpFlash())
			.isTrue();
	}

	@Test
	public void testForceAlwaysWriteFlashCookie() {
		assertThat(this.mojarraProperties.getForceAlwaysWriteFlashCookie())
			.isTrue();
	}

	@Test
	public void testNamespaceParameters() {
		assertThat(this.mojarraProperties.getNamespaceParameters())
			.isTrue();
	}

	@Test
	public void testRegisterConverterPropertyEditors() {
		assertThat(this.mojarraProperties.getRegisterConverterPropertyEditors())
			.isTrue();
	}

	@Test
	public void testSendPoweredByHeader() {
		assertThat(this.mojarraProperties.getSendPoweredByHeader())
			.isTrue();
	}

	@Test
	public void testDisallowDoctypeDeclFactory() {
		assertThat(this.mojarraProperties.getDisallowDoctypeDecl())
			.isTrue();
	}

}
