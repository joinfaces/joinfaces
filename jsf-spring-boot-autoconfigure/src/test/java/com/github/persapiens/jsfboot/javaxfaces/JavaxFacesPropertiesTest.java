package com.github.persapiens.jsfboot.javaxfaces;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class JavaxFacesPropertiesTest {

	public void testProjectState() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.setProjectStage("UnitTest");

		assertThat(javaxFacesProperties.getProjectStage()).isEqualTo("UnitTest");
	}

	public void testResourceExcludes() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.setResourceExcludes("myExcludes");

		assertThat(javaxFacesProperties.getResourceExcludes()).isEqualTo("myExcludes");
	}

	public void testWebappContractsDirectory() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.setWebappContractsDirectory("myDirectory");

		assertThat(javaxFacesProperties.getWebappContractsDirectory()).isEqualTo("myDirectory");
	}

	public void testWebappResourcesDirectory() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.setWebappResourcesDirectory("myResourcesDirectory");

		assertThat(javaxFacesProperties.getWebappResourcesDirectory()).isEqualTo("myResourcesDirectory");
	}

	public void testFullStateSavingViewIds() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.setFullStateSavingViewIds("myIds");

		assertThat(javaxFacesProperties.getFullStateSavingViewIds()).isEqualTo("myIds");
	}

	public void testPartialStateSaving() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.setPartialStateSaving(true);

		assertThat(javaxFacesProperties.getPartialStateSaving()).isTrue();
	}

	public void testSerializeServerState() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.setSerializeServerState(true);

		assertThat(javaxFacesProperties.getSerializeServerState()).isTrue();
	}

	public void testStateSavingMethod() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.setStateSavingMethod("server");

		assertThat(javaxFacesProperties.getStateSavingMethod()).isEqualTo("server");
	}

	public void testDefaultSuffix() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.setDefaultSuffix(".xhtml");

		assertThat(javaxFacesProperties.getDefaultSuffix()).isEqualTo(".xhtml");
	}

	public void testDisableFaceletJsfViewhandler() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.setDisableFaceletJsfViewhandler(true);

		assertThat(javaxFacesProperties.getDisableFaceletJsfViewhandler()).isTrue();
	}

	public void testFaceletsBufferSize() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.setFaceletsBufferSize(33);

		assertThat(javaxFacesProperties.getFaceletsBufferSize()).isEqualTo(33);
	}

	public void testFaceletsDecorators() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.setFaceletsDecorators("myDecorator");

		assertThat(javaxFacesProperties.getFaceletsDecorators()).isEqualTo("myDecorator");
	}

	public void testFaceletsLibraries() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.setFaceletsLibraries("myLibrary");

		assertThat(javaxFacesProperties.getFaceletsLibraries()).isEqualTo("myLibrary");
	}

	public void testFaceletsRefreshPeriod() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.setFaceletsRefreshPeriod(35);

		assertThat(javaxFacesProperties.getFaceletsRefreshPeriod()).isEqualTo(35);
	}

	public void testFaceletsSkipComments() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.setFaceletsSkipComments(true);

		assertThat(javaxFacesProperties.getFaceletsSkipComments()).isTrue();
	}

	public void testFaceletsSuffix() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.setFaceletsSuffix(".html");

		assertThat(javaxFacesProperties.getFaceletsSuffix()).isEqualTo(".html");
	}

	public void testFaceletsViewMappings() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.setFaceletsViewMappings("newMapping");

		assertThat(javaxFacesProperties.getFaceletsViewMappings()).isEqualTo("newMapping");
	}

	public void testHonorCurrentComponentAttributes() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.setHonorCurrentComponentAttributes(true);

		assertThat(javaxFacesProperties.getHonorCurrentComponentAttributes()).isTrue();
	}

	public void testValidateEmptyFields() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.setValidateEmptyFields("auto");

		assertThat(javaxFacesProperties.getValidateEmptyFields()).isEqualTo("auto");
	}

	public void testSeparatorChar() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.setSeparatorChar(";");

		assertThat(javaxFacesProperties.getSeparatorChar()).isEqualTo(";");
	}

	public void testPartialExecute() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.getPartial().setExecute(true);

		assertThat(javaxFacesProperties.getPartial().getExecute()).isTrue();
	}

	public void testPartialRender() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.getPartial().setRender(true);

		assertThat(javaxFacesProperties.getPartial().getRender()).isTrue();
	}

	public void testPartialResetValues() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.getPartial().setResetValues(true);

		assertThat(javaxFacesProperties.getPartial().getResetValues()).isTrue();
	}

	public void testDatetimeconverterDefaultTimezoneIsSystemTimezone() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.setDatetimeconverterDefaultTimezoneIsSystemTimezone(true);

		assertThat(javaxFacesProperties.getDatetimeconverterDefaultTimezoneIsSystemTimezone()).isTrue();
	}

	public void testFlowNullFlow() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.getFlow().setNullFlow(true);

		assertThat(javaxFacesProperties.getFlow().getNullFlow()).isTrue();
	}

	public void testValidatorDisableDefaultBeanValidator() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.getValidator().setDisableDefaultBeanValidator(true);

		assertThat(javaxFacesProperties.getValidator().getDisableDefaultBeanValidator()).isTrue();
	}

	public void testFaceletsResourceResolver() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.setFaceletsResourceResolver("myResolver");

		assertThat(javaxFacesProperties.getFaceletsResourceResolver()).isEqualTo("myResolver");
	}

	public void testConfigFiles() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.setConfigFiles("myConfig");

		assertThat(javaxFacesProperties.getConfigFiles()).isEqualTo("myConfig");
	}

	public void testLifecycleId() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.setLifecycleId("myId");

		assertThat(javaxFacesProperties.getLifecycleId()).isEqualTo("myId");
	}

	public void testClientWindowMode() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.setClientWindowMode("url");

		assertThat(javaxFacesProperties.getClientWindowMode()).isEqualTo("url");
	}

	public void testInterpretEmptyStringSubmittedValuesAsNull() {
		JavaxFacesProperties javaxFacesProperties = new JavaxFacesProperties();
        javaxFacesProperties.setInterpretEmptyStringSubmittedValuesAsNull(true);

		assertThat(javaxFacesProperties.getInterpretEmptyStringSubmittedValuesAsNull()).isTrue();
	}

}
