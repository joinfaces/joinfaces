package com.github.persapiens.jsfboot.security;

import com.github.persapiens.jsfboot.mock.JsfIT;
import java.io.IOException;
import org.testng.annotations.Test;
import org.springframework.security.core.Authentication;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Unit tests for {@link AnonymousFaceletsTagHandler}.
 */
@SpringApplicationConfiguration(classes = SecurityConfiguration.class)
@Test
@WebAppConfiguration
public class AnonymousFaceletsTagHandlerIT extends JsfIT {

	public void testNotAuthorize() throws IOException {
        new SpringSecurityMock().init(null);
                
		AnonymousFaceletsTagHandler tag = new AnonymousFaceletsTagHandler(
            getJsfMock().getMockTagConfig());
        
		tag.apply(null, null);
        
        assertThat(getJsfMock().getMockFaceletHandler().isApplied())
            .isFalse();
	}

	public void testAuthorize() throws IOException {
        Authentication authentication = AuthenticationFactory.anonymous(Roles.ROLE_A);
        new SpringSecurityMock().init(authentication);
        
		AnonymousFaceletsTagHandler tag = new AnonymousFaceletsTagHandler(
            getJsfMock().getMockTagConfig());
        
		tag.apply(null, null);
        
        assertThat(getJsfMock().getMockFaceletHandler().isApplied())
            .isTrue();
	}

}
