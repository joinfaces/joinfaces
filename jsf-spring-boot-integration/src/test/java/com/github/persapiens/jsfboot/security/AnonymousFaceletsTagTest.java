package com.github.persapiens.jsfboot.security;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link AnonymousFaceletsTag}.
 */
@Test
public class AnonymousFaceletsTagTest {

	public void testAnonymous() {
		AnonymousFaceletsTag tag = new AnonymousFaceletsTag();
		assertThat("isAnonymous()")
            .isEqualTo(tag.getAccess());
	}

}
