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

package org.joinfaces.configuration;

/**
 * Marker interface for all {@link org.springframework.boot.context.properties.ConfigurationProperties configuration properties}
 * which contain {@link ServletContextInitParameter init parameters} to be set on a {@link javax.servlet.ServletContext servlet context}.
 * <p>
 * Classes implementing this interface should
 * <ul>
 * <li>be annotated with {@link org.springframework.boot.context.properties.ConfigurationProperties @ConfigurationProperties()} and</li>
 * <li>annotate their fields with either {@link ServletContextInitParameter @ServletContextInitParameter()} or {@link NestedProperty @NestedProperty}</li>
 * </ul>
 *
 * @author Lars Grefer
 * @see ServletContextInitParameter
 * @see NestedProperty
 */
public interface ServletContextInitParameterConfigurationProperties {
}
