/*
 * Copyright 2016-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.persapiens.jsfboot.omnifaces;

import org.testng.annotations.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringApplicationConfiguration(classes = OmnifacesSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class OmnifacesPropertiesIT extends AbstractTestNGSpringContextTests {

	@Autowired
	private OmnifacesProperties omnifacesProperties;

	public void testCacheProvider() {
		assertThat(this.omnifacesProperties.getCacheProvider()).isEqualTo("myCacheProvider");
	}

	public void testDefaultcache() {
		assertThat(this.omnifacesProperties.getDefaultcache()).isEqualTo("myCache");
	}

	public void testExceptionTypesToUnwrap() {
		assertThat(this.omnifacesProperties.getExceptionTypesToUnwrap()).isEqualTo("myException");
	}

	public void testFacesViewsDispatchMethod() {
		assertThat(this.omnifacesProperties.getFacesViewsDispatchMethod()).isEqualTo("myFacesMethod");
	}

	public void testFacesViewsEnabled() {
		assertThat(this.omnifacesProperties.getFacesViewsEnabled()).isTrue();
	}

	public void testFacesViewsExtensionAction() {
		assertThat(this.omnifacesProperties.getFacesViewsExtensionAction()).isEqualTo("extensionAction");
	}

	public void testFacesViewsFilterAfterDeclaredFilters() {
		assertThat(this.omnifacesProperties.getFacesViewsFilterAfterDeclaredFilters()).isEqualTo("myView");
	}

	public void testFacesViewsPathAction() {
		assertThat(this.omnifacesProperties.getFacesViewsPathAction()).isEqualTo("pathAction");
	}

	public void testFacesViewsScanPaths() {
		assertThat(this.omnifacesProperties.getFacesViewsScanPaths()).isEqualTo("scanPath");
	}

	public void testFacesViewsScannedViewsAlwaysExtensionless() {
		assertThat(this.omnifacesProperties.getFacesViewsScannedViewsAlwaysExtensionless()).isTrue();
	}

	public void testFacesViewsViewHandlerMode() {
		assertThat(this.omnifacesProperties.getFacesViewsViewHandlerMode()).isEqualTo("myMode");
	}

	public void testHtml5RenderKitPassthroughAttributes() {
		assertThat(this.omnifacesProperties.getHtml5RenderKitPassthroughAttributes()).isEqualTo("myAttribute");
	}

	public void testCdnResourceHandlerDisabled() {
		assertThat(this.omnifacesProperties.getCdnResourceHandlerDisabled()).isTrue();
	}

	public void testCdnResourceHandlerUrls() {
		assertThat(this.omnifacesProperties.getCdnResourceHandlerUrls()).isEqualTo("myurl");
	}

	public void testCombinedResourceHandlerCacheTtl() {
		assertThat(this.omnifacesProperties.getCombinedResourceHandlerCacheTtl()).isEqualTo(15);
	}

	public void testCombinedResourceHandlerDisabled() {
		assertThat(this.omnifacesProperties.getCombinedResourceHandlerDisabled()).isTrue();
	}

	public void testCombinedResourceHandlerExcludedResources() {
		assertThat(this.omnifacesProperties.getCombinedResourceHandlerExcludedResources()).isEqualTo("excludedResource");
	}

	public void testCombinedResourceHandlerInlineCss() {
		assertThat(this.omnifacesProperties.getCombinedResourceHandlerInlineCss()).isTrue();
	}

	public void testCombinedResourceHandlerInlineJs() {
		assertThat(this.omnifacesProperties.getCombinedResourceHandlerInlineJs()).isTrue();
	}

	public void testCombinedResourceHandlerSuppressedResources() {
		assertThat(this.omnifacesProperties.getCombinedResourceHandlerSuppressedResources()).isEqualTo("suppressedResource");
	}

	public void testCacheApplicationMaxCapacity() {
		assertThat(this.omnifacesProperties.getCache().getApplicationMaxCapacity()).isEqualTo(10);
	}

	public void testCacheApplicationTtl() {
		assertThat(this.omnifacesProperties.getCache().getApplicationTtl()).isEqualTo(11);
	}

	public void testCacheSessionMaxCapacity() {
		assertThat(this.omnifacesProperties.getCache().getSessionMaxCapacity()).isEqualTo(12);
	}

	public void testCacheSessionTtl() {
		assertThat(this.omnifacesProperties.getCache().getSessionTtl()).isEqualTo(13);
	}

}
