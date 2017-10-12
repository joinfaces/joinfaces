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

package org.joinfaces.richfaces;

import lombok.Getter;
import lombok.Setter;
import org.joinfaces.configuration.NestedProperty;
import org.joinfaces.configuration.ServletContextInitParameter;
import org.joinfaces.configuration.ServletContextInitParameterConfigurationProperties;
import org.richfaces.application.CoreConfiguration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties of RichFaces.
 * Taken from
 * + core/src/main/java/org/richfaces/application/CoreConfiguration.java
 * + components/a4j/src/main/java/org/richfaces/application/CommonComponentsConfiguration.java
 * + components/rich/src/main/java/org/richfaces/application/IterationComponentsConfiguration.java
 *
 * @author Jamillo Santos
 * @author Lars Grefer
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "jsf.richfaces")
public class RichfacesProperties implements ServletContextInitParameterConfigurationProperties {

	// (defaultValue = "true", names = "org.richfaces.enableControlSkinning")
	@ServletContextInitParameter("org.richfaces.enableControlSkinning")
	private Boolean enableControlSkinning;

	// (defaultValue = "false", names = "org.richfaces.enableControlSkinningClasses")
	@ServletContextInitParameter("org.richfaces.enableControlSkinningClasses")
	private Boolean enableControlSkinningClasses;

	// (names = "org.richfaces.skin")
	@ServletContextInitParameter(CoreConfiguration.SKIN_PARAM_NAME)
	private String skin;

	// (names = "org.richfaces.baseSkin")
	@ServletContextInitParameter(CoreConfiguration.BASE_SKIN_PARAM_NAME)
	private String baseSkin;

	// (defaultValue = "86400" /* 24 * 60 * 60 */, names = "org.richfaces.resourceDefaultTTL", literal = true)
	@ServletContextInitParameter("org.richfaces.resourceDefaultTTL")
	private Integer resourceDefaultTtl;

	// (defaultValue = "512", names = "org.richfaces.resourceCacheSize", literal = true)
	@ServletContextInitParameter(CoreConfiguration.RESOURCES_CACHE_SIZE_PARAM_NAME)
	private Integer resourceCacheSize;

	// (names = "org.richfaces.resourceDefaultVersion")
	@ServletContextInitParameter("org.richfaces.resourceDefaultVersion")
	private String resourceDefaultVersion;

	@NestedProperty
	private Cache cache = new Cache();

	@NestedProperty
	private ResourceMapping resourceMapping = new ResourceMapping();

	@NestedProperty
	private ResourceOptimization resourceOptimization = new ResourceOptimization();

	// (defaultValue = "true", names = "org.richfaces.executeAWTInitializer", literal = true)
	@ServletContextInitParameter("org.richfaces.executeAWTInitializer")
	private Boolean executeAwtInitializer;

	@NestedProperty
	private Push push = new Push();

	@NestedProperty
	private Builtin builtin = new Builtin();

	@NestedProperty
	private Queue queue = new Queue();

	// (defaultValue = "false", names = "org.richfaces.datatableUsesViewLocale")
	@ServletContextInitParameter("org.richfaces.datatableUsesViewLocale")
	private String datatableUsesViewLocale;

	/**
	 * Cache namespace.
	 */
	@Getter
	@Setter
	public static class Cache {

		// (names = "org.richfaces.cache.LRU_MAP_CACHE_SIZE", literal = true)
		@ServletContextInitParameter("org.richfaces.cache.LRU_MAP_CACHE_SIZE")
		private Integer lruMapCacheSize;
	}

	/**
	 * ResourceMapping namespace.
	 */
	@Getter
	@Setter
	public static class ResourceMapping {

		// (names = "org.richfaces.resourceMapping.enabled", literal = true)
		@ServletContextInitParameter("org.richfaces.resourceMapping.enabled")
		private Boolean enabled;

		// (names = "org.richfaces.resourceMapping.location", literal = true)
		@ServletContextInitParameter("org.richfaces.resourceMapping.location")
		private String location;

		// (names = "org.richfaces.resourceMapping.mappingFile")
		@ServletContextInitParameter("org.richfaces.resourceMapping.mappingFile")
		private String mappingFile;

		// (names = "org.richfaces.resourceMapping.compressedStages")
		@ServletContextInitParameter("org.richfaces.resourceMapping.compressedStages")
		private String compressedStages;

