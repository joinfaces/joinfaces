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

package org.joinfaces.weld;

import lombok.extern.slf4j.Slf4j;
import org.jboss.weld.environment.servlet.Container;
import org.jboss.weld.environment.servlet.EnhancedListener;
import org.jboss.weld.environment.servlet.WeldServletLifecycle;
import org.joinfaces.servlet.ServletContainerInitializerRegistrationBean;

import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.util.StringUtils;

/**
 * {@link ServletContainerInitializerRegistrationBean} for WELD's {@link EnhancedListener}.
 * This also sets the {@value Container#CONTEXT_PARAM_CONTAINER_CLASS} based on the type of the embedded
 * server.
 *
 * @author Lars Grefer
 */
@Slf4j
public class WeldServletContainerInitializerRegistrationBean extends ServletContainerInitializerRegistrationBean<EnhancedListener> {

	/**
	 * A servlet-context init-param used by Weld, which can be used to force its initialization.
	 *
	 * @see WeldServletLifecycle
	 */
	public static final String CONTEXT_PARAM_ARCHIVE_ISOLATION = "org.jboss.weld.environment.servlet.archive.isolation";

	public WeldServletContainerInitializerRegistrationBean() {
		super(EnhancedListener.class);
	}

	@Override
	public void customize(ConfigurableServletWebServerFactory factory) {
		setContainerClass(factory);
		forceCdiInitialization(factory);

		super.customize(factory);
	}

	/**
	 * Force the initialization of WELD even with an empty BDA.
	 *
	 * @param factory ConfigurableServletWebServerFactory
	 * @see <a href="https://github.com/eclipse-ee4j/mojarra/issues/5232#issuecomment-1555916766">eclipse-ee4j/mojarra#5232</a>
	 */
	void forceCdiInitialization(ConfigurableServletWebServerFactory factory) {
		factory.addInitializers(servletContext -> {
			String currentArchiveIsolation = servletContext.getInitParameter(CONTEXT_PARAM_ARCHIVE_ISOLATION);
			if (StringUtils.hasText(currentArchiveIsolation)) {
				log.info("{} has already been set to {}", CONTEXT_PARAM_ARCHIVE_ISOLATION, currentArchiveIsolation);
			}
			else {
				log.debug("Setting {} to {}", CONTEXT_PARAM_ARCHIVE_ISOLATION, "false");
				servletContext.setInitParameter(CONTEXT_PARAM_ARCHIVE_ISOLATION, "false");
			}
		});
	}

	void setContainerClass(ConfigurableServletWebServerFactory factory) {
		Class<? extends Container> containerClass = NoopContainer.class;

		String containerClassName = containerClass.getName();
		factory.addInitializers(servletContext -> {
				String currentContainerClass = servletContext.getInitParameter(Container.CONTEXT_PARAM_CONTAINER_CLASS);
				if (StringUtils.hasText(currentContainerClass)) {
					log.info("{} has already been set to {}", Container.CONTEXT_PARAM_CONTAINER_CLASS, currentContainerClass);
				}
				else {
					log.debug("Setting {} to {}", Container.CONTEXT_PARAM_CONTAINER_CLASS, containerClassName);
					servletContext.setInitParameter(Container.CONTEXT_PARAM_CONTAINER_CLASS, containerClassName);
				}
			}
		);
	}
}
