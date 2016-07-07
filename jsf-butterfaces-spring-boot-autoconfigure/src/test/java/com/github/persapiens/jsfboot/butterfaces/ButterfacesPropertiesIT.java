package com.github.persapiens.jsfboot.butterfaces;

import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.assertj.core.api.Assertions.assertThat;

@SpringApplicationConfiguration(classes = ButterfacesSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class ButterfacesPropertiesIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private ButterfacesProperties butterfacesProperties;
    
	public void testAjaxProcessingGlyphiconOnRequest() {
		assertThat(butterfacesProperties.getAjaxProcessingGlyphiconOnRequest())
            .isEqualTo("Processing");
	}
    
	public void testAjaxProcessingTextOnRequest() {
		assertThat(butterfacesProperties.getAjaxProcessingTextOnRequest())
            .isEqualTo("Processing");
	}
    
	public void testMaxLengthText() {
		assertThat(butterfacesProperties.getMaxLengthText())
            .isEqualTo("{0} of {1} characters");
	}
    
	public void testNoEntriesText() {
		assertThat(butterfacesProperties.getNoEntriesText())
            .isEqualTo("No matching entries...");
	}
    
	public void testSpinnerText() {
		assertThat(butterfacesProperties.getSpinnerText())
            .isEqualTo("Fetching data...");
	}
    
	public void testGlyphiconCollapsing() {
		assertThat(butterfacesProperties.getGlyphicon().getCollapsing())
            .isEqualTo("o_collape");
	}
    
	public void testGlyphiconExpansion() {
		assertThat(butterfacesProperties.getGlyphicon().getExpansion())
            .isEqualTo("o_expand");
	}
    
	public void testGlyphiconOptions() {
		assertThat(butterfacesProperties.getGlyphicon().getOptions())
            .isEqualTo("glyphicon glyphicon-th");
	}
    
	public void testGlyphiconRefresh() {
		assertThat(butterfacesProperties.getGlyphicon().getRefresh())
            .isEqualTo("glyphicon glyphicon-refresh");
	}
    
	public void testGlyphiconOrderLeft() {
		assertThat(butterfacesProperties.getGlyphicon().getOrder().getLeft())
            .isEqualTo("glyphicon glyphicon-chevron-left");
	}
    
	public void testGlyphiconOrderRight() {
		assertThat(butterfacesProperties.getGlyphicon().getOrder().getRight())
            .isEqualTo("glyphicon glyphicon-chevron-right");
	}
    
	public void testGlyphiconSortAscending() {
		assertThat(butterfacesProperties.getGlyphicon().getSort().getAscending())
            .isEqualTo("glyphicon glyphicon-chevron-down");
	}
    
	public void testGlyphiconSortDescending() {
		assertThat(butterfacesProperties.getGlyphicon().getSort().getDescending())
            .isEqualTo("glyphicon glyphicon-chevron-up");
	}
    
	public void testGlyphiconSortNone() {
		assertThat(butterfacesProperties.getGlyphicon().getSort().getNone())
            .isEqualTo("glyphicon glyphicon-chevron-right");
	}

	public void testAjaxDisableRenderRegionsOnRequest() {
		assertThat(butterfacesProperties.getAjaxDisableRenderRegionsOnRequest())
            .isTrue();
	}

	public void testAutoTrimInputFields() {
		assertThat(butterfacesProperties.getAutoTrimInputFields())
            .isTrue();
	}

	public void testProvideBootstrap() {
		assertThat(butterfacesProperties.getProvideBootstrap())
            .isTrue();
	}

	public void testProvideJQuery() {
		assertThat(butterfacesProperties.getProvideJQuery())
            .isTrue();
	}

	public void testUseCompressedResources() {
		assertThat(butterfacesProperties.getUseCompressedResources())
            .isTrue();
	}

	public void testIntegrationPrimefacesDisableJQuery() {
		assertThat(butterfacesProperties.getIntegration().getPrimefaces().getDisableJQuery())
            .isTrue();
	}

}
