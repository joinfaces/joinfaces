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

package org.joinfaces.mojarra;

import java.util.Set;

import javax.servlet.annotation.HandlesTypes;

import com.sun.faces.config.FacesInitializer;
import org.joinfaces.JsfClassFactory;
import org.joinfaces.JsfClassFactoryConfiguration;
import org.joinfaces.ServletContainerInitializerRegistrationBean;

/**
 * Servlet Context Initializer of Mojarra.
 * @author Marcelo Fernandes
 */
public class MojarraServletContextInitializer extends ServletContainerInitializerRegistrationBean<FacesInitializer> {

	/**
	 * Constant of another faces config of mojarra.
	 */
	public static final String ANOTHER_CONFIG = "com/sun/faces/jsf-ri-runtime.xml";

	public MojarraServletContextInitializer() {
		super(FacesInitializer.class);
	}

	@Override
	protected Set<Class<?>> getClasses(HandlesTypes handlesTypes) {
		JsfClassFactory jsfClassFactory = new JsfClassFactory(JsfClassFactoryConfiguration.builder()
				.anotherConfig(ANOTHER_CONFIG)
				.handlesTypes(handlesTypes)
				.excludeScopedAnnotations(true)
				.build());
		return jsfClassFactory.getAllClasses();
	}
}
