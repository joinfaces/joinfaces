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
public class MyfacesPropertiesOtherIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private MyfacesProperties myfacesProperties;

	public void testConfigRefreshPeriod() {
		assertThat(myfacesProperties.getConfigRefreshPeriod()).isEqualTo(2);
	}

	public void testDelegateFacesServlet() {
		assertThat(myfacesProperties.getDelegateFacesServlet()).isEqualTo("myFacesServlet");
	}

	public void testValidateXml() {
		assertThat(myfacesProperties.getValidateXml()).isTrue();
	}

	public void testDebugPhaseListener() {
		assertThat(myfacesProperties.getDebugPhaseListener()).isTrue();
	}

	public void testStrictJsf2RefreshTargetAjax() {
		assertThat(myfacesProperties.getStrictJsf2RefreshTargetAjax()).isTrue();
	}

	public void testGaeJsfJarFiles() {
		assertThat(myfacesProperties.getGaeJsfJarFiles()).isEqualTo("myjar.jar");
	}

	public void testGaeJsfAnnotationsJarFiles() {
		assertThat(myfacesProperties.getGaeJsfAnnotationsJarFiles()).isEqualTo("myAnnotationJar.jar");
	}

	public void testFlashScopeDisabled() {
		assertThat(myfacesProperties.getFlashScopeDisabled()).isTrue();
	}

	public void testLazyLoadConfigObjects() {
		assertThat(myfacesProperties.getLazyLoadConfigObjects()).isTrue();
	}

	public void testValidate() {
		assertThat(myfacesProperties.getValidate()).isTrue();
	}

	public void testInitializeSkipJarFacesConfigScan() {
		assertThat(myfacesProperties.getInitializeSkipJarFacesConfigScan()).isTrue();
	}

	public void testDefaultWindowMode() {
		assertThat(myfacesProperties.getDefaultWindowMode()).isEqualTo("url");
	}

	public void testErrorTemplateResource() {
		assertThat(myfacesProperties.getErrorTemplateResource()).isEqualTo("META-INF/rsc/myfaces-dev-error.xml");
	}

	public void testDebugTemplateResource() {
		assertThat(myfacesProperties.getDebugTemplateResource()).isEqualTo("META-INF/rsc/myfaces-dev-debug.xml");
	}

	public void testErrorHandling() {
		assertThat(myfacesProperties.getErrorHandling()).isTrue();
	}

	public void testTemporalResourcehandlerCacheEnabled() {
		assertThat(myfacesProperties.getTemporalResourcehandlerCacheEnabled()).isTrue();
	}

	public void testServiceProviderFinder() {
		assertThat(myfacesProperties.getServiceProviderFinder()).isTrue();
	}

	public void testSpiInjectionProvider() {
		assertThat(myfacesProperties.getSpi().getInjectionProvider()).isEqualTo("myInjectionProvider");
	}

	public void testMarkInitialStateWhenApplyBuildView() {
		assertThat(myfacesProperties.getMarkInitialStateWhenApplyBuildView()).isTrue();
	}

	public void testWrapTagExceptionsAsContextAware() {
		assertThat(myfacesProperties.getWrapTagExceptionsAsContextAware()).isTrue();
	}

	public void testViewPoolMaxPoolSize() {
		assertThat(myfacesProperties.getViewPoolMaxPoolSize()).isEqualTo(5);
	}

	public void testViewPoolMaxDynamicPartialLimit() {
		assertThat(myfacesProperties.getViewPoolMaxDynamicPartialLimit()).isEqualTo(2);
	}

	public void testViewPoolEntryMode() {
		assertThat(myfacesProperties.getViewPoolEntryMode()).isEqualTo("soft");
	}

	public void testViewPoolDeferredNavigation() {
		assertThat(myfacesProperties.getViewPoolDeferredNavigation()).isTrue();
	}

	public void testLogWebContextParams() {
		assertThat(myfacesProperties.getLogWebContextParams()).isEqualTo("auto");
	}

	public void testFacesInitializer() {
		assertThat(myfacesProperties.getFacesInitializer()).isEqualTo("myFacesInitializer");
	}

	public void testFacesInitPlugins() {
		assertThat(myfacesProperties.getFacesInitPlugins()).isEqualTo("myInitPlugin");
	}

	public void testAnnotationUseCdiForAnnotationScanning() {
		assertThat(myfacesProperties.getAnnotation().getUseCdiForAnnotationScanning()).isTrue();
	}

	public void testAnnotationScanPackages() {
		assertThat(myfacesProperties.getAnnotation().getScanPackages()).isEqualTo("myScanPackages");
	}

	public void testInitializeAlwaysStandalone() {
		assertThat(myfacesProperties.getInitializeAlwaysStandalone()).isFalse();
	}

}
