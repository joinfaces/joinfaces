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

package org.joinfaces.primefaces;

import javax.servlet.ServletContext;

import org.joinfaces.ServletContextConfigurer;

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
		setInitParameterString(Captcha.PRIVATE_KEY, this.primefacesProperties.getPrivateCaptchaKey());
		setInitParameterString(Captcha.PUBLIC_KEY, this.primefacesProperties.getPublicCaptchaKey());
		setInitParameterString(ContextParams.AUTO_UPDATE, this.primefacesProperties.getAutoUpdate());
		setInitParameterBoolean(ContextParams.BEAN_VALIDATION_DISABLED, this.primefacesProperties.getBeanValidationDisabled());
		setInitParameterString(ContextParams.CACHE_PROVIDER, this.primefacesProperties.getCacheProvider());
		setInitParameterString(ContextParams.DIRECTION, this.primefacesProperties.getDir());
		setInitParameterBoolean(ContextParams.EARLY_POST_PARAM_EVALUATION, this.primefacesProperties.getEarlyPostParamEvaluation());
		setInitParameterBoolean(ContextParams.FONT_AWESOME, this.primefacesProperties.getFontAwesome());
		setInitParameterBoolean(ContextParams.INTERPOLATE_CLIENT_SIDE_VALIDATION_MESSAGES, this.primefacesProperties.getInterpolateClientSideValidationMessages());
		setInitParameterBoolean(ContextParams.LEGACY_WIDGET_NAMESPACE, this.primefacesProperties.getLegacyWidgetNamespace());
		setInitParameterString(ContextParams.MOBILE_THEME, this.primefacesProperties.getMobile().getTheme());
		setInitParameterBoolean(ContextParams.PFV_KEY, this.primefacesProperties.getClientSideValidation());
		setInitParameterString(ContextParams.PUSH_SERVER_URL, this.primefacesProperties.getPushServerUrl());
		setInitParameterBoolean(ContextParams.RESET_VALUES, this.primefacesProperties.getResetValues());
		setInitParameterString(ContextParams.SECRET_KEY, this.primefacesProperties.getSecret());
		setInitParameterString(ContextParams.SUBMIT, this.primefacesProperties.getSubmit());
		setInitParameterString(ContextParams.THEME, this.primefacesProperties.getTheme());
		setInitParameterBoolean(ContextParams.TRANSFORM_METADATA, this.primefacesProperties.getTransformMetadata());
		setInitParameterString(ContextParams.UPLOADER, this.primefacesProperties.getUploader());

		setInitParameterString("org.primefaces.component.captcha.PRIVATE_KEY", this.primefacesProperties.getCaptcha().getPrivateKey());
	}
}
