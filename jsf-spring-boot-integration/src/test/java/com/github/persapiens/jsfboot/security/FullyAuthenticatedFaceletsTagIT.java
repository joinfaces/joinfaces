package com.github.persapiens.jsfboot.security;

import com.github.persapiens.jsfboot.mock.JsfIT;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.Test;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Unit tests for {@link FullyAuthenticatedFaceletsTag}.
 */
@SpringApplicationConfiguration(classes = SecurityConfiguration.class)
@Test
@WebAppConfiguration
public class FullyAuthenticatedFaceletsTagIT extends JsfIT {

	public void testFullyAuthenticated() {
		FullyAuthenticatedFaceletsTag tag = new FullyAuthenticatedFaceletsTag();
		assertThat("isFullyAuthenticated()")
            .isEqualTo(tag.getAccess());
	}

	public void testNotAuthorize() throws IOException {
        new SpringSecurityMock().init(null);
        
		FullyAuthenticatedFaceletsTag tag = new FullyAuthenticatedFaceletsTag();
		assertThat(tag.authorize())
            .isFalse();
	}

	public void testAuthorize() throws IOException {
        Authentication authentication = AuthenticationFactory.authentication(Roles.ROLE_A);
        new SpringSecurityMock().init(authentication);
        
		FullyAuthenticatedFaceletsTag tag = new FullyAuthenticatedFaceletsTag();
		assertThat(tag.authorize())
            .isTrue();
	}

}
