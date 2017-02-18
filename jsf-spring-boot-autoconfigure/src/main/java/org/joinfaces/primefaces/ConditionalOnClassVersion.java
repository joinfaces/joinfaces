package org.joinfaces.primefaces;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @author Lars Grefer
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(ClassVersionCondition.class)
public @interface ConditionalOnClassVersion {

	Class<?> value();

	String version();
}
