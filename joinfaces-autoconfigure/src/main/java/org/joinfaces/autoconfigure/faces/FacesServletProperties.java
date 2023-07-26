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

package org.joinfaces.autoconfigure.faces;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * {@link ConfigurationProperties} for the {@link jakarta.faces.webapp.FacesServlet FacesServlet}-registration.
 *
 * The properties of this class are directly mapped to the
 * {@link org.springframework.boot.web.servlet.ServletRegistrationBean ServletRegistrationBean}
 *
 * @author Lars Grefer
 * @see FacesServletAutoConfiguration#facesServletRegistrationBean(FacesServletProperties)
 */
@Data
@ConfigurationProperties("joinfaces.faces-servlet")
public class FacesServletProperties {

	private String name = "FacesServlet";

	private int loadOnStartup = -1;

	private Set<String> urlMappings = new LinkedHashSet<>(Arrays.asList("/faces/*", "*.jsf", "*.faces", "*.xhtml"));

	/**
	 * If the FacesServlet should be actively handled by Joinfaces.
	 */
	private boolean enabled = true;

	private boolean asyncSupported = true;

	/**
	 * The order-property for the ServletRegistrationBean.
	 */
	private int order = 0;

	private boolean ignoreRegistrationFailure = true;

}
