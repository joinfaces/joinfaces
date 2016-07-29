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

package org.joinfaces.butterfaces;

import org.testng.annotations.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = ButterfacesSpringBootAutoConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Test
public class ButterfacesPropertiesIT extends AbstractTestNGSpringContextTests {

	@Autowired
	private ButterfacesProperties butterfacesProperties;

	public void testAjaxProcessingGlyphiconOnRequest() {
		assertThat(this.butterfacesProperties.getAjaxProcessingGlyphiconOnRequest())
			.isEqualTo("Processing");
	}

	public void testAjaxProcessingTextOnRequest() {
		assertThat(this.butterfacesProperties.getAjaxProcessingTextOnRequest())
			.isEqualTo("Processing");
	}

	public void testMaxLengthText() {
		assertThat(this.butterfacesProperties.getMaxLengthText())
			.isEqualTo("{0} of {1} characters");
	}

	public void testNoEntriesText() {
		assertThat(this.butterfacesProperties.getNoEntriesText())
			.isEqualTo("No matching entries...");
	}

	public void testSpinnerText() {
		assertThat(this.butterfacesProperties.getSpinnerText())
			.isEqualTo("Fetching data...");
	}

	public void testGlyphiconCollapsing() {
		assertThat(this.butterfacesProperties.getGlyphicon().getCollapsing())
			.isEqualTo("o_collape");
	}

	public void testGlyphiconExpansion() {
		assertThat(this.butterfacesProperties.getGlyphicon().getExpansion())
			.isEqualTo("o_expand");
	}

	public void testGlyphiconOptions() {
		assertThat(this.butterfacesProperties.getGlyphicon().getOptions())
			.isEqualTo("glyphicon glyphicon-th");
	}

	public void testGlyphiconRefresh() {
		assertThat(this.butterfacesProperties.getGlyphicon().getRefresh())
			.isEqualTo("glyphicon glyphicon-refresh");
	}

	public void testGlyphiconOrderLeft() {
		assertThat(this.butterfacesProperties.getGlyphicon().getOrder().getLeft())
			.isEqualTo("glyphicon glyphicon-chevron-left");
	}

	public void testGlyphiconOrderRight() {
		assertThat(this.butterfacesProperties.getGlyphicon().getOrder().getRight())
			.isEqualTo("glyphicon glyphicon-chevron-right");
	}

	public void testGlyphiconSortAscending() {
		assertThat(this.butterfacesProperties.getGlyphicon().getSort().getAscending())
			.isEqualTo("glyphicon glyphicon-chevron-down");
	}

	public void testGlyphiconSortDescending() {
		assertThat(this.butterfacesProperties.getGlyphicon().getSort().getDescending())
			.isEqualTo("glyphicon glyphicon-chevron-up");
	}

	public void testGlyphiconSortNone() {
		assertThat(this.butterfacesProperties.getGlyphicon().getSort().getNone())
			.isEqualTo("glyphicon glyphicon-chevron-right");
	}

	public void testAjaxDisableRenderRegionsOnRequest() {
		assertThat(this.butterfacesProperties.getAjaxDisableRenderRegionsOnRequest())
			.isTrue();
	}

	public void testAutoTrimInputFields() {
		assertThat(this.butterfacesProperties.getAutoTrimInputFields())
			.isTrue();
	}

	public void testProvideBootstrap() {
		assertThat(this.butterfacesProperties.getProvideBootstrap())
			.isTrue();
	}

	public void testProvideJQuery() {
		assertThat(this.butterfacesProperties.getProvideJQuery())
			.isTrue();
	}

	public void testUseCompressedResources() {
		assertThat(this.butterfacesProperties.getUseCompressedResources())
			.isTrue();
	}

	public void testIntegrationPrimefacesDisableJQuery() {
		assertThat(this.butterfacesProperties.getIntegration().getPrimefaces().getDisableJQuery())
			.isTrue();
	}

}
