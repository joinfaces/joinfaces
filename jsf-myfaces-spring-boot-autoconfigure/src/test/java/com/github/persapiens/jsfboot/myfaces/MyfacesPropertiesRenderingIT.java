package com.github.persapiens.jsfboot.myfaces;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringApplicationConfiguration(classes = MyfacesSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class MyfacesPropertiesRenderingIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private MyfacesProperties myfacesProperties;

	public void testPrettyHtml() {
		assertThat(myfacesProperties.getPrettyHtml()).isTrue();
	}

	public void testAllowJavascript() {
		assertThat(myfacesProperties.getAllowJavascript()).isTrue();
	}

	public void testStrictXhtmlLinks() {
		assertThat(myfacesProperties.getStrictXhtmlLinks()).isTrue();
	}

	public void testRenderClearJavascriptForButton() {
		assertThat(myfacesProperties.getRenderClearJavascriptForButton()).isTrue();
	}

	public void testRenderHiddenFieldsForLinkParams() {
		assertThat(myfacesProperties.getRenderHiddenFieldsForLinkParams()).isTrue();
	}

	public void testSaveFormSubmitLinkIe() {
		assertThat(myfacesProperties.getSaveFormSubmitLinkIe()).isTrue();
	}

	public void testWrapScriptContentWithXmlCommentTag() {
		assertThat(myfacesProperties.getWrapScriptContentWithXmlCommentTag()).isTrue();
	}

	public void testRenderFormSubmitScriptInline() {
		assertThat(myfacesProperties.getRenderFormSubmitScriptInline()).isTrue();
	}

	public void testDefaultResponseWriterContentTypeMode() {
		assertThat(myfacesProperties.getDefaultResponseWriterContentTypeMode()).isEqualTo("text/html");
	}        
    
	public void testUseMultipleJsFilesForJsfUncompressedJs() {
		assertThat(myfacesProperties.getUseMultipleJsFilesForJsfUncompressedJs()).isTrue();
	}    
    
	public void testJsfJsMode() {
		assertThat(myfacesProperties.getJsfJsMode()).isEqualTo("normal");
	}
        
	public void testEarlyFlushEnabled() {
		assertThat(myfacesProperties.getEarlyFlushEnabled()).isTrue();
	}

	public void testRenderFormViewStateAtBegin() {
		assertThat(myfacesProperties.getRenderFormViewStateAtBegin()).isTrue();
	}

}
