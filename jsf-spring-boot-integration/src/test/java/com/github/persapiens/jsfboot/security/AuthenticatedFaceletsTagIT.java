package com.github.persapiens.jsfboot.security;

import com.github.persapiens.jsfboot.mock.JsfIT;
import java.io.IOException;
import org.testng.annotations.Test;
import org.springframework.security.core.Authentication;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Unit tests for {@link AuthenticatedFaceletsTag}.
 */
@SpringApplicationConfiguration(classes = SecurityConfiguration.class)
@Test
@WebAppConfiguration
public class AuthenticatedFaceletsTagIT extends JsfIT {

	public void testAuthenticated() {
		AuthenticatedFaceletsTag tag = new AuthenticatedFaceletsTag();
		assertThat(tag.getAccess())
            .isEqualTo("isAuthenticated()");
	}

	public void testNotAuthorize() throws IOException {
        new SpringSecurityMock().init(null);
        
		AuthenticatedFaceletsTag tag = new AuthenticatedFaceletsTag();
		assertThat(tag.authorize())
            .isFalse();
	}

	public void testAuthorize() throws IOException {
        Authentication authentication = AuthenticationFactory.authentication(Roles.ROLE_A);
        new SpringSecurityMock().init(authentication);
        
		AuthenticatedFaceletsTag tag = new AuthenticatedFaceletsTag();
		assertThat(tag.authorize())
            .isTrue();
	}

}
