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

package org.joinfaces.primefaces;

import org.testng.annotations.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = PrimefacesSpringBootAutoConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Test
public class PrimefacesPropertiesIT extends AbstractTestNGSpringContextTests {

	@Autowired
	private PrimefacesProperties primefacesProperties;

	public void testPrivateCaptchaKey() {
		assertThat(this.primefacesProperties.getPrivateCaptchaKey()).isEqualTo("myPrivateCaptchaKey");
	}

	public void testPublicCaptchaKey() {
		assertThat(this.primefacesProperties.getPublicCaptchaKey()).isEqualTo("myKey");
	}

	public void testAutoUpdate() {
		assertThat(this.primefacesProperties.getAutoUpdate()).isEqualTo("auto");
	}

	public void testBeanValidationDisabled() {
		assertThat(this.primefacesProperties.getBeanValidationDisabled()).isTrue();
	}

	public void testCacheProvider() {
		assertThat(this.primefacesProperties.getCacheProvider()).isEqualTo("myCacheProvider");
	}

	public void testDir() {
		assertThat(this.primefacesProperties.getDir()).isEqualTo("left");
	}

	public void testEarlyPostParamEvaluation() {
		assertThat(this.primefacesProperties.getEarlyPostParamEvaluation()).isTrue();
	}

	public void testFontAwesome() {
		assertThat(this.primefacesProperties.getFontAwesome()).isTrue();
	}

	public void testInterpolateClientSideValidationMessages() {
		assertThat(this.primefacesProperties.getInterpolateClientSideValidationMessages()).isTrue();
	}

	public void testLegacyWidgetNamespace() {
		assertThat(this.primefacesProperties.getLegacyWidgetNamespace()).isTrue();
	}

	public void testMobileTheme() {
		assertThat(this.primefacesProperties.getMobile().getTheme()).isEqualTo("cupertino");
	}

	public void testClientSideValidation() {
		assertThat(this.primefacesProperties.getClientSideValidation()).isTrue();
	}

	public void testPushServerUrl() {
		assertThat(this.primefacesProperties.getPushServerUrl()).isEqualTo("http://myhost.org");
	}

	public void testResetValues() {
		assertThat(this.primefacesProperties.getResetValues()).isTrue();
	}

	public void testSecret() {
		assertThat(this.primefacesProperties.getSecret()).isEqualTo("mySecret");
	}

	public void testSubmit() {
		assertThat(this.primefacesProperties.getSubmit()).isEqualTo("partial");
	}

	public void testTheme() {
		assertThat(this.primefacesProperties.getTheme()).isEqualTo("omega");
	}

	public void testTransformMetadata() {
		assertThat(this.primefacesProperties.getTransformMetadata()).isTrue();
	}

	public void testUploader() {
		assertThat(this.primefacesProperties.getUploader()).isEqualTo("myUploader");
	}

	public void testCaptchaPrivateKey() {
		assertThat(this.primefacesProperties.getCaptcha().getPrivateKey()).isEqualTo("myPrivateKey");
	}

}
