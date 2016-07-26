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

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public final class AuthenticationFactory {

	private AuthenticationFactory() {
	}

	private static Collection<? extends GrantedAuthority> grantedAuthorities(String... authorities) {
		Collection<SimpleGrantedAuthority> result = new HashSet<SimpleGrantedAuthority>();
		for (String authority : authorities) {
			result.add(new SimpleGrantedAuthority(authority));
		}
		return result;
	}

	public static Authentication anonymous(String... authorities) {
		return new AnonymousAuthenticationToken("anonymous", "anonymous", grantedAuthorities(authorities));
	}

	public static Authentication authentication(String... authorities) {
		return new UsernamePasswordAuthenticationToken("user", "user", grantedAuthorities(authorities));
	}

}
