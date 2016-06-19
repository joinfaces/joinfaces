package com.github.persapiens.jsfboot.security;

import com.github.persapiens.jsfboot.mock.JsfIT;
import java.io.IOException;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Unit tests for {@link AuthorizeFaceletsTag}.
 */
@SpringApplicationConfiguration(classes = SecurityConfiguration.class)
@Test
@WebAppConfiguration
public class FaceletsTagUtilsIT extends JsfIT {

    public void testNothing() {
		assertThat(new FaceletsTagUtils())
            .isNotNull();
    }
    
    public void testAnonymous() throws IOException {
        Authentication authentication = AuthenticationFactory.authentication(Roles.ROLE_A);
        new SpringSecurityMock().init(authentication);
                
		assertThat(FaceletsTagUtils.isAnonymous())
            .isFalse();
	}

    public void testAuthenticated() throws IOException {
        Authentication authentication = AuthenticationFactory.authentication(Roles.ROLE_A);
        new SpringSecurityMock().init(authentication);
                
		assertThat(FaceletsTagUtils.isAuthenticated())
            .isTrue();
	}

    public void testFullyAuthenticated() throws IOException {
        Authentication authentication = AuthenticationFactory.authentication(Roles.ROLE_A);
        new SpringSecurityMock().init(authentication);
                
		assertThat(FaceletsTagUtils.isFullyAuthenticated())
            .isTrue();
	}

    public void testAreNotGranted() throws IOException {
        Authentication authentication = AuthenticationFactory.authentication(Roles.ROLE_A);
        new SpringSecurityMock().init(authentication);
                
		assertThat(FaceletsTagUtils.areNotGranted(Roles.ROLE_C))
            .isTrue();
	}

    public void testAreAllGranted() throws IOException {
        Authentication authentication = AuthenticationFactory.authentication(
            Roles.ROLE_A, Roles.ROLE_B);
        new SpringSecurityMock().init(authentication);
                
		assertThat(FaceletsTagUtils.areAllGranted(Roles.ROLE_A + "," + Roles.ROLE_C))
            .isFalse();
	}

    public void testAreAnyGranted() throws IOException {
        Authentication authentication = AuthenticationFactory.authentication(
            Roles.ROLE_A, Roles.ROLE_B);
        new SpringSecurityMock().init(authentication);
                
		assertThat(FaceletsTagUtils.areAnyGranted(Roles.ROLE_A + "," + Roles.ROLE_C))
            .isTrue();
	}

}
