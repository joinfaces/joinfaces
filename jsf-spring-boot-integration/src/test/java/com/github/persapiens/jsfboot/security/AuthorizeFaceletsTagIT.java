package com.github.persapiens.jsfboot.security;

import com.github.persapiens.jsfboot.mock.JsfIT;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Unit tests for {@link AuthorizeFaceletsTag}.
 */
@SpringApplicationConfiguration(classes = SecurityConfiguration.class)
@Test
@WebAppConfiguration
public class AuthorizeFaceletsTagIT extends JsfIT {

	public void testIfAllGrantedEmpty() {
		AuthorizeFaceletsTag tag = new AuthorizeFaceletsTag();
		tag.setIfAllGranted("");
		assertThat(tag.getAccess())
            .isNull();
	}

	public void testIfAnyGrantedEmpty() {
		AuthorizeFaceletsTag tag = new AuthorizeFaceletsTag();
		tag.setIfAnyGranted("");
		assertThat(tag.getAccess())
            .isNull();
	}

	public void testIfNotGrantedEmpty() {
		AuthorizeFaceletsTag tag = new AuthorizeFaceletsTag();
		tag.setIfNotGranted("");
		assertThat(tag.getAccess())
            .isNull();
	}

	public void testIfAllGrantedTwoRoles() {
		AuthorizeFaceletsTag tag = new AuthorizeFaceletsTag();
		tag.setIfAllGranted(Roles.ROLE_A);
		tag.setIfAllGranted(Roles.ROLE_B);
		assertThat(tag.getAccess())
            .isEqualTo("hasRole('ROLE_A') and hasRole('ROLE_B')");
	}

	public void testIfAllGrantedWithMultipleRoles() {
		AuthorizeFaceletsTag tag = new AuthorizeFaceletsTag();
		tag.setIfAllGranted("ROLE_A, ROLE_B, ROLE_C");
		assertThat(tag.getAccess())
            .isEqualTo("hasRole('ROLE_A') and hasRole('ROLE_B') and hasRole('ROLE_C')");
	}

	public void testIfAnyGrantedWithOneRole() {
		AuthorizeFaceletsTag tag = new AuthorizeFaceletsTag();
		tag.setIfAnyGranted(Roles.ROLE_A);
		assertThat(tag.getAccess())
            .isEqualTo("hasAnyRole('ROLE_A')");
	}

	public void testIfAnyGrantedWithMultipleRole() {
		AuthorizeFaceletsTag tag = new AuthorizeFaceletsTag();
		tag.setIfAnyGranted("ROLE_A, ROLE_B, ROLE_C");
		assertThat(tag.getAccess())
            .isEqualTo("hasAnyRole('ROLE_A','ROLE_B','ROLE_C')");
	}

	public void testIfNoneGrantedWithOneRole() {
		AuthorizeFaceletsTag tag = new AuthorizeFaceletsTag();
		tag.setIfNotGranted(Roles.ROLE_A);
		assertThat(tag.getAccess())
            .isEqualTo("!hasAnyRole('ROLE_A')");
	}

	public void testIfNoneGrantedWithMultipleRole() {
		AuthorizeFaceletsTag tag = new AuthorizeFaceletsTag();
		tag.setIfNotGranted("ROLE_A, ROLE_B, ROLE_C");
		assertThat(tag.getAccess())
            .isEqualTo("!hasAnyRole('ROLE_A','ROLE_B','ROLE_C')");
	}

	public void testIfAllAnyNotGranted() {
		AuthorizeFaceletsTag tag = new AuthorizeFaceletsTag();
		tag.setIfAllGranted(Roles.ROLE_A);
		tag.setIfAnyGranted(Roles.ROLE_B);
		tag.setIfNotGranted(Roles.ROLE_C);
		assertThat(tag.getAccess())
            .isEqualTo("hasRole('ROLE_A') and hasAnyRole('ROLE_B') and !hasAnyRole('ROLE_C')");
	}

}
