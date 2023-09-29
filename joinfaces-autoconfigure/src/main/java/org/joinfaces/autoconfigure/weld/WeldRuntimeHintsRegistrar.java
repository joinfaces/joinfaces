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

package org.joinfaces.autoconfigure.weld;

import jakarta.enterprise.event.Event;
import jakarta.enterprise.inject.spi.Extension;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import lombok.SneakyThrows;
import org.jboss.weld.bootstrap.api.helpers.RegistrySingletonProvider;
import org.jboss.weld.environment.logging.WeldEnvironmentLogger;
import org.jboss.weld.logging.WeldLogger;

import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.aot.hint.TypeReference;
import org.springframework.lang.Nullable;

/**
 * {@link RuntimeHintsRegistrar} for Weld.
 *
 * @author Lars Grefer
 */
public class WeldRuntimeHintsRegistrar implements RuntimeHintsRegistrar {
	@SneakyThrows
	@Override
	public void registerHints(RuntimeHints hints, @Nullable ClassLoader classLoader) {
		scanHints(hints, classLoader);

		hints.reflection().registerType(RegistrySingletonProvider.class, MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS);

		hints.proxies().registerJdkProxy(Event.class);
	}

	void scanHints(RuntimeHints hints, @Nullable ClassLoader classLoader) {
		ClassGraph classGraph = new ClassGraph()
			.enableClassInfo();

		if (classLoader != null) {
			classGraph = classGraph.overrideClassLoaders(classLoader);
		}

		try (ScanResult scanResult = classGraph.scan()) {
			for (ClassInfo loggerImpl : scanResult.getClassesImplementing(WeldLogger.class)) {
				registerLogger(hints, loggerImpl);
			}
			for (ClassInfo loggerImpl : scanResult.getClassesImplementing(WeldEnvironmentLogger.class)) {
				registerLogger(hints, loggerImpl);
			}

			for (ClassInfo classInfo : scanResult.getClassesImplementing(Extension.class)) {
				hints.reflection().registerType(TypeReference.of(classInfo.getName()), MemberCategory.INVOKE_DECLARED_METHODS);
			}
		}
	}

	void registerLogger(RuntimeHints hints, ClassInfo loggerImpl) {
		String implName = loggerImpl.getName();
		if (implName.endsWith("_$logger")) {
			hints.reflection().registerType(TypeReference.of(implName), builder -> {
				String interfaceName = implName.replace("_$logger", "");
				builder.onReachableType(TypeReference.of(interfaceName)).withMembers(MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS);
			});
		}
	}
}
