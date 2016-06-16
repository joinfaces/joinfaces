package com.github.persapiens.jsfboot.myfaces;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class MyfacesPropertiesStateSavingTest {

    private static final String SHA1PRNG = "SHA1PRNG";
    private static final String AUTO = "auto";
    
	public void testRenderViewstateId() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setRenderViewstateId(true);

		assertThat(myfacesProperties.getRenderViewstateId()).isTrue();
	}

	public void testRefreshTransientBuildOnPss() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setRefreshTransientBuildOnPss(AUTO);

		assertThat(myfacesProperties.getRefreshTransientBuildOnPss()).isEqualTo(AUTO);
	}

	public void testRefreshTransientBuildOnPssPreserveState() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setRefreshTransientBuildOnPssPreserveState(true);

		assertThat(myfacesProperties.getRefreshTransientBuildOnPssPreserveState()).isTrue();
	}

	public void testNumberOfViewsInSession() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setNumberOfViewsInSession(20);

		assertThat(myfacesProperties.getNumberOfViewsInSession()).isEqualTo(20);
	}

	public void testNumberOfSequentialViewsInSession() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setNumberOfSequentialViewsInSession(4);

		assertThat(myfacesProperties.getNumberOfSequentialViewsInSession()).isEqualTo(4);
	}
                
	public void testNumberOfFlashTokensInSession() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setNumberOfFlashTokensInSession(5);

		assertThat(myfacesProperties.getNumberOfFlashTokensInSession()).isEqualTo(5);
	}

	public void testFacesFlowClientWindowIdsInSession() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setFacesFlowClientWindowIdsInSession(6);

		assertThat(myfacesProperties.getFacesFlowClientWindowIdsInSession()).isEqualTo(6);
	}

	public void testUseEncryption() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setUseEncryption(true);

		assertThat(myfacesProperties.getUseEncryption()).isTrue();
	}

	public void testSecret() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setSecret("mySecret");

		assertThat(myfacesProperties.getSecret()).isEqualTo("mySecret");
	}

	public void testAlgorithm() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setAlgorithm("DES");

		assertThat(myfacesProperties.getAlgorithm()).isEqualTo("DES");
	}

	public void testSecretCache() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setSecretCache(true);

		assertThat(myfacesProperties.getSecretCache()).isTrue();
	}

	public void testAlgorithmIv() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setAlgorithmIv("iv");

		assertThat(myfacesProperties.getAlgorithmIv()).isEqualTo("iv");
	}

	public void testAlgorithmParameters() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setAlgorithmParameters("ECB/PKCS5Padding");

		assertThat(myfacesProperties.getAlgorithmParameters()).isEqualTo("ECB/PKCS5Padding");
	}

	public void testSerialFactory() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setSerialFactory("mySerialFactory");

		assertThat(myfacesProperties.getSerialFactory()).isEqualTo("mySerialFactory");
	}

	public void testCompressStateInClient() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setCompressStateInClient(true);

		assertThat(myfacesProperties.getCompressStateInClient()).isTrue();
	}

	public void testMacAlgorithm() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setMacAlgorithm("HmacSHA1");

		assertThat(myfacesProperties.getMacAlgorithm()).isEqualTo("HmacSHA1");
	}

	public void testMacSecret() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setMacSecret("myMacSecret");

		assertThat(myfacesProperties.getMacSecret()).isEqualTo("myMacSecret");
	}

	public void testMacSecretCache() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setMacSecretCache(true);

		assertThat(myfacesProperties.getMacSecretCache()).isTrue();
	}

	public void testRandomKeyInCsrfSessionToken() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setRandomKeyInCsrfSessionToken("none");

		assertThat(myfacesProperties.getRandomKeyInCsrfSessionToken()).isEqualTo("none");
	}

	public void testRandomKeyInCsrfSessionTokenLength() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setRandomKeyInCsrfSessionTokenLength(16);

		assertThat(myfacesProperties.getRandomKeyInCsrfSessionTokenLength()).isEqualTo(16);
	}

	public void testRandomKeyInCsrfSessionTokenSecureRandomClass() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setRandomKeyInCsrfSessionTokenSecureRandomClass("mySecureRamdomClass");

		assertThat(myfacesProperties.getRandomKeyInCsrfSessionTokenSecureRandomClass()).isEqualTo("mySecureRamdomClass");
	}
    
	public void testRandomKeyInCsrfSessionTokenSecureRandomProvider() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setRandomKeyInCsrfSessionTokenSecureRandomProvider("mySecureRamdomProvider");

		assertThat(myfacesProperties.getRandomKeyInCsrfSessionTokenSecureRandomProvider()).isEqualTo("mySecureRamdomProvider");
	}

	public void testRandomKeyInCsrfSessionTokenSecureRandomAlgoritm() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setRandomKeyInCsrfSessionTokenSecureRandomAlgoritm(SHA1PRNG);

		assertThat(myfacesProperties.getRandomKeyInCsrfSessionTokenSecureRandomAlgoritm()).isEqualTo(SHA1PRNG);
	}

	public void testClientViewStateTimeout() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setClientViewStateTimeout(0);

		assertThat(myfacesProperties.getClientViewStateTimeout()).isEqualTo(0);
	}

	public void testCompressStateInSession() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setCompressStateInSession(true);

		assertThat(myfacesProperties.getCompressStateInSession()).isTrue();
	}

	public void testUseFlashScopePurgeViewsInSession() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setUseFlashScopePurgeViewsInSession(true);

		assertThat(myfacesProperties.getUseFlashScopePurgeViewsInSession()).isTrue();
	}

	public void testRandomKeyInViewStateSessionToken() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setRandomKeyInViewStateSessionToken("myStateSessionToken");

		assertThat(myfacesProperties.getRandomKeyInViewStateSessionToken()).isEqualTo("myStateSessionToken");
	}

	public void testRandomKeyInViewStateSessionTokenLength() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setRandomKeyInViewStateSessionTokenLength(8);

		assertThat(myfacesProperties.getRandomKeyInViewStateSessionTokenLength()).isEqualTo(8);
	}

	public void testRandomKeyInViewStateSessionTokenSecureRandomClass() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setRandomKeyInViewStateSessionTokenSecureRandomClass("mySecureRandomClass");

		assertThat(myfacesProperties.getRandomKeyInViewStateSessionTokenSecureRandomClass()).isEqualTo("mySecureRandomClass");
	}

	public void testRandomKeyInViewStateSessionTokenSecureRandomProvider() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setRandomKeyInViewStateSessionTokenSecureRandomProvider("mySecureRandomProvider");

		assertThat(myfacesProperties.getRandomKeyInViewStateSessionTokenSecureRandomProvider()).isEqualTo("mySecureRandomProvider");
	}

	public void testRandomKeyInViewStateSessionTokenSecureRandomAlgorithm() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setRandomKeyInViewStateSessionTokenSecureRandomAlgorithm(SHA1PRNG);

		assertThat(myfacesProperties.getRandomKeyInViewStateSessionTokenSecureRandomAlgorithm()).isEqualTo(SHA1PRNG);
	}

	public void testAutocompleteOffViewState() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setAutocompleteOffViewState(true);

		assertThat(myfacesProperties.getAutocompleteOffViewState()).isTrue();
	}

	public void testCheckIdProductionMode() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setCheckIdProductionMode(AUTO);

		assertThat(myfacesProperties.getCheckIdProductionMode()).isEqualTo(AUTO);
	}

}
