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

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * This class analyzes the {@link SpringApplication} and its {@link ApplicationReadyEvent#getApplicationContext()}.
 *
 * @author Lars Grefer
 */
@Slf4j
public class JoinfacesApplicationAnalyzer implements ApplicationListener<ApplicationReadyEvent> {

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		warnAboutJsfManagedBeans(event.getApplicationContext());
	}

	@SuppressFBWarnings(value = "NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE", justification = "getType() is always non-null when reached")
	private void warnAboutJsfManagedBeans(ConfigurableApplicationContext applicationContext) {
		String[] managedBeanNames = applicationContext.getBeanNamesForAnnotation(ManagedBean.class);

		for (String managedBeanName : managedBeanNames) {
			log.warn("The spring bean '{}' of type '{}' is also annotated with '@{}'. This may lead to unexpected behaviour.", managedBeanName, applicationContext.getType(managedBeanName).getName(), ManagedBean.class.getName());
		}
	}
}
