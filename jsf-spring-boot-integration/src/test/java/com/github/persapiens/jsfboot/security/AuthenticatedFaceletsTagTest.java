package com.github.persapiens.jsfboot.security;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link AuthenticatedFaceletsTag}.
 */
@Test
public class AuthenticatedFaceletsTagTest {

	public void testAuthenticated() {
		AuthenticatedFaceletsTag tag = new AuthenticatedFaceletsTag();
		assertThat("isAuthenticated()")
            .isEqualTo(tag.getAccess());
	}

}