		// (names = "org.richfaces.resourceMapping.packedStages")
		@ServletContextInitParameter("org.richfaces.resourceMapping.packedStages")
		private String packedStages;
	}

	/**
	 * Resource optimization namespace.
	 */
	@Getter
	@Setter
	public static class ResourceOptimization {

		// (defaultValue = "false", names = { "org.richfaces.resourceOptimization.enabled", "org.richfaces.resourceMapping.enabled" }, literal = true)
		@ServletContextInitParameter("org.richfaces.resourceOptimization.enabled")
		private Boolean enabled;

		// (defaultValue = "Production,SystemTest", names = { "org.richfaces.resourceOptimization.compressionStages", "org.richfaces.resourceMapping.compressedStages" }, literal = true)
		@ServletContextInitParameter("org.richfaces.resourceOptimization.compressionStages")
		private String compressionStages;

		// (defaultValue = "All", names = { "org.richfaces.resourceOptimization.packagingStages", "org.richfaces.resourceMapping.packedStages" }, literal = true)
		@ServletContextInitParameter("org.richfaces.resourceOptimization.packagingStages")
		private String packedStages;
	}

	/**
	 * Push namespace.
	 */
	@Getter
	@Setter
	public static class Push {

		// (names = "org.richfaces.push.handlerMapping", literal = true)
		@ServletContextInitParameter("org.richfaces.push.handlerMapping")
		private String handlerMapping;

		@NestedProperty
		private Jms jms = new Jms();

		// (defaultValue = "false", names="org.richfaces.push.initializeOnStartup")
		@ServletContextInitParameter("org.richfaces.push.initializeOnStartup")
		private Boolean initializeOnStartup;

		@NestedProperty
		private Session session = new Session();

		/**
		 * Jms namespace.
		 */
		@Getter
		@Setter
		public static class Jms {

			// (defaultValue = "/ConnectionFactory", names = "org.richfaces.push.jms.connectionFactory")
			@ServletContextInitParameter("org.richfaces.push.jms.connectionFactory")
			private String connectionFactory;

			// (defaultValue = "", names = "org.richfaces.push.jms.enabled")
			@ServletContextInitParameter("org.richfaces.push.jms.enabled")
			private Boolean enabled;

			// (defaultValue = "/topic", names = "org.richfaces.push.jms.topicsNamespace")
			@ServletContextInitParameter("org.richfaces.push.jms.topicsNamespace")
			private String topicsNamespace;

			// (defaultValue = "", names = "org.richfaces.push.jms.connectionUsername")
			@ServletContextInitParameter("org.richfaces.push.jms.connectionUsername")
			private String connectionUsername;

			// (defaultValue = "", names = "org.richfaces.push.jms.connectionPassword")
			@ServletContextInitParameter("org.richfaces.push.jms.connectionPassword")
			private String connectionPassword;
		}

		/**
		 * Session namespace.
		 */
		@Getter
		@Setter
		public static class Session {

			// (defaultValue = "300000", names="org.richfaces.push.session.maxInactiveInterval")
			@ServletContextInitParameter("org.richfaces.push.session.maxInactiveInterval")
			private Integer maxInactiveInterval;
		}

	}

	/**
	 * Builtin namespace.
	 */
	@Getter
	public static class Builtin {

		@NestedProperty
		private Sort sort = new Sort();

		@NestedProperty
		private Filter filter = new Filter();

		/**
		 * Sort namespace.
		 */
		@Getter
		@Setter
		public static class Sort {

			// (defaultValue = "true", names = "org.richfaces.builtin.sort.enabled")
			@ServletContextInitParameter("org.richfaces.builtin.sort.enabled")
			private Boolean enabled;
		}

		/**
		 * Filter namespace.
		 */
		@Getter
		@Setter
		public static class Filter {

			// (defaultValue = "true", names = "org.richfaces.builtin.filter.enabled")
			@ServletContextInitParameter("org.richfaces.builtin.filter.enabled")
			private Boolean enabled;
		}
	}

	/**
	 * Queue namespace.
	 */
	@Getter
	@Setter
	public static class Queue {

		// (defaultValue = "true", names = "org.richfaces.queue.enabled", literal = true)
		@ServletContextInitParameter("org.richfaces.queue.enabled")
		private Boolean enabled;
	}
}
