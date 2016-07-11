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

package com.github.persapiens.jsfboot.javaxfaces;

import org.testng.annotations.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringApplicationConfiguration(classes = JavaxFacesSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class JavaxFacesPropertiesIT extends AbstractTestNGSpringContextTests {

	@Autowired
	private JavaxFacesProperties javaxFacesProperties;

	public void testProjectState() {
		assertThat(this.javaxFacesProperties.getProjectStage()).isEqualTo("Development");
	}

	public void testResourceExcludes() {
		assertThat(this.javaxFacesProperties.getResourceExcludes()).isEqualTo("myExcludes");
	}

	public void testWebappContractsDirectory() {
		assertThat(this.javaxFacesProperties.getWebappContractsDirectory()).isEqualTo("myContractsDirectory");
	}

	public void testWebappResourcesDirectory() {
		assertThat(this.javaxFacesProperties.getWebappResourcesDirectory()).isEqualTo("myResourcesDirectory");
	}

	public void testFullStateSavingViewIds() {
		assertThat(this.javaxFacesProperties.getFullStateSavingViewIds()).isEqualTo("myIds");
	}

	public void testPartialStateSaving() {
		assertThat(this.javaxFacesProperties.getPartialStateSaving()).isTrue();
	}

	public void testSerializeServerState() {
		assertThat(this.javaxFacesProperties.getSerializeServerState()).isTrue();
	}

	public void testStateSavingMethod() {
		assertThat(this.javaxFacesProperties.getStateSavingMethod()).isEqualTo("server");
	}

	public void testDefaultSuffix() {
		assertThat(this.javaxFacesProperties.getDefaultSuffix()).isEqualTo(".xhtml");
	}

	public void testDisableFaceletJsfViewhandler() {
		assertThat(this.javaxFacesProperties.getDisableFaceletJsfViewhandler()).isTrue();
	}

	public void testFaceletsBufferSize() {
		assertThat(this.javaxFacesProperties.getFaceletsBufferSize()).isEqualTo(33);
	}

	public void testFaceletsDecorators() {
		assertThat(this.javaxFacesProperties.getFaceletsDecorators()).isEqualTo("myDecorator");
	}

	public void testFaceletsLibraries() {
		assertThat(this.javaxFacesProperties.getFaceletsLibraries()).isEqualTo("myLibrary");
	}

	public void testFaceletsRefreshPeriod() {
		assertThat(this.javaxFacesProperties.getFaceletsRefreshPeriod()).isEqualTo(35);
	}

	public void testFaceletsSkipComments() {
		assertThat(this.javaxFacesProperties.getFaceletsSkipComments()).isTrue();
	}

	public void testFaceletsSuffix() {
		assertThat(this.javaxFacesProperties.getFaceletsSuffix()).isEqualTo(".html");
	}

	public void testFaceletsViewMappings() {
		assertThat(this.javaxFacesProperties.getFaceletsViewMappings()).isEqualTo("newMapping");
	}

	public void testHonorCurrentComponentAttributes() {
		assertThat(this.javaxFacesProperties.getHonorCurrentComponentAttributes()).isTrue();
	}

	public void testValidateEmptyFields() {
		assertThat(this.javaxFacesProperties.getValidateEmptyFields()).isEqualTo("auto");
	}

	public void testSeparatorChar() {
		assertThat(this.javaxFacesProperties.getSeparatorChar()).isEqualTo(";");
	}

	public void testPartialExecute() {
		assertThat(this.javaxFacesProperties.getPartial().getExecute()).isTrue();
	}

	public void testPartialRender() {
		assertThat(this.javaxFacesProperties.getPartial().getRender()).isTrue();
	}

	public void testPartialResetValues() {
		assertThat(this.javaxFacesProperties.getPartial().getResetValues()).isTrue();
	}

	public void testDatetimeconverterDefaultTimezoneIsSystemTimezone() {
		assertThat(this.javaxFacesProperties.getDatetimeconverterDefaultTimezoneIsSystemTimezone()).isTrue();
	}

	public void testFlowNullFlow() {
		assertThat(this.javaxFacesProperties.getFlow().getNullFlow()).isTrue();
	}

	public void testValidatorDisableDefaultBeanValidator() {
		assertThat(this.javaxFacesProperties.getValidator().getDisableDefaultBeanValidator()).isTrue();
	}

	public void testFaceletsResourceResolver() {
		assertThat(this.javaxFacesProperties.getFaceletsResourceResolver()).isEqualTo("myResolver");
	}

	public void testConfigFiles() {
		assertThat(this.javaxFacesProperties.getConfigFiles()).isEqualTo("myConfig");
	}

	public void testLifecycleId() {
		assertThat(this.javaxFacesProperties.getLifecycleId()).isEqualTo("myId");
	}

	public void testClientWindowMode() {
		assertThat(this.javaxFacesProperties.getClientWindowMode()).isEqualTo("url");
	}

	public void testInterpretEmptyStringSubmittedValuesAsNull() {
		assertThat(this.javaxFacesProperties.getInterpretEmptyStringSubmittedValuesAsNull()).isTrue();
	}

}
