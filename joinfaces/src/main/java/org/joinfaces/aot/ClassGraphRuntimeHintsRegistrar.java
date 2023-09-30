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

package org.joinfaces.aot;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;

import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.aot.hint.TypeReference;
import org.springframework.lang.Nullable;


/**
 * Abstract {@link RuntimeHintsRegistrar} which performs a Classpath scan using {@link ClassGraph}.
 *
 * @author Lars Grefer
 */
public abstract class ClassGraphRuntimeHintsRegistrar implements RuntimeHintsRegistrar {

	@Override
	public void registerHints(RuntimeHints hints, @Nullable ClassLoader classLoader) {
		scanHints(hints, classLoader);
	}

	public abstract void registerHints(RuntimeHints hints, @Nullable ClassLoader classLoader, ScanResult scanResult);

	void scanHints(RuntimeHints hints, @Nullable ClassLoader classLoader) {
		ClassGraph classGraph = new ClassGraph()
			.enableClassInfo();

		if (classLoader != null) {
			classGraph = classGraph.overrideClassLoaders(classLoader);
		}

		try (ScanResult scanResult = classGraph.scan()) {
			registerHints(hints, classLoader, scanResult);
		}
	}


	protected static void registerStandardClasses(ClassInfoList scanResult, RuntimeHints hints) {
		for (ClassInfo classInfo : scanResult) {
			if (classInfo.isStandardClass()) {
				hints.reflection().registerType(TypeReference.of(classInfo.getName()), MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS);
			}
		}
	}
}
