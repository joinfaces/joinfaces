package com.github.persapiens.jsfboot.undertow;

import io.undertow.Undertow;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.servlet.api.DeploymentInfo;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.undertow.UndertowDeploymentInfoCustomizer;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(UndertowSpringBootAutoConfiguration.class);

    private UndertowDeploymentInfoCustomizer jettyServerCustomizer() {
        return new UndertowDeploymentInfoCustomizerImpl();
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        if (container instanceof UndertowEmbeddedServletContainerFactory) {
            ((UndertowEmbeddedServletContainerFactory) container).addDeploymentInfoCustomizers(jettyServerCustomizer());
        }
    }

    private class UndertowDeploymentInfoCustomizerImpl implements UndertowDeploymentInfoCustomizer {
        @Override
        public void customize(final DeploymentInfo di) {
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                @Override
                public Void run() {
                    ClassLoader jsfClassLoader = new URLClassLoader(new URL[0], this.getClass().getClassLoader());
                    di.setClassLoader(jsfClassLoader);

                    di.setResourceManager(new ClassPathResourceManager(jsfClassLoader, undertowProperties.getClassPathResource()));

                    return null;
                }
            });

            LOGGER.info("Setting Undertow classLoader to META-INF/resources directory");
        }
    }
}
