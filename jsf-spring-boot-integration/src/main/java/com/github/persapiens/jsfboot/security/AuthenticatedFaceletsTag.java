package com.github.persapiens.jsfboot.security;


import org.springframework.security.taglibs.authz.AbstractAuthorizeTag;

/**
 * A concrete implementation of {@link AbstractAuthorizeTag} for use with standard Facelets rendering technology.
 */
public class AuthenticatedFaceletsTag extends AbstractFaceletsAuthorizeTag {

	/**
	 * A default constructor. Callers of this constructor are responsible for setting one or more of the tag attributes
	 * in {@link AbstractAuthorizeTag}.
	 */
	public AuthenticatedFaceletsTag() {
        setAccess("isAuthenticated()");
	}

}
