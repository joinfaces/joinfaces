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

package org.joinfaces.configuration;

import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author Lars Grefer
 * @see ConditionalOnClassVersion
 */
public class ClassVersionCondition extends SpringBootCondition {

	@Override
	public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {

		Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(ConditionalOnClassVersion.class.getName(), false);

		Class<?> clazz = (Class<?>) annotationAttributes.get("value");
		String version = (String) annotationAttributes.get("version");

		String specificationVersion = clazz.getPackage().getSpecificationVersion();

		if (specificationVersion != null && specificationVersion.matches(version)) {
			return ConditionOutcome.match(
					ConditionMessage.forCondition(ConditionalOnClassVersion.class)
							.found("Specification-Version")
							.items(specificationVersion)
			);
		}

		String implementationVersion = clazz.getPackage().getImplementationVersion();
		if (implementationVersion != null && implementationVersion.matches(version)) {
			return ConditionOutcome.match(
					ConditionMessage.forCondition(ConditionalOnClassVersion.class)
							.found("Implementation-Version")
							.items(implementationVersion)
			);
		}

		if (specificationVersion == null && implementationVersion == null) {
			return ConditionOutcome.noMatch(
					ConditionMessage.forCondition(ConditionalOnClassVersion.class)
							.didNotFind("manifest attributes")
							.items("Specification-Version", "Implementation-Version")
			);
		}

		return ConditionOutcome.noMatch(
				ConditionMessage.forCondition(ConditionalOnClassVersion.class)
						.found("version", "versions")
						.items(specificationVersion, implementationVersion)
		);
	}
}
