package org.joinfaces.configuration;


/**
 * Interface for beans which want to customize properties
 *
 * @param <PC> Actual type of the properties object
 * @author Lars Grefer
 */
public interface PropertiesCustomizer<PC> {

	void process(PC properties);
}
