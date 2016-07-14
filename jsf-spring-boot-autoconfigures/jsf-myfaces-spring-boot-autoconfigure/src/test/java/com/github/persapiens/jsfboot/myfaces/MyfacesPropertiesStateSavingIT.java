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

package com.github.persapiens.jsfboot.myfaces;

import org.testng.annotations.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringApplicationConfiguration(classes = MyfacesSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class MyfacesPropertiesStateSavingIT extends AbstractTestNGSpringContextTests {

	@Autowired
	private MyfacesProperties myfacesProperties;

	private static final String SHA1PRNG = "SHA1PRNG";
	private static final String AUTO = "auto";

	public void testRenderViewstateId() {
		assertThat(this.myfacesProperties.getRenderViewstateId()).isTrue();
	}

	public void testRefreshTransientBuildOnPss() {
		assertThat(this.myfacesProperties.getRefreshTransientBuildOnPss()).isEqualTo(AUTO);
	}

	public void testRefreshTransientBuildOnPssPreserveState() {
		assertThat(this.myfacesProperties.getRefreshTransientBuildOnPssPreserveState()).isTrue();
	}

	public void testNumberOfViewsInSession() {
		assertThat(this.myfacesProperties.getNumberOfViewsInSession()).isEqualTo(20);
	}

	public void testNumberOfSequentialViewsInSession() {
		assertThat(this.myfacesProperties.getNumberOfSequentialViewsInSession()).isEqualTo(4);
	}

	public void testNumberOfFlashTokensInSession() {
		assertThat(this.myfacesProperties.getNumberOfFlashTokensInSession()).isEqualTo(5);
	}

	public void testFacesFlowClientWindowIdsInSession() {
		assertThat(this.myfacesProperties.getFacesFlowClientWindowIdsInSession()).isEqualTo(6);
	}

	public void testUseEncryption() {
		assertThat(this.myfacesProperties.getUseEncryption()).isTrue();
	}

	public void testSecret() {
		assertThat(this.myfacesProperties.getSecret()).isEqualTo("mySecret");
	}

	public void testAlgorithm() {
		assertThat(this.myfacesProperties.getAlgorithm()).isEqualTo("DES");
	}

	public void testSecretCache() {
		assertThat(this.myfacesProperties.getSecretCache()).isTrue();
	}

	public void testAlgorithmIv() {
		assertThat(this.myfacesProperties.getAlgorithmIv()).isEqualTo("iv");
	}

	public void testAlgorithmParameters() {
		assertThat(this.myfacesProperties.getAlgorithmParameters()).isEqualTo("ECB/PKCS5Padding");
	}

	public void testSerialFactory() {
		assertThat(this.myfacesProperties.getSerialFactory()).isEqualTo("mySerialFactory");
	}

	public void testCompressStateInClient() {
		assertThat(this.myfacesProperties.getCompressStateInClient()).isTrue();
	}

	public void testMacAlgorithm() {
		assertThat(this.myfacesProperties.getMacAlgorithm()).isEqualTo("HmacSHA1");
	}

	public void testMacSecret() {
		assertThat(this.myfacesProperties.getMacSecret()).isEqualTo("myMacSecret");
	}

	public void testMacSecretCache() {
		assertThat(this.myfacesProperties.getMacSecretCache()).isTrue();
	}

	public void testRandomKeyInCsrfSessionToken() {
		assertThat(this.myfacesProperties.getRandomKeyInCsrfSessionToken()).isEqualTo("none");
	}

	public void testRandomKeyInCsrfSessionTokenLength() {
		assertThat(this.myfacesProperties.getRandomKeyInCsrfSessionTokenLength()).isEqualTo(16);
	}

	public void testRandomKeyInCsrfSessionTokenSecureRandomClass() {
		assertThat(this.myfacesProperties.getRandomKeyInCsrfSessionTokenSecureRandomClass()).isEqualTo("mySecureRamdomClass");
	}

	public void testRandomKeyInCsrfSessionTokenSecureRandomProvider() {
		assertThat(this.myfacesProperties.getRandomKeyInCsrfSessionTokenSecureRandomProvider()).isEqualTo("mySecureRamdomProvider");
	}

	public void testRandomKeyInCsrfSessionTokenSecureRandomAlgoritm() {
		assertThat(this.myfacesProperties.getRandomKeyInCsrfSessionTokenSecureRandomAlgoritm()).isEqualTo(SHA1PRNG);
	}

	public void testClientViewStateTimeout() {
		assertThat(this.myfacesProperties.getClientViewStateTimeout()).isEqualTo(0);
	}

	public void testCompressStateInSession() {
		assertThat(this.myfacesProperties.getCompressStateInSession()).isTrue();
	}

	public void testUseFlashScopePurgeViewsInSession() {
		assertThat(this.myfacesProperties.getUseFlashScopePurgeViewsInSession()).isTrue();
	}

	public void testRandomKeyInViewStateSessionToken() {
		assertThat(this.myfacesProperties.getRandomKeyInViewStateSessionToken()).isEqualTo("myStateSessionToken");
	}

	public void testRandomKeyInViewStateSessionTokenLength() {
		assertThat(this.myfacesProperties.getRandomKeyInViewStateSessionTokenLength()).isEqualTo(8);
	}

	public void testRandomKeyInViewStateSessionTokenSecureRandomClass() {
		assertThat(this.myfacesProperties.getRandomKeyInViewStateSessionTokenSecureRandomClass()).isEqualTo("mySecureRandomClass");
	}

	public void testRandomKeyInViewStateSessionTokenSecureRandomProvider() {
		assertThat(this.myfacesProperties.getRandomKeyInViewStateSessionTokenSecureRandomProvider()).isEqualTo("mySecureRandomProvider");
	}

	public void testRandomKeyInViewStateSessionTokenSecureRandomAlgorithm() {
		assertThat(this.myfacesProperties.getRandomKeyInViewStateSessionTokenSecureRandomAlgorithm()).isEqualTo(SHA1PRNG);
	}

	public void testAutocompleteOffViewState() {
		assertThat(this.myfacesProperties.getAutocompleteOffViewState()).isTrue();
	}

	public void testCheckIdProductionMode() {
		assertThat(this.myfacesProperties.getCheckIdProductionMode()).isEqualTo(AUTO);
	}

}
