/*
 * Copyright 2016-2016 the original author or authors.
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

package org.joinfaces;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.annotation.HandlesTypes;

import lombok.Builder;
import lombok.Data;

/**
 * Configuration of Jsf Class Factory.
 * @author Marcelo Fernandes
 */
@Data
@Builder
public class JsfClassFactoryConfiguration {

	/**
	 * {@link HandlesTypes} annotation found on the {@link ServletContainerInitializer}.
	 */
	private HandlesTypes handlesTypes;

	/**
	 * Another config resource to include in search.
	 */
	private String anotherConfig;

	/**
	 * Inform if exclude scoped annotations in search.
	 */
	private boolean excludeScopedAnnotations;
}
