package com.github.persapiens.jsfboot.primefaces;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class PrimefacesPropertiesTest {

	public void testPrivateCaptchaKey() {
		PrimefacesProperties primefacesProperties = new PrimefacesProperties();
        primefacesProperties.setPrivateCaptchaKey("myPrivateCaptchaKey");

		assertThat(primefacesProperties.getPrivateCaptchaKey()).isEqualTo("myPrivateCaptchaKey");
	}

	public void testPublicCaptchaKey() {
		PrimefacesProperties primefacesProperties = new PrimefacesProperties();
        primefacesProperties.setPublicCaptchaKey("myKey");

		assertThat(primefacesProperties.getPublicCaptchaKey()).isEqualTo("myKey");
	}

	public void testAutoUpdate() {
		PrimefacesProperties primefacesProperties = new PrimefacesProperties();
        primefacesProperties.setAutoUpdate("auto");

		assertThat(primefacesProperties.getAutoUpdate()).isEqualTo("auto");
	}

	public void testBeanValidationDisabled() {
		PrimefacesProperties primefacesProperties = new PrimefacesProperties();
        primefacesProperties.setBeanValidationDisabled(true);

		assertThat(primefacesProperties.getBeanValidationDisabled()).isTrue();
	}

	public void testCacheProvider() {
		PrimefacesProperties primefacesProperties = new PrimefacesProperties();
        primefacesProperties.setCacheProvider("myCacheProvider");

		assertThat(primefacesProperties.getCacheProvider()).isEqualTo("myCacheProvider");
	}

	public void testDir() {
		PrimefacesProperties primefacesProperties = new PrimefacesProperties();
        primefacesProperties.setDir("left");

		assertThat(primefacesProperties.getDir()).isEqualTo("left");
	}

	public void testEarlyPostParamEvaluation() {
		PrimefacesProperties primefacesProperties = new PrimefacesProperties();
        primefacesProperties.setEarlyPostParamEvaluation(true);

		assertThat(primefacesProperties.getEarlyPostParamEvaluation()).isTrue();
	}

	public void testFontAwesome() {
		PrimefacesProperties primefacesProperties = new PrimefacesProperties();
        primefacesProperties.setFontAwesome(true);

		assertThat(primefacesProperties.getFontAwesome()).isTrue();
	}

	public void testInterpolateClientSideValidationMessages() {
		PrimefacesProperties primefacesProperties = new PrimefacesProperties();
        primefacesProperties.setInterpolateClientSideValidationMessages(true);

		assertThat(primefacesProperties.getInterpolateClientSideValidationMessages()).isTrue();
	}
    
	public void testLegacyWidgetNamespace() {
		PrimefacesProperties primefacesProperties = new PrimefacesProperties();
        primefacesProperties.setLegacyWidgetNamespace(true);

		assertThat(primefacesProperties.getLegacyWidgetNamespace()).isTrue();
	}

	public void testMobileTheme() {
		PrimefacesProperties primefacesProperties = new PrimefacesProperties();
        primefacesProperties.getMobile().setTheme("cupertino");

		assertThat(primefacesProperties.getMobile().getTheme()).isEqualTo("cupertino");
	}

	public void testClientSideValidation() {
		PrimefacesProperties primefacesProperties = new PrimefacesProperties();
        primefacesProperties.setClientSideValidation(true);

		assertThat(primefacesProperties.getClientSideValidation()).isTrue();
	}

	public void testPushServerUrl() {
		PrimefacesProperties primefacesProperties = new PrimefacesProperties();
        primefacesProperties.setPushServerUrl("http://myhost.org");

		assertThat(primefacesProperties.getPushServerUrl()).isEqualTo("http://myhost.org");
	}

	public void testResetValues() {
		PrimefacesProperties primefacesProperties = new PrimefacesProperties();
        primefacesProperties.setResetValues(true);

		assertThat(primefacesProperties.getResetValues()).isTrue();
	}

	public void testSecret() {
		PrimefacesProperties primefacesProperties = new PrimefacesProperties();
        primefacesProperties.setSecret("mySecret");

		assertThat(primefacesProperties.getSecret()).isEqualTo("mySecret");
	}

	public void testSubmit() {
		PrimefacesProperties primefacesProperties = new PrimefacesProperties();
        primefacesProperties.setSubmit("partial");

		assertThat(primefacesProperties.getSubmit()).isEqualTo("partial");
	}

	public void testTheme() {
		PrimefacesProperties primefacesProperties = new PrimefacesProperties();
        primefacesProperties.setTheme("omega");

		assertThat(primefacesProperties.getTheme()).isEqualTo("omega");
	}
        
	public void testTransformMetadata() {
		PrimefacesProperties primefacesProperties = new PrimefacesProperties();
        primefacesProperties.setTransformMetadata(true);

		assertThat(primefacesProperties.getTransformMetadata()).isTrue();
	}

	public void testUploader() {
		PrimefacesProperties primefacesProperties = new PrimefacesProperties();
        primefacesProperties.setUploader("myUploader");

		assertThat(primefacesProperties.getUploader()).isEqualTo("myUploader");
	}

	public void testCaptchaPrivateKey() {
		PrimefacesProperties primefacesProperties = new PrimefacesProperties();
        primefacesProperties.getCaptcha().setPrivateKey("myPrivateKey");

		assertThat(primefacesProperties.getCaptcha().getPrivateKey()).isEqualTo("myPrivateKey");
	}

}
