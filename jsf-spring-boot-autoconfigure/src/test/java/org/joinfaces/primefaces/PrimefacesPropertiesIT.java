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

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Primefaces6_0AutoConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class PrimefacesPropertiesIT {

	@Autowired
	private PrimefacesProperties primefacesProperties;

	@Test
	public void testPrivateCaptchaKey() {
		assertThat(this.primefacesProperties.getPrivateCaptchaKey()).isEqualTo("myPrivateCaptchaKey");
	}

	@Test
	public void testPublicCaptchaKey() {
		assertThat(this.primefacesProperties.getPublicCaptchaKey()).isEqualTo("myKey");
	}

	@Test
	public void testAutoUpdate() {
		assertThat(this.primefacesProperties.getAutoUpdate()).isEqualTo("auto");
	}

	@Test
	public void testBeanValidationDisabled() {
		assertThat(this.primefacesProperties.getBeanValidationDisabled()).isTrue();
	}

	@Test
	public void testCacheProvider() {
		assertThat(this.primefacesProperties.getCacheProvider()).isEqualTo("myCacheProvider");
	}

	@Test
	public void testDir() {
		assertThat(this.primefacesProperties.getDir()).isEqualTo("left");
	}

	@Test
	public void testEarlyPostParamEvaluation() {
		assertThat(this.primefacesProperties.getEarlyPostParamEvaluation()).isTrue();
	}

	@Test
	public void testFontAwesome() {
		assertThat(this.primefacesProperties.getFontAwesome()).isTrue();
	}

	@Test
	public void testInterpolateClientSideValidationMessages() {
		assertThat(this.primefacesProperties.getInterpolateClientSideValidationMessages()).isTrue();
	}

	@Test
	public void testLegacyWidgetNamespace() {
		assertThat(this.primefacesProperties.getLegacyWidgetNamespace()).isTrue();
	}

	@Test
	public void testMobileTheme() {
		assertThat(this.primefacesProperties.getMobile().getTheme()).isEqualTo("cupertino");
	}

	@Test
	public void testClientSideValidation() {
		assertThat(this.primefacesProperties.getClientSideValidation()).isTrue();
	}

	@Test
	public void testPushServerUrl() {
		assertThat(this.primefacesProperties.getPushServerUrl()).isEqualTo("http://myhost.org");
	}

	@Test
	public void testResetValues() {
		assertThat(this.primefacesProperties.getResetValues()).isTrue();
	}

	@Test
	public void testSecret() {
		assertThat(this.primefacesProperties.getSecret()).isEqualTo("mySecret");
	}

	@Test
	public void testSubmit() {
		assertThat(this.primefacesProperties.getSubmit()).isEqualTo("partial");
	}

	@Test
	public void testTheme() {
		assertThat(this.primefacesProperties.getTheme()).isEqualTo("omega");
	}

	@Test
	public void testTransformMetadata() {
		assertThat(this.primefacesProperties.getTransformMetadata()).isTrue();
	}

	@Test
	public void testUploader() {
		assertThat(this.primefacesProperties.getUploader()).isEqualTo("auto");
	}

	@Test
	public void testCaptchaPrivateKey() {
		assertThat(this.primefacesProperties.getCaptcha().getPrivateKey()).isEqualTo("myPrivateKey");
	}

}
