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

package org.joinfaces.javaxfaces;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JavaxFacesSpringBootAutoConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class JavaxFacesPropertiesIT {

	@Autowired
	private JavaxFacesProperties javaxFacesProperties;

	@Test
	public void testProjectState() {
		assertThat(this.javaxFacesProperties.getProjectStage()).isEqualTo("Development");
	}

	@Test
	public void testResourceExcludes() {
		assertThat(this.javaxFacesProperties.getResourceExcludes()).isEqualTo("myExcludes");
	}

	@Test
	public void testWebappContractsDirectory() {
		assertThat(this.javaxFacesProperties.getWebappContractsDirectory()).isEqualTo("myContractsDirectory");
	}

	@Test
	public void testWebappResourcesDirectory() {
		assertThat(this.javaxFacesProperties.getWebappResourcesDirectory()).isEqualTo("myResourcesDirectory");
	}

	@Test
	public void testFullStateSavingViewIds() {
		assertThat(this.javaxFacesProperties.getFullStateSavingViewIds()).isEqualTo("myIds");
	}

	@Test
	public void testPartialStateSaving() {
		assertThat(this.javaxFacesProperties.getPartialStateSaving()).isTrue();
	}

	@Test
	public void testSerializeServerState() {
		assertThat(this.javaxFacesProperties.getSerializeServerState()).isTrue();
	}

	@Test
	public void testStateSavingMethod() {
		assertThat(this.javaxFacesProperties.getStateSavingMethod()).isEqualTo("server");
	}

	@Test
	public void testDefaultSuffix() {
		assertThat(this.javaxFacesProperties.getDefaultSuffix()).isEqualTo(".xhtml");
	}

	@Test
	public void testDisableFaceletJsfViewhandler() {
		assertThat(this.javaxFacesProperties.getDisableFaceletJsfViewhandler()).isTrue();
	}

	@Test
	public void testFaceletsBufferSize() {
		assertThat(this.javaxFacesProperties.getFaceletsBufferSize()).isEqualTo(33);
	}

	@Test
	public void testFaceletsDecorators() {
		assertThat(this.javaxFacesProperties.getFaceletsDecorators()).isEqualTo("myDecorator");
	}

	@Test
	public void testFaceletsLibraries() {
		assertThat(this.javaxFacesProperties.getFaceletsLibraries()).isEqualTo("myLibrary");
	}

	@Test
	public void testFaceletsRefreshPeriod() {
		assertThat(this.javaxFacesProperties.getFaceletsRefreshPeriod()).isEqualTo(35);
	}

	@Test
	public void testFaceletsSkipComments() {
		assertThat(this.javaxFacesProperties.getFaceletsSkipComments()).isTrue();
	}

	@Test
	public void testFaceletsSuffix() {
		assertThat(this.javaxFacesProperties.getFaceletsSuffix()).isEqualTo(".html");
	}

	@Test
	public void testFaceletsViewMappings() {
		assertThat(this.javaxFacesProperties.getFaceletsViewMappings()).isEqualTo("newMapping");
	}

	@Test
	public void testHonorCurrentComponentAttributes() {
		assertThat(this.javaxFacesProperties.getHonorCurrentComponentAttributes()).isTrue();
	}

	@Test
	public void testValidateEmptyFields() {
		assertThat(this.javaxFacesProperties.getValidateEmptyFields()).isEqualTo("auto");
	}

	@Test
	public void testSeparatorChar() {
		assertThat(this.javaxFacesProperties.getSeparatorChar()).isEqualTo(";");
	}

	@Test
	public void testPartialExecute() {
		assertThat(this.javaxFacesProperties.getPartial().getExecute()).isTrue();
	}

	@Test
	public void testPartialRender() {
		assertThat(this.javaxFacesProperties.getPartial().getRender()).isTrue();
	}

	@Test
	public void testPartialResetValues() {
		assertThat(this.javaxFacesProperties.getPartial().getResetValues()).isTrue();
	}

	@Test
	public void testDatetimeconverterDefaultTimezoneIsSystemTimezone() {
		assertThat(this.javaxFacesProperties.getDatetimeconverterDefaultTimezoneIsSystemTimezone()).isTrue();
	}

	@Test
	public void testFlowNullFlow() {
		assertThat(this.javaxFacesProperties.getFlow().getNullFlow()).isTrue();
	}

	@Test
	public void testValidatorDisableDefaultBeanValidator() {
		assertThat(this.javaxFacesProperties.getValidator().getDisableDefaultBeanValidator()).isTrue();
	}

	@Test
	public void testFaceletsResourceResolver() {
		assertThat(this.javaxFacesProperties.getFaceletsResourceResolver()).isEqualTo("myResolver");
	}

	@Test
	public void testConfigFiles() {
		assertThat(this.javaxFacesProperties.getConfigFiles()).isEqualTo("myConfig");
	}

	@Test
	public void testLifecycleId() {
		assertThat(this.javaxFacesProperties.getLifecycleId()).isEqualTo("myId");
	}

	@Test
	public void testClientWindowMode() {
		assertThat(this.javaxFacesProperties.getClientWindowMode()).isEqualTo("url");
	}

	@Test
	public void testInterpretEmptyStringSubmittedValuesAsNull() {
		assertThat(this.javaxFacesProperties.getInterpretEmptyStringSubmittedValuesAsNull()).isTrue();
	}

}
