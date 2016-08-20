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

import lombok.Builder;
import org.joinfaces.ServletContextConfigurer;

import javax.servlet.ServletContext;

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

	public void setInitParameter(String name, String value)
	{
		if (value != null)
			this.setInitParameterString(name, value);
	}

	public void setInitParameter(String name, Integer value)
	{
		if (value != null)
		this.setInitParameterInteger(name, value);
	}

	public void setInitParameter(String name, Boolean value)
	{
		if (value != null)
			this.setInitParameterBoolean(name, value);
	}

	@Override
	public void configure()
	{
		this.setInitParameter("org.richfaces.LongValueWithDefault", this.richfacesProperties.getLongValueWithDefault());
		this.setInitParameter("org.richfaces.LongValueWithDefault", this.richfacesProperties.getIntValue());
		this.setInitParameter("org.richfaces.IntValueWithDefault", this.richfacesProperties.getIntValueWithDefault());
		this.setInitParameter("org.richfaces.StringValue", this.richfacesProperties.getStringValue());
		this.setInitParameter("org.richfaces.StringValueWithDefault", this.richfacesProperties.getStringValueWithDefault());
		this.setInitParameter("org.richfaces.EnumValue", this.richfacesProperties.getEnumValue());
		this.setInitParameter("org.richfaces.EnumValue", this.richfacesProperties.getEnumValueWithDefault());
		this.setInitParameter("org.richfaces.BooleanValue", this.richfacesProperties.getBooleanValue());
		this.setInitParameter("org.richfaces.BooleanValue", this.richfacesProperties.getBooleanValueWithDefault());
		this.setInitParameter("org.richfaces.MultiValue1", this.richfacesProperties.getMultiValue1());
		this.setInitParameter("org.richfaces.MultiValue2", this.richfacesProperties.getMultiValue2());
		this.setInitParameter("org.richfaces.FacesContextReference", this.richfacesProperties.getFacesContextReference());
		this.setInitParameter("org.richfaces.DynamicValueWithDefault", this.richfacesProperties.getDynamicValueWithDefault());
		this.setInitParameter("org.richfaces.LiteralOnlyValue", this.richfacesProperties.getLiteralOnlyValue());
		this.setInitParameter("org.richfaces.LiteralOnlyWithEl", this.richfacesProperties.getLiteralOnlyWithEl());
		this.setInitParameter("org.richfaces.enableControlSkinning", this.richfacesProperties.getEnableControlSkinning());
		this.setInitParameter("org.richfaces.enableControlSkinningClasses", this.richfacesProperties.getEnableControlSkinningClasses());
		this.setInitParameter("org.richfaces.skin", this.richfacesProperties.getSkin());
		this.setInitParameter("org.richfaces.baseSkin", this.richfacesProperties.getBaseSkin());
		this.setInitParameter("org.richfaces.resourceDefaultTTL", this.richfacesProperties.getResourceDefaultTTL());
		this.setInitParameter("org.richfaces.resourceCacheSize", this.richfacesProperties.getResourceCacheSize());
		this.setInitParameter("org.richfaces.resourceDefaultVersion", this.richfacesProperties.getResourceDefaultVersion());
		this.setInitParameter("org.richfaces.cache.LRU_MAP_CACHE_SIZE", this.richfacesProperties.getCache().getLruMapCacheSize());
		this.setInitParameter("org.richfaces.resourceMapping.enabled", this.richfacesProperties.getResourceMapping().getEnabled());
		this.setInitParameter("org.richfaces.resourceMapping.location", this.richfacesProperties.getResourceMapping().getLocation());
		this.setInitParameter("org.richfaces.resourceMapping.mappingFile", this.richfacesProperties.getResourceMapping().getMappingFile());
		this.setInitParameter("org.richfaces.resourceMapping.compressedStages", this.richfacesProperties.getResourceMapping().getCompressedStages());
		this.setInitParameter("org.richfaces.resourceMapping.packedStages", this.richfacesProperties.getResourceMapping().getPackedStages());
		this.setInitParameter("org.richfaces.resourceOptimization.enabled", this.richfacesProperties.getResourceOptimization().getEnabled());
		this.setInitParameter("org.richfaces.resourceOptimization.compressionStages", this.richfacesProperties.getResourceOptimization().getCompressionStages());
		this.setInitParameter("org.richfaces.resourceOptimization.packagingStages", this.richfacesProperties.getResourceOptimization().getPackedStages());
		this.setInitParameter("org.richfaces.executeAWTInitializer", this.richfacesProperties.getExecuteAWTInitializer());
		this.setInitParameter("org.richfaces.push.handlerMapping", this.richfacesProperties.getPush().getHandlerMapping());
		this.setInitParameter("org.richfaces.push.jms.connectionFactory", this.richfacesProperties.getPush().getJms().getConnectionFactory());
		this.setInitParameter("org.richfaces.push.jms.enabled", this.richfacesProperties.getPush().getJms().getEnabled());
		this.setInitParameter("org.richfaces.push.jms.topicsNamespace", this.richfacesProperties.getPush().getJms().getTopicsNamespace());
		this.setInitParameter("org.richfaces.push.jms.connectionUsername", this.richfacesProperties.getPush().getJms().getConnectionUsername());
		this.setInitParameter("org.richfaces.push.jms.connectionPassword", this.richfacesProperties.getPush().getJms().getConnectionPassword());
		this.setInitParameter("org.richfaces.push.initializeOnStartup", this.richfacesProperties.getPush().getInitializeOnStartup());
		this.setInitParameter("org.richfaces.push.session.maxInactiveInterval", this.richfacesProperties.getPush().getSession().getMaxInactiveInterval());
		this.setInitParameter("org.richfaces.builtin.sort.enabled", this.richfacesProperties.getBuiltin().getSort().getEnabled());
		this.setInitParameter("org.richfaces.builtin.filter.enabled", this.richfacesProperties.getBuiltin().getFilter().getEnabled());
		this.setInitParameter("org.richfaces.queue.enabled", this.richfacesProperties.getQueue().getEnabled());
	}
}
