/*
 * Copyright 2016-2016 the original author or authors.
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

package org.joinfaces.autoconfigure.myfaces;

import javax.servlet.ServletContainerInitializer;

import org.apache.myfaces.ee.MyFacesContainerInitializer;
import org.apache.myfaces.webapp.StartupServletContextListener;
import org.joinfaces.autoconfigure.javaxfaces.JavaxFacesAutoConfiguration;
import org.joinfaces.autoconfigure.servlet.WebFragmentRegistrationBean;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot Auto Configuration of MyFaces.
 *
 * @author Marcelo Fernandes
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({MyfacesProperties.class, MyFaces2_3Properties.class})
@ConditionalOnClass(MyFacesContainerInitializer.class)
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@AutoConfigureAfter({JavaxFacesAutoConfiguration.class})
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class MyFacesAutoConfiguration {

	@Bean
	public MyFacesInitializerRegistrationBean myFacesServletContainerInitializer() {
		return new MyFacesInitializerRegistrationBean();
	}

	/**
	 * This {@link WebFragmentRegistrationBean} is equivalent to the
	 * {@code META-INF/web-fragment.xml} of the {@code myfaces-impl.jar}.
	 *
	 * @return myFacesWebFragmentRegistrationBean
	 */
	@Bean
	@ConditionalOnClass(name = "org.apache.myfaces.webapp.StartupServletContextListener")
	public WebFragmentRegistrationBean myFacesWebFragmentRegistrationBean() {
		WebFragmentRegistrationBean webFragmentRegistrationBean = new WebFragmentRegistrationBean();
		webFragmentRegistrationBean.getListeners().add(StartupServletContextListener.class);
		return webFragmentRegistrationBean;
	}
}
