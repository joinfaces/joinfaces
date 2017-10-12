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

import javax.faces.application.ProjectStage;

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
public class JavaxFacesPropertiesIT {

	@Autowired
	private JavaxFaces2_0Properties javaxFaces2_0Properties;

	@Autowired
	private JavaxFaces2_1Properties javaxFaces2_1Properties;

	@Autowired
	private JavaxFaces2_2Properties javaxFaces2_2Properties;

	@Test
	public void testProjectState() {
		assertThat(this.javaxFaces2_0Properties.getProjectStage()).isEqualTo(ProjectStage.Development);
	}

	@Test
	public void testResourceExcludes() {
		assertThat(this.javaxFaces2_0Properties.getResourceExcludes()).containsExactly("myExcludes");
	}

	@Test
	public void testWebappContractsDirectory() {
		assertThat(this.javaxFaces2_2Properties.getWebappContractsDirectory()).isEqualTo("myContractsDirectory");
	}

	@Test
	public void testWebappResourcesDirectory() {
		assertThat(this.javaxFaces2_2Properties.getWebappResourcesDirectory()).isEqualTo("myResourcesDirectory");
	}

	@Test
	public void testFullStateSavingViewIds() {
		assertThat(this.javaxFaces2_0Properties.getFullStateSavingViewIds()).containsExactly("myIds");
	}

	@Test
	public void testPartialStateSaving() {
		assertThat(this.javaxFaces2_0Properties.getPartialStateSaving()).isTrue();
	}

	@Test
	public void testSerializeServerState() {
		assertThat(this.javaxFaces2_2Properties.getSerializeServerState()).isTrue();
	}

	@Test
	public void testStateSavingMethod() {
		assertThat(this.javaxFaces2_0Properties.getStateSavingMethod()).isEqualTo("server");
	}

	@Test
	public void testDefaultSuffix() {
		assertThat(this.javaxFaces2_0Properties.getDefaultSuffix()).isEqualTo(".xhtml");
	}

	@Test
	public void testDisableFaceletJsfViewhandler() {
		assertThat(this.javaxFaces2_0Properties.getDisableFaceletJsfViewhandler()).isTrue();
	}

	@Test
	public void testFaceletsBufferSize() {
		assertThat(this.javaxFaces2_0Properties.getFaceletsBufferSize()).isEqualTo(33);
	}

	@Test
	public void testFaceletsLibraries() {
		assertThat(this.javaxFaces2_0Properties.getFaceletsLibraries()).containsExactly("myLibrary");
	}

	@Test
	public void testFaceletsRefreshPeriod() {
		assertThat(this.javaxFaces2_0Properties.getFaceletsRefreshPeriod()).isEqualTo(35);
	}

	@Test
	public void testFaceletsSkipComments() {
		assertThat(this.javaxFaces2_0Properties.getFaceletsSkipComments()).isTrue();
	}

	@Test
	public void testFaceletsSuffix() {
		assertThat(this.javaxFaces2_0Properties.getFaceletsSuffix()).isEqualTo(".html");
	}

	@Test
	public void testFaceletsViewMappings() {
		assertThat(this.javaxFaces2_0Properties.getFaceletsViewMappings()).containsExactly("newMapping");
	}

	@Test
	public void testHonorCurrentComponentAttributes() {
		assertThat(this.javaxFaces2_1Properties.getHonorCurrentComponentAttributes()).isTrue();
	}

	@Test
	public void testValidateEmptyFields() {
		assertThat(this.javaxFaces2_0Properties.getValidateEmptyFields()).isEqualTo("auto");
	}

	@Test
	public void testSeparatorChar() {
		assertThat(this.javaxFaces2_0Properties.getSeparatorChar()).isEqualTo(";");
	}

	@Test
	public void testPartialExecute() {
		assertThat(this.javaxFaces2_0Properties.getPartial().getExecute()).isTrue();
	}

	@Test
	public void testPartialRender() {
		assertThat(this.javaxFaces2_0Properties.getPartial().getRender()).isTrue();
	}

	@Test
	public void testPartialResetValues() {
		assertThat(this.javaxFaces2_2Properties.getPartial().getResetValues()).isTrue();
	}

	@Test
	public void testDatetimeconverterDefaultTimezoneIsSystemTimezone() {
		assertThat(this.javaxFaces2_0Properties.getDatetimeconverterDefaultTimezoneIsSystemTimezone()).isTrue();
	}

	@Test
	public void testFlowNullFlow() {
		assertThat(this.javaxFaces2_2Properties.getFlow().getNullFlow()).isTrue();
	}

	@Test
	public void testValidatorDisableDefaultBeanValidator() {
		assertThat(this.javaxFaces2_0Properties.getValidator().getDisableDefaultBeanValidator()).isTrue();
	}

	@Test
	public void testConfigFiles() {
		assertThat(this.javaxFaces2_0Properties.getConfigFiles()).containsExactly("myConfig");
	}

	@Test
	public void testLifecycleId() {
		assertThat(this.javaxFaces2_0Properties.getLifecycleId()).isEqualTo("myId");
	}

	@Test
	public void testClientWindowMode() {
		assertThat(this.javaxFaces2_2Properties.getClientWindowMode()).isEqualTo("url");
	}

	@Test
	public void testInterpretEmptyStringSubmittedValuesAsNull() {
		assertThat(this.javaxFaces2_0Properties.getInterpretEmptyStringSubmittedValuesAsNull()).isTrue();
	}

}
