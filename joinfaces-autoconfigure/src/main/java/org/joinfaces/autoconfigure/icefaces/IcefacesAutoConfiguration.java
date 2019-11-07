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

package org.joinfaces.autoconfigure.icefaces;

import org.icefaces.impl.application.SessionExpiredListener;
import org.icefaces.impl.application.WindowScopeManager;
import org.icefaces.impl.push.servlet.ICEpushResourceHandlerLifecycle;
import org.icefaces.util.EnvUtils;
import org.joinfaces.autoconfigure.servlet.TldListenerRegistrationBean;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot auto configuration for ICEfaces.
 *
 * @author Lars Grefer
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnClass({EnvUtils.class, WindowScopeManager.class})
@EnableConfigurationProperties(IcefacesProperties.class)
public class IcefacesAutoConfiguration {

	@Bean
	public static CustomScopeConfigurer windowScopeConfigurer() {
		CustomScopeConfigurer windowScopeConfigurer = new CustomScopeConfigurer();
		windowScopeConfigurer.addScope(WindowScopeManager.ScopeName, new WindowScope());
		return windowScopeConfigurer;
	}

	@Bean
	public TldListenerRegistrationBean icefacesTldListenerRegistrationBean() {
		return TldListenerRegistrationBean.builder()
				.listener(ICEpushResourceHandlerLifecycle.class)
				.listener(SessionExpiredListener.class)
				.listener(WindowScopeManager.SetupTimer.class)
				.build();
	}

}
