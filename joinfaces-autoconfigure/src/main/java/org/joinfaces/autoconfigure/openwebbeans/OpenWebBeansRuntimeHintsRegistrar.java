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

package org.joinfaces.autoconfigure.openwebbeans;

import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import org.apache.webbeans.servlet.WebBeansConfigurationListener;
import org.joinfaces.aot.ClassGraphRuntimeHintsRegistrar;

import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;

public class OpenWebBeansRuntimeHintsRegistrar extends ClassGraphRuntimeHintsRegistrar {

	@Override
	public void registerHints(RuntimeHints hints, ClassLoader classLoader, ScanResult scanResult) {
		hints.resources().registerResourceBundle("openwebbeans/Messages");
		hints.resources().registerPattern("META-INF/openwebbeans/openwebbeans.properties");

		hints.reflection().registerType(WebBeansConfigurationListener.Auto.class, MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS);

		for (ClassInfo interfaceInfo : scanResult.getAllInterfaces()) {
			if (interfaceInfo.getPackageName().startsWith("org.apache.webbeans.spi")) {

				registerStandardClasses(scanResult.getClassesImplementing(interfaceInfo.getName()), hints);

			}
		}
	}
}
