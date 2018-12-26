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

package org.joinfaces.autoconfigure.rewrite;

import java.util.List;

import lombok.Data;
import org.joinfaces.autoconfigure.servlet.initparams.ServletContextInitParameter;
import org.joinfaces.autoconfigure.servlet.initparams.ServletContextInitParameterProperties;
import org.ocpsoft.rewrite.annotation.config.AnnotationConfigProvider;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties of Rewrite.
 *
 * @author Marcelo Fernandes
 */
@Data
@ConfigurationProperties(prefix = "joinfaces.rewrite")
public class RewriteProperties implements ServletContextInitParameterProperties {

	/**
	 * Reload configuration.
	 */
	@ServletContextInitParameter("org.ocpsoft.rewrite.config.CONFIG_RELOADING")
	private Boolean configReloading;

	/**
	 * Does the user want to scan the WEB-INF/lib directory.
	 */
	@ServletContextInitParameter(AnnotationConfigProvider.CONFIG_SCAN_LIB_DIR)
	private Boolean scanLibDirectory;

	/**
	 * Retrieve the optional package filter configuration parameter. Users can
	 * disable annotation scanning with value none.
	 */
	@ServletContextInitParameter(value = AnnotationConfigProvider.CONFIG_BASE_PACKAGES, listSeparator = ",")
	private List<String> basePackages;

	/**
	 * Should classpath scanning for annotations be enabled (Joinfaces feature).
	 */
	@ServletContextInitParameter(SpringBootAnnotationConfigProvider.SCAN_CLASSPATH)
	private Boolean scanClasspath;

}
