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

package org.joinfaces.autoconfigure.omnifaces;

import java.util.Set;

import javax.servlet.annotation.HandlesTypes;

import org.joinfaces.autoconfigure.ServletContainerInitializerRegistrationBean;
import org.omnifaces.facesviews.FacesViewsInitializer;

/**
 * Servlet context initializer of OmniFaces.
 * @author Marcelo Fernandes
 */
public class OmnifacesInitializerRegistrationBean extends ServletContainerInitializerRegistrationBean<FacesViewsInitializer> {

	public OmnifacesInitializerRegistrationBean() {
		super(FacesViewsInitializer.class);
	}

	@Override
	protected Set<Class<?>> getClasses(HandlesTypes handlesTypes) {
		throw new IllegalStateException(FacesViewsInitializer.class + " is not annotated with " + HandlesTypes.class);
	}
}
