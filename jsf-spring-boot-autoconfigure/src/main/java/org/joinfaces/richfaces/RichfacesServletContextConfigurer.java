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

package org.joinfaces.richfaces;

import javax.servlet.ServletContext;

import lombok.Builder;
import org.joinfaces.ServletContextConfigurer;

/**
 * Servlet context configurer of RichFaces.
 * @author Jamillo SAntos
 */
public class RichfacesServletContextConfigurer extends ServletContextConfigurer {

	private RichfacesProperties richfacesProperties;

	@Builder
	public RichfacesServletContextConfigurer(RichfacesProperties richfacesProperties, ServletContext servletContext) {
		super(servletContext, "");
		this.richfacesProperties = richfacesProperties;
	}

	@Override
	public void configure() {
		this.setInitParameterLong("org.richfaces.LongValueWithDefault", this.richfacesProperties.getLongValueWithDefault());
		this.setInitParameterInteger("org.richfaces.LongValueWithDefault", this.richfacesProperties.getIntValue());
		this.setInitParameterInteger("org.richfaces.IntValueWithDefault", this.richfacesProperties.getIntValueWithDefault());
		this.setInitParameterString("org.richfaces.StringValue", this.richfacesProperties.getStringValue());
		this.setInitParameterString("org.richfaces.StringValueWithDefault", this.richfacesProperties.getStringValueWithDefault());
		this.setInitParameterString("org.richfaces.EnumValue", this.richfacesProperties.getEnumValue());
		this.setInitParameterString("org.richfaces.EnumValue", this.richfacesProperties.getEnumValueWithDefault());
		this.setInitParameterBoolean("org.richfaces.BooleanValue", this.richfacesProperties.getBooleanValue());
		this.setInitParameterBoolean("org.richfaces.BooleanValue", this.richfacesProperties.getBooleanValueWithDefault());
		this.setInitParameterString("org.richfaces.MultiValue1", this.richfacesProperties.getMultiValue1());
		this.setInitParameterString("org.richfaces.MultiValue2", this.richfacesProperties.getMultiValue2());
		this.setInitParameterString("org.richfaces.FacesContextReference", this.richfacesProperties.getFacesContextReference());
		this.setInitParameterString("org.richfaces.DynamicValueWithDefault", this.richfacesProperties.getDynamicValueWithDefault());
		this.setInitParameterString("org.richfaces.LiteralOnlyValue", this.richfacesProperties.getLiteralOnlyValue());
		this.setInitParameterString("org.richfaces.LiteralOnlyWithEl", this.richfacesProperties.getLiteralOnlyWithEl());
		this.setInitParameterBoolean("org.richfaces.enableControlSkinning", this.richfacesProperties.getEnableControlSkinning());
		this.setInitParameterBoolean("org.richfaces.enableControlSkinningClasses", this.richfacesProperties.getEnableControlSkinningClasses());
		this.setInitParameterString("org.richfaces.skin", this.richfacesProperties.getSkin());
		this.setInitParameterString("org.richfaces.baseSkin", this.richfacesProperties.getBaseSkin());
		this.setInitParameterInteger("org.richfaces.resourceDefaultTTL", this.richfacesProperties.getResourceDefaultTTL());
		this.setInitParameterInteger("org.richfaces.resourceCacheSize", this.richfacesProperties.getResourceCacheSize());
		this.setInitParameterString("org.richfaces.resourceDefaultVersion", this.richfacesProperties.getResourceDefaultVersion());
		this.setInitParameterInteger("org.richfaces.cache.LRU_MAP_CACHE_SIZE", this.richfacesProperties.getCache().getLruMapCacheSize());
		this.setInitParameterBoolean("org.richfaces.resourceMapping.enabled", this.richfacesProperties.getResourceMapping().getEnabled());
		this.setInitParameterString("org.richfaces.resourceMapping.location", this.richfacesProperties.getResourceMapping().getLocation());
		this.setInitParameterString("org.richfaces.resourceMapping.mappingFile", this.richfacesProperties.getResourceMapping().getMappingFile());
		this.setInitParameterString("org.richfaces.resourceMapping.compressedStages", this.richfacesProperties.getResourceMapping().getCompressedStages());
		this.setInitParameterString("org.richfaces.resourceMapping.packedStages", this.richfacesProperties.getResourceMapping().getPackedStages());
		this.setInitParameterBoolean("org.richfaces.resourceOptimization.enabled", this.richfacesProperties.getResourceOptimization().getEnabled());
		this.setInitParameterString("org.richfaces.resourceOptimization.compressionStages", this.richfacesProperties.getResourceOptimization().getCompressionStages());
		this.setInitParameterString("org.richfaces.resourceOptimization.packagingStages", this.richfacesProperties.getResourceOptimization().getPackedStages());
		this.setInitParameterBoolean("org.richfaces.executeAWTInitializer", this.richfacesProperties.getExecuteAWTInitializer());
		this.setInitParameterString("org.richfaces.push.handlerMapping", this.richfacesProperties.getPush().getHandlerMapping());
		this.setInitParameterString("org.richfaces.push.jms.connectionFactory", this.richfacesProperties.getPush().getJms().getConnectionFactory());
		this.setInitParameterBoolean("org.richfaces.push.jms.enabled", this.richfacesProperties.getPush().getJms().getEnabled());
		this.setInitParameterString("org.richfaces.push.jms.topicsNamespace", this.richfacesProperties.getPush().getJms().getTopicsNamespace());
		this.setInitParameterString("org.richfaces.push.jms.connectionUsername", this.richfacesProperties.getPush().getJms().getConnectionUsername());
		this.setInitParameterString("org.richfaces.push.jms.connectionPassword", this.richfacesProperties.getPush().getJms().getConnectionPassword());
		this.setInitParameterBoolean("org.richfaces.push.initializeOnStartup", this.richfacesProperties.getPush().getInitializeOnStartup());
		this.setInitParameterInteger("org.richfaces.push.session.maxInactiveInterval", this.richfacesProperties.getPush().getSession().getMaxInactiveInterval());
		this.setInitParameterBoolean("org.richfaces.builtin.sort.enabled", this.richfacesProperties.getBuiltin().getSort().getEnabled());
		this.setInitParameterBoolean("org.richfaces.builtin.filter.enabled", this.richfacesProperties.getBuiltin().getFilter().getEnabled());
		this.setInitParameterBoolean("org.richfaces.queue.enabled", this.richfacesProperties.getQueue().getEnabled());
	}
}
