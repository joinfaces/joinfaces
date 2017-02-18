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
import org.joinfaces.configuration.InitParameter;
import org.joinfaces.configuration.NestedProperty;
import org.primefaces.cache.CacheProvider;
import org.primefaces.component.captcha.Captcha;
import org.primefaces.util.Constants;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for Primefaces 5.0.
 *
 * Values taken from http://www.primefaces.org/docs/guide/primefaces_user_guide_5_0.pdf
 * pages 13, 42, 55.
 *
 * @author Lars Grefer
 */
@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties("jsf.primefaces")
public class Primefaces5_0Properties {

	/**
	 * Theme of the application.
	 */
	@InitParameter(Constants.ContextParams.THEME)
	private String theme = "aristo";

	@NestedProperty
	private Mobile mobile = new Mobile();

	@InitParameter(Constants.ContextParams.AUTO_UPDATE)
	private String autoUpdate;

	/**
	 * Custom server url for PrimeFaces Push.
	 */
	@InitParameter(Constants.ContextParams.PUSH_SERVER_URL)
	private String pushServerUrl;

	/**
	 * Defines ajax submit mode, full or partial.
	 */
	@InitParameter(Constants.ContextParams.SUBMIT)
	private String submit = "full";

	/**
	 * Defines orientation, ltr or rtl.
	 */
	@InitParameter(Constants.ContextParams.DIRECTION)
	private String dir = "ltr";

	/**
	 * When enabled, ajax updated inputs are reset first.
	 */
	@InitParameter(Constants.ContextParams.RESET_VALUES)
	private boolean resetValues = false;

	/**
	 * Secret key to encrypt-decrypt value expressions exposed in rendering StreamedContents.
	 */
	@InitParameter(Constants.ContextParams.SECRET_KEY)
	private String secret = "primefaces";

	/**
	 * Controls client side validatation.
	 */
	@InitParameter(Constants.ContextParams.PFV_KEY)
	private boolean clientSideValidation = false;

	/**
	 * Defines uploader mode; auto, native or commons.
	 */
	@InitParameter(Constants.ContextParams.UPLOADER)
	private String uploader = "auto";

	/**
	 * Transforms bean validation metadata to html attributes.
	 */
	@InitParameter(Constants.ContextParams.TRANSFORM_METADATA)
	private boolean transformMetadata = false;

	/**
	 * A cache store is required to use the cache component, two different providers are supported as cache implementation;
	 * EHCache and Hazelcast.
	 */
	@InitParameter(Constants.ContextParams.CACHE_PROVIDER)
	private Class<? extends CacheProvider> cacheProvider;

	/**
	 * Private reCaptcha key.
	 */
	@InitParameter(Captcha.PRIVATE_KEY)
	private String privateCaptchaKey = null;

	/**
	 * Public reCaptcha key.
	 */
	@InitParameter(Captcha.PUBLIC_KEY)
	private String publicCaptchaKey = null;

	@Getter
	@Setter
	@NoArgsConstructor
	public static class Mobile {
		/**
		 * Theme of the mobile application.
		 */
		@InitParameter(Constants.ContextParams.MOBILE_THEME)
		private String theme;
	}
}
