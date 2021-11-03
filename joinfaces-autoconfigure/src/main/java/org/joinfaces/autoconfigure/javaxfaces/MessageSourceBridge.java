/*
 * Copyright 2016-2019 the original author or authors.
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

package org.joinfaces.autoconfigure.javaxfaces;

import java.util.AbstractMap;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.lang.NonNull;

/**
 * This class wraps an {@link org.springframework.context.MessageSource MessageSource} and exposes it through the
 * {@link java.util.Map#get(Object) Map API} so it can be easily accessed from JSF.
 *
 * @author Lars Grefer
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class MessageSourceBridge extends AbstractMap<String, String> {

	@Setter
	private MessageSourceAccessor messageSourceAccessor;

	public MessageSourceBridge(MessageSource messageSource) {
		this.messageSourceAccessor = new MessageSourceAccessor(messageSource);
	}

	@Override
	public String get(Object key) {
		return this.messageSourceAccessor.getMessage((String) key);
	}

	@Override
	@NonNull
	public Set<Entry<String, String>> entrySet() {
		throw new UnsupportedOperationException();
	}
}
