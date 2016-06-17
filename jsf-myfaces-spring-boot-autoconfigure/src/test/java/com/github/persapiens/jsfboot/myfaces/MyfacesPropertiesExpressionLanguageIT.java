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
public class MyfacesPropertiesExpressionLanguageIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private MyfacesProperties myfacesProperties;

	public void testStrictJsf2CcElResolver() {
		assertThat(myfacesProperties.getStrictJsf2CcElResolver()).isEqualTo("myElResolver");
	}

	public void testSupportJspAndFacesEl() {
		assertThat(myfacesProperties.getSupportJspAndFacesEl()).isTrue();
	}

	public void testSupportEl3ImportHandler() {
		assertThat(myfacesProperties.getSupportEl3ImportHandler()).isTrue();
	}

	public void testElResolverComparator() {
		assertThat(myfacesProperties.getElResolverComparator()).isEqualTo("myResolverComparator");
	}

	public void testElResolverPredicate() {
		assertThat(myfacesProperties.getElResolverPredicate()).isEqualTo("myResolverPredicate");
	}

	public void testCacheElExpressions() {
		assertThat(myfacesProperties.getCacheElExpressions()).isTrue();
	}

	public void testExpressionFactory() {
		assertThat(myfacesProperties.getExpressionFactory()).isEqualTo("myExpressionFactory");
	}

}
