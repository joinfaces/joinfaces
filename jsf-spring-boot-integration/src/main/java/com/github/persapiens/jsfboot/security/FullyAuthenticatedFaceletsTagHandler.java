package com.github.persapiens.jsfboot.security;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;

import org.springframework.security.taglibs.authz.AbstractAuthorizeTag;

public class FullyAuthenticatedFaceletsTagHandler extends TagHandler {

	/**
	 * A default constructor. Callers of this constructor are responsible for setting one or more of the tag attributes
	 * in {@link AbstractAuthorizeTag}.
	 */
	public FullyAuthenticatedFaceletsTagHandler(TagConfig config) {
        super(config);
	}
    
	/**
	 * @see TagHandler#apply(FaceletContext, UIComponent)
	 */
    @Override
	public void apply(FaceletContext faceletContext, UIComponent parent) throws IOException {
		FullyAuthenticatedFaceletsTag fullyAuthenticatedTag = new FullyAuthenticatedFaceletsTag();

		boolean isAuthorized = fullyAuthenticatedTag.authorize();

		if (isAuthorized) {
			this.nextHandler.apply(faceletContext, parent);
		}
	}

}
