package com.github.persapiens.jsfboot.omnifaces;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringApplicationConfiguration(classes = OmnifacesSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class OmnifacesPropertiesIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private OmnifacesProperties omnifacesProperties;

	public void testCacheProvider() {
		assertThat(omnifacesProperties.getCacheProvider()).isEqualTo("myCacheProvider");
	}
    
	public void testDefaultcache() {
		assertThat(omnifacesProperties.getDefaultcache()).isEqualTo("myCache");
	}

	public void testExceptionTypesToUnwrap() {
		assertThat(omnifacesProperties.getExceptionTypesToUnwrap()).isEqualTo("myException");
	}

	public void testFacesViewsDispatchMethod() {
		assertThat(omnifacesProperties.getFacesViewsDispatchMethod()).isEqualTo("myFacesMethod");
	}

	public void testFacesViewsEnabled() {
		assertThat(omnifacesProperties.getFacesViewsEnabled()).isTrue();
	}

	public void testFacesViewsExtensionAction() {
		assertThat(omnifacesProperties.getFacesViewsExtensionAction()).isEqualTo("extensionAction");
	}

	public void testFacesViewsFilterAfterDeclaredFilters() {
		assertThat(omnifacesProperties.getFacesViewsFilterAfterDeclaredFilters()).isEqualTo("myView");
	}

	public void testFacesViewsPathAction() {
		assertThat(omnifacesProperties.getFacesViewsPathAction()).isEqualTo("pathAction");
	}

	public void testFacesViewsScanPaths() {
		assertThat(omnifacesProperties.getFacesViewsScanPaths()).isEqualTo("scanPath");
	}

	public void testFacesViewsScannedViewsAlwaysExtensionless() {
		assertThat(omnifacesProperties.getFacesViewsScannedViewsAlwaysExtensionless()).isTrue();
	}

	public void testFacesViewsViewHandlerMode() {
		assertThat(omnifacesProperties.getFacesViewsViewHandlerMode()).isEqualTo("myMode");
	}

	public void testHtml5RenderKitPassthroughAttributes() {
		assertThat(omnifacesProperties.getHtml5RenderKitPassthroughAttributes()).isEqualTo("myAttribute");
	}

	public void testCdnResourceHandlerDisabled() {
		assertThat(omnifacesProperties.getCdnResourceHandlerDisabled()).isTrue();
	}

	public void testCdnResourceHandlerUrls() {
		assertThat(omnifacesProperties.getCdnResourceHandlerUrls()).isEqualTo("myurl");
	}
        
	public void testCombinedResourceHandlerCacheTtl() {
		assertThat(omnifacesProperties.getCombinedResourceHandlerCacheTtl()).isEqualTo(15);
	}

	public void testCombinedResourceHandlerDisabled() {
		assertThat(omnifacesProperties.getCombinedResourceHandlerDisabled()).isTrue();
	}

	public void testCombinedResourceHandlerExcludedResources() {
		assertThat(omnifacesProperties.getCombinedResourceHandlerExcludedResources()).isEqualTo("excludedResource");
	}

	public void testCombinedResourceHandlerInlineCss() {
		assertThat(omnifacesProperties.getCombinedResourceHandlerInlineCss()).isTrue();
	}

	public void testCombinedResourceHandlerInlineJs() {
		assertThat(omnifacesProperties.getCombinedResourceHandlerInlineJs()).isTrue();
	}

	public void testCombinedResourceHandlerSuppressedResources() {
		assertThat(omnifacesProperties.getCombinedResourceHandlerSuppressedResources()).isEqualTo("suppressedResource");
	}

	public void testCacheApplicationMaxCapacity() {
		assertThat(omnifacesProperties.getCache().getApplicationMaxCapacity()).isEqualTo(10);
	}

	public void testCacheApplicationTtl() {
		assertThat(omnifacesProperties.getCache().getApplicationTtl()).isEqualTo(11);
	}

	public void testCacheSessionMaxCapacity() {
		assertThat(omnifacesProperties.getCache().getSessionMaxCapacity()).isEqualTo(12);
	}

	public void testCacheSessionTtl() {
		assertThat(omnifacesProperties.getCache().getSessionTtl()).isEqualTo(13);
	}

}
