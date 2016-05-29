package com.github.persapiens.jsfboot.security;

import java.io.IOException;

/**
 * This class provides static methods that are registered as EL functions and available for use in Unified EL
 * expressions in standard Facelets views.
 */
public abstract class FaceletsTagUtils {

	/**
	 * Returns true if the user has all of of the given authorities.
	 * 
	 * @param authorities a comma-separated list of user authorities.
	 */
	public static boolean areAllGranted(String authorities) throws IOException {
		FaceletsAuthorizeTag authorizeTag = new FaceletsAuthorizeTag();
		authorizeTag.setIfAllGranted(authorities);
		return authorizeTag.authorize();
	}

	/**
	 * Returns true if the user has any of the given authorities.
	 * 
	 * @param authorities a comma-separated list of user authorities.
	 */
	public static boolean areAnyGranted(String authorities) throws IOException {
		FaceletsAuthorizeTag authorizeTag = new FaceletsAuthorizeTag();
		authorizeTag.setIfAnyGranted(authorities);
		return authorizeTag.authorize();
	}

	/**
	 * Returns true if the user does not have any of the given authorities.
	 * 
	 * @param authorities a comma-separated list of user authorities.
	 */
	public static boolean areNotGranted(String authorities) throws IOException {
		FaceletsAuthorizeTag authorizeTag = new FaceletsAuthorizeTag();
		authorizeTag.setIfNotGranted(authorities);
		return authorizeTag.authorize();
	}

	/**
	 * Returns true if the user is allowed to access the given URL and HTTP method combination. The HTTP method is
	 * optional and case insensitive.
	 */
	public static boolean isAllowed(String url, String method) throws IOException {
		FaceletsAuthorizeTag authorizeTag = new FaceletsAuthorizeTag();
		authorizeTag.setUrl(url);
		authorizeTag.setMethod(method);
		return authorizeTag.authorizeUsingUrlCheck();
	}

	/**
	 * Returns true if the user is authenticated.
	 */
	public static boolean isAuthenticated() throws IOException {
		return FaceletsAuthenticatedTagHandler.authenticated();
	}

	/**
	 * Returns true if no user is authenticated.
	 */
	public static boolean isAnonymous() throws IOException {
		return FaceletsAnonymousTagHandler.anonymous();
	}

}
