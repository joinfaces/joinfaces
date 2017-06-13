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

package org.joinfaces.myfaces;

import org.apache.myfaces.shared.util.serial.DefaultSerialFactory;
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
public class MyfacesPropertiesStateSavingIT {

	@Autowired
	private MyfacesProperties myfacesProperties;

	private static final String SHA1PRNG = "SHA1PRNG";
	private static final String AUTO = "auto";

	@Test
	public void testRenderViewstateId() {
		assertThat(this.myfacesProperties.getRenderViewstateId()).isTrue();
	}

	@Test
	public void testRefreshTransientBuildOnPss() {
		assertThat(this.myfacesProperties.getRefreshTransientBuildOnPss()).isEqualTo(AUTO);
	}

	@Test
	public void testRefreshTransientBuildOnPssPreserveState() {
		assertThat(this.myfacesProperties.getRefreshTransientBuildOnPssPreserveState()).isTrue();
	}

	@Test
	public void testNumberOfViewsInSession() {
		assertThat(this.myfacesProperties.getNumberOfViewsInSession()).isEqualTo(20);
	}

	@Test
	public void testNumberOfSequentialViewsInSession() {
		assertThat(this.myfacesProperties.getNumberOfSequentialViewsInSession()).isEqualTo(4);
	}

	@Test
	public void testNumberOfFlashTokensInSession() {
		assertThat(this.myfacesProperties.getNumberOfFlashTokensInSession()).isEqualTo(5);
	}

	@Test
	public void testFacesFlowClientWindowIdsInSession() {
		assertThat(this.myfacesProperties.getFacesFlowClientWindowIdsInSession()).isEqualTo(6);
	}

	@Test
	public void testUseEncryption() {
		assertThat(this.myfacesProperties.getUseEncryption()).isTrue();
	}

	@Test
	public void testSecret() {
		assertThat(this.myfacesProperties.getSecret()).isEqualTo("mySecret");
	}

	@Test
	public void testAlgorithm() {
		assertThat(this.myfacesProperties.getAlgorithm()).isEqualTo("DES");
	}

	@Test
	public void testSecretCache() {
		assertThat(this.myfacesProperties.getSecretCache()).isTrue();
	}

	@Test
	public void testAlgorithmIv() {
		assertThat(this.myfacesProperties.getAlgorithmIv()).isEqualTo("iv");
	}

	@Test
	public void testAlgorithmParameters() {
		assertThat(this.myfacesProperties.getAlgorithmParameters()).isEqualTo("ECB/PKCS5Padding");
	}

	@Test
	public void testSerialFactory() {
		assertThat(this.myfacesProperties.getSerialFactory()).isEqualTo(DefaultSerialFactory.class);
	}

	@Test
	public void testCompressStateInClient() {
		assertThat(this.myfacesProperties.getCompressStateInClient()).isTrue();
	}

	@Test
	public void testMacAlgorithm() {
		assertThat(this.myfacesProperties.getMacAlgorithm()).isEqualTo("HmacSHA1");
	}

	@Test
	public void testMacSecret() {
		assertThat(this.myfacesProperties.getMacSecret()).isEqualTo("myMacSecret");
	}

	@Test
	public void testMacSecretCache() {
		assertThat(this.myfacesProperties.getMacSecretCache()).isTrue();
	}

	@Test
	public void testRandomKeyInCsrfSessionToken() {
		assertThat(this.myfacesProperties.getRandomKeyInCsrfSessionToken()).isEqualTo("none");
	}

	@Test
	public void testRandomKeyInCsrfSessionTokenLength() {
		assertThat(this.myfacesProperties.getRandomKeyInCsrfSessionTokenLength()).isEqualTo(16);
	}

	@Test
	public void testRandomKeyInCsrfSessionTokenSecureRandomClass() {
		assertThat(this.myfacesProperties.getRandomKeyInCsrfSessionTokenSecureRandomClass()).isEqualTo("mySecureRamdomClass");
	}

	@Test
	public void testRandomKeyInCsrfSessionTokenSecureRandomProvider() {
		assertThat(this.myfacesProperties.getRandomKeyInCsrfSessionTokenSecureRandomProvider()).isEqualTo("mySecureRamdomProvider");
	}

	@Test
	public void testRandomKeyInCsrfSessionTokenSecureRandomAlgoritm() {
		assertThat(this.myfacesProperties.getRandomKeyInCsrfSessionTokenSecureRandomAlgoritm()).isEqualTo(SHA1PRNG);
	}

	@Test
	public void testClientViewStateTimeout() {
		assertThat(this.myfacesProperties.getClientViewStateTimeout()).isEqualTo(0);
	}

	@Test
	public void testCompressStateInSession() {
		assertThat(this.myfacesProperties.getCompressStateInSession()).isTrue();
	}

	@Test
	public void testUseFlashScopePurgeViewsInSession() {
		assertThat(this.myfacesProperties.getUseFlashScopePurgeViewsInSession()).isTrue();
	}

	@Test
	public void testRandomKeyInViewStateSessionToken() {
		assertThat(this.myfacesProperties.getRandomKeyInViewStateSessionToken()).isEqualTo("myStateSessionToken");
	}

	@Test
	public void testRandomKeyInViewStateSessionTokenLength() {
		assertThat(this.myfacesProperties.getRandomKeyInViewStateSessionTokenLength()).isEqualTo(8);
	}

	@Test
	public void testRandomKeyInViewStateSessionTokenSecureRandomClass() {
		assertThat(this.myfacesProperties.getRandomKeyInViewStateSessionTokenSecureRandomClass()).isEqualTo("mySecureRandomClass");
	}

	@Test
	public void testRandomKeyInViewStateSessionTokenSecureRandomProvider() {
		assertThat(this.myfacesProperties.getRandomKeyInViewStateSessionTokenSecureRandomProvider()).isEqualTo("mySecureRandomProvider");
	}

	@Test
	public void testRandomKeyInViewStateSessionTokenSecureRandomAlgorithm() {
		assertThat(this.myfacesProperties.getRandomKeyInViewStateSessionTokenSecureRandomAlgorithm()).isEqualTo(SHA1PRNG);
	}

	@Test
	public void testAutocompleteOffViewState() {
		assertThat(this.myfacesProperties.getAutocompleteOffViewState()).isTrue();
	}

	@Test
	public void testCheckIdProductionMode() {
		assertThat(this.myfacesProperties.getCheckIdProductionMode()).isEqualTo(AUTO);
	}

}
