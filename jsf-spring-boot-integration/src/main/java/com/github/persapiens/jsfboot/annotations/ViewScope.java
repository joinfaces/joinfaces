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

import java.util.Map;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * Implementation of view scope.
 * @author Marcelo Fernandes
 */
public class ViewScope implements Scope {

	@Override
	public Object get(String name, ObjectFactory objectFactory) {
		Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();

		if (viewMap.containsKey(name)) {
			return viewMap.get(name);
		}
		else {
			Object object = objectFactory.getObject();
			viewMap.put(name, object);

			return object;
		}
	}

	@Override
	public Object remove(String name) {
		return FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove(name);
	}

	@Override
	public String getConversationId() {
		return null;
	}

	@Override
	public void registerDestructionCallback(String name, Runnable callback) {
		//Not supported
	}

	@Override
	public Object resolveContextualObject(String key) {
		return null;
	}
}
