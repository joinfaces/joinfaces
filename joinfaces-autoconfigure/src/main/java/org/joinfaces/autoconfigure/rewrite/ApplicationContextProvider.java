/*
 * Copyright 2016-2017 the original author or authors.
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

package org.joinfaces.autoconfigure.rewrite;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Getter;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.Nullable;

@SuppressFBWarnings("ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD")
public class ApplicationContextProvider implements ApplicationContextAware, DisposableBean {

	@Nullable
	@Getter
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext newApplicationContext) throws BeansException {
		applicationContext = newApplicationContext;
	}

	@Override
	public void destroy() {
		applicationContext = null;
	}
}
