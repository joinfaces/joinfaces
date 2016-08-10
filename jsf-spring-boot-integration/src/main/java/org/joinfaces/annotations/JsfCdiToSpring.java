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

package org.joinfaces.annotations;

import java.util.Set;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.core.type.MethodMetadata;
import org.springframework.web.context.WebApplicationContext;

/**
 * Convert jsf and cdi enterprise annotation types to spring scope.
 * @author Marcelo Fernandes
 */
final class JsfCdiToSpring {

	/**
	 * Constant for request scope.
	 */
	static final String REQUEST = WebApplicationContext.SCOPE_REQUEST;
	/**
	 * Constant for session scope.
	 */
	static final String SESSION = WebApplicationContext.SCOPE_SESSION;
	/**
	 * Constant for singleton scope.
	 */
	static final String SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;
	/**
	 * Constant for prototype scope.
	 */
	static final String PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;
	/**
	 * Constant for view scope.
	 */
	static final String VIEW = "view";

	protected JsfCdiToSpring() {
	}

	static String deduceScopeName(Set<String> annotationTypes) {
		String result = null;
		if (annotationTypes != null && !annotationTypes.isEmpty()) {
			if (annotationTypes.contains(
				javax.enterprise.context.RequestScoped.class.getName())
				|| annotationTypes.contains(
					javax.faces.bean.RequestScoped.class.getName())) {
				result = REQUEST;
			}
			else if (annotationTypes.contains(
				javax.enterprise.context.SessionScoped.class.getName())
				|| annotationTypes.contains(
					javax.faces.bean.SessionScoped.class.getName())) {
				result = SESSION;
			}
			else if (annotationTypes.contains(
				javax.enterprise.context.ApplicationScoped.class.getName())
				|| annotationTypes.contains(
					javax.faces.bean.ApplicationScoped.class.getName())) {
				result = SINGLETON;
			}
			else if (annotationTypes.contains(
				javax.faces.bean.NoneScoped.class.getName())) {
				result = PROTOTYPE;
			}
			else if (annotationTypes.contains(
				javax.faces.view.ViewScoped.class.getName())
				|| annotationTypes.contains(
					javax.faces.bean.ViewScoped.class.getName())) {
				result = VIEW;
			}
			else if (annotationTypes.contains(
				javax.enterprise.context.ConversationScoped.class.getName())) {
				result = SESSION;
			}
		}
		return result;
	}

	static String deduceScopeName(MethodMetadata methodMetadata) {
		String result = null;
		if (methodMetadata != null) {
			if (methodMetadata.isAnnotated(javax.enterprise.context.RequestScoped.class.getName())
				|| methodMetadata.isAnnotated(javax.faces.bean.RequestScoped.class.getName())) {
				result = REQUEST;
			}
			else if (methodMetadata.isAnnotated(javax.enterprise.context.SessionScoped.class.getName())
				|| methodMetadata.isAnnotated(javax.faces.bean.SessionScoped.class.getName())) {
				result = SESSION;
			}
			else if (methodMetadata.isAnnotated(javax.enterprise.context.ApplicationScoped.class.getName())
				|| methodMetadata.isAnnotated(javax.faces.bean.ApplicationScoped.class.getName())) {
				result = SINGLETON;
			}
			else if (methodMetadata.isAnnotated(javax.faces.bean.NoneScoped.class.getName())) {
				result = PROTOTYPE;
			}
			else if (methodMetadata.isAnnotated(javax.faces.view.ViewScoped.class.getName())
				|| methodMetadata.isAnnotated(javax.faces.bean.ViewScoped.class.getName())) {
				result = VIEW;
			}
			else if (methodMetadata.isAnnotated(javax.enterprise.context.ConversationScoped.class.getName())) {
				result = SESSION;
			}
		}

		return result;

	}
}
