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

package com.github.persapiens.jsfboot.primefaces;

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties of PrimeFaces.
 * Taken from http://www.primefaces.org/docs/api/6.0/constant-values.html
 * @author Marcelo Fernandes
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "jsf.primefaces")
public class PrimefacesProperties {

	private String privateCaptchaKey;

	private String publicCaptchaKey;

	/**
	 * Theme of the application.
	 */
	private String theme;

	private Mobile mobile = new Mobile();

	/**
	 * Custom server url for PrimeFaces Push.
	 */
	private String pushServerUrl;

	/**
	 * Defines ajax submit mode, full or partial.
	 */
	private String submit;

	/**
	 * Defines orientation, ltr or rtl.
	 */
	private String dir;

	/**
	 * When enabled, ajax updated inputs are reset.
	 */
	private Boolean resetValues;

	/**
	 * Secret key to encrypt-decrypt value expressions exposed in rendering
	 * StreamedContents.
	 */
	private String secret;

	/**
	 * Controls client side validation.
	 */
	private Boolean clientSideValidation;

	/**
	 * Defines uploader mode; auto, native or commons.
	 */
	private String uploader;

	/**
	 * Transforms bean validation metadata to html attributes.
	 */
	private Boolean transformMetadata;

	/**
	 * Enables window scope so that widgets can be accessed using
	 * widgetVar.method() in addition to default PF namespace approach like
	 * PF('widgetVar').method().
	 */
	private Boolean legacyWidgetNamespace;

	/**
	 * Enabled font-awesome icons.
	 */
	private Boolean fontAwesome;

	/**
	 * A cache store is required to use the cache component, two different
	 * providers are supported as cache implementation; EHCache and Hazelcast.
	 */
	private String cacheProvider;

	private String autoUpdate;

	private Captcha captcha = new Captcha();

	private Boolean earlyPostParamEvaluation;

	private Boolean beanValidationDisabled;

	private Boolean interpolateClientSideValidationMessages;

	/**
	 * Mobile class of theme property.
	 */
	@Getter
	@Setter
	public static class Mobile {

		/**
		 * Theme of the mobile application.
		 */
		private String theme;
	}

	/**
	 * Captcha class of privateKey property.
	 */
	@Getter
	@Setter
	public static class Captcha {

		private String privateKey;

	}
}
