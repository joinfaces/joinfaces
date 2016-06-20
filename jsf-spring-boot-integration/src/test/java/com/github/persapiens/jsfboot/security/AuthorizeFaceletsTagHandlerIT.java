package com.github.persapiens.jsfboot.security;

import com.github.persapiens.jsfboot.mock.JsfIT;
import com.github.persapiens.jsfboot.mock.MockTagAttribute;
import java.io.IOException;
import org.testng.annotations.Test;
import org.springframework.security.core.Authentication;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Unit tests for {@link AuthorizeFaceletsTagHandler}.
 */
@SpringApplicationConfiguration(classes = SecurityConfiguration.class)
@Test
@WebAppConfiguration
public class AuthorizeFaceletsTagHandlerIT extends JsfIT {

	public void testNotAuthorize() throws IOException {
        new SpringSecurityMock().init(null);
        
		AuthorizeFaceletsTagHandler tag = new AuthorizeFaceletsTagHandler(
            getJsfMock().getMockTagConfig());
        
		tag.apply(null, null);
        
        assertThat(getJsfMock().getMockFaceletHandler().isApplied())
            .isFalse();
	}

	public void testAuthorizeAccess() throws IOException {
        Authentication authentication = AuthenticationFactory.authentication(Roles.ROLE_A);
        new SpringSecurityMock().init(authentication);
                
        getJsfMock().getMockTagAttributes().getTagAtributes().put(
            "access", new MockTagAttribute("hasAnyRole('ROLE_A')"));
        
		AuthorizeFaceletsTagHandler tag = new AuthorizeFaceletsTagHandler(
            getJsfMock().getMockTagConfig());
        
		tag.apply(null, null);
        
        assertThat(getJsfMock().getMockFaceletHandler().isApplied())
            .isTrue();
	}

	public void testAuthorizeIfAllGranted() throws IOException {
        Authentication authentication = AuthenticationFactory.authentication(Roles.ROLE_A);
        new SpringSecurityMock().init(authentication);
                
        getJsfMock().getMockTagAttributes().getTagAtributes().put(
            "ifAllGranted", new MockTagAttribute(Roles.ROLE_A));
        
		AuthorizeFaceletsTagHandler tag = new AuthorizeFaceletsTagHandler(
            getJsfMock().getMockTagConfig());
        
		tag.apply(getJsfMock().getMockFaceletContext(), null);
        
        assertThat(getJsfMock().getMockFaceletHandler().isApplied())
            .isTrue();
	}

}
