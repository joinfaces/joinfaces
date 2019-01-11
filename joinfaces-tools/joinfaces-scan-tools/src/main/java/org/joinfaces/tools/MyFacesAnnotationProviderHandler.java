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
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.github.classgraph.ScanResult;

/**
 * {@link ScanResultHandler} for the {@link org.apache.myfaces.spi.AnnotationProvider}-SPI.
 *
 * @author Lars Grefer
 */
public class MyFacesAnnotationProviderHandler extends ScanResultHandler {

	private static final String MYFACES_ANNOTATION_PROVIDER = "org.apache.myfaces.spi.AnnotationProvider";
	private static final List<String> MYFACES_ANNOTATIONS = Collections.unmodifiableList(Arrays.asList(
			"javax.faces.bean.ManagedBean",
			"javax.faces.component.FacesComponent",
			"javax.faces.component.behavior.FacesBehavior",
			"javax.faces.convert.FacesConverter",
			"javax.faces.event.NamedEvent",
			"javax.faces.render.FacesRenderer",
			"javax.faces.render.FacesBehaviorRenderer",
			"javax.faces.validator.FacesValidator"
	));

	@Override
	public void handle(ScanResult scanResult, File joinfacesBaseDir) throws IOException {
		if (scanResult.getClassInfo(MYFACES_ANNOTATION_PROVIDER) == null) {
			return;
		}

		Map<String, List<String>> result = new LinkedHashMap<>();

		for (String myfacesAnnotation : MYFACES_ANNOTATIONS) {
			result.put(myfacesAnnotation, scanResult.getClassesWithAnnotation(myfacesAnnotation).getNames());
		}

		File resultFile = new File(joinfacesBaseDir, MYFACES_ANNOTATION_PROVIDER + ".classes");

		writeClassMap(resultFile, result);

	}
}
