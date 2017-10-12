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

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * An {@link org.springframework.boot.autoconfigure.EnableAutoConfiguration AutoConfiguration}
 * which registers the {@link ViewScope} scope.
 *
 * @author Lars Grefer
 */
@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({FacesContext.class, UIViewRoot.class})
public class ViewScopeAutoConfiguration {

	@Bean
	public static CustomScopeConfigurer viewScopeConfigurer() {
		CustomScopeConfigurer customScopeConfigurer = new CustomScopeConfigurer();
		customScopeConfigurer.addScope(ViewScope.SCOPE_VIEW, new ViewScope());
		return customScopeConfigurer;
	}
}
