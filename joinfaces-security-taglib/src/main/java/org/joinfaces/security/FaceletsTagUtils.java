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

/**
 * This class provides static methods that are registered as EL functions and
 * available for use in Unified EL expressions in standard Facelets views.
 * @author Marcelo Fernandes
 */
public final class FaceletsTagUtils {

	protected FaceletsTagUtils() {
	}

	/**
	 * Returns true if the user has all of of the given authorities.
	 *
	 * @param authorities a comma-separated list of user authorities.
	 * @return computation if the user has all of of the given authorities
	 * @throws IOException io exception
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
	 * @return computation if the user has any of the given authorities.
	 * @throws IOException io exception
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
	 * @return computation if the user does not have any of the given authorities.
	 * @throws IOException io exception
	 */
	public static boolean areNotGranted(String authorities) throws IOException {
		AuthorizeFaceletsTag authorizeTag = new AuthorizeFaceletsTag();
		authorizeTag.setIfNotGranted(authorities);
		return authorizeTag.authorize();
	}

	/**
	 * Returns true if the user is allowed to access the given URL and HTTP
	 * method combination. The HTTP method is optional and case insensitive.
	 * @param url to be accessed.
	 * @param method to be called.
	 * @return computation if the user is allowed to access the given URL and HTTP method.
	 * @throws IOException io exception
	 */
	public static boolean isAllowed(String url, String method) throws IOException {
		AuthorizeFaceletsTag authorizeTag = new AuthorizeFaceletsTag();
		authorizeTag.setUrl(url);
		authorizeTag.setMethod(method);
		return authorizeTag.authorizeUsingUrlCheck();
	}

	/**
	 * Returns true if user is anonymous.
	 * @return computation if the user is anonymous.
	 * @throws IOException io exception
	 */
	public static boolean isAnonymous() throws IOException {
		AnonymousFaceletsTag anonymousTag = new AnonymousFaceletsTag();
		return anonymousTag.authorize();
	}

	/**
	 * Returns true if the user is not anonymous.
	 * @return computation if the user is not anonymous.
	 * @throws IOException io exception
	 */
	public static boolean isAuthenticated() throws IOException {
		AuthenticatedFaceletsTag authenticatedTag = new AuthenticatedFaceletsTag();
		return authenticatedTag.authorize();
	}

	/**
	 * Returns true if the is not an anonymous or a remember-me user.
	 * @return computation if the user is not an anonymous or a remember-me user.
	 * @throws IOException io exception
	 */
	public static boolean isFullyAuthenticated() throws IOException {
		FullyAuthenticatedFaceletsTag authenticatedTag = new FullyAuthenticatedFaceletsTag();
		return authenticatedTag.authorize();
	}

}
