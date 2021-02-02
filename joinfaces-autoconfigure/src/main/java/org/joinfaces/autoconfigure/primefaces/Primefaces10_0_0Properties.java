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

package org.joinfaces.autoconfigure.primefaces;

import lombok.Data;
import org.joinfaces.autoconfigure.servlet.initparams.ServletContextInitParameter;
import org.joinfaces.autoconfigure.servlet.initparams.ServletContextInitParameterProperties;
import org.primefaces.util.Constants;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for Primefaces 10.0.0.
 *
 * @see <a href="https://primefaces.github.io/primefaces/10_0_0/#/gettingstarted/configuration">PrimeFaces 10</a>
 * @author Melloware
 */
@Data
@ConfigurationProperties("joinfaces.primefaces")
public class Primefaces10_0_0Properties implements ServletContextInitParameterProperties {

	@ServletContextInitParameter(Constants.ContextParams.FLEX)
	private Boolean flex;

	@ServletContextInitParameter(Constants.ContextParams.TOUCHABLE)
	private Boolean touchable;

	@ServletContextInitParameter(Constants.ContextParams.MARK_INPUT_AS_INVALID_ON_ERROR_MSG)
	private Boolean markInputAsInvalidOnErrorMessage;

	@ServletContextInitParameter(Constants.ContextParams.COOKIES_SAME_SITE)
	private String cookiesSameSite;

	@ServletContextInitParameter(Constants.ContextParams.MULTI_VIEW_STATE_STORE)
	private String multiViewStateStore;

}
