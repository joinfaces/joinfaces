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

package org.joinfaces.autoconfigure.rewrite;

import java.util.Collections;
import java.util.EnumSet;
import java.util.LinkedHashSet;
import java.util.Set;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.DispatcherType;
import org.springframework.core.Ordered;

/**
 * {@link ConfigurationProperties} for the {@link org.ocpsoft.rewrite.servlet.RewriteFilter RewriteFilter}.
 *
 * @see RewriteAutoConfiguration#rewriteFilterRegistrationBean(RewriteFilterProperties)
 */
@Data
@ConfigurationProperties("joinfaces.rewrite.filter")
public class RewriteFilterProperties {

	/**
	 * If the RewriteFilter should be actively handled by JoinFaces.
	 */
	private boolean enabled = true;

	/**
	 * The order of the RewriteFilter.
	 */
	private int order = Ordered.LOWEST_PRECEDENCE;

	/**
	 * URL patterns for the RewriteFilter as defined in the Servlet Specification.
	 *
	 * @see org.springframework.boot.web.servlet.FilterRegistrationBean#addUrlPatterns(String...)
	 */
	private Set<String> urlPatterns = new LinkedHashSet<>(Collections.singleton("/*"));

	/**
	 * Dispatcher types for the RewriteFilter as defined in the Servlet Specification.
	 *
	 * @see org.springframework.boot.web.servlet.FilterRegistrationBean#setDispatcherTypes(EnumSet)
	 */
	private EnumSet<DispatcherType> dispatcherTypes = EnumSet.allOf(DispatcherType.class);
}
