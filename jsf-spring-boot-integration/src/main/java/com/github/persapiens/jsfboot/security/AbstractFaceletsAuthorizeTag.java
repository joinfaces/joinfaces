package com.github.persapiens.jsfboot.security;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.taglibs.authz.AbstractAuthorizeTag;

/**
 * Implement some abstract methods using jsf technology
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
