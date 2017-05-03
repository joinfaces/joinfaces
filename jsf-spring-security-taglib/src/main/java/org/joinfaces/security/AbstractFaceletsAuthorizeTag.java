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

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.taglibs.authz.AbstractAuthorizeTag;

/**
 * Implement some abstract methods using jsf technology.
 * @author Marcelo Fernandes
 */
public class AbstractFaceletsAuthorizeTag extends AbstractAuthorizeTag {

	@Override
	protected ServletRequest getRequest() {
		return (ServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	@Override
	protected ServletResponse getResponse() {
		return (ServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	}

	@Override
	protected ServletContext getServletContext() {
		return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	}

}
