/*
 * Copyright 2016-2019 the original author or authors.
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

package org.joinfaces.security.taglib;

import java.io.IOException;

import jakarta.faces.component.UIComponent;
import jakarta.faces.view.facelets.FaceletContext;
import jakarta.faces.view.facelets.TagAttribute;
import jakarta.faces.view.facelets.TagConfig;
import jakarta.faces.view.facelets.TagHandler;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * A standard Facelets {@link TagHandler} for performing Spring Security
 * authorization decisions. The tag supports the following combinations
 * attributes for authorization:
 * <ul>
 * <li>access</li>
 * <li>url, method</li>
 * <li>ifAllGranted, ifAnyGranted, ifNotGranted</li>
 * </ul>
 * The var attribute can be used to store the result of the authorization
 * decision for later use in the view.
 *
 * @author Marcelo Fernandes
 * @see org.springframework.faces.security.FaceletsAuthorizeTagHandler
 */
public class FaceletsAuthorizeTagHandler extends TagHandler {

	private final TagAttribute access;
	private final TagAttribute url;
	private final TagAttribute method;
	private final TagAttribute ifAllGranted;
	private final TagAttribute ifAnyGranted;
	private final TagAttribute ifNotGranted;
	private final TagAttribute var;

	public FaceletsAuthorizeTagHandler(TagConfig config) {
		super(config);
		this.access = this.getAttribute("access");
		this.url = this.getAttribute("url");
		this.method = this.getAttribute("method");
		this.ifAllGranted = this.getAttribute("ifAllGranted");
		this.ifAnyGranted = this.getAttribute("ifAnyGranted");
		this.ifNotGranted = this.getAttribute("ifNotGranted");
		this.var = this.getAttribute("var");
	}

	@Override
	public void apply(FaceletContext faceletContext, UIComponent parent) throws IOException {
		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			return;
		}

		FaceletsAuthorizeTag authorizeTag = new FaceletsAuthorizeTag(faceletContext, this.access, this.url, this.method, this.ifAllGranted, this.ifAnyGranted, this.ifNotGranted);

		boolean isAuthorized = authorizeTag.authorize();

		if (isAuthorized) {
			this.nextHandler.apply(faceletContext, parent);
		}

		if (this.var != null) {
			faceletContext.setAttribute(this.var.getValue(faceletContext), isAuthorized);
		}
	}

}
