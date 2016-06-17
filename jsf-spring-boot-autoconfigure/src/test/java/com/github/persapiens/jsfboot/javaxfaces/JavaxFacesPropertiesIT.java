package com.github.persapiens.jsfboot.javaxfaces;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringApplicationConfiguration(classes = JavaxFacesSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class JavaxFacesPropertiesIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private JavaxFacesProperties javaxFacesProperties;

	public void testProjectState() {
		assertThat(javaxFacesProperties.getProjectStage()).isEqualTo("Development");
	}

	public void testResourceExcludes() {
		assertThat(javaxFacesProperties.getResourceExcludes()).isEqualTo("myExcludes");
	}

	public void testWebappContractsDirectory() {
		assertThat(javaxFacesProperties.getWebappContractsDirectory()).isEqualTo("myContractsDirectory");
	}

	public void testWebappResourcesDirectory() {
		assertThat(javaxFacesProperties.getWebappResourcesDirectory()).isEqualTo("myResourcesDirectory");
	}

	public void testFullStateSavingViewIds() {
		assertThat(javaxFacesProperties.getFullStateSavingViewIds()).isEqualTo("myIds");
	}

	public void testPartialStateSaving() {
		assertThat(javaxFacesProperties.getPartialStateSaving()).isTrue();
	}

	public void testSerializeServerState() {
		assertThat(javaxFacesProperties.getSerializeServerState()).isTrue();
	}

	public void testStateSavingMethod() {
		assertThat(javaxFacesProperties.getStateSavingMethod()).isEqualTo("server");
	}

	public void testDefaultSuffix() {
		assertThat(javaxFacesProperties.getDefaultSuffix()).isEqualTo(".xhtml");
	}

	public void testDisableFaceletJsfViewhandler() {
		assertThat(javaxFacesProperties.getDisableFaceletJsfViewhandler()).isTrue();
	}

	public void testFaceletsBufferSize() {
		assertThat(javaxFacesProperties.getFaceletsBufferSize()).isEqualTo(33);
	}

	public void testFaceletsDecorators() {
		assertThat(javaxFacesProperties.getFaceletsDecorators()).isEqualTo("myDecorator");
	}

	public void testFaceletsLibraries() {
		assertThat(javaxFacesProperties.getFaceletsLibraries()).isEqualTo("myLibrary");
	}

	public void testFaceletsRefreshPeriod() {
		assertThat(javaxFacesProperties.getFaceletsRefreshPeriod()).isEqualTo(35);
	}

	public void testFaceletsSkipComments() {
		assertThat(javaxFacesProperties.getFaceletsSkipComments()).isTrue();
	}

	public void testFaceletsSuffix() {
		assertThat(javaxFacesProperties.getFaceletsSuffix()).isEqualTo(".html");
	}

	public void testFaceletsViewMappings() {
		assertThat(javaxFacesProperties.getFaceletsViewMappings()).isEqualTo("newMapping");
	}

	public void testHonorCurrentComponentAttributes() {
		assertThat(javaxFacesProperties.getHonorCurrentComponentAttributes()).isTrue();
	}

	public void testValidateEmptyFields() {
		assertThat(javaxFacesProperties.getValidateEmptyFields()).isEqualTo("auto");
	}

	public void testSeparatorChar() {
		assertThat(javaxFacesProperties.getSeparatorChar()).isEqualTo(";");
	}

	public void testPartialExecute() {
		assertThat(javaxFacesProperties.getPartial().getExecute()).isTrue();
	}

	public void testPartialRender() {
		assertThat(javaxFacesProperties.getPartial().getRender()).isTrue();
	}

	public void testPartialResetValues() {
		assertThat(javaxFacesProperties.getPartial().getResetValues()).isTrue();
	}

	public void testDatetimeconverterDefaultTimezoneIsSystemTimezone() {
		assertThat(javaxFacesProperties.getDatetimeconverterDefaultTimezoneIsSystemTimezone()).isTrue();
	}

	public void testFlowNullFlow() {
		assertThat(javaxFacesProperties.getFlow().getNullFlow()).isTrue();
	}

	public void testValidatorDisableDefaultBeanValidator() {
		assertThat(javaxFacesProperties.getValidator().getDisableDefaultBeanValidator()).isTrue();
	}

	public void testFaceletsResourceResolver() {
		assertThat(javaxFacesProperties.getFaceletsResourceResolver()).isEqualTo("myResolver");
	}

	public void testConfigFiles() {
		assertThat(javaxFacesProperties.getConfigFiles()).isEqualTo("myConfig");
	}

	public void testLifecycleId() {
		assertThat(javaxFacesProperties.getLifecycleId()).isEqualTo("myId");
	}

	public void testClientWindowMode() {
		assertThat(javaxFacesProperties.getClientWindowMode()).isEqualTo("url");
	}

	public void testInterpretEmptyStringSubmittedValuesAsNull() {
		assertThat(javaxFacesProperties.getInterpretEmptyStringSubmittedValuesAsNull()).isTrue();
	}

}
