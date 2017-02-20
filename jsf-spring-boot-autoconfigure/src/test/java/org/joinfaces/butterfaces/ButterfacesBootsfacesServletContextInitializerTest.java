package org.joinfaces.butterfaces;

import org.joinfaces.bootsfaces.BootsfacesSpringBootAutoConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
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

	@Test
	public void testOverriddenInitParam() {
		assertThat(this.applicationContext.getServletContext().getInitParameter("net.bootsfaces.get_jquery_from_cdn")).isEqualTo("true");
	}
}
