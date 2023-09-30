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

import jakarta.servlet.ServletContext;

import lombok.experimental.UtilityClass;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;

/**
 * Utility Methods for working with {@link ServletContext}s.
 *
 * @author Lars Grefer
 */
@UtilityClass
public class ServletContextUtils {

	/**
	 * Jetty-safe variant of calling {@link ServletContext#getClassLoader()}.
	 *
	 * @param servletContext The {@link ServletContext} on which {@link ServletContext#getClassLoader()} will be called.
	 * @return The {@link ClassLoader} of the given {@link ServletContext}.
	 */
	public ClassLoader getClassLoader(ServletContext servletContext) {
		try {
			return servletContext.getClassLoader();
		}
		catch (UnsupportedOperationException e) {
			if (servletContext instanceof ServletContextHandler.ServletContextApi  jettyContext) {
				boolean enabled = jettyContext.isEnabled();
				try {
					jettyContext.setEnabled(true);
					return servletContext.getClassLoader();
				}
				finally {
					jettyContext.setEnabled(enabled);
				}
			}
			else {
				throw e;
			}
		}
	}
}
