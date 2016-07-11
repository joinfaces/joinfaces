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

import javax.servlet.ServletContext;

import com.github.persapiens.jsfboot.ServletContextConfigurer;

import lombok.Builder;

import org.primefaces.component.captcha.Captcha;
import org.primefaces.util.Constants.ContextParams;

/**
 * Servlet context configurer of PrimeFaces.
 * @author Marcelo Fernandes
 */
public class PrimefacesServletContextConfigurer extends ServletContextConfigurer {

	private PrimefacesProperties primefacesProperties;

	@Builder
	public PrimefacesServletContextConfigurer(PrimefacesProperties primefacesProperties, ServletContext servletContext) {
		super(servletContext, "");
		this.primefacesProperties = primefacesProperties;
	}

	@Override
	public void configure() {
		setInitParameter(Captcha.PRIVATE_KEY, this.primefacesProperties.getPrivateCaptchaKey());
		setInitParameter(Captcha.PUBLIC_KEY, this.primefacesProperties.getPublicCaptchaKey());
		setInitParameter(ContextParams.AUTO_UPDATE, this.primefacesProperties.getAutoUpdate());
		setInitParameter(ContextParams.BEAN_VALIDATION_DISABLED, this.primefacesProperties.getBeanValidationDisabled());
		setInitParameter(ContextParams.CACHE_PROVIDER, this.primefacesProperties.getCacheProvider());
		setInitParameter(ContextParams.DIRECTION, this.primefacesProperties.getDir());
		setInitParameter(ContextParams.EARLY_POST_PARAM_EVALUATION, this.primefacesProperties.getEarlyPostParamEvaluation());
		setInitParameter(ContextParams.FONT_AWESOME, this.primefacesProperties.getFontAwesome());
		setInitParameter(ContextParams.INTERPOLATE_CLIENT_SIDE_VALIDATION_MESSAGES, this.primefacesProperties.getInterpolateClientSideValidationMessages());
		setInitParameter(ContextParams.LEGACY_WIDGET_NAMESPACE, this.primefacesProperties.getLegacyWidgetNamespace());
		setInitParameter(ContextParams.MOBILE_THEME, this.primefacesProperties.getMobile().getTheme());
		setInitParameter(ContextParams.PFV_KEY, this.primefacesProperties.getClientSideValidation());
		setInitParameter(ContextParams.PUSH_SERVER_URL, this.primefacesProperties.getPushServerUrl());
		setInitParameter(ContextParams.RESET_VALUES, this.primefacesProperties.getResetValues());
		setInitParameter(ContextParams.SECRET_KEY, this.primefacesProperties.getSecret());
		setInitParameter(ContextParams.SUBMIT, this.primefacesProperties.getSubmit());
		setInitParameter(ContextParams.THEME, this.primefacesProperties.getTheme());
		setInitParameter(ContextParams.TRANSFORM_METADATA, this.primefacesProperties.getTransformMetadata());
		setInitParameter(ContextParams.UPLOADER, this.primefacesProperties.getUploader());

		setInitParameter("org.primefaces.component.captcha.PRIVATE_KEY", this.primefacesProperties.getCaptcha().getPrivateKey());
	}
}
