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

package org.joinfaces.primefaces;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joinfaces.configuration.ServletContextInitParameter;
import org.joinfaces.configuration.ServletContextInitParameterConfigurationProperties;
import org.primefaces.component.captcha.Captcha;
import org.primefaces.util.Constants;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for Primefaces 4.0.
 *
 * Values taken from http://www.primefaces.org/docs/guide/primefaces_user_guide_4_0.pdf
 * page 15, 56.
 *
 * @author Lars Grefer
 */
@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties("jsf.primefaces")
public class Primefaces4_0Properties implements ServletContextInitParameterConfigurationProperties {

	/**
	 * Theme of the application.
	 */
	@ServletContextInitParameter(Constants.ContextParams.THEME)
	private String theme = "aristo";

	/**
	 * Custom server url for PrimeFaces Push.
	 */
	@ServletContextInitParameter(Constants.ContextParams.PUSH_SERVER_URL)
	private String pushServerUrl;

	/**
	 * Defines ajax submit mode, full or partial.
	 */
	@ServletContextInitParameter(Constants.ContextParams.SUBMIT)
	private String submit = "full";

	/**
	 * Defines orientation, ltr or rtl.
	 */
	@ServletContextInitParameter(Constants.ContextParams.DIRECTION)
	private String dir = "ltr";

	/**
	 * When enabled, ajax updated inputs are reset first.
	 */
	@ServletContextInitParameter(Constants.ContextParams.RESET_VALUES)
	private boolean resetValues = false;

	/**
	 * Secret key to encrypt-decrypt value expressions exposed in rendering StreamedContents.
	 */
	@ServletContextInitParameter(Constants.ContextParams.SECRET_KEY)
	private String secret = "primefaces";

	/**
	 * Controls client side validatation.
	 */
	@ServletContextInitParameter(Constants.ContextParams.PFV_KEY)
	private boolean clientSideValidation = false;

	/**
	 * Defines uploader mode; auto, native or commons.
	 */
	@ServletContextInitParameter(Constants.ContextParams.UPLOADER)
	private String uploader = "auto";

	/**
	 * Private reCaptcha key.
	 */
	@ServletContextInitParameter(Captcha.PRIVATE_KEY)
	private String privateCaptchaKey = null;

	/**
	 * Public reCaptcha key.
	 */
	@ServletContextInitParameter(Captcha.PUBLIC_KEY)
	private String publicCaptchaKey = null;
}
