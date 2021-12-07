/*
 * Copyright 2016-2021 the original author or authors.
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

package org.joinfaces.session;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionContext;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * {@link javax.servlet.Filter Servlet filter} which re-sets session attributes in order to workaround
 * <a href="https://github.com/spring-projects/spring-session/issues/177">spring-projects/spring-session#177</a>.
 *
 * @author Lars Grefer
 */
public class SpringSessionFixFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		RequestWrapper wrappedRequest = new RequestWrapper(request);

		filterChain.doFilter(wrappedRequest, response);

		HttpSession session = request.getSession(false);

		if (session != null && wrappedRequest.sessionWrapper != null) {
			reSetAttributes(wrappedRequest.sessionWrapper);
		}

	}

	private void reSetAttributes(SessionWrapper sessionWrapper) {
		HttpSession realSession = sessionWrapper.delegate;
		Enumeration<String> attributeNames = realSession.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			String attributeName = attributeNames.nextElement();

			if (!sessionWrapper.readAttributeNames.contains(attributeName)) {
				//Attribute was not read, so we don't need to re-set it.
				continue;
			}

			Object attributeValue = realSession.getAttribute(attributeName);
			if (ClassUtils.isPrimitiveOrWrapper(attributeValue.getClass())) {
				//Attribute is primitive (immutable), so we don't need to re-set it.
				continue;
			}

			realSession.setAttribute(attributeName, attributeValue);
		}
	}

	/**
	 * Wrapper around an {@link HttpServletRequest} which wraps the {@link HttpServletRequest#getSession()}
	 * in a {@link SessionWrapper}.
	 *
	 * @author Lars Grefer
	 */
	static class RequestWrapper extends HttpServletRequestWrapper {
		@Nullable
		private SessionWrapper sessionWrapper;

		/**
		 * Constructs a request object wrapping the given request.
		 *
		 * @param request the {@link HttpServletRequest} to be wrapped.
		 * @throws IllegalArgumentException if the request is null
		 */
		RequestWrapper(HttpServletRequest request) {
			super(request);
		}

		@Override
		@Nullable
		public HttpSession getSession(boolean create) {
			if (this.sessionWrapper == null) {
				HttpSession session = super.getSession(create);
				if (session != null) {
					this.sessionWrapper = new SessionWrapper(session);
				}
			}
			return this.sessionWrapper;
		}

		@Override
		public HttpSession getSession() {
			if (this.sessionWrapper == null) {
				this.sessionWrapper = new SessionWrapper(super.getSession());
			}
			return this.sessionWrapper;
		}
	}

	/**
	 * Wrapper around a {@link HttpSession} which tracks the names of all read attributes.
	 *
	 * @author Lars Grefer
	 */
	@RequiredArgsConstructor
	static class SessionWrapper implements HttpSession {
		private final HttpSession delegate;

		@NonNull
		@Getter
		private final Set<String> readAttributeNames = new HashSet<>();

		@Override
		public long getCreationTime() {
			return this.delegate.getCreationTime();
		}

		@Override
		public String getId() {
			return this.delegate.getId();
		}

		@Override
		public long getLastAccessedTime() {
			return this.delegate.getLastAccessedTime();
		}

		@Override
		public ServletContext getServletContext() {
			return this.delegate.getServletContext();
		}

		@Override
		public void setMaxInactiveInterval(int interval) {
			this.delegate.setMaxInactiveInterval(interval);
		}

		@Override
		public int getMaxInactiveInterval() {
			return this.delegate.getMaxInactiveInterval();
		}

		@Override
		@Deprecated
		public HttpSessionContext getSessionContext() {
			return this.delegate.getSessionContext();
		}

		@Override
		@Nullable
		public Object getAttribute(String name) {
			this.readAttributeNames.add(name);
			return this.delegate.getAttribute(name);
		}

		@Override
		@Deprecated
		@Nullable
		public Object getValue(String name) {
			this.readAttributeNames.add(name);
			return this.delegate.getValue(name);
		}

		@Override
		public Enumeration<String> getAttributeNames() {
			return this.delegate.getAttributeNames();
		}

		@Override
		@Deprecated
		public String[] getValueNames() {
			return this.delegate.getValueNames();
		}

		@Override
		public void setAttribute(String name, Object value) {
			this.delegate.setAttribute(name, value);
		}

		@Override
		@Deprecated
		public void putValue(String name, Object value) {
			this.delegate.putValue(name, value);
		}

		@Override
		public void removeAttribute(String name) {
			this.delegate.removeAttribute(name);
		}

		@Override
		@Deprecated
		public void removeValue(String name) {
			this.delegate.removeValue(name);
		}

		@Override
		public void invalidate() {
			this.delegate.invalidate();
		}

		@Override
		public boolean isNew() {
			return this.delegate.isNew();
		}
	}
}
