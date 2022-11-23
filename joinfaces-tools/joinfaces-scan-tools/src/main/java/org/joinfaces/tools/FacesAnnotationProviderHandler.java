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

package org.joinfaces.tools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.github.classgraph.ScanResult;

/**
 * {@link ScanResultHandler} for the {@link org.apache.myfaces.spi.AnnotationProvider}-SPI.
 *
 * @author Lars Grefer
 */
public abstract class FacesAnnotationProviderHandler extends ScanResultHandler {

	private final String spiClassName;
	private List<String> annotations = new ArrayList<>(List.of(
			"jakarta.faces.bean.ManagedBean",
			"jakarta.faces.component.FacesComponent",
			"jakarta.faces.component.behavior.FacesBehavior",
			"jakarta.faces.convert.FacesConverter",
			"jakarta.faces.event.NamedEvent",
			"jakarta.faces.render.FacesRenderer",
			"jakarta.faces.render.FacesBehaviorRenderer",
			"jakarta.faces.validator.FacesValidator"
	));

	protected FacesAnnotationProviderHandler(String spiClassName) {
		this.spiClassName = spiClassName;
	}

	@Override
	public void handle(ScanResult scanResult, File classpathRoot) throws IOException {
		if (scanResult.getClassInfo(this.spiClassName) == null) {
			return;
		}

		Map<String, Set<String>> result = new LinkedHashMap<>();

		for (String annotation : this.annotations) {
			Set<String> annotatedClasses = new LinkedHashSet<>();

			annotatedClasses.addAll(scanResult.getClassesWithAnnotation(annotation).getNames());
			annotatedClasses.addAll(scanResult.getClassesWithMethodAnnotation(annotation).getNames());
			annotatedClasses.addAll(scanResult.getClassesWithFieldAnnotation(annotation).getNames());

			result.put(annotation, annotatedClasses);
		}

		File resultFile = new File(classpathRoot, "META-INF/joinfaces/" + this.spiClassName + ".classes");

		writeClassMap(resultFile, result);

	}
}
