package com.github.persapiens.jsfboot.security;

import java.io.IOException;

/**
 * This class provides static methods that are registered as EL functions and available for use in Unified EL
 * expressions in standard Facelets views.
 */
public class FaceletsTagUtils {

	/**
	 * Returns true if the user has all of of the given authorities.
	 * 
	 * @param authorities a comma-separated list of user authorities.
	 */
	public static boolean areAllGranted(String authorities) throws IOException {
		AuthorizeFaceletsTag authorizeTag = new AuthorizeFaceletsTag();
		authorizeTag.setIfAllGranted(authorities);
		return authorizeTag.authorize();
	}

	/**
	 * Returns true if the user has any of the given authorities.
	 * 
	 * @param authorities a comma-separated list of user authorities.
	 */
	public static boolean areAnyGranted(String authorities) throws IOException {
		AuthorizeFaceletsTag authorizeTag = new AuthorizeFaceletsTag();
		authorizeTag.setIfAnyGranted(authorities);
		return authorizeTag.authorize();
	}

	/**
	 * Returns true if the user does not have any of the given authorities.
	 * 
	 * @param authorities a comma-separated list of user authorities.
	 */
	public static boolean areNotGranted(String authorities) throws IOException {
		AuthorizeFaceletsTag authorizeTag = new AuthorizeFaceletsTag();
		authorizeTag.setIfNotGranted(authorities);
		return authorizeTag.authorize();
	}

	/**
	 * Returns true if the user is allowed to access the given URL and HTTP method combination. The HTTP method is
	 * optional and case insensitive.
	 */
	public static boolean isAllowed(String url, String method) throws IOException {
		AuthorizeFaceletsTag authorizeTag = new AuthorizeFaceletsTag();
		authorizeTag.setUrl(url);
		authorizeTag.setMethod(method);
		return authorizeTag.authorizeUsingUrlCheck();
	}

	/**
	 * Returns true if user is anonymous.
	 */
	public static boolean isAnonymous() throws IOException {
		AnonymousFaceletsTag anonymousTag = new AnonymousFaceletsTag();
		return anonymousTag.authorize();
	}

	/**
	 * Returns true if the user is not anonymous.
	 */
	public static boolean isAuthenticated() throws IOException {
		AuthenticatedFaceletsTag authenticatedTag = new AuthenticatedFaceletsTag();
		return authenticatedTag.authorize();
	}

	/**
	 * Returns true if the is not an anonymous or a remember-me user.
	 */
	public static boolean isFullyAuthenticated() throws IOException {
		FullyAuthenticatedFaceletsTag authenticatedTag = new FullyAuthenticatedFaceletsTag();
		return authenticatedTag.authorize();
	}

}
