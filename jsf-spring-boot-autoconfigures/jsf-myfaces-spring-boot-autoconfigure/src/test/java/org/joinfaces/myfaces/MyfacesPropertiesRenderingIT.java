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

import org.testng.annotations.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringApplicationConfiguration(classes = MyfacesSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class MyfacesPropertiesRenderingIT extends AbstractTestNGSpringContextTests {

	@Autowired
	private MyfacesProperties myfacesProperties;

	public void testPrettyHtml() {
		assertThat(this.myfacesProperties.getPrettyHtml()).isTrue();
	}

	public void testAllowJavascript() {
		assertThat(this.myfacesProperties.getAllowJavascript()).isTrue();
	}

	public void testStrictXhtmlLinks() {
		assertThat(this.myfacesProperties.getStrictXhtmlLinks()).isTrue();
	}

	public void testRenderClearJavascriptForButton() {
		assertThat(this.myfacesProperties.getRenderClearJavascriptForButton()).isTrue();
	}

	public void testRenderHiddenFieldsForLinkParams() {
		assertThat(this.myfacesProperties.getRenderHiddenFieldsForLinkParams()).isTrue();
	}

	public void testSaveFormSubmitLinkIe() {
		assertThat(this.myfacesProperties.getSaveFormSubmitLinkIe()).isTrue();
	}

	public void testWrapScriptContentWithXmlCommentTag() {
		assertThat(this.myfacesProperties.getWrapScriptContentWithXmlCommentTag()).isTrue();
	}

	public void testRenderFormSubmitScriptInline() {
		assertThat(this.myfacesProperties.getRenderFormSubmitScriptInline()).isTrue();
	}

	public void testDefaultResponseWriterContentTypeMode() {
		assertThat(this.myfacesProperties.getDefaultResponseWriterContentTypeMode()).isEqualTo("text/html");
	}

	public void testUseMultipleJsFilesForJsfUncompressedJs() {
		assertThat(this.myfacesProperties.getUseMultipleJsFilesForJsfUncompressedJs()).isTrue();
	}

	public void testJsfJsMode() {
		assertThat(this.myfacesProperties.getJsfJsMode()).isEqualTo("normal");
	}

	public void testEarlyFlushEnabled() {
		assertThat(this.myfacesProperties.getEarlyFlushEnabled()).isTrue();
	}

	public void testRenderFormViewStateAtBegin() {
		assertThat(this.myfacesProperties.getRenderFormViewStateAtBegin()).isTrue();
	}

}
