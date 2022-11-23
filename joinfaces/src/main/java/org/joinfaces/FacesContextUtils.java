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

package org.joinfaces;

import jakarta.faces.context.ExternalContext;
import jakarta.servlet.ServletContext;

import lombok.experimental.UtilityClass;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Extension of Springs own {@link org.springframework.web.jsf.FacesContextUtils}.
 *
 * @author Lars Grefer
 */
@UtilityClass
public class FacesContextUtils extends org.springframework.web.jsf.FacesContextUtils {

	public static ServletContext getServletContext(ExternalContext externalContext) {
		return (ServletContext) externalContext.getContext();
	}

	public static WebApplicationContext getWebApplicationContext(ExternalContext externalContext) {
		return WebApplicationContextUtils.getWebApplicationContext(getServletContext(externalContext));
	}

	public static WebApplicationContext getRequiredWebApplicationContext(ExternalContext externalContext) {
		return WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext(externalContext));
	}
}
