package com.github.persapiens.jsfboot.mojarra;

import com.github.persapiens.jsfboot.mojarra.MojarraProperties;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class MojarraPropertiesTest {

	public void testForceLoadConfiguration() {
		MojarraProperties mojarraProperties = new MojarraProperties();
        mojarraProperties.setForceLoadConfiguration(true);

		assertThat(mojarraProperties.getForceLoadConfiguration()).isTrue();
	}

}
