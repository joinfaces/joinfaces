package com.github.persapiens.jsfboot.security;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.taglibs.authz.AbstractAuthorizeTag;

public class FaceletsAuthenticatedTagHandler extends TagHandler {

	/**
	 * A default constructor. Callers of this constructor are responsible for setting one or more of the tag attributes
	 * in {@link AbstractAuthorizeTag}.
	 */
	public FaceletsAuthenticatedTagHandler(TagConfig config) {
        super(config);
	}

    public static boolean authenticated () {
        return SecurityContextHolder.getContext().getAuthentication() != null;
    }
    
	/**
	 * @see TagHandler#apply(FaceletContext, UIComponent)
	 */
	public void apply(FaceletContext faceletContext, UIComponent parent) throws IOException {
		boolean isAuthorized = authenticated();

		if (isAuthorized) {
			this.nextHandler.apply(faceletContext, parent);
		}
	}

}
