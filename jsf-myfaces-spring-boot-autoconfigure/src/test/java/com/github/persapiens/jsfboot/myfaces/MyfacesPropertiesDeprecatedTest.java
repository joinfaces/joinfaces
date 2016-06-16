package com.github.persapiens.jsfboot.myfaces;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class MyfacesPropertiesDeprecatedTest {

	public void testErrorHandler() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setErrorHandler("myHandler");

		assertThat(myfacesProperties.getErrorHandler()).isEqualTo("myHandler");
	}

	public void testViewstateJavascript() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setViewstateJavascript(true);

		assertThat(myfacesProperties.getViewstateJavascript()).isTrue();
	}

	public void testSerializeStateInSession() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setSerializeStateInSession(true);

		assertThat(myfacesProperties.getSerializeStateInSession()).isTrue();
	}

	public void testCacheOldViewsInSessionMode() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setCacheOldViewsInSessionMode("off");

		assertThat(myfacesProperties.getCacheOldViewsInSessionMode()).isEqualTo("off");
	}

	public void testHandleStateCachingMechanics() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setHandleStateCachingMechanics(true);

		assertThat(myfacesProperties.getHandleStateCachingMechanics()).isTrue();
	}

	public void testSaveStateWithVisitTreeOnPass() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setSaveStateWithVisitTreeOnPass(true);

		assertThat(myfacesProperties.getSaveStateWithVisitTreeOnPass()).isTrue();
	}

}
