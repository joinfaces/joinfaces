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

package org.joinfaces.autoconfigure.myfaces;

import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import lombok.SneakyThrows;
import org.joinfaces.aot.ClassGraphRuntimeHintsRegistrar;

import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

/**
 * {@link RuntimeHintsRegistrar} for Apache MyFaces.
 *
 * @author Lars Grefer
 */
public class MyFacesRuntimeHintsRegistrar extends ClassGraphRuntimeHintsRegistrar {
	@SneakyThrows
	@Override
	public void registerHints(RuntimeHints hints, ClassLoader classLoader, ScanResult scanResult) {

		hints.reflection().registerTypeIfPresent(classLoader, "org.apache.myfaces.webapp.StartupServletContextListener", MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS);
		hints.reflection().registerTypeIfPresent(classLoader, "org.apache.myfaces.webapp.MyFacesContainerInitializer", MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS);
		hints.reflection().registerTypeIfPresent(classLoader, "org.apache.myfaces.ee.MyFacesContainerInitializer", MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS);

		hints.reflection().registerTypeIfPresent(classLoader, "jakarta.faces.context._MyFacesExternalContextHelper", MemberCategory.DECLARED_FIELDS);

		for (ClassInfo classInfo : scanResult.getAllClasses()) {
			if (classInfo.getPackageName().equals("org.apache.myfaces.spi") && classInfo.isAbstract()) {
				registerStandardClasses(scanResult.getSubclasses(classInfo.getName()), hints);
			}
		}

		hints.resources().registerPattern("META-INF/standard-faces-config.xml");
		hints.resources().registerPattern("org/apache/myfaces/resource/default.dtd");

	}

}
