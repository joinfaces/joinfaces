package com.github.persapiens.jsfboot.myfaces;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringApplicationConfiguration(classes = MyfacesSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class MyfacesPropertiesStateSavingIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private MyfacesProperties myfacesProperties;

    private static final String SHA1PRNG = "SHA1PRNG";
    private static final String AUTO = "auto";
    
	public void testRenderViewstateId() {
		assertThat(myfacesProperties.getRenderViewstateId()).isTrue();
	}

	public void testRefreshTransientBuildOnPss() {
		assertThat(myfacesProperties.getRefreshTransientBuildOnPss()).isEqualTo(AUTO);
	}

	public void testRefreshTransientBuildOnPssPreserveState() {
		assertThat(myfacesProperties.getRefreshTransientBuildOnPssPreserveState()).isTrue();
	}

	public void testNumberOfViewsInSession() {
		assertThat(myfacesProperties.getNumberOfViewsInSession()).isEqualTo(20);
	}

	public void testNumberOfSequentialViewsInSession() {
		assertThat(myfacesProperties.getNumberOfSequentialViewsInSession()).isEqualTo(4);
	}
                
	public void testNumberOfFlashTokensInSession() {
		assertThat(myfacesProperties.getNumberOfFlashTokensInSession()).isEqualTo(5);
	}

	public void testFacesFlowClientWindowIdsInSession() {
		assertThat(myfacesProperties.getFacesFlowClientWindowIdsInSession()).isEqualTo(6);
	}

	public void testUseEncryption() {
		assertThat(myfacesProperties.getUseEncryption()).isTrue();
	}

	public void testSecret() {
		assertThat(myfacesProperties.getSecret()).isEqualTo("mySecret");
	}

	public void testAlgorithm() {
		assertThat(myfacesProperties.getAlgorithm()).isEqualTo("DES");
	}

	public void testSecretCache() {
		assertThat(myfacesProperties.getSecretCache()).isTrue();
	}

	public void testAlgorithmIv() {
		assertThat(myfacesProperties.getAlgorithmIv()).isEqualTo("iv");
	}

	public void testAlgorithmParameters() {
		assertThat(myfacesProperties.getAlgorithmParameters()).isEqualTo("ECB/PKCS5Padding");
	}

	public void testSerialFactory() {
		assertThat(myfacesProperties.getSerialFactory()).isEqualTo("mySerialFactory");
	}

	public void testCompressStateInClient() {
		assertThat(myfacesProperties.getCompressStateInClient()).isTrue();
	}

	public void testMacAlgorithm() {
		assertThat(myfacesProperties.getMacAlgorithm()).isEqualTo("HmacSHA1");
	}

	public void testMacSecret() {
		assertThat(myfacesProperties.getMacSecret()).isEqualTo("myMacSecret");
	}

	public void testMacSecretCache() {
		assertThat(myfacesProperties.getMacSecretCache()).isTrue();
	}

	public void testRandomKeyInCsrfSessionToken() {
		assertThat(myfacesProperties.getRandomKeyInCsrfSessionToken()).isEqualTo("none");
	}

	public void testRandomKeyInCsrfSessionTokenLength() {
		assertThat(myfacesProperties.getRandomKeyInCsrfSessionTokenLength()).isEqualTo(16);
	}

	public void testRandomKeyInCsrfSessionTokenSecureRandomClass() {
		assertThat(myfacesProperties.getRandomKeyInCsrfSessionTokenSecureRandomClass()).isEqualTo("mySecureRamdomClass");
	}
    
	public void testRandomKeyInCsrfSessionTokenSecureRandomProvider() {
		assertThat(myfacesProperties.getRandomKeyInCsrfSessionTokenSecureRandomProvider()).isEqualTo("mySecureRamdomProvider");
	}

	public void testRandomKeyInCsrfSessionTokenSecureRandomAlgoritm() {
		assertThat(myfacesProperties.getRandomKeyInCsrfSessionTokenSecureRandomAlgoritm()).isEqualTo(SHA1PRNG);
	}

	public void testClientViewStateTimeout() {
		assertThat(myfacesProperties.getClientViewStateTimeout()).isEqualTo(0);
	}

	public void testCompressStateInSession() {
		assertThat(myfacesProperties.getCompressStateInSession()).isTrue();
	}

	public void testUseFlashScopePurgeViewsInSession() {
		assertThat(myfacesProperties.getUseFlashScopePurgeViewsInSession()).isTrue();
	}

	public void testRandomKeyInViewStateSessionToken() {
		assertThat(myfacesProperties.getRandomKeyInViewStateSessionToken()).isEqualTo("myStateSessionToken");
	}

	public void testRandomKeyInViewStateSessionTokenLength() {
		assertThat(myfacesProperties.getRandomKeyInViewStateSessionTokenLength()).isEqualTo(8);
	}

	public void testRandomKeyInViewStateSessionTokenSecureRandomClass() {
		assertThat(myfacesProperties.getRandomKeyInViewStateSessionTokenSecureRandomClass()).isEqualTo("mySecureRandomClass");
	}

	public void testRandomKeyInViewStateSessionTokenSecureRandomProvider() {
		assertThat(myfacesProperties.getRandomKeyInViewStateSessionTokenSecureRandomProvider()).isEqualTo("mySecureRandomProvider");
	}

	public void testRandomKeyInViewStateSessionTokenSecureRandomAlgorithm() {
		assertThat(myfacesProperties.getRandomKeyInViewStateSessionTokenSecureRandomAlgorithm()).isEqualTo(SHA1PRNG);
	}

	public void testAutocompleteOffViewState() {
		assertThat(myfacesProperties.getAutocompleteOffViewState()).isTrue();
	}

	public void testCheckIdProductionMode() {
		assertThat(myfacesProperties.getCheckIdProductionMode()).isEqualTo(AUTO);
	}

}
