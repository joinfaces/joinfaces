/*
 * Copyright 2016-2019 the original author or authors.
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

package org.joinfaces.tools;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ClassRefTypeSignature;
import io.github.classgraph.MethodInfo;
import io.github.classgraph.MethodTypeSignature;
import io.github.classgraph.ScanResult;
import io.github.classgraph.TypeArgument;

/**
 * {@link ScanResultHandler} for Rewrite {@link org.ocpsoft.rewrite.annotation.spi.AnnotationHandler}s.
 *
 * @author Lars Grefer
 */
public class RewriteAnnotationProviderHandler extends ScanResultHandler {

	private static final String REWRITE_ANNOTATION_HANDLER = "org.ocpsoft.rewrite.annotation.spi.AnnotationHandler";

	@Override
	public void handle(ScanResult scanResult, File joinfacesBaseDir) throws IOException {
		ClassInfoList annotationHandlers = scanResult.getClassesImplementing(REWRITE_ANNOTATION_HANDLER);

		if (annotationHandlers.isEmpty()) {
			return;
		}

		List<String> annotationClassNames = annotationHandlers.stream()
				.map(classInfo -> classInfo.getDeclaredMethodInfo("handles"))
				.flatMap(Collection::stream)
				.map(MethodInfo::getTypeSignature)
				.map(MethodTypeSignature::getResultType)
				.filter(ClassRefTypeSignature.class::isInstance)
				.map(ClassRefTypeSignature.class::cast)
				.map(classRefTypeSignature -> classRefTypeSignature.getTypeArguments().get(0))
				.map(TypeArgument::getTypeSignature)
				.filter(ClassRefTypeSignature.class::isInstance)
				.map(ClassRefTypeSignature.class::cast)
				.map(ClassRefTypeSignature::getFullyQualifiedClassName)
				.collect(Collectors.toList());

		File resultFile = new File(joinfacesBaseDir, REWRITE_ANNOTATION_HANDLER + ".classes");

		SortedSet<String> result = new TreeSet<>(String::compareTo);

		for (String annotationClassName : annotationClassNames) {
			result.addAll(scanResult.getClassesWithAnnotation(annotationClassName).getNames());
			result.addAll(scanResult.getClassesWithFieldAnnotation(annotationClassName).getNames());
			result.addAll(scanResult.getClassesWithMethodAnnotation(annotationClassName).getNames());
		}

		writeClassList(resultFile, result);
	}
}
