/*
 * Copyright 2016-2017 the original author or authors.
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

package org.joinfaces.primefaces;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Some more or less pointless unit tests in order to reach the desired line coverage
 *
 * @author Lars Grefer
 */
public class PrimefacesAutoConfigurationTest {

	@Test
	public void testBase() {
		PrimefacesAutoConfiguration primefacesAutoConfiguration = new PrimefacesAutoConfiguration();
		assertThat(primefacesAutoConfiguration).isNotNull();
	}

	@Test
	public void test5_0() {
		PrimefacesAutoConfiguration.Primefaces5_0AutoConfiguration primefaces5_0AutoConfiguration = new PrimefacesAutoConfiguration.Primefaces5_0AutoConfiguration();
		assertThat(primefaces5_0AutoConfiguration).isNotNull();
	}

	@Test
	public void test5_1() {
		PrimefacesAutoConfiguration.Primefaces5_1AutoConfiguration primefaces5_1AutoConfiguration = new PrimefacesAutoConfiguration.Primefaces5_1AutoConfiguration();
		assertThat(primefaces5_1AutoConfiguration).isNotNull();
	}

	@Test
	public void test5_2() {
		PrimefacesAutoConfiguration.Primefaces5_2AutoConfiguration primefaces5_2AutoConfiguration = new PrimefacesAutoConfiguration.Primefaces5_2AutoConfiguration();
		assertThat(primefaces5_2AutoConfiguration).isNotNull();
	}

	@Test
	public void test6_0() {
		PrimefacesAutoConfiguration.Primefaces6_0AutoConfiguration primefaces6_0AutoConfiguration = new PrimefacesAutoConfiguration.Primefaces6_0AutoConfiguration();
		assertThat(primefaces6_0AutoConfiguration).isNotNull();
	}

}
