package org.joinfaces.test;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lars Grefer
 */
@Configuration
@EnableConfigurationProperties(ListProperties.class)
public class ListPropertiesAutoConfiguration {
}
