package com.github.persapiens.jsfboot.security;

import com.github.persapiens.jsfboot.mock.JsfIT;
import com.github.persapiens.jsfboot.mock.MockTagAttribute;
import java.io.IOException;
import javax.faces.view.facelets.FaceletContext;
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

	public void testApplyFalse() throws IOException {
        new SpringSecurityMock().init(null);
        
		AuthorizeFaceletsTagHandler tag = new AuthorizeFaceletsTagHandler(
            getJsfMock().getMockTagConfig());
        
		tag.apply(null, null);
        
        assertThat(getJsfMock().getMockFaceletHandler().isApplied())
            .isFalse();
	}

	public void testApplyAccess() throws IOException {
        Authentication authentication = AuthenticationFactory.authentication(Roles.ROLE_A);
        new SpringSecurityMock().init(authentication);
                
        getJsfMock().getMockTagAttributes().getTagAttributes().put(
            "access", new MockTagAttribute("hasAnyRole('ROLE_A')"));
        
		AuthorizeFaceletsTagHandler tag = new AuthorizeFaceletsTagHandler(
            getJsfMock().getMockTagConfig());
        
		tag.apply(null, null);
        
        assertThat(getJsfMock().getMockFaceletHandler().isApplied())
            .isTrue();
	}

	public void testApplyIfAllGranted() throws IOException {
        Authentication authentication = AuthenticationFactory.authentication(Roles.ROLE_A);
        new SpringSecurityMock().init(authentication);
                
        getJsfMock().getMockTagAttributes().getTagAttributes().put(
            "ifAllGranted", new MockTagAttribute(Roles.ROLE_A));
        
		AuthorizeFaceletsTagHandler tag = new AuthorizeFaceletsTagHandler(
            getJsfMock().getMockTagConfig());
        
		tag.apply(getJsfMock().getMockFaceletContext(), null);
        
        assertThat(getJsfMock().getMockFaceletHandler().isApplied())
            .isTrue();
	}

	public void testApplyVar() throws IOException {
        Authentication authentication = AuthenticationFactory.authentication(Roles.ROLE_A);
        new SpringSecurityMock().init(authentication);
                
        MockTagAttribute myVariableTagAttribute = new MockTagAttribute("myVariable");
        getJsfMock().getMockTagAttributes().getTagAttributes().put(
            "var", myVariableTagAttribute);
        getJsfMock().getMockTagAttributes().getTagAttributes().put(
            "ifAllGranted", new MockTagAttribute(Roles.ROLE_A));
        
        FaceletContext mockFaceletContext = getJsfMock().getMockFaceletContext();
        
		AuthorizeFaceletsTagHandler tag = new AuthorizeFaceletsTagHandler(
            getJsfMock().getMockTagConfig());
        
		tag.apply(mockFaceletContext, null);
        
        assertThat(mockFaceletContext.getAttribute("myVariable"))
            .isEqualTo(Boolean.TRUE);
	}

}
