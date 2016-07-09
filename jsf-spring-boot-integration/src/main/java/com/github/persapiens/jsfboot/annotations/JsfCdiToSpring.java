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

package com.github.persapiens.jsfboot.annotations;

import java.util.Set;

/**
 * Convert jsf and cdi enterprise annotation types to spring scope
 */
public final class JsfCdiToSpring {

	public final static String REQUEST = "request";
	public final static String SESSION = "session";
	public final static String SINGLETON = "singleton";
	public final static String PROTOTYPE = "prototype";
	public final static String VIEW = "view";

	private JsfCdiToSpring() {
	}

	public static String scopeName(Set<String> annotationTypes) {
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
}
