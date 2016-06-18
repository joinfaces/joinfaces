package com.github.persapiens.jsfboot.undertow;

import io.undertow.servlet.api.DeploymentInfo;
import java.io.IOException;
import java.net.MalformedURLException;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.undertow.UndertowDeploymentInfoCustomizer;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = UndertowSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class UndertowSpringBootAutoConfigurationIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private UndertowSpringBootAutoConfiguration undertowSpringBootAutoConfiguration;
    
	public void customize() throws MalformedURLException, IOException {
		UndertowEmbeddedServletContainerFactory factory = new UndertowEmbeddedServletContainerFactory();
        
        undertowSpringBootAutoConfiguration.customize(factory);

        UndertowDeploymentInfoCustomizer undertowDeploymentInfoCustomizer =
            factory.getDeploymentInfoCustomizers().iterator().next();
        
        DeploymentInfo deploymentInfo = new DeploymentInfo();
        
        undertowDeploymentInfoCustomizer.customize(deploymentInfo);
        
		assertThat(deploymentInfo.getResourceManager().getResource("test.txt")).isNotNull();
	}
}
