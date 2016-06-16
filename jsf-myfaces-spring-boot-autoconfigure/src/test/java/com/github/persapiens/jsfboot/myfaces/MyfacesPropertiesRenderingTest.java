package com.github.persapiens.jsfboot.myfaces;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class MyfacesPropertiesRenderingTest {

	public void testPrettyHtml() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setPrettyHtml(true);

		assertThat(myfacesProperties.getPrettyHtml()).isTrue();
	}

	public void testAllowJavascript() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setAllowJavascript(true);

		assertThat(myfacesProperties.getAllowJavascript()).isTrue();
	}

	public void testStrictXhtmlLinks() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setStrictXhtmlLinks(true);

		assertThat(myfacesProperties.getStrictXhtmlLinks()).isTrue();
	}

	public void testRenderClearJavascriptForButton() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setRenderClearJavascriptForButton(true);

		assertThat(myfacesProperties.getRenderClearJavascriptForButton()).isTrue();
	}

	public void testRenderHiddenFieldsForLinkParams() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setRenderHiddenFieldsForLinkParams(true);

		assertThat(myfacesProperties.getRenderHiddenFieldsForLinkParams()).isTrue();
	}

	public void testSaveFormSubmitLinkIe() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setSaveFormSubmitLinkIe(true);

		assertThat(myfacesProperties.getSaveFormSubmitLinkIe()).isTrue();
	}

	public void testWrapScriptContentWithXmlCommentTag() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setWrapScriptContentWithXmlCommentTag(true);

		assertThat(myfacesProperties.getWrapScriptContentWithXmlCommentTag()).isTrue();
	}

	public void testRenderFormSubmitScriptInline() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setRenderFormSubmitScriptInline(true);

		assertThat(myfacesProperties.getRenderFormSubmitScriptInline()).isTrue();
	}

	public void testDefaultResponseWriterContentTypeMode() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setDefaultResponseWriterContentTypeMode("text/html");

		assertThat(myfacesProperties.getDefaultResponseWriterContentTypeMode()).isEqualTo("text/html");
	}        
    
	public void testUseMultipleJsFilesForJsfUncompressedJs() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setUseMultipleJsFilesForJsfUncompressedJs(true);

		assertThat(myfacesProperties.getUseMultipleJsFilesForJsfUncompressedJs()).isTrue();
	}    
    
	public void testJsfJsMode() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setJsfJsMode("normal");

		assertThat(myfacesProperties.getJsfJsMode()).isEqualTo("normal");
	}
        
	public void testEarlyFlushEnabled() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setEarlyFlushEnabled(true);

		assertThat(myfacesProperties.getEarlyFlushEnabled()).isTrue();
	}

	public void testRenderFormViewStateAtBegin() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setRenderFormViewStateAtBegin(true);

		assertThat(myfacesProperties.getRenderFormViewStateAtBegin()).isTrue();
	}

}
