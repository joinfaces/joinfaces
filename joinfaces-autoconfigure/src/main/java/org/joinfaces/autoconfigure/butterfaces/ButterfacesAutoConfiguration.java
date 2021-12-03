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

package org.joinfaces.autoconfigure.butterfaces;

import lombok.extern.slf4j.Slf4j;
import org.butterfaces.util.ReflectionUtil;
import org.joinfaces.autoconfigure.javaxfaces.JavaxFacesAutoConfiguration;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot Auto Configuration of ButterFaces.
 *
 * @author Marcelo Fernandes
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(ButterfacesProperties.class)
@ConditionalOnClass(ReflectionUtil.class)
@AutoConfigureBefore(JavaxFacesAutoConfiguration.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@Slf4j
public class ButterfacesAutoConfiguration {

}
