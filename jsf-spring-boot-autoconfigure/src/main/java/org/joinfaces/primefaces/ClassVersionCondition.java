package org.joinfaces.primefaces;

import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * @author Lars Grefer
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
