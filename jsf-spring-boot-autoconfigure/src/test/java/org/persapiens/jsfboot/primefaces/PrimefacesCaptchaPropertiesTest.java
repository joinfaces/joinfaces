package org.persapiens.jsfboot.primefaces;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class PrimefacesCaptchaPropertiesTest {

	public void testTheme() {
		PrimefacesCaptchaProperties primefacesCaptchaProperties = new PrimefacesCaptchaProperties();
        primefacesCaptchaProperties.setPrivateKey("myPrivateKey");

		assertThat(primefacesCaptchaProperties.getPrivateKey()).isEqualTo("myPrivateKey");
	}

}
