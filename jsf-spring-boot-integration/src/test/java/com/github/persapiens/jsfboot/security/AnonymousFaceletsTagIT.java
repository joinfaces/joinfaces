package com.github.persapiens.jsfboot.security;

import com.github.persapiens.jsfboot.mock.JsfIT;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

/**
 * Unit tests for {@link AnonymousFaceletsTag}.
 */
@SpringApplicationConfiguration(classes = SecurityConfiguration.class)
@Test
@WebAppConfiguration
public class AnonymousFaceletsTagIT extends JsfIT {
    
	public void testAnonymous() {
		AnonymousFaceletsTag tag = new AnonymousFaceletsTag();
		assertThat(tag.getAccess())
            .isEqualTo("isAnonymous()");
	}

	public void testNotAuthorize() throws IOException {
        new SpringSecurityMock().init(null);
        
		AnonymousFaceletsTag tag = new AnonymousFaceletsTag();
		assertThat(tag.authorize())
            .isFalse();
	}

	public void testAuthorize() throws IOException {
        new SpringSecurityMock().init(AuthenticationFactory.anonymous(Roles.ROLE_A));
        
		AnonymousFaceletsTag tag = new AnonymousFaceletsTag();
		assertThat(tag.authorize())
            .isTrue();
	}

}
