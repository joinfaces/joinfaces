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

package org.joinfaces.integration;

import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurettin Yilmaz
 */
@Configuration
public class ScopedBeansConfiguration {

	@Bean
	@ViewScoped
	public ViewScopedBean viewScopedBean() {
		return new ViewScopedBean();
	}

	@Bean
	@SessionScoped
	public SessionScopedBean sessionScopedBean() {
		return new SessionScopedBean();
	}

	@Bean
	public NoScopedBean noScopedBean() {
		return new NoScopedBean();
	}

	static class ViewScopedBean {
	}

	static class SessionScopedBean {
	}

	static class NoScopedBean {
	}

}
