/*
 * Copyright 2016-2018 the original author or authors.
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

package org.joinfaces.autoconfigure;

import javax.faces.bean.ManagedBean;

import org.junit.Before;
import org.junit.Test;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.when;

public class JoinfacesApplicationAnalyzerTest {

	private JoinfacesApplicationAnalyzer joinfacesApplicationAnalyzer;
	private ApplicationContextRunner applicationContextRunner;

	@Before
	public void setUp() {
		this.joinfacesApplicationAnalyzer = new JoinfacesApplicationAnalyzer();
		this.applicationContextRunner = new ApplicationContextRunner();
	}

	@Test
	public void testJsfManagedBeans() {
		this.applicationContextRunner
				.withUserConfiguration(DummyConfiguration.class)
				.run(context -> {
					assertThat(context.getBeanNamesForAnnotation(ManagedBean.class)).isNotEmpty();

					ApplicationReadyEvent applicationReadyEvent = mock(ApplicationReadyEvent.class);
					when(applicationReadyEvent.getApplicationContext()).thenReturn(context.getSourceApplicationContext());

					this.joinfacesApplicationAnalyzer.onApplicationEvent(applicationReadyEvent);
				});
	}

	@ManagedBean
	static class DummyBean {
	}

	@Configuration
	static class DummyConfiguration {
		@Bean
		public DummyBean dummyBean() {
			return new DummyBean();
		}
	}
}
