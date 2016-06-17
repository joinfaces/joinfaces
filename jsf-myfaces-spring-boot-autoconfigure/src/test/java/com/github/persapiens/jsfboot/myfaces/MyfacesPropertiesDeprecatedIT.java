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
public class MyfacesPropertiesDeprecatedIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private MyfacesProperties myfacesProperties;

	public void testErrorHandler() {
		assertThat(myfacesProperties.getErrorHandler()).isEqualTo("myHandler");
	}

	public void testViewstateJavascript() {
		assertThat(myfacesProperties.getViewstateJavascript()).isTrue();
	}

	public void testSerializeStateInSession() {
		assertThat(myfacesProperties.getSerializeStateInSession()).isTrue();
	}

	public void testCacheOldViewsInSessionMode() {
		assertThat(myfacesProperties.getCacheOldViewsInSessionMode()).isFalse();
	}

	public void testHandleStateCachingMechanics() {
		assertThat(myfacesProperties.getHandleStateCachingMechanics()).isTrue();
	}

	public void testSaveStateWithVisitTreeOnPass() {
		assertThat(myfacesProperties.getSaveStateWithVisitTreeOnPass()).isTrue();
	}

}
