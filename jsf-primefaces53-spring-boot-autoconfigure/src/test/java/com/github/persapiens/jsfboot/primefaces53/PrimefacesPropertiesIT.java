package com.github.persapiens.jsfboot.primefaces53;

import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.assertj.core.api.Assertions.assertThat;

@SpringApplicationConfiguration(classes = PrimefacesSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class PrimefacesPropertiesIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private PrimefacesProperties primefacesProperties;

	public void testPrivateCaptchaKey() {
		assertThat(primefacesProperties.getPrivateCaptchaKey()).isEqualTo("myPrivateCaptchaKey");
	}

	public void testPublicCaptchaKey() {
		assertThat(primefacesProperties.getPublicCaptchaKey()).isEqualTo("myKey");
	}

	public void testAutoUpdate() {
		assertThat(primefacesProperties.getAutoUpdate()).isEqualTo("auto");
	}

	public void testBeanValidationDisabled() {
		assertThat(primefacesProperties.getBeanValidationDisabled()).isTrue();
	}

	public void testCacheProvider() {
		assertThat(primefacesProperties.getCacheProvider()).isEqualTo("myCacheProvider");
	}

	public void testDir() {
		assertThat(primefacesProperties.getDir()).isEqualTo("left");
	}

	public void testEarlyPostParamEvaluation() {
		assertThat(primefacesProperties.getEarlyPostParamEvaluation()).isTrue();
	}

	public void testFontAwesome() {
		assertThat(primefacesProperties.getFontAwesome()).isTrue();
	}

	public void testInterpolateClientSideValidationMessages() {
		assertThat(primefacesProperties.getInterpolateClientSideValidationMessages()).isTrue();
	}
    
	public void testLegacyWidgetNamespace() {
		assertThat(primefacesProperties.getLegacyWidgetNamespace()).isTrue();
	}

	public void testMobileTheme() {
		assertThat(primefacesProperties.getMobile().getTheme()).isEqualTo("cupertino");
	}

	public void testClientSideValidation() {
		assertThat(primefacesProperties.getClientSideValidation()).isTrue();
	}

	public void testPushServerUrl() {
		assertThat(primefacesProperties.getPushServerUrl()).isEqualTo("http://myhost.org");
	}

	public void testResetValues() {
		assertThat(primefacesProperties.getResetValues()).isTrue();
	}

	public void testSecret() {
		assertThat(primefacesProperties.getSecret()).isEqualTo("mySecret");
	}

	public void testSubmit() {
		assertThat(primefacesProperties.getSubmit()).isEqualTo("partial");
	}

	public void testTheme() {
		assertThat(primefacesProperties.getTheme()).isEqualTo("omega");
	}
        
	public void testTransformMetadata() {
		assertThat(primefacesProperties.getTransformMetadata()).isTrue();
	}

	public void testUploader() {
		assertThat(primefacesProperties.getUploader()).isEqualTo("myUploader");
	}

	public void testCaptchaPrivateKey() {
		assertThat(primefacesProperties.getCaptcha().getPrivateKey()).isEqualTo("myPrivateKey");
	}

}
