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

package org.joinfaces.mojarra;

import javax.servlet.ServletContext;

import lombok.Builder;
import org.joinfaces.ServletContextConfigurer;

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
		setInitParameterInteger("clientStateTimeout", this.mojarraProperties.getClientStateTimeout());
		setInitParameterInteger("clientStateWriteBufferSize", this.mojarraProperties.getClientStateWriteBufferSize());
		setInitParameterBoolean("compressViewState", this.mojarraProperties.getCompressViewState());
		setInitParameterBoolean("disableClientStateEncryption", this.mojarraProperties.getDisableClientStateEncryption());
		setInitParameterBoolean("enableClientStateDebugging", this.mojarraProperties.getEnableClientStateDebugging());
		setInitParameterBoolean("generateUniqueServerStateIds", this.mojarraProperties.getGenerateUniqueServerStateIds());
		setInitParameterInteger("numberOfLogicalViews", this.mojarraProperties.getNumberOfLogicalViews());
		setInitParameterInteger("numberOfViewsInSession", this.mojarraProperties.getNumberOfViewsInSession());
		setInitParameterBoolean("serializeServerState", this.mojarraProperties.getSerializeServerState());
		setInitParameterBoolean("writeStateAtFormEnd", this.mojarraProperties.getWriteStateAtFormEnd());
		setInitParameterBoolean("allowTextChildren", this.mojarraProperties.getAllowTextChildren());
		setInitParameterBoolean("autoCompleteOffOnViewState", this.mojarraProperties.getAutoCompleteOffOnViewState());
		setInitParameterBoolean("compressJavaScript", this.mojarraProperties.getCompressJavaScript());
		setInitParameterBoolean("disableUnicodeEscaping", this.mojarraProperties.getDisableUnicodeEscaping());
		setInitParameterBoolean("disableIdUniquenessCheck", this.mojarraProperties.getDisableIdUniquenessCheck());
		setInitParameterBoolean("enabledJSStyleHiding", this.mojarraProperties.getEnabledJSStyleHiding());
		setInitParameterBoolean("enableScriptsInAttributeValues", this.mojarraProperties.getEnableScriptsInAttributeValues());
		setInitParameterBoolean("enableViewStateIdRendering", this.mojarraProperties.getEnableViewStateIdRendering());
		setInitParameterBoolean("preferXHTML", this.mojarraProperties.getPreferXHTML());
		setInitParameterInteger("responseBufferSize", this.mojarraProperties.getResponseBufferSize());
		setInitParameterBoolean("cacheResourceModificationTimestamp", this.mojarraProperties.getCacheResourceModificationTimestamp());
		setInitParameterString("compressableMimeTypes", this.mojarraProperties.getCompressableMimeTypes());
		setInitParameterInteger("defaultResourceMaxAge", this.mojarraProperties.getDefaultResourceMaxAge());
		setInitParameterBoolean("enableFaceletsResourceResolverCompositeComponents", this.mojarraProperties.getEnableFaceletsResourceResolverCompositeComponents());
		setInitParameterBoolean("enableMissingResourceLibraryDetection", this.mojarraProperties.getEnableMissingResourceLibraryDetection());
		setInitParameterInteger("resourceUpdateCheckPeriod", this.mojarraProperties.getResourceUpdateCheckPeriod());
		setInitParameterBoolean("enableAgressiveSessionDirtying", this.mojarraProperties.getEnableAgressiveSessionDirtying());
		setInitParameterBoolean("enableDistributable", this.mojarraProperties.getEnableDistributable());
		setInitParameterString("annotationScanPackages", this.mojarraProperties.getAnnotationScanPackages());
		setInitParameterBoolean("displayConfiguration", this.mojarraProperties.getDisplayConfiguration());
		setInitParameterBoolean("enableCoreTagLibValidator", this.mojarraProperties.getEnableCoreTagLibValidator());
		setInitParameterBoolean("enableHtmlTagLibValidator", this.mojarraProperties.getEnableHtmlTagLibValidator());
		setInitParameterBoolean("enableLazyBeanValidation", this.mojarraProperties.getEnableLazyBeanValidation());
		setInitParameterBoolean("enableThreading", this.mojarraProperties.getEnableThreading());
		setInitParameterBoolean("forceLoadConfiguration", this.mojarraProperties.getForceLoadConfiguration());
		setInitParameterBoolean("validateXml", this.mojarraProperties.getValidateXml());
		setInitParameterBoolean("verifyObjects", this.mojarraProperties.getVerifyObjects());
		setInitParameterBoolean("enableTransitionTimeNoOpFlash", this.mojarraProperties.getEnableTransitionTimeNoOpFlash());
		setInitParameterString("expressionFactory", this.mojarraProperties.getExpressionFactory());
		setInitParameterBoolean("forceAlwaysWriteFlashCookie", this.mojarraProperties.getForceAlwaysWriteFlashCookie());
		setInitParameterString("injectionProvider", this.mojarraProperties.getInjectionProvider());
		setInitParameterBoolean("namespaceParameters", this.mojarraProperties.getNamespaceParameters());
		setInitParameterBoolean("registerConverterPropertyEditors", this.mojarraProperties.getRegisterConverterPropertyEditors());
		setInitParameterBoolean("sendPoweredByHeader", this.mojarraProperties.getSendPoweredByHeader());
		setInitParameterString("serializationProvider", this.mojarraProperties.getSerializationProvider());
		setInitParameterString("faceletFactory", this.mojarraProperties.getFaceletFactory());
		setInitParameterBoolean("disallowDoctypeDecl", this.mojarraProperties.getDisallowDoctypeDecl());
	}
}
