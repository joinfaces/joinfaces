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
public class ButterfacesPropertiesIT {

	@Autowired
	private ButterfacesProperties butterfacesProperties;

	@Test
	public void testAjaxProcessingGlyphiconOnRequest() {
		assertThat(this.butterfacesProperties.getAjaxProcessingGlyphiconOnRequest())
			.isEqualTo("Processing");
	}

	@Test
	public void testAjaxProcessingTextOnRequest() {
		assertThat(this.butterfacesProperties.getAjaxProcessingTextOnRequest())
			.isEqualTo("Processing");
	}

	@Test
	public void testMaxLengthText() {
		assertThat(this.butterfacesProperties.getMaxLengthText())
			.isEqualTo("{0} of {1} characters");
	}

	@Test
	public void testNoEntriesText() {
		assertThat(this.butterfacesProperties.getNoEntriesText())
			.isEqualTo("No matching entries...");
	}

	@Test
	public void testSpinnerText() {
		assertThat(this.butterfacesProperties.getSpinnerText())
			.isEqualTo("Fetching data...");
	}

	@Test
	public void testGlyphiconCollapsing() {
		assertThat(this.butterfacesProperties.getGlyphicon().getCollapsing())
			.isEqualTo("o_collape");
	}

	@Test
	public void testGlyphiconExpansion() {
		assertThat(this.butterfacesProperties.getGlyphicon().getExpansion())
			.isEqualTo("o_expand");
	}

	@Test
	public void testGlyphiconOptions() {
		assertThat(this.butterfacesProperties.getGlyphicon().getOptions())
			.isEqualTo("glyphicon glyphicon-th");
	}

	@Test
	public void testGlyphiconRefresh() {
		assertThat(this.butterfacesProperties.getGlyphicon().getRefresh())
			.isEqualTo("glyphicon glyphicon-refresh");
	}

	@Test
	public void testGlyphiconOrderLeft() {
		assertThat(this.butterfacesProperties.getGlyphicon().getOrder().getLeft())
			.isEqualTo("glyphicon glyphicon-chevron-left");
	}

	@Test
	public void testGlyphiconOrderRight() {
		assertThat(this.butterfacesProperties.getGlyphicon().getOrder().getRight())
			.isEqualTo("glyphicon glyphicon-chevron-right");
	}

	@Test
	public void testGlyphiconSortAscending() {
		assertThat(this.butterfacesProperties.getGlyphicon().getSort().getAscending())
			.isEqualTo("glyphicon glyphicon-chevron-down");
	}

	@Test
	public void testGlyphiconSortDescending() {
		assertThat(this.butterfacesProperties.getGlyphicon().getSort().getDescending())
			.isEqualTo("glyphicon glyphicon-chevron-up");
	}

	@Test
	public void testGlyphiconSortNone() {
		assertThat(this.butterfacesProperties.getGlyphicon().getSort().getNone())
			.isEqualTo("glyphicon glyphicon-chevron-right");
	}

	@Test
	public void testAjaxDisableRenderRegionsOnRequest() {
		assertThat(this.butterfacesProperties.getAjaxDisableRenderRegionsOnRequest())
			.isTrue();
	}

	@Test
	public void testAutoTrimInputFields() {
		assertThat(this.butterfacesProperties.getAutoTrimInputFields())
			.isTrue();
	}

	@Test
	public void testProvideBootstrap() {
		assertThat(this.butterfacesProperties.getProvideBootstrap())
			.isTrue();
	}

	@Test
	public void testProvideJQuery() {
		assertThat(this.butterfacesProperties.getProvideJQuery())
			.isTrue();
	}

	@Test
	public void testUseCompressedResources() {
		assertThat(this.butterfacesProperties.getUseCompressedResources())
			.isTrue();
	}

	@Test
	public void testIntegrationPrimefacesDisableJQuery() {
		assertThat(this.butterfacesProperties.getIntegration().getPrimefaces().getDisableJQuery())
			.isTrue();
	}

	@Test
	public void testTreeBoxShowClearButton() {
		assertThat(this.butterfacesProperties.getTreebox().getShowClearButton())
			.isTrue();
	}

}
