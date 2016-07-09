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

package com.github.persapiens.jsfboot.myfaces;

import org.testng.annotations.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringApplicationConfiguration(classes = MyfacesSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class MyfacesPropertiesExpressionLanguageIT extends AbstractTestNGSpringContextTests {

	@Autowired
	private MyfacesProperties myfacesProperties;

	public void testStrictJsf2CcElResolver() {
		assertThat(this.myfacesProperties.getStrictJsf2CcElResolver()).isEqualTo("myElResolver");
	}

	public void testSupportJspAndFacesEl() {
		assertThat(this.myfacesProperties.getSupportJspAndFacesEl()).isTrue();
	}

	public void testSupportEl3ImportHandler() {
		assertThat(this.myfacesProperties.getSupportEl3ImportHandler()).isTrue();
	}

	public void testElResolverComparator() {
		assertThat(this.myfacesProperties.getElResolverComparator()).isEqualTo("myResolverComparator");
	}

	public void testElResolverPredicate() {
		assertThat(this.myfacesProperties.getElResolverPredicate()).isEqualTo("myResolverPredicate");
	}

	public void testCacheElExpressions() {
		assertThat(this.myfacesProperties.getCacheElExpressions()).isTrue();
	}

	public void testExpressionFactory() {
		assertThat(this.myfacesProperties.getExpressionFactory()).isEqualTo("myExpressionFactory");
	}

}
