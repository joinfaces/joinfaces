package com.github.persapiens.jsfboot.undertow;

import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.servlet.api.DeploymentInfo;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.undertow.UndertowDeploymentInfoCustomizer;

/**
 * Configure undertow to load jsf resources from classpath
 */
public class JsfUndertowDeploymentInfoCustomizer implements UndertowDeploymentInfoCustomizer {
    
    private final UndertowProperties undertowProperties;

    private static final Logger LOGGER = LoggerFactory.getLogger(JsfUndertowDeploymentInfoCustomizer.class);

    public JsfUndertowDeploymentInfoCustomizer(UndertowProperties undertowProperties) {
        this.undertowProperties = undertowProperties;
    }
    
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

        LOGGER.info("Setting Undertow classLoader to " + undertowProperties.getClassPathResource() + " directory");
    }
}
