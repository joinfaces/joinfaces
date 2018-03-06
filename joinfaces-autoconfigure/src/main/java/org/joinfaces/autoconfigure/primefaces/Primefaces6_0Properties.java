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

import lombok.Getter;
import lombok.Setter;
import org.joinfaces.autoconfigure.configuration.ServletContextInitParameter;
import org.joinfaces.autoconfigure.configuration.ServletContextInitParameterProperties;
import org.primefaces.util.Constants;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for Primefaces 6+.
 *
 * Values taken from http://www.primefaces.org/docs/guide/primefaces_user_guide_6_0.pdf page 13.
 *
 * @author Lars Grefer
 */
@Getter
@Setter
@ConfigurationProperties("jsf.primefaces")
public class Primefaces6_0Properties implements ServletContextInitParameterProperties {

	@ServletContextInitParameter(Constants.ContextParams.EARLY_POST_PARAM_EVALUATION)
	private boolean earlyPostParamEvaluation = false;

	@ServletContextInitParameter(Constants.ContextParams.INTERPOLATE_CLIENT_SIDE_VALIDATION_MESSAGES)
	private boolean interpolateClientSideValidationMessages = false;

}
