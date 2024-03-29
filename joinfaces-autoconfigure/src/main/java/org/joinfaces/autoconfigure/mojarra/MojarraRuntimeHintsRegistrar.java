/*
 * Copyright 2016-2023 the original author or authors.
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

package org.joinfaces.autoconfigure.mojarra;

import com.sun.faces.config.ConfigureListener;
import com.sun.faces.config.manager.spi.FilterClassesFromFacesInitializerAnnotationProvider;
import lombok.SneakyThrows;

import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

/**
 * {@link RuntimeHintsRegistrar} for Mojarra.
 *
 * @author Lars Grefer
 */
public class MojarraRuntimeHintsRegistrar implements RuntimeHintsRegistrar {
	@SneakyThrows
	@Override
	public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
		hints.resources().registerResourceBundle("jakarta.faces.LogStrings");

		hints.reflection().registerType(ConfigureListener.class, MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS);

		hints.reflection().registerType(FilterClassesFromFacesInitializerAnnotationProvider.class, MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS);
	}

}
