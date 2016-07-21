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

import javax.el.ValueExpression;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagHandler;

import org.springframework.security.taglibs.authz.AbstractAuthorizeTag;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * A concrete implementation of {@link AbstractAuthorizeTag} for use with
 * standard Facelets rendering technology.
 * @author Marcelo Fernandes
 */
public class AuthorizeFaceletsTag extends AbstractFaceletsAuthorizeTag {

	/**
	 * A class constructor for use in a {@link TagHandler}. Accepts all possible
	 * tag attributes as {@link TagAttribute} instances. The constructor
	 * extracts the attribute values by evaluating them as Unified EL
	 * expressions. This excludes the access attribute, which is expected to be
	 * a Spring EL expression.
	 *
	 * @param faceletContext the current FaceletContext
	 * @param access the access attribute or null
	 * @param url the url attribute or null
	 * @param method the method attribute or null
	 * @param ifAllGranted the ifAllGranted attribute or null
	 * @param ifAnyGranted the ifAnyGranted attribute or null
	 * @param ifNotGranted the ifNotGranted attribute or null
	 */
	public AuthorizeFaceletsTag(FaceletContext faceletContext, TagAttribute access, TagAttribute url, TagAttribute method, TagAttribute ifAllGranted, TagAttribute ifAnyGranted, TagAttribute ifNotGranted) {
		setAccess(getAttributeValue(faceletContext, access, false));
		setUrl(getAttributeValue(faceletContext, url, true));
		setMethod(getAttributeValue(faceletContext, method, true));
		setIfAllGranted(getAttributeValue(faceletContext, ifAllGranted, true));
		setIfAnyGranted(getAttributeValue(faceletContext, ifAnyGranted, true));
		setIfNotGranted(getAttributeValue(faceletContext, ifNotGranted, true));
	}

	/**
	 * A default constructor. Callers of this constructor are responsible for
	 * setting one or more of the tag attributes in
	 * {@link AbstractAuthorizeTag}.
	 */
	public AuthorizeFaceletsTag() {
	}

	private static final String AND = " and ";

	void setIfAllGranted(String ifAllGranted) {
		String[] roles = StringUtils.tokenizeToStringArray(ifAllGranted, ",");
		if (!ObjectUtils.isEmpty(roles)) {
			String expression = toHasRoleExpression(roles);
			setAccess(getAccess() != null ? getAccess() + AND + expression : expression);
		}
	}

	void setIfAnyGranted(String ifAnyGranted) {
		String[] roles = StringUtils.tokenizeToStringArray(ifAnyGranted, ",");
		if (!ObjectUtils.isEmpty(roles)) {
			String expression = toHasAnyRoleExpression(roles, false);
			setAccess(getAccess() != null ? getAccess() + AND + expression : expression);
		}
	}

	void setIfNotGranted(String ifNotGranted) {
		String[] roles = StringUtils.tokenizeToStringArray(ifNotGranted, ",");
		if (!ObjectUtils.isEmpty(roles)) {
			String expression = toHasAnyRoleExpression(roles, true);
			setAccess(getAccess() != null ? getAccess() + AND + expression : expression);
		}
	}

	private static String toHasRoleExpression(String[] roles) {
		StringBuilder expression = new StringBuilder();
		boolean insertSeparator = false;
		for (String role : roles) {
			expression.append(insertSeparator ? AND : "");
			expression.append("hasRole('").append(role).append("')");
			insertSeparator = true;
		}
		return expression.toString();
	}

	private static String toHasAnyRoleExpression(String[] roles, boolean negate) {
		StringBuilder expression = new StringBuilder();
		expression = expression.append(negate ? "!" : "");
		expression = expression.append("hasAnyRole(");
		boolean insertSeparator = false;
		for (String role : roles) {
			expression.append(insertSeparator ? "," : "");
			expression.append('\'').append(role).append('\'');
			insertSeparator = true;
		}
		return expression.append(")").toString();
	}

	private String getAttributeValue(FaceletContext faceletContext, TagAttribute tagAttribute, boolean evaluate) {
		String value = null;
		if (tagAttribute != null) {
			if (evaluate) {
				ValueExpression expression = tagAttribute.getValueExpression(faceletContext, String.class);
				value = (String) expression.getValue(faceletContext.getFacesContext().getELContext());
			}
			else {
				value = tagAttribute.getValue();
			}
		}
		return value;
	}

}
