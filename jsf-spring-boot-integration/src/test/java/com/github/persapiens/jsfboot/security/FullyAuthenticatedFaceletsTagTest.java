package com.github.persapiens.jsfboot.security;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link FullyAuthenticatedFaceletsTag}.
 */
@Test
public class FullyAuthenticatedFaceletsTagTest {

	public void testFullyAuthenticated() {
		FullyAuthenticatedFaceletsTag tag = new FullyAuthenticatedFaceletsTag();
		assertThat("isFullyAuthenticated()")
            .isEqualTo(tag.getAccess());
	}

}
