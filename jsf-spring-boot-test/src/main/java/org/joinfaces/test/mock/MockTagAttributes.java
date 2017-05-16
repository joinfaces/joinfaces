/*
 * Copyright 2016-2017 the original author or authors.
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

package org.joinfaces.test.mock;

import java.util.HashMap;
import java.util.Map;

import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagAttributes;

import lombok.Getter;

/**
 * Tag Attributes Mock.
 *
 * @author Marcelo Romulo Fernandes
 */
public class MockTagAttributes extends TagAttributes {

	private static final String NOT_SUPPORTED_YET = "Not supported yet.";

	@Getter
	private Map<String, TagAttribute> tagAttributes = new HashMap<String, TagAttribute>();

	@Override
	public TagAttribute[] getAll() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public TagAttribute get(String localName) {
		return this.tagAttributes.get(localName);
	}

	@Override
	public TagAttribute get(String ns, String localName) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public TagAttribute[] getAll(String namespace) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public String[] getNamespaces() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}
}
