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

package org.joinfaces.security;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;

/**
 * TagHandler of fully authenticated user.
 * @author Marcelo Fernandes
 */
public class FullyAuthenticatedFaceletsTagHandler extends TagHandler {

	public FullyAuthenticatedFaceletsTagHandler(TagConfig config) {
		super(config);
	}

	@Override
	public void apply(FaceletContext faceletContext, UIComponent parent) throws IOException {
		FullyAuthenticatedFaceletsTag fullyAuthenticatedTag = new FullyAuthenticatedFaceletsTag();

		boolean isAuthorized = fullyAuthenticatedTag.authorize();

		if (isAuthorized) {
			this.nextHandler.apply(faceletContext, parent);
		}
	}

}
