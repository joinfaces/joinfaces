/*
 * Copyright 2016-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.joinfaces.autoconfigure.adminfaces;

import com.github.adminfaces.template.session.AdminSession;
import org.joinfaces.autoconfigure.primefaces.Primefaces4_0Properties;
import org.joinfaces.autoconfigure.primefaces.Primefaces5_2Properties;
import org.joinfaces.autoconfigure.primefaces.PrimefacesAutoConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

public class AdminfacesAutoConfigurationTest {

	private WebApplicationContextRunner webApplicationContextRunner;

	@BeforeEach
	public void setUp() {
		this.webApplicationContextRunner = new WebApplicationContextRunner()
			.withConfiguration(AutoConfigurations.of(AdminfacesAutoConfiguration.class, PrimefacesAutoConfiguration.class));
	}

	@Test
	public void testConfigurationProvider() {
		this.webApplicationContextRunner
			.run(context -> {
				Primefaces4_0Properties primefaces4_0Properties = context.getBean("joinfaces.primefaces-org.joinfaces.autoconfigure.primefaces.Primefaces4_0Properties",
					Primefaces4_0Properties.class);
				assertThat(primefaces4_0Properties.getTheme())
					.isEqualTo("admin");

				Primefaces5_2Properties primefaces5_2Properties = context.getBean("joinfaces.primefaces-org.joinfaces.autoconfigure.primefaces.Primefaces5_2Properties",
					Primefaces5_2Properties.class);
				assertThat(primefaces5_2Properties.getFontAwesome())
					.isTrue();

				assertThat(context.getBean("adminSession", AdminSession.class))
					.isNotNull();

				assertThat(context.getBean(AdminConfigWrapper.class))
					.isNotNull();
			});
	}
}
