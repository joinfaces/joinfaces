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

package org.joinfaces.myfaces;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(
		properties = "spring.profiles.active=propertyTest",
		webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
public class MyfacesPropertiesOtherIT {

	@Autowired
	private MyfacesProperties myfacesProperties;

	@Test
	public void testConfigRefreshPeriod() {
		assertThat(this.myfacesProperties.getConfigRefreshPeriod()).isEqualTo(2);
	}

	@Test
	public void testValidateXml() {
		assertThat(this.myfacesProperties.getValidateXml()).isTrue();
	}

	@Test
	public void testDebugPhaseListener() {
		assertThat(this.myfacesProperties.getDebugPhaseListener()).isTrue();
	}

	@Test
	public void testStrictJsf2RefreshTargetAjax() {
		assertThat(this.myfacesProperties.getStrictJsf2RefreshTargetAjax()).isTrue();
	}

	@Test
	public void testGaeJsfJarFiles() {
		assertThat(this.myfacesProperties.getGaeJsfJarFiles()).isEqualTo("myjar.jar");
	}

	@Test
	public void testGaeJsfAnnotationsJarFiles() {
		assertThat(this.myfacesProperties.getGaeJsfAnnotationsJarFiles()).isEqualTo("myAnnotationJar.jar");
	}

	@Test
	public void testFlashScopeDisabled() {
		assertThat(this.myfacesProperties.getFlashScopeDisabled()).isTrue();
	}

	@Test
	public void testLazyLoadConfigObjects() {
		assertThat(this.myfacesProperties.getLazyLoadConfigObjects()).isTrue();
	}

	@Test
	public void testValidate() {
		assertThat(this.myfacesProperties.getValidate()).isTrue();
	}

	@Test
	public void testInitializeSkipJarFacesConfigScan() {
		assertThat(this.myfacesProperties.getInitializeSkipJarFacesConfigScan()).isTrue();
	}

	@Test
	public void testDefaultWindowMode() {
		assertThat(this.myfacesProperties.getDefaultWindowMode()).isEqualTo("url");
	}

	@Test
	public void testErrorTemplateResource() {
		assertThat(this.myfacesProperties.getErrorTemplateResource()).isEqualTo("META-INF/rsc/myfaces-dev-error.xml");
	}

	@Test
	public void testDebugTemplateResource() {
		assertThat(this.myfacesProperties.getDebugTemplateResource()).isEqualTo("META-INF/rsc/myfaces-dev-debug.xml");
	}

	@Test
	public void testErrorHandling() {
		assertThat(this.myfacesProperties.getErrorHandling()).isTrue();
	}

	@Test
	public void testTemporalResourcehandlerCacheEnabled() {
		assertThat(this.myfacesProperties.getTemporalResourcehandlerCacheEnabled()).isTrue();
	}

	@Test
	public void testServiceProviderFinder() {
		assertThat(this.myfacesProperties.getServiceProviderFinder()).isNotNull();
	}

	@Test
	public void testSpiInjectionProvider() {
		assertThat(this.myfacesProperties.getSpi().getInjectionProvider()).isEqualTo("myInjectionProvider");
	}

	@Test
	public void testMarkInitialStateWhenApplyBuildView() {
		assertThat(this.myfacesProperties.getMarkInitialStateWhenApplyBuildView()).isTrue();
	}

	@Test
	public void testWrapTagExceptionsAsContextAware() {
		assertThat(this.myfacesProperties.getWrapTagExceptionsAsContextAware()).isTrue();
	}

	@Test
	public void testViewPoolMaxPoolSize() {
		assertThat(this.myfacesProperties.getViewPoolMaxPoolSize()).isEqualTo(5);
	}

	@Test
	public void testViewPoolMaxDynamicPartialLimit() {
		assertThat(this.myfacesProperties.getViewPoolMaxDynamicPartialLimit()).isEqualTo(2);
	}

	@Test
	public void testViewPoolEntryMode() {
		assertThat(this.myfacesProperties.getViewPoolEntryMode()).isEqualTo("soft");
	}

	@Test
	public void testViewPoolDeferredNavigation() {
		assertThat(this.myfacesProperties.getViewPoolDeferredNavigation()).isTrue();
	}

	@Test
	public void testLogWebContextParams() {
		assertThat(this.myfacesProperties.getLogWebContextParams()).isEqualTo("auto");
	}

	@Test
	public void testAnnotationUseCdiForAnnotationScanning() {
		assertThat(this.myfacesProperties.getAnnotation().getUseCdiForAnnotationScanning()).isTrue();
	}

	@Test
	public void testAnnotationScanPackages() {
		assertThat(this.myfacesProperties.getAnnotation().getScanPackages()).isEqualTo("myScanPackages");
	}

	@Test
	public void testInitializeAlwaysStandalone() {
		assertThat(this.myfacesProperties.getInitializeAlwaysStandalone()).isFalse();
	}

}
