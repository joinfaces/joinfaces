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

package org.joinfaces.autoconfigure.mojarra;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;

import com.sun.faces.config.FacesInitializer;
import io.github.classgraph.ScanResult;
import lombok.AccessLevel;
import lombok.Getter;
import org.joinfaces.autoconfigure.FacesAnnotationProviderUtil;
import org.joinfaces.servlet.ServletContainerInitializerRegistrationBean;

/**
 * Servlet Context Initializer of Mojarra.
 *
 * @author Lars Grefer
 */
public class MojarraInitializerRegistrationBean extends ServletContainerInitializerRegistrationBean<FacesInitializer> {

	@Getter(AccessLevel.PACKAGE)
	private Map<Class<? extends Annotation>, Set<Class<?>>> annotatedClasses;

	public MojarraInitializerRegistrationBean() {
		super(FacesInitializer.class);
	}

	@Override
	protected void handleScanResult(ScanResult scanResult) {
		super.handleScanResult(scanResult);

		this.annotatedClasses = FacesAnnotationProviderUtil.findAnnotatedClasses(scanResult);
	}
}
