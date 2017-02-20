package org.joinfaces.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;

/**
 * Common base class for all auto-configuration classes which provide properties
 * as {@link javax.servlet.ServletContext} init parameters.
 *
 * @param <PC> Actual type of the properties class
 * @author Lars Grefer
 */
public abstract class PropertiesAutoConfiguration<PC> {

	@Autowired
	protected PC properties;

	@Autowired(required = false)
	protected List<PropertiesCustomizer<? super PC>> propertiesCustomizers;

	@Bean
	@ConditionalOnWebApplication
	public ServletContextInitializer propertiesServletContextInitializer() {

		if (propertiesCustomizers != null) {
			for (PropertiesCustomizer<? super PC> propertiesCustomizer : this.propertiesCustomizers) {
				propertiesCustomizer.process(properties);
			}
		}

		return new ReflectiveServletContextInitializer<PC>(this.properties);
	}
}
