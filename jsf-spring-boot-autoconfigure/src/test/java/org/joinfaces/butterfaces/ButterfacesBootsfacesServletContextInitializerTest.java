package org.joinfaces.butterfaces;

import org.joinfaces.bootsfaces.BootsfacesProperties;
import org.joinfaces.bootsfaces.BootsfacesSpringBootAutoConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(
		properties = "jsf.bootsfaces.get-jquery-from-cdn=false",
		classes = {
				EmbeddedServletContainerAutoConfiguration.class,
				BootsfacesSpringBootAutoConfiguration.class,
				ButterfacesSpringBootAutoConfiguration.class,
				ButterfacesSpringBootAutoConfiguration.ButterfacesBootsfacesAutoConfiguration.class
		},
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class ButterfacesBootsfacesServletContextInitializerTest {

	@Autowired
	private WebApplicationContext applicationContext;

	@Autowired
	private BootsfacesProperties bootsfacesProperties;

	@Test
	public void testBootsfacesProperties() {
		assertThat(this.bootsfacesProperties.getGetJqueryFromCdn()).isFalse();
	}

	@Test
	public void testOverriddenInitParam() {
		assertThat(this.applicationContext.getBeansOfType(ServletContextInitializer.class)).size().isGreaterThanOrEqualTo(2);

		assertThat(this.applicationContext.getServletContext().getInitParameter("net.bootsfaces.get_jquery_from_cdn")).isEqualTo("true");
	}
}
