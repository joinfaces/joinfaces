/*
 * Copyright 2016-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.joinfaces.weld;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;
import org.jboss.weld.environment.deployment.discovery.BeanArchiveBuilder;
import org.jboss.weld.environment.deployment.discovery.BeanArchiveHandler;
import org.jboss.weld.environment.deployment.discovery.FileSystemBeanArchiveHandler;
import org.jboss.weld.environment.logging.CommonLogger;
import org.jboss.weld.environment.util.URLUtils;

/**
 * Special {@link BeanArchiveHandler} which can handle the new "jar:nested:" urls
 * introduced with Spring Boot 3.2.
 *
 * @author Lars Grefer
 */
@Slf4j
public class NestedJarArchiveHandler extends FileSystemBeanArchiveHandler {

	private static final Pattern nestedJarUrlPattern = Pattern.compile("jar:nested:(.+)/!(.+)!/(.+)");

	@Override
	public BeanArchiveBuilder handle(String beanArchiveReference) {

		Matcher nestedJarUrlMatcher = nestedJarUrlPattern.matcher(beanArchiveReference);
		if (nestedJarUrlMatcher.matches()) {

			String outerJar = nestedJarUrlMatcher.group(1);
			String innerJar = nestedJarUrlMatcher.group(2);

			File file = new File(outerJar);
			String path = outerJar + URLUtils.JAR_URL_SEPARATOR + innerJar;

			BeanArchiveBuilder builder = new BeanArchiveBuilder();

			try {
				handleNestedFile(path, file, builder);
			}
			catch (IOException e) {
				CommonLogger.LOG.cannotHandleFilePath(file, path, e);
				return null;
			}

			return builder;
		}

		return null;
	}
}
