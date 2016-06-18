package com.github.persapiens.jsfboot.undertow;

import io.undertow.Undertow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

/**
 * Configure undertow to load jsf resources from classpath
 */
@Configuration
@EnableConfigurationProperties({UndertowProperties.class})
@ConditionalOnClass(Undertow.class)
public class UndertowSpringBootAutoConfiguration extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer {

    @Autowired
    private UndertowProperties undertowProperties;

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        if (container instanceof UndertowEmbeddedServletContainerFactory) {
            ((UndertowEmbeddedServletContainerFactory) container).addDeploymentInfoCustomizers(
                new JsfUndertowDeploymentInfoCustomizer(undertowProperties));
        }
    }
}
