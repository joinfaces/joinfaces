package com.github.persapiens.jsfboot.security;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link AuthorizeFaceletsTag}.
 */
@Test
public class AuthorizeFaceletsTagTest {

    private static String ROLE_A = "ROLE_A";
    
	public void testIfAllGrantedWithOneRole() {
		AuthorizeFaceletsTag tag = new AuthorizeFaceletsTag();
		tag.setIfAllGranted(ROLE_A);
		assertThat("hasRole('ROLE_A')")
            .isEqualTo(tag.getAccess());
	}

	public void testIfAllGrantedWithMultipleRoles() {
		AuthorizeFaceletsTag tag = new AuthorizeFaceletsTag();
		tag.setIfAllGranted("ROLE_A, ROLE_B, ROLE_C");
		assertThat("hasRole('ROLE_A') and hasRole('ROLE_B') and hasRole('ROLE_C')")
            .isEqualTo(tag.getAccess());            
	}

	public void testIfAnyGrantedWithOneRole() {
		AuthorizeFaceletsTag tag = new AuthorizeFaceletsTag();
		tag.setIfAnyGranted(ROLE_A);
		assertThat("hasAnyRole('ROLE_A')")
            .isEqualTo(tag.getAccess());
	}

	public void testIfAnyGrantedWithMultipleRole() {
		AuthorizeFaceletsTag tag = new AuthorizeFaceletsTag();
		tag.setIfAnyGranted("ROLE_A, ROLE_B, ROLE_C");
		assertThat("hasAnyRole('ROLE_A','ROLE_B','ROLE_C')")
            .isEqualTo(tag.getAccess());
	}

	public void testIfNoneGrantedWithOneRole() {
		AuthorizeFaceletsTag tag = new AuthorizeFaceletsTag();
		tag.setIfNotGranted(ROLE_A);
		assertThat("!hasAnyRole('ROLE_A')")
            .isEqualTo(tag.getAccess());
	}

	public void testIfNoneGrantedWithMultipleRole() {
		AuthorizeFaceletsTag tag = new AuthorizeFaceletsTag();
		tag.setIfNotGranted("ROLE_A, ROLE_B, ROLE_C");
		assertThat("!hasAnyRole('ROLE_A','ROLE_B','ROLE_C')")
            .isEqualTo(tag.getAccess());
	}

	public void testIfAllAnyNotGranted() {
		AuthorizeFaceletsTag tag = new AuthorizeFaceletsTag();
		tag.setIfAllGranted(ROLE_A);
		tag.setIfAnyGranted("ROLE_B");
		tag.setIfNotGranted("ROLE_C");
		assertThat("hasRole('ROLE_A') and hasAnyRole('ROLE_B') and !hasAnyRole('ROLE_C')")
            .isEqualTo(tag.getAccess());
	}

}
