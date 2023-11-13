/*
 * Copyright 2016-2022 the original author or authors.
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

package org.joinfaces.rewrite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.ocpsoft.common.spi.ServiceEnricher;

import org.springframework.context.ApplicationContext;

/**
 * {@link ServiceEnricher} for Spring based applications.
 * <p>
 * This implementation also supports the {@link ServiceEnricher#produce(Class)} method
 * (which {@link org.ocpsoft.rewrite.spring.SpringServiceEnricher} does not)
 *
 * @author Lars Grefer
 * @see org.ocpsoft.rewrite.spring.SpringServiceEnricher
 */
@Deprecated(forRemoval = true)
@Slf4j
public class SpringBootServiceEnricher implements ServiceEnricher {

	@Override
	public <T> Collection<T> produce(Class<T> type) {
		ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();

		if (applicationContext == null) {
			log.info("No application context found. Can't produce services of type {}", type);
			return new ArrayList<>();
		}

		return applicationContext.getBeanProvider(type)
				.orderedStream()
				.collect(Collectors.toList());
	}

	@Override
	public <T> void enrich(T service) {
		ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();

		if (applicationContext != null) {
			applicationContext.getAutowireCapableBeanFactory().autowireBean(service);
		}
	}
}
