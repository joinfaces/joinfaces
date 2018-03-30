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

package org.joinfaces.autoconfigure.rewrite;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import javax.servlet.DispatcherType;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Filter Configuration of Rewrite.
 *
 * @author Marcelo Fernandes
 */
@Data
@ConfigurationProperties(prefix = "rewrite")
public class RewriteFilterProperties {
	/**
	 * list of url patterns to rewrite filter.
	 */
	private List<String> urlPatterns = Arrays.asList("/*");

	private EnumSet<DispatcherType> dispatcherTypes = EnumSet.allOf(DispatcherType.class);

	private boolean enabled = true;

	private boolean asyncSupported = true;

	/**
	 * The order-property for the ServletRegistrationBean.
	 */
	private int order = 0;
}
