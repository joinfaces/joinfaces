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

package com.github.persapiens.jsfboot.mojarra;

import javax.servlet.ServletContext;

import com.github.persapiens.jsfboot.ServletContextConfigurer;
import lombok.Builder;

/**
 * Servlet context configurer of Mojarra.
 * @author Marcelo Fernandes
 */
public class MojarraServletContextConfigurer extends ServletContextConfigurer {

	private MojarraProperties mojarraProperties;

	/**
	 * Preffix of original mojarra parameters.
	 */
	public static final String PREFFIX = "com.sun.faces";

	@Builder
	public MojarraServletContextConfigurer(MojarraProperties mojarraProperties, ServletContext servletContext) {
		super(servletContext, PREFFIX);
		this.mojarraProperties = mojarraProperties;
	}

	@Override
	public void configure() {
		setInitParameter("clientStateTimeout", this.mojarraProperties.getClientStateTimeout());
		setInitParameter("clientStateWriteBufferSize", this.mojarraProperties.getClientStateWriteBufferSize());
		setInitParameter("compressViewState", this.mojarraProperties.getCompressViewState());
		setInitParameter("disableClientStateEncryption", this.mojarraProperties.getDisableClientStateEncryption());
		setInitParameter("enableClientStateDebugging", this.mojarraProperties.getEnableClientStateDebugging());
		setInitParameter("generateUniqueServerStateIds", this.mojarraProperties.getGenerateUniqueServerStateIds());
		setInitParameter("numberOfLogicalViews", this.mojarraProperties.getNumberOfLogicalViews());
		setInitParameter("numberOfViewsInSession", this.mojarraProperties.getNumberOfViewsInSession());
		setInitParameter("serializeServerState", this.mojarraProperties.getSerializeServerState());
		setInitParameter("writeStateAtFormEnd", this.mojarraProperties.getWriteStateAtFormEnd());
		setInitParameter("allowTextChildren", this.mojarraProperties.getAllowTextChildren());
		setInitParameter("autoCompleteOffOnViewState", this.mojarraProperties.getAutoCompleteOffOnViewState());
		setInitParameter("compressJavaScript", this.mojarraProperties.getCompressJavaScript());
		setInitParameter("disableUnicodeEscaping", this.mojarraProperties.getDisableUnicodeEscaping());
		setInitParameter("disableIdUniquenessCheck", this.mojarraProperties.getDisableIdUniquenessCheck());
		setInitParameter("enabledJSStyleHiding", this.mojarraProperties.getEnabledJSStyleHiding());
		setInitParameter("enableScriptsInAttributeValues", this.mojarraProperties.getEnableScriptsInAttributeValues());
		setInitParameter("enableViewStateIdRendering", this.mojarraProperties.getEnableViewStateIdRendering());
		setInitParameter("preferXHTML", this.mojarraProperties.getPreferXHTML());
		setInitParameter("responseBufferSize", this.mojarraProperties.getResponseBufferSize());
		setInitParameter("cacheResourceModificationTimestamp", this.mojarraProperties.getCacheResourceModificationTimestamp());
		setInitParameter("compressableMimeTypes", this.mojarraProperties.getCompressableMimeTypes());
		setInitParameter("defaultResourceMaxAge", this.mojarraProperties.getDefaultResourceMaxAge());
		setInitParameter("enableFaceletsResourceResolverCompositeComponents", this.mojarraProperties.getEnableFaceletsResourceResolverCompositeComponents());
		setInitParameter("enableMissingResourceLibraryDetection", this.mojarraProperties.getEnableMissingResourceLibraryDetection());
		setInitParameter("resourceUpdateCheckPeriod", this.mojarraProperties.getResourceUpdateCheckPeriod());
		setInitParameter("enableAgressiveSessionDirtying", this.mojarraProperties.getEnableAgressiveSessionDirtying());
		setInitParameter("enableDistributable", this.mojarraProperties.getEnableDistributable());
		setInitParameter("annotationScanPackages", this.mojarraProperties.getAnnotationScanPackages());
		setInitParameter("displayConfiguration", this.mojarraProperties.getDisplayConfiguration());
		setInitParameter("enableCoreTagLibValidator", this.mojarraProperties.getEnableCoreTagLibValidator());
		setInitParameter("enableHtmlTagLibValidator", this.mojarraProperties.getEnableHtmlTagLibValidator());
		setInitParameter("enableLazyBeanValidation", this.mojarraProperties.getEnableLazyBeanValidation());
		setInitParameter("enableThreading", this.mojarraProperties.getEnableThreading());
		setInitParameter("forceLoadConfiguration", this.mojarraProperties.getForceLoadConfiguration());
		setInitParameter("validateXml", this.mojarraProperties.getValidateXml());
		setInitParameter("verifyObjects", this.mojarraProperties.getVerifyObjects());
		setInitParameter("enableTransitionTimeNoOpFlash", this.mojarraProperties.getEnableTransitionTimeNoOpFlash());
		setInitParameter("expressionFactory", this.mojarraProperties.getExpressionFactory());
		setInitParameter("forceAlwaysWriteFlashCookie", this.mojarraProperties.getForceAlwaysWriteFlashCookie());
		setInitParameter("injectionProvider", this.mojarraProperties.getInjectionProvider());
		setInitParameter("namespaceParameters", this.mojarraProperties.getNamespaceParameters());
		setInitParameter("registerConverterPropertyEditors", this.mojarraProperties.getRegisterConverterPropertyEditors());
		setInitParameter("sendPoweredByHeader", this.mojarraProperties.getSendPoweredByHeader());
		setInitParameter("serializationProvider", this.mojarraProperties.getSerializationProvider());
		setInitParameter("faceletFactory", this.mojarraProperties.getFaceletFactory());
	}
}
