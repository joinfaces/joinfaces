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

package org.joinfaces.omnifaces;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.omnifaces.component.output.cache.DefaultCacheProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(
		properties = "spring.profiles.active=propertyTest",
		webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
public class OmnifacesPropertiesIT {

	@Autowired
	private OmnifacesProperties omnifacesProperties;

	@Test
	public void testCacheProvider() {
		assertThat(this.omnifacesProperties.getCacheProvider()).isEqualTo(DefaultCacheProvider.class);
	}

	@Test
	public void testDefaultcache() {
		assertThat(this.omnifacesProperties.getDefaultcache()).isEqualTo("myCache");
	}

	@Test
	public void testExceptionTypesToUnwrap() {
		assertThat(this.omnifacesProperties.getExceptionTypesToUnwrap()).containsExactly(IllegalStateException.class);
	}

	@Test
	public void testFacesViewsDispatchMethod() {
		assertThat(this.omnifacesProperties.getFacesViewsDispatchMethod()).isEqualTo("myFacesMethod");
	}

	@Test
	public void testFacesViewsEnabled() {
		assertThat(this.omnifacesProperties.getFacesViewsEnabled()).isTrue();
	}

	@Test
	public void testFacesViewsExtensionAction() {
		assertThat(this.omnifacesProperties.getFacesViewsExtensionAction()).isEqualTo("extensionAction");
	}

	@Test
	public void testFacesViewsFilterAfterDeclaredFilters() {
		assertThat(this.omnifacesProperties.getFacesViewsFilterAfterDeclaredFilters()).isEqualTo("myView");
	}

	@Test
	public void testFacesViewsPathAction() {
		assertThat(this.omnifacesProperties.getFacesViewsPathAction()).isEqualTo("pathAction");
	}

	@Test
	public void testFacesViewsScanPaths() {
		assertThat(this.omnifacesProperties.getFacesViewsScanPaths()).containsExactly("scanPath");
	}

	@Test
	public void testFacesViewsScannedViewsAlwaysExtensionless() {
		assertThat(this.omnifacesProperties.getFacesViewsScannedViewsAlwaysExtensionless()).isTrue();
	}

	@Test
	public void testFacesViewsViewHandlerMode() {
		assertThat(this.omnifacesProperties.getFacesViewsViewHandlerMode()).isEqualTo("myMode");
	}

	@Test
	public void testHtml5RenderKitPassthroughAttributes() {
		assertThat(this.omnifacesProperties.getHtml5RenderKitPassthroughAttributes()).isEqualTo("myAttribute");
	}

	@Test
	public void testCdnResourceHandlerDisabled() {
		assertThat(this.omnifacesProperties.getCdnResourceHandlerDisabled()).isTrue();
	}

	@Test
	public void testCdnResourceHandlerUrls() {
		assertThat(this.omnifacesProperties.getCdnResourceHandlerUrls()).isEqualTo("myurl");
	}

	@Test
	public void testCombinedResourceHandlerCacheTtl() {
		assertThat(this.omnifacesProperties.getCombinedResourceHandlerCacheTtl()).isEqualTo(15);
	}

	@Test
	public void testCombinedResourceHandlerDisabled() {
		assertThat(this.omnifacesProperties.getCombinedResourceHandlerDisabled()).isTrue();
	}

	@Test
	public void testCombinedResourceHandlerExcludedResources() {
		assertThat(this.omnifacesProperties.getCombinedResourceHandlerExcludedResources()).containsExactly("excludedResource");
	}

	@Test
	public void testCombinedResourceHandlerInlineCss() {
		assertThat(this.omnifacesProperties.getCombinedResourceHandlerInlineCss()).isTrue();
	}

	@Test
	public void testCombinedResourceHandlerInlineJs() {
		assertThat(this.omnifacesProperties.getCombinedResourceHandlerInlineJs()).isTrue();
	}

	@Test
	public void testCombinedResourceHandlerSuppressedResources() {
		assertThat(this.omnifacesProperties.getCombinedResourceHandlerSuppressedResources()).containsExactly("suppressedResource");
	}

	@Test
	public void testCacheApplicationMaxCapacity() {
		assertThat(this.omnifacesProperties.getCacheSettingApplicationMaxCapacity()).isEqualTo(10);
	}

	@Test
	public void testCacheApplicationTtl() {
		assertThat(this.omnifacesProperties.getCacheSettingApplicationTtl()).isEqualTo(11);
	}

	@Test
	public void testCacheSessionMaxCapacity() {
		assertThat(this.omnifacesProperties.getCacheSettingSessionMaxCapacity()).isEqualTo(12);
	}

	@Test
	public void testCacheSessionTtl() {
		assertThat(this.omnifacesProperties.getCacheSettingSessionTtl()).isEqualTo(13);
	}

}
