package com.github.persapiens.jsfboot.myfaces;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class MyfacesPropertiesExpressionLanguageTest {

	public void testStrictJsf2CcElResolver() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setStrictJsf2CcElResolver("myElResolver");

		assertThat(myfacesProperties.getStrictJsf2CcElResolver()).isEqualTo("myElResolver");
	}

	public void testSupportJspAndFacesEl() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setSupportJspAndFacesEl(true);

		assertThat(myfacesProperties.getSupportJspAndFacesEl()).isTrue();
	}

	public void testSupportEl3ImportHandler() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setSupportEl3ImportHandler(true);

		assertThat(myfacesProperties.getSupportEl3ImportHandler()).isTrue();
	}

	public void testElResolverComparator() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setElResolverComparator("myResolverComparator");

		assertThat(myfacesProperties.getElResolverComparator()).isEqualTo("myResolverComparator");
	}

	public void testElResolverPredicate() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setElResolverPredicate("myResolverPredicate");

		assertThat(myfacesProperties.getElResolverPredicate()).isEqualTo("myResolverPredicate");
	}

	public void testCacheElExpressions() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setCacheElExpressions(true);

		assertThat(myfacesProperties.getCacheElExpressions()).isTrue();
	}

	public void testExpressionFactory() {
		MyfacesProperties myfacesProperties = new MyfacesProperties();
        myfacesProperties.setExpressionFactory("myExpressionFactory");

		assertThat(myfacesProperties.getExpressionFactory()).isEqualTo("myExpressionFactory");
	}

}
