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

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties of RichFaces.
 * Taken from
 * + core/src/test/java/org/richfaces/application/Configuration.java
 * + core/src/main/java/org/richfaces/application/CoreConfiguration.java
 * + components/a4j/src/main/java/org/richfaces/application/CommonComponentsConfiguration.java
 * + components/rich/src/main/java/org/richfaces/application/IterationComponentsConfiguration.java
 *
 * @author Jamillo Santos
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "jsf.richfaces")
public class RichfacesProperties {

	// names = "org.richfaces.LongValueWithDefault", defaultValue = "-100")
	private Long longValueWithDefault;

	// names = "org.richfaces.IntValue")
	private Integer intValue;

	// names = "org.richfaces.IntValueWithDefault", defaultValue = "-1")
	private Integer intValueWithDefault;

	// names = "org.richfaces.StringValue")
	private String stringValue;

	// names = "org.richfaces.StringValueWithDefault", defaultValue = "default name")
	private String stringValueWithDefault;

	// names = "org.richfaces.EnumValue", defaultValue = "foo")
	private String enumValue;

	// names = "org.richfaces.EnumValue", defaultValue = "foo")
	private String enumValueWithDefault;

	// names = "org.richfaces.BooleanValue")
	private Boolean booleanValue;

	// names = "org.richfaces.BooleanValue", defaultValue = "false")
	private Boolean booleanValueWithDefault;

	// names = { "org.richfaces.MultiValue1", "org.richfaces.MultiValue2" })
	private String multiValue1;
	private String multiValue2;

	// names = "org.richfaces.FacesContextReference")
	private String facesContextReference;

	// names = "org.richfaces.DynamicValueWithDefault", defaultValue = "<something>")
	private String dynamicValueWithDefault;

	// names = "org.richfaces.LiteralOnlyValue", literal = true)
	private String literalOnlyValue;

	// names = "org.richfaces.LiteralOnlyWithEl", literal = true)
	private String literalOnlyWithEl;

	// (defaultValue = "true", names = "org.richfaces.enableControlSkinning")
	private Boolean enableControlSkinning;

	// (defaultValue = "false", names = "org.richfaces.enableControlSkinningClasses")
	private Boolean enableControlSkinningClasses;

	// (names = "org.richfaces.skin")
	private String skin;

	// (names = "org.richfaces.baseSkin")
	private String baseSkin;

	// (defaultValue = "86400" /* 24 * 60 * 60 */, names = "org.richfaces.resourceDefaultTTL", literal = true)
	private Integer resourceDefaultTTL;

	// (defaultValue = "512", names = "org.richfaces.resourceCacheSize", literal = true)
	private Integer resourceCacheSize;

	// (names = "org.richfaces.resourceDefaultVersion")
	private String resourceDefaultVersion;

	private Cache cache = new Cache();

	private ResourceMapping resourceMapping = new ResourceMapping();

	private ResourceOptimization resourceOptimization = new ResourceOptimization();

	// (defaultValue = "true", names = "org.richfaces.executeAWTInitializer", literal = true)
	private Boolean executeAWTInitializer;

	private Push push = new Push();

	private Builtin builtin = new Builtin();

	private Queue queue = new Queue();

	// (defaultValue = "false", names = "org.richfaces.datatableUsesViewLocale")
	private String datatableUsesViewLocale;

	/**
	 * Cache namespace.
	 */
	@Getter
	@Setter
	public static class Cache {

		// (names = "org.richfaces.cache.LRU_MAP_CACHE_SIZE", literal = true)
		private Integer lruMapCacheSize;
	}

	/**
	 * ResourceMapping namespace.
	 */
	@Getter
	@Setter
	public static class ResourceMapping {

		// (names = "org.richfaces.resourceMapping.enabled", literal = true)
		private Boolean enabled;

		// (names = "org.richfaces.resourceMapping.location", literal = true)
		private String location;

		// (names = "org.richfaces.resourceMapping.mappingFile")
		private String mappingFile;

		// (names = "org.richfaces.resourceMapping.compressedStages")
		private String compressedStages;

		// (names = "org.richfaces.resourceMapping.packedStages")
		private String packedStages;
	}

	/**
	 * Resource optimization namespace.
	 */
	@Getter
	@Setter
	public static class ResourceOptimization {

		// (defaultValue = "false", names = { "org.richfaces.resourceOptimization.enabled", "org.richfaces.resourceMapping.enabled" }, literal = true)
		private Boolean enabled;

		// (defaultValue = "Production,SystemTest", names = { "org.richfaces.resourceOptimization.compressionStages", "org.richfaces.resourceMapping.compressedStages" }, literal = true)
		private String compressionStages;

		// (defaultValue = "All", names = { "org.richfaces.resourceOptimization.packagingStages", "org.richfaces.resourceMapping.packedStages" }, literal = true)
		private String packedStages;
	}

	/**
	 * Push namespace.
	 */
	@Getter
	@Setter
	public static class Push {

		// (names = "org.richfaces.push.handlerMapping", literal = true)
		private String handlerMapping;

		private Jms jms = new Jms();

		// (defaultValue = "false", names="org.richfaces.push.initializeOnStartup")
		private Boolean initializeOnStartup;

		private Session session = new Session();

		/**
		 * Jms namespace.
		 */
		@Getter
		@Setter
		public static class Jms {

			// (defaultValue = "/ConnectionFactory", names = "org.richfaces.push.jms.connectionFactory")
			private String connectionFactory;

			// (defaultValue = "", names = "org.richfaces.push.jms.enabled")
			private Boolean enabled;

			// (defaultValue = "/topic", names = "org.richfaces.push.jms.topicsNamespace")
			private String topicsNamespace;

			// (defaultValue = "", names = "org.richfaces.push.jms.connectionUsername")
			private String connectionUsername;

			// (defaultValue = "", names = "org.richfaces.push.jms.connectionPassword")
			private String connectionPassword;
		}

		/**
		 * Session namespace.
		 */
		@Getter
		@Setter
		public static class Session {

			// (defaultValue = "300000", names="org.richfaces.push.session.maxInactiveInterval")
			private Integer maxInactiveInterval;
		}

	}

	/**
	 * Builtin namespace.
	 */
	@Getter
	public static class Builtin {

		private Sort sort = new Sort();

		private Filter filter = new Filter();

		/**
		 * Sort namespace.
		 */
		@Getter
		@Setter
		public static class Sort {

			// (defaultValue = "true", names = "org.richfaces.builtin.sort.enabled")
			private Boolean enabled;
		}

		/**
		 * Filter namespace.
		 */
		@Getter
		@Setter
		public static class Filter {

			// (defaultValue = "true", names = "org.richfaces.builtin.filter.enabled")
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
		private Boolean enabled;
	}
}
