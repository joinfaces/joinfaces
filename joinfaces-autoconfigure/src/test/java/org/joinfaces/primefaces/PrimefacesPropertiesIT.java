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
import org.primefaces.cache.DefaultCacheProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(
		properties = "spring.profiles.active=propertyTest",
		webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
public class PrimefacesPropertiesIT {

	@Autowired
	private Primefaces4_0Properties primefaces4_0Properties;

	@Autowired
	private Primefaces5_0Properties primefaces5_0Properties;

	@Autowired
	private Primefaces5_1Properties primefaces5_1Properties;

	@Autowired
	private Primefaces5_2Properties primefaces5_2Properties;

	@Autowired
	private Primefaces6_0Properties primefaces6_0Properties;

	@Test
	public void testPrivateCaptchaKey() {
		assertThat(this.primefaces4_0Properties.getPrivateCaptchaKey()).isEqualTo("myPrivateCaptchaKey");
	}

	@Test
	public void testPublicCaptchaKey() {
		assertThat(this.primefaces4_0Properties.getPublicCaptchaKey()).isEqualTo("myKey");
	}

	@Test
	public void testAutoUpdate() {
		assertThat(this.primefaces5_0Properties.getAutoUpdate()).isEqualTo("auto");
	}

	@Test
	public void testCacheProvider() {
		assertThat(this.primefaces5_0Properties.getCacheProvider()).isEqualTo(DefaultCacheProvider.class);
	}

	@Test
	public void testDir() {
		assertThat(this.primefaces4_0Properties.getDir()).isEqualTo("ltr");
	}

	@Test
	public void testEarlyPostParamEvaluation() {
		assertThat(this.primefaces6_0Properties.isEarlyPostParamEvaluation()).isTrue();
	}

	@Test
	public void testFontAwesome() {
		assertThat(this.primefaces5_2Properties.isFontAwesome()).isTrue();
	}

	@Test
	public void testInterpolateClientSideValidationMessages() {
		assertThat(this.primefaces6_0Properties.isInterpolateClientSideValidationMessages()).isTrue();
	}

	@Test
	public void testLegacyWidgetNamespace() {
		assertThat(this.primefaces5_1Properties.isLegacyWidgetNamespace()).isTrue();
	}

	@Test
	public void testMobileTheme() {
		assertThat(this.primefaces5_0Properties.getMobile().getTheme()).isEqualTo("cupertino");
	}

	@Test
	public void testClientSideValidation() {
		assertThat(this.primefaces4_0Properties.isClientSideValidation()).isTrue();
	}

	@Test
	public void testPushServerUrl() {
		assertThat(this.primefaces4_0Properties.getPushServerUrl()).isEqualTo("http://myhost.org");
	}

	@Test
	public void testResetValues() {
		assertThat(this.primefaces4_0Properties.isResetValues()).isTrue();
	}

	@Test
	public void testSecret() {
		assertThat(this.primefaces4_0Properties.getSecret()).isEqualTo("mySecret");
	}

	@Test
	public void testSubmit() {
		assertThat(this.primefaces4_0Properties.getSubmit()).isEqualTo("partial");
	}

	@Test
	public void testTheme() {
		assertThat(this.primefaces4_0Properties.getTheme()).isEqualTo("omega");
	}

	@Test
	public void testTransformMetadata() {
		assertThat(this.primefaces5_0Properties.isTransformMetadata()).isTrue();
	}

	@Test
	public void testUploader() {
		assertThat(this.primefaces4_0Properties.getUploader()).isEqualTo("auto");
	}

}
