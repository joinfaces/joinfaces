package com.github.persapiens.jsfboot.omnifaces;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class OmnifacesPropertiesTest {

	public void testCacheProvider() {
		OmnifacesProperties omnifacesProperties = new OmnifacesProperties();
        omnifacesProperties.setCacheProvider("myCacheProvider");

		assertThat(omnifacesProperties.getCacheProvider()).isEqualTo("myCacheProvider");
	}
    
	public void testDefaultcache() {
		OmnifacesProperties omnifacesProperties = new OmnifacesProperties();
        omnifacesProperties.setDefaultcache("defaultCache");

		assertThat(omnifacesProperties.getDefaultcache()).isEqualTo("defaultCache");
	}

	public void testExceptionTypesToUnwrap() {
		OmnifacesProperties omnifacesProperties = new OmnifacesProperties();
        omnifacesProperties.setExceptionTypesToUnwrap("myException");

		assertThat(omnifacesProperties.getExceptionTypesToUnwrap()).isEqualTo("myException");
	}

	public void testFacesViewsDispatchMethod() {
		OmnifacesProperties omnifacesProperties = new OmnifacesProperties();
        omnifacesProperties.setFacesViewsDispatchMethod("methodName");

		assertThat(omnifacesProperties.getFacesViewsDispatchMethod()).isEqualTo("methodName");
	}

	public void testFacesViewsEnabled() {
		OmnifacesProperties omnifacesProperties = new OmnifacesProperties();
        omnifacesProperties.setFacesViewsEnabled(true);

		assertThat(omnifacesProperties.getFacesViewsEnabled()).isTrue();
	}

	public void testFacesViewsExtensionAction() {
		OmnifacesProperties omnifacesProperties = new OmnifacesProperties();
        omnifacesProperties.setFacesViewsExtensionAction("extensionAction");

		assertThat(omnifacesProperties.getFacesViewsExtensionAction()).isEqualTo("extensionAction");
	}

	public void testFacesViewsFilterAfterDeclaredFilters() {
		OmnifacesProperties omnifacesProperties = new OmnifacesProperties();
        omnifacesProperties.setFacesViewsFilterAfterDeclaredFilters("myView");

		assertThat(omnifacesProperties.getFacesViewsFilterAfterDeclaredFilters()).isEqualTo("myView");
	}

	public void testFacesViewsPathAction() {
		OmnifacesProperties omnifacesProperties = new OmnifacesProperties();
        omnifacesProperties.setFacesViewsPathAction("pathAction");

		assertThat(omnifacesProperties.getFacesViewsPathAction()).isEqualTo("pathAction");
	}

	public void testFacesViewsScanPaths() {
		OmnifacesProperties omnifacesProperties = new OmnifacesProperties();
        omnifacesProperties.setFacesViewsScanPaths("scanPath");

		assertThat(omnifacesProperties.getFacesViewsScanPaths()).isEqualTo("scanPath");
	}

	public void testFacesViewsScannedViewsAlwaysExtensionless() {
		OmnifacesProperties omnifacesProperties = new OmnifacesProperties();
        omnifacesProperties.setFacesViewsScannedViewsAlwaysExtensionless(true);

		assertThat(omnifacesProperties.getFacesViewsScannedViewsAlwaysExtensionless()).isTrue();
	}

	public void testFacesViewsViewHandlerMode() {
		OmnifacesProperties omnifacesProperties = new OmnifacesProperties();
        omnifacesProperties.setFacesViewsViewHandlerMode("myMode");

		assertThat(omnifacesProperties.getFacesViewsViewHandlerMode()).isEqualTo("myMode");
	}

	public void testHtml5RenderKitPassthroughAttributes() {
		OmnifacesProperties omnifacesProperties = new OmnifacesProperties();
        omnifacesProperties.setHtml5RenderKitPassthroughAttributes("myAttribute");

		assertThat(omnifacesProperties.getHtml5RenderKitPassthroughAttributes()).isEqualTo("myAttribute");
	}

	public void testCdnResourceHandlerDisabled() {
		OmnifacesProperties omnifacesProperties = new OmnifacesProperties();
        omnifacesProperties.setCdnResourceHandlerDisabled(true);

		assertThat(omnifacesProperties.getCdnResourceHandlerDisabled()).isTrue();
	}

	public void testCdnResourceHandlerUrls() {
		OmnifacesProperties omnifacesProperties = new OmnifacesProperties();
        omnifacesProperties.setCdnResourceHandlerUrls("myurl");

		assertThat(omnifacesProperties.getCdnResourceHandlerUrls()).isEqualTo("myurl");
	}
        
	public void testCombinedResourceHandlerCacheTtl() {
		OmnifacesProperties omnifacesProperties = new OmnifacesProperties();
        omnifacesProperties.setCombinedResourceHandlerCacheTtl(15);

		assertThat(omnifacesProperties.getCombinedResourceHandlerCacheTtl()).isEqualTo(15);
	}

	public void testCombinedResourceHandlerDisabled() {
		OmnifacesProperties omnifacesProperties = new OmnifacesProperties();
        omnifacesProperties.setCombinedResourceHandlerDisabled(true);

		assertThat(omnifacesProperties.getCombinedResourceHandlerDisabled()).isTrue();
	}

	public void testCombinedResourceHandlerExcludedResources() {
		OmnifacesProperties omnifacesProperties = new OmnifacesProperties();
        omnifacesProperties.setCombinedResourceHandlerExcludedResources("excludedResource");

		assertThat(omnifacesProperties.getCombinedResourceHandlerExcludedResources()).isEqualTo("excludedResource");
	}

	public void testCombinedResourceHandlerInlineCss() {
		OmnifacesProperties omnifacesProperties = new OmnifacesProperties();
        omnifacesProperties.setCombinedResourceHandlerInlineCss(true);

		assertThat(omnifacesProperties.getCombinedResourceHandlerInlineCss()).isTrue();
	}

	public void testCombinedResourceHandlerInlineJs() {
		OmnifacesProperties omnifacesProperties = new OmnifacesProperties();
        omnifacesProperties.setCombinedResourceHandlerInlineJs(true);

		assertThat(omnifacesProperties.getCombinedResourceHandlerInlineJs()).isTrue();
	}

	public void testCombinedResourceHandlerSuppressedResources() {
		OmnifacesProperties omnifacesProperties = new OmnifacesProperties();
        omnifacesProperties.setCombinedResourceHandlerSuppressedResources("suppressedResource");

		assertThat(omnifacesProperties.getCombinedResourceHandlerSuppressedResources()).isEqualTo("suppressedResource");
	}

	public void testCacheApplicationMaxCapacity() {
		OmnifacesProperties omnifacesProperties = new OmnifacesProperties();
        omnifacesProperties.getCache().setApplicationMaxCapacity(10);

		assertThat(omnifacesProperties.getCache().getApplicationMaxCapacity()).isEqualTo(10);
	}

	public void testCacheApplicationTtl() {
		OmnifacesProperties omnifacesProperties = new OmnifacesProperties();
        omnifacesProperties.getCache().setApplicationTtl(11);

		assertThat(omnifacesProperties.getCache().getApplicationTtl()).isEqualTo(11);
	}

	public void testCacheSessionMaxCapacity() {
		OmnifacesProperties omnifacesProperties = new OmnifacesProperties();
        omnifacesProperties.getCache().setSessionMaxCapacity(12);

		assertThat(omnifacesProperties.getCache().getSessionMaxCapacity()).isEqualTo(12);
	}

	public void testCacheSessionTtl() {
		OmnifacesProperties omnifacesProperties = new OmnifacesProperties();
        omnifacesProperties.getCache().setSessionTtl(13);

		assertThat(omnifacesProperties.getCache().getSessionTtl()).isEqualTo(13);
	}

}
