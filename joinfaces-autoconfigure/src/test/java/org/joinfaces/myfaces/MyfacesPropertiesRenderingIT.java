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
public class MyfacesPropertiesRenderingIT {

	@Autowired
	private MyfacesProperties myfacesProperties;

	@Test
	public void testPrettyHtml() {
		assertThat(this.myfacesProperties.getPrettyHtml()).isTrue();
	}

	@Test
	public void testAllowJavascript() {
		assertThat(this.myfacesProperties.getAllowJavascript()).isTrue();
	}

	@Test
	public void testStrictXhtmlLinks() {
		assertThat(this.myfacesProperties.getStrictXhtmlLinks()).isTrue();
	}

	@Test
	public void testRenderClearJavascriptForButton() {
		assertThat(this.myfacesProperties.getRenderClearJavascriptForButton()).isTrue();
	}

	@Test
	public void testRenderHiddenFieldsForLinkParams() {
		assertThat(this.myfacesProperties.getRenderHiddenFieldsForLinkParams()).isTrue();
	}

	@Test
	public void testSaveFormSubmitLinkIe() {
		assertThat(this.myfacesProperties.getSaveFormSubmitLinkIe()).isTrue();
	}

	@Test
	public void testWrapScriptContentWithXmlCommentTag() {
		assertThat(this.myfacesProperties.getWrapScriptContentWithXmlCommentTag()).isTrue();
	}

	@Test
	public void testRenderFormSubmitScriptInline() {
		assertThat(this.myfacesProperties.getRenderFormSubmitScriptInline()).isTrue();
	}

	@Test
	public void testDefaultResponseWriterContentTypeMode() {
		assertThat(this.myfacesProperties.getDefaultResponseWriterContentTypeMode()).isEqualTo("text/html");
	}

	@Test
	public void testUseMultipleJsFilesForJsfUncompressedJs() {
		assertThat(this.myfacesProperties.getUseMultipleJsFilesForJsfUncompressedJs()).isTrue();
	}

	@Test
	public void testJsfJsMode() {
		assertThat(this.myfacesProperties.getJsfJsMode()).isEqualTo("normal");
	}

	@Test
	public void testEarlyFlushEnabled() {
		assertThat(this.myfacesProperties.getEarlyFlushEnabled()).isTrue();
	}

	@Test
	public void testRenderFormViewStateAtBegin() {
		assertThat(this.myfacesProperties.getRenderFormViewStateAtBegin()).isTrue();
	}

}
