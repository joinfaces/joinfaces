package com.github.persapiens.jsfboot.myfaces;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class MyfacesPropertiesOtherTest {

	public void testConfigRefreshPeriod() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setConfigRefreshPeriod(2);

		assertThat(myfacesProperties.getConfigRefreshPeriod()).isEqualTo(2);
	}

	public void testDelegateFacesServlet() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setDelegateFacesServlet("myFacesServlet");

		assertThat(myfacesProperties.getDelegateFacesServlet()).isEqualTo("myFacesServlet");
	}

	public void testValidateXml() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setValidateXml(true);

		assertThat(myfacesProperties.getValidateXml()).isTrue();
	}

	public void testDebugPhaseListener() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setDebugPhaseListener(true);

		assertThat(myfacesProperties.getDebugPhaseListener()).isTrue();
	}

	public void testStrictJsf2RefreshTargetAjax() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setStrictJsf2RefreshTargetAjax(true);

		assertThat(myfacesProperties.getStrictJsf2RefreshTargetAjax()).isTrue();
	}

	public void testGaeJsfJarFiles() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setGaeJsfJarFiles("myjar.jar");

		assertThat(myfacesProperties.getGaeJsfJarFiles()).isEqualTo("myjar.jar");
	}

	public void testGaeJsfAnnotationsJarFiles() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setGaeJsfAnnotationsJarFiles("myAnnotationJar.jar");

		assertThat(myfacesProperties.getGaeJsfAnnotationsJarFiles()).isEqualTo("myAnnotationJar.jar");
	}

	public void testFlashScopeDisabled() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setFlashScopeDisabled(true);

		assertThat(myfacesProperties.getFlashScopeDisabled()).isTrue();
	}

	public void testLazyLoadConfigObjects() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setLazyLoadConfigObjects(true);

		assertThat(myfacesProperties.getLazyLoadConfigObjects()).isTrue();
	}

	public void testValidate() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setValidate(true);

		assertThat(myfacesProperties.getValidate()).isTrue();
	}

	public void testInitializeSkipJarFacesConfigScan() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setInitializeSkipJarFacesConfigScan(true);

		assertThat(myfacesProperties.getInitializeSkipJarFacesConfigScan()).isTrue();
	}

	public void testDefaultWindowMode() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setDefaultWindowMode("url");

		assertThat(myfacesProperties.getDefaultWindowMode()).isEqualTo("url");
	}

	public void testErrorTemplateResource() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setErrorTemplateResource("META-INF/rsc/myfaces-dev-error.xml");

		assertThat(myfacesProperties.getErrorTemplateResource()).isEqualTo("META-INF/rsc/myfaces-dev-error.xml");
	}

	public void testDebugTemplateResource() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setDebugTemplateResource("META-INF/rsc/myfaces-dev-debug.xml");

		assertThat(myfacesProperties.getDebugTemplateResource()).isEqualTo("META-INF/rsc/myfaces-dev-debug.xml");
	}

	public void testErrorHandling() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setErrorHandling(true);

		assertThat(myfacesProperties.getErrorHandling()).isTrue();
	}

	public void testTemporalResourcehandlerCacheEnabled() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setTemporalResourcehandlerCacheEnabled(true);

		assertThat(myfacesProperties.getTemporalResourcehandlerCacheEnabled()).isTrue();
	}

	public void testServiceProviderFinder() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setServiceProviderFinder(true);

		assertThat(myfacesProperties.getServiceProviderFinder()).isTrue();
	}

	public void testSpiInjectionProvider() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.getSpi().setInjectionProvider("myInjectionProvider");

		assertThat(myfacesProperties.getSpi().getInjectionProvider()).isEqualTo("myInjectionProvider");
	}

	public void testMarkInitialStateWhenApplyBuildView() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setMarkInitialStateWhenApplyBuildView(true);

		assertThat(myfacesProperties.getMarkInitialStateWhenApplyBuildView()).isTrue();
	}

	public void testWrapTagExceptionsAsContextAware() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setWrapTagExceptionsAsContextAware(true);

		assertThat(myfacesProperties.getWrapTagExceptionsAsContextAware()).isTrue();
	}

	public void testViewPoolMaxPoolSize() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setViewPoolMaxPoolSize(5);

		assertThat(myfacesProperties.getViewPoolMaxPoolSize()).isEqualTo(5);
	}

	public void testViewPoolMaxDynamicPartialLimit() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setViewPoolMaxDynamicPartialLimit(2);

		assertThat(myfacesProperties.getViewPoolMaxDynamicPartialLimit()).isEqualTo(2);
	}

	public void testViewPoolEntryMode() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setViewPoolEntryMode("soft");

		assertThat(myfacesProperties.getViewPoolEntryMode()).isEqualTo("soft");
	}

	public void testViewPoolDeferredNavigation() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setViewPoolDeferredNavigation(true);

		assertThat(myfacesProperties.getViewPoolDeferredNavigation()).isTrue();
	}

	public void testLogWebContextParams() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setLogWebContextParams("auto");

		assertThat(myfacesProperties.getLogWebContextParams()).isEqualTo("auto");
	}

	public void testFacesInitializer() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setFacesInitializer("myFacesInitializer");

		assertThat(myfacesProperties.getFacesInitializer()).isEqualTo("myFacesInitializer");
	}

	public void testFacesInitPlugins() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setFacesInitPlugins("myInitPlugin");

		assertThat(myfacesProperties.getFacesInitPlugins()).isEqualTo("myInitPlugin");
	}

	public void testAnnotationUseCdiForAnnotationScanning() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.getAnnotation().setUseCdiForAnnotationScanning(true);

		assertThat(myfacesProperties.getAnnotation().getUseCdiForAnnotationScanning()).isTrue();
	}

	public void testAnnotationScanPackages() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.getAnnotation().setScanPackages("myScanPackages");

		assertThat(myfacesProperties.getAnnotation().getScanPackages()).isEqualTo("myScanPackages");
	}

	public void testInitializeAlwaysStandalone() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setInitializeAlwaysStandalone(false);

		assertThat(myfacesProperties.getInitializeAlwaysStandalone()).isEqualTo("myExpressionFactory");
	}

}
